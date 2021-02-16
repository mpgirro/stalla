package dev.stalla.model

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream

internal class StyledDurationTest {

    @ParameterizedTest
    @MethodSource("nonNegativeDurationAndExpectedStringFormat")
    internal fun `should write a formatted non-negative duration correctly`(duration: StyledDuration, expected: String) {
        assertThat(duration.asFormattedString(), duration.toString()).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("negativeDurationAndExpectedStringFormat")
    internal fun `should write a formatted negative duration correctly`(duration: StyledDuration, expected: String) {
        assertThat(duration.asFormattedString(), duration.toString()).isEqualTo(expected)
    }

    @ParameterizedTest
    @ValueSource(strings = ["", " ", "banana", "1:02:03:04", "1:02:03.123", "1234.111.222", "1234,1122"])
    internal fun `should return null when parsing invalid or empty durations`(rawDuration: String) {
        assertThat(StyledDuration.of(rawDuration), rawDuration).isNull()
    }

    @ParameterizedTest
    @MethodSource("inputAndExpectedNonNegativeHHMMSSDurations")
    internal fun `should parse non-negative HHMMSS durations correctly`(rawDuration: String, expected: StyledDuration) {
        assertThat(StyledDuration.of(rawDuration), rawDuration)
            .isNotNull()
            .isInstanceOf(expected::class)
            .isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("inputAndExpectedNonNegativeSecondsAndFractionDurations")
    internal fun `should parse non-negative seconds and fraction durations correctly`(rawDuration: String, expected: StyledDuration) {
        assertThat(StyledDuration.of(rawDuration), rawDuration)
            .isNotNull()
            .isInstanceOf(expected::class)
            .isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("inputAndExpectedNegativeHHMMSSDurations")
    internal fun `should parse negative HHMMSS durations correctly`(rawDuration: String, expected: StyledDuration) {
        assertThat(StyledDuration.of(rawDuration), rawDuration)
            .isNotNull()
            .isInstanceOf(expected::class)
            .isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("inputAndExpectedNegativeSecondsAndFractionDurations")
    internal fun `should parse negative seconds and fraction durations correctly`(rawDuration: String, expected: StyledDuration) {
        assertThat(StyledDuration.of(rawDuration), rawDuration)
            .isNotNull()
            .isInstanceOf(expected::class)
            .isEqualTo(expected)
    }

    @Suppress("unused") // Accessed by reflection by JUnit
    companion object {

        @JvmStatic
        fun nonNegativeDurationAndExpectedStringFormat(): Stream<Arguments> = Stream.of(
            Arguments.of(StyledDuration.secondsAndFraction(0, 0), "0"),
            Arguments.of(StyledDuration.secondsAndFraction(1, 0), "1"),
            Arguments.of(StyledDuration.secondsAndFraction(10, 0), "10"),
            Arguments.of(StyledDuration.secondsAndFraction(1_000, 0), "1000"),
            Arguments.of(StyledDuration.secondsAndFraction(0, 123), "0.000000123"),
            Arguments.of(StyledDuration.secondsAndFraction(1, 123), "1.000000123"),
            Arguments.of(StyledDuration.secondsAndFraction(10, 123), "10.000000123"),
            Arguments.of(StyledDuration.secondsAndFraction(1_000, 123), "1000.000000123"),
            Arguments.of(StyledDuration.secondsAndFraction(1, 123000000), "1.123"),
            Arguments.of(StyledDuration.secondsAndFraction(1, 123000), "1.000123"),
            Arguments.of(StyledDuration.seconds(0), "0"),
            Arguments.of(StyledDuration.seconds(1), "1"),
            Arguments.of(StyledDuration.seconds(10), "10"),
            Arguments.of(StyledDuration.seconds(1_000), "1000"),
            Arguments.of(StyledDuration.minutesSeconds(0, 0), "0:00"),
            Arguments.of(StyledDuration.minutesSeconds(0, 1), "0:01"),
            Arguments.of(StyledDuration.minutesSeconds(0, 10), "0:10"),
            Arguments.of(StyledDuration.minutesSeconds(1, 0), "1:00"),
            Arguments.of(StyledDuration.minutesSeconds(1, 2), "1:02"),
            Arguments.of(StyledDuration.minutesSeconds(1, 10), "1:10"),
            Arguments.of(StyledDuration.minutesSeconds(11, 10), "11:10"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(0, 0, 0), "0:00:00"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(0, 0, 1), "0:00:01"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(0, 0, 10), "0:00:10"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(0, 1, 0), "0:01:00"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(0, 1, 2), "0:01:02"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(0, 1, 10), "0:01:10"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(0, 11, 10), "0:11:10"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(1, 0, 0), "1:00:00"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(1, 0, 1), "1:00:01"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(1, 0, 10), "1:00:10"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(1, 1, 0), "1:01:00"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(1, 1, 2), "1:01:02"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(1, 1, 10), "1:01:10"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(1, 11, 10), "1:11:10"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(10, 0, 0), "10:00:00"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(10, 0, 1), "10:00:01"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(10, 0, 10), "10:00:10"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(10, 1, 0), "10:01:00"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(10, 1, 2), "10:01:02"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(10, 1, 10), "10:01:10"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(10, 11, 10), "10:11:10")
        )

        @JvmStatic
        fun negativeDurationAndExpectedStringFormat(): Stream<Arguments> = Stream.of(
            Arguments.of(StyledDuration.secondsAndFraction(0, 0, positive = false), "0"),
            Arguments.of(StyledDuration.secondsAndFraction(1, 0, positive = false), "-1"),
            Arguments.of(StyledDuration.secondsAndFraction(10, 0, positive = false), "-10"),
            Arguments.of(StyledDuration.secondsAndFraction(1_000, 0, positive = false), "-1000"),
            Arguments.of(StyledDuration.secondsAndFraction(0, 123, positive = false), "-0.000000123"),
            Arguments.of(StyledDuration.secondsAndFraction(1, 123, positive = false), "-1.000000123"),
            Arguments.of(StyledDuration.secondsAndFraction(10, 123, positive = false), "-10.000000123"),
            Arguments.of(StyledDuration.secondsAndFraction(1_000, 123, positive = false), "-1000.000000123"),
            Arguments.of(StyledDuration.secondsAndFraction(1, 123000000, positive = false), "-1.123"),
            Arguments.of(StyledDuration.secondsAndFraction(1, 123000, positive = false), "-1.000123"),
            Arguments.of(StyledDuration.seconds(0, positive = false), "0"),
            Arguments.of(StyledDuration.seconds(1, positive = false), "-1"),
            Arguments.of(StyledDuration.seconds(10, positive = false), "-10"),
            Arguments.of(StyledDuration.seconds(1_000, positive = false), "-1000"),
            Arguments.of(StyledDuration.minutesSeconds(0, 0, positive = false), "0:00"),
            Arguments.of(StyledDuration.minutesSeconds(0, 1, positive = false), "-0:01"),
            Arguments.of(StyledDuration.minutesSeconds(0, 10, positive = false), "-0:10"),
            Arguments.of(StyledDuration.minutesSeconds(1, 0, positive = false), "-1:00"),
            Arguments.of(StyledDuration.minutesSeconds(1, 2, positive = false), "-1:02"),
            Arguments.of(StyledDuration.minutesSeconds(1, 10, positive = false), "-1:10"),
            Arguments.of(StyledDuration.minutesSeconds(11, 10, positive = false), "-11:10"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(0, 0, 0, positive = false), "0:00:00"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(0, 0, 1, positive = false), "-0:00:01"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(0, 0, 10, positive = false), "-0:00:10"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(0, 1, 0, positive = false), "-0:01:00"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(0, 1, 2, positive = false), "-0:01:02"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(0, 1, 10, positive = false), "-0:01:10"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(0, 11, 10, positive = false), "-0:11:10"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(1, 0, 0, positive = false), "-1:00:00"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(1, 0, 1, positive = false), "-1:00:01"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(1, 0, 10, positive = false), "-1:00:10"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(1, 1, 0, positive = false), "-1:01:00"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(1, 1, 2, positive = false), "-1:01:02"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(1, 1, 10, positive = false), "-1:01:10"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(1, 11, 10, positive = false), "-1:11:10"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(10, 0, 0, positive = false), "-10:00:00"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(10, 0, 1, positive = false), "-10:00:01"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(10, 0, 10, positive = false), "-10:00:10"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(10, 1, 0, positive = false), "-10:01:00"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(10, 1, 2, positive = false), "-10:01:02"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(10, 1, 10, positive = false), "-10:01:10"),
            Arguments.of(StyledDuration.hoursMinutesSeconds(10, 11, 10, positive = false), "-10:11:10")
        )

        @JvmStatic
        fun inputAndExpectedNonNegativeHHMMSSDurations(): Stream<Arguments> = Stream.of(
            Arguments.of("0:00", StyledDuration.minutesSeconds(seconds = 0)),
            Arguments.of("0:01", StyledDuration.minutesSeconds(seconds = 1)),
            Arguments.of("0:1", StyledDuration.minutesSeconds(seconds = 1)),
            Arguments.of("0:10", StyledDuration.minutesSeconds(seconds = 10)),
            Arguments.of("1:00", StyledDuration.minutesSeconds(minutes = 1)),
            Arguments.of("1:02", StyledDuration.minutesSeconds(minutes = 1, seconds = 2)),
            Arguments.of("1:10", StyledDuration.minutesSeconds(minutes = 1, seconds = 10)),
            Arguments.of("00:00", StyledDuration.minutesSeconds(seconds = 0)),
            Arguments.of("00:01", StyledDuration.minutesSeconds(seconds = 1)),
            Arguments.of("00:1", StyledDuration.minutesSeconds(seconds = 1)),
            Arguments.of("00:10", StyledDuration.minutesSeconds(seconds = 10)),
            Arguments.of("01:00", StyledDuration.minutesSeconds(minutes = 1)),
            Arguments.of("01:02", StyledDuration.minutesSeconds(minutes = 1, seconds = 2)),
            Arguments.of("01:10", StyledDuration.minutesSeconds(minutes = 1, seconds = 10)),
            Arguments.of("11:10", StyledDuration.minutesSeconds(minutes = 11, seconds = 10)),
            Arguments.of("0:00:01", StyledDuration.hoursMinutesSeconds(seconds = 1)),
            Arguments.of("0:00:1", StyledDuration.hoursMinutesSeconds(seconds = 1)),
            Arguments.of("0:00:10", StyledDuration.hoursMinutesSeconds(seconds = 10)),
            Arguments.of("0:1:10", StyledDuration.hoursMinutesSeconds(minutes = 1, seconds = 10)),
            Arguments.of("0:01:10", StyledDuration.hoursMinutesSeconds(minutes = 1, seconds = 10)),
            Arguments.of("0:1:2", StyledDuration.hoursMinutesSeconds(minutes = 1, seconds = 2)),
            Arguments.of("0:11:10", StyledDuration.hoursMinutesSeconds(minutes = 11, seconds = 10)),
            Arguments.of("1:11:10", StyledDuration.hoursMinutesSeconds(hours = 1, minutes = 11, seconds = 10)),
            Arguments.of("01:00:00", StyledDuration.hoursMinutesSeconds(hours = 1)),
            Arguments.of("01:00:02", StyledDuration.hoursMinutesSeconds(hours = 1, seconds = 2)),
            Arguments.of("01:00:2", StyledDuration.hoursMinutesSeconds(hours = 1, seconds = 2)),
            Arguments.of("01:00:10", StyledDuration.hoursMinutesSeconds(hours = 1, seconds = 10)),
            Arguments.of("01:02:10", StyledDuration.hoursMinutesSeconds(hours = 1, minutes = 2, seconds = 10)),
            Arguments.of("01:2:10", StyledDuration.hoursMinutesSeconds(hours = 1, minutes = 2, seconds = 10)),
            Arguments.of("01:11:10", StyledDuration.hoursMinutesSeconds(hours = 1, minutes = 11, seconds = 10)),
            Arguments.of("20:11:10", StyledDuration.hoursMinutesSeconds(hours = 20, minutes = 11, seconds = 10)),
        )

        @JvmStatic
        fun inputAndExpectedNonNegativeSecondsAndFractionDurations(): Stream<Arguments> = Stream.of(
            Arguments.of("0", StyledDuration.seconds(0)),
            Arguments.of("00", StyledDuration.seconds(0)),
            Arguments.of("1", StyledDuration.seconds(1)),
            Arguments.of("01", StyledDuration.seconds(1)),
            Arguments.of("10", StyledDuration.seconds(10)),
            Arguments.of("1024", StyledDuration.seconds(1024))
        )

        @JvmStatic
        fun inputAndExpectedNegativeHHMMSSDurations(): Stream<Arguments> = Stream.of(
            Arguments.of("-0:00", StyledDuration.minutesSeconds(seconds = 0, positive = false)),
            Arguments.of("-0:01", StyledDuration.minutesSeconds(seconds = 1, positive = false)),
            Arguments.of("-0:1", StyledDuration.minutesSeconds(seconds = 1, positive = false)),
            Arguments.of("-0:10", StyledDuration.minutesSeconds(seconds = 10, positive = false)),
            Arguments.of("-1:00", StyledDuration.minutesSeconds(minutes = 1, positive = false)),
            Arguments.of("-1:02", StyledDuration.minutesSeconds(minutes = 1, seconds = 2, positive = false)),
            Arguments.of("-1:10", StyledDuration.minutesSeconds(minutes = 1, seconds = 10, positive = false)),
            Arguments.of("-00:00", StyledDuration.minutesSeconds(seconds = 0, positive = false)),
            Arguments.of("-00:01", StyledDuration.minutesSeconds(seconds = 1, positive = false)),
            Arguments.of("-00:1", StyledDuration.minutesSeconds(seconds = 1, positive = false)),
            Arguments.of("-00:10", StyledDuration.minutesSeconds(seconds = 10, positive = false)),
            Arguments.of("-01:00", StyledDuration.minutesSeconds(minutes = 1, positive = false)),
            Arguments.of("-01:02", StyledDuration.minutesSeconds(minutes = 1, seconds = 2, positive = false)),
            Arguments.of("-01:10", StyledDuration.minutesSeconds(minutes = 1, seconds = 10, positive = false)),
            Arguments.of("-11:10", StyledDuration.minutesSeconds(minutes = 11, seconds = 10, positive = false)),
            Arguments.of("-0:00:01", StyledDuration.hoursMinutesSeconds(seconds = 1, positive = false)),
            Arguments.of("-0:00:1", StyledDuration.hoursMinutesSeconds(seconds = 1, positive = false)),
            Arguments.of("-0:00:10", StyledDuration.hoursMinutesSeconds(seconds = 10, positive = false)),
            Arguments.of("-0:1:10", StyledDuration.hoursMinutesSeconds(minutes = 1, seconds = 10, positive = false)),
            Arguments.of("-0:01:10", StyledDuration.hoursMinutesSeconds(minutes = 1, seconds = 10, positive = false)),
            Arguments.of("-0:1:2", StyledDuration.hoursMinutesSeconds(minutes = 1, seconds = 2, positive = false)),
            Arguments.of("-0:11:10", StyledDuration.hoursMinutesSeconds(minutes = 11, seconds = 10, positive = false)),
            Arguments.of("-1:11:10", StyledDuration.hoursMinutesSeconds(hours = 1, minutes = 11, seconds = 10, positive = false)),
            Arguments.of("-01:00:00", StyledDuration.hoursMinutesSeconds(hours = 1, positive = false)),
            Arguments.of("-01:00:02", StyledDuration.hoursMinutesSeconds(hours = 1, seconds = 2, positive = false)),
            Arguments.of("-01:00:2", StyledDuration.hoursMinutesSeconds(hours = 1, seconds = 2, positive = false)),
            Arguments.of("-01:00:10", StyledDuration.hoursMinutesSeconds(hours = 1, seconds = 10, positive = false)),
            Arguments.of("-01:02:10", StyledDuration.hoursMinutesSeconds(hours = 1, minutes = 2, seconds = 10, positive = false)),
            Arguments.of("-01:2:10", StyledDuration.hoursMinutesSeconds(hours = 1, minutes = 2, seconds = 10, positive = false)),
            Arguments.of("-01:11:10", StyledDuration.hoursMinutesSeconds(hours = 1, minutes = 11, seconds = 10, positive = false)),
            Arguments.of("-20:11:10", StyledDuration.hoursMinutesSeconds(hours = 20, minutes = 11, seconds = 10, positive = false))
        )

        @JvmStatic
        fun inputAndExpectedNegativeSecondsAndFractionDurations(): Stream<Arguments> = Stream.of(
            Arguments.of("-0", StyledDuration.seconds(0, positive = false)),
            Arguments.of("-00", StyledDuration.seconds(0, positive = false)),
            Arguments.of("-1", StyledDuration.seconds(1, positive = false)),
            Arguments.of("-01", StyledDuration.seconds(1, positive = false)),
            Arguments.of("-10", StyledDuration.seconds(10, positive = false)),
            Arguments.of("-1024", StyledDuration.seconds(1024, positive = false))
        )
    }
}
