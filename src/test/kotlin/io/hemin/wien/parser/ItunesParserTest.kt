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
class ItunesParserTest : NamespaceParserTest() {

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
        channel?.let {
            val builder = PodcastBuilder()
            parse(builder, it)

            builder.build().itunes?.let {
                assertEquals("Lorem Ipsum", it.subtitle)
                assertEquals("Lorem Ipsum", it.summary)
                assertEquals(expectedPodcastImage, it.image)
                assertEquals("Lorem Ipsum", it.keywords)
                assertEquals("Lorem Ipsum", it.author)
                //assertEquals(emptyList<String>(), it.categories) // TODO this will fail --> see XML
                assertEquals(false, it.explicit)
                assertEquals(false, it.block)
                assertEquals(false, it.complete)
                assertEquals(Podcast.Itunes.ShowType.EPISODIC, it.type)
                assertEquals(expectedOwner, it.owner)
            } ?: run {
                fail("Podcast iTunes data not extracted")
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

            builder.build().itunes?.let {
                assertEquals("Lorem Ipsum", it.title)
                assertEquals("03:24:27", it.duration)
                assertEquals(expectedEpisodeImage, it.image)
                assertEquals(false, it.explicit)
                assertEquals(false, it.block)
                assertEquals(1, it.season)
                assertEquals(1, it.episode)
                assertEquals(Episode.Itunes.EpisodeType.FULL, it.episodeType)
                // TODO test more fields
            } ?: run {
                fail("Episode iTunes data not extracted")
            }
        } ?: run {
            fail("item not found")
        }
    }

}
