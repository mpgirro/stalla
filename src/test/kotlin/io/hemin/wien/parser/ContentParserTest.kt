package io.hemin.wien.parser

import io.hemin.wien.builder.EpisodeBuilder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.w3c.dom.Node

/** Provides unit tests for [ContentParser]. */
internal class ContentParserTest : NamespaceParserTest() {

    override val parser: NamespaceParser = ContentParser()

    private val item: Node? = nodeFromResource("item", "/xml/item.xml")

    @Test
    fun testParseItemContent() {
        item?.let { node ->
            val builder = EpisodeBuilder()
            parseItemNode(builder, node)

            builder.build().content?.let { content ->
                assertEquals("Lorem Ipsum", content.encoded)
            } ?: run {
                fail("Episode Content data not extracted")
            }
        } ?: run {
            fail("item not found")
        }
    }
}
