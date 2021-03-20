package dev.stalla.builder.validating.episode

import assertk.all
import assertk.assertAll
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import dev.stalla.builder.episode.EpisodePodcastindexBuilder
import dev.stalla.model.MediaType
import dev.stalla.model.StyledDuration
import dev.stalla.model.anEpisodePodcastindex
import dev.stalla.model.podcastindex.EpisodePodcastindex
import dev.stalla.model.podcastindex.TranscriptType
import org.junit.jupiter.api.Test
import java.util.Locale

internal class ValidatingEpisodePodcastindexBuilderTest {

    private val expectedChaptersBuilder = ValidatingEpisodePodcastindexChaptersBuilder()
        .url("https://example.com/episode/chapters.json")
        .type(MediaType.JSON)

    private val firstExpectedSoundbiteBuilder = ValidatingEpisodePodcastindexSoundbiteBuilder()
        .startTime(StyledDuration.secondsAndFraction(1))
        .duration(StyledDuration.secondsAndFraction(15, 11))
        .title("First soundbite")

    private val secondExpectedSoundbiteBuilder = ValidatingEpisodePodcastindexSoundbiteBuilder()
        .startTime(StyledDuration.secondsAndFraction(2))
        .duration(StyledDuration.secondsAndFraction(12, 876786))
        .title("Second soundbite")

    private val firstExpectedTranscriptBuilder = ValidatingEpisodePodcastindexTranscriptBuilder()
        .url("https://example.com/episode/transcript.srt")
        .type(TranscriptType.SRT)
        .language(Locale.ITALIAN)
        .rel("captions")

    private val secondExpectedTranscriptBuilder = ValidatingEpisodePodcastindexTranscriptBuilder()
        .url("https://example.com/episode/transcript.txt")
        .type(TranscriptType.PLAIN_TEXT)
        .language(Locale.ITALIAN)

    @Test
    internal fun `should not build an Episode Podcastindex with when all the fields are empty`() {
        val episodePodcastBuilder = ValidatingEpisodePodcastindexBuilder()

        assertAll {
            assertThat(episodePodcastBuilder).prop(EpisodePodcastindexBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodePodcastBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode Podcastindex with at least a chapters builder`() {
        val episodePodcastBuilder = ValidatingEpisodePodcastindexBuilder()
            .chaptersBuilder(expectedChaptersBuilder)

        assertAll {
            assertThat(episodePodcastBuilder).prop(EpisodePodcastindexBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodePodcastBuilder.build()).isNotNull().prop(EpisodePodcastindex::chapters).isEqualTo(expectedChaptersBuilder.build())
        }
    }

    @Test
    internal fun `should not build an Episode Podcastindexx when there is only a chapters builder that doesn't build`() {
        val episodePodcastBuilder = ValidatingEpisodePodcastindexBuilder()
            .chaptersBuilder(ValidatingEpisodePodcastindexChaptersBuilder())

        assertAll {
            assertThat(episodePodcastBuilder).prop(EpisodePodcastindexBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodePodcastBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode Podcastindex with at least one soundbite builder`() {
        val episodePodcastBuilder = ValidatingEpisodePodcastindexBuilder()
            .addSoundbiteBuilder(firstExpectedSoundbiteBuilder)

        assertAll {
            assertThat(episodePodcastBuilder).prop(EpisodePodcastindexBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodePodcastBuilder.build()).isNotNull()
                .prop(EpisodePodcastindex::soundbites).containsExactly(firstExpectedSoundbiteBuilder.build())
        }
    }

    @Test
    internal fun `should not build an Episode Podcastindex when there is only a soundbite builder that doesn't build`() {
        val episodePodcastBuilder = ValidatingEpisodePodcastindexBuilder()
            .addSoundbiteBuilder(ValidatingEpisodePodcastindexSoundbiteBuilder())

        assertAll {
            assertThat(episodePodcastBuilder).prop(EpisodePodcastindexBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodePodcastBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode Podcastindex with at least a transcript builder`() {
        val episodePodcastBuilder = ValidatingEpisodePodcastindexBuilder()
            .addTranscriptBuilder(firstExpectedTranscriptBuilder)

        assertAll {
            assertThat(episodePodcastBuilder).prop(EpisodePodcastindexBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodePodcastBuilder.build()).isNotNull()
                .prop(EpisodePodcastindex::transcripts).containsExactly(firstExpectedTranscriptBuilder.build())
        }
    }

    @Test
    internal fun `should not build an Episode Podcastindex when there is only a transcript builder that doesn't build`() {
        val episodePodcastBuilder = ValidatingEpisodePodcastindexBuilder()
            .addTranscriptBuilder(ValidatingEpisodePodcastindexTranscriptBuilder())

        assertAll {
            assertThat(episodePodcastBuilder).prop(EpisodePodcastindexBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodePodcastBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode Podcastindex with with all the added entries to its fields`() {
        val episodePodcastBuilder = ValidatingEpisodePodcastindexBuilder()
            .chaptersBuilder(expectedChaptersBuilder)
            .addSoundbiteBuilder(firstExpectedSoundbiteBuilder)
            .addSoundbiteBuilder(secondExpectedSoundbiteBuilder)
            .addTranscriptBuilder(firstExpectedTranscriptBuilder)
            .addTranscriptBuilder(secondExpectedTranscriptBuilder)

        assertAll {
            assertThat(episodePodcastBuilder).prop(EpisodePodcastindexBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodePodcastBuilder.build()).isNotNull().all {
                prop(EpisodePodcastindex::chapters).isEqualTo(expectedChaptersBuilder.build())
                prop(EpisodePodcastindex::soundbites).containsExactly(firstExpectedSoundbiteBuilder.build(), secondExpectedSoundbiteBuilder.build())
                prop(EpisodePodcastindex::transcripts)
                    .containsExactly(firstExpectedTranscriptBuilder.build(), secondExpectedTranscriptBuilder.build())
            }
        }
    }

    @Test
    internal fun `should populate an Episode Podcastindex builder with all properties from an Episode Podcastindex model`() {
        val episodePodcast = anEpisodePodcastindex()
        val episodePodcastBuilder = EpisodePodcastindex.builder().applyFrom(episodePodcast)

        assertAll {
            assertThat(episodePodcastBuilder).prop(EpisodePodcastindexBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodePodcastBuilder.build()).isNotNull().isEqualTo(episodePodcast)
        }
    }
}
