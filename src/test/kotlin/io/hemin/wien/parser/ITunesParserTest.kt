package io.hemin.wien.parser

import io.hemin.wien.builder.EpisodeBuilder
import io.hemin.wien.builder.ImageBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.builder.PodcastBuilder
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.w3c.dom.Node

/** Provides unit tests for [ItunesParser]. */
internal class ITunesParserTest : NamespaceParserTest() {

    override val parser = ItunesParser()

    private val channel: Node? = nodeFromResource("channel", "/xml/channel.xml")
    private val item: Node? = nodeFromResource("item", "/xml/item.xml")

    private val expectedPodcastImage = ImageBuilder()
        .url("http://example.org/podcast-cover.jpg")
        .build()
    private val expectedEpisodeImage = ImageBuilder()
        .url("http://example.org/episode-cover.jpg")
        .build()
    private val expectedOwner = PersonBuilder()
        .name("Lorem Ipsum")
        .email("owner@example.org")
        .build()

    @Test
    fun testParseChannelItunes() {
        channel?.let { node ->
            val builder = PodcastBuilder()
            parseChannelNode(builder, node)

            builder.build().iTunes?.let { itunes ->
                assertEquals("Lorem Ipsum", itunes.subtitle)
                assertEquals("Lorem Ipsum", itunes.summary)
                assertEquals(expectedPodcastImage, itunes.image)
                assertEquals("Lorem Ipsum", itunes.keywords)
                assertEquals("Lorem Ipsum", itunes.author)
                // assertEquals(emptyList<String>(), itunes.categories) // TODO this will fail --> see XML
                assertEquals(false, itunes.explicit)
                assertEquals(false, itunes.block)
                assertEquals(false, itunes.complete)
                assertEquals(Podcast.ITunes.ShowType.EPISODIC, itunes.type)
                assertEquals(expectedOwner, itunes.owner)
            } ?: run {
                fail("Podcast iTunes data not extracted")
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

            builder.build().itunes?.let { itunes ->
                assertEquals("Lorem Ipsum", itunes.title)
                assertEquals("03:24:27", itunes.duration)
                assertEquals(expectedEpisodeImage, itunes.image)
                assertEquals(false, itunes.explicit)
                assertEquals(false, itunes.block)
                assertEquals(1, itunes.season)
                assertEquals(1, itunes.episode)
                assertEquals(Episode.Itunes.EpisodeType.FULL, itunes.episodeType)
                // TODO test more fields
            } ?: run {
                fail("Episode iTunes data not extracted")
            }
        } ?: run {
            fail("item not found")
        }
    }
}
