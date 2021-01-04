package io.hemin.wien.parser

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import assertk.assertions.prop
import io.hemin.wien.builder.fake.episode.FakeEpisodeBitloveBuilder
import io.hemin.wien.builder.fake.episode.FakeEpisodeBuilder
import io.hemin.wien.nodeFromResource
import io.hemin.wien.parser.namespace.BitloveParser
import org.junit.jupiter.api.Test
import org.w3c.dom.Node

internal class BitloveParserTest : NamespaceParserTest() {

    override val parser = BitloveParser()

    @Test
    fun `should not extract the Bitlove guid attribute from enclosure nodes when it's absent`() {
        val node: Node = nodeFromResource("item", "/xml/item-incomplete.xml")
        val builder = FakeEpisodeBuilder()
        parseItemChildNodes(builder, node)

        assertThat(builder.bitlove, "item bitlove data")
            .prop(FakeEpisodeBitloveBuilder::guid).isNull()
    }

    @Test
    fun `should extract the Bitlove guid attribute from enclosure nodes when it's present`() {
        val node: Node = nodeFromResource("item", "/xml/item.xml")
        val builder = FakeEpisodeBuilder()
        parseItemChildNodes(builder, node)

        assertThat(builder.bitlove, "item bitlove data")
            .prop(FakeEpisodeBitloveBuilder::guid).isEqualTo("abcdefg")
    }
}
