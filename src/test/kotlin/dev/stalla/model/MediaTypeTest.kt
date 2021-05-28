package dev.stalla.model

import assertk.all
import assertk.assertAll
import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.hasSize
import assertk.assertions.index
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import dev.stalla.arguments
import dev.stalla.doesNotMatchSymmetrically
import dev.stalla.equalToString
import dev.stalla.hasParameterWithValue
import dev.stalla.matchPattern
import dev.stalla.matchesSymmetrically
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import kotlin.reflect.KVisibility
import kotlin.reflect.full.declaredMemberProperties

internal class MediaTypeTest {

    private class MediaTypeNameProvider : ArgumentsProvider by arguments(*allMediaTypeNames.toTypedArray())

    private class MediaTypeFactoryPropertyProvider : ArgumentsProvider by arguments(
        *MediaType.Factory::class.declaredMemberProperties
            .filter { member -> member.visibility == KVisibility.PUBLIC }
            .mapNotNull { member -> member.getter.call(this) }
            .filterIsInstance<MediaType>()
            .toTypedArray()
    )

    private val factoryPropertyMap: Map<String, MediaType> by lazy {
        val values: List<MediaType> = MediaType.Factory::class.declaredMemberProperties
            .filter { member -> member.visibility == KVisibility.PUBLIC }
            .mapNotNull { member -> member.getter.call(this) }
            .filterIsInstance<MediaType>()

        values.associateBy({ it.toString() }, { it })
    }

    @Test
    fun `should define the plain text type correctly`() {
        assertThat(MediaType.PLAIN_TEXT).isNotNull().all {
            prop(MediaType::type).isEqualTo("text")
            prop(MediaType::subtype).isEqualTo("plain")
            prop(MediaType::parameters).isEmpty()
        }
    }

    @Test
    fun `should transform the JSON type correctly to string`() {
        assertThat(MediaType.JSON.toString()).isEqualTo("application/json")
    }

    @Test
    fun `should instantiate from the factory method correctly`() {
        assertThat(MediaType.of("text/plain")).isNotNull().all {
            prop(MediaType::type).isEqualTo("text")
            prop(MediaType::subtype).isEqualTo("plain")
            prop(MediaType::parameters).isEmpty()
        }
    }

    @Test
    fun `should recognize blank as Any`() {
        assertThat(MediaType.of("")).isNotNull().isEqualTo(MediaType.ANY)
    }

    @Test
    fun `should not parse null to an instance in the factory nethod`() {
        assertThat(MediaType.of(null)).isNull()
    }

    @Test
    fun `should handle quoted plain charsets correctly`() {
        val mediaType1 = MediaType.of("text/plain; charset=us-ascii")
        val mediaType2 = MediaType.of("text/plain; charset=\"us-ascii\"")
        assertAll {
            assertThat(mediaType1).isNotNull()
            assertThat(mediaType2).isNotNull()
            assertThat(mediaType1).isEqualTo(mediaType2)
        }
    }

    @Test
    fun `should handle plain charsets case insensitive`() {
        val mediaType1 = MediaType.of("text/plain; charset=UTF-8")
        val mediaType2 = MediaType.of("text/plain; CHARSET=utf-8")
        assertAll {
            assertThat(mediaType1).isNotNull()
            assertThat(mediaType2).isNotNull()
            assertThat(mediaType1).isEqualTo(mediaType2)
        }
    }

    @Test
    fun `should expose its essence correctly`() {
        val mediaType = MediaType.PLAIN_TEXT.withParameter("charset", "charset=UTF-8")
        assertThat(mediaType.essence).isEqualTo("text/plain")
    }

    @Test
    fun `should only accept a parameter key once and ignore all subsequent`() {
        assertThat(MediaType.of("text/plain; charset=utf-8; charset=us-ascii")).isNotNull().all {
            prop(MediaType::type).isEqualTo("text")
            prop(MediaType::subtype).isEqualTo("plain")
            prop(MediaType::parameters).hasSize(1)
            hasParameterWithValue("charset", "utf-8")
        }
    }

    @Test
    fun `should parse plain charsets parameter correctly`() {
        val expected = MediaType.PLAIN_TEXT.withParameter("charset", "utf-8")
        assertThat(MediaType.of("text/plain ; charset = utf-8")).isNotNull().all {
            prop(MediaType::type).isEqualTo("text")
            prop(MediaType::subtype).isEqualTo("plain")
            prop(MediaType::parameters).hasSize(1)
            prop(MediaType::parameters).index(0).isEqualTo(MediaType.Parameter("charset", "utf-8"))
            equalToString(expected)
            matchPattern(expected)
            isEqualTo(expected)
        }
    }

    @Test
    fun `should parse plain arbitrary parameters correctly`() {
        val expected = MediaType.PLAIN_TEXT
            .withParameter("charset", "utf-8")
            .withParameter("foo", "bar")
        assertThat(MediaType.of("text/plain ; charset = utf-8;foo=bar")).isNotNull().all {
            equalToString(expected)
            matchPattern(expected)
            isEqualTo(expected)
        }
    }

    @Test
    fun `should fail to parse patterns with an invalid essence`() {
        assertThat(MediaType.of("text/plain/something")).isNull()
    }

    @Test
    fun `should fail to parse patterns when there is only the type`() {
        assertThat(MediaType.of("text")).isNull()
    }

    @Test
    fun `should fail to parse patterns when the delimiter between type and subtype is missing`() {
        assertThat(MediaType.of("text plain")).isNull()
    }

    @Test
    fun `should fail to parse patterns when the type is missing`() {
        assertThat(MediaType.of(" / plain")).isNull()
    }

    @Test
    fun `should fail to parse patterns when the type is a wildcard but the subtype is concrete`() {
        assertThat(MediaType.of("*/json")).isNull()
    }

    @Test
    fun `should fail to parse patterns when the subtype is missing`() {
        assertThat(MediaType.of("text / ")).isNull()
    }

    @Test
    fun `should fail to parse patterns when there is an RFC 2616 separator symbol in the type`() {
        assertThat(MediaType.of("audio(/basic")).isNull()
    }

    @Test
    fun `should fail to parse patterns when there is an RFC 2616 separator symbol in the subtype`() {
        assertThat(MediaType.of("audio/basic)")).isNull()
    }

    @Test
    fun `should fail to parse patterns when there is only a parameter`() {
        assertThat(MediaType.of("     ;a=b")).isNull()
    }

    @Test
    fun `should ignore parameters during parsing when the key is missing`() {
        assertThat(MediaType.of("audio/*;=value")).isNotNull()
            .isEqualTo(MediaType.ANY_AUDIO)
    }

    @Test
    fun `should not attach a parameter with an empty parameter key`() {
        assertThat(MediaType.ANY_AUDIO.withParameter("", "value")).isNotNull()
            .isEqualTo(MediaType.ANY_AUDIO)
    }

    @Test
    fun `should ignore parameters during parsing with an invalid key`() {
        assertThat(MediaType.of("audio/*;attr<=value")).isNotNull()
            .isEqualTo(MediaType.ANY_AUDIO)
    }

    @Test
    fun `should not attach a parameter with an invalid parameter key`() {
        assertThat(MediaType.ANY_AUDIO.withParameter("attr<", "value")).isNotNull()
            .isEqualTo(MediaType.ANY_AUDIO)
    }

    @Test
    fun `should parse patterns when the parameter value is single quoted`() {
        assertThat(MediaType.of("audio/*;attr='v>alue'")).isNotNull().all {
            prop(MediaType::type).isEqualTo("audio")
            prop(MediaType::subtype).isEqualTo("*")
            prop(MediaType::parameters).hasSize(1)
            hasParameterWithValue("attr", "v>alue")
        }
    }

    @Test
    fun `should ignore parameters during parsing where the value is empty`() {
        assertThat(MediaType.of("audio/*;attr=")).isNotNull()
            .isEqualTo(MediaType.of("audio/*"))
    }

    @Test
    fun `should match a predefined instance correctly`() {
        assertThat(MediaType.AAC_AUDIO).matchesSymmetrically(MediaType.of("audio/aac"))
    }

    @Test
    fun `should match a predefined instance from a string pattern`() {
        assertThat(MediaType.AAC_AUDIO.matches("audio/aac")).isTrue()
    }

    @Test
    fun `should match a predefined instance with a wildcard subtype`() {
        assertThat(MediaType.AAC_AUDIO.matches("audio/*")).isTrue()
    }

    @Test
    fun `should not match a wildcard subtype with a concrete subtype`() {
        assertThat(MediaType.ANY_AUDIO).matchesSymmetrically(MediaType.OGG_AUDIO)
    }

    @Test
    fun `should match a concrete subtype with a wildcard subtype`() {
        assertThat(MediaType.OGG_AUDIO).matchesSymmetrically(MediaType.ANY_AUDIO)
    }

    @Test
    fun `should match ANY with a wildcard subtype`() {
        assertThat(MediaType.ANY).matchesSymmetrically(MediaType.ANY_AUDIO)
    }

    @Test
    fun `should not match two instances with different types`() {
        assertThat(MediaType.MARKDOWN).doesNotMatchSymmetrically(MediaType.OPUS)
    }

    @Test
    fun `should not match two instances with same type but different subtype`() {
        assertThat(MediaType.MPEG_AUDIO).doesNotMatchSymmetrically(MediaType.OPUS)
    }

    @Test
    fun `should match if parameters are equal`() {
        val mediaType1 = MediaType.PLAIN_TEXT.withParameter("p1", "v1")
        val mediaType2 = MediaType.PLAIN_TEXT.withParameter("p1", "v1")
        assertThat(mediaType1).matchesSymmetrically(mediaType2)
    }

    @Test
    fun `should not match if parameter values are not equal`() {
        val mediaType1 = MediaType.PLAIN_TEXT.withParameter("p1", "v1")
        val mediaType2 = MediaType.PLAIN_TEXT.withParameter("p1", "v2")
        assertThat(mediaType1).doesNotMatchSymmetrically(mediaType2)
    }

    @Test
    fun `should not match if parameter keys are not equal`() {
        val mediaType1 = MediaType.PLAIN_TEXT.withParameter("p1", "v1")
        val mediaType2 = MediaType.PLAIN_TEXT.withParameter("p2", "v1")
        assertThat(mediaType1).doesNotMatchSymmetrically(mediaType2)
    }

    @Test
    fun `should match on parameter with wildcard keys`() {
        val mediaType1 = MediaType.PLAIN_TEXT.withParameter("p1", "v1")
        val mediaType2 = MediaType.PLAIN_TEXT.withParameter("*", "v1")
        assertThat(mediaType1).matchesSymmetrically(mediaType2)
    }

    @Test
    fun `should match on parameter with wildcard values`() {
        val mediaType1 = MediaType.PLAIN_TEXT.withParameter("p1", "v1")
        val mediaType2 = MediaType.PLAIN_TEXT.withParameter("p1", "*")
        assertThat(mediaType1).matchesSymmetrically(mediaType2)
    }

    @Test
    fun `should parse patterns even when illegal characters in the parameter value are not quoted (auto-quote)`() {
        // Note: This pattern is a sample from RFC 2046 (https://tools.ietf.org/html/rfc2046#section-5.1.1)
        assertThat(MediaType.of("multipart/mixed; boundary=gc0pJq0M:08jU534c0p")).isNotNull().all {
            prop(MediaType::type).isEqualTo("multipart")
            prop(MediaType::subtype).isEqualTo("mixed")
            prop(MediaType::parameters).hasSize(1)
            hasParameterWithValue("boundary", "gc0pJq0M:08jU534c0p")
        }
    }

    @Test
    fun `should parse patterns when illegal characters are quoted`() {
        // Note: This pattern is a sample from RFC 2046 (https://tools.ietf.org/html/rfc2046#section-5.1.1)
        val expected = MediaType.PLAIN_TEXT.withParameter("boundary", "gc0pJq0M:08jU534c0p")
        assertThat(MediaType.of("text/plain; boundary=\"gc0pJq0M:08jU534c0p\"")).isNotNull().all {
            prop(MediaType::type).isEqualTo("text")
            prop(MediaType::subtype).isEqualTo("plain")
            prop(MediaType::parameters).hasSize(1)
            prop(MediaType::parameters).index(0).isEqualTo(MediaType.Parameter("boundary", "gc0pJq0M:08jU534c0p"))
            equalToString("text/plain; boundary=\"gc0pJq0M:08jU534c0p\"")
            matchPattern(expected)
            isEqualTo(expected)
        }
    }

    @Test
    fun `should parse patterns with a wildcard subtype`() {
        assertThat(MediaType.of("text / *")).isNotNull().all {
            prop(MediaType::type).isEqualTo("text")
            prop(MediaType::subtype).isEqualTo("*")
            prop(MediaType::parameters).isEmpty()
        }
    }

    @Test
    fun `should parse empty parameter blocks correctly`() {
        assertThat(MediaType.PLAIN_TEXT).all {
            isEqualTo(MediaType.of("text/plain; "))
            isEqualTo(MediaType.of("text/plain;"))
        }
    }

    @Test
    fun `should not parse + sign as a delimeter`() {
        assertThat(MediaType.of("application/json+chapters")).isNotNull().all {
            isEqualTo(MediaType.JSON_CHAPTERS)
            prop(MediaType::type).isEqualTo("application")
            prop(MediaType::subtype).isEqualTo("json+chapters")
            prop(MediaType::parameters).isEmpty()
        }
    }

    @Test
    fun `should render parameters correctly`() {
        assertThat(MediaType.PLAIN_TEXT.withParameter("p1", "v1")).isNotNull()
            .equalToString("text/plain; p1=v1")
    }

    @Test
    fun `should handle invalid quotes in parameter values correctly`() {
        assertThat(MediaType.of("image/png; charset=utf-8\" but not really")).isNotNull()
            .isEqualTo(MediaType.PNG.withParameter("charset", "utf-8\" but not really"))
    }

    @Test
    fun `should handle single quotes at start in parameter values correctly`() {
        assertThat(MediaType.of("image/png; charset=\"utf-8 but not really")).isNotNull()
            .isEqualTo(MediaType.PNG.withParameter("charset", "\"utf-8 but not really"))
    }

    @Test
    fun `should handle quotes at start and middle in parameter values correctly`() {
        // Note: This pattern is a sample from the MIME sniffing standard (https://mimesniff.spec.whatwg.org)
        val expected = MediaType.HTML.withParameter("charset", "\"shift_jis\"")
        assertThat(MediaType.of("text/html;charset=\"shift_jis\"iso-2022-jp")).isNotNull().all {
            prop(MediaType::type).isEqualTo("text")
            prop(MediaType::subtype).isEqualTo("html")
            prop(MediaType::parameters).hasSize(1)
            prop(MediaType::parameters).index(0).isEqualTo(MediaType.Parameter("charset", "\"shift_jis\""))
            equalToString("text/html; charset=\"shift_jis\"")
            matchPattern(expected)
            isEqualTo(expected)
        }
    }

    @Test
    fun `should handle quotes at start and middle in parameter values with multiple parameters correctly`() {
        val expected = MediaType.PNG
            .withParameter("charset", "\"utf-8\"")
            .withParameter("p1", "v1")
        assertThat(MediaType.of("image/png; charset=\"utf-8\" but not really; p1=v1")).isNotNull()
            .isEqualTo(expected)
    }

    @Test
    fun `should remove parameters correctly`() {
        assertThat(MediaType.PLAIN_TEXT).all {
            isEqualTo(MediaType.PLAIN_TEXT.withoutParameters())
            isEqualTo(MediaType.PLAIN_TEXT.withParameter("a", "1").withoutParameters())
        }

        assertThat("text/plain").isEqualTo(MediaType.PLAIN_TEXT.withParameter("a", "1").withoutParameters().toString())

        assertThat("text/html").isEqualTo(MediaType.of("text/html;charset=utf-8")?.withoutParameters().toString())
    }

    @ParameterizedTest
    @ArgumentsSource(MediaTypeNameProvider::class)
    fun `should retrieve all predefined types from the factory method`(typeName: String) {
        assertThat(MediaType.of(typeName)).isNotNull().equalToString(typeName)
    }

    @ParameterizedTest
    @ArgumentsSource(MediaTypeNameProvider::class)
    fun `should have a companion object property for every predefined type`(typeName: String) {
        assertThat(factoryPropertyMap[typeName]).isNotNull()
    }

    @ParameterizedTest
    @ArgumentsSource(MediaTypeFactoryPropertyProvider::class)
    fun `should expose only instance properties that are defined`(type: MediaType) {
        assertThat(allMediaTypeNames).contains(type.toString())
    }

    @ParameterizedTest
    @ArgumentsSource(MediaTypeFactoryPropertyProvider::class)
    fun `should retrieve the correct instance for every predefined type from the factory method`(type: MediaType) {
        assertThat(MediaType.of(type.toString())).isEqualTo(type)
    }

    companion object {

        @JvmStatic
        private val allMediaTypeNames = listOf(
            "*/*",
            "audio/*",
            "audio/aac",
            "audio/basic",
            "audio/flac",
            "audio/L16",
            "audio/L24",
            "audio/mp4",
            "audio/mpeg",
            "audio/ogg",
            "audio/opus",
            "audio/vorbis",
            "audio/webm",
            "application/atom+xml",
            "application/epub+zip",
            "application/gzip",
            "application/javascript",
            "application/json",
            "application/json+chapters",
            "application/manifest+json",
            "application/ogg",
            "application/opensearchdescription+xml",
            "application/pdf",
            "application/postscript",
            "application/rdf+xml",
            "application/rss+xml",
            "application/srt",
            "application/xml",
            "application/x-tar",
            "application/zip",
            "image/*",
            "image/bmp",
            "image/gif",
            "image/heif",
            "image/jpeg",
            "image/png",
            "image/svg+xml",
            "image/tiff",
            "image/webp",
            "text/*",
            "text/css",
            "text/csv",
            "text/html",
            "text/markdown",
            "text/plain",
            "text/vcard",
            "text/vtt",
            "text/xml",
            "video/*",
            "video/mp4",
            "video/mpeg",
            "video/ogg",
            "video/quicktime",
            "video/webm"
        )
    }
}
