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
import dev.stalla.builder.episode.EpisodePodloveSimpleChapterBuilder
import dev.stalla.model.Episode
import dev.stalla.model.episode.aPodloveSimpleChapter
import org.junit.jupiter.api.Test

internal class ValidatingEpisodePodloveSimpleChapterBuilderTest {

    @Test
    internal fun `should not build an Episode PodloveSimpleChapter when the mandatory fields are absent`() {
        val chapterBuilder = ValidatingEpisodePodloveSimpleChapterBuilder()

        assertAll {
            assertThat(chapterBuilder).prop(EpisodePodloveSimpleChapterBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(chapterBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode PodloveSimpleChapter with all the mandatory fields`() {
        val chapterBuilder = ValidatingEpisodePodloveSimpleChapterBuilder()
            .start("start")
            .title("title")

        assertAll {
            assertThat(chapterBuilder).prop(EpisodePodloveSimpleChapterBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(chapterBuilder.build()).isNotNull().all {
                prop(Episode.Podlove.SimpleChapter::start).isEqualTo("start")
                prop(Episode.Podlove.SimpleChapter::title).isEqualTo("title")
                prop(Episode.Podlove.SimpleChapter::href).isNull()
                prop(Episode.Podlove.SimpleChapter::image).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Episode PodloveSimpleChapter with all the added entries to its fields`() {
        val chapterBuilder = ValidatingEpisodePodloveSimpleChapterBuilder()
            .start("start")
            .title("title")
            .href("href")
            .image("image")

        assertAll {
            assertThat(chapterBuilder).prop(EpisodePodloveSimpleChapterBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(chapterBuilder.build()).isNotNull().all {
                prop(Episode.Podlove.SimpleChapter::start).isEqualTo("start")
                prop(Episode.Podlove.SimpleChapter::title).isEqualTo("title")
                prop(Episode.Podlove.SimpleChapter::href).isEqualTo("href")
                prop(Episode.Podlove.SimpleChapter::image).isEqualTo("image")
            }
        }
    }

    @Test
    internal fun `should populate an Episode Podlove SimpleChapter builder with all properties from an Episode Podlove SimpleChapter model`() {
        val podloveSimpleChapter = aPodloveSimpleChapter()
        val podloveSimpleChaptersBuilder = Episode.Podlove.SimpleChapter.builder().from(podloveSimpleChapter)

        assertAll {
            assertThat(podloveSimpleChaptersBuilder).prop(EpisodePodloveSimpleChapterBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podloveSimpleChaptersBuilder.build()).isNotNull().isEqualTo(podloveSimpleChapter)
        }
    }
}
