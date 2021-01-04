package io.hemin.wien.builder.validating.episode

import assertk.all
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import io.hemin.wien.model.Episode
import org.junit.jupiter.api.Test

internal class ValidatingEpisodeEnclosureBuilderTest {

    @Test
    internal fun `should not build an Episode Enclosure when the mandatory fields are missing`() {
        val episodeEnclosureBuilder = ValidatingEpisodeEnclosureBuilder()

        assertThat(episodeEnclosureBuilder.build()).isNull()
    }

    @Test
    internal fun `should not build an Episode Enclosure when the mandatory url is missing`() {
        val episodeEnclosureBuilder = ValidatingEpisodeEnclosureBuilder()
            .type("type")
            .length(123)

        assertThat(episodeEnclosureBuilder.build()).isNull()
    }

    @Test
    internal fun `should not build an Episode Enclosure when the mandatory type is missing`() {
        val episodeEnclosureBuilder = ValidatingEpisodeEnclosureBuilder()
            .url("url")
            .length(123)

        assertThat(episodeEnclosureBuilder.build()).isNull()
    }

    @Test
    internal fun `should not build an Episode Enclosure when the mandatory length is missing`() {
        val episodeEnclosureBuilder = ValidatingEpisodeEnclosureBuilder()
            .url("url")
            .type("type")

        assertThat(episodeEnclosureBuilder.build()).isNull()
    }

    @Test
    internal fun `should build an Episode Enclosure with all the mandatory fields`() {
        val episodeEnclosureBuilder = ValidatingEpisodeEnclosureBuilder()
            .url("url")
            .type("type")
            .length(123)

        assertThat(episodeEnclosureBuilder.build()).isNotNull().all {
            prop(Episode.Enclosure::url).isEqualTo("url")
            prop(Episode.Enclosure::type).isEqualTo("type")
            prop(Episode.Enclosure::length).isEqualTo(123)
        }
    }
}
