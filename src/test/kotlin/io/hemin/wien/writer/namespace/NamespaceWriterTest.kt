package io.hemin.wien.writer.namespace

import assertk.Assert
import assertk.assertThat
import assertk.assertions.support.appendName
import assertk.assertions.support.expected
import assertk.fail
import io.hemin.wien.documentFromResource
import io.hemin.wien.doesNotExist
import io.hemin.wien.dom.DomBuilderFactory
import io.hemin.wien.dom.appendElement
import io.hemin.wien.dom.asListOfNodes
import io.hemin.wien.dom.asString
import io.hemin.wien.dom.findElementByName
import io.hemin.wien.dom.findNodeByXPath
import io.hemin.wien.dom.findNodesByXPath
import io.hemin.wien.dom.getAttributeByName
import io.hemin.wien.dom.isEmpty
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import io.hemin.wien.model.episode.anEpisode
import io.hemin.wien.model.podcast.aPodcast
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.writer.NamespaceWriter
import org.w3c.dom.Attr
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.xmlunit.builder.DiffBuilder
import org.xmlunit.builder.Input
import org.xmlunit.diff.Diff

internal abstract class NamespaceWriterTest {

    private val documentBuilder = DomBuilderFactory.newDocumentBuilder()

    protected abstract val writer: NamespaceWriter

    protected fun Element.diffFromExpected(xpath: String, resourcePath: String = "/xml/writer-fixtures.xml"): Diff =
        DiffBuilder.compare(Input.fromNode(this).build())
            .withTest(Input.fromNode(expectedNode(xpath, resourcePath)))
            .ignoreWhitespace()
            .build()

    private fun expectedNode(nodePath: String, resourcePath: String): Node {
        val document = documentFromResource(resourcePath)
        val node = document.findNodeByXPath(nodePath)
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

    protected fun writePodcastDataXPath(
        xPath: String,
        podcast: Podcast = aPodcast(),
        assertions: (element: Element) -> Unit
    ) {
        val itemElement = createChannelElement()
        writer.tryWritingPodcastData(podcast, itemElement)

        val actual = itemElement.findNodeByXPath(xPath)
            ?: fail("No tag matching `$xPath` was written")
        if (actual !is Element) fail("The XPath `$xPath` does not match an element")

        assertions(actual)
    }

    protected fun writePodcastDataXPathMultiple(
        xPath: String,
        podcast: Podcast = aPodcast(),
        assertions: (elements: List<Element>) -> Unit
    ) {
        val itemElement = createChannelElement()
        writer.tryWritingPodcastData(podcast, itemElement)

        val actual = itemElement.findNodesByXPath(xPath)
            ?: fail("No tags matching `$xPath` found")
        if (actual.isEmpty()) fail("No tag matching `$xPath` was written")
        val actualElements = actual.asListOfNodes().filterIsInstance(Element::class.java)
        if (actual.length != actualElements.size) fail("Not all nodes matched by the `$xPath` are Elements")

        assertions(actualElements)
    }

    protected fun assertTagIsNotWrittenToPodcast(
        podcast: Podcast,
        localName: String,
        namespace: FeedNamespace? = writer.namespace
    ) {
        val itemElement = createChannelElement()
        writer.tryWritingPodcastData(podcast, itemElement)

        assertThat(itemElement.findElementByName(localName, namespace)).doesNotExist()
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

    protected fun writeEpisodeDataXPath(
        xPath: String,
        episode: Episode = anEpisode(),
        assertions: (element: Element) -> Unit
    ) {
        val itemElement = createChannelElement().createItemElement()
        writer.tryWritingEpisodeData(episode, itemElement)

        val actual = itemElement.findNodeByXPath(xPath)
            ?: fail("No tag matching `$xPath` was written")
        if (actual !is Element) fail("The XPath `$xPath` does not match an element")

        assertions(actual)
    }

    protected fun writeEpisodeDataXPathMultiple(
        xPath: String,
        episode: Episode = anEpisode(),
        assertions: (elements: List<Element>) -> Unit
    ) {
        val itemElement = createChannelElement().createItemElement()
        writer.tryWritingEpisodeData(episode, itemElement)

        val actual = itemElement.findNodesByXPath(xPath)
            ?: fail("No tag matching `$xPath` was written")
        val actualElements = actual.asListOfNodes().filterIsInstance(Element::class.java)
        if (actual.length != actualElements.size) fail("Not all nodes matched by the `$xPath` are Elements")

        assertions(actualElements)
    }

    protected fun assertTagIsNotWrittenToEpisode(
        episode: Episode,
        localName: String,
        namespace: FeedNamespace? = writer.namespace
    ) {
        val itemElement = createChannelElement().createItemElement()
        writer.tryWritingEpisodeData(episode, itemElement)

        assertThat(itemElement.findElementByName(localName, namespace)).doesNotExist()
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

    protected fun Assert<Element>.containsElement(
        localName: String,
        namespace: FeedNamespace? = writer.namespace
    ): Assert<Element> {
        given { element ->
            val actual = element.findElementByName(localName, namespace)
            if (actual != null) return assertThat(actual)
            val tagName = if (namespace != null) "${namespace.prefix}:$localName" else localName
            expected("to contain element $tagName:\n${element.asString()}", expected = "<$tagName>...</$tagName>", actual = "null")
        }
        throw IllegalStateException("Should never reach here")
    }

    protected fun Assert<Element>.hasAttribute(
        localName: String,
        namespace: FeedNamespace? = writer.namespace
    ): Assert<Attr> {
        given { element ->
            val actual = element.getAttributeByName(localName, namespace)
            val attributeName = if (namespace != null) "${namespace.prefix}:$localName" else localName
            if (actual != null) return assertThat(actual, appendName(" $attributeName attribute"))
            expected("to contain attribute $attributeName:\n${element.asString()}", expected = "$attributeName=\"...\"", actual = "null")
        }
        throw IllegalStateException("Should never reach here")
    }

    protected fun Assert<Element>.doesNotContainElement(
        localName: String,
        namespace: FeedNamespace? = writer.namespace
    ) = given { element ->
        val actual = element.findElementByName(localName, namespace) ?: return@given
        val tagName = if (namespace != null) "${namespace.prefix}:$localName" else localName
        expected("NOT to contain element $tagName", expected = "null", actual = actual.asString())
    }
}
