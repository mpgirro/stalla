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
import dev.stalla.builder.episode.EpisodeGoogleplayBuilder
import dev.stalla.builder.validating.ValidatingHrefOnlyImageBuilder
import dev.stalla.model.episode.anEpisodeGoogleplay
import dev.stalla.model.googleplay.EpisodeGoogleplay
import dev.stalla.model.googleplay.ExplicitType
import org.junit.jupiter.api.Test

internal class ValidatingEpisodeGoogleplayBuilderTest {

    private val expectedImageBuilder = ValidatingHrefOnlyImageBuilder().href("image href")

    @Test
    internal fun `should not build an Episode GooglePlay when all fields are missing`() {
        val episodeGooglePlayBuilder = ValidatingEpisodeGoogleplayBuilder()

        assertAll {
            assertThat(episodeGooglePlayBuilder).prop(EpisodeGoogleplayBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodeGooglePlayBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode GooglePlay with only an author`() {
        val episodeGooglePlayBuilder = ValidatingEpisodeGoogleplayBuilder()
            .author("author")

        assertAll {
            assertThat(episodeGooglePlayBuilder).prop(EpisodeGoogleplayBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeGooglePlayBuilder.build()).isNotNull().all {
                prop(EpisodeGoogleplay::author).isEqualTo("author")
                prop(EpisodeGoogleplay::description).isNull()
                prop(EpisodeGoogleplay::explicit).isNull()
                prop(EpisodeGoogleplay::block).isFalse()
                prop(EpisodeGoogleplay::image).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode GooglePlay with only a description`() {
        val episodeGooglePlayBuilder = ValidatingEpisodeGoogleplayBuilder()
            .description("description")

        assertAll {
            assertThat(episodeGooglePlayBuilder).prop(EpisodeGoogleplayBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeGooglePlayBuilder.build()).isNotNull().all {
                prop(EpisodeGoogleplay::description).isEqualTo("description")
                prop(EpisodeGoogleplay::explicit).isNull()
                prop(EpisodeGoogleplay::block).isFalse()
                prop(EpisodeGoogleplay::image).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode GooglePlay with only explicit`() {
        val episodeGooglePlayBuilder = ValidatingEpisodeGoogleplayBuilder()
            .explicit("no")

        assertAll {
            assertThat(episodeGooglePlayBuilder).prop(EpisodeGoogleplayBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeGooglePlayBuilder.build()).isNotNull().all {
                prop(EpisodeGoogleplay::description).isNull()
                prop(EpisodeGoogleplay::explicit).isNotNull().isEqualTo(ExplicitType.NO)
                prop(EpisodeGoogleplay::block).isFalse()
                prop(EpisodeGoogleplay::image).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode GooglePlay with only block`() {
        val episodeGooglePlayBuilder = ValidatingEpisodeGoogleplayBuilder()
            .block(true)

        assertAll {
            assertThat(episodeGooglePlayBuilder).prop(EpisodeGoogleplayBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeGooglePlayBuilder.build()).isNotNull().all {
                prop(EpisodeGoogleplay::description).isNull()
                prop(EpisodeGoogleplay::explicit).isNull()
                prop(EpisodeGoogleplay::block).isNotNull().isTrue()
                prop(EpisodeGoogleplay::image).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode GooglePlay with only an image`() {
        val episodeGooglePlayBuilder = ValidatingEpisodeGoogleplayBuilder()
            .imageBuilder(expectedImageBuilder)

        assertAll {
            assertThat(episodeGooglePlayBuilder).prop(EpisodeGoogleplayBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeGooglePlayBuilder.build()).isNotNull().all {
                prop(EpisodeGoogleplay::description).isNull()
                prop(EpisodeGoogleplay::explicit).isNull()
                prop(EpisodeGoogleplay::block).isFalse()
                prop(EpisodeGoogleplay::image).isEqualTo(expectedImageBuilder.build())
            }
        }
    }

    @Test
    internal fun `should build an Episode GooglePlay with all the optional fields`() {
        val episodeGooglePlayBuilder = ValidatingEpisodeGoogleplayBuilder()
            .description("description")
            .explicit("no")
            .block(false)
            .imageBuilder(expectedImageBuilder)

        assertAll {
            assertThat(episodeGooglePlayBuilder).prop(EpisodeGoogleplayBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeGooglePlayBuilder.build()).isNotNull().all {
                prop(EpisodeGoogleplay::description).isEqualTo("description")
                prop(EpisodeGoogleplay::explicit).isNotNull().isEqualTo(ExplicitType.NO)
                prop(EpisodeGoogleplay::block).isNotNull().isFalse()
                prop(EpisodeGoogleplay::image).isEqualTo(expectedImageBuilder.build())
            }
        }
    }

    @Test
    internal fun `should populate an Episode GooglePlay builder with all properties from an Episode GooglePlay model`() {
        val episodeGooglePlay = anEpisodeGoogleplay()
        val episodeGooglePlayBuilder = EpisodeGoogleplay.builder().from(episodeGooglePlay)

        assertAll {
            assertThat(episodeGooglePlayBuilder).prop(EpisodeGoogleplayBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeGooglePlayBuilder.build()).isNotNull().isEqualTo(episodeGooglePlay)
        }
    }
}
