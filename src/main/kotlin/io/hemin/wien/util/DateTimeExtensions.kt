package io.hemin.wien.util

import io.hemin.wien.writer.DateFormatter
import java.time.temporal.TemporalAccessor

/**
 * Converts a [TemporalAccessor] value to a RFC2822 [String] representation.
 *
 * @return The string representing the value in the chosen style.
 */
internal fun TemporalAccessor.asDateString() = DateFormatter.formatAsRfc2822(this)
