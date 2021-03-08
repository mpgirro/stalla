package dev.stalla.parser

import dev.stalla.model.MediaType
import dev.stalla.util.InternalAPI
import dev.stalla.util.containsMediaTypeSeparatorSymbol
import dev.stalla.util.nextIsSemicolonOrEnd
import dev.stalla.util.subtrim

/**
 * Parser implementation for [MediaType] values, as defined in [RFC 2046](https://tools.ietf.org/html/rfc2046).
 *
 * The parsing logic is heavily based on the inner workings of the
 * [ContentType](https://github.com/ktorio/ktor/blob/master/ktor-http/common/src/io/ktor/http/ContentTypes.kt)
 * class of the [Ktor](https://ktor.io) project and some related code in its HTTP package.
 * Special thanks to the Ktor contributors. The acceptance criteria and validation checks however do not
 * follow the same ruleset as Ktor does and are more based on the
 * [MIME Sniffing Standard](https://mimesniff.spec.whatwg.org).
 */
@InternalAPI
internal object MediaTypeParser {

    /**
     * Represents a media type value.
     *
     * @property essence The actual type/subtype construct value.
     * @property params Additional parameters for this value (could be empty).
     */
    private data class Value(
        val essence: String,
        val params: List<MediaType.Parameter> = emptyList()
    )

    /** Parse a string into a [MediaType] if possible. Otherwise returns `null`. */
    internal fun parse(value: String): MediaType? {
        if (value.isBlank()) return MediaType.ANY

        return parse(value) { parts, parameters ->
            val slash = parts.indexOf('/')

            if (slash == -1) {
                return if (parts.trim() == "*") MediaType.ANY else null
            }

            val type = parts.substring(0, slash).trim()

            if (type.isEmpty() || type.containsMediaTypeSeparatorSymbol()) return null

            val subtype = parts.substring(slash + 1).trim()

            if (subtype.isEmpty() || subtype.containsMediaTypeSeparatorSymbol()) return null

            if (type == "*" && subtype != "*") return null

            MediaType(type, subtype, parameters)
        }
    }

    private inline fun <R> parse(value: String, init: (String, List<MediaType.Parameter>) -> R): R {
        val mediaTypeValue = parseValue(value).single()
        return init(mediaTypeValue.essence, mediaTypeValue.params)
    }

    private fun parseValue(text: String?): List<Value> {
        if (text == null) return emptyList()

        var position = 0
        val items = lazy(LazyThreadSafetyMode.NONE) { arrayListOf<Value>() }
        while (position <= text.lastIndex) {
            position = parseItem(text, position, items)
        }
        return items.valueOrEmpty()
    }

    private fun parseItem(
        text: String,
        start: Int,
        items: Lazy<ArrayList<Value>>
    ): Int {
        var position = start
        val parameters = lazy(LazyThreadSafetyMode.NONE) { arrayListOf<MediaType.Parameter>() }
        var valueEnd: Int? = null

        while (position <= text.lastIndex) {
            when (text[position]) {
                ';' -> {
                    if (valueEnd == null) valueEnd = position
                    position = parseParameter(text, position + 1, parameters)
                }
                else -> {
                    position += 1
                }
            }
        }

        items.value.add(Value(text.subtrim(start, valueEnd ?: position), parameters.valueOrEmpty()))
        return position
    }

    private fun parseParameter(
        text: String,
        start: Int,
        parameters: Lazy<ArrayList<MediaType.Parameter>>
    ): Int {
        fun containsParam(name: String): Boolean = parameters.value.any { param -> param.key == name }

        fun addParam(text: String, start: Int, end: Int, value: String) {
            val name = text.subtrim(start, end)
            if (name.isEmpty()) return
            if (containsParam(name)) return

            parameters.value.add(MediaType.Parameter(name, value))
        }

        var position = start
        while (position <= text.lastIndex) {
            when (text[position]) {
                '=' -> {
                    val paramKey = text.subtrim(start, position)
                    val (paramEnd, paramValue) = parseParameterValue(text, position + 1)
                    if (!paramKey.containsMediaTypeSeparatorSymbol() && paramValue.isNotBlank()) {
                        addParam(text, start, position, paramValue)
                    }
                    return paramEnd
                }
                else -> position++
            }
        }

        addParam(text, start, position, "")
        return position
    }

    private fun parseParameterValue(value: String, start: Int): Pair<Int, String> {
        if (value.length == start) return start to ""

        var position = start
        if (value[start].isQuoteSymbol()) return parseParameterValueQuoted(value, position + 1)

        while (position <= value.lastIndex) {
            when (value[position]) {
                ';' -> return position to value.subtrim(start, position)
                else -> position++
            }
        }
        return position to value.subtrim(start, position)
    }

    private fun parseParameterValueQuoted(value: String, start: Int): Pair<Int, String> {
        var position = start
        val builder = StringBuilder()
        loop@ while (position <= value.lastIndex) {
            val currentChar = value[position]

            when {
                currentChar.isQuoteSymbol() && value.nextIsSemicolonOrEnd(position) -> {
                    return position + 1 to builder.toString()
                }
                currentChar == '\\' && position < value.lastIndex - 2 -> {
                    builder.append(value[position + 1])
                    position += 2
                    continue@loop
                }
            }

            builder.append(currentChar)
            position++

            if (currentChar.isQuoteSymbol() && !value.nextIsSemicolonOrEnd(position)) {
                break@loop
            }
        }

        // The value is unquoted here
        return position to '"' + builder.toString()
    }

    private fun <T> Lazy<List<T>>.valueOrEmpty(): List<T> = if (isInitialized()) value else emptyList()

    private fun Char.isQuoteSymbol(): Boolean = this == '"' || this == '\''
}
