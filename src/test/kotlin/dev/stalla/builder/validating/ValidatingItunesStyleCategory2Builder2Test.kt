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
import dev.stalla.builder.ItunesStyleCategoryBuilder2
import dev.stalla.model.anItunesCategory
import dev.stalla.model.itunes.ItunesStyleCategory2
import org.junit.jupiter.api.Test

internal class ValidatingItunesStyleCategory2Builder2Test {

    @Test
    internal fun `should not build an ITunesStyleCategory when the mandatory fields are absent`() {
        val categoryBuilder = ValidatingItunesStyleCategoryBuilder2()

        assertAll {
            assertThat(categoryBuilder).prop(ItunesStyleCategoryBuilder2::hasEnoughDataToBuild).isFalse()

            assertThat(categoryBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an ITunesStyleCategory Simple when only provided a category`() {
        val categoryBuilder = ValidatingItunesStyleCategoryBuilder2()
            .category("category")

        assertAll {
            assertThat(categoryBuilder).prop(ItunesStyleCategoryBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(categoryBuilder.build()).isNotNull()
                .isInstanceOf(ItunesStyleCategory2.Simple::class)
                .prop(ItunesStyleCategory2.Simple::name).isEqualTo("category")
        }
    }

    @Test
    internal fun `should build an ITunesStyleCategory Nested when provided both a category and a subcategory`() {
        val categoryBuilder = ValidatingItunesStyleCategoryBuilder2()
            .category("category")
            .subcategory("subcategory")

        assertAll {
            assertThat(categoryBuilder).prop(ItunesStyleCategoryBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(categoryBuilder.build()).isNotNull()
                .isInstanceOf(ItunesStyleCategory2.Nested::class).all {
                    prop(ItunesStyleCategory2.Nested::name).isEqualTo("category")
                    prop(ItunesStyleCategory2.Nested::subcategory).isEqualTo(ItunesStyleCategory2.Simple("subcategory"))
                }
        }
    }

    @Test
    internal fun `should populate an ITunesStyleCategory builder with all properties from an ITunesStyleCategory model`() {
        val category = anItunesCategory()
        val categoryBuilder = ItunesStyleCategory2.builder().from(category)

        assertAll {
            assertThat(categoryBuilder).prop(ItunesStyleCategoryBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(categoryBuilder.build()).isNotNull().isEqualTo(category)
        }
    }
}
