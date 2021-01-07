package io.hemin.wien.builder.validating.episode

import assertk.all
import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import io.hemin.wien.builder.episode.EpisodeGooglePlayBuilder
import io.hemin.wien.builder.episode.EpisodeGuidBuilder
import io.hemin.wien.builder.validating.ValidatingHrefOnlyImageBuilder
import io.hemin.wien.model.Episode
import org.junit.jupiter.api.Test

internal class ValidatingEpisodeGooglePlayBuilderTest {

    private val expectedImageBuilder = ValidatingHrefOnlyImageBuilder().href("image href")

    @Test
    internal fun `should not build an Episode GooglePlay when all fields are missing`() {
        val episodeGooglePlayBuilder = ValidatingEpisodeGooglePlayBuilder()

        assertAll {
            assertThat(episodeGooglePlayBuilder).prop(EpisodeGooglePlayBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodeGooglePlayBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode GooglePlay with only a description`() {
        val episodeGooglePlayBuilder = ValidatingEpisodeGooglePlayBuilder()
            .description("description")

        assertAll {
            assertThat(episodeGooglePlayBuilder).prop(EpisodeGooglePlayBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeGooglePlayBuilder.build()).isNotNull().all {
                prop(Episode.GooglePlay::description).isEqualTo("description")
                prop(Episode.GooglePlay::explicit).isNull()
                prop(Episode.GooglePlay::block).isNull()
                prop(Episode.GooglePlay::image).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode GooglePlay with only explicit`() {
        val episodeGooglePlayBuilder = ValidatingEpisodeGooglePlayBuilder()
            .explicit(false)

        assertAll {
            assertThat(episodeGooglePlayBuilder).prop(EpisodeGooglePlayBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeGooglePlayBuilder.build()).isNotNull().all {
                prop(Episode.GooglePlay::description).isNull()
                prop(Episode.GooglePlay::explicit).isNotNull().isFalse()
                prop(Episode.GooglePlay::block).isNull()
                prop(Episode.GooglePlay::image).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode GooglePlay with only block`() {
        val episodeGooglePlayBuilder = ValidatingEpisodeGooglePlayBuilder()
            .block(false)

        assertAll {
            assertThat(episodeGooglePlayBuilder).prop(EpisodeGooglePlayBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeGooglePlayBuilder.build()).isNotNull().all {
                prop(Episode.GooglePlay::description).isNull()
                prop(Episode.GooglePlay::explicit).isNull()
                prop(Episode.GooglePlay::block).isNotNull().isFalse()
                prop(Episode.GooglePlay::image).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode GooglePlay with only an image`() {
        val episodeGooglePlayBuilder = ValidatingEpisodeGooglePlayBuilder()
            .imageBuilder(expectedImageBuilder)

        assertAll {
            assertThat(episodeGooglePlayBuilder).prop(EpisodeGooglePlayBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeGooglePlayBuilder.build()).isNotNull().all {
                prop(Episode.GooglePlay::description).isNull()
                prop(Episode.GooglePlay::explicit).isNull()
                prop(Episode.GooglePlay::block).isNull()
                prop(Episode.GooglePlay::image).isEqualTo(expectedImageBuilder.build())
            }
        }
    }

    @Test
    internal fun `should build an Episode GooglePlay with all the optional fields`() {
        val episodeGooglePlayBuilder = ValidatingEpisodeGooglePlayBuilder()
            .description("description")
            .explicit(false)
            .block(false)
            .imageBuilder(expectedImageBuilder)

        assertAll {
            assertThat(episodeGooglePlayBuilder).prop(EpisodeGooglePlayBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeGooglePlayBuilder.build()).isNotNull().all {
                prop(Episode.GooglePlay::description).isEqualTo("description")
                prop(Episode.GooglePlay::explicit).isNotNull().isFalse()
                prop(Episode.GooglePlay::block).isNotNull().isFalse()
                prop(Episode.GooglePlay::image).isEqualTo(expectedImageBuilder.build())
            }
        }
    }
}
