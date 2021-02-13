package dev.stalla.model.factory

import assertk.all
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import dev.stalla.model.itunes.ItunesCategory
import org.junit.jupiter.api.Test

class ItunesCategoryFactoryTest {

    private val validSimpleItunesCategoryNames = listOf(
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

    private val validNestedItunesCategoryNames = listOf(
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

    @Test
    fun `should retrieve all defined iTunes categories from the factory method`() {
        for (simpleCategory in validSimpleItunesCategoryNames) {
            assertThat(ItunesCategory.from(simpleCategory)).isNotNull().all {
                prop(ItunesCategory::value).isEqualTo(simpleCategory)
            }
        }
        for (nestedCategory in validNestedItunesCategoryNames) {
            assertThat(ItunesCategory.from(nestedCategory)).isNotNull().all {
                prop(ItunesCategory::value).isEqualTo(nestedCategory)
            }
        }
    }

    @Test
    fun `should not retrieve a not defined iTunes category from the factory method`() {
        assertThat(ItunesCategory.from("itunes category")).isNull()
    }

}
