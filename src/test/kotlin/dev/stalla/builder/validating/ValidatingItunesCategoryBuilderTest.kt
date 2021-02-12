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
import dev.stalla.builder.ItunesCategoryBuilder
import dev.stalla.model.anItunesCategory
import dev.stalla.model.itunes.ItunesCategory
import org.junit.jupiter.api.Test

internal class ValidatingItunesCategoryBuilderTest {

    @Test
    internal fun `should not build an ITunesStyleCategory when the mandatory fields are absent`() {
        val categoryBuilder = ValidatingItunesCategoryBuilder()

        assertAll {
            assertThat(categoryBuilder).prop(ItunesCategoryBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(categoryBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an ITunesStyleCategory Simple when only provided a category`() {
        val categoryBuilder = ValidatingItunesCategoryBuilder()
            .category("category")

        assertAll {
            assertThat(categoryBuilder).prop(ItunesCategoryBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(categoryBuilder.build()).isNotNull()
                .isInstanceOf(ItunesCategory.Simple::class)
                .prop(ItunesCategory.Simple::name).isEqualTo("category")
        }
    }

    @Test
    internal fun `should build an ITunesStyleCategory Nested when provided both a category and a subcategory`() {
        val categoryBuilder = ValidatingItunesCategoryBuilder()
            .category("category")
            .subcategory("subcategory")

        assertAll {
            assertThat(categoryBuilder).prop(ItunesCategoryBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(categoryBuilder.build()).isNotNull()
                .isInstanceOf(ItunesCategory.Nested::class).all {
                    prop(ItunesCategory.Nested::name).isEqualTo("category")
                    prop(ItunesCategory.Nested::subcategory).isEqualTo(ItunesCategory.Simple("subcategory"))
                }
        }
    }

    @Test
    internal fun `should populate an ITunesStyleCategory builder with all properties from an ITunesStyleCategory model`() {
        val category = anItunesCategory()
        val categoryBuilder = ItunesCategory.builder().from(category)

        assertAll {
            assertThat(categoryBuilder).prop(ItunesCategoryBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(categoryBuilder.build()).isNotNull().isEqualTo(category)
        }
    }
}
