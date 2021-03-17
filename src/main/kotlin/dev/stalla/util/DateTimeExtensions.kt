package dev.stalla.util

import dev.stalla.writer.DateFormatter
import java.time.temporal.TemporalAccessor

/**
 * Converts a [TemporalAccessor] value to an RFC 2822 [String] representation.
 *
 * @return The string representing the value in the chosen style.
 */
@InternalAPI
internal fun TemporalAccessor.asDateString() = DateFormatter.formatAsRfc2822(this)
