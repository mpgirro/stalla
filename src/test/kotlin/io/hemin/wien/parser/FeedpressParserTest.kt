package io.hemin.wien.parser

import io.hemin.wien.builder.PodcastBuilder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.w3c.dom.Node

internal class FeedpressParserTest : NamespaceParserTest() {

    override val parser: NamespaceParser = FeedpressParser()

    private val channel: Node? = nodeFromResource("channel", "/xml/channel.xml")

    @Test
    fun testParseChannelItunes() {
        channel?.let { node ->
            val builder = PodcastBuilder()
            parse(builder, node)

            builder.build().feedpress?.let { feedpress ->
                assertEquals("abc123", feedpress.newsletterId)
                assertEquals("en", feedpress.locale)
                assertEquals("xyz123", feedpress.podcastId)
                assertEquals("http://example.org/style.css", feedpress.cssFile)
            } ?: run {
                fail("Podcast Feedpress data not extracted")
            }
        } ?: run {
            fail("channel not found")
        }
    }
}
