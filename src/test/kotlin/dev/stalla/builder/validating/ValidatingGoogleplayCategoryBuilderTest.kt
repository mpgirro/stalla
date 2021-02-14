package dev.stalla.builder.validating

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
import dev.stalla.builder.GoogleplayCategoryBuilder
import dev.stalla.model.aGoogleplayCategory
import dev.stalla.model.googleplay.GoogleplayCategory
import org.junit.jupiter.api.Test

internal class ValidatingGoogleplayCategoryBuilderTest {

    @Test
    internal fun `should not build a GoogleplayCategory when the mandatory fields are absent`() {
        val categoryBuilder = ValidatingGoogleplayCategoryBuilder()

        assertAll {
            assertThat(categoryBuilder).prop(GoogleplayCategoryBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(categoryBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build a GoogleplayCategory Simple when only provided a category`() {
        val categoryBuilder = ValidatingGoogleplayCategoryBuilder()
            .category("category")

        assertAll {
            assertThat(categoryBuilder).prop(GoogleplayCategoryBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(categoryBuilder.build()).isNotNull()
                .isInstanceOf(GoogleplayCategory.Simple::class)
                .prop(GoogleplayCategory.Simple::name).isEqualTo("category")
        }
    }

    @Test
    internal fun `should build a GoogleplayCategory Nested when provided both a category and a subcategory`() {
        val categoryBuilder = ValidatingGoogleplayCategoryBuilder()
            .category("category")
            .subcategory("subcategory")

        assertAll {
            assertThat(categoryBuilder).prop(GoogleplayCategoryBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(categoryBuilder.build()).isNotNull()
                .isInstanceOf(GoogleplayCategory.Nested::class).all {
                    prop(GoogleplayCategory.Nested::name).isEqualTo("category")
                    prop(GoogleplayCategory.Nested::subcategory).isEqualTo(GoogleplayCategory.Simple("subcategory"))
                }
        }
    }

    @Test
    internal fun `should populate a GoogleplayCategory builder with all properties from a GoogleplayCategory model`() {
        val category = aGoogleplayCategory()
        val categoryBuilder = GoogleplayCategory.builder().from(category)

        assertAll {
            assertThat(categoryBuilder).prop(GoogleplayCategoryBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(categoryBuilder.build()).isNotNull().isEqualTo(category)
        }
    }
}
