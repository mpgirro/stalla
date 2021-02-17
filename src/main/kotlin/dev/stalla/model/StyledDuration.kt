package dev.stalla.model

import dev.stalla.util.InternalApi
import java.time.Duration
import kotlin.math.absoluteValue
import kotlin.math.pow

public sealed class StyledDuration {

    public abstract fun asFormattedString(): String

    public abstract val rawDuration: Duration

    public val isNegative: Boolean
        get() = rawDuration.seconds < 0 || rawDuration.nano < 0

    public val isZero: Boolean
        get() = rawDuration.isZero

    public val isStrictlyPositive: Boolean
        get() = !(isNegative || isZero)

    final override fun toString(): String = "${javaClass.simpleName}(rawDuration: '$rawDuration', formatted: \"${asFormattedString()}\")"

    public data class SecondsAndFraction @InternalApi internal constructor(
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

    public data class Seconds @InternalApi internal constructor(
        public override val rawDuration: Duration
    ) : StyledDuration() {

        override fun asFormattedString(): String = buildString {
            if (isNegative) append('-')
            append(rawDuration.toSeconds().absoluteValue)
        }
    }

    public data class MinutesSeconds @InternalApi internal constructor(
        public override val rawDuration: Duration
    ) : StyledDuration() {

        override fun asFormattedString(): String = buildString {
            if (isNegative) append('-')
            append(rawDuration.toMinutesPart().absoluteValue)
            append(':')
            append(rawDuration.toSecondsPart().absoluteValue.toString().padStart(2, '0'))
        }
    }

    public data class HoursMinutesSeconds @InternalApi internal constructor(
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

    public companion object Factory : TypeFactory<StyledDuration> {

        // Parses the format: [-][HH:]MM:SS
        private val hhMmSsRegex = "(-)?(?:(\\d{1,2}):)?(\\d{1,2}):(\\d{1,2})".toRegex()

        // Parses the format: [-]SS...[.FFF...]
        private val plainSecondsRegex = "(-)?(\\d+)(?:\\.(\\d+))?".toRegex()

        @JvmStatic
        public override fun of(type: String?): StyledDuration? {
            if (type.isNullOrBlank()) return null
            return tryParsingAsPlainSeconds(type) ?: tryParsingAsHhMmSs(type)
        }

        private fun tryParsingAsPlainSeconds(rawDuration: String): StyledDuration? {
            val match = plainSecondsRegex.matchEntire(rawDuration) ?: return null
            val positive = match.groups[1]?.value.isNullOrBlank()

            val groupValues = match.groupValues.drop(1)
                .filter { it.isNotBlank() && it != "-" }
                .map { it.trim() }

            val seconds = groupValues.first().toInt()

            return when (groupValues.count()) {
                2 -> {
                    val nanoString = groupValues.last().removeAllUnneededDecimalZeroes()
                    val nano = nanoString.toLong() * 10F.pow(9 - nanoString.length).toLong()
                    secondsAndFraction(seconds, nano, positive)
                }
                1 -> seconds(seconds, positive)
                else -> null
            }
        }

        private fun String.removeAllUnneededDecimalZeroes(): String {
            val trimmed = trimEnd('0')
            return if (trimmed.isEmpty()) "0" else trimmed
        }

        private fun tryParsingAsHhMmSs(rawDuration: String): StyledDuration? {
            val match = hhMmSsRegex.matchEntire(rawDuration.trim()) ?: return null
            val positive = match.groups[1]?.value.isNullOrBlank()

            val groupValues = match.groupValues.drop(1)
                .filter { it.isNotBlank() && it != "-" }
                .map { it.trim() }

            val seconds = groupValues.last().toInt()

            return when (groupValues.count()) {
                3 -> hoursMinutesSeconds(hours = groupValues[0].toInt(), minutes = groupValues[1].toInt(), seconds, positive)
                2 -> minutesSeconds(minutes = groupValues[0].toInt(), seconds, positive)
                else -> null
            }
        }

        @JvmStatic
        @JvmOverloads
        public fun secondsAndFraction(seconds: Int = 0, nano: Long = 0, positive: Boolean = true): SecondsAndFraction {
            require(seconds >= 0 && nano >= 0) { "Components cannot be negative" }
            val sign = if (positive) "" else "-"
            // This is not ideal but Duration does not handle negative nano-only inputs well otherwise
            val durationString = "PT$sign$seconds.${nano.toString().padStart(9, '0')}S"
            return SecondsAndFraction(Duration.parse(durationString))
        }

        @JvmStatic
        @JvmOverloads
        public fun seconds(seconds: Int = 0, positive: Boolean = true): Seconds {
            require(seconds >= 0) { "Seconds cannot be negative" }
            val sign = signForPositive(positive)
            return Seconds(Duration.ofSeconds(seconds.toLong() * sign))
        }

        @JvmStatic
        @JvmOverloads
        public fun minutesSeconds(minutes: Int = 0, seconds: Int = 0, positive: Boolean = true): MinutesSeconds {
            require(minutes >= 0 && seconds >= 0) { "Components cannot be negative" }
            val sign = signForPositive(positive)
            val rawDuration = Duration.ofMinutes(minutes.toLong() * sign)
                .plusSeconds(seconds.toLong() * sign)
            return MinutesSeconds(rawDuration)
        }

        @JvmOverloads
        public fun hoursMinutesSeconds(hours: Int = 0, minutes: Int = 0, seconds: Int = 0, positive: Boolean = true): HoursMinutesSeconds {
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
