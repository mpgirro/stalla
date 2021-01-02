package io.hemin.wien.parser

import io.hemin.wien.builder.EpisodeBuilder
import io.hemin.wien.builder.PodcastBuilder
import io.hemin.wien.util.DomBuilderFactory
import io.hemin.wien.util.NodeListWrapper.Companion.asList
import org.w3c.dom.Document
import org.w3c.dom.Node
import javax.xml.parsers.DocumentBuilder

/** Base class for unit test classes checking implementations of [NamespaceParser]. */
abstract class NamespaceParserTest {

    /** The [NamespaceParser] implementation that the test class tests. */
    abstract val parser: NamespaceParser

    private val domBuilder: DocumentBuilder = DomBuilderFactory.newBuilder()

    /** Parse [channel] and add result to [builder]. */
    protected fun parse(builder: PodcastBuilder, channel: Node) {
        for (element in asList(channel.childNodes)) {
            parser.parse(builder, element)
        }
    }

    /** Parse [item] and add result to [builder]. */
    protected fun parse(builder: EpisodeBuilder, item: Node) {
        for (element in asList(item.childNodes)) {
            parser.parse(builder, element)
        }
    }

    /** Finds a DOM node matching [elementName] in a resource loaded from given [filePath]. */
    protected fun nodeFromResource(elementName: String, filePath: String): Node? {
        val xml = this.javaClass.getResource(filePath)
        val doc: Document = domBuilder.parse(xml.openStream())

        var result: Node? = null
        for (node in asList(doc.childNodes)) {
            if (elementName == node.localName) {
                result = node
            }
        }
        return result
    }
}
