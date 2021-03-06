package dev.stalla.model

import assertk.all
import assertk.assertAll
import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.index
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import dev.stalla.equalToString
import dev.stalla.matchPattern
import org.junit.jupiter.api.Test

class MediaTypeTest {

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
    fun `should instantiate an instance from the factory method correctly`() {
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
    fun `should not parse null to an instance`() {
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
            prop("parameter") { MediaType::parameter.call(it, "charset") }.isNotNull().isEqualTo("utf-8")
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
    fun `should fail to parse Media Type patterns for an invalid input`() {
        assertThat(MediaType.of("text/plain/something")).isNull()
    }

    @Test
    fun `should fail to parse Media Type patterns when there is only the type part`() {
        assertThat(MediaType.of("text")).isNull()
    }

    @Test
    fun `should fail to parse Media Type patterns when the delimiter is missing`() {
        assertThat(MediaType.of("text plain")).isNull()
    }

    @Test
    fun `should fail to parse Media Type patterns when the type part is missing`() {
        assertThat(MediaType.of(" / plain")).isNull()
    }

    @Test
    fun `should fail to parse Media Type patterns when the type is a wildcard but the subtype is concrete`() {
        assertThat(MediaType.of("*/json")).isNull()
    }

    @Test
    fun `should fail to parse Media Type patterns when the subtype part is missing`() {
        assertThat(MediaType.of("text / ")).isNull()
    }

    @Test
    fun `should fail to parse Media Type patterns when there is a RFC 2616 separator symbol in the type part`() {
        assertThat(MediaType.of("audio(/basic")).isNull()
    }

    @Test
    fun `should fail to parse Media Type patterns when there is a RFC 2616 separator symbol in the subtype part`() {
        assertThat(MediaType.of("audio/basic)")).isNull()
    }

    @Test
    fun `should fail to parse Media Type patterns when there is only a parameter`() {
        assertThat(MediaType.of("     ;a=b")).isNull()
    }

    @Test
    fun `should parse Media Type patterns and ignore parameter when the key is missing`() {
        assertThat(MediaType.of("audio/*;=value")).isNotNull()
            .isEqualTo(MediaType.of("audio/*"))
    }

    @Test
    fun `should parse Media Type patterns correctly`() {
        assertThat(MediaType.of("audio/*;attr<=value")).isNotNull()
            .isEqualTo(MediaType.of("audio/*"))
    }

    @Test
    fun `should parse Media Type patterns when the parameter value is single quoted`() {
        assertThat(MediaType.of("audio/*;attr='v>alue'")).isNotNull().all {
            prop(MediaType::type).isEqualTo("audio")
            prop(MediaType::subtype).isEqualTo("*")
            prop(MediaType::parameters).hasSize(1)
            prop("parameter") { MediaType::parameter.call(it, "attr") }.isNotNull().isEqualTo("v>alue")
        }
    }

    @Test
    fun `should parse Media Type patterns and ignore parameter where the value is empty`() {
        assertThat(MediaType.of("audio/*;attr=")).isNotNull()
            .isEqualTo(MediaType.of("audio/*"))
    }

    @Test
    fun `should match a predefined Media Type`() {
        assertThat(MediaType.AAC_AUDIO.match(MediaType.of("audio/aac"))).isTrue()
    }

    @Test
    fun `should match a predefined Media Type from a string pattern`() {
        assertThat(MediaType.AAC_AUDIO.match("audio/aac")).isTrue()
    }

    @Test
    fun `should match a predefined Media Type with a wildcard subtype pattern`() {
        assertThat(MediaType.AAC_AUDIO.match("audio/*")).isTrue()
    }

    @Test
    fun `should not match a wildcard Media Type with a concrete type`() {
        assertThat(MediaType.ANY_AUDIO.match(MediaType.OGG_AUDIO)).isFalse()
    }

    @Test
    fun `should match a concrete Media Type with a wildcard type`() {
        assertThat(MediaType.OGG_AUDIO.match(MediaType.ANY_AUDIO)).isTrue()
    }

    @Test
    fun `should match a Media Type taking parameters into account`() {
        assertThat(MediaType.PLAIN_TEXT.withParameter("p1", "v1")).isNotNull()
            .prop("match") { MediaType::parameter.call(it, "p1") }.isNotNull().isEqualTo("v1")
    }

    @Test
    fun `should parse Media Type patterns even when illegal characters in parameter value are not quoted`() {
        // Note: This pattern is a sample from RFC 2046 (https://tools.ietf.org/html/rfc2046#section-5.1.1)
        assertThat(MediaType.of("multipart/mixed; boundary=gc0pJq0M:08jU534c0p")).isNotNull().all {
            prop(MediaType::type).isEqualTo("multipart")
            prop(MediaType::subtype).isEqualTo("mixed")
            prop(MediaType::parameters).hasSize(1)
            prop("parameter") { MediaType::parameter.call(it, "boundary") }.isNotNull().isEqualTo("gc0pJq0M:08jU534c0p")
        }
    }

    @Test
    fun `should parse Media Type patterns when illegal characters are quoted`() {
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
    fun `should parse Media Type patterns with a wildcard subtype`() {
        assertThat(MediaType.of("text / *")).isNotNull().all {
            prop(MediaType::type).isEqualTo("text")
            prop(MediaType::subtype).isEqualTo("*")
            prop(MediaType::parameters).isEmpty()
        }
    }

    @Test
    fun `should parse empty parameter block correctly`() {
        assertThat(MediaType.PLAIN_TEXT).all {
            isEqualTo(MediaType.of("text/plain; "))
            isEqualTo(MediaType.of("text/plain;"))
        }
    }

    @Test
    fun `should render parameters correctly`() {
        assertThat(MediaType.PLAIN_TEXT.withParameter("p1", "v1")).isNotNull()
            .prop("toString") { MediaType::toString.call(it) }.isEqualTo("text/plain; p1=v1")
    }

    @Test
    fun `should handle invalid quotes in parameters correctly`() {
        assertThat(MediaType.of("image/png; charset=utf-8\" but not really")).isNotNull()
            .isEqualTo(MediaType.PNG.withParameter("charset", "utf-8\" but not really"))
    }

    @Test
    fun `should handle single quotes at start in parameters correctly`() {
        assertThat(MediaType.of("image/png; charset=\"utf-8 but not really")).isNotNull()
            .isEqualTo(MediaType.PNG.withParameter("charset", "\"utf-8 but not really"))
    }

    @Test
    fun `should handle single quotes at start and middle in parameters correctly`() {
        assertThat(MediaType.of("image/png; charset=\"utf-8\" but not really")).isNotNull()
            .isEqualTo(MediaType.PNG.withParameter("charset", "\"utf-8\" but not really"))
    }

    @Test
    fun `should transform to a without parameters form correctly`() {
        assertThat(MediaType.PLAIN_TEXT).all {
            isEqualTo(MediaType.PLAIN_TEXT.withoutParameters())
            isEqualTo(MediaType.PLAIN_TEXT.withParameter("a", "1").withoutParameters())
        }

        assertThat("text/plain").isEqualTo(MediaType.PLAIN_TEXT.withParameter("a", "1").withoutParameters().toString())

        assertThat("text/html").isEqualTo(MediaType.of("text/html;charset=utf-8")?.withoutParameters().toString())
    }
}
