package dev.stalla.model

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import dev.stalla.arguments
import dev.stalla.model.itunes.EpisodeType
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

internal class EpisodeTypeTest {

    private class EpisodeTypeNameProvider : ArgumentsProvider by arguments(*allEpisodeTypeNames.toTypedArray())

    private class EpisodeTypeEnumPropertyProvider : ArgumentsProvider by arguments(*EpisodeType.values())

    @ParameterizedTest
    @ArgumentsSource(EpisodeTypeNameProvider::class)
    fun `should retrieve all iTunes episode types from the factory method`(typeName: String) {
        assertThat(EpisodeType.of(typeName)).isNotNull()
            .prop(EpisodeType::type).isEqualTo(typeName)
    }

    @ParameterizedTest
    @ArgumentsSource(EpisodeTypeEnumPropertyProvider::class)
    fun `should expose only iTunes episode type properties that are defined`(episodeType: EpisodeType) {
        assertThat(allEpisodeTypeNames).contains(episodeType.type)
    }

    @Test
    fun `should not retrieve an undefined iTunes episode type from the factory method`() {
        assertThat(EpisodeType.of("itunes episode type")).isNull()
    }

    @Test
    fun `should be case insensitive in the iTunes episode type factory method`() {
        assertThat(EpisodeType.of("BONUS")).isNotNull()
            .prop(EpisodeType::type).isEqualTo("bonus")
    }

    companion object {

        @JvmStatic
        private val allEpisodeTypeNames = listOf(
            "bonus",
            "full",
            "trailer"
        )
    }
}
