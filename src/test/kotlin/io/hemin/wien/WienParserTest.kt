package io.hemin.wien

import io.hemin.wien.util.DomBuilderFactory
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.w3c.dom.Document
import org.w3c.dom.Node
import javax.xml.parsers.DocumentBuilderFactory

/** Provides unit tests for [WienParser]. */
class WienParserTest {

    private val rss: Document

    init {
        val domBuilder = DomBuilderFactory.newBuilder()
        val xml = this.javaClass.getResource("/xml/rss.xml")
        rss = domBuilder.parse(xml.openStream())
    }

    fun createRssNode(localName: String): Node = DocumentBuilderFactory
        .newInstance()
        .newDocumentBuilder()
        .newDocument()
        .createElementNS(null, localName)

    @Test
    fun testRssChannelNodeCheck() {
        assertNotNull(WienParser.toPodcast(createRssNode("channel")))
        assertNull(WienParser.toPodcast(createRssNode("foo")))
    }

    @Test
    fun testRssItemNodeCheck() {
        assertNotNull(WienParser.toEpisode(createRssNode("item")))
        assertNull(WienParser.toEpisode(createRssNode("foo")))
    }

    @Test
    fun testParse() {
        val parser = WienParser()
        assertNotNull(parser.parse(rss))
    }

}
