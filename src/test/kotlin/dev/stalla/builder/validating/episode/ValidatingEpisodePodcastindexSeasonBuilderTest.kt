package dev.stalla.builder.validating.episode

import assertk.all
import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import dev.stalla.builder.episode.EpisodePodcastindexSeasonBuilder
import dev.stalla.model.anEpisodePodcastindexSeason
import dev.stalla.model.podcastindex.PodcastindexSeason
import org.junit.jupiter.api.Test

internal class ValidatingEpisodePodcastindexSeasonBuilderTest {

    @Test
    internal fun `should not build an Episode Podcastindex Season with when all the fields are missing`() {
        val seasonBuilder = ValidatingEpisodePodcastindexSeasonBuilder()

        assertAll {
            assertThat(seasonBuilder).prop(EpisodePodcastindexSeasonBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(seasonBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build an Episode Podcastindex Season with when the number field is missing`() {
        val seasonBuilder = ValidatingEpisodePodcastindexSeasonBuilder()
            .name("name")

        assertAll {
            assertThat(seasonBuilder).prop(EpisodePodcastindexSeasonBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(seasonBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode Podcastindex Season with all the mandatory fields`() {
        val seasonBuilder = ValidatingEpisodePodcastindexSeasonBuilder()
            .number(1)

        assertAll {
            assertThat(seasonBuilder).prop(EpisodePodcastindexSeasonBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(seasonBuilder.build()).isNotNull().all {
                prop(PodcastindexSeason::number).isEqualTo(1)
                prop(PodcastindexSeason::name).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode Podcastindex Season with all the added entries to its fields`() {
        val seasonBuilder = ValidatingEpisodePodcastindexSeasonBuilder()
            .number(1)
            .name("name")

        assertAll {
            assertThat(seasonBuilder).prop(EpisodePodcastindexSeasonBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(seasonBuilder.build()).isNotNull().all {
                prop(PodcastindexSeason::number).isEqualTo(1)
                prop(PodcastindexSeason::name).isEqualTo("name")
            }
        }
    }

    @Test
    internal fun `should populate an Episode Podcastindex Season builder with all properties from an Episode Podcastindex Episode model`() {
        val podcastindexSeason = anEpisodePodcastindexSeason()
        val podcastindexseasonBuilder = PodcastindexSeason.builder().applyFrom(podcastindexSeason)

        assertAll {
            assertThat(podcastindexseasonBuilder).prop(EpisodePodcastindexSeasonBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastindexseasonBuilder.build()).isNotNull().isEqualTo(podcastindexSeason)
        }
    }

}
