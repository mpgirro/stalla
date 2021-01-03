package io.hemin.wien.parser

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.Instant
import java.util.Date

// TODO test: 2019-05-01T21:04:13.958+02:00[Europe/Vienna]

/** Provides unit tests for [DateParser]. */
internal class DateParserTest {

    // millis calculated from: "Fri, 16 Mar 2018 22:49:08 +0000"
    private val expected: Date = Date.from(Instant.ofEpochMilli(1521240548000))

    @Test
    fun testParseDateFormats() {

        assertEquals(expected, DateParser.parse("Fri, 16 Mar 2018 22:49:08 +0000"))

        // TODO add more date formats
    }
}
