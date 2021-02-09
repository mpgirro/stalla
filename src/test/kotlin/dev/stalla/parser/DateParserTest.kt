package dev.stalla.parser

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.Month
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.concurrent.TimeUnit

internal class DateParserTest {

    @Test
    fun `should parse RFC-1123 aka RFC-(2)822 timestamps correctly`() {
        assertAll {
            assertThat(DateParser.parse("3 Dec 2011 10:15"), "3 Dec 2011 10:15")
                .isEqualTo(localDateTime(hour = 10, minute = 15))
            assertThat(DateParser.parse("Sat, 3 Dec 2011 10:15"), "Sat, 3 Dec 2011 10:15")
                .isEqualTo(localDateTime(hour = 10, minute = 15))
            assertThat(DateParser.parse("Sat, 3 Dec 2011 10:15:30"), "Sat, 3 Dec 2011 10:15:30")
                .isEqualTo(localDateTime(hour = 10, minute = 15, second = 30))
            assertThat(DateParser.parse("Sat, 3 Dec 2011 10:15 +0100"), "Sat, 3 Dec 2011 10:15 +0100")
                .isEqualTo(zonedDateTime(hour = 10, minute = 15, timeZoneId = ZoneId.of("+0100")))
            assertThat(DateParser.parse("Sat, 3 Dec 2011 10:15:30 +0100"), "Sat, 3 Dec 2011 10:15:30 +0100")
                .isEqualTo(zonedDateTime(hour = 10, minute = 15, second = 30, timeZoneId = ZoneId.of("+0100")))
            assertThat(DateParser.parse("3 Dec 2011 10:15:30"), "3 Dec 2011 10:15:30")
                .isEqualTo(localDateTime(hour = 10, minute = 15, second = 30))
            assertThat(DateParser.parse("3 Dec 2011 10:15 +0100"), "3 Dec 2011 10:15 +0100")
                .isEqualTo(zonedDateTime(hour = 10, minute = 15, timeZoneId = ZoneId.of("+0100")))
            assertThat(DateParser.parse("3 Dec 2011 10:15:30 +0100"), "3 Dec 2011 10:15:30 +0100")
                .isEqualTo(zonedDateTime(hour = 10, minute = 15, second = 30, timeZoneId = ZoneId.of("+0100")))

            assertThat(DateParser.parse("Tue, 13 Dec 2011 10:15:30 +0100"), "Tue, 13 Dec 2011 10:15:30 +0100")
                .isEqualTo(zonedDateTime(day = 13, hour = 10, minute = 15, second = 30, timeZoneId = ZoneId.of("+0100")))
            assertThat(DateParser.parse("13 Dec 2011 10:15:30 +0100"), "13 Dec 2011 10:15:30 +0100")
                .isEqualTo(zonedDateTime(day = 13, hour = 10, minute = 15, second = 30, timeZoneId = ZoneId.of("+0100")))
        }
    }

    @Test
    fun `should parse ISO-8601 timestamps correctly`() {
        assertAll {
            assertThat(DateParser.parse("2011-12-03T10:15Z"), "2011-12-03T10:15Z")
                .isEqualTo(zonedDateTime(hour = 10, minute = 15))
            assertThat(DateParser.parse("2011-12-03T10:15:30Z"), "2011-12-03T10:15:30Z")
                .isEqualTo(zonedDateTime(hour = 10, minute = 15, second = 30))
            assertThat(DateParser.parse("2011-12-03T10:15:30.123Z"), "2011-12-03T10:15:30.123Z")
                .isEqualTo(zonedDateTime(hour = 10, minute = 15, second = 30, millisecond = 123))

            assertThat(DateParser.parse("2011-12-03T10:15+01:00"), "2011-12-03T10:15+01:00")
                .isEqualTo(zonedDateTime(hour = 10, minute = 15, timeZoneId = ZoneId.of("+01:00")))
            assertThat(DateParser.parse("2011-12-03T10:15:30+01:00"), "2011-12-03T10:15:30+01:00")
                .isEqualTo(zonedDateTime(hour = 10, minute = 15, second = 30, timeZoneId = ZoneId.of("+01:00")))
            assertThat(DateParser.parse("2011-12-03T10:15:30.123+01:00"), "2011-12-03T10:15:30.123+01:00")
                .isEqualTo(zonedDateTime(hour = 10, minute = 15, second = 30, millisecond = 123, timeZoneId = ZoneId.of("+01:00")))
        }
    }

    private fun localDateTime(
        hour: Int = 0,
        minute: Int = 0,
        second: Int = 0,
        nanosecond: Int = 0
    ) = LocalDateTime.of(
        LocalDate.of(2011, Month.DECEMBER, 3),
        LocalTime.of(hour, minute, second, nanosecond)
    )

    private fun zonedDateTime(
        year: Int = 2011,
        month: Month = Month.DECEMBER,
        day: Int = 3,
        hour: Int = 0,
        minute: Int = 0,
        second: Int = 0,
        millisecond: Int = 0,
        timeZoneId: ZoneId = ZoneId.of("Z")
    ) = ZonedDateTime.of(
        LocalDate.of(year, month, day),
        LocalTime.of(hour, minute, second, TimeUnit.MILLISECONDS.toNanos(millisecond.toLong()).toInt()),
        timeZoneId
    )
}
