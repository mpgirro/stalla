package io.hemin.wien.parser

import io.hemin.wien.util.InternalApi
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.format.DateTimeParseException
import java.time.format.SignStyle
import java.time.temporal.ChronoField
import java.time.temporal.TemporalAccessor
import java.util.Locale

/**
 * Parser implementation for transforming date strings to date objects.
 * Various formats are supported. This class attempts to find the correct
 * format to produce the intended date object.
 */
@InternalApi
internal object DateParser {

    private val dayOfWeek = mapOf(
        1L to "Mon",
        2L to "Tue",
        3L to "Wed",
        4L to "Thu",
        5L to "Fri",
        6L to "Sat",
        7L to "Sun"
    )

    private val monthOfYear = mapOf(
        1L to "Jan",
        2L to "Feb",
        3L to "Mar",
        4L to "Apr",
        5L to "May",
        6L to "Jun",
        7L to "Jul",
        8L to "Aug",
        9L to "Sep",
        10L to "Oct",
        11L to "Nov",
        12L to "Dec"
    )

    // Attempt to parse RFC-(2)822/RFC-1123 first, fallback on ISO-8601.
    // Our RFC parser is based on DateTimeFormatter#RFC_1123_DATE_TIME, but is much more
    // lenient in parsing weird stuff. Because as y'all know time is bloody hard to do right.
    // Note: brackets indicate optional parts in the pattern
    private val formatters = listOf(
        DateTimeFormatterBuilder().parseCaseInsensitive() // RFC-(2)822/RFC-1123 (-ish): [Tue, ]3 Dec 2011 10:15[:30[.123]][ +0100]
            .parseLenient()
            // Start of date section
            // Optional EEE: day of week (in English, 3 letters)
            .optionalStart()
            .appendText(ChronoField.DAY_OF_WEEK, dayOfWeek)
            .appendLiteral(", ")
            .optionalEnd()
            // [d]d MMM yy: day number (1 or 2 digits), month name (in English, 3 letters), year (4 digits)
            .appendValue(ChronoField.DAY_OF_MONTH, 1, 2, SignStyle.NOT_NEGATIVE)
            .appendLiteral(' ')
            .appendText(ChronoField.MONTH_OF_YEAR, monthOfYear)
            .appendLiteral(' ')
            .appendValue(ChronoField.YEAR, 4)
            .appendLiteral(' ')
            // Start of time section
            .appendValue(ChronoField.HOUR_OF_DAY, 2)
            .appendLiteral(':')
            .appendValue(ChronoField.MINUTE_OF_HOUR, 2)
            .optionalStart() // Start [:ss[.SSS]]
            .appendLiteral(':')
            .appendValue(ChronoField.SECOND_OF_MINUTE, 2)
            .optionalStart() // Start [.SSS]
            .appendLiteral('.')
            .appendValue(ChronoField.MILLI_OF_SECOND, 3)
            .optionalEnd() // End [.SSS]
            .optionalEnd() // End [:ss[.SSS]]
            .optionalStart() // Start [ [x]]
            .appendLiteral(' ')
            .appendOffset("+HHMM", "GMT") // (fallback to GMT when missing)
            .optionalEnd() // End [ [x]]
            .toFormatter(),

        DateTimeFormatter.ISO_DATE_TIME // ISO-8601: 2011-12-03T10:15[:30[.100]][+01:00'['Europe/Paris']']
    )

    /**
     * Parses a string into a date object, if the string represents a valid date format.
     *
     * @param value The string representation of a date.
     * @return The date object defined by the string, or null of parsing was unsuccessful.
     */
    fun parse(value: String?, locale: Locale = Locale.US): TemporalAccessor? {
        if (value.isNullOrBlank()) return null
        val trimmedValue = value.trim()

        for (formatter in formatters) {
            return try {
                formatter.withLocale(locale)
                    .parseBest(trimmedValue, ZonedDateTime::from, LocalDateTime::from)
            } catch (ignored: DateTimeParseException) {
                continue
            }
        }

        return null
    }
}
