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
import dev.stalla.builder.ItunesStyleCategoryBuilder
import dev.stalla.model.anItunesCategory
import dev.stalla.model.itunes.ItunesStyleCategory
import org.junit.jupiter.api.Test

internal class ValidatingItunesStyleCategoryBuilderTest {

    @Test
    internal fun `should not build an ITunesStyleCategory when the mandatory fields are absent`() {
        val categoryBuilder = ValidatingItunesStyleCategoryBuilder()

        assertAll {
            assertThat(categoryBuilder).prop(ItunesStyleCategoryBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(categoryBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an ITunesStyleCategory Simple when only provided a category`() {
        val categoryBuilder = ValidatingItunesStyleCategoryBuilder()
            .category("category")

        assertAll {
            assertThat(categoryBuilder).prop(ItunesStyleCategoryBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(categoryBuilder.build()).isNotNull()
                .isInstanceOf(ItunesStyleCategory.Simple::class)
                .prop(ItunesStyleCategory.Simple::name).isEqualTo("category")
        }
    }

    @Test
    internal fun `should build an ITunesStyleCategory Nested when provided both a category and a subcategory`() {
        val categoryBuilder = ValidatingItunesStyleCategoryBuilder()
            .category("category")
            .subcategory("subcategory")

        assertAll {
            assertThat(categoryBuilder).prop(ItunesStyleCategoryBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(categoryBuilder.build()).isNotNull()
                .isInstanceOf(ItunesStyleCategory.Nested::class).all {
                    prop(ItunesStyleCategory.Nested::name).isEqualTo("category")
                    prop(ItunesStyleCategory.Nested::subcategory).isEqualTo(ItunesStyleCategory.Simple("subcategory"))
                }
        }
    }

    @Test
    internal fun `should populate an ITunesStyleCategory builder with all properties from an ITunesStyleCategory model`() {
        val category = anItunesCategory()
        val categoryBuilder = ItunesStyleCategory.builder().from(category)

        assertAll {
            assertThat(categoryBuilder).prop(ItunesStyleCategoryBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(categoryBuilder.build()).isNotNull().isEqualTo(category)
        }
    }
}