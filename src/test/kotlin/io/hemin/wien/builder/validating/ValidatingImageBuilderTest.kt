package io.hemin.wien.builder.validating

import assertk.all
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import io.hemin.wien.model.Image
import org.junit.jupiter.api.Test

internal class ValidatingImageBuilderTest {

    @Test
    internal fun `should not build an Image when the mandatory fields are missing`() {
        val imageBuilder = ValidatingImageBuilder()

        assertThat(imageBuilder.build()).isNull()
    }

    @Test
    internal fun `should build an Image with all the mandatory fields`() {
        val imageBuilder = ValidatingImageBuilder()
            .url("url")

        assertThat(imageBuilder.build()).isNotNull().all {
            prop(Image::url).isEqualTo("url")
            prop(Image::title).isNull()
            prop(Image::link).isNull()
            prop(Image::width).isNull()
            prop(Image::height).isNull()
            prop(Image::description).isNull()
        }
    }

    @Test
    internal fun `should build an Image with all the optional fields`() {
        val imageBuilder = ValidatingImageBuilder()
            .url("url")
            .title("title")
            .link("link")
            .width(123)
            .height(456)
            .description("description")

        assertThat(imageBuilder.build()).isNotNull().all {
            prop(Image::url).isEqualTo("url")
            prop(Image::title).isEqualTo("title")
            prop(Image::link).isEqualTo("link")
            prop(Image::width).isEqualTo(123)
            prop(Image::height).isEqualTo(456)
            prop(Image::description).isEqualTo("description")
        }
    }
}
