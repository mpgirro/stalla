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
import dev.stalla.builder.episode.EpisodePodcastindexEpisodeBuilder
import dev.stalla.model.anEpisodePodcastindexEpisode
import dev.stalla.model.podcastindex.PodcastindexEpisode
import org.junit.jupiter.api.Test

internal class ValidatingEpisodePodcastindexEpisodeBuilderTest {

    @Test
    internal fun `should not build an Episode Podcastindex Episode with when all the fields are missing`() {
        val episodeBuilder = ValidatingEpisodePodcastindexEpisodeBuilder()

        assertAll {
            assertThat(episodeBuilder).prop(EpisodePodcastindexEpisodeBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodeBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build an Episode Podcastindex Episode with when the number field is missing`() {
        val episodeBuilder = ValidatingEpisodePodcastindexEpisodeBuilder()
            .display("display")

        assertAll {
            assertThat(episodeBuilder).prop(EpisodePodcastindexEpisodeBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodeBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode Podcastindex Episode with all the mandatory fields`() {
        val episodeBuilder = ValidatingEpisodePodcastindexEpisodeBuilder()
            .number(1.0)

        assertAll {
            assertThat(episodeBuilder).prop(EpisodePodcastindexEpisodeBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeBuilder.build()).isNotNull().all {
                prop(PodcastindexEpisode::number).isEqualTo(1.0)
                prop(PodcastindexEpisode::display).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode Podcastindex Episode with all the added entries to its fields`() {
        val episodeBuilder = ValidatingEpisodePodcastindexEpisodeBuilder()
            .number(1.0)
            .display("display")

        assertAll {
            assertThat(episodeBuilder).prop(EpisodePodcastindexEpisodeBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeBuilder.build()).isNotNull().all {
                prop(PodcastindexEpisode::number).isEqualTo(1.0)
                prop(PodcastindexEpisode::display).isEqualTo("display")
            }
        }
    }

    @Test
    internal fun `should populate an Episode Podcastindex Episode builder with all properties from an Episode Podcastindex Episode model`() {
        val podcastindexEpisode = anEpisodePodcastindexEpisode()
        val podcastindexEpisodeBuilder = PodcastindexEpisode.builder().applyFrom(podcastindexEpisode)

        assertAll {
            assertThat(podcastindexEpisodeBuilder).prop(EpisodePodcastindexEpisodeBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastindexEpisodeBuilder.build()).isNotNull().isEqualTo(podcastindexEpisode)
        }
    }

}
