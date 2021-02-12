package dev.stalla.builder.validating

import assertk.all
import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import dev.stalla.builder.LinkBuilder
import dev.stalla.model.aLink
import dev.stalla.model.atom.Link
import org.junit.jupiter.api.Test

internal class ValidatingLinkBuilderTest {

    @Test
    internal fun `should not build a link when the mandatory fields are missing`() {
        val linkBuilder = ValidatingLinkBuilder()

        assertAll {
            assertThat(linkBuilder).prop(LinkBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(linkBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build a link with all the mandatory fields`() {
        val linkBuilder = ValidatingLinkBuilder()
            .href("https://example.com/link")

        assertAll {
            assertThat(linkBuilder).prop(LinkBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(linkBuilder.build()).isNotNull().all {
                prop(Link::href).isEqualTo("https://example.com/link")
                prop(Link::hrefLang).isNull()
                prop(Link::hrefResolved).isNull()
                prop(Link::length).isNull()
                prop(Link::rel).isNull()
                prop(Link::title).isNull()
                prop(Link::type).isNull()
            }
        }
    }

    @Test
    internal fun `should build a link with all the optional fields`() {
        val linkBuilder = ValidatingLinkBuilder()
            .href("https://example.com/link")
            .hrefLang("hrefLang")
            .hrefResolved("hrefResolved")
            .length("length")
            .rel("rel")
            .title("title")
            .type("type")

        assertAll {
            assertThat(linkBuilder).prop(LinkBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(linkBuilder.build()).isNotNull().all {
                prop(Link::href).isEqualTo("https://example.com/link")
                prop(Link::hrefLang).isEqualTo("hrefLang")
                prop(Link::hrefResolved).isEqualTo("hrefResolved")
                prop(Link::length).isEqualTo("length")
                prop(Link::rel).isEqualTo("rel")
                prop(Link::title).isEqualTo("title")
                prop(Link::type).isEqualTo("type")
            }
        }
    }

    @Test
    internal fun `should populate a Link builder with all properties from a Link model`() {
        val link = aLink()
        val linkBuilder = Link.builder().from(link)

        assertAll {
            assertThat(linkBuilder).prop(LinkBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(linkBuilder.build()).isNotNull().isEqualTo(link)
        }
    }
}
