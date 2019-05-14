package io.hemin.wien.parser

import io.hemin.wien.builder.PodcastBuilder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.w3c.dom.Node

class FeedpressParserTest : NamespaceParserTest() {

    override val parser: NamespaceParser = FeedpressParser()

    val channel: Node? = nodeFromResource("channel", "/xml/channel.xml")

    @Test
    fun testParseChannelItunes() {
        channel?.let {
            val builder = PodcastBuilder()
            parse(builder, it)

            builder.build().feedpress?.let {
                assertEquals("abc123", it.newsletterId)
                assertEquals("en", it.locale)
                assertEquals("xyz123", it.podcastId)
                assertEquals("http://example.org/style.css", it.cssFile)
            } ?: run {
                fail("Podcast Feedpress data not extracted")
            }
        } ?: run {
            fail("channel not found")
        }
    }

}
