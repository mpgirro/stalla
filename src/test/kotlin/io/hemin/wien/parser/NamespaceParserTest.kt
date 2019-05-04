package io.hemin.wien.parser

import io.hemin.wien.builder.EpisodeBuilder
import io.hemin.wien.builder.PodcastBuilder
import io.hemin.wien.util.NodeListWrapper
import io.hemin.wien.util.NodeListWrapper.Companion.asList
import org.w3c.dom.Document
import org.w3c.dom.Node
import javax.xml.parsers.DocumentBuilder

interface NamespaceParserTest {

    val parser: NamespaceParser

    val domBuilder: DocumentBuilder

    fun parse(builder: PodcastBuilder, channel: Node) {
        for (element in asList(channel.childNodes)) {
            parser.parse(builder, element)
        }
    }

    fun parse(builder: EpisodeBuilder, item: Node) {
        for (element in asList(item.childNodes)) {
            parser.parse(builder, element)
        }
    }

    fun nodeFromResource(elementName: String, filePath: String): Node? {
        val xml = this.javaClass.getResource(filePath)
        val doc: Document = domBuilder.parse(xml.openStream())

        var result: Node? = null
        for (node in asList(doc.childNodes)) {
            if (elementName.equals(node.localName)) {
                result = node
            }
        }
        return result
    }

}
