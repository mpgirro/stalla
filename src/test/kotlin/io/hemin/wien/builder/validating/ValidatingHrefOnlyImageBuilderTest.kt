package io.hemin.wien.builder.validating

import assertk.all
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import io.hemin.wien.model.HrefOnlyImage
import org.junit.jupiter.api.Test

internal class ValidatingHrefOnlyImageBuilderTest {

    @Test
    internal fun `should not build an Image when the mandatory fields are missing`() {
        val imageBuilder = ValidatingHrefOnlyImageBuilder()

        assertThat(imageBuilder.build()).isNull()
    }

    @Test
    internal fun `should build an Image with all the mandatory href field`() {
        val imageBuilder = ValidatingHrefOnlyImageBuilder()
            .href("href")

        assertThat(imageBuilder.build()).isNotNull().all {
            prop(HrefOnlyImage::href).isEqualTo("href")
        }
    }
}
