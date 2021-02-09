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
import dev.stalla.builder.episode.EpisodePodcastChaptersBuilder
import dev.stalla.model.Episode
import dev.stalla.model.episode.anEpisodePodcastChapters
import org.junit.jupiter.api.Test

internal class ValidatingEpisodePodcastChaptersBuilderTest {

    @Test
    internal fun `should not build an Episode Podcast Chapters with when all the fields are missing`() {
        val chaptersBuilder = ValidatingEpisodePodcastChaptersBuilder()

        assertAll {
            assertThat(chaptersBuilder).prop(EpisodePodcastChaptersBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(chaptersBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build an Episode Podcast Chapters with when the url field is missing`() {
        val chaptersBuilder = ValidatingEpisodePodcastChaptersBuilder()
            .type("text/json+chapters")

        assertAll {
            assertThat(chaptersBuilder).prop(EpisodePodcastChaptersBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(chaptersBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build an Episode Podcast Chapters with when the type field is missing`() {
        val chaptersBuilder = ValidatingEpisodePodcastChaptersBuilder()
            .url("https://example.com/episode/chapters.json")

        assertAll {
            assertThat(chaptersBuilder).prop(EpisodePodcastChaptersBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(chaptersBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode Podcast Chapters with with all the added entries to its fields`() {
        val chaptersBuilder = ValidatingEpisodePodcastChaptersBuilder()
            .url("https://example.com/episode/chapters.json")
            .type("text/json+chapters")

        assertAll {
            assertThat(chaptersBuilder).prop(EpisodePodcastChaptersBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(chaptersBuilder.build()).isNotNull().all {
                prop(Episode.Podcast.Chapters::url).isEqualTo("https://example.com/episode/chapters.json")
                prop(Episode.Podcast.Chapters::type).isEqualTo("text/json+chapters")
            }
        }
    }

    @Test
    internal fun `should populate an Episode Podcastindex Chapters builder with all properties from an Episode Podcastindex Chapters model`() {
        val episodePodcastChapters = anEpisodePodcastChapters()
        val episodePodcastChaptersBuilder = Episode.Podcast.Chapters.builder().from(episodePodcastChapters)

        assertAll {
            assertThat(episodePodcastChaptersBuilder).prop(EpisodePodcastChaptersBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodePodcastChaptersBuilder.build()).isNotNull().isEqualTo(episodePodcastChapters)
        }
    }
}
