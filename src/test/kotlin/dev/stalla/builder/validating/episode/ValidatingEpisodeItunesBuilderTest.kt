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
import dev.stalla.builder.episode.EpisodeItunesBuilder
import dev.stalla.builder.validating.ValidatingHrefOnlyImageBuilder
import dev.stalla.model.episode.anEpisodeItunes
import dev.stalla.model.itunes.EpisodeItunes
import dev.stalla.model.itunes.EpisodeType
import org.junit.jupiter.api.Test

internal class ValidatingEpisodeItunesBuilderTest {

    private val expectedImageBuilder = ValidatingHrefOnlyImageBuilder().href("image href")

    @Test
    internal fun `should not build an Episode ITunes when all fields are missing`() {
        val episodeITunesBuilder = ValidatingEpisodeItunesBuilder()

        assertAll {
            assertThat(episodeITunesBuilder).prop(EpisodeItunesBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodeITunesBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode ITunes with only a title`() {
        val episodeITunesBuilder = ValidatingEpisodeItunesBuilder()
            .title("title")

        assertAll {
            assertThat(episodeITunesBuilder).prop(EpisodeItunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeITunesBuilder.build()).isNotNull().all {
                prop(EpisodeItunes::title).isEqualTo("title")
                prop(EpisodeItunes::duration).isNull()
                prop(EpisodeItunes::season).isNull()
                prop(EpisodeItunes::episode).isNull()
                prop(EpisodeItunes::episodeType).isNull()
                prop(EpisodeItunes::explicit).isNull()
                prop(EpisodeItunes::block).isFalse()
                prop(EpisodeItunes::image).isNull()
                prop(EpisodeItunes::author).isNull()
                prop(EpisodeItunes::subtitle).isNull()
                prop(EpisodeItunes::summary).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode ITunes with only a duration`() {
        val episodeITunesBuilder = ValidatingEpisodeItunesBuilder()
            .duration("duration")

        assertAll {
            assertThat(episodeITunesBuilder).prop(EpisodeItunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeITunesBuilder.build()).isNotNull().all {
                prop(EpisodeItunes::title).isNull()
                prop(EpisodeItunes::duration).isEqualTo("duration")
                prop(EpisodeItunes::season).isNull()
                prop(EpisodeItunes::episode).isNull()
                prop(EpisodeItunes::episodeType).isNull()
                prop(EpisodeItunes::explicit).isNull()
                prop(EpisodeItunes::block).isFalse()
                prop(EpisodeItunes::image).isNull()
                prop(EpisodeItunes::author).isNull()
                prop(EpisodeItunes::subtitle).isNull()
                prop(EpisodeItunes::summary).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode ITunes with only a season number`() {
        val episodeITunesBuilder = ValidatingEpisodeItunesBuilder()
            .season(2)

        assertAll {
            assertThat(episodeITunesBuilder).prop(EpisodeItunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeITunesBuilder.build()).isNotNull().all {
                prop(EpisodeItunes::title).isNull()
                prop(EpisodeItunes::duration).isNull()
                prop(EpisodeItunes::season).isEqualTo(2)
                prop(EpisodeItunes::episode).isNull()
                prop(EpisodeItunes::episodeType).isNull()
                prop(EpisodeItunes::explicit).isNull()
                prop(EpisodeItunes::block).isFalse()
                prop(EpisodeItunes::image).isNull()
                prop(EpisodeItunes::author).isNull()
                prop(EpisodeItunes::subtitle).isNull()
                prop(EpisodeItunes::summary).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode ITunes with only an episode number`() {
        val episodeITunesBuilder = ValidatingEpisodeItunesBuilder()
            .episode(3)

        assertAll {
            assertThat(episodeITunesBuilder).prop(EpisodeItunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeITunesBuilder.build()).isNotNull().all {
                prop(EpisodeItunes::title).isNull()
                prop(EpisodeItunes::duration).isNull()
                prop(EpisodeItunes::season).isNull()
                prop(EpisodeItunes::episode).isEqualTo(3)
                prop(EpisodeItunes::episodeType).isNull()
                prop(EpisodeItunes::explicit).isNull()
                prop(EpisodeItunes::block).isFalse()
                prop(EpisodeItunes::image).isNull()
                prop(EpisodeItunes::author).isNull()
                prop(EpisodeItunes::subtitle).isNull()
                prop(EpisodeItunes::summary).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode ITunes with only an episodeType`() {
        val episodeITunesBuilder = ValidatingEpisodeItunesBuilder()
            .episodeType(EpisodeType.BONUS.type)

        assertAll {
            assertThat(episodeITunesBuilder).prop(EpisodeItunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeITunesBuilder.build()).isNotNull().all {
                prop(EpisodeItunes::title).isNull()
                prop(EpisodeItunes::duration).isNull()
                prop(EpisodeItunes::season).isNull()
                prop(EpisodeItunes::episode).isNull()
                prop(EpisodeItunes::episodeType).isEqualTo(EpisodeType.BONUS)
                prop(EpisodeItunes::explicit).isNull()
                prop(EpisodeItunes::block).isFalse()
                prop(EpisodeItunes::image).isNull()
                prop(EpisodeItunes::author).isNull()
                prop(EpisodeItunes::subtitle).isNull()
                prop(EpisodeItunes::summary).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode ITunes with only explicit`() {
        val episodeITunesBuilder = ValidatingEpisodeItunesBuilder()
            .explicit(false)

        assertAll {
            assertThat(episodeITunesBuilder).prop(EpisodeItunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeITunesBuilder.build()).isNotNull().all {
                prop(EpisodeItunes::title).isNull()
                prop(EpisodeItunes::duration).isNull()
                prop(EpisodeItunes::season).isNull()
                prop(EpisodeItunes::episode).isNull()
                prop(EpisodeItunes::episodeType).isNull()
                prop(EpisodeItunes::explicit).isNotNull().isFalse()
                prop(EpisodeItunes::block).isFalse()
                prop(EpisodeItunes::image).isNull()
                prop(EpisodeItunes::author).isNull()
                prop(EpisodeItunes::subtitle).isNull()
                prop(EpisodeItunes::summary).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode ITunes with only block`() {
        val episodeITunesBuilder = ValidatingEpisodeItunesBuilder()
            .block(true)

        assertAll {
            assertThat(episodeITunesBuilder).prop(EpisodeItunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeITunesBuilder.build()).isNotNull().all {
                prop(EpisodeItunes::title).isNull()
                prop(EpisodeItunes::duration).isNull()
                prop(EpisodeItunes::season).isNull()
                prop(EpisodeItunes::episode).isNull()
                prop(EpisodeItunes::episodeType).isNull()
                prop(EpisodeItunes::explicit).isNull()
                prop(EpisodeItunes::block).isNotNull().isTrue()
                prop(EpisodeItunes::image).isNull()
                prop(EpisodeItunes::author).isNull()
                prop(EpisodeItunes::subtitle).isNull()
                prop(EpisodeItunes::summary).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode ITunes with only an image`() {
        val episodeITunesBuilder = ValidatingEpisodeItunesBuilder()
            .imageBuilder(expectedImageBuilder)

        assertAll {
            assertThat(episodeITunesBuilder).prop(EpisodeItunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeITunesBuilder.build()).isNotNull().all {
                prop(EpisodeItunes::title).isNull()
                prop(EpisodeItunes::duration).isNull()
                prop(EpisodeItunes::season).isNull()
                prop(EpisodeItunes::episode).isNull()
                prop(EpisodeItunes::episodeType).isNull()
                prop(EpisodeItunes::explicit).isNull()
                prop(EpisodeItunes::block).isFalse()
                prop(EpisodeItunes::image).isEqualTo(expectedImageBuilder.build())
                prop(EpisodeItunes::author).isNull()
                prop(EpisodeItunes::subtitle).isNull()
                prop(EpisodeItunes::summary).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode ITunes with only an author`() {
        val episodeITunesBuilder = ValidatingEpisodeItunesBuilder()
            .author("author")

        assertAll {
            assertThat(episodeITunesBuilder).prop(EpisodeItunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeITunesBuilder.build()).isNotNull().all {
                prop(EpisodeItunes::title).isNull()
                prop(EpisodeItunes::duration).isNull()
                prop(EpisodeItunes::season).isNull()
                prop(EpisodeItunes::episode).isNull()
                prop(EpisodeItunes::episodeType).isNull()
                prop(EpisodeItunes::explicit).isNull()
                prop(EpisodeItunes::block).isFalse()
                prop(EpisodeItunes::image).isNull()
                prop(EpisodeItunes::author).isEqualTo("author")
                prop(EpisodeItunes::subtitle).isNull()
                prop(EpisodeItunes::summary).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode ITunes with only a subtitle`() {
        val episodeITunesBuilder = ValidatingEpisodeItunesBuilder()
            .subtitle("subtitle")

        assertAll {
            assertThat(episodeITunesBuilder).prop(EpisodeItunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeITunesBuilder.build()).isNotNull().all {
                prop(EpisodeItunes::title).isNull()
                prop(EpisodeItunes::duration).isNull()
                prop(EpisodeItunes::season).isNull()
                prop(EpisodeItunes::episode).isNull()
                prop(EpisodeItunes::episodeType).isNull()
                prop(EpisodeItunes::explicit).isNull()
                prop(EpisodeItunes::block).isFalse()
                prop(EpisodeItunes::image).isNull()
                prop(EpisodeItunes::author).isNull()
                prop(EpisodeItunes::subtitle).isEqualTo("subtitle")
                prop(EpisodeItunes::summary).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode ITunes with only a summary`() {
        val episodeITunesBuilder = ValidatingEpisodeItunesBuilder()
            .summary("summary")

        assertAll {
            assertThat(episodeITunesBuilder).prop(EpisodeItunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeITunesBuilder.build()).isNotNull().all {
                prop(EpisodeItunes::title).isNull()
                prop(EpisodeItunes::duration).isNull()
                prop(EpisodeItunes::season).isNull()
                prop(EpisodeItunes::episode).isNull()
                prop(EpisodeItunes::episodeType).isNull()
                prop(EpisodeItunes::explicit).isNull()
                prop(EpisodeItunes::block).isFalse()
                prop(EpisodeItunes::image).isNull()
                prop(EpisodeItunes::author).isNull()
                prop(EpisodeItunes::subtitle).isNull()
                prop(EpisodeItunes::summary).isEqualTo("summary")
            }
        }
    }

    @Test
    internal fun `should build an Episode ITunes with all the optional fields`() {
        val episodeITunesBuilder = ValidatingEpisodeItunesBuilder()
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
            assertThat(episodeITunesBuilder).prop(EpisodeItunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeITunesBuilder.build()).isNotNull().all {
                prop(EpisodeItunes::title).isEqualTo("title")
                prop(EpisodeItunes::duration).isEqualTo("duration")
                prop(EpisodeItunes::season).isEqualTo(2)
                prop(EpisodeItunes::episode).isEqualTo(3)
                prop(EpisodeItunes::episodeType).isEqualTo(EpisodeType.BONUS)
                prop(EpisodeItunes::explicit).isNotNull().isFalse()
                prop(EpisodeItunes::block).isNotNull().isFalse()
                prop(EpisodeItunes::image).isEqualTo(expectedImageBuilder.build())
                prop(EpisodeItunes::author).isEqualTo("author")
                prop(EpisodeItunes::subtitle).isEqualTo("subtitle")
                prop(EpisodeItunes::summary).isEqualTo("summary")
            }
        }
    }

    @Test
    internal fun `should populate an Episode ITunes builder with all properties from an Episode ITunes model`() {
        val episodeItunes = anEpisodeItunes()
        val episodeITunesBuilder = EpisodeItunes.builder().from(episodeItunes)

        assertAll {
            assertThat(episodeITunesBuilder).prop(EpisodeItunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeITunesBuilder.build()).isNotNull().isEqualTo(episodeItunes)
        }
    }
}
