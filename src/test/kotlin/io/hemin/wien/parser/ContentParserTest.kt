package io.hemin.wien.parser

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import assertk.assertions.prop
import io.hemin.wien.builder.fake.episode.FakeEpisodeBuilder
import io.hemin.wien.builder.fake.episode.FakeEpisodeContentBuilder
import io.hemin.wien.nodeFromResource
import io.hemin.wien.parser.namespace.ContentParser
import org.junit.jupiter.api.Test
import org.w3c.dom.Node

internal class ContentParserTest : NamespaceParserTest() {

    override val parser: NamespaceParser = ContentParser()

    @Test
    fun `should not extract content data from item when absent`() {
        val node: Node = nodeFromResource("item", "/xml/item-incomplete.xml")
        val builder = FakeEpisodeBuilder()
        parseItemChildNodes(builder, node)

        assertThat(builder.content, "item content data")
            .prop(FakeEpisodeContentBuilder::encoded).isNull()
    }

    @Test
    fun `should extract content data from item when present`() {
        val node: Node = nodeFromResource("item", "/xml/item.xml")
        val builder = FakeEpisodeBuilder()
        parseItemChildNodes(builder, node)

        assertThat(builder.content, "item content data")
            .prop(FakeEpisodeContentBuilder::encoded).isEqualTo("Lorem Ipsum")
    }
}
