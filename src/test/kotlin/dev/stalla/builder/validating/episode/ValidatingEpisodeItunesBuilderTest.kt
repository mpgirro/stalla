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
import dev.stalla.model.StyledDuration
import dev.stalla.model.anEpisodeItunes
import dev.stalla.model.itunes.EpisodeItunes
import dev.stalla.model.itunes.EpisodeType
import org.junit.jupiter.api.Test

internal class ValidatingEpisodeItunesBuilderTest {

    private val expectedImageBuilder = ValidatingHrefOnlyImageBuilder().href("image href")

    @Test
    internal fun `should not build an Episode Itunes when all fields are missing`() {
        val episodeItunesBuilder = ValidatingEpisodeItunesBuilder()

        assertAll {
            assertThat(episodeItunesBuilder).prop(EpisodeItunesBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodeItunesBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode Itunes with only a title`() {
        val episodeItunesBuilder = ValidatingEpisodeItunesBuilder()
            .title("title")

        assertAll {
            assertThat(episodeItunesBuilder).prop(EpisodeItunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeItunesBuilder.build()).isNotNull().all {
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
    internal fun `should build an Episode Itunes with only a duration`() {
        val episodeItunesBuilder = ValidatingEpisodeItunesBuilder()
            .duration(StyledDuration.seconds(10))

        assertAll {
            assertThat(episodeItunesBuilder).prop(EpisodeItunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeItunesBuilder.build()).isNotNull().all {
                prop(EpisodeItunes::title).isNull()
                prop(EpisodeItunes::duration).isEqualTo(StyledDuration.seconds(10))
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
    internal fun `should build an Episode Itunes with only a season number`() {
        val episodeItunesBuilder = ValidatingEpisodeItunesBuilder()
            .season(2)

        assertAll {
            assertThat(episodeItunesBuilder).prop(EpisodeItunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeItunesBuilder.build()).isNotNull().all {
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
    internal fun `should build an Episode Itunes with only an episode number`() {
        val episodeItunesBuilder = ValidatingEpisodeItunesBuilder()
            .episode(3)

        assertAll {
            assertThat(episodeItunesBuilder).prop(EpisodeItunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeItunesBuilder.build()).isNotNull().all {
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
    internal fun `should build an Episode Itunes with only an episodeType`() {
        val episodeItunesBuilder = ValidatingEpisodeItunesBuilder()
            .episodeType(EpisodeType.BONUS.type)

        assertAll {
            assertThat(episodeItunesBuilder).prop(EpisodeItunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeItunesBuilder.build()).isNotNull().all {
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
    internal fun `should build an Episode Itunes with only explicit`() {
        val episodeItunesBuilder = ValidatingEpisodeItunesBuilder()
            .explicit(false)

        assertAll {
            assertThat(episodeItunesBuilder).prop(EpisodeItunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeItunesBuilder.build()).isNotNull().all {
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
    internal fun `should build an Episode Itunes with only block`() {
        val episodeItunesBuilder = ValidatingEpisodeItunesBuilder()
            .block(true)

        assertAll {
            assertThat(episodeItunesBuilder).prop(EpisodeItunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeItunesBuilder.build()).isNotNull().all {
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
    internal fun `should build an Episode Itunes with only an image`() {
        val episodeItunesBuilder = ValidatingEpisodeItunesBuilder()
            .imageBuilder(expectedImageBuilder)

        assertAll {
            assertThat(episodeItunesBuilder).prop(EpisodeItunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeItunesBuilder.build()).isNotNull().all {
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
    internal fun `should build an Episode Itunes with only an author`() {
        val episodeItunesBuilder = ValidatingEpisodeItunesBuilder()
            .author("author")

        assertAll {
            assertThat(episodeItunesBuilder).prop(EpisodeItunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeItunesBuilder.build()).isNotNull().all {
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
    internal fun `should build an Episode Itunes with only a subtitle`() {
        val episodeItunesBuilder = ValidatingEpisodeItunesBuilder()
            .subtitle("subtitle")

        assertAll {
            assertThat(episodeItunesBuilder).prop(EpisodeItunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeItunesBuilder.build()).isNotNull().all {
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
    internal fun `should build an Episode Itunes with only a summary`() {
        val episodeItunesBuilder = ValidatingEpisodeItunesBuilder()
            .summary("summary")

        assertAll {
            assertThat(episodeItunesBuilder).prop(EpisodeItunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeItunesBuilder.build()).isNotNull().all {
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
    internal fun `should build an Episode Itunes with all the optional fields`() {
        val episodeItunesBuilder = ValidatingEpisodeItunesBuilder()
            .title("title")
            .duration(StyledDuration.seconds(10))
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
            assertThat(episodeItunesBuilder).prop(EpisodeItunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeItunesBuilder.build()).isNotNull().all {
                prop(EpisodeItunes::title).isEqualTo("title")
                prop(EpisodeItunes::duration).isEqualTo(StyledDuration.seconds(10))
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
    internal fun `should populate an Episode Itunes builder with all properties from an Episode Itunes model`() {
        val episodeItunes = anEpisodeItunes()
        val episodeItunesBuilder = EpisodeItunes.builder().applyFrom(episodeItunes)

        assertAll {
            assertThat(episodeItunesBuilder).prop(EpisodeItunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeItunesBuilder.build()).isNotNull().isEqualTo(episodeItunes)
        }
    }
}
