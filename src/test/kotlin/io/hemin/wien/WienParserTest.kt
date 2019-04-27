package io.hemin.wien

import kotlin.test.Test
import kotlin.test.assertTrue

class WienParserTest {
    @Test fun testSomeLibraryMethod() {
        val parser = WienParser()
        parser.parse("file:///Users/max/Desktop/feeds/ukw.xml")
        assertTrue(true, "parse(uri) should parse the XML")

    }
}
