package dev.stalla.model

import assertk.all
import assertk.assertAll
import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.index
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isFailure
import assertk.assertions.isInstanceOf
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
    fun `should fail to parse an invalid input`() {
        assertThat {
            MediaType.of("text/plain/something")
        }.isFailure().isInstanceOf(BadMediaTypeFormatException::class)
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
