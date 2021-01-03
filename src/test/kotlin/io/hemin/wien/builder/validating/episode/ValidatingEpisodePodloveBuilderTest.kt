package io.hemin.wien.builder.validating.episode

import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import io.hemin.wien.model.Episode
import org.junit.jupiter.api.Test

internal class ValidatingEpisodePodloveBuilderTest {

    private val aChapter = Episode.Podlove.SimpleChapter("start", "title")

    @Test
    internal fun `should not build an Episode Podlover Simple Chapters when there is no chapter`() {
        val podloveBuilder = ValidatingEpisodePodloveBuilder()

        assertThat(podloveBuilder.build()).isNull()
    }

    @Test
    internal fun `should build an Episode Podlover Simple Chapters when adding a simple chapter`() {
        val podloveBuilder = ValidatingEpisodePodloveBuilder()
            .addSimpleChapter(aChapter)

        assertThat(podloveBuilder.build()?.simpleChapters).isNotNull()
            .containsExactly(aChapter)
    }

    @Test
    internal fun `should build an Episode Podlover Simple Chapters when adding multiple simple chapters at once`() {
        val anotherChapter = Episode.Podlove.SimpleChapter("start 2", "title 2")
        val podloveBuilder = ValidatingEpisodePodloveBuilder()
            .addSimpleChapters(listOf(aChapter, anotherChapter))

        assertThat(podloveBuilder.build()?.simpleChapters).isNotNull()
            .containsExactly(aChapter, anotherChapter)
    }
}
