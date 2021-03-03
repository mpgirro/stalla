package dev.stalla.model

import dev.stalla.parser.MediaTypeParser
import dev.stalla.util.escapeIfNeededTo
import java.util.Locale

/**
 * Represents a Media Type value as defiend in [RFC 2046][https://tools.ietf.org/html/rfc2046].
 * This class is heavily inspired by the
 * [ContentType][https://github.com/ktorio/ktor/blob/master/ktor-http/common/src/io/ktor/http/ContentTypes.kt]
 * class of the [Ktor][https://ktor.io] project. Special thanks to the Ktor contributors.
 *
 * Note that this class only ensures syntactically valid media type constructs,
 * but does not ensure that an instance is an
 * [IANA defined media type][http://www.iana.org/assignments/media-types/media-types.xhtml].
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
            var result = name.toLowerCase(Locale.ROOT).hashCode()
            result += 31 * result + value.toLowerCase(Locale.ROOT).hashCode()
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

    /** Creates a copy of `this` type without any parameters.*/
    public fun withoutParameters(): MediaType = when {
        parameters.isEmpty() -> this
        else -> MediaType(type, subtype)
    }

    /**
     * Checks if `this` type matches a [pattern] type taking into account placeholder symbols `*` and parameters.
     */
    public fun match(pattern: MediaType?): Boolean {
        if (pattern == null) return false
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
    public fun match(pattern: String): Boolean = match(MediaTypeParser.parse(pattern))

    override fun equals(other: Any?): Boolean =
        other is MediaType &&
            type.equals(other.type, ignoreCase = true) &&
            subtype.equals(other.subtype, ignoreCase = true) &&
            parameters == other.parameters

    override fun hashCode(): Int {
        var result = type.toLowerCase(Locale.ROOT).hashCode()
        result += 31 * result + subtype.toLowerCase(Locale.ROOT).hashCode()
        result += 31 * parameters.hashCode()
        return result
    }

    /** Gets an instance of [MediaType] from a raw value. */
    public companion object Factory : TypeFactory<MediaType> {

        /**
         * Factory method that returns the instance matching the [rawValue] parameter, if any.
         * Does not check if [rawValue] matches an
         * [IANA defined media type][http://www.iana.org/assignments/media-types/media-types.xhtml].
         *
         * @param rawValue The string representation of the instance.
         * @return The instance matching [rawValue], or `null` if no matching instance exists.
         */
        @JvmStatic
        override fun of(rawValue: String?): MediaType? = rawValue?.let { value ->
            return MediaTypeParser.parse(value)
        }

        /** Represents a pattern `* / *` to match any media type. */
        @JvmField
        public val ANY: MediaType = MediaType("*", "*")

        /** Represents the pattern `application / json`. */
        @JvmField
        public val JSON: MediaType = MediaType("application", "json")

        /** Represents the pattern `application / srt`. */
        @JvmField
        public val SRT: MediaType = MediaType("application", "srt")

        /** Represents the pattern `image / png`. */
        @JvmField
        public val PNG: MediaType = MediaType("image", "png")

        /** Represents the pattern `text / html`. */
        @JvmField
        public val HTML: MediaType = MediaType("text", "html")

        /** Represents the pattern `text / plain`. */
        @JvmField
        public val PLAIN_TEXT: MediaType = MediaType("text", "plain")
    }
}
