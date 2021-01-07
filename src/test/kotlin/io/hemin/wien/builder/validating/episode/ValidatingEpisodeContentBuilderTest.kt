package io.hemin.wien.builder.validating.episode

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import io.hemin.wien.builder.episode.EpisodeContentBuilder
import io.hemin.wien.model.Episode
import org.junit.jupiter.api.Test

internal class ValidatingEpisodeContentBuilderTest {

    @Test
    internal fun `should not build an Episode Content when the mandatory fields are missing`() {
        val episodeContentBuilder = ValidatingEpisodeContentBuilder()

        assertAll {
            assertThat(episodeContentBuilder).prop(EpisodeContentBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodeContentBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode Content with all the mandatory fields`() {
        val episodeContentBuilder = ValidatingEpisodeContentBuilder()
            .encoded("encoded")

        assertAll {
            assertThat(episodeContentBuilder).prop(EpisodeContentBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeContentBuilder.build()).isNotNull().prop(Episode.Content::encoded).isEqualTo("encoded")
        }
    }
}
