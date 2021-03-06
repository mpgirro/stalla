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
import dev.stalla.builder.episode.EpisodePodcastindexTranscriptBuilder
import dev.stalla.model.anEpisodePodcastindexTranscript
import dev.stalla.model.podcastindex.Transcript
import dev.stalla.model.podcastindex.TranscriptType
import org.junit.jupiter.api.Test
import java.util.Locale

internal class ValidatingEpisodePodcastindexTranscriptBuilderTest {

    @Test
    internal fun `should not build an Episode Podcastindex Transcript with when all the fields are missing`() {
        val transcriptBuilder = ValidatingEpisodePodcastindexTranscriptBuilder()

        assertAll {
            assertThat(transcriptBuilder).prop(EpisodePodcastindexTranscriptBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(transcriptBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build an Episode Podcastindex Transcript with when the url field is missing`() {
        val transcriptBuilder = ValidatingEpisodePodcastindexTranscriptBuilder()
            .type(TranscriptType.PLAIN_TEXT)

        assertAll {
            assertThat(transcriptBuilder).prop(EpisodePodcastindexTranscriptBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(transcriptBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build an Episode Podcastindex Transcript with when the type field is missing`() {
        val transcriptBuilder = ValidatingEpisodePodcastindexTranscriptBuilder()
            .url("https://example.com/episode/transcript.txt")

        assertAll {
            assertThat(transcriptBuilder).prop(EpisodePodcastindexTranscriptBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(transcriptBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode Podcastindex Transcript with with all the added entries to its fields`() {
        val transcriptBuilder = ValidatingEpisodePodcastindexTranscriptBuilder()
            .url("https://example.com/episode/transcript.txt")
            .type(TranscriptType.PLAIN_TEXT)
            .language(Locale.ITALIAN)
            .rel("captions")

        assertAll {
            assertThat(transcriptBuilder).prop(EpisodePodcastindexTranscriptBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(transcriptBuilder.build()).isNotNull().all {
                prop(Transcript::url).isEqualTo("https://example.com/episode/transcript.txt")
                prop(Transcript::type).isEqualTo(TranscriptType.PLAIN_TEXT)
                prop(Transcript::language).isEqualTo(Locale.ITALIAN)
                prop(Transcript::rel).isEqualTo("captions")
            }
        }
    }

    @Test
    internal fun `should populate an Episode Podcastindex Transcript builder with all properties from an Episode Podcastindex Transcript model`() {
        val episodePodcastTranscript = anEpisodePodcastindexTranscript()
        val episodePodcastTranscriptBuilder = Transcript.builder().applyFrom(episodePodcastTranscript)

        assertAll {
            assertThat(episodePodcastTranscriptBuilder).prop(EpisodePodcastindexTranscriptBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodePodcastTranscriptBuilder.build()).isNotNull().isEqualTo(episodePodcastTranscript)
        }
    }
}
