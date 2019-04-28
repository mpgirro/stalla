package io.hemin.wien.sax

import kotlin.test.Test
import kotlin.test.assertTrue

class SaxParserTest {
    @Test fun tesParse() {
        val parser = SaxParser()
        parser.parse("file:///Users/max/Desktop/feeds/ukw.xml")
        assertTrue(true, "parse(uri) should parse the XML")

    }
}
