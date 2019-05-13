package io.hemin.wien.parser

import io.hemin.wien.builder.PodcastBuilder
import io.hemin.wien.builder.PodcastFyydBuilder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.w3c.dom.Node

/** Provides unit tests for [FyydParser]. */
class FyydParserTest : NamespaceParserTest() {

    override val parser: NamespaceParser = FyydParser()

    private val channel: Node? = nodeFromResource("channel", "/xml/channel.xml")

    private val expectedFyyd = PodcastFyydBuilder()
        .verify("abcdefg")
        .build()

    @Test
    fun testParseChannelFyyd() {
        channel?.let {
            val builder = PodcastBuilder()
            parse(builder, it)

            builder.build().fyyd?.let {
                assertEquals("abcdefg", it.verify)
            } ?: run {
                fail("Podcast Fyyd data not extracted")
            }
        } ?: run {
            fail("channel not found")
        }
    }

}
