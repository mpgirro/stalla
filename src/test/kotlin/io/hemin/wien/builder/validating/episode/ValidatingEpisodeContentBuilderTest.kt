package io.hemin.wien.builder.validating.episode

import assertk.all
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import io.hemin.wien.model.Episode
import org.junit.jupiter.api.Test

internal class ValidatingEpisodeContentBuilderTest {

    @Test
    internal fun `should not build an Episode Content when the mandatory fields are missing`() {
        val episodeContentBuilder = ValidatingEpisodeContentBuilder()

        assertThat(episodeContentBuilder.build()).isNull()
    }

    @Test
    internal fun `should build an Episode Content with all the mandatory fields`() {
        val episodeContentBuilder = ValidatingEpisodeContentBuilder()
            .encoded("encoded")

        assertThat(episodeContentBuilder.build()).isNotNull().all {
            prop(Episode.Content::encoded).isEqualTo("encoded")
        }
    }
}
