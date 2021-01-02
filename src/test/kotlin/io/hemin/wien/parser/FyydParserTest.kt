package io.hemin.wien.parser

import io.hemin.wien.builder.PodcastBuilder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.w3c.dom.Node

/** Provides unit tests for [FyydParser]. */
internal class FyydParserTest : NamespaceParserTest() {

    override val parser: NamespaceParser = FyydParser()

    private val channel: Node? = nodeFromResource("channel", "/xml/channel.xml")

    @Test
    fun testParseChannelFyyd() {
        channel?.let { node ->
            val builder = PodcastBuilder()
            parse(builder, node)

            builder.build().fyyd?.let { fyyd ->
                assertEquals("abcdefg", fyyd.verify)
            } ?: run {
                fail("Podcast Fyyd data not extracted")
            }
        } ?: run {
            fail("channel not found")
        }
    }
}
