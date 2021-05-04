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
import dev.stalla.builder.episode.EpisodePodcastindexSoundbiteBuilder
import dev.stalla.model.StyledDuration
import dev.stalla.model.anEpisodePodcastindexSoundbite
import dev.stalla.model.podcastindex.Soundbite
import org.junit.jupiter.api.Test

internal class ValidatingEpisodePodcastindexSoundbiteBuilderTest {

    @Test
    internal fun `should not build an Episode Podcastindex Soundbite with when all the fields are missing`() {
        val soundbiteBuilder = ValidatingEpisodePodcastindexSoundbiteBuilder()

        assertAll {
            assertThat(soundbiteBuilder).prop(EpisodePodcastindexSoundbiteBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(soundbiteBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build an Episode Podcastindex Soundbite with when the startTime field is missing`() {
        val soundbiteBuilder = ValidatingEpisodePodcastindexSoundbiteBuilder()
            .duration(StyledDuration.secondsAndFraction(15))

        assertAll {
            assertThat(soundbiteBuilder).prop(EpisodePodcastindexSoundbiteBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(soundbiteBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build an Episode Podcastindex Soundbite with when the startTime field is negative`() {
        val soundbiteBuilder = ValidatingEpisodePodcastindexSoundbiteBuilder()
            .startTime(StyledDuration.secondsAndFraction(1, positive = false))
            .duration(StyledDuration.secondsAndFraction(15))

        assertAll {
            assertThat(soundbiteBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build an Episode Podcastindex Soundbite with when the duration field is missing`() {
        val soundbiteBuilder = ValidatingEpisodePodcastindexSoundbiteBuilder()
            .startTime(StyledDuration.secondsAndFraction(1))

        assertAll {
            assertThat(soundbiteBuilder).prop(EpisodePodcastindexSoundbiteBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(soundbiteBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build an Episode Podcastindex Soundbite with when the duration field is zero`() {
        val soundbiteBuilder = ValidatingEpisodePodcastindexSoundbiteBuilder()
            .startTime(StyledDuration.secondsAndFraction(1))
            .duration(StyledDuration.secondsAndFraction())

        assertAll {
            assertThat(soundbiteBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build an Episode Podcastindex Soundbite with when the duration field is negative`() {
        val soundbiteBuilder = ValidatingEpisodePodcastindexSoundbiteBuilder()
            .startTime(StyledDuration.secondsAndFraction(1))
            .duration(StyledDuration.secondsAndFraction(1, positive = false))

        assertAll {
            assertThat(soundbiteBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode Podcastindex Soundbite with all the added entries to its fields`() {
        val soundbiteBuilder = ValidatingEpisodePodcastindexSoundbiteBuilder()
            .startTime(StyledDuration.secondsAndFraction(1))
            .duration(StyledDuration.secondsAndFraction(15))
            .title("soundbite")

        assertAll {
            assertThat(soundbiteBuilder).prop(EpisodePodcastindexSoundbiteBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(soundbiteBuilder.build()).isNotNull().all {
                prop(Soundbite::startTime).isEqualTo(StyledDuration.secondsAndFraction(1))
                prop(Soundbite::duration).isEqualTo(StyledDuration.secondsAndFraction(15))
                prop(Soundbite::title).isEqualTo("soundbite")
            }
        }
    }

    @Test
    internal fun `should populate an Episode Podcastindex Soundbite builder with all properties from an Episode Podcastindex Soundbite model`() {
        val episodePodcastSoundbite = anEpisodePodcastindexSoundbite()
        val episodePodcastSoundbiteBuilder = Soundbite.builder().applyFrom(episodePodcastSoundbite)

        assertAll {
            assertThat(episodePodcastSoundbiteBuilder).prop(EpisodePodcastindexSoundbiteBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodePodcastSoundbiteBuilder.build()).isNotNull().isEqualTo(episodePodcastSoundbite)
        }
    }
}
