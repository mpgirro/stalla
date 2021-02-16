package dev.stalla.builder.validating

import assertk.all
import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import dev.stalla.builder.RssCategoryBuilder
import dev.stalla.model.anRssCategory
import dev.stalla.model.rss.RssCategory
import org.junit.jupiter.api.Test

internal class ValidatingRssCategoryBuilderTest {

    @Test
    internal fun `should not build an RssCategory when the category value is empty`() {
        val categoryBuilder = ValidatingRssCategoryBuilder()

        assertAll {
            assertThat(categoryBuilder).prop(RssCategoryBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(categoryBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an RssCategory Simple when only provided a category`() {
        val categoryBuilder = ValidatingRssCategoryBuilder()
            .category("category")

        assertAll {
            assertThat(categoryBuilder).prop(RssCategoryBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(categoryBuilder.build()).isNotNull().all {
                prop(RssCategory::name).isEqualTo("category")
                prop(RssCategory::domain).isNull()
            }
        }
    }

    @Test
    internal fun `should build an RssCategory Nested when provided both a category and a subcategory`() {
        val categoryBuilder = ValidatingRssCategoryBuilder()
            .category("category")
            .domain("domain")

        assertAll {
            assertThat(categoryBuilder).prop(RssCategoryBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(categoryBuilder.build()).isNotNull().all {
                prop(RssCategory::name).isEqualTo("category")
                prop(RssCategory::domain).isEqualTo("domain")
            }
        }
    }

    @Test
    internal fun `should populate a RssCategory builder with all properties from a RssCategory model`() {
        val category = anRssCategory()
        val categoryBuilder = RssCategory.builder().applyFrom(category)

        assertAll {
            assertThat(categoryBuilder).prop(RssCategoryBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(categoryBuilder.build()).isNotNull().isEqualTo(category)
        }
    }
}
