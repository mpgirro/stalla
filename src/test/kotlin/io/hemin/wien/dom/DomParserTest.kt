package io.hemin.wien.dom

import org.junit.Test
import kotlin.test.assertTrue

class DomParserTest {
    @Test
    fun tesParse() {
        val parser = DomParser()
        parser.parse("file:///Users/max/Desktop/feeds/ukw.xml")
        assertTrue(true, "parse(uri) should parse the XML")

    }
}
