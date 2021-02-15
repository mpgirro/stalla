package dev.stalla.model.factory

import assertk.all
import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import dev.stalla.model.itunes.ItunesCategory
import org.junit.jupiter.api.Test
import kotlin.reflect.KVisibility
import kotlin.reflect.full.declaredMemberProperties

class ItunesCategoryFactoryTest {

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

    private val allCategoryNames = simpleCategoryNames + nestedCategoryNames

    @Test
    fun `should retrieve all defined iTunes categories from the interface factory method`() {
        for (simpleCategory in simpleCategoryNames) {
            assertThat(ItunesCategory.from(simpleCategory)).isNotNull().all {
                prop(ItunesCategory::name).isEqualTo(simpleCategory)
                isInstanceOf(ItunesCategory.Simple::class)
            }
        }
        for (nestedCategory in nestedCategoryNames) {
            assertThat(ItunesCategory.from(nestedCategory)).isNotNull().all {
                prop(ItunesCategory::name).isEqualTo(nestedCategory)
                isInstanceOf(ItunesCategory.Nested::class)
            }
        }
    }

    @Test
    fun `should have a property in the interface factory companion for each iTunes category`() {
        ItunesCategory.Factory::class.declaredMemberProperties.forEach { member ->
            if (member.visibility == KVisibility.PUBLIC) {
                val value = member.getter.call(this)
                if (value is ItunesCategory) {
                    assertThat(allCategoryNames).contains(value.name)
                }
            }
        }
    }

    @Test
    fun `should retrieve the correct iTunes category instances from the companion object factory method`() {
        ItunesCategory.Factory::class.declaredMemberProperties.forEach { member ->
            if (member.visibility == KVisibility.PUBLIC) {
                val value = member.getter.call(this)
                when (value) {
                    is ItunesCategory.Simple -> assertThat(ItunesCategory.from(value.name)).isNotNull().all {
                        prop(ItunesCategory::name).isEqualTo(value.name)
                        isInstanceOf(ItunesCategory.Simple::class)
                    }
                    is ItunesCategory.Nested -> assertThat(ItunesCategory.from(value.name)).isNotNull().all {
                        prop(ItunesCategory::name).isEqualTo(value.name)
                        isInstanceOf(ItunesCategory.Nested::class)
                    }
                }
            }
        }
    }

    @Test
    fun `should not retrieve an undefined iTunes category from the interface factory method`() {
        assertThat(ItunesCategory.from("itunes category")).isNull()
    }
}
