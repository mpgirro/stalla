package io.hemin.wien.parser

import io.hemin.wien.builder.EpisodeBuilder
import io.hemin.wien.builder.ImageBuilder
import io.hemin.wien.builder.PodcastBuilder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.w3c.dom.Node

internal class GoogleplayParserTest : NamespaceParserTest() {

    override val parser: NamespaceParser = GoogleplayParser()

    private val channel: Node? = nodeFromResource("channel", "/xml/channel.xml")
    private val item: Node? = nodeFromResource("item", "/xml/item.xml")

    private val expectedPodcastImage = ImageBuilder()
        .url("http://example.org/podcast-cover.jpg")
        .build()

    private val expectedEpisodeImage = ImageBuilder()
        .url("http://example.org/episode-cover.jpg")
        .build()

    @Test
    fun testParseChannelItunes() {
        channel?.let { node ->
            val builder = PodcastBuilder()
            parseChannelNode(builder, node)

            builder.build().googleplay?.let { googleplay ->
                assertEquals("Lorem Ipsum", googleplay.author)
                assertEquals("email@example.org", googleplay.email)
                // assertEquals(emptyList<String>(), googleplay.categories) // TODO this will fail --> see XML
                assertEquals("Lorem Ipsum", googleplay.description)
                assertEquals(false, googleplay.explicit)
                assertEquals(expectedPodcastImage, googleplay.image)
            } ?: run {
                fail("Podcast Googleplay data not extracted")
            }
        } ?: run {
            fail("channel not found")
        }
    }

    @Test
    fun testParseItemItunes() {
        item?.let { node ->
            val builder = EpisodeBuilder()
            parseItemNode(builder, node)

            builder.build().googleplay?.let { googleplay ->
                assertEquals("Lorem Ipsum", googleplay.description)
                assertEquals("03:24:27", googleplay.duration)
                assertEquals(false, googleplay.explicit)
                assertEquals(false, googleplay.block)
                assertEquals(expectedEpisodeImage, googleplay.image)
                // TODO test more fields
            } ?: run {
                fail("Episode Googleplay data not extracted")
            }
        } ?: run {
            fail("item not found")
        }
    }
}
