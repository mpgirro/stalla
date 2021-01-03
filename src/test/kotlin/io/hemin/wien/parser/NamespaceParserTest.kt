package io.hemin.wien.parser

import assertk.fail
import io.hemin.wien.builder.fake.episode.FakeEpisodeBuilder
import io.hemin.wien.builder.fake.podcast.FakePodcastBuilder
import io.hemin.wien.util.DomBuilderFactory
import io.hemin.wien.util.NodeListWrapper.Companion.asListOfNodes
import org.w3c.dom.Document
import org.w3c.dom.Node
import javax.xml.parsers.DocumentBuilder

/** Base class for unit test classes checking implementations of [NamespaceParser]. */
internal abstract class NamespaceParserTest {

    /** The [NamespaceParser] implementation that the test class tests. */
    abstract val parser: NamespaceParser

    private val domBuilder: DocumentBuilder = DomBuilderFactory.newBuilder()

    /** Parse [channel] and add result to [builder]. */
    protected fun parseChannelNode(builder: FakePodcastBuilder, channel: Node) {
        for (element in channel.childNodes.asListOfNodes()) {
            parser.parse(builder, element)
        }
    }

    /** Parse [item] and add result to [builder]. */
    protected fun parseItemNode(builder: FakeEpisodeBuilder, item: Node) {
        for (element in item.childNodes.asListOfNodes()) {
            parser.parse(builder, element)
        }
    }

    /** Finds a DOM node matching [elementName] in a resource loaded from given [filePath]. */
    protected fun nodeFromResource(elementName: String, filePath: String): Node {
        val resourceUrl = this.javaClass.getResource(filePath)
            ?: fail("The resource '$filePath' does not exist")
        val doc: Document = domBuilder.parse(resourceUrl.openStream())

        var result: Node? = null
        for (node in doc.childNodes.asListOfNodes()) {
            if (elementName == node.localName) {
                result = node
            }
        }
        return result ?: fail("Unable to find the element '$elementName' in resource '$filePath'")
    }
}
