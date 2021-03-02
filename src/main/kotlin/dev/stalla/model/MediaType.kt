package dev.stalla.model

import dev.stalla.util.escapeIfNeededTo
import dev.stalla.util.nextIsSemicolonOrEnd
import dev.stalla.util.subtrim

/** Exception thrown when a media type string is malformed. */
public class BadMediaTypeFormatException(value: String) : Exception("Bad Media-Type format: $value")

/**
 * Represents a Media Type value as defiend by [RFC 2046][https://tools.ietf.org/html/rfc2046].
 * This class is heavily based on the
 * [ContentType][https://github.com/ktorio/ktor/blob/master/ktor-http/common/src/io/ktor/http/ContentTypes.kt]
 * class of the [Ktor][https://ktor.io] project. Special thanks to the Ktor contributors.
 *
 * @property type The type part of the media type.
 * @property subtype The subtype part of the media type.
 * @property parameters The modifiers of the media subtype.
 */
public open class MediaType private constructor(
    public open val type: String,
    public open val subtype: String,
    private val content: String,
    public val parameters: List<Parameter> = emptyList()
) {

    public constructor(
        contentType: String,
        contentSubtype: String,
        parameters: List<Parameter> = emptyList()
    ) : this(
        contentType,
        contentSubtype,
        "$contentType/$contentSubtype",
        parameters
    )

    /**
     * Represents a single value parameter.
     *
     * @property name of parameter
     * @property value of parameter
     */
    public data class Parameter(val name: String, val value: String) {
        override fun equals(other: Any?): Boolean {
            return other is Parameter &&
                other.name.equals(name, ignoreCase = true) &&
                other.value.equals(value, ignoreCase = true)
        }

        override fun hashCode(): Int {
            var result = name.toLowerCase().hashCode()
            result += 31 * result + value.toLowerCase().hashCode()
            return result
        }
    }

    /**
     * The first value for the parameter with [name] comparing case-insensitively or `null` if no such parameters found.
     */
    public fun parameter(name: String): String? =
        parameters.firstOrNull { it.name.equals(name, ignoreCase = true) }?.value

    override fun toString(): String = when {
        parameters.isEmpty() -> content
        else -> {
            val size = content.length + parameters.sumBy { it.name.length + it.value.length + 3 }
            StringBuilder(size).apply {
                append(content)
                for (element in parameters) {
                    val (name, value) = element
                    append("; ")
                    append(name)
                    append("=")
                    value.escapeIfNeededTo(this)
                }
            }.toString()
        }
    }

    /** Creates a copy of `this` type with the added parameter with the [name] and [value]. */
    public fun withParameter(name: String, value: String): MediaType {
        if (hasParameter(name, value)) return this

        return MediaType(type, subtype, content, parameters + Parameter(name, value))
    }

    private fun hasParameter(name: String, value: String): Boolean = when (parameters.size) {
        0 -> false
        1 -> parameters[0].let { it.name.equals(name, ignoreCase = true) && it.value.equals(value, ignoreCase = true) }
        else -> parameters.any { it.name.equals(name, ignoreCase = true) && it.value.equals(value, ignoreCase = true) }
    }

    /**
     * Creates a copy of `this` type without any parameters
     */
    public fun withoutParameters(): MediaType = when {
        parameters.isEmpty() -> this
        else -> MediaType(type, subtype)
    }

    /**
     * Checks if `this` type matches a [pattern] type taking into account placeholder symbols `*` and parameters.
     */
    public fun match(pattern: MediaType): Boolean {
        if (pattern.type != "*" && !pattern.type.equals(type, ignoreCase = true)) return false

        if (pattern.subtype != "*" && !pattern.subtype.equals(subtype, ignoreCase = true)) return false

        for ((patternName, patternValue) in pattern.parameters) {
            val matches = when (patternName) {
                "*" -> {
                    when (patternValue) {
                        "*" -> true
                        else -> parameters.any { p -> p.value.equals(patternValue, ignoreCase = true) }
                    }
                }
                else -> {
                    val value = parameter(patternName)
                    when (patternValue) {
                        "*" -> value != null
                        else -> value.equals(patternValue, ignoreCase = true)
                    }
                }
            }

            if (!matches) return false
        }
        return true
    }

    /**
     * Checks if `this` type matches a [pattern] type taking into account placeholder symbols `*` and parameters.
     */
    public fun match(pattern: String): Boolean = match(parse(pattern))

    override fun equals(other: Any?): Boolean =
        other is MediaType &&
            type.equals(other.type, ignoreCase = true) &&
            subtype.equals(other.subtype, ignoreCase = true) &&
            parameters == other.parameters

    override fun hashCode(): Int {
        var result = type.toLowerCase().hashCode()
        result += 31 * result + subtype.toLowerCase().hashCode()
        result += 31 * parameters.hashCode()
        return result
    }

    public companion object Factory : TypeFactory<MediaType> {

        /**
         * Factory method that returns the instance matching the [rawValue] parameter, if any.
         *
         * @param rawValue The string representation of the instance.
         * @return The instance matching [rawValue], or `null` if no matching instance exists.
         * @throws BadMediaTypeFormatException
         */
        @JvmStatic
        @Throws(BadMediaTypeFormatException::class)
        override fun of(rawValue: String?): MediaType? = rawValue?.let { value ->
            return parse(value)
        }

        /** Represents a pattern `* / *` to match any media type. */
        @JvmField
        public val ANY: MediaType = MediaType("*", "*")

        @JvmField
        public val JSON: MediaType = MediaType("application", "json")

        @JvmField
        public val SRT: MediaType = MediaType("application", "srt")

        @JvmField
        public val PNG: MediaType = MediaType("image", "png")

        @JvmField
        public val HTML: MediaType = MediaType("text", "html")

        @JvmField
        public val PLAIN_TEXT: MediaType = MediaType("text", "plain")

        /** Parses a string representing into a [MediaType] instance. */
        private fun parse(value: String): MediaType {
            if (value.isBlank()) return ANY

            return parse(value) { parts, parameters ->
                val slash = parts.indexOf('/')

                if (slash == -1) {
                    if (parts.trim() == "*") return ANY

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

        /** Parse header with parameter and pass it to [init] function to instantiate particular type */
        private inline fun <R> parse(value: String, init: (String, List<Parameter>) -> R): R {
            val headerValue = parseHeaderValue(value).single()
            return init(headerValue.value, headerValue.params)
        }

        private fun parseHeaderValue(text: String?): List<HeaderValue> {
            if (text == null) return emptyList()

            var position = 0
            val items = lazy(LazyThreadSafetyMode.NONE) { arrayListOf<HeaderValue>() }
            while (position <= text.lastIndex) {
                position = parseHeaderValueItem(text, position, items)
            }
            return items.valueOrEmpty()
        }


        private fun parseHeaderValueItem(
            text: String,
            start: Int,
            items: Lazy<ArrayList<HeaderValue>>
        ): Int {
            var position = start
            val parameters = lazy(LazyThreadSafetyMode.NONE) { arrayListOf<Parameter>() }
            var valueEnd: Int? = null

            while (position <= text.lastIndex) {
                when (text[position]) {
                    ',' -> {
                        items.value.add(HeaderValue(text.subtrim(start, valueEnd ?: position), parameters.valueOrEmpty()))
                        return position + 1
                    }
                    ';' -> {
                        if (valueEnd == null) valueEnd = position
                        position = parseHeaderValueParameter(text, position + 1, parameters)
                    }
                    else -> {
                        position += 1
                    }
                }
            }

            items.value.add(HeaderValue(text.subtrim(start, valueEnd ?: position), parameters.valueOrEmpty()))
            return position
        }

        private fun parseHeaderValueParameter(
            text: String,
            start: Int,
            parameters: Lazy<ArrayList<Parameter>>
        ): Int {
            fun addParam(text: String, start: Int, end: Int, value: String) {
                val name = text.subtrim(start, end)
                if (name.isEmpty()) return

                parameters.value.add(Parameter(name, value))
            }

            var position = start
            while (position <= text.lastIndex) {
                when (text[position]) {
                    '=' -> {
                        val (paramEnd, paramValue) = parseHeaderValueParameterValue(text, position + 1)
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

        private fun parseHeaderValueParameterValue(value: String, start: Int): Pair<Int, String> {
            if (value.length == start) return start to ""

            var position = start
            if (value[start] == '"') return parseHeaderValueParameterValueQuoted(value, position + 1)

            while (position <= value.lastIndex) {
                when (value[position]) {
                    ';', ',' -> return position to value.subtrim(start, position)
                    else -> position++
                }
            }
            return position to value.subtrim(start, position)
        }

        private fun parseHeaderValueParameterValueQuoted(value: String, start: Int): Pair<Int, String> {
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

    /**
     * Represents a header value.
     * @property value
     * @property params for this value (could be empty)
     */
    private data class HeaderValue(val value: String, val params: List<Parameter> = listOf()) {
        /** Value's quality according to `q` parameter or `1.0` if missing or invalid */
        val quality: Double =
            params.firstOrNull { it.name == "q" }?.value?.toDoubleOrNull()?.takeIf { it in 0.0..1.0 } ?: 1.0
    }

}
