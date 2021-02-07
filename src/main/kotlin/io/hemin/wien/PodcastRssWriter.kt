package io.hemin.wien

import io.hemin.wien.dom.DomBuilderFactory
import io.hemin.wien.dom.asElement
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.writer.NamespaceWriter
import io.hemin.wien.writer.namespace.AtomWriter
import io.hemin.wien.writer.namespace.BitloveWriter
import io.hemin.wien.writer.namespace.ContentWriter
import io.hemin.wien.writer.namespace.FeedpressWriter
import io.hemin.wien.writer.namespace.FyydWriter
import io.hemin.wien.writer.namespace.GooglePlayWriter
import io.hemin.wien.writer.namespace.ITunesWriter
import io.hemin.wien.writer.namespace.PodcastNamespaceWriter
import io.hemin.wien.writer.namespace.PodloveSimpleChapterWriter
import io.hemin.wien.writer.namespace.RssWriter
import org.w3c.dom.Document
import org.w3c.dom.Element
import java.io.File
import java.io.OutputStream
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

object PodcastRssWriter {

    // Writers are sorted in order of "importance"
    private val writers: List<NamespaceWriter> = listOf(
        RssWriter,
        ContentWriter,
        ITunesWriter,
        GooglePlayWriter,
        AtomWriter,
        BitloveWriter,
        FeedpressWriter,
        FyydWriter,
        PodloveSimpleChapterWriter,
        PodcastNamespaceWriter
    )

    private val supportedNamespaces = writers.mapNotNull { it.namespace }

    private val documentBuilder = DomBuilderFactory.newDocumentBuilder()

    private val transformer = TransformerFactory.newInstance()
        .newTransformer()
        .apply {
            setOutputProperty(OutputKeys.INDENT, "yes")
            setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2")
        }

    /**
     * Writes a [Podcast] to a [File] as an RSS feed.
     *
     * @param podcast The [Podcast] to write out.
     * @param file The [File] to write to. Any contents will be overwritten.
     */
    fun writeRssFeed(podcast: Podcast, file: File) {
        file.outputStream()
            .use { outputStream -> writeRssFeed(podcast, outputStream) }
    }

    /**
     * Writes a [Podcast] to a [OutputStream] as an RSS feed.
     *
     * @param podcast The [Podcast] to write out.
     * @param stream The [OutputStream] to write to.
     */
    fun writeRssFeed(podcast: Podcast, stream: OutputStream) {
        val document = writeToDocument(podcast)
        val source = DOMSource(document)

        stream.bufferedWriter().use { writer ->
            val result = StreamResult(writer)
            transformer.transform(source, result)
        }
    }

    private fun writeToDocument(podcast: Podcast): Document {
        val document = documentBuilder.newDocument()
        val rssElement = createRssElement(document)

        val channelElement = rssElement.appendChild(document.createElement("channel"))
            .asElement()

        for (writer in writers) {
            writer.tryWritingPodcastData(podcast, channelElement)
        }

        for (episode in podcast.episodes) {
            val itemElement = channelElement.appendChild(document.createElement("item"))
                .asElement()

            for (writer in writers) {
                writer.tryWritingEpisodeData(episode, itemElement)
            }
        }

        return document
    }

    private fun createRssElement(document: Document) = document.appendChild(document.createElement("rss"))
        .asElement()
        .addNamespaces(supportedNamespaces)
        .apply {
            setAttribute("version", "2.0")
            setAttribute("encoding", "UTF-8")
        }

    private fun Element.addNamespaces(namespaces: List<FeedNamespace>): Element {
        for (ns in namespaces) {
            setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:${ns.prefix}", ns.uri)
        }
        return this
    }
}
