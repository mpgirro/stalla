package io.hemin.wien.parser

import io.hemin.wien.builder.EpisodeBuilder
import io.hemin.wien.builder.ImageBuilder
import io.hemin.wien.builder.PodcastBuilder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.w3c.dom.Node

class GoogleplayParserTest : NamespaceParserTest() {

    override val parser: NamespaceParser = GoogleplayParser()

    val channel: Node? = nodeFromResource("channel", "/xml/channel.xml")
    val item: Node? = nodeFromResource("item", "/xml/item.xml")

    val expectedPodcastImage = ImageBuilder()
        .url("http://example.org/podcast-cover.jpg")
        .build()

    @Test
    fun testParseChannelItunes() {
        channel?.let {
            val builder = PodcastBuilder()
            parse(builder, it)

            builder.build().googleplay?.let {
                assertEquals("Lorem Ipsum", it.author)
                //assertEquals(emptyList<String>(), it.categories) // TODO this will fail --> see XML
                assertEquals("Lorem Ipsum", it.description)
                assertEquals(false, it.explicit)
                assertEquals(expectedPodcastImage, it.image)
            } ?: run {
                fail("Podcast Googleplay data not extracted")
            }
        } ?: run {
            fail("channel not found")
        }
    }

    @Test
    fun testParseItemItunes() {
        item?.let {
            val builder = EpisodeBuilder()
            parse(builder, it)

            builder.build().googleplay?.let {
                assertEquals("Lorem Ipsum", it.description)
                assertEquals("03:24:27", it.duration)
                assertEquals(false, it.explicit)
                // TODO test more fields
            } ?: run {
                fail("Episode Googleplay data not extracted")
            }
        } ?: run {
            fail("item not found")
        }
    }

}
