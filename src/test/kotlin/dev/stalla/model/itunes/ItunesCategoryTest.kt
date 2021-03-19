package dev.stalla.model.itunes

import assertk.all
import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import assertk.assertions.isNotInstanceOf
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import dev.stalla.arguments
import dev.stalla.model.ItunesCategoryNameProvider
import dev.stalla.model.allItunesCategoryNames
import dev.stalla.model.nestedCategoryNames
import dev.stalla.model.simpleCategoryNames
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import kotlin.reflect.KVisibility
import kotlin.reflect.full.declaredMemberProperties

internal class ItunesCategoryTest {

    private class SimpleCategoryNameProvider : ArgumentsProvider by arguments(*simpleCategoryNames.toTypedArray())

    private class NestedCategoryNameProvider : ArgumentsProvider by arguments(*nestedCategoryNames.toTypedArray())

    private class ItunesCategoryFactoryPropertyProvider : ArgumentsProvider by arguments(
        *ItunesCategory.Factory::class.declaredMemberProperties
            .filter { member -> member.visibility == KVisibility.PUBLIC }
            .mapNotNull { member -> member.getter.call(this) }
            .filterIsInstance<ItunesCategory>()
            .toTypedArray()
    )

    private val categoryPropertyMap: Map<String, ItunesCategory> by lazy {
        val values: List<ItunesCategory> = ItunesCategory.Factory::class.declaredMemberProperties
            .filter { member -> member.visibility == KVisibility.PUBLIC }
            .mapNotNull { member -> member.getter.call(this) }
            .filterIsInstance<ItunesCategory>()

        values.associateBy({ it.type }, { it })
    }

    @ParameterizedTest
    @ArgumentsSource(SimpleCategoryNameProvider::class)
    fun `should retrieve all simple iTunes categories from the interface factory method`(simpleCategoryName: String) {
        assertThat(ItunesCategory.of(simpleCategoryName)).isNotNull().all {
            prop(ItunesCategory::type).isEqualTo(simpleCategoryName)
            isInstanceOf(ItunesCategory.Simple::class)
        }
    }

    @ParameterizedTest
    @ArgumentsSource(NestedCategoryNameProvider::class)
    fun `should retrieve all nested iTunes categories from the interface factory method`(nestedCategoryName: String) {
        assertThat(ItunesCategory.of(nestedCategoryName)).isNotNull().all {
            prop(ItunesCategory::type).isEqualTo(nestedCategoryName)
            isInstanceOf(ItunesCategory.Nested::class)
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ItunesCategoryNameProvider::class)
    fun `should retrieve all iTunes categories from the interface factory method`(categoryName: String) {
        assertThat(ItunesCategory.of(categoryName)).isNotNull().prop(ItunesCategory::type).isEqualTo(categoryName)
    }

    @ParameterizedTest
    @ArgumentsSource(SimpleCategoryNameProvider::class)
    fun `should not retrieve a nested iTunes categories from the interface factory method when asked for a simple`(
        simpleCategoryName: String
    ) {
        assertThat(ItunesCategory.of(simpleCategoryName)).isNotNull().isNotInstanceOf(ItunesCategory.Nested::class)
    }

    @ParameterizedTest
    @ArgumentsSource(NestedCategoryNameProvider::class)
    fun `should not retrieve a simple iTunes categories from the interface factory method when asked for a nested`(
        nestedCategoryName: String
    ) {
        assertThat(ItunesCategory.of(nestedCategoryName)).isNotNull().isNotInstanceOf(ItunesCategory.Simple::class)
    }

    @ParameterizedTest
    @ArgumentsSource(ItunesCategoryNameProvider::class)
    fun `should have a companion object property for every iTunes category`(categoryName: String) {
        assertThat(categoryPropertyMap[categoryName]).isNotNull()
    }

    @ParameterizedTest
    @ArgumentsSource(ItunesCategoryFactoryPropertyProvider::class)
    fun `should expose only iTunes category properties that are defined`(category: ItunesCategory) {
        assertThat(allItunesCategoryNames).contains(category.type)
    }

    @ParameterizedTest
    @ArgumentsSource(ItunesCategoryFactoryPropertyProvider::class)
    fun `should retrieve the correct iTunes category instances from the companion object factory method`(
        category: ItunesCategory
    ) {
        when (category) {
            is ItunesCategory.Simple -> assertThat(ItunesCategory.of(category.type)).isNotNull().all {
                prop(ItunesCategory::type).isEqualTo(category.type)
                isInstanceOf(ItunesCategory.Simple::class)
            }
            is ItunesCategory.Nested -> assertThat(ItunesCategory.of(category.type)).isNotNull().all {
                prop(ItunesCategory::type).isEqualTo(category.type)
                isInstanceOf(ItunesCategory.Nested::class)
            }
        }
    }

    @Test
    fun `should not retrieve an undefined iTunes category from the interface factory method`() {
        assertThat(ItunesCategory.of("itunes category")).isNull()
    }

    @Test
    fun `should be case insensitive in the iTunes category factory method`() {
        assertThat(ItunesCategory.of("ARTS")).isNotNull()
            .prop(ItunesCategory::type).isEqualTo("Arts")
    }

    @Test
    fun `should not parse null to an instance in the factory nethod`() {
        assertThat(ItunesCategory.of(null)).isNull()
    }

    companion object // Ensure there is a `this` instance to pass to kotlin.reflect.KCallable.call
}
