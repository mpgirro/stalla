package dev.stalla

import dev.stalla.dom.DomBuilderFactory
import dev.stalla.dom.asElement
import dev.stalla.model.Podcast
import dev.stalla.util.FeedNamespace
import dev.stalla.writer.NamespaceWriter
import dev.stalla.writer.namespace.AtomWriter
import dev.stalla.writer.namespace.BitloveWriter
import dev.stalla.writer.namespace.ContentWriter
import dev.stalla.writer.namespace.FeedpressWriter
import dev.stalla.writer.namespace.FyydWriter
import dev.stalla.writer.namespace.GoogleplayWriter
import dev.stalla.writer.namespace.ItunesWriter
import dev.stalla.writer.namespace.PodcastindexWriter
import dev.stalla.writer.namespace.PodloveSimpleChapterWriter
import dev.stalla.writer.namespace.RssWriter
import org.w3c.dom.Document
import org.w3c.dom.Element
import java.io.File
import java.io.OutputStream
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

/**
 * The main entry point to write a [Podcast] instance to an RSS feed.
 * You can use any of the `writeRssFeed` overloads.
 *
 * @since 1.0.0
 */
public object PodcastRssWriter {

    // Writers are sorted in order of "importance"
    private val writers: List<NamespaceWriter> = listOf(
        RssWriter,
        ContentWriter,
        ItunesWriter,
        GoogleplayWriter,
        AtomWriter,
        BitloveWriter,
        FeedpressWriter,
        FyydWriter,
        PodloveSimpleChapterWriter,
        PodcastindexWriter
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
    public fun write(podcast: Podcast, file: File) {
        file.outputStream()
            .use { outputStream -> write(podcast, outputStream) }
    }

    /**
     * Writes a [Podcast] to a [OutputStream] as an RSS feed.
     *
     * @param podcast The [Podcast] to write out.
     * @param stream The [OutputStream] to write to.
     */
    public fun write(podcast: Podcast, stream: OutputStream) {
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
