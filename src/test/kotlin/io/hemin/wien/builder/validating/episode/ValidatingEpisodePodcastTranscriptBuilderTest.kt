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
import io.hemin.wien.builder.episode.EpisodePodcastTranscriptBuilder
import io.hemin.wien.model.Episode
import io.hemin.wien.model.episode.anEpisodePodcastTranscript
import org.junit.jupiter.api.Test
import java.util.Locale

internal class ValidatingEpisodePodcastTranscriptBuilderTest {

    @Test
    internal fun `should not build an Episode Podcast Transcript with when all the fields are missing`() {
        val transcriptBuilder = ValidatingEpisodePodcastTranscriptBuilder()

        assertAll {
            assertThat(transcriptBuilder).prop(EpisodePodcastTranscriptBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(transcriptBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build an Episode Podcast Transcript with when the url field is missing`() {
        val transcriptBuilder = ValidatingEpisodePodcastTranscriptBuilder()
            .type(Episode.Podcast.Transcript.Type.PLAIN_TEXT)

        assertAll {
            assertThat(transcriptBuilder).prop(EpisodePodcastTranscriptBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(transcriptBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build an Episode Podcast Transcript with when the type field is missing`() {
        val transcriptBuilder = ValidatingEpisodePodcastTranscriptBuilder()
            .url("https://example.com/episode/transcript.txt")

        assertAll {
            assertThat(transcriptBuilder).prop(EpisodePodcastTranscriptBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(transcriptBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode Podcast Transcript with with all the added entries to its fields`() {
        val transcriptBuilder = ValidatingEpisodePodcastTranscriptBuilder()
            .url("https://example.com/episode/transcript.txt")
            .type(Episode.Podcast.Transcript.Type.PLAIN_TEXT)
            .language(Locale.ITALIAN)
            .rel("captions")

        assertAll {
            assertThat(transcriptBuilder).prop(EpisodePodcastTranscriptBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(transcriptBuilder.build()).isNotNull().all {
                prop(Episode.Podcast.Transcript::url).isEqualTo("https://example.com/episode/transcript.txt")
                prop(Episode.Podcast.Transcript::type).isEqualTo(Episode.Podcast.Transcript.Type.PLAIN_TEXT)
                prop(Episode.Podcast.Transcript::language).isEqualTo(Locale.ITALIAN)
                prop(Episode.Podcast.Transcript::rel).isEqualTo("captions")
            }
        }
    }

    @Test
    internal fun `should populate an Episode Podcastindex Transcript builder with all properties from an Episode Podcastindex Transcript model`() {
        val episodePodcastTranscript = anEpisodePodcastTranscript()
        val episodePodcastTranscriptBuilder = Episode.Podcast.Transcript.builder().from(episodePodcastTranscript)

        assertAll {
            assertThat(episodePodcastTranscriptBuilder).prop(EpisodePodcastTranscriptBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodePodcastTranscriptBuilder.build()).isNotNull().isEqualTo(episodePodcastTranscript)
        }
    }
}
