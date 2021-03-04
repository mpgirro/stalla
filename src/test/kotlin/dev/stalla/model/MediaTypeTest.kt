package dev.stalla.model

import assertk.all
import assertk.assertAll
import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.index
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
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
    fun `should parse plain charsets parameter correctly`() {
        assertThat(MediaType.of("text/plain ; charset = utf-8")).isNotNull().all {
            prop(MediaType::type).isEqualTo("text")
            prop(MediaType::subtype).isEqualTo("plain")
            prop(MediaType::parameters).hasSize(1)
            prop(MediaType::parameters).index(0).isEqualTo(MediaType.Parameter("charset", "utf-8"))
            prop("toString") { MediaType::toString.call(it) }.isEqualTo("text/plain; charset=utf-8")
            isEqualTo(MediaType.PLAIN_TEXT.withParameter("charset", "utf-8"))
        }
    }

    @Test
    fun `should parse plain arbitrary parameters correctly`() {
        assertThat(MediaType.of("text/plain ; charset = utf-8;foo=bar")).isNotNull()
            .prop("toString") { MediaType::toString.call(it) }.isEqualTo("text/plain; charset=utf-8; foo=bar")
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
    fun `should fail to parse Media Type patterns when  the type part is missing`() {
        assertThat(MediaType.of(" / plain")).isNull()
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
    fun `should parse Media Type patterns and ignore parameter when the attribute is missing`() {
        assertThat(MediaType.of("audio/*;=value")).isNotNull()
            .isEqualTo(MediaType.of("audio/*"))
    }

    @Test
    fun `should parse Media Type patterns and ignore parameter when the parameter attribute contains separator symbols`() {
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
        assertThat(MediaType.of("text/plain; boundary=\"gc0pJq0M:08jU534c0p\"")).isNotNull().all {
            prop(MediaType::type).isEqualTo("text")
            prop(MediaType::subtype).isEqualTo("plain")
            prop(MediaType::parameters).hasSize(1)
            prop(MediaType::parameters).index(0).isEqualTo(MediaType.Parameter("boundary", "gc0pJq0M:08jU534c0p"))
            prop("toString") { MediaType::toString.call(it) }.isEqualTo("text/plain; boundary=\"gc0pJq0M:08jU534c0p\"")
            isEqualTo(MediaType.PLAIN_TEXT.withParameter("boundary", "gc0pJq0M:08jU534c0p"))
        }
    }

    @Test
    fun `should fail to parse Media Type patterns with a subtype placeholder`() {
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
