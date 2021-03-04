package dev.stalla.util

/** Returns the trimmed string, or `null` if it was blank to begin with. */
@InternalApi
internal fun String?.trimmedOrNullIfBlank(): String? {
    if (this == null) return null
    return trim().ifEmpty { null }
}

/** Returns `true` when this string is neither `null` nor blank. */
@InternalApi
internal fun String?.isNeitherNullNorBlank(): Boolean = this != null && isNotBlank()

/** Appends this string to the [StringBuilder], escapes characters on demand. */
@InternalApi
internal fun String.escapeIfNeededTo(out: StringBuilder) = when {
    checkNeedEscape() -> out.append(this.quote())
    else -> out.append(this)
}

/** Separator symbols listed in RFC 2616 https://tools.ietf.org/html/rfc2616#section-2.2 */
@InternalApi
internal val mediaTypeSeparatorSymbols: Set<Char> =
    setOf('(', ')', '<', '>', '@', ',', ';', ':', '\\', '\"', '/', '[', ']', '?', '=', '{', '}', ' ', '\t', '\n', '\r')

/** Return `true` if this string contains any character from [mediaTypeSeparatorSymbols]. */
@InternalApi
internal fun String.containsMediaTypeSeparatorSymbol(): Boolean =
    this.any { c -> mediaTypeSeparatorSymbols.contains(c) }

/** Returns `true` of this string contains characters that need excaping. */
@InternalApi
internal fun String.checkNeedEscape(): Boolean {
    if (isEmpty()) return true
    if (isQuoted()) return false

    for (index in 0 until length) {
        if (mediaTypeSeparatorSymbols.contains(this[index])) return true
    }

    return false
}

/** Quotes this string. */
@InternalApi
internal fun String.quote(): String = buildString { this@quote.quoteTo(this) }

/** Appends the quotes form of this string to the [StringBuilder]. */
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
