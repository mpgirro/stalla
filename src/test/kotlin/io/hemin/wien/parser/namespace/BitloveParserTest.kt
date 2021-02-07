package io.hemin.wien.parser.namespace

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import assertk.assertions.prop
import io.hemin.wien.builder.fake.episode.FakeEpisodeBitloveBuilder
import io.hemin.wien.builder.fake.episode.FakeEpisodeBuilder
import io.hemin.wien.dom.XmlRes
import io.hemin.wien.parser.NamespaceParserTest
import org.junit.jupiter.api.Test
import org.w3c.dom.Node

internal class BitloveParserTest : NamespaceParserTest() {

    override val parser = BitloveParser

    @Test
    fun `should extract the Bitlove guid attribute from enclosure nodes when it's present`() {
        val node: Node = XmlRes("/xml/item.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        node.parseItemChildNodes(builder)

        assertThat(builder.bitloveBuilder, "item bitlove data")
            .prop(FakeEpisodeBitloveBuilder::guid).isEqualTo("abcdefg")
    }

    @Test
    fun `should not extract the Bitlove guid attribute from enclosure nodes when it's absent`() {
        val node: Node = XmlRes("/xml/item-incomplete.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        node.parseItemChildNodes(builder)

        assertThat(builder.bitloveBuilder, "item bitlove data")
            .prop(FakeEpisodeBitloveBuilder::guid).isNull()
    }

    @Test
    fun `should extract nothing from item when bitlove data is all empty`() {
        val channel: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel/item")
        val builder = FakeEpisodeBuilder()
        channel.parseItemChildNodes(builder)

        assertThat(builder.bitloveBuilder, "item bitlove data")
            .prop(FakeEpisodeBitloveBuilder::guid).isNull()
    }
}
