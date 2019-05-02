package io.hemin.wien

import org.junit.Assert.assertTrue
import org.junit.Test

class WienParserTest {

    @Test
    fun tesParse() {
        val parser = WienParser()
        parser.parse("file:///Users/max/Desktop/feeds/ukw.xml")
        assertTrue(true) // TODO

    }

}
