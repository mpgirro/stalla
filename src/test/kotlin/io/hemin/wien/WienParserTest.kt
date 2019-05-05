package io.hemin.wien

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.w3c.dom.Node
import javax.xml.parsers.DocumentBuilderFactory

class WienParserTest {

    val dbf = DocumentBuilderFactory.newInstance()
    val builder = dbf.newDocumentBuilder()
    val doc = builder.newDocument()

    fun createRssNode(localName: String): Node = doc.createElementNS(null, localName)

    @Test
    fun tesParse() {
        val parser = WienParser()
        parser.parse("file:///Users/max/Desktop/feeds/ukw.xml")
        assertTrue(true) // TODO
    }

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

}
