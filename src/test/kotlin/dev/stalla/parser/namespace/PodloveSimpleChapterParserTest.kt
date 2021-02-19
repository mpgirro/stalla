package dev.stalla.parser.namespace

import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEmpty
import assertk.assertions.prop
import dev.stalla.builder.fake.episode.FakeEpisodeBuilder
import dev.stalla.builder.fake.episode.FakeEpisodePodloveBuilder
import dev.stalla.builder.fake.episode.FakeEpisodePodloveSimpleChapterBuilder
import dev.stalla.dom.XmlRes
import dev.stalla.parser.NamespaceParserTest
import org.junit.jupiter.api.Test
import org.w3c.dom.Node

internal class PodloveSimpleChapterParserTest : NamespaceParserTest() {

    override val parser = PodloveSimpleChapterParser

    @Test
    fun `should not extract Podlove SimpleChapter data from item when absent`() {
        val node = XmlRes("/xml/item-incomplete.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        node.parseItemChildNodes(builder)

        assertThat(builder.podloveBuilder.chapterBuilders, "item.podlove_simple_chapters").isEmpty()
    }

    @Test
    fun `should extract nothing from item when Podlove SimpleChapter data is all empty`() {
        val channel: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel/item")
        val builder = FakeEpisodeBuilder()
        channel.parseItemChildNodes(builder)

        assertThat(builder.podloveBuilder, "item.podlove_simple_chapters")
            .prop(FakeEpisodePodloveBuilder::chapterBuilders).isEmpty()
    }

    @Test
    fun `should extract Podlove SimpleChapter data from item when present`() {
        val node = XmlRes("/xml/item.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        node.parseItemChildNodes(builder)

        assertThat(builder.podloveBuilder.chapterBuilders, "item.podlove_simple_chapters")
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
