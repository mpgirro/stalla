package io.hemin.wien

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class WienParserTest {

    @Test
    fun tesParse() {
        val parser = WienParser()
        parser.parse("file:///Users/max/Desktop/feeds/ukw.xml")
        assertTrue(true) // TODO
    }

}
