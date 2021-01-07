package io.hemin.wien.builder.validating.episode

import assertk.all
import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import io.hemin.wien.builder.episode.EpisodeGuidBuilder
import io.hemin.wien.builder.episode.EpisodeITunesBuilder
import io.hemin.wien.model.Episode
import org.junit.jupiter.api.Test

internal class ValidatingEpisodeGuidBuilderTest {

    @Test
    internal fun `should not build an Episode Guid when all fields are missing`() {
        val episodeGuidBuilder = ValidatingEpisodeGuidBuilder()

        assertAll {
            assertThat(episodeGuidBuilder).prop(EpisodeGuidBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodeGuidBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode Guid with only a textContent`() {
        val episodeGuidBuilder = ValidatingEpisodeGuidBuilder()
            .textContent("textContent")

        assertAll {
            assertThat(episodeGuidBuilder).prop(EpisodeGuidBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeGuidBuilder.build()).isNotNull().all {
                prop(Episode.Guid::textContent).isEqualTo("textContent")
                prop(Episode.Guid::isPermalink).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode Guid with all the optional fields`() {
        val episodeGuidBuilder = ValidatingEpisodeGuidBuilder()
            .textContent("textContent")
            .isPermalink(true)

        assertAll {
            assertThat(episodeGuidBuilder).prop(EpisodeGuidBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeGuidBuilder.build()).isNotNull().all {
                prop(Episode.Guid::textContent).isEqualTo("textContent")
                prop(Episode.Guid::isPermalink).isNotNull().isTrue()
            }
        }
    }
}
