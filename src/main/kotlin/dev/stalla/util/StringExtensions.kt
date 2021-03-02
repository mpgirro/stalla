package dev.stalla.util

/** Returns the trimmed string, or null if it was blank to begin with. */
@InternalApi
internal fun String?.trimmedOrNullIfBlank(): String? {
    if (this == null) return null
    return trim().ifEmpty { null }
}

/** Returns `true` when this string is neither `null` nor blank. */
@InternalApi
internal fun String?.isNeitherNullNorBlank(): Boolean = this != null && isNotBlank()

internal fun String.escapeIfNeededTo(out: StringBuilder) = when {
    checkNeedEscape() -> out.append(this.quote())
    else -> out.append(this)
}

/** Separator symbols listed in RFC https://tools.ietf.org/html/rfc2616#section-2.2 */
private val HeaderFieldValueSeparators =
    setOf('(', ')', '<', '>', '@', ',', ';', ':', '\\', '\"', '/', '[', ']', '?', '=', '{', '}', ' ', '\t', '\n', '\r')


internal fun String.checkNeedEscape(): Boolean {
    if (isEmpty()) return true
    if (isQuoted()) return false

    for (index in 0 until length) {
        if (HeaderFieldValueSeparators.contains(this[index])) return true
    }

    return false
}

internal fun String.quote(): String = buildString { this@quote.quoteTo(this) }

internal fun String.quoteTo(out: StringBuilder) {
    out.append("\"")
    for (i in 0 until length) {
        val ch = this[i]
        when (ch) {
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

internal fun String.isQuoted(): Boolean {
    if (length < 2) {
        return false
    }
    if (first() != '"' || last() != '"') {
        return false
    }
    var startIndex = 1
    do {
        val index = indexOf('"', startIndex)
        if (index == lastIndex) {
            break
        }

        var slashesCount = 0
        var slashIndex = index - 1
        while (this[slashIndex] == '\\') {
            slashesCount++
            slashIndex--
        }
        if (slashesCount % 2 == 0) {
            return false
        }

        startIndex = index + 1
    } while (startIndex < length)

    return true
}

internal fun String.subtrim(start: Int, end: Int): String {
    return substring(start, end).trim()
}

internal fun String.nextIsSemicolonOrEnd(start: Int): Boolean {
    var position = start + 1
    loop@ while (position < length && get(position) == ' ') {
        position += 1
    }

    return position == length || get(position) == ';'
}
