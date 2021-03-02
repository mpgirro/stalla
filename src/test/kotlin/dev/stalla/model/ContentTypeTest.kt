package dev.stalla.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ContentTypeTest {

    @Test
    fun contentTypeTextPlain() {
        val ct = ContentType.TEXT_PLAIN
        assertEquals("text", ct.contentType)
        assertEquals("plain", ct.contentSubtype)
        assertEquals(0, ct.parameters.size)
    }

    @Test
    fun textPlain() {
        val ct = ContentType.parse("text/plain")
        assertEquals("text", ct.contentType)
        assertEquals("plain", ct.contentSubtype)
        assertEquals(0, ct.parameters.size)
    }

    @Test
    fun testBlankIsAny() {
        assertEquals(ContentType.ANY, ContentType.parse(""))
    }

    @Test
    fun textPlainCharsetInQuotes() {
        val ct1 = ContentType.parse("text/plain; charset=us-ascii")
        val ct2 = ContentType.parse("text/plain; charset=\"us-ascii\"")
        assertEquals(ct1, ct2)
    }

    @Test
    fun textPlainCharsetCaseInsensitive() {
        val ct1 = ContentType.parse("Text/plain; charset=UTF-8")
        val ct2 = ContentType.parse("text/Plain; CHARSET=utf-8")
        assertEquals(ct1, ct2)
    }

    @Test
    fun textPlainCharsetIsUtf8() {
        val ct = ContentType.parse("text/plain ; charset = utf-8")
        assertEquals("text", ct.contentType)
        assertEquals("plain", ct.contentSubtype)
        assertEquals(1, ct.parameters.size)
        assertEquals(HeaderValueParam("charset", "utf-8"), ct.parameters[0])
        val toString = ct.toString()
        assertEquals("text/plain; charset=utf-8", toString)
        assertEquals(ContentType.TEXT_PLAIN.withParameter("charset", "utf-8"), ct)
    }

    @Test
    fun textPlainCharsetIsUtf8WithParameterFooBar() {
        val ct = ContentType.parse("text/plain ; charset = utf-8;foo=bar")

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
        assertThrows<BadContentTypeFormatException> {
            ContentType.parse("text/plain/something")
        }
    }

    @Test
    fun contentTypeWithEmptyParametersBlock() {
        assertEquals(ContentType.TEXT_PLAIN, ContentType.parse("text/plain; "))
        assertEquals(ContentType.TEXT_PLAIN, ContentType.parse("text/plain;"))
    }

    @Test
    fun contentTypeRenderWorks() {
        // rendering tests are in [HeadersTest] so it is just a smoke test
        assertEquals("text/plain; p1=v1", ContentType.TEXT_PLAIN.withParameter("p1", "v1").toString())
    }

    @Test
    fun testContentTypeInvalid() {
        val result = ContentType.parse("image/png; charset=utf-8\" but not really")
        assertEquals(ContentType.IMAGE_PNG.withParameter("charset", "utf-8\" but not really"), result)
    }

    @Test
    fun testContentTypeSingleQuoteAtStart() {
        val result = ContentType.parse("image/png; charset=\"utf-8 but not really")
        assertEquals(ContentType.IMAGE_PNG.withParameter("charset", "\"utf-8 but not really"), result)
    }

    @Test
    fun testContentTypeQuotedAtStartAndMiddle() {
        val result = ContentType.parse("image/png; charset=\"utf-8\" but not really")
        assertEquals(ContentType.IMAGE_PNG.withParameter("charset", "\"utf-8\" but not really"), result)
    }

    @Test
    fun testWithoutParameters() {
        assertEquals(ContentType.TEXT_PLAIN, ContentType.TEXT_PLAIN.withoutParameters())
        assertEquals(
            ContentType.TEXT_PLAIN,
            ContentType.TEXT_PLAIN.withParameter("a", "1").withoutParameters()
        )

        assertEquals(
            "text/plain",
            ContentType.TEXT_PLAIN.withParameter("a", "1").withoutParameters().toString()
        )

        assertEquals(
            "text/html",
            ContentType.parse("text/html;charset=utf-8").withoutParameters().toString()
        )
    }
}
