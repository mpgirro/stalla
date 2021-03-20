package dev.stalla.builder.validating.episode

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import dev.stalla.builder.episode.EpisodeContentBuilder
import dev.stalla.model.anEpisodeContent
import dev.stalla.model.content.Content
import org.junit.jupiter.api.Test

internal class ValidatingEpisodeContentBuilderTest {

    @Test
    internal fun `should not build an Episode Content when the mandatory fields are missing`() {
        val episodeContentBuilder = ValidatingEpisodeContentBuilder()

        assertAll {
            assertThat(episodeContentBuilder).prop(EpisodeContentBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodeContentBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode Content with all the mandatory fields`() {
        val episodeContentBuilder = ValidatingEpisodeContentBuilder()
            .encoded("encoded")

        assertAll {
            assertThat(episodeContentBuilder).prop(EpisodeContentBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeContentBuilder.build()).isNotNull().prop(Content::encoded).isEqualTo("encoded")
        }
    }

    @Test
    internal fun `should populate an Episode Bitlove builder with all properties from an Episode Bitlove model`() {
        val episodeContent = anEpisodeContent()
        val episodeContentBuilder = Content.builder().applyFrom(episodeContent)

        assertAll {
            assertThat(episodeContentBuilder).prop(EpisodeContentBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeContentBuilder.build()).isNotNull().isEqualTo(episodeContent)
        }
    }
}
