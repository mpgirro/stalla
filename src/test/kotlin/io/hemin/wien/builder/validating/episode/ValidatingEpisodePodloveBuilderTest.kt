package io.hemin.wien.builder.validating.episode

import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import org.junit.jupiter.api.Test

internal class ValidatingEpisodePodloveBuilderTest {

    private val expectedChapterBuilder = ValidatingEpisodePodloveSimpleChapterBuilder()
        .start("start")
        .title("chapter title")

    @Test
    internal fun `should not build an Episode Podlover Simple Chapters when there is no chapter`() {
        val podloveBuilder = ValidatingEpisodePodloveBuilder()

        assertThat(podloveBuilder.build()).isNull()
    }

    @Test
    internal fun `should build an Episode Podlover Simple Chapters when adding a simple chapter`() {
        val podloveBuilder = ValidatingEpisodePodloveBuilder()
            .addSimpleChapterBuilder(expectedChapterBuilder)

        assertThat(podloveBuilder.build()?.simpleChapters).isNotNull()
            .containsExactly(expectedChapterBuilder.build())
    }

    @Test
    internal fun `should build an Episode Podlover Simple Chapters when adding multiple simple chapters at once`() {
        val anotherChapterBuilder = ValidatingEpisodePodloveSimpleChapterBuilder()
            .start("start 2")
            .title("chapter title 2")
        val podloveBuilder = ValidatingEpisodePodloveBuilder()
            .addSimpleChapterBuilders(listOf(expectedChapterBuilder, anotherChapterBuilder))

        assertThat(podloveBuilder.build()?.simpleChapters).isNotNull()
            .containsExactly(expectedChapterBuilder.build(), anotherChapterBuilder.build())
    }
}
