package io.hemin.wien.builder.validating.episode

import assertk.all
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import io.hemin.wien.builder.validating.ValidatingImageBuilder
import io.hemin.wien.model.Episode
import org.junit.jupiter.api.Test

internal class ValidatingEpisodeITunesBuilderTest {

    private val expectedImageBuilder = ValidatingImageBuilder().url("image url")

    @Test
    internal fun `should not build an Episode ITunes when all fields are missing`() {
        val episodeITunesBuilder = ValidatingEpisodeITunesBuilder()

        assertThat(episodeITunesBuilder.build()).isNull()
    }

    @Test
    internal fun `should build an Episode ITunes with only a title`() {
        val episodeITunesBuilder = ValidatingEpisodeITunesBuilder()
            .title("title")

        assertThat(episodeITunesBuilder.build()).isNotNull().all {
            prop(Episode.ITunes::title).isEqualTo("title")
            prop(Episode.ITunes::duration).isNull()
            prop(Episode.ITunes::season).isNull()
            prop(Episode.ITunes::episode).isNull()
            prop(Episode.ITunes::episodeType).isNull()
            prop(Episode.ITunes::explicit).isNull()
            prop(Episode.ITunes::block).isNull()
            prop(Episode.ITunes::image).isNull()
            prop(Episode.ITunes::author).isNull()
            prop(Episode.ITunes::subtitle).isNull()
            prop(Episode.ITunes::summary).isNull()
        }
    }

    @Test
    internal fun `should build an Episode ITunes with only a duration`() {
        val episodeITunesBuilder = ValidatingEpisodeITunesBuilder()
            .duration("duration")

        assertThat(episodeITunesBuilder.build()).isNotNull().all {
            prop(Episode.ITunes::title).isNull()
            prop(Episode.ITunes::duration).isEqualTo("duration")
            prop(Episode.ITunes::season).isNull()
            prop(Episode.ITunes::episode).isNull()
            prop(Episode.ITunes::episodeType).isNull()
            prop(Episode.ITunes::explicit).isNull()
            prop(Episode.ITunes::block).isNull()
            prop(Episode.ITunes::image).isNull()
            prop(Episode.ITunes::author).isNull()
            prop(Episode.ITunes::subtitle).isNull()
            prop(Episode.ITunes::summary).isNull()
        }
    }

    @Test
    internal fun `should build an Episode ITunes with only a season number`() {
        val episodeITunesBuilder = ValidatingEpisodeITunesBuilder()
            .season(2)

        assertThat(episodeITunesBuilder.build()).isNotNull().all {
            prop(Episode.ITunes::title).isNull()
            prop(Episode.ITunes::duration).isNull()
            prop(Episode.ITunes::season).isEqualTo(2)
            prop(Episode.ITunes::episode).isNull()
            prop(Episode.ITunes::episodeType).isNull()
            prop(Episode.ITunes::explicit).isNull()
            prop(Episode.ITunes::block).isNull()
            prop(Episode.ITunes::image).isNull()
            prop(Episode.ITunes::author).isNull()
            prop(Episode.ITunes::subtitle).isNull()
            prop(Episode.ITunes::summary).isNull()
        }
    }

    @Test
    internal fun `should build an Episode ITunes with only an episode number`() {
        val episodeITunesBuilder = ValidatingEpisodeITunesBuilder()
            .episode(3)

        assertThat(episodeITunesBuilder.build()).isNotNull().all {
            prop(Episode.ITunes::title).isNull()
            prop(Episode.ITunes::duration).isNull()
            prop(Episode.ITunes::season).isNull()
            prop(Episode.ITunes::episode).isEqualTo(3)
            prop(Episode.ITunes::episodeType).isNull()
            prop(Episode.ITunes::explicit).isNull()
            prop(Episode.ITunes::block).isNull()
            prop(Episode.ITunes::image).isNull()
            prop(Episode.ITunes::author).isNull()
            prop(Episode.ITunes::subtitle).isNull()
            prop(Episode.ITunes::summary).isNull()
        }
    }

    @Test
    internal fun `should build an Episode ITunes with only an episodeType`() {
        val episodeITunesBuilder = ValidatingEpisodeITunesBuilder()
            .episodeType(Episode.ITunes.EpisodeType.BONUS.type)

        assertThat(episodeITunesBuilder.build()).isNotNull().all {
            prop(Episode.ITunes::title).isNull()
            prop(Episode.ITunes::duration).isNull()
            prop(Episode.ITunes::season).isNull()
            prop(Episode.ITunes::episode).isNull()
            prop(Episode.ITunes::episodeType).isEqualTo(Episode.ITunes.EpisodeType.BONUS)
            prop(Episode.ITunes::explicit).isNull()
            prop(Episode.ITunes::block).isNull()
            prop(Episode.ITunes::image).isNull()
            prop(Episode.ITunes::author).isNull()
            prop(Episode.ITunes::subtitle).isNull()
            prop(Episode.ITunes::summary).isNull()
        }
    }

    @Test
    internal fun `should build an Episode ITunes with only explicit`() {
        val episodeITunesBuilder = ValidatingEpisodeITunesBuilder()
            .explicit(false)

        assertThat(episodeITunesBuilder.build()).isNotNull().all {
            prop(Episode.ITunes::title).isNull()
            prop(Episode.ITunes::duration).isNull()
            prop(Episode.ITunes::season).isNull()
            prop(Episode.ITunes::episode).isNull()
            prop(Episode.ITunes::episodeType).isNull()
            prop(Episode.ITunes::explicit).isNotNull().isFalse()
            prop(Episode.ITunes::block).isNull()
            prop(Episode.ITunes::image).isNull()
            prop(Episode.ITunes::author).isNull()
            prop(Episode.ITunes::subtitle).isNull()
            prop(Episode.ITunes::summary).isNull()
        }
    }

    @Test
    internal fun `should build an Episode ITunes with only block`() {
        val episodeITunesBuilder = ValidatingEpisodeITunesBuilder()
            .block(false)

        assertThat(episodeITunesBuilder.build()).isNotNull().all {
            prop(Episode.ITunes::title).isNull()
            prop(Episode.ITunes::duration).isNull()
            prop(Episode.ITunes::season).isNull()
            prop(Episode.ITunes::episode).isNull()
            prop(Episode.ITunes::episodeType).isNull()
            prop(Episode.ITunes::explicit).isNull()
            prop(Episode.ITunes::block).isNotNull().isFalse()
            prop(Episode.ITunes::image).isNull()
            prop(Episode.ITunes::author).isNull()
            prop(Episode.ITunes::subtitle).isNull()
            prop(Episode.ITunes::summary).isNull()
        }
    }

    @Test
    internal fun `should build an Episode ITunes with only an image`() {
        val episodeITunesBuilder = ValidatingEpisodeITunesBuilder()
            .imageBuilder(expectedImageBuilder)

        assertThat(episodeITunesBuilder.build()).isNotNull().all {
            prop(Episode.ITunes::title).isNull()
            prop(Episode.ITunes::duration).isNull()
            prop(Episode.ITunes::season).isNull()
            prop(Episode.ITunes::episode).isNull()
            prop(Episode.ITunes::episodeType).isNull()
            prop(Episode.ITunes::explicit).isNull()
            prop(Episode.ITunes::block).isNull()
            prop(Episode.ITunes::image).isEqualTo(expectedImageBuilder.build())
            prop(Episode.ITunes::author).isNull()
            prop(Episode.ITunes::subtitle).isNull()
            prop(Episode.ITunes::summary).isNull()
        }
    }

    @Test
    internal fun `should build an Episode ITunes with only an author`() {
        val episodeITunesBuilder = ValidatingEpisodeITunesBuilder()
            .author("author")

        assertThat(episodeITunesBuilder.build()).isNotNull().all {
            prop(Episode.ITunes::title).isNull()
            prop(Episode.ITunes::duration).isNull()
            prop(Episode.ITunes::season).isNull()
            prop(Episode.ITunes::episode).isNull()
            prop(Episode.ITunes::episodeType).isNull()
            prop(Episode.ITunes::explicit).isNull()
            prop(Episode.ITunes::block).isNull()
            prop(Episode.ITunes::image).isNull()
            prop(Episode.ITunes::author).isEqualTo("author")
            prop(Episode.ITunes::subtitle).isNull()
            prop(Episode.ITunes::summary).isNull()
        }
    }

    @Test
    internal fun `should build an Episode ITunes with only a subtitle`() {
        val episodeITunesBuilder = ValidatingEpisodeITunesBuilder()
            .subtitle("subtitle")

        assertThat(episodeITunesBuilder.build()).isNotNull().all {
            prop(Episode.ITunes::title).isNull()
            prop(Episode.ITunes::duration).isNull()
            prop(Episode.ITunes::season).isNull()
            prop(Episode.ITunes::episode).isNull()
            prop(Episode.ITunes::episodeType).isNull()
            prop(Episode.ITunes::explicit).isNull()
            prop(Episode.ITunes::block).isNull()
            prop(Episode.ITunes::image).isNull()
            prop(Episode.ITunes::author).isNull()
            prop(Episode.ITunes::subtitle).isEqualTo("subtitle")
            prop(Episode.ITunes::summary).isNull()
        }
    }

    @Test
    internal fun `should build an Episode ITunes with only a summary`() {
        val episodeITunesBuilder = ValidatingEpisodeITunesBuilder()
            .summary("summary")

        assertThat(episodeITunesBuilder.build()).isNotNull().all {
            prop(Episode.ITunes::title).isNull()
            prop(Episode.ITunes::duration).isNull()
            prop(Episode.ITunes::season).isNull()
            prop(Episode.ITunes::episode).isNull()
            prop(Episode.ITunes::episodeType).isNull()
            prop(Episode.ITunes::explicit).isNull()
            prop(Episode.ITunes::block).isNull()
            prop(Episode.ITunes::image).isNull()
            prop(Episode.ITunes::author).isNull()
            prop(Episode.ITunes::subtitle).isNull()
            prop(Episode.ITunes::summary).isEqualTo("summary")
        }
    }

    @Test
    internal fun `should build an Episode ITunes with all the optional fields`() {
        val episodeITunesBuilder = ValidatingEpisodeITunesBuilder()
            .title("title")
            .duration("duration")
            .season(2)
            .episode(3)
            .episodeType(Episode.ITunes.EpisodeType.BONUS.type)
            .explicit(false)
            .block(false)
            .imageBuilder(expectedImageBuilder)
            .author("author")
            .subtitle("subtitle")
            .summary("summary")

        assertThat(episodeITunesBuilder.build()).isNotNull().all {
            prop(Episode.ITunes::title).isEqualTo("title")
            prop(Episode.ITunes::duration).isEqualTo("duration")
            prop(Episode.ITunes::season).isEqualTo(2)
            prop(Episode.ITunes::episode).isEqualTo(3)
            prop(Episode.ITunes::episodeType).isEqualTo(Episode.ITunes.EpisodeType.BONUS)
            prop(Episode.ITunes::explicit).isNotNull().isFalse()
            prop(Episode.ITunes::block).isNotNull().isFalse()
            prop(Episode.ITunes::image).isEqualTo(expectedImageBuilder.build())
            prop(Episode.ITunes::author).isEqualTo("author")
            prop(Episode.ITunes::subtitle).isEqualTo("subtitle")
            prop(Episode.ITunes::summary).isEqualTo("summary")
        }
    }
}
