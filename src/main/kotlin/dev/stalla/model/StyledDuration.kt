package dev.stalla.model

import dev.stalla.model.StyledDuration.HoursMinutesSeconds
import dev.stalla.model.StyledDuration.MinutesSeconds
import dev.stalla.model.StyledDuration.Seconds
import dev.stalla.model.StyledDuration.SecondsAndFraction
import dev.stalla.util.InternalAPI
import java.time.Duration
import kotlin.math.absoluteValue
import kotlin.math.pow

/**
 * Represents a duration, expressed in one of the supported styles. Each supported style is
 * represented by one of the concrete implementations of [StyledDuration].
 *
 * @see Seconds
 * @see SecondsAndFraction
 * @see HoursMinutesSeconds
 * @see MinutesSeconds
 */
public sealed class StyledDuration {

    /**
     * Formats the duration value as a string in the style this instance.
     */
    public abstract fun asFormattedString(): String

    /**
     * The underlying [Duration] value.
     *
     * Note that due to the way [Duration] represents values internally, the
     * [Duration.seconds] and [Duration.nanos] values may seem incorrect even
     * though they are representing the right value.
     */
    public abstract val rawDuration: Duration

    /**
     * True when the duration value is less than `0:00:00.000`.
     *
     * @see Duration.isNegative
     */
    public val isNegative: Boolean
        get() = rawDuration.isNegative

    /**
     * True when the duration value is exactly `0:00:00.000`.
     *
     * @see Duration.isZero
     */
    public val isZero: Boolean
        get() = rawDuration.isZero

    /**
     * True when the duration value is equal to or greater than than `0:00:00.000`.
     *
     * @see isZero
     * @see isNegative
     */
    public val isStrictlyPositive: Boolean
        get() = !(isNegative || isZero)

    final override fun toString(): String =
        "${javaClass.simpleName}(rawDuration: '$rawDuration', formatted: \"${asFormattedString()}\")"

    /**
     * A duration expressed in the style: `[-]S[.FFF]`.
     *
     * The negative symbol and fractional part are optional, and are omitted
     * by [asFormattedString] when the duration is positive, and has no fractional
     * part, respectively.
     *
     * The seconds can have as many digits as needed.
     *
     * @see StyledDuration.Factory.secondsAndFraction
     */
    public data class SecondsAndFraction @InternalAPI internal constructor(
        public override val rawDuration: Duration
    ) : StyledDuration() {

        // Freely inspired by Duration#toString(), accounting for the weird internal representation
        override fun asFormattedString(): String = buildString {
            if (rawDuration == Duration.ZERO) {
                append("0")
                return@buildString
            }

            val seconds = rawDuration.seconds
            val nanos = rawDuration.nano

            val effectiveTotalSecs = if (seconds < 0 && nanos > 0) {
                seconds + 1
            } else {
                seconds
            }

            if (seconds < 0L && nanos > 0) {
                if (effectiveTotalSecs == 0L) append("-0") else append(effectiveTotalSecs)
            } else {
                append(effectiveTotalSecs)
            }

            if (nanos > 0) {
                val dotPos = length
                val adjustedNanos = if (seconds < 0) {
                    2 * NANOS_PER_SECOND - nanos
                } else {
                    nanos + NANOS_PER_SECOND
                }
                append(adjustedNanos.toString().trimEnd('0'))
                setCharAt(dotPos, '.')
            }
        }

        private companion object {

            private const val NANOS_PER_SECOND = 1_000_000_000L
        }
    }

    /**
     * A duration expressed in the style: `[-]S`.
     *
     * The negative symbol is optional, and is omitted by [asFormattedString] when
     * the duration is positive.
     *
     * The seconds can have as many digits as needed.
     *
     * @see StyledDuration.Factory.seconds
     */
    public data class Seconds @InternalAPI internal constructor(
        public override val rawDuration: Duration
    ) : StyledDuration() {

        override fun asFormattedString(): String = buildString {
            if (isNegative) append('-')
            append(rawDuration.toSeconds().absoluteValue)
        }
    }

    /**
     * A duration expressed in the style: `[-]MM:SS`.
     *
     * The negative symbol is optional, and is omitted by [asFormattedString] when
     * the duration is positive. Note that while the seconds are always formatted
     * with two digits, the minutes may have one or two depending on their value.
     * This means the duration 1 min 3 sec is formatted as `1:03`, not `01:03`.
     *
     * @see StyledDuration.Factory.minutesSeconds
     */
    public data class MinutesSeconds @InternalAPI internal constructor(
        public override val rawDuration: Duration
    ) : StyledDuration() {

        override fun asFormattedString(): String = buildString {
            if (isNegative) append('-')
            append(rawDuration.toMinutesPart().absoluteValue)
            append(':')
            append(rawDuration.toSecondsPart().absoluteValue.toString().padStart(2, '0'))
        }
    }

    /**
     * A duration expressed in the style: `[-]HH:MM:SS`.
     *
     * The negative symbol is optional, and is omitted by [asFormattedString] when
     * the duration is positive. Note that while the minutes and seconds are always
     * formatted with two digits, the hours may have one or two depending on their
     * value. This means the duration 1 hour, 2 min and 3 sec is formatted as
     * `1:02:03`, not `01:02:03`.
     *
     * @see StyledDuration.Factory.hoursMinutesSeconds
     */
    public data class HoursMinutesSeconds @InternalAPI internal constructor(
        public override val rawDuration: Duration
    ) : StyledDuration() {

        override fun asFormattedString(): String = buildString {
            if (isNegative) append('-')
            append(rawDuration.toHoursPart().absoluteValue)
            append(':')
            append(rawDuration.toMinutesPart().absoluteValue.toString().padStart(2, '0'))
            append(':')
            append(rawDuration.toSecondsPart().absoluteValue.toString().padStart(2, '0'))
        }
    }

    /** Builds an instance of [StyledDuration] from a raw value. */
    public companion object Factory : TypeFactory<StyledDuration> {

        private const val DECIMAL_BASE = 10F
        private const val NANO_DIGITS = 9
        private const val HHMMSS_PATTERN = 3
        private const val MMSS_PATTERN = 2
        private const val SS_PATTERN = 1

        // Parses the format: [-][HH:]MM:SS
        private val hhMmSsRegex = "(-)?(?:(\\d{1,2}):)?(\\d{1,2}):(\\d{1,2})".toRegex()

        // Parses the format: [-]SS...[.FFF...]
        private val plainSecondsRegex = "(-)?(\\d+)(?:\\.(\\d+))?".toRegex()

        /**
         * Parses the provided [rawValue] and returns the most appropriate
         * type of [StyledDuration], representing the value and its style.
         *
         * @param rawValue The string representation of the instance.
         * @return The instance matching [rawValue], or `null` if [rawValue]
         * is not in any supported style.
         */
        @JvmStatic
        public override fun of(rawValue: String?): StyledDuration? {
            if (rawValue.isNullOrBlank()) return null
            return tryParsingAsPlainSeconds(rawValue) ?: tryParsingAsHhMmSs(rawValue)
        }

        private fun tryParsingAsPlainSeconds(rawDuration: String): StyledDuration? {
            val match = plainSecondsRegex.matchEntire(rawDuration) ?: return null
            val positive = match.groups[1]?.value.isNullOrBlank()

            val groupValues = match.groupValues.drop(1)
                .filter { it.isNotBlank() && it != "-" }
                .map { it.trim() }

            val seconds = groupValues.first().toInt()

            return when (groupValues.count()) {
                MMSS_PATTERN -> {
                    val nanoString = groupValues.last().removeAllUnneededDecimalZeroes()
                    val nano = nanoString.toLong() * DECIMAL_BASE.pow(NANO_DIGITS - nanoString.length).toLong()
                    secondsAndFraction(seconds, nano, positive)
                }
                SS_PATTERN -> seconds(seconds, positive)
                else -> null
            }
        }

        private fun String.removeAllUnneededDecimalZeroes(): String =
            trimEnd('0').ifEmpty { "0" }

        private fun tryParsingAsHhMmSs(rawDuration: String): StyledDuration? {
            val match = hhMmSsRegex.matchEntire(rawDuration.trim()) ?: return null
            val positive = match.groups[1]?.value.isNullOrBlank()

            val groupValues = match.groupValues.drop(1)
                .filter { it.isNotBlank() && it != "-" }
                .map { it.trim() }

            val seconds = groupValues.last().toInt()

            return when (groupValues.count()) {
                HHMMSS_PATTERN -> hoursMinutesSeconds(
                    hours = groupValues[0].toInt(),
                    minutes = groupValues[1].toInt(),
                    seconds = seconds,
                    positive = positive
                )
                MMSS_PATTERN -> minutesSeconds(
                    minutes = groupValues[0].toInt(),
                    seconds = seconds,
                    positive = positive
                )
                else -> null
            }
        }

        /**
         * Creates an instance of [SecondsAndFraction] from the duration components.
         *
         * @param seconds The number of seconds. Must be positive.
         * @param nano The nanoseconds (fractional part of the second). Must be positive.
         * Should be `[0, 999_999_999]`.
         * @param positive True when the duration is positive, false when it's negative.
         * @return A new [SecondsAndFraction] instance representing the given duration.
         */
        @JvmStatic
        @JvmOverloads
        public fun secondsAndFraction(seconds: Int = 0, nano: Long = 0, positive: Boolean = true): SecondsAndFraction {
            require(seconds >= 0 && nano >= 0) { "Components cannot be negative" }
            val sign = if (positive) "" else "-"
            // This is not ideal but Duration does not handle negative nano-only inputs well otherwise
            val durationString = "PT$sign$seconds.${nano.toString().padStart(NANO_DIGITS, '0')}S"
            return SecondsAndFraction(Duration.parse(durationString))
        }

        /**
         * Creates an instance of [Seconds] from the duration components.
         *
         * @param seconds The number of seconds. Must be positive.
         * @param positive True when the duration is positive, false when it's negative.
         * @return A new [Seconds] instance representing the given duration.
         */
        @JvmStatic
        @JvmOverloads
        public fun seconds(seconds: Int = 0, positive: Boolean = true): Seconds {
            require(seconds >= 0) { "Seconds cannot be negative" }
            val sign = signForPositive(positive)
            return Seconds(Duration.ofSeconds(seconds.toLong() * sign))
        }

        /**
         * Creates an instance of [MinutesSeconds] from the duration components.
         *
         * Note that you should only use values of [minutes] and [seconds] up to `59`
         * to avoid nasty bugs, but this method doesn't enforce that.
         *
         * @param minutes The number of minutes. Must be positive. Should be `[0, 59]`.
         * @param seconds The number of seconds. Must be positive. Should be `[0, 59]`.
         * @param positive True when the duration is positive, false when it's negative.
         * @return A new [MinutesSeconds] instance representing the given duration.
         */
        @JvmStatic
        @JvmOverloads
        public fun minutesSeconds(minutes: Int = 0, seconds: Int = 0, positive: Boolean = true): MinutesSeconds {
            require(minutes >= 0 && seconds >= 0) { "Components cannot be negative" }
            val sign = signForPositive(positive)
            val rawDuration = Duration.ofMinutes(minutes.toLong() * sign)
                .plusSeconds(seconds.toLong() * sign)
            return MinutesSeconds(rawDuration)
        }

        /**
         * Creates an instance of [HoursMinutesSeconds] from the duration components.
         *
         * Note that you should only use values of [minutes] and [seconds] up to `59`,
         * and up to `99` [hours] only to avoid nasty bugs, but this method doesn't
         * enforce that.
         *
         * @param hours The number of hours. Must be positive. Should be `[0, 99]`.
         * @param minutes The number of minutes. Must be positive. Should be `[0, 59]`.
         * @param seconds The number of seconds. Must be positive. Should be `[0, 59]`.
         * @param positive True when the duration is positive, false when it's negative.
         * @return A new [HoursMinutesSeconds] instance representing the given duration.
         */
        @JvmOverloads
        public fun hoursMinutesSeconds(
            hours: Int = 0,
            minutes: Int = 0,
            seconds: Int = 0,
            positive: Boolean = true
        ): HoursMinutesSeconds {
            require(hours >= 0 && minutes >= 0 && seconds >= 0) { "Components cannot be negative" }
            val sign = signForPositive(positive)
            val rawDuration = Duration.ofHours(hours.toLong() * sign)
                .plusMinutes(minutes.toLong() * sign)
                .plusSeconds(seconds.toLong() * sign)
            return HoursMinutesSeconds(rawDuration)
        }

        private fun signForPositive(positive: Boolean) = if (positive) 1 else -1
    }
}
