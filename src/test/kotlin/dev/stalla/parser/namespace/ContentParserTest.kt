package dev.stalla.parser.namespace

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import assertk.assertions.prop
import dev.stalla.builder.fake.episode.FakeEpisodeBuilder
import dev.stalla.builder.fake.episode.FakeEpisodeContentBuilder
import dev.stalla.dom.XmlRes
import dev.stalla.parser.NamespaceParserTest
import org.junit.jupiter.api.Test
import org.w3c.dom.Node

internal class ContentParserTest : NamespaceParserTest() {

    override val parser = ContentParser

    @Test
    fun `should extract Content data from item when present`() {
        val node: Node = XmlRes("/xml/item.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        node.parseItemChildNodes(builder)

        assertThat(builder.contentBuilder, "item.content")
            .prop(FakeEpisodeContentBuilder::encoded).isEqualTo("episode content encoded")
    }

    @Test
    fun `should not extract Content data from item when absent`() {
        val node: Node = XmlRes("/xml/item-incomplete.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        node.parseItemChildNodes(builder)

        assertThat(builder.contentBuilder, "item.content")
            .prop(FakeEpisodeContentBuilder::encoded).isNull()
    }

    @Test
    fun `should extract nothing from item when Content data is all empty`() {
        val channel: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel/item")
        val builder = FakeEpisodeBuilder()
        channel.parseItemChildNodes(builder)

        assertThat(builder.contentBuilder, "item.content")
            .prop(FakeEpisodeContentBuilder::encoded).isNull()
    }
}
