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
import dev.stalla.builder.episode.EpisodeGuidBuilder
import dev.stalla.model.rss.Guid
import dev.stalla.model.episode.anEpisodeGuid
import org.junit.jupiter.api.Test

internal class ValidatingEpisodeGuidBuilderTest {

    @Test
    internal fun `should not build an Episode Guid when all fields are missing`() {
        val episodeGuidBuilder = ValidatingEpisodeGuidBuilder()

        assertAll {
            assertThat(episodeGuidBuilder).prop(EpisodeGuidBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodeGuidBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode Guid with only a textContent`() {
        val episodeGuidBuilder = ValidatingEpisodeGuidBuilder()
            .textContent("textContent")

        assertAll {
            assertThat(episodeGuidBuilder).prop(EpisodeGuidBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeGuidBuilder.build()).isNotNull().all {
                prop(Guid::guid).isEqualTo("textContent")
                prop(Guid::isPermalink).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode Guid with all the optional fields`() {
        val episodeGuidBuilder = ValidatingEpisodeGuidBuilder()
            .textContent("textContent")
            .isPermalink(true)

        assertAll {
            assertThat(episodeGuidBuilder).prop(EpisodeGuidBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeGuidBuilder.build()).isNotNull().all {
                prop(Guid::guid).isEqualTo("textContent")
                prop(Guid::isPermalink).isNotNull().isTrue()
            }
        }
    }

    @Test
    internal fun `should populate an Episode Guid builder with all properties from an Episode Guid model`() {
        val episodeGuid = anEpisodeGuid()
        val episodeGuidBuilder = Guid.builder().from(episodeGuid)

        assertAll {
            assertThat(episodeGuidBuilder).prop(EpisodeGuidBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeGuidBuilder.build()).isNotNull().isEqualTo(episodeGuid)
        }
    }
}
