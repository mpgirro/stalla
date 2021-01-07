package io.hemin.wien.builder.validating

import assertk.all
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import io.hemin.wien.model.RssImage
import org.junit.jupiter.api.Test

internal class ValidatingRssImageBuilderTest {

    @Test
    internal fun `should not build an Image when the mandatory fields are missing`() {
        val imageBuilder = ValidatingRssImageBuilder()

        assertThat(imageBuilder.build()).isNull()
    }

    @Test
    internal fun `should not build an Image with only the url mandatory field`() {
        val imageBuilder = ValidatingRssImageBuilder()
            .url("url")

        assertThat(imageBuilder.build()).isNull()
    }

    @Test
    internal fun `should not build an Image with only the title mandatory field`() {
        val imageBuilder = ValidatingRssImageBuilder()
            .title("title")

        assertThat(imageBuilder.build()).isNull()
    }

    @Test
    internal fun `should not build an Image with only the link mandatory field`() {
        val imageBuilder = ValidatingRssImageBuilder()
            .link("link")

        assertThat(imageBuilder.build()).isNull()
    }

    @Test
    internal fun `should build an Image with all the mandatory fields`() {
        val imageBuilder = ValidatingRssImageBuilder()
            .url("url")
            .title("title")
            .link("link")

        assertThat(imageBuilder.build()).isNotNull().all {
            prop(RssImage::url).isEqualTo("url")
            prop(RssImage::title).isEqualTo("title")
            prop(RssImage::link).isEqualTo("link")
            prop(RssImage::width).isNull()
            prop(RssImage::height).isNull()
            prop(RssImage::description).isNull()
        }
    }

    @Test
    internal fun `should build an Image with all the optional fields`() {
        val imageBuilder = ValidatingRssImageBuilder()
            .url("url")
            .title("title")
            .link("link")
            .width(123)
            .height(456)
            .description("description")

        assertThat(imageBuilder.build()).isNotNull().all {
            prop(RssImage::url).isEqualTo("url")
            prop(RssImage::title).isEqualTo("title")
            prop(RssImage::link).isEqualTo("link")
            prop(RssImage::width).isEqualTo(123)
            prop(RssImage::height).isEqualTo(456)
            prop(RssImage::description).isEqualTo("description")
        }
    }
}
