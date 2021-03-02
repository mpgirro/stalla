package dev.stalla.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MediaTypeTest {

    @Test
    fun contentTypeTextPlain() {
        val ct = MediaType.PLAIN_TEXT
        assertEquals("text", ct.type)
        assertEquals("plain", ct.subtype)
        assertEquals(0, ct.parameters.size)
    }

    @Test
    fun textPlain() {
        val ct = MediaType.parse("text/plain")
        assertEquals("text", ct.type)
        assertEquals("plain", ct.subtype)
        assertEquals(0, ct.parameters.size)
    }

    @Test
    fun testBlankIsAny() {
        assertEquals(MediaType.ANY, MediaType.parse(""))
    }

    @Test
    fun textPlainCharsetInQuotes() {
        val ct1 = MediaType.parse("text/plain; charset=us-ascii")
        val ct2 = MediaType.parse("text/plain; charset=\"us-ascii\"")
        assertEquals(ct1, ct2)
    }

    @Test
    fun textPlainCharsetCaseInsensitive() {
        val ct1 = MediaType.parse("Text/plain; charset=UTF-8")
        val ct2 = MediaType.parse("text/Plain; CHARSET=utf-8")
        assertEquals(ct1, ct2)
    }

    @Test
    fun textPlainCharsetIsUtf8() {
        val ct = MediaType.parse("text/plain ; charset = utf-8")
        assertEquals("text", ct.type)
        assertEquals("plain", ct.subtype)
        assertEquals(1, ct.parameters.size)
        assertEquals(MediaType.Parameter("charset", "utf-8"), ct.parameters[0])
        val toString = ct.toString()
        assertEquals("text/plain; charset=utf-8", toString)
        assertEquals(MediaType.PLAIN_TEXT.withParameter("charset", "utf-8"), ct)
    }

    @Test
    fun textPlainCharsetIsUtf8WithParameterFooBar() {
        val ct = MediaType.parse("text/plain ; charset = utf-8;foo=bar")

        val toString = ct.toString()
        assertEquals("text/plain; charset=utf-8; foo=bar", toString)
    }

    @Test
    fun textPlainInvalid() {
        /*
        assertFailsWith(BadContentTypeFormatException::class) {
            ContentType.parse("text/plain/something")
        }
         */
        assertThrows<BadMediaTypeFormatException> {
            MediaType.parse("text/plain/something")
        }
    }

    @Test
    fun contentTypeWithEmptyParametersBlock() {
        assertEquals(MediaType.PLAIN_TEXT, MediaType.parse("text/plain; "))
        assertEquals(MediaType.PLAIN_TEXT, MediaType.parse("text/plain;"))
    }

    @Test
    fun contentTypeRenderWorks() {
        // rendering tests are in [HeadersTest] so it is just a smoke test
        assertEquals("text/plain; p1=v1", MediaType.PLAIN_TEXT.withParameter("p1", "v1").toString())
    }

    @Test
    fun testContentTypeInvalid() {
        val result = MediaType.parse("image/png; charset=utf-8\" but not really")
        assertEquals(MediaType.PNG.withParameter("charset", "utf-8\" but not really"), result)
    }

    @Test
    fun testContentTypeSingleQuoteAtStart() {
        val result = MediaType.parse("image/png; charset=\"utf-8 but not really")
        assertEquals(MediaType.PNG.withParameter("charset", "\"utf-8 but not really"), result)
    }

    @Test
    fun testContentTypeQuotedAtStartAndMiddle() {
        val result = MediaType.parse("image/png; charset=\"utf-8\" but not really")
        assertEquals(MediaType.PNG.withParameter("charset", "\"utf-8\" but not really"), result)
    }

    @Test
    fun testWithoutParameters() {
        assertEquals(MediaType.PLAIN_TEXT, MediaType.PLAIN_TEXT.withoutParameters())
        assertEquals(
            MediaType.PLAIN_TEXT,
            MediaType.PLAIN_TEXT.withParameter("a", "1").withoutParameters()
        )

        assertEquals(
            "text/plain",
            MediaType.PLAIN_TEXT.withParameter("a", "1").withoutParameters().toString()
        )

        assertEquals(
            "text/html",
            MediaType.parse("text/html;charset=utf-8").withoutParameters().toString()
        )
    }
}
