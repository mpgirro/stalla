package dev.stalla.util

import kotlin.contracts.contract

/** Returns the trimmed string, or `null` if it was blank to begin with. */
@InternalApi
internal fun String?.trimmedOrNullIfBlank(): String? {
    contract {
        returnsNotNull() implies (this@trimmedOrNullIfBlank != null)
    }
    if (this == null) return null
    return trim().ifEmpty { null }
}

/** Returns `true` when this string is neither `null` nor blank. */
@InternalApi
internal fun String?.isNeitherNullNorBlank(): Boolean {
    contract {
        returns(true) implies (this@isNeitherNullNorBlank != null)
    }
    return this != null && isNotBlank()
}

/** Separator symbols listed in RFC 2616 https://tools.ietf.org/html/rfc2616#section-2.2 */
@InternalApi
internal val mediaTypeSeparatorSymbols: Set<Char> =
    setOf('(', ')', '<', '>', '@', ',', ';', ':', '\\', '\"', '/', '[', ']', '?', '=', '{', '}', ' ', '\t', '\n', '\r')

/** Return `true` if this string contains any character from [mediaTypeSeparatorSymbols]. */
@InternalApi
internal fun String.containsMediaTypeSeparatorSymbol(): Boolean =
    this.any { c -> mediaTypeSeparatorSymbols.contains(c) }

/** Quotes this string. */
@InternalApi
internal fun String.quote(): String = buildString { this@quote.quoteTo(this) }

/** Appends this string in quotes to the [StringBuilder]. */
@InternalApi
internal fun String.quoteTo(out: StringBuilder) {
    out.append("\"")
    for (i in 0 until length) {
        when (val ch = this[i]) {
            '\\' -> out.append("\\\\")
            '\n' -> out.append("\\n")
            '\r' -> out.append("\\r")
            '\t' -> out.append("\\t")
            '\"' -> out.append("\\\"")
            else -> out.append(ch)
        }
    }
    out.append("\"")
}

/** Returns `true` of this string is quoted. */
@InternalApi
internal fun String.isQuoted(): Boolean {
    if (length < 2) return false
    if (first() != '"' || last() != '"') return false

    var startIndex = 1
    do {
        val index = indexOf('"', startIndex)
        if (index == lastIndex) break

        var slashesCount = 0
        var slashIndex = index - 1
        while (this[slashIndex] == '\\') {
            slashesCount++
            slashIndex--
        }
        if (slashesCount % 2 == 0) return false

        startIndex = index + 1
    } while (startIndex < length)

    return true
}

/** Trims a substring of this string. */
@InternalApi
internal fun String.subtrim(start: Int, end: Int): String = substring(start, end).trim()

/** Returns `true` if the next non blank character is a semicolon, or if the string is ended. */
@InternalApi
internal fun String.nextIsSemicolonOrEnd(start: Int): Boolean {
    var position = start + 1
    loop@ while (position < length && get(position) == ' ') {
        position += 1
    }

    return position == length || get(position) == ';'
}

/** Returns `true` if this string equals `other` ignoring character case. */
@InternalApi
internal fun String?.equalsIgnoreCase(other: String?): Boolean = this.equals(other, ignoreCase = true)
