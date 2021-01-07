package io.hemin.wien.builder.validating

import assertk.all
import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import io.hemin.wien.builder.HrefOnlyImageBuilder
import io.hemin.wien.builder.ITunesStyleCategoryBuilder
import io.hemin.wien.model.HrefOnlyImage
import org.junit.jupiter.api.Test

internal class ValidatingHrefOnlyImageBuilderTest {

    @Test
    internal fun `should not build an Image when the mandatory fields are missing`() {
        val imageBuilder = ValidatingHrefOnlyImageBuilder()

        assertAll {
            assertThat(imageBuilder).prop(HrefOnlyImageBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(imageBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Image with all the mandatory href field`() {
        val imageBuilder = ValidatingHrefOnlyImageBuilder()
            .href("href")

        assertAll {
            assertThat(imageBuilder).prop(HrefOnlyImageBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(imageBuilder.build()).isNotNull().all {
                prop(HrefOnlyImage::href).isEqualTo("href")
            }
        }
    }
}
