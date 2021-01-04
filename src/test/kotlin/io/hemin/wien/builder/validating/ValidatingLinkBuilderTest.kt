package io.hemin.wien.builder.validating

import assertk.all
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import io.hemin.wien.model.Link
import org.junit.jupiter.api.Test

internal class ValidatingLinkBuilderTest {

    @Test
    internal fun `should not build a link when the mandatory fields are missing`() {
        val linkBuilder = ValidatingLinkBuilder()

        assertThat(linkBuilder.build()).isNull()
    }

    @Test
    internal fun `should build a link with all the mandatory fields`() {
        val linkBuilder = ValidatingLinkBuilder()
            .href("https://example.com/link")

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
