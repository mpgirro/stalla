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
import dev.stalla.builder.episode.EpisodeItunesBuilder2
import dev.stalla.builder.validating.ValidatingHrefOnlyImageBuilder
import dev.stalla.model.episode.anEpisodeItunes
import dev.stalla.model.itunes.EpisodeItunes2
import dev.stalla.model.itunes.EpisodeType
import org.junit.jupiter.api.Test

internal class ValidatingEpisodeItunesBuilder2Test {

    private val expectedImageBuilder = ValidatingHrefOnlyImageBuilder().href("image href")

    @Test
    internal fun `should not build an Episode ITunes when all fields are missing`() {
        val episodeITunesBuilder = ValidatingEpisodeItunesBuilder2()

        assertAll {
            assertThat(episodeITunesBuilder).prop(EpisodeItunesBuilder2::hasEnoughDataToBuild).isFalse()

            assertThat(episodeITunesBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode ITunes with only a title`() {
        val episodeITunesBuilder = ValidatingEpisodeItunesBuilder2()
            .title("title")

        assertAll {
            assertThat(episodeITunesBuilder).prop(EpisodeItunesBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(episodeITunesBuilder.build()).isNotNull().all {
                prop(EpisodeItunes2::title).isEqualTo("title")
                prop(EpisodeItunes2::duration).isNull()
                prop(EpisodeItunes2::season).isNull()
                prop(EpisodeItunes2::episode).isNull()
                prop(EpisodeItunes2::episodeType).isNull()
                prop(EpisodeItunes2::explicit).isNull()
                prop(EpisodeItunes2::block).isFalse()
                prop(EpisodeItunes2::image).isNull()
                prop(EpisodeItunes2::author).isNull()
                prop(EpisodeItunes2::subtitle).isNull()
                prop(EpisodeItunes2::summary).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode ITunes with only a duration`() {
        val episodeITunesBuilder = ValidatingEpisodeItunesBuilder2()
            .duration("duration")

        assertAll {
            assertThat(episodeITunesBuilder).prop(EpisodeItunesBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(episodeITunesBuilder.build()).isNotNull().all {
                prop(EpisodeItunes2::title).isNull()
                prop(EpisodeItunes2::duration).isEqualTo("duration")
                prop(EpisodeItunes2::season).isNull()
                prop(EpisodeItunes2::episode).isNull()
                prop(EpisodeItunes2::episodeType).isNull()
                prop(EpisodeItunes2::explicit).isNull()
                prop(EpisodeItunes2::block).isFalse()
                prop(EpisodeItunes2::image).isNull()
                prop(EpisodeItunes2::author).isNull()
                prop(EpisodeItunes2::subtitle).isNull()
                prop(EpisodeItunes2::summary).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode ITunes with only a season number`() {
        val episodeITunesBuilder = ValidatingEpisodeItunesBuilder2()
            .season(2)

        assertAll {
            assertThat(episodeITunesBuilder).prop(EpisodeItunesBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(episodeITunesBuilder.build()).isNotNull().all {
                prop(EpisodeItunes2::title).isNull()
                prop(EpisodeItunes2::duration).isNull()
                prop(EpisodeItunes2::season).isEqualTo(2)
                prop(EpisodeItunes2::episode).isNull()
                prop(EpisodeItunes2::episodeType).isNull()
                prop(EpisodeItunes2::explicit).isNull()
                prop(EpisodeItunes2::block).isFalse()
                prop(EpisodeItunes2::image).isNull()
                prop(EpisodeItunes2::author).isNull()
                prop(EpisodeItunes2::subtitle).isNull()
                prop(EpisodeItunes2::summary).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode ITunes with only an episode number`() {
        val episodeITunesBuilder = ValidatingEpisodeItunesBuilder2()
            .episode(3)

        assertAll {
            assertThat(episodeITunesBuilder).prop(EpisodeItunesBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(episodeITunesBuilder.build()).isNotNull().all {
                prop(EpisodeItunes2::title).isNull()
                prop(EpisodeItunes2::duration).isNull()
                prop(EpisodeItunes2::season).isNull()
                prop(EpisodeItunes2::episode).isEqualTo(3)
                prop(EpisodeItunes2::episodeType).isNull()
                prop(EpisodeItunes2::explicit).isNull()
                prop(EpisodeItunes2::block).isFalse()
                prop(EpisodeItunes2::image).isNull()
                prop(EpisodeItunes2::author).isNull()
                prop(EpisodeItunes2::subtitle).isNull()
                prop(EpisodeItunes2::summary).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode ITunes with only an episodeType`() {
        val episodeITunesBuilder = ValidatingEpisodeItunesBuilder2()
            .episodeType(EpisodeType.BONUS.type)

        assertAll {
            assertThat(episodeITunesBuilder).prop(EpisodeItunesBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(episodeITunesBuilder.build()).isNotNull().all {
                prop(EpisodeItunes2::title).isNull()
                prop(EpisodeItunes2::duration).isNull()
                prop(EpisodeItunes2::season).isNull()
                prop(EpisodeItunes2::episode).isNull()
                prop(EpisodeItunes2::episodeType).isEqualTo(EpisodeType.BONUS)
                prop(EpisodeItunes2::explicit).isNull()
                prop(EpisodeItunes2::block).isFalse()
                prop(EpisodeItunes2::image).isNull()
                prop(EpisodeItunes2::author).isNull()
                prop(EpisodeItunes2::subtitle).isNull()
                prop(EpisodeItunes2::summary).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode ITunes with only explicit`() {
        val episodeITunesBuilder = ValidatingEpisodeItunesBuilder2()
            .explicit(false)

        assertAll {
            assertThat(episodeITunesBuilder).prop(EpisodeItunesBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(episodeITunesBuilder.build()).isNotNull().all {
                prop(EpisodeItunes2::title).isNull()
                prop(EpisodeItunes2::duration).isNull()
                prop(EpisodeItunes2::season).isNull()
                prop(EpisodeItunes2::episode).isNull()
                prop(EpisodeItunes2::episodeType).isNull()
                prop(EpisodeItunes2::explicit).isNotNull().isFalse()
                prop(EpisodeItunes2::block).isFalse()
                prop(EpisodeItunes2::image).isNull()
                prop(EpisodeItunes2::author).isNull()
                prop(EpisodeItunes2::subtitle).isNull()
                prop(EpisodeItunes2::summary).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode ITunes with only block`() {
        val episodeITunesBuilder = ValidatingEpisodeItunesBuilder2()
            .block(true)

        assertAll {
            assertThat(episodeITunesBuilder).prop(EpisodeItunesBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(episodeITunesBuilder.build()).isNotNull().all {
                prop(EpisodeItunes2::title).isNull()
                prop(EpisodeItunes2::duration).isNull()
                prop(EpisodeItunes2::season).isNull()
                prop(EpisodeItunes2::episode).isNull()
                prop(EpisodeItunes2::episodeType).isNull()
                prop(EpisodeItunes2::explicit).isNull()
                prop(EpisodeItunes2::block).isNotNull().isTrue()
                prop(EpisodeItunes2::image).isNull()
                prop(EpisodeItunes2::author).isNull()
                prop(EpisodeItunes2::subtitle).isNull()
                prop(EpisodeItunes2::summary).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode ITunes with only an image`() {
        val episodeITunesBuilder = ValidatingEpisodeItunesBuilder2()
            .imageBuilder(expectedImageBuilder)

        assertAll {
            assertThat(episodeITunesBuilder).prop(EpisodeItunesBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(episodeITunesBuilder.build()).isNotNull().all {
                prop(EpisodeItunes2::title).isNull()
                prop(EpisodeItunes2::duration).isNull()
                prop(EpisodeItunes2::season).isNull()
                prop(EpisodeItunes2::episode).isNull()
                prop(EpisodeItunes2::episodeType).isNull()
                prop(EpisodeItunes2::explicit).isNull()
                prop(EpisodeItunes2::block).isFalse()
                prop(EpisodeItunes2::image).isEqualTo(expectedImageBuilder.build())
                prop(EpisodeItunes2::author).isNull()
                prop(EpisodeItunes2::subtitle).isNull()
                prop(EpisodeItunes2::summary).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode ITunes with only an author`() {
        val episodeITunesBuilder = ValidatingEpisodeItunesBuilder2()
            .author("author")

        assertAll {
            assertThat(episodeITunesBuilder).prop(EpisodeItunesBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(episodeITunesBuilder.build()).isNotNull().all {
                prop(EpisodeItunes2::title).isNull()
                prop(EpisodeItunes2::duration).isNull()
                prop(EpisodeItunes2::season).isNull()
                prop(EpisodeItunes2::episode).isNull()
                prop(EpisodeItunes2::episodeType).isNull()
                prop(EpisodeItunes2::explicit).isNull()
                prop(EpisodeItunes2::block).isFalse()
                prop(EpisodeItunes2::image).isNull()
                prop(EpisodeItunes2::author).isEqualTo("author")
                prop(EpisodeItunes2::subtitle).isNull()
                prop(EpisodeItunes2::summary).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode ITunes with only a subtitle`() {
        val episodeITunesBuilder = ValidatingEpisodeItunesBuilder2()
            .subtitle("subtitle")

        assertAll {
            assertThat(episodeITunesBuilder).prop(EpisodeItunesBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(episodeITunesBuilder.build()).isNotNull().all {
                prop(EpisodeItunes2::title).isNull()
                prop(EpisodeItunes2::duration).isNull()
                prop(EpisodeItunes2::season).isNull()
                prop(EpisodeItunes2::episode).isNull()
                prop(EpisodeItunes2::episodeType).isNull()
                prop(EpisodeItunes2::explicit).isNull()
                prop(EpisodeItunes2::block).isFalse()
                prop(EpisodeItunes2::image).isNull()
                prop(EpisodeItunes2::author).isNull()
                prop(EpisodeItunes2::subtitle).isEqualTo("subtitle")
                prop(EpisodeItunes2::summary).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode ITunes with only a summary`() {
        val episodeITunesBuilder = ValidatingEpisodeItunesBuilder2()
            .summary("summary")

        assertAll {
            assertThat(episodeITunesBuilder).prop(EpisodeItunesBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(episodeITunesBuilder.build()).isNotNull().all {
                prop(EpisodeItunes2::title).isNull()
                prop(EpisodeItunes2::duration).isNull()
                prop(EpisodeItunes2::season).isNull()
                prop(EpisodeItunes2::episode).isNull()
                prop(EpisodeItunes2::episodeType).isNull()
                prop(EpisodeItunes2::explicit).isNull()
                prop(EpisodeItunes2::block).isFalse()
                prop(EpisodeItunes2::image).isNull()
                prop(EpisodeItunes2::author).isNull()
                prop(EpisodeItunes2::subtitle).isNull()
                prop(EpisodeItunes2::summary).isEqualTo("summary")
            }
        }
    }

    @Test
    internal fun `should build an Episode ITunes with all the optional fields`() {
        val episodeITunesBuilder = ValidatingEpisodeItunesBuilder2()
            .title("title")
            .duration("duration")
            .season(2)
            .episode(3)
            .episodeType(EpisodeType.BONUS.type)
            .explicit(false)
            .block(false)
            .imageBuilder(expectedImageBuilder)
            .author("author")
            .subtitle("subtitle")
            .summary("summary")

        assertAll {
            assertThat(episodeITunesBuilder).prop(EpisodeItunesBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(episodeITunesBuilder.build()).isNotNull().all {
                prop(EpisodeItunes2::title).isEqualTo("title")
                prop(EpisodeItunes2::duration).isEqualTo("duration")
                prop(EpisodeItunes2::season).isEqualTo(2)
                prop(EpisodeItunes2::episode).isEqualTo(3)
                prop(EpisodeItunes2::episodeType).isEqualTo(EpisodeType.BONUS)
                prop(EpisodeItunes2::explicit).isNotNull().isFalse()
                prop(EpisodeItunes2::block).isNotNull().isFalse()
                prop(EpisodeItunes2::image).isEqualTo(expectedImageBuilder.build())
                prop(EpisodeItunes2::author).isEqualTo("author")
                prop(EpisodeItunes2::subtitle).isEqualTo("subtitle")
                prop(EpisodeItunes2::summary).isEqualTo("summary")
            }
        }
    }

    @Test
    internal fun `should populate an Episode ITunes builder with all properties from an Episode ITunes model`() {
        val episodeItunes = anEpisodeItunes()
        val episodeITunesBuilder = EpisodeItunes2.builder().from(episodeItunes)

        assertAll {
            assertThat(episodeITunesBuilder).prop(EpisodeItunesBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(episodeITunesBuilder.build()).isNotNull().isEqualTo(episodeItunes)
        }
    }
}
