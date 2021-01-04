package io.hemin.wien.parser

import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEmpty
import io.hemin.wien.builder.fake.episode.FakeEpisodeBuilder
import io.hemin.wien.builder.fake.episode.FakeEpisodePodloveSimpleChapterBuilder
import io.hemin.wien.nodeFromResource
import io.hemin.wien.parser.namespace.PodloveSimpleChapterParser
import org.junit.jupiter.api.Test

internal class PodloveSimpleChapterParserTest : NamespaceParserTest() {

    override val parser = PodloveSimpleChapterParser()

    @Test
    fun `should not extract podlove chapter data from item when absent`() {
        val node = nodeFromResource("item", "/xml/item-incomplete.xml")
        val builder = FakeEpisodeBuilder()
        parseItemNode(builder, node)

        assertThat(builder.podlove.chapterBuilders, "item.podlove_simple_chapters").isEmpty()
    }

    @Test
    fun `should extract podlove chapter data from item when present`() {
        val node = nodeFromResource("item", "/xml/item.xml")
        val builder = FakeEpisodeBuilder()
        parseItemNode(builder, node)

        assertThat(builder.podlove.chapterBuilders, "item.podlove_simple_chapters")
            .containsExactly(
                FakeEpisodePodloveSimpleChapterBuilder()
                    .start("00:00:00.000")
                    .title("Lorem Ipsum")
                    .href("http://example.org")
                    .image("http://example.org/cover"),
                FakeEpisodePodloveSimpleChapterBuilder()
                    .start("00:01:03.856")
                    .title("Lorem Ipsum")
                    .href("http://example.org")
                    .image("http://example.org/cover"),
                FakeEpisodePodloveSimpleChapterBuilder()
                    .start("00:02:12.641")
                    .title("Lorem Ipsum")
                    .href("http://example.org")
                    .image("http://example.org/cover")
            )
    }
}
