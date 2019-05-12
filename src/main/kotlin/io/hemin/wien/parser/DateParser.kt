package io.hemin.wien.parser

import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.*

/**
 * Parser implementation for transforming date strings to date objects.
 * Various formats are supported. This class attempts to find the correct
 * format to produce the intended date object.
 */
class DateParser {

    companion object {

        private val masks = arrayOf(
            "EEE, dd MMM yy HH:mm:ss z",
            "EEE, dd MMM yy HH:mm z",
            "dd MMM yy HH:mm:ss z",
            "dd MMM yy HH:mm z",
            "yyyy-MM-dd'T'HH:mm:ss.SSSz",
            "yyyy-MM-dd't'HH:mm:ss.SSSz",
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            "yyyy-MM-dd't'HH:mm:ss.SSS'z'",
            "yyyy-MM-dd'T'HH:mm:ssz",
            "yyyy-MM-dd't'HH:mm:ssz",
            "yyyy-MM-dd'T'HH:mm:ssZ",
            "yyyy-MM-dd't'HH:mm:ssZ",
            "yyyy-MM-dd'T'HH:mm:ss'Z'",
            "yyyy-MM-dd't'HH:mm:ss'z'",
            "yyyy-MM-dd'T'HH:mmz",
            "yyyy-MM'T'HH:mmz",
            "yyyy'T'HH:mmz",
            "yyyy-MM-dd't'HH:mmz",
            "yyyy-MM-dd'T'HH:mm'Z'",
            "yyyy-MM-dd't'HH:mm'z'",
            "yyyy-MM-dd",
            "yyyy-MM",
            "yyyy"
        )

        fun parse(value: String?): Date? = parse(value, Locale.forLanguageTag("en-US"))

        fun parse(value: String?, locale: Locale): Date? {
            var d: Date? = null
            value?.let {
                val sDate = it.trim()
                var pp: ParsePosition?
                var i = 0
                while (d == null && i < masks.size) {
                    val df = SimpleDateFormat(masks[i], locale)
                    df.isLenient = true
                    try {
                        pp = ParsePosition(0)
                        d = df.parse(sDate, pp)
                        if (pp.index != sDate.length) {
                            d = null
                        }
                    } catch (ex: NullPointerException) {
                        d = null
                    }

                    i++
                }
            }
            return d
        }
    }
}
