package io.hemin.wien.builder.validating.episode

import assertk.all
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import io.hemin.wien.model.Episode
import org.junit.jupiter.api.Test

internal class ValidatingEpisodePodloveSimpleChapterBuilderTest {

    @Test
    internal fun `should not build an Episode PodloveSimpleChapter when the mandatory fields are absent`() {
        val episodePodloveSimpleChapterBuilder = ValidatingEpisodePodloveSimpleChapterBuilder()

        assertThat(episodePodloveSimpleChapterBuilder.build()).isNull()
    }

    @Test
    internal fun `should build an Episode PodloveSimpleChapter with all the mandatory fields`() {
        val episodePodloveSimpleChapterBuilder = ValidatingEpisodePodloveSimpleChapterBuilder()
            .start("start")
            .title("title")

        assertThat(episodePodloveSimpleChapterBuilder.build()).isNotNull().all {
            prop(Episode.Podlove.SimpleChapter::start).isEqualTo("start")
            prop(Episode.Podlove.SimpleChapter::title).isEqualTo("title")
            prop(Episode.Podlove.SimpleChapter::href).isNull()
            prop(Episode.Podlove.SimpleChapter::image).isNull()
        }
    }

    @Test
    internal fun `should build an Episode PodloveSimpleChapter with all the added entries to its fields`() {
        val episodePodloveSimpleChapterBuilder = ValidatingEpisodePodloveSimpleChapterBuilder()
            .start("start")
            .title("title")
            .href("href")
            .image("image")

        assertThat(episodePodloveSimpleChapterBuilder.build()).isNotNull().all {
            prop(Episode.Podlove.SimpleChapter::start).isEqualTo("start")
            prop(Episode.Podlove.SimpleChapter::title).isEqualTo("title")
            prop(Episode.Podlove.SimpleChapter::href).isEqualTo("href")
            prop(Episode.Podlove.SimpleChapter::image).isEqualTo("image")
        }
    }
}
