package dev.stalla.writer

import dev.stalla.util.InternalAPI
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor

@InternalAPI
internal object DateFormatter {

    private val formatter = DateTimeFormatter.RFC_1123_DATE_TIME

    fun formatAsRfc2822(date: TemporalAccessor): String = formatter.format(date)
}
