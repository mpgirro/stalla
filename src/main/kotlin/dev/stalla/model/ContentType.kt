package dev.stalla.model

import dev.stalla.util.escapeIfNeededTo
import dev.stalla.util.nextIsSemicolonOrEnd
import dev.stalla.util.subtrim

/**
 * Represents a single value parameter
 * @property name of parameter
 * @property value of parameter
 */
public data class HeaderValueParam(val name: String, val value: String) {
    override fun equals(other: Any?): Boolean {
        return other is HeaderValueParam &&
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
 * Exception thrown when a content type string is malformed.
 */
public class BadContentTypeFormatException(value: String) : Exception("Bad Content-Type format: $value")


/**
 * Represents a value for a `Content-Type` header.
 * @property contentType represents a type part of the media type.
 * @property contentSubtype represents a subtype part of the media type.
 */
public class ContentType private constructor(
    public val contentType: String,
    public val contentSubtype: String,
    private val content: String,
    public val parameters: List<HeaderValueParam> = emptyList()
) {

    public constructor(
        contentType: String,
        contentSubtype: String,
        parameters: List<HeaderValueParam> = emptyList()
    ) : this(
        contentType,
        contentSubtype,
        "$contentType/$contentSubtype",
        parameters
    )

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
    public fun withParameter(name: String, value: String): ContentType {
        if (hasParameter(name, value)) return this

        return ContentType(contentType, contentSubtype, content, parameters + HeaderValueParam(name, value))
    }

    private fun hasParameter(name: String, value: String): Boolean = when (parameters.size) {
        0 -> false
        1 -> parameters[0].let { it.name.equals(name, ignoreCase = true) && it.value.equals(value, ignoreCase = true) }
        else -> parameters.any { it.name.equals(name, ignoreCase = true) && it.value.equals(value, ignoreCase = true) }
    }

    /**
     * Creates a copy of `this` type without any parameters
     */
    public fun withoutParameters(): ContentType = when {
        parameters.isEmpty() -> this
        else -> ContentType(contentType, contentSubtype)
    }

    /**
     * Checks if `this` type matches a [pattern] type taking into account placeholder symbols `*` and parameters.
     */
    public fun match(pattern: ContentType): Boolean {
        if (pattern.contentType != "*" && !pattern.contentType.equals(contentType, ignoreCase = true)) return false

        if (pattern.contentSubtype != "*" && !pattern.contentSubtype.equals(contentSubtype, ignoreCase = true)) return false

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
        other is ContentType &&
            contentType.equals(other.contentType, ignoreCase = true) &&
            contentSubtype.equals(other.contentSubtype, ignoreCase = true) &&
            parameters == other.parameters

    override fun hashCode(): Int {
        var result = contentType.toLowerCase().hashCode()
        result += 31 * result + contentSubtype.toLowerCase().hashCode()
        result += 31 * parameters.hashCode()
        return result
    }

    public companion object {

        /**
         * Represents a pattern `* / *` to match any content type.
         */
        public val ANY: ContentType = ContentType("*", "*")

        public val IMAGE_PNG: ContentType = ContentType("image", "png")

        public val TEXT_PLAIN: ContentType = ContentType("text", "plain")

        /**
         * Parses a string representing a `Content-Type` header into a [ContentType] instance.
         */
        public fun parse(value: String): ContentType {
            if (value.isBlank()) return ANY

            return parse(value) { parts, parameters ->
                val slash = parts.indexOf('/')

                if (slash == -1) {
                    if (parts.trim() == "*") return ANY

                    throw BadContentTypeFormatException(value)
                }

                val type = parts.substring(0, slash).trim()

                if (type.isEmpty()) throw BadContentTypeFormatException(value)

                val subtype = parts.substring(slash + 1).trim()

                if (subtype.isEmpty() || subtype.contains('/')) {
                    throw BadContentTypeFormatException(value)
                }

                ContentType(type, subtype, parameters)
            }
        }

        /** Parse header with parameter and pass it to [init] function to instantiate particular type */
        private inline fun <R> parse(value: String, init: (String, List<HeaderValueParam>) -> R): R {
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
            val parameters = lazy(LazyThreadSafetyMode.NONE) { arrayListOf<HeaderValueParam>() }
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
            parameters: Lazy<ArrayList<HeaderValueParam>>
        ): Int {
            fun addParam(text: String, start: Int, end: Int, value: String) {
                val name = text.subtrim(start, end)
                if (name.isEmpty()) return

                parameters.value.add(HeaderValueParam(name, value))
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
    private data class HeaderValue(val value: String, val params: List<HeaderValueParam> = listOf()) {
        /** Value's quality according to `q` parameter or `1.0` if missing or invalid */
        val quality: Double =
            params.firstOrNull { it.name == "q" }?.value?.toDoubleOrNull()?.takeIf { it in 0.0..1.0 } ?: 1.0
    }

}
