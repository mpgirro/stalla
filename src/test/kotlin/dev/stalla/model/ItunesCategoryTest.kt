package dev.stalla.model

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
import dev.stalla.model.itunes.ItunesCategory
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import kotlin.reflect.KVisibility
import kotlin.reflect.full.declaredMemberProperties

internal class ItunesCategoryTest {

    private class SimpleCategoryNameProvider : ArgumentsProvider by arguments(*simpleCategoryNames.toTypedArray())

    private class NestedCategoryNameProvider : ArgumentsProvider by arguments(*nestedCategoryNames.toTypedArray())

    private class ItunesCategoryNameProvider : ArgumentsProvider by arguments(*allItunesCategoryNames.toTypedArray())

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

    companion object {

        @JvmStatic
        private val simpleCategoryNames = listOf(
            "Arts",
            "Business",
            "Comedy",
            "Education",
            "Fiction",
            "Government",
            "History",
            "Health & Fitness",
            "Kids & Family",
            "Leisure",
            "Music",
            "News",
            "Religion & Spirituality",
            "Science",
            "Society & Culture",
            "Sports",
            "Technology",
            "True Crime",
            "TV & Film"
        )

        @JvmStatic
        private val nestedCategoryNames = listOf(
            "Books",
            "Design",
            "Fashion & Beauty",
            "Food",
            "Performing Arts",
            "Visual Arts",
            "Careers",
            "Entrepreneurship",
            "Investing",
            "Management",
            "Marketing",
            "Non-Profit",
            "Comedy Interviews",
            "Improv",
            "Stand-Up",
            "Courses",
            "How To",
            "Language Learning",
            "Self-Improvement",
            "Comedy Fiction",
            "Drama",
            "Science Fiction",
            "Alternative Health",
            "Fitness",
            "Medicine",
            "Mental Health",
            "Nutrition",
            "Sexuality",
            "Education for Kids",
            "Parenting",
            "Pets & Animals",
            "Stories for Kids",
            "Animation & Manga",
            "Automotive",
            "Aviation",
            "Crafts",
            "Games",
            "Hobbies",
            "Home & Garden",
            "Video Games",
            "Music Commentary",
            "Music History",
            "Music Interviews",
            "Business News",
            "Daily News",
            "Entertainment News",
            "News Commentary",
            "Politics",
            "Sports News",
            "Tech News",
            "Buddhism",
            "Christianity",
            "Hinduism",
            "Islam",
            "Judaism",
            "Religion",
            "Spirituality",
            "Astronomy",
            "Chemistry",
            "Earth Sciences",
            "Life Sciences",
            "Mathematics",
            "Natural Sciences",
            "Nature",
            "Physics",
            "Social Sciences",
            "Documentary",
            "Personal Journals",
            "Philosophy",
            "Places & Travel",
            "Relationships",
            "Baseball",
            "Basketball",
            "Cricket",
            "Fantasy Sports",
            "Football",
            "Golf",
            "Hockey",
            "Rugby",
            "Running",
            "Soccer",
            "Swimming",
            "Tennis",
            "Volleyball",
            "Wilderness",
            "Wrestling",
            "After Shows",
            "Film History",
            "Film Interviews",
            "Film Reviews",
            "TV Reviews"
        )

        @JvmStatic
        private val allItunesCategoryNames = simpleCategoryNames + nestedCategoryNames
    }
}
