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
import dev.stalla.builder.validating.ValidatingPodcastindexLocationBuilder
import dev.stalla.builder.validating.ValidatingPodcastindexPersonBuilder
import dev.stalla.model.MediaType
import dev.stalla.model.StyledDuration
import dev.stalla.model.anEpisodePodcastindex
import dev.stalla.model.podcastindex.EpisodePodcastindex
import dev.stalla.model.podcastindex.GeographicLocation
import dev.stalla.model.podcastindex.OpenStreetMapElement
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

    private val firstExpectedPersonBuilder = ValidatingPodcastindexPersonBuilder()
        .name("First name")
        .role("First role")
        .group("First group")
        .img("First img")
        .href("First href")

    private val secondExpectedPersonBuilder = ValidatingPodcastindexPersonBuilder()
        .name("Second name")
        .role("Second role")
        .group("Second group")
        .img("Second img")
        .href("Second href")

    private val expectedLocationBuilder = ValidatingPodcastindexLocationBuilder()
        .name("Location name")
        .geo(GeographicLocation.of("geo:1,2,3"))
        .osm(OpenStreetMapElement.of("R123"))

    private val expectedSeasonBuilder = ValidatingEpisodePodcastindexSeasonBuilder()
        .number(1)
        .name("Season name")

    private val expectedEpisodeBuilder = ValidatingEpisodePodcastindexEpisodeBuilder()
        .number(1.0)
        .display("Episode display")

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
    internal fun `should not build an Episode Podcastindex when there is only a chapters builder that doesn't build`() {
        val episodePodcastindexBuilder = ValidatingEpisodePodcastindexBuilder()
            .chaptersBuilder(ValidatingEpisodePodcastindexChaptersBuilder())

        assertAll {
            assertThat(episodePodcastindexBuilder).prop(EpisodePodcastindexBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodePodcastindexBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode Podcastindex with at least one soundbite builder`() {
        val episodePodcastindexBuilder = ValidatingEpisodePodcastindexBuilder()
            .addSoundbiteBuilder(firstExpectedSoundbiteBuilder)

        assertAll {
            assertThat(episodePodcastindexBuilder).prop(EpisodePodcastindexBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodePodcastindexBuilder.build()).isNotNull()
                .prop(EpisodePodcastindex::soundbites).containsExactly(firstExpectedSoundbiteBuilder.build())
        }
    }

    @Test
    internal fun `should not build an Episode Podcastindex when there is only a soundbite builder that doesn't build`() {
        val episodePodcastindexBuilder = ValidatingEpisodePodcastindexBuilder()
            .addSoundbiteBuilder(ValidatingEpisodePodcastindexSoundbiteBuilder())

        assertAll {
            assertThat(episodePodcastindexBuilder).prop(EpisodePodcastindexBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodePodcastindexBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode Podcastindex with at least a transcript builder`() {
        val episodePodcastindexBuilder = ValidatingEpisodePodcastindexBuilder()
            .addTranscriptBuilder(firstExpectedTranscriptBuilder)

        assertAll {
            assertThat(episodePodcastindexBuilder).prop(EpisodePodcastindexBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodePodcastindexBuilder.build()).isNotNull()
                .prop(EpisodePodcastindex::transcripts).containsExactly(firstExpectedTranscriptBuilder.build())
        }
    }

    @Test
    internal fun `should not build an Episode Podcastindex when there is only a transcript builder that doesn't build`() {
        val episodePodcastindexBuilder = ValidatingEpisodePodcastindexBuilder()
            .addTranscriptBuilder(ValidatingEpisodePodcastindexTranscriptBuilder())

        assertAll {
            assertThat(episodePodcastindexBuilder).prop(EpisodePodcastindexBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodePodcastindexBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode Podcastindex with at least one valid person builder`() {
        val episodePodcastindexBuilder = ValidatingEpisodePodcastindexBuilder()
            .addPersonBuilder(firstExpectedPersonBuilder)

        assertAll {
            assertThat(episodePodcastindexBuilder).prop(EpisodePodcastindexBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodePodcastindexBuilder.build()).isNotNull()
                .prop(EpisodePodcastindex::persons).containsExactly(firstExpectedPersonBuilder.build())
        }
    }

    @Test
    internal fun `should not build an Episode Podcastindex with only a person builder that doesn't build`() {
        val episodePodcastindexBuilder = ValidatingEpisodePodcastindexBuilder()
            .addPersonBuilder(ValidatingPodcastindexPersonBuilder())

        assertAll {
            assertThat(episodePodcastindexBuilder).prop(EpisodePodcastindexBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodePodcastindexBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode Podcastindex with only a valid location builder`() {
        val episodePodcastindexBuilder = ValidatingEpisodePodcastindexBuilder()
            .locationBuilder(expectedLocationBuilder)

        assertAll {
            assertThat(episodePodcastindexBuilder).prop(EpisodePodcastindexBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodePodcastindexBuilder.build()).isNotNull()
                .prop(EpisodePodcastindex::location).isEqualTo(expectedLocationBuilder.build())
        }
    }

    @Test
    internal fun `should not build an Episode Podcastindex with only a location builder that doesn't build`() {
        val episodePodcastindexBuilder = ValidatingEpisodePodcastindexBuilder()
            .locationBuilder(ValidatingPodcastindexLocationBuilder())

        assertAll {
            assertThat(episodePodcastindexBuilder).prop(EpisodePodcastindexBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodePodcastindexBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode Podcastindex with only a valid season builder`() {
        val episodePodcastindexBuilder = ValidatingEpisodePodcastindexBuilder()
            .seasonBuilder(expectedSeasonBuilder)

        assertAll {
            assertThat(episodePodcastindexBuilder).prop(EpisodePodcastindexBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodePodcastindexBuilder.build()).isNotNull()
                .prop(EpisodePodcastindex::season).isEqualTo(expectedSeasonBuilder.build())
        }
    }

    @Test
    internal fun `should not build an Episode Podcastindex with only a season builder that doesn't build`() {
        val episodePodcastindexBuilder = ValidatingEpisodePodcastindexBuilder()
            .seasonBuilder(ValidatingEpisodePodcastindexSeasonBuilder())

        assertAll {
            assertThat(episodePodcastindexBuilder).prop(EpisodePodcastindexBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodePodcastindexBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode Podcastindex with only a valid episode builder`() {
        val episodePodcastindexBuilder = ValidatingEpisodePodcastindexBuilder()
            .episodeBuilder(expectedEpisodeBuilder)

        assertAll {
            assertThat(episodePodcastindexBuilder).prop(EpisodePodcastindexBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodePodcastindexBuilder.build()).isNotNull()
                .prop(EpisodePodcastindex::episode).isEqualTo(expectedEpisodeBuilder.build())
        }
    }

    @Test
    internal fun `should not build an Episode Podcastindex with only an episode builder that doesn't build`() {
        val episodePodcastindexBuilder = ValidatingEpisodePodcastindexBuilder()
            .episodeBuilder(ValidatingEpisodePodcastindexEpisodeBuilder())

        assertAll {
            assertThat(episodePodcastindexBuilder).prop(EpisodePodcastindexBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodePodcastindexBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode Podcastindex with with all the added entries to its fields`() {
        val episodePodcastindexBuilder = ValidatingEpisodePodcastindexBuilder()
            .chaptersBuilder(expectedChaptersBuilder)
            .addSoundbiteBuilder(firstExpectedSoundbiteBuilder)
            .addSoundbiteBuilder(secondExpectedSoundbiteBuilder)
            .addTranscriptBuilder(firstExpectedTranscriptBuilder)
            .addTranscriptBuilder(secondExpectedTranscriptBuilder)
            .addPersonBuilder(firstExpectedPersonBuilder)
            .addPersonBuilder(secondExpectedPersonBuilder)
            .locationBuilder(expectedLocationBuilder)
            .seasonBuilder(expectedSeasonBuilder)
            .episodeBuilder(expectedEpisodeBuilder)

        assertAll {
            assertThat(episodePodcastindexBuilder).prop(EpisodePodcastindexBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodePodcastindexBuilder.build()).isNotNull().all {
                prop(EpisodePodcastindex::chapters).isEqualTo(expectedChaptersBuilder.build())
                prop(EpisodePodcastindex::soundbites).containsExactly(firstExpectedSoundbiteBuilder.build(), secondExpectedSoundbiteBuilder.build())
                prop(EpisodePodcastindex::transcripts)
                    .containsExactly(firstExpectedTranscriptBuilder.build(), secondExpectedTranscriptBuilder.build())
                prop(EpisodePodcastindex::persons).containsExactly(firstExpectedPersonBuilder.build(), secondExpectedPersonBuilder.build())
                prop(EpisodePodcastindex::location).isEqualTo(expectedLocationBuilder.build())
                prop(EpisodePodcastindex::season).isEqualTo(expectedSeasonBuilder.build())
                prop(EpisodePodcastindex::episode).isEqualTo(expectedEpisodeBuilder.build())
            }
        }
    }

    @Test
    internal fun `should populate an Episode Podcastindex builder with all properties from an Episode Podcastindex model`() {
        val episodePodcastindex = anEpisodePodcastindex()
        val episodePodcastindexBuilder = EpisodePodcastindex.builder().applyFrom(episodePodcastindex)

        assertAll {
            assertThat(episodePodcastindexBuilder).prop(EpisodePodcastindexBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodePodcastindexBuilder.build()).isNotNull().isEqualTo(episodePodcastindex)
        }
    }
}
