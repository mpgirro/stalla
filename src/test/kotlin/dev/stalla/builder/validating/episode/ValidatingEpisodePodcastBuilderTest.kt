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
import dev.stalla.builder.episode.EpisodePodcastBuilder
import dev.stalla.model.Episode
import dev.stalla.model.episode.anEpisodePodcast
import org.junit.jupiter.api.Test
import java.time.Duration
import java.util.Locale

internal class ValidatingEpisodePodcastBuilderTest {

    private val expectedChaptersBuilder = ValidatingEpisodePodcastChaptersBuilder()
        .url("https://example.com/episode/chapters.json")
        .type("application/json+chapters")

    private val firstExpectedSoundbiteBuilder = ValidatingEpisodePodcastSoundbiteBuilder()
        .startTime(Duration.ofSeconds(1))
        .duration(Duration.ofSeconds(15))
        .title("First soundbite")

    private val secondExpectedSoundbiteBuilder = ValidatingEpisodePodcastSoundbiteBuilder()
        .startTime(Duration.ofSeconds(2))
        .duration(Duration.ofSeconds(12))
        .title("Second soundbite")

    private val firstExpectedTranscriptBuilder = ValidatingEpisodePodcastTranscriptBuilder()
        .url("https://example.com/episode/transcript.srt")
        .type(Episode.Podcast.Transcript.Type.SRT)
        .language(Locale.ITALIAN)
        .rel("captions")

    private val secondExpectedTranscriptBuilder = ValidatingEpisodePodcastTranscriptBuilder()
        .url("https://example.com/episode/transcript.txt")
        .type(Episode.Podcast.Transcript.Type.PLAIN_TEXT)
        .language(Locale.ITALIAN)

    @Test
    internal fun `should not build an Episode Podcast with when all the fields are empty`() {
        val episodePodcastBuilder = ValidatingEpisodePodcastBuilder()

        assertAll {
            assertThat(episodePodcastBuilder).prop(EpisodePodcastBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodePodcastBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode Podcast with at least a chapters builder`() {
        val episodePodcastBuilder = ValidatingEpisodePodcastBuilder()
            .chaptersBuilder(expectedChaptersBuilder)

        assertAll {
            assertThat(episodePodcastBuilder).prop(EpisodePodcastBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodePodcastBuilder.build()).isNotNull().prop(Episode.Podcast::chapters).isEqualTo(expectedChaptersBuilder.build())
        }
    }

    @Test
    internal fun `should not build an Episode Podcast when there is only a chapters builder that doesn't build`() {
        val episodePodcastBuilder = ValidatingEpisodePodcastBuilder()
            .chaptersBuilder(ValidatingEpisodePodcastChaptersBuilder())

        assertAll {
            assertThat(episodePodcastBuilder).prop(EpisodePodcastBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodePodcastBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode Podcast with at least one soundbite builder`() {
        val episodePodcastBuilder = ValidatingEpisodePodcastBuilder()
            .addSoundbiteBuilder(firstExpectedSoundbiteBuilder)

        assertAll {
            assertThat(episodePodcastBuilder).prop(EpisodePodcastBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodePodcastBuilder.build()).isNotNull()
                .prop(Episode.Podcast::soundbites).containsExactly(firstExpectedSoundbiteBuilder.build())
        }
    }

    @Test
    internal fun `should not build an Episode Podcast when there is only a soundbite builder that doesn't build`() {
        val episodePodcastBuilder = ValidatingEpisodePodcastBuilder()
            .addSoundbiteBuilder(ValidatingEpisodePodcastSoundbiteBuilder())

        assertAll {
            assertThat(episodePodcastBuilder).prop(EpisodePodcastBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodePodcastBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode Podcast with at least a transcript builder`() {
        val episodePodcastBuilder = ValidatingEpisodePodcastBuilder()
            .addTranscriptBuilder(firstExpectedTranscriptBuilder)

        assertAll {
            assertThat(episodePodcastBuilder).prop(EpisodePodcastBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodePodcastBuilder.build()).isNotNull()
                .prop(Episode.Podcast::transcripts).containsExactly(firstExpectedTranscriptBuilder.build())
        }
    }

    @Test
    internal fun `should not build an Episode Podcast when there is only a transcript builder that doesn't build`() {
        val episodePodcastBuilder = ValidatingEpisodePodcastBuilder()
            .addTranscriptBuilder(ValidatingEpisodePodcastTranscriptBuilder())

        assertAll {
            assertThat(episodePodcastBuilder).prop(EpisodePodcastBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodePodcastBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode Podcast with with all the added entries to its fields`() {
        val episodePodcastBuilder = ValidatingEpisodePodcastBuilder()
            .chaptersBuilder(expectedChaptersBuilder)
            .addSoundbiteBuilder(firstExpectedSoundbiteBuilder)
            .addSoundbiteBuilder(secondExpectedSoundbiteBuilder)
            .addTranscriptBuilder(firstExpectedTranscriptBuilder)
            .addTranscriptBuilder(secondExpectedTranscriptBuilder)

        assertAll {
            assertThat(episodePodcastBuilder).prop(EpisodePodcastBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodePodcastBuilder.build()).isNotNull().all {
                prop(Episode.Podcast::chapters).isEqualTo(expectedChaptersBuilder.build())
                prop(Episode.Podcast::soundbites).containsExactly(firstExpectedSoundbiteBuilder.build(), secondExpectedSoundbiteBuilder.build())
                prop(Episode.Podcast::transcripts).containsExactly(firstExpectedTranscriptBuilder.build(), secondExpectedTranscriptBuilder.build())
            }
        }
    }

    @Test
    internal fun `should populate an Episode Podcastindex builder with all properties from an Episode Podcastindex model`() {
        val episodePodcast = anEpisodePodcast()
        val episodePodcastBuilder = Episode.Podcast.builder().from(episodePodcast)

        assertAll {
            assertThat(episodePodcastBuilder).prop(EpisodePodcastBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodePodcastBuilder.build()).isNotNull().isEqualTo(episodePodcast)
        }
    }
}
