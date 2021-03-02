package dev.stalla.parser

import dev.stalla.model.BadMediaTypeFormatException
import dev.stalla.model.MediaType
import dev.stalla.util.InternalApi
import dev.stalla.util.nextIsSemicolonOrEnd
import dev.stalla.util.subtrim

@InternalApi
internal object MediaTypeParser {

    /**
     * Represents a media type value.
     *
     * @property value
     * @property params for this value (could be empty).
     */
    private data class MediaTypeValue(
        val value: String,
        val params: List<MediaType.Parameter> = emptyList()
    )

    internal fun parse(value: String): MediaType {
        if (value.isBlank()) return MediaType.ANY

        return parse(value) { parts, parameters ->
            val slash = parts.indexOf('/')

            if (slash == -1) {
                if (parts.trim() == "*") return MediaType.ANY

                throw BadMediaTypeFormatException(value)
            }

            val type = parts.substring(0, slash).trim()

            if (type.isEmpty()) throw BadMediaTypeFormatException(value)

            val subtype = parts.substring(slash + 1).trim()

            if (subtype.isEmpty() || subtype.contains('/')) {
                throw BadMediaTypeFormatException(value)
            }

            MediaType(type, subtype, parameters)
        }
    }

    private inline fun <R> parse(value: String, init: (String, List<MediaType.Parameter>) -> R): R {
        val mediaTypeValue = parseMediaTypeValue(value).single()
        return init(mediaTypeValue.value, mediaTypeValue.params)
    }

    private fun parseMediaTypeValue(text: String?): List<MediaTypeValue> {
        if (text == null) return emptyList()

        var position = 0
        val items = lazy(LazyThreadSafetyMode.NONE) { arrayListOf<MediaTypeValue>() }
        while (position <= text.lastIndex) {
            position = parseMediaTypeValueItem(text, position, items)
        }
        return items.valueOrEmpty()
    }

    private fun parseMediaTypeValueItem(
        text: String,
        start: Int,
        items: Lazy<ArrayList<MediaTypeValue>>
    ): Int {
        var position = start
        val parameters = lazy(LazyThreadSafetyMode.NONE) { arrayListOf<MediaType.Parameter>() }
        var valueEnd: Int? = null

        while (position <= text.lastIndex) {
            when (text[position]) {
                ',' -> {
                    items.value.add(
                        MediaTypeValue(
                            value = text.subtrim(start, valueEnd ?: position),
                            params = parameters.valueOrEmpty()
                        )
                    )
                    return position + 1
                }
                ';' -> {
                    if (valueEnd == null) valueEnd = position
                    position = parseMediaTypeValueParameter(text, position + 1, parameters)
                }
                else -> {
                    position += 1
                }
            }
        }

        items.value.add(MediaTypeValue(text.subtrim(start, valueEnd ?: position), parameters.valueOrEmpty()))
        return position
    }

    private fun parseMediaTypeValueParameter(
        text: String,
        start: Int,
        parameters: Lazy<ArrayList<MediaType.Parameter>>
    ): Int {
        fun addParam(text: String, start: Int, end: Int, value: String) {
            val name = text.subtrim(start, end)
            if (name.isEmpty()) return

            parameters.value.add(MediaType.Parameter(name, value))
        }

        var position = start
        while (position <= text.lastIndex) {
            when (text[position]) {
                '=' -> {
                    val (paramEnd, paramValue) = parseMediaTypeValueParameterValue(text, position + 1)
                    addParam(text, start, position, paramValue)
                    return paramEnd
                }
                ';', ',' -> {
                    addParam(text, start, position, "")
                    return position
                }
                else -> position++
            }
        }

        addParam(text, start, position, "")
        return position
    }

    private fun parseMediaTypeValueParameterValue(value: String, start: Int): Pair<Int, String> {
        if (value.length == start) return start to ""

        var position = start
        if (value[start] == '"') return parseMediaTypeValueParameterValueQuoted(value, position + 1)

        while (position <= value.lastIndex) {
            when (value[position]) {
                ';', ',' -> return position to value.subtrim(start, position)
                else -> position++
            }
        }
        return position to value.subtrim(start, position)
    }

    private fun parseMediaTypeValueParameterValueQuoted(value: String, start: Int): Pair<Int, String> {
        var position = start
        val builder = StringBuilder()
        loop@ while (position <= value.lastIndex) {
            val currentChar = value[position]

            when {
                currentChar == '"' && value.nextIsSemicolonOrEnd(position) -> {
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
        }

        // The value is unquoted here
        return position to '"' + builder.toString()
    }

    private fun <T> Lazy<List<T>>.valueOrEmpty(): List<T> = if (isInitialized()) value else emptyList()
}
