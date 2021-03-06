package dev.stalla.model

import dev.stalla.model.MediaType.Factory
import dev.stalla.parser.MediaTypeParser
import dev.stalla.util.InternalApi
import dev.stalla.util.containsMediaTypeSeparatorSymbol
import dev.stalla.util.escapeIfNeededTo
import java.util.Locale

/**
 * Represents a Media Type value, as defined in [RFC 2046](https://tools.ietf.org/html/rfc2046).
 * This class is heavily inspired by the
 * [ContentType](https://github.com/ktorio/ktor/blob/master/ktor-http/common/src/io/ktor/http/ContentTypes.kt)
 * class of the [Ktor](https://ktor.io) project. Special thanks to the Ktor contributors.
 *
 * Note that this class only ensures syntactically valid media type constructs,
 * but does not ensure that an instance is actually an
 * [IANA media type](http://www.iana.org/assignments/media-types/media-types.xhtml).
 *
 * Some useful predefined instances are provided by the [companion object][Factory] (static class members in Java).
 *
 * @property type The type part of the media type.
 * @property subtype The subtype part of the media type.
 * @property essence The [type] followed by `/` followed by [subtype].
 * @property parameters The modifiers of the media subtype.
 *
 * @see Factory Companion object exposing references to the some predefined instances and a factory method.
 */
public open class MediaType private constructor(
    public open val type: String,
    public open val subtype: String,
    public val essence: String,
    public val parameters: List<Parameter> = emptyList()
) {

    @InternalApi
    internal constructor(
        type: String,
        subtype: String,
        parameters: List<Parameter> = emptyList()
    ) : this(type, subtype, "$type/$subtype", parameters)

    /**
     * Represents a single value parameter.
     *
     * @property key The key of parameter.
     * @property value The value of parameter.
     */
    public data class Parameter(val key: String, val value: String) {
        override fun equals(other: Any?): Boolean {
            return other is Parameter &&
                other.key.equals(key, ignoreCase = true) &&
                other.value.equals(value, ignoreCase = true)
        }

        override fun hashCode(): Int {
            var result = key.toLowerCase(Locale.ROOT).hashCode()
            result += 31 * result + value.toLowerCase(Locale.ROOT).hashCode()
            return result
        }
    }

    /**
     * The first value for the parameter with [key] comparing
     * case-insensitively or `null` if no such parameters found.
     */
    public fun parameter(key: String): String? =
        parameters.firstOrNull { it.key.equals(key, ignoreCase = true) }?.value

    /** Creates a copy of `this` type with an added parameter of [key] and [value]. */
    public fun withParameter(key: String, value: String): MediaType {
        if (key.isBlank() || key.containsMediaTypeSeparatorSymbol()) return this
        if (value.isBlank()) return this
        if (hasParameter(key, value)) return this

        return MediaType(type, subtype, essence, parameters + Parameter(key, value))
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

    override fun toString(): String = when {
        parameters.isEmpty() -> essence
        else -> {
            val size = essence.length + parameters.sumBy { it.key.length + it.value.length + 3 }
            StringBuilder(size).apply {
                append(essence)
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

    private fun hasParameter(attribute: String, value: String): Boolean = when (parameters.size) {
        0 -> false
        1 -> parameters[0].let { param ->
            param.key.equals(attribute, ignoreCase = true) && param.value.equals(value, ignoreCase = true)
        }
        else -> parameters.any { param ->
            param.key.equals(attribute, ignoreCase = true) && param.value.equals(value, ignoreCase = true)
        }
    }

    /** Gets an instance of [MediaType] from a raw value. Exposes several predefined media type constants. */
    public companion object Factory : TypeFactory<MediaType> {

        /**
         * Factory method that returns the instance matching the [rawValue] parameter, if any.
         * Does not check if [rawValue] matches an
         * [IANA media type][http://www.iana.org/assignments/media-types/media-types.xhtml].
         *
         * @param rawValue The string representation of the instance.
         * @return The instance matching [rawValue], or `null` if no matching instance exists.
         */
        @JvmStatic
        override fun of(rawValue: String?): MediaType? = rawValue?.let { value ->
            return MediaTypeParser.parse(value)
        }

        /** [Advanced Audio Coding](https://en.wikipedia.org/wiki/Advanced_Audio_Coding) pattern `audio/aac`. */
        @JvmField
        public val AAC_AUDIO: MediaType = MediaType("audio", "aac")

        /** Represents the pattern `* / *` to match any media type. */
        @JvmField
        public val ANY: MediaType = MediaType("*", "*")

        /** Represents the pattern `audio / *` to match any audio media type. */
        @JvmField
        public val ANY_AUDIO: MediaType = MediaType("audio", "*")

        /** Represents the pattern `image / *` to match any image media type. */
        @JvmField
        public val ANY_IMAGE: MediaType = MediaType("image", "*")

        /** Represents the pattern `text / *` to match any text media type. */
        @JvmField
        public val ANY_TEXT: MediaType = MediaType("text", "*")

        /** Represents the pattern `video / *` to match any video media type. */
        @JvmField
        public val ANY_VIDEO: MediaType = MediaType("video", "*")

        /**
         * [Atom Syndication Format](https://en.wikipedia.org/wiki/Atom_(Web_standard))
         * pattern `application/atom+xml`, as defined by [RFC 4287](https://tools.ietf.org/html/rfc4287).
         */
        @JvmField
        public val ATOM: MediaType = MediaType("application", "atom+xml")

        /**
         * Basic Audio pattern `audio/basic`, as defined by
         * [RFC 2046](https://tools.ietf.org/html/rfc2046#section-4.3).
         */
        @JvmField
        public val BASIC_AUDIO: MediaType = MediaType("audio", "basic")

        /**
         * [Bitmap file format](https://en.wikipedia.org/wiki/BMP_file_format) pattern
         * `image/bmp`, as defined by [RFC 7903](https://tools.ietf.org/html/rfc7903).
         */
        @JvmField
        public val BMP: MediaType = MediaType("image", "bmp")

        /**
         * [Cascading Style Sheet](https://www.w3.org/Style/CSS/) pattern
         * `text/css`, as defined by [RFC 2318](https://tools.ietf.org/html/rfc2318).
         */
        @JvmField
        public val CSS: MediaType = MediaType("text", "css")

        /**
         * Comma-Separated Values pattern `text/csv`, as defined by
         * [RFC 4180](https://tools.ietf.org/html/rfc4180).
         */
        @JvmField
        public val CSV: MediaType = MediaType("text", "csv")

        /**
         * [EPUB Open Container Format](http://www.idpf.org/epub/30/spec/epub30-ocf.html)
         * pattern `application/epub+zip`.
         */
        @JvmField
        public val EPUB: MediaType = MediaType("application", "epub+zip")

        /** [Free Lossless Audio Codec](https://xiph.org/flac/format.html) pattern `audio/flac`. */
        @JvmField
        public val FLAC: MediaType = MediaType("audio", "flac")

        /** [Graphics Interchange Format](https://en.wikipedia.org/wiki/GIF) pattern `image/gif`. */
        @JvmField
        public val GIF: MediaType = MediaType("image", "gif")

        /**
         * [gzip file format](https://en.wikipedia.org/wiki/Gzip) pattern `application/gzip`,
         * as defined by [RFC 6713](https://tools.ietf.org/html/rfc6713).
         */
        @JvmField
        public val GZIP: MediaType = MediaType("application", "gzip")

        /** [HEIF image format](https://www.iana.org/assignments/media-types/image/heif) pattern `image/heif`. */
        @JvmField
        public val HEIF: MediaType = MediaType("image", "heif")

        /**
         * [Hypertext Markup Language](https://html.spec.whatwg.org) pattern `text/html`,
         * as defined by [RFC 2854](https://tools.ietf.org/html/rfc2854).
         */
        @JvmField
        public val HTML: MediaType = MediaType("text", "html")

        /**
         * JavaScript pattern `application/javascript`,
         * as defined by [RFC 4329](http://www.rfc-editor.org/rfc/rfc4329)
         * as the correct media type for JavaScript files.
         */
        @JvmField
        public val JAVASCRIPT: MediaType = MediaType("application", "javascript")

        /** [JPEG file format](https://jpeg.org/jpeg/) pattern `image/jpeg`. */
        @JvmField
        public val JPEG: MediaType = MediaType("image", "jpeg")

        /**
         * [JavaScript Object Notation](https://en.wikipedia.org/wiki/JSON) pattern
         * `application/json`, as defined by [RFC 8259](https://tools.ietf.org/html/rfc8259).
         */
        @JvmField
        public val JSON: MediaType = MediaType("application", "json")

        /** L16 audio pattern `audio/L16`, as defined by [RFC 2586](https://tools.ietf.org/html/rfc2586). */
        @JvmField
        public val L16_AUDIO: MediaType = MediaType("audio", "L16")

        /** L24 audio pattern `audio/L16`, as defined by [RFC 3190](https://tools.ietf.org/html/rfc3190). */
        @JvmField
        public val L24_AUDIO: MediaType = MediaType("audio", "L24")

        /** [Manifest for a web application](http://www.w3.org/TR/appmanifest/)  pattern `application/manifest+json`. */
        @JvmField
        public val MANIFEST_JSON: MediaType = MediaType("application", "manifest+json")

        /**
         * [Markdown](https://en.wikipedia.org/wiki/Markdown) pattern `text/markdown`,
         * as defined by [RFC 7763](https://tools.ietf.org/html/rfc7763).
         */
        @JvmField
        public val MARKDOWN: MediaType = MediaType("text", "markdown")

        /**
         * [MP4 audio stream](https://en.wikipedia.org/wiki/MPEG-4_Part_14) pattern `audio/mp4`,
         * as defined by [RFC 4337](https://tools.ietf.org/html/rfc4337#section-2).
         */
        @JvmField
        public val MP4_AUDIO: MediaType = MediaType("audio", "mp4")

        /**
         * [MP4 video stream](https://en.wikipedia.org/wiki/MPEG-4_Part_14) pattern `video/mp4`,
         * as defined by [RFC 4337](https://tools.ietf.org/html/rfc4337#section-2).
         */
        @JvmField
        public val MP4_VIDEO: MediaType = MediaType("video", "mp4")

        /**
         * [MPEG-1](https://en.wikipedia.org/wiki/MPEG-1) and
         * [MPEG-2](https://en.wikipedia.org/wiki/MPEG-2) audio pattern `audio/mpeg`,
         * as defined by [RFC 3003](https://tools.ietf.org/html/rfc3003).
         */
        @JvmField
        public val MPEG_AUDIO: MediaType = MediaType("audio", "mpeg")

        /**
         * [MPEG-1](https://en.wikipedia.org/wiki/MPEG-1) and
         * [MPEG-2](https://en.wikipedia.org/wiki/MPEG-2) video pattern `video/mpeg`.
         */
        @JvmField
        public val MPEG_VIDEO: MediaType = MediaType("video", "mpeg")

        /**
         * [Ogg](https://en.wikipedia.org/wiki/Ogg) audio pattern `audio/ogg`,
         * as defined by [RFC 5334](https://tools.ietf.org/html/rfc5334#section-2).
         */
        @JvmField
        public val OGG_AUDIO: MediaType = MediaType("audio", "ogg")

        /**
         * [Ogg](https://en.wikipedia.org/wiki/Ogg) bitstream pattern `application/ogg`,
         * as defined by [RFC 3534](https://tools.ietf.org/html/rfc3534).
         */
        @JvmField
        public val OGG_CONTAINER: MediaType = MediaType("application", "ogg")

        /**
         * [Ogg](https://en.wikipedia.org/wiki/Ogg) video pattern `video/ogg`,
         * as defined by [RFC 5334](https://tools.ietf.org/html/rfc5334#section-2).
         */
        @JvmField
        public val OGG_VIDEO: MediaType = MediaType("video", "ogg")

        /**
         * [OpenSearch description](https://en.wikipedia.org/wiki/OpenSearch)
         * pattern `application/opensearchdescription+xml`, as proposed by the
         * [media type draft](https://tools.ietf.org/id/draft-ellermann-opensearch-03.html).
         */
        @JvmField
        public val OPENSEARCH_DESCRIPTION: MediaType = MediaType("application", "opensearchdescription+xml")

        /**
         * [Opus Audio](https://en.wikipedia.org/wiki/Opus_(audio_format)) pattern `audio/opus`,
         * as defined by [RFC 6716](https://tools.ietf.org/html/rfc6716).
         */
        @JvmField
        public val OPUS: MediaType = MediaType("audio", "opus")

        /**
         * [Portable Document Format](https://en.wikipedia.org/wiki/PDF) pattern `application/pdf`,
         * as defined by [RFC 8118](https://tools.ietf.org/html/rfc8118).
         */
        @JvmField
        public val PDF: MediaType = MediaType("application", "pdf")

        /**
         * Plain text pattern `text/plain`, as defined by [RFC 2046](https://tools.ietf.org/html/rfc2046#section-4.1).
         */
        @JvmField
        public val PLAIN_TEXT: MediaType = MediaType("text", "plain")

        /**
         * [Portable Network Graphics](https://en.wikipedia.org/wiki/Portable_Network_Graphics)
         * pattern `image/png`, as defined by [RFC 2083](https://tools.ietf.org/html/rfc2083).
         */
        @JvmField
        public val PNG: MediaType = MediaType("image", "png")

        /**
         * [PostScript](https://en.wikipedia.org/wiki/PostScript) pattern `application/postscript`,
         * as defined by [RFC 2046](https://tools.ietf.org/html/rfc2046#section-4.5.2).
         */
        @JvmField
        public val POSTSCRIPT: MediaType = MediaType("application", "postscript")

        /** [QuickTime](https://en.wikipedia.org/wiki/QuickTime) pattern `video/quicktime`. */
        @JvmField
        public val QUICKTIME: MediaType = MediaType("video", "quicktime")

        /** [RSS](https://en.wikipedia.org/wiki/RSS) feed pattern `application/rss+xml`. */
        @JvmField
        public val RSS: MediaType = MediaType("application", "rss+xml")

        /** [SubRip Text](https://en.wikipedia.org/wiki/SubRip) pattern `application/srt`. */
        @JvmField
        public val SRT: MediaType = MediaType("application", "srt")

        /** [Scalable Vector Graphics](https://www.w3.org/Graphics/SVG/) pattern `image/svg+xml`. */
        @JvmField
        public val SVG: MediaType = MediaType("image", "svg+xml")

        /** [tar file format](https://en.wikipedia.org/wiki/Tar_(computing)) pattern `application/x-tarl`. */
        @JvmField
        public val TAR: MediaType = MediaType("application", "x-tar")

        /**
         * [Tag Image File Format](https://en.wikipedia.org/wiki/TIFF) pattern `image/tiff`,
         * as defined in [RFC 3302](https://tools.ietf.org/html/rfc3302).
         */
        @JvmField
        public val TIFF: MediaType = MediaType("image", "tiff")

        /**
         * [Virtual Contact File](https://en.wikipedia.org/wiki/VCard) pattern `text/vcard`,
         * as defined by [RFC 6350](https://tools.ietf.org/html/rfc6350#section-3).
         */
        @JvmField
        public val VCARD: MediaType = MediaType("text", "vcard")

        /**
         * [Vorbis](https://en.wikipedia.org/wiki/Vorbis) audio pattern `audio/vorbis`,
         * as defined by [RFC 5215](http://tools.ietf.org/html/rfc5215).
         */
        @JvmField
        public val VORBIS: MediaType = MediaType("audio", "vorbis")

        /** [Web Video Text Tracks](https://www.w3.org/TR/webvtt1/) (WebVTT) pattern `text/vtt`. */
        @JvmField
        public val VTT: MediaType = MediaType("text", "vtt")

        /** [WebM](https://en.wikipedia.org/wiki/WebM) audio pattern `audio/webm`. */
        @JvmField
        public val WEBM_AUDIO: MediaType = MediaType("audio", "webm")

        /** [WebM](https://en.wikipedia.org/wiki/WebM) video pattern `video/webm`. */
        @JvmField
        public val WEBM_VIDEO: MediaType = MediaType("video", "webm")

        /** [WebP](https://en.wikipedia.org/wiki/WebP) image pattern `image/webp`. */
        @JvmField
        public val WEBP: MediaType = MediaType("image", "webp")

        /**
         * XML document pattern `application/xml`, intended for "unreadable by casual users"
         * documents, as defined by [RFC 3023](https://www.ietf.org/rfc/rfc3023.html).
         * Use [XML_TEXT] for documents that may be read by users.
         */
        @JvmField
        public val XML_APPLICATION: MediaType = MediaType("application", "xml")

        /**
         * XML document pattern `text/xml`, intended for "readable by casual users"
         * documents, as defined by [RFC 3023](https://www.ietf.org/rfc/rfc3023.html).
         * Use [XML_APPLICATION] for documents that are intended for applications.
         */
        @JvmField
        public val XML_TEXT: MediaType = MediaType("text", "xml")

        /**
         * [ZIP](https://en.wikipedia.org/wiki/ZIP_(file_format)) pattern `application/zip`,
         * as defined by [IANA](https://www.iana.org/assignments/media-types/application/zip).
         */
        @JvmField
        public val ZIP: MediaType = MediaType("application", "zip")
    }
}
