package io.hemin.wien.parser

import io.hemin.wien.builder.EpisodeBuilder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.w3c.dom.Node

/** Provides unit tests for [BitloveParser]. */
class BitloveParserTest : NamespaceParserTest() {

    override val parser = BitloveParser()

    val item: Node? = nodeFromResource("item", "/xml/item.xml")

    @Test
    fun testParseItemBitlove() {
        item?.let {
            val builder = EpisodeBuilder()
            parse(builder, it)

            builder.build().bitlove?.let {
                assertEquals("abcdefg", it.guid)
            } ?: run {
                fail("Episode Bitlove data not extracted")
            }
        } ?: run {
            fail("item not found")
        }
    }

}
