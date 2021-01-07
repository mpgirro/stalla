package io.hemin.wien.writer.namespace

import assertk.assertThat
import assertk.assertions.isNull
import assertk.fail
import io.hemin.wien.documentFromResource
import io.hemin.wien.dom.DomBuilderFactory
import io.hemin.wien.dom.appendElement
import io.hemin.wien.dom.findElementByName
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import io.hemin.wien.model.episode.anEpisode
import io.hemin.wien.model.podcast.aPodcast
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.writer.NamespaceWriter
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.xmlunit.builder.DiffBuilder
import org.xmlunit.builder.Input
import org.xmlunit.diff.Diff
import javax.xml.namespace.NamespaceContext
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

internal abstract class NamespaceWriterTest {

    private val xpath = XPathFactory.newDefaultInstance().newXPath()
        .apply { namespaceContext = FeedNamespaceContext }

    private val documentBuilder = DomBuilderFactory.newDocumentBuilder()

    protected abstract val writer: NamespaceWriter

    protected fun Element.diffFromExpected(xpath: String, resourcePath: String = "/xml/writer-fixtures.xml"): Diff =
        DiffBuilder.compare(Input.fromNode(this).build())
            .withTest(Input.fromNode(expectedNode(xpath, resourcePath)))
            .ignoreWhitespace()
            .build()

    private fun expectedNode(nodePath: String, resourcePath: String): Node {
        val document = documentFromResource(resourcePath)
        val node = xpath.evaluate(nodePath, document, XPathConstants.NODE) as? Node
        requireNotNull(node) { "The node with XPath '$nodePath' could not be found in resource '$resourcePath'" }
        return node
    }

    protected fun writePodcastData(
        localName: String,
        namespace: FeedNamespace? = writer.namespace,
        podcast: Podcast = aPodcast(),
        assertions: (element: Element) -> Unit
    ) {
        val itemElement = createChannelElement()
        writer.tryWritingPodcastData(podcast, itemElement)

        val actualElement = itemElement.findElementByName(localName, namespace)
            ?: fail("The <${namespace?.prefix ?: ""}:$localName> tag was not written")

        assertions(actualElement)
    }

    protected fun assertTagIsNotWrittenToPodcast(
        podcast: Podcast,
        localName: String,
        namespace: FeedNamespace? = writer.namespace
    ) {
        val itemElement = createChannelElement()
        writer.tryWritingPodcastData(podcast, itemElement)

        assertThat(itemElement.findElementByName(localName, namespace)).isNull()
    }

    protected fun writeEpisodeData(
        localName: String,
        namespace: FeedNamespace? = writer.namespace,
        episode: Episode = anEpisode(),
        assertions: (element: Element) -> Unit
    ) {
        val itemElement = createChannelElement().createItemElement()
        writer.tryWritingEpisodeData(episode, itemElement)

        val actualElement = itemElement.findElementByName(localName, namespace)
            ?: fail("The <${namespace?.prefix ?: ""}:$localName> tag was not written")

        assertions(actualElement)
    }

    protected fun assertTagIsNotWrittenToEpisode(
        episode: Episode,
        localName: String,
        namespace: FeedNamespace? = writer.namespace
    ) {
        val itemElement = createChannelElement().createItemElement()
        writer.tryWritingEpisodeData(episode, itemElement)

        assertThat(itemElement.findElementByName(localName, namespace)).isNull()
    }

    protected fun createChannelElement(): Element {
        val document = documentBuilder.newDocument()

        var channel: Element? = null
        document.appendElement("rss") {
            channel = appendElement("channel")
        }
        return channel!!
    }

    protected fun Element.createItemElement(): Element {
        require(namespaceURI == null && nodeName == "channel") { " Only a <channel> can contain items" }
        return appendElement("item")
    }
}

private object FeedNamespaceContext : NamespaceContext {

    override fun getNamespaceURI(prefix: String?): String? {
        if (prefix == null) return null
        return FeedNamespace.values().find { it.prefix == prefix }?.uri
    }

    override fun getPrefix(namespaceURI: String?): String? {
        if (namespaceURI == null) return null
        return FeedNamespace.values().find { it.uri == namespaceURI }?.prefix
    }

    override fun getPrefixes(namespaceURI: String?): Iterator<String> {
        val prefix = getPrefix(namespaceURI) ?: return emptyList<String>().iterator()
        return listOf(prefix).iterator()
    }
}
