package io.hemin.wien.builder.validating

import assertk.all
import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isInstanceOf
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import io.hemin.wien.builder.ITunesStyleCategoryBuilder
import io.hemin.wien.model.ITunesStyleCategory
import org.junit.jupiter.api.Test

internal class ValidatingITunesStyleCategoryBuilderTest {

    @Test
    internal fun `should not build an ITunesStyleCategory when the mandatory fields are absent`() {
        val categoryBuilder = ValidatingITunesStyleCategoryBuilder()

        assertAll {
            assertThat(categoryBuilder).prop(ITunesStyleCategoryBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(categoryBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an ITunesStyleCategory Simple when only provided a category`() {
        val categoryBuilder = ValidatingITunesStyleCategoryBuilder()
            .category("category")

        assertAll {
            assertThat(categoryBuilder).prop(ITunesStyleCategoryBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(categoryBuilder.build()).isNotNull()
                .isInstanceOf(ITunesStyleCategory.Simple::class)
                .prop(ITunesStyleCategory.Simple::name).isEqualTo("category")
        }
    }

    @Test
    internal fun `should build an ITunesStyleCategory Nested when provided both a category and a subcategory`() {
        val categoryBuilder = ValidatingITunesStyleCategoryBuilder()
            .category("category")
            .subcategory("subcategory")

        assertAll {
            assertThat(categoryBuilder).prop(ITunesStyleCategoryBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(categoryBuilder.build()).isNotNull()
                .isInstanceOf(ITunesStyleCategory.Nested::class).all {
                    prop(ITunesStyleCategory.Nested::name).isEqualTo("category")
                    prop(ITunesStyleCategory.Nested::subcategory).isEqualTo(ITunesStyleCategory.Simple("subcategory"))
                }
        }
    }
}
