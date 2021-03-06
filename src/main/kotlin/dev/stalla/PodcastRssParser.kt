package dev.stalla

import dev.stalla.PodcastRssParser.parse
import dev.stalla.builder.episode.EpisodeBuilder
import dev.stalla.builder.episode.ProvidingEpisodeBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodeBuilder
import dev.stalla.builder.validating.podcast.ValidatingPodcastBuilder
import dev.stalla.dom.DomBuilderFactory
import dev.stalla.dom.asListOfNodes
import dev.stalla.dom.findElementByName
import dev.stalla.dom.isNotEmpty
import dev.stalla.model.Podcast
import dev.stalla.parser.NamespaceParser
import dev.stalla.parser.namespace.AtomParser
import dev.stalla.parser.namespace.BitloveParser
import dev.stalla.parser.namespace.ContentParser
import dev.stalla.parser.namespace.FeedpressParser
import dev.stalla.parser.namespace.FyydParser
import dev.stalla.parser.namespace.GoogleplayParser
import dev.stalla.parser.namespace.ItunesParser
import dev.stalla.parser.namespace.PodcastindexParser
import dev.stalla.parser.namespace.PodloveSimpleChapterParser
import dev.stalla.parser.namespace.RssParser
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.Text
import org.xml.sax.InputSource
import org.xml.sax.SAXException
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.io.Reader
import javax.xml.parsers.DocumentBuilder
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * The main entry point to parse a podcast RSS feed. You can use
 * any of the [parse] overloads to obtain an instance of [Podcast].
 *
 * @since 1.0.0
 */
@Suppress("unused")
public object PodcastRssParser {

    private val parsers: List<NamespaceParser> = listOf(
        AtomParser,
        BitloveParser,
        ContentParser,
        FeedpressParser,
        FyydParser,
        GoogleplayParser,
        ItunesParser,
        PodloveSimpleChapterParser,
        RssParser,
        PodcastindexParser
    )

    private val builder: DocumentBuilder = DomBuilderFactory.newDocumentBuilder()

    /**
     * Parse the content of the given URI as an XML document
     * and return a [Podcast] if the XML document is an RSS feed.
     *
     * @param uri The location of the content to be parsed.
     * @return A [Podcast] if the XML document behind the URI is an RSS document, otherwise `null`.
     * @throws IOException If any IO errors occur.
     * @throws SAXException If any XML parsing errors occur.
     * @throws NullPointerException If [uri] is `null`.
     */
    @JvmStatic
    @Throws(IOException::class, SAXException::class)
    public fun parse(uri: String): Podcast? = parse(InputSource(uri))

    /**
     * Parse the content of the given input stream as an XML document
     * and return a [Podcast] if the XML document is an RSS feed.
     *
     * @param inputStream InputStream containing the content to be parsed. It will not be closed automatically.
     * @return A [Podcast] if the XML document behind the input stream is an RSS document, otherwise `null`.
     * @throws IOException If any IO errors occur.
     * @throws SAXException If any XML parsing errors occur.
     * @throws NullPointerException If [inputStream] is `null`.
     */
    @JvmStatic
    @Throws(IOException::class, SAXException::class)
    public fun parse(inputStream: InputStream): Podcast? = inputStream.bufferedReader().use { parse(it) }

    /**
     * Parse the content of the given input stream as an XML document
     * and return a [Podcast] if the XML document is an RSS feed.
     *
     * @param inputStream InputStream containing the content to be parsed.
     * @param systemId Provide a base for resolving relative URIs.
     * @return A [Podcast] if the XML document behind the input stream is an RSS document, otherwise `null`.
     * @throws IOException If any IO errors occur.
     * @throws SAXException If any XML parsing errors occur.
     * @throws NullPointerException If [inputStream] is `null`.
     */
    @JvmStatic
    @Throws(IOException::class, SAXException::class)
    public fun parse(inputStream: InputStream, systemId: String?): Podcast? =
        parse(InputSource(inputStream).also { it.systemId = systemId })

    /**
     * Parse the content of the given file as an XML document
     * and return a [Podcast] if the XML document is an RSS feed.
     *
     * @param file File containing the content to be parsed.
     * @return A [Podcast] if the XML document behind the file is an RSS document, otherwise `null`.
     * @throws IOException If any IO errors occur.
     * @throws SAXException If any XML parsing errors occur.
     * @throws NullPointerException If [file] is `null`.
     */
    @JvmStatic
    @Throws(IOException::class, SAXException::class)
    public fun parse(file: File): Podcast? = file.bufferedReader().use { parse(it) }

    /**
     * Parse the content of the given [Reader] as an XML document
     * and return a [Podcast] if the XML document is an RSS feed.
     *
     * @param reader Reader containing the content to be parsed. It will not be closed automatically.
     * @return A [Podcast] if the XML document behind the reader is an RSS document, otherwise `null`.
     * @throws IOException If any IO errors occur.
     * @throws SAXException If any XML parsing errors occur.
     * @throws NullPointerException If [reader] is `null`.
     */
    @JvmStatic
    @Throws(IOException::class, SAXException::class)
    public fun parse(reader: Reader): Podcast? = parse(InputSource(reader))

    /**
     * Parse the content of the given input source as an XML document
     * and return a [Podcast] if the XML document is an RSS feed.
     *
     * @param inputSource InputSource containing the content to be parsed. It will not be closed automatically.
     * @return A [Podcast] if the XML document behind the input source is an RSS document, otherwise null.
     * @throws IOException If any IO errors occur.
     * @throws SAXException If any XML parsing errors occur.
     * @throws NullPointerException If [inputSource] is `null`.
     */
    @JvmStatic
    @Throws(IOException::class, SAXException::class)
    public fun parse(inputSource: InputSource): Podcast? = parse(builder.parse(inputSource))

    /**
     * Parse the content of the given XML document and return a [Podcast] if the XML document is an RSS feed.
     *
     * @param document Document containing the content to be parsed.
     * @return A [Podcast] if the XML document is an RSS document, otherwise `null`.
     * @throws NullPointerException If [document] is `null`.
     */
    @JvmStatic
    public fun parse(document: Document): Podcast? {
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
                        parser.tryParsingChannelChildNode(element, builder)
                    }
                }
            }
        }
        builder.build()
    }

    private fun Node.parseEpisodeNode(): EpisodeBuilder? = ifTagNameIs("item") {
        val builder: ProvidingEpisodeBuilder = ValidatingEpisodeBuilder()
        for (element in childNodes.asListOfNodes()) {
            for (parser in parsers) {
                parser.tryParsingItemChildNode(element, builder)
            }
        }
        builder
    }

    private fun <T> Node.ifTagNameIs(tagName: String, block: () -> T): T? {
        contract {
            callsInPlace(block, InvocationKind.AT_MOST_ONCE)
        }
        return if (namespaceURI == null && nodeName == tagName) {
            block()
        } else {
            null
        }
    }
}
