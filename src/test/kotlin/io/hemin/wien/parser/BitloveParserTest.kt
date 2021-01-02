package io.hemin.wien.parser

import io.hemin.wien.builder.EpisodeBuilder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.w3c.dom.Node

/** Provides unit tests for [BitloveParser]. */
internal class BitloveParserTest : NamespaceParserTest() {

    override val parser = BitloveParser()

    private val item: Node? = nodeFromResource("item", "/xml/item.xml")

    @Test
    fun testParseItemBitlove() {
        item?.let { node ->
            val builder = EpisodeBuilder()
            parseItemNode(builder, node)

            builder.build().bitlove?.let { bitlove ->
                assertEquals("abcdefg", bitlove.guid)
            } ?: run {
                fail("Episode Bitlove data not extracted")
            }
        } ?: run {
            fail("item not found")
        }
    }
}
