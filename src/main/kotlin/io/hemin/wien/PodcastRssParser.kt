package io.hemin.wien

import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.validating.episode.ValidatingEpisodeBuilder
import io.hemin.wien.builder.validating.podcast.ValidatingPodcastBuilder
import io.hemin.wien.model.Podcast
import io.hemin.wien.parser.NamespaceParser
import io.hemin.wien.parser.namespace.AtomParser
import io.hemin.wien.parser.namespace.BitloveParser
import io.hemin.wien.parser.namespace.ContentParser
import io.hemin.wien.parser.namespace.FeedpressParser
import io.hemin.wien.parser.namespace.FyydParser
import io.hemin.wien.parser.namespace.GooglePlayParser
import io.hemin.wien.parser.namespace.ITunesParser
import io.hemin.wien.parser.namespace.PodloveSimpleChapterParser
import io.hemin.wien.parser.namespace.RssParser
import io.hemin.wien.util.DomBuilderFactory
import io.hemin.wien.util.asListOfNodes
import io.hemin.wien.util.findElementByName
import io.hemin.wien.util.isNotEmpty
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.Text
import org.xml.sax.InputSource
import java.io.File
import java.io.InputStream
import javax.xml.parsers.DocumentBuilder

@Suppress("unused")
object PodcastRssParser {

    private val parsers: List<NamespaceParser> = listOf(
        AtomParser(),
        BitloveParser(),
        ContentParser(),
        FeedpressParser(),
        FyydParser(),
        GooglePlayParser(),
        ITunesParser(),
        PodloveSimpleChapterParser(),
        RssParser()
    )

    /** Set of all XML namespaces supported when parsing documents. */
    val supportedNamespaces: Set<String> = parsers.mapNotNull { parser -> parser.namespace?.uri }
        .toSet()

    private val builder: DocumentBuilder = DomBuilderFactory.newDocumentBuilder()

    /**
     * Parse the content of the given URI as an XML document
     * and return a [Podcast] if the XML document is an RSS feed.
     *
     * @param uri The location of the content to be parsed.
     * @return A [Podcast] if the XML document behind the URI is an RSS document, otherwise null.
     */
    fun parse(uri: String): Podcast? = parse(builder.parse(uri))

    /**
     * Parse the content of the given input stream as an XML document
     * and return a [Podcast] if the XML document is an RSS feed.
     *
     * @param inputStream InputStream containing the content to be parsed.
     * @return A [Podcast] if the XML document behind the input stream is an RSS document, otherwise null.
     */
    fun parse(inputStream: InputStream): Podcast? = parse(builder.parse(inputStream))

    /**
     * Parse the content of the given input stream as an XML document
     * and return a [Podcast] if the XML document is an RSS feed.
     *
     * @param inputStream InputStream containing the content to be parsed.
     * @param systemId Provide a base for resolving relative URIs.
     * @return A [Podcast] if the XML document behind the input stream is an RSS document, otherwise null.
     */
    fun parse(inputStream: InputStream, systemId: String): Podcast? = parse(builder.parse(inputStream, systemId))

    /**
     * Parse the content of the given file as an XML document
     * and return a [Podcast] if the XML document is an RSS feed.
     *
     * @param file File containing the content to be parsed.
     * @return A [Podcast] if the XML document behind the file is an RSS document, otherwise null.
     */
    fun parse(file: File): Podcast? = parse(builder.parse(file))

    /**
     * Parse the content of the given input source as an XML document
     * and return a [Podcast] if the XML document is an RSS feed.
     *
     * @param inputSource InputSource containing the content to be parsed.
     * @return A [Podcast] if the XML document behind the input source is an RSS document, otherwise null.
     */
    fun parse(inputSource: InputSource): Podcast? = parse(builder.parse(inputSource))

    /**
     * Parse the content of the given XML document and return a [Podcast] if the XML document is an RSS feed.
     *
     * @param document Document containing the content to be parsed.
     * @return A [Podcast] if the XML document is an RSS document, otherwise null.
     */
    fun parse(document: Document): Podcast? {
        val channel = document.findRssChannelElement() ?: return null
        return channel.parseChannelElement()
    }

    private fun Document.findRssChannelElement() =
        findElementByName("rss") { rss -> rss.childNodes.isNotEmpty() }
            ?.findElementByName("channel") { channel -> channel.childNodes.isNotEmpty() }

    private fun Element.parseChannelElement(): Podcast? = ifTagNameIs("channel") {
        val builder = ValidatingPodcastBuilder()
        for (element in childNodes.asListOfNodes()) {
            when {
                element is Text -> Unit
                element.nodeName == "item" -> {
                    val episodeBuilder = element.parseEpisodeNode() ?: continue
                    builder.addEpisodeBuilder(episodeBuilder)
                }
                else -> {
                    for (parser in parsers) {
                        parser.tryParsingChannelChildNode(builder, element)
                    }
                }
            }
        }
        builder.build()
    }

    private fun Node.parseEpisodeNode(): EpisodeBuilder? = ifTagNameIs("item") {
        val builder: EpisodeBuilder = ValidatingEpisodeBuilder()
        for (element in childNodes.asListOfNodes()) {
            for (parser in parsers) {
                parser.tryParsingItemChildNode(builder, element)
            }
        }
        builder
    }

    private fun <T> Node.ifTagNameIs(tagName: String, block: () -> T): T? =
        if (namespaceURI == null && nodeName == tagName) {
            block()
        } else {
            null
        }
}
