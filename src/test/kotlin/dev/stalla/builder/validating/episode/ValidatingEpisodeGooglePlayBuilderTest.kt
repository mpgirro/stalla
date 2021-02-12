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
import dev.stalla.builder.episode.EpisodeGoogleplayBuilder2
import dev.stalla.builder.validating.ValidatingHrefOnlyImageBuilder
import dev.stalla.model.episode.anEpisodeGoogleplay
import dev.stalla.model.googleplay.EpisodeGoogleplay2
import org.junit.jupiter.api.Test

internal class ValidatingEpisodeGoogleplayBuilderTest {

    private val expectedImageBuilder = ValidatingHrefOnlyImageBuilder().href("image href")

    @Test
    internal fun `should not build an Episode GooglePlay when all fields are missing`() {
        val episodeGooglePlayBuilder = ValidatingEpisodeGoogleplayBuilder2()

        assertAll {
            assertThat(episodeGooglePlayBuilder).prop(EpisodeGoogleplayBuilder2::hasEnoughDataToBuild).isFalse()

            assertThat(episodeGooglePlayBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode GooglePlay with only a description`() {
        val episodeGooglePlayBuilder = ValidatingEpisodeGoogleplayBuilder2()
            .description("description")

        assertAll {
            assertThat(episodeGooglePlayBuilder).prop(EpisodeGoogleplayBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(episodeGooglePlayBuilder.build()).isNotNull().all {
                prop(EpisodeGoogleplay2::description).isEqualTo("description")
                prop(EpisodeGoogleplay2::explicit).isNull()
                prop(EpisodeGoogleplay2::block).isFalse()
                prop(EpisodeGoogleplay2::image).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode GooglePlay with only explicit`() {
        val episodeGooglePlayBuilder = ValidatingEpisodeGoogleplayBuilder2()
            .explicit(false)

        assertAll {
            assertThat(episodeGooglePlayBuilder).prop(EpisodeGoogleplayBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(episodeGooglePlayBuilder.build()).isNotNull().all {
                prop(EpisodeGoogleplay2::description).isNull()
                prop(EpisodeGoogleplay2::explicit).isNotNull().isFalse()
                prop(EpisodeGoogleplay2::block).isFalse()
                prop(EpisodeGoogleplay2::image).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode GooglePlay with only block`() {
        val episodeGooglePlayBuilder = ValidatingEpisodeGoogleplayBuilder2()
            .block(true)

        assertAll {
            assertThat(episodeGooglePlayBuilder).prop(EpisodeGoogleplayBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(episodeGooglePlayBuilder.build()).isNotNull().all {
                prop(EpisodeGoogleplay2::description).isNull()
                prop(EpisodeGoogleplay2::explicit).isNull()
                prop(EpisodeGoogleplay2::block).isNotNull().isTrue()
                prop(EpisodeGoogleplay2::image).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode GooglePlay with only an image`() {
        val episodeGooglePlayBuilder = ValidatingEpisodeGoogleplayBuilder2()
            .imageBuilder(expectedImageBuilder)

        assertAll {
            assertThat(episodeGooglePlayBuilder).prop(EpisodeGoogleplayBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(episodeGooglePlayBuilder.build()).isNotNull().all {
                prop(EpisodeGoogleplay2::description).isNull()
                prop(EpisodeGoogleplay2::explicit).isNull()
                prop(EpisodeGoogleplay2::block).isFalse()
                prop(EpisodeGoogleplay2::image).isEqualTo(expectedImageBuilder.build())
            }
        }
    }

    @Test
    internal fun `should build an Episode GooglePlay with all the optional fields`() {
        val episodeGooglePlayBuilder = ValidatingEpisodeGoogleplayBuilder2()
            .description("description")
            .explicit(false)
            .block(false)
            .imageBuilder(expectedImageBuilder)

        assertAll {
            assertThat(episodeGooglePlayBuilder).prop(EpisodeGoogleplayBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(episodeGooglePlayBuilder.build()).isNotNull().all {
                prop(EpisodeGoogleplay2::description).isEqualTo("description")
                prop(EpisodeGoogleplay2::explicit).isNotNull().isFalse()
                prop(EpisodeGoogleplay2::block).isNotNull().isFalse()
                prop(EpisodeGoogleplay2::image).isEqualTo(expectedImageBuilder.build())
            }
        }
    }

    @Test
    internal fun `should populate an Episode GooglePlay builder with all properties from an Episode GooglePlay model`() {
        val episodeGooglePlay = anEpisodeGoogleplay()
        val episodeGooglePlayBuilder = EpisodeGoogleplay2.builder().from(episodeGooglePlay)

        assertAll {
            assertThat(episodeGooglePlayBuilder).prop(EpisodeGoogleplayBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(episodeGooglePlayBuilder.build()).isNotNull().isEqualTo(episodeGooglePlay)
        }
    }
}
