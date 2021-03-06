package dev.stalla.model.googleplay

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import dev.stalla.arguments
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

internal class GoogleplayCategoryTest {

    private class GoogleplayCategoryNameProvider : ArgumentsProvider by arguments(*allGoogleplayCategoryNames.toTypedArray())

    private class GoogleplayCategoryEnumPropertyProvider : ArgumentsProvider by arguments(*GoogleplayCategory.values())

    @ParameterizedTest
    @ArgumentsSource(GoogleplayCategoryNameProvider::class)
    fun `should retrieve all Google Play categories from the factory method`(categoryName: String) {
        assertThat(GoogleplayCategory.of(categoryName)).isNotNull()
            .prop(GoogleplayCategory::type).isEqualTo(categoryName)
    }

    @ParameterizedTest
    @ArgumentsSource(GoogleplayCategoryEnumPropertyProvider::class)
    fun `should expose only Google Play category properties that are defined`(category: GoogleplayCategory) {
        assertThat(allGoogleplayCategoryNames).contains(category.type)
    }

    @Test
    fun `should not retrieve an undefined Google Play category from the factory method`() {
        assertThat(GoogleplayCategory.of("googleplay category")).isNull()
    }

    @Test
    fun `should be case insensitive in the Google Play category factory method`() {
        assertThat(GoogleplayCategory.of("ARTS")).isNotNull()
            .prop(GoogleplayCategory::type).isEqualTo("Arts")
    }

    @Test
    fun `should not parse null to an instance in the factory nethod`() {
        assertThat(GoogleplayCategory.of(null)).isNull()
    }

    companion object {

        @JvmStatic
        private val allGoogleplayCategoryNames = listOf(
            "Arts",
            "Business",
            "Comedy",
            "Education",
            "Games & Hobbies",
            "Government & Organizations",
            "Health",
            "Kids & Family",
            "Music",
            "News & Politics",
            "Religion & Spirituality",
            "Science & Medicine",
            "Society & Culture",
            "Sports & Recreation",
            "TV & Film",
            "Technology"
        )
    }
}
