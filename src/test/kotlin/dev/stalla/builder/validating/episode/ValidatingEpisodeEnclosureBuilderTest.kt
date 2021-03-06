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
import dev.stalla.builder.episode.EpisodeEnclosureBuilder
import dev.stalla.model.MediaType
import dev.stalla.model.anEpisodeEnclosure
import dev.stalla.model.rss.Enclosure
import org.junit.jupiter.api.Test

internal class ValidatingEpisodeEnclosureBuilderTest {

    @Test
    internal fun `should not build an Episode Enclosure when the mandatory fields are missing`() {
        val episodeEnclosureBuilder = ValidatingEpisodeEnclosureBuilder()

        assertAll {
            assertThat(episodeEnclosureBuilder).prop(EpisodeEnclosureBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodeEnclosureBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build an Episode Enclosure when the mandatory url is missing`() {
        val episodeEnclosureBuilder = ValidatingEpisodeEnclosureBuilder()
            .type(MediaType.MPEG_AUDIO)
            .length(123)

        assertAll {
            assertThat(episodeEnclosureBuilder).prop(EpisodeEnclosureBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodeEnclosureBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build an Episode Enclosure when the mandatory type is missing`() {
        val episodeEnclosureBuilder = ValidatingEpisodeEnclosureBuilder()
            .url("url")
            .length(123)

        assertAll {
            assertThat(episodeEnclosureBuilder).prop(EpisodeEnclosureBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodeEnclosureBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build an Episode Enclosure when the mandatory length is missing`() {
        val episodeEnclosureBuilder = ValidatingEpisodeEnclosureBuilder()
            .url("url")
            .type(MediaType.MPEG_AUDIO)

        assertAll {
            assertThat(episodeEnclosureBuilder).prop(EpisodeEnclosureBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodeEnclosureBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode Enclosure with all the mandatory fields`() {
        val episodeEnclosureBuilder = ValidatingEpisodeEnclosureBuilder()
            .url("url")
            .type(MediaType.MPEG_AUDIO)
            .length(123)

        assertAll {
            assertThat(episodeEnclosureBuilder).prop(EpisodeEnclosureBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeEnclosureBuilder.build()).isNotNull().all {
                prop(Enclosure::url).isEqualTo("url")
                prop(Enclosure::type).isEqualTo(MediaType.MPEG_AUDIO)
                prop(Enclosure::length).isEqualTo(123)
            }
        }
    }

    @Test
    internal fun `should populate an Episode Enclosure builder with all properties from an Episode Enclosure model`() {
        val episodeEnclosure = anEpisodeEnclosure()
        val episodeEnclosureBuilder = Enclosure.builder().applyFrom(episodeEnclosure)

        assertAll {
            assertThat(episodeEnclosureBuilder).prop(EpisodeEnclosureBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeEnclosureBuilder.build()).isNotNull().isEqualTo(episodeEnclosure)
        }
    }
}
