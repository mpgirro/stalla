package io.hemin.wien.writer

import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor

internal object DateFormatter {

    private val formatter = DateTimeFormatter.RFC_1123_DATE_TIME

    fun formatAsRfc2822(date: TemporalAccessor): String = formatter.format(date)
}
