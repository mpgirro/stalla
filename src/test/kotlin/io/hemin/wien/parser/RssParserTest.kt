package io.hemin.wien.parser

import io.hemin.wien.builder.EpisodeBuilder
import io.hemin.wien.builder.PodcastBuilder
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Image
import io.hemin.wien.model.Podcast
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.w3c.dom.Node
import java.util.Date

/** Provides unit tests for [RssParser]. */
internal class RssParserTest : NamespaceParserTest() {

    override val parser = RssParser()

    private val channel: Node? = nodeFromResource("channel", "/xml/channel.xml")
    private val item: Node? = nodeFromResource("item", "/xml/item.xml")

    private val expectedDate: Date? = DateParser.parse("Fri, 16 Mar 2018 22:49:08 +0000")
    private val expectedEnclosure = Episode.Enclosure(
        url = "http://example.org/episode1.m4a",
        length = 78589133,
        type = "audio/mp4")
    private val expectedGuid = Episode.Guid(
        textContent = "1fa609024fdf097",
        isPermalink = true
    )
    private val expectedImage = Image(
        url = "http://example.org/podcast-cover.jpg",
        title = "Lorem Ipsum",
        link = "http://example.org",
        width = 600,
        height = 600,
        description = "Lorem Ipsum"
    )

    @Test
    fun testParseChannel() {
        channel?.let { node ->
            val builder = PodcastBuilder()
            parse(builder, node)
            val podcast: Podcast = builder.build()

            assertEquals("Lorem Ipsum", podcast.title)
            assertEquals("http://example.org", podcast.link)
            assertEquals("Lorem Ipsum", podcast.description)
            assertEquals(expectedDate, podcast.pubDate)
            assertEquals(expectedDate, podcast.lastBuildDate)
            assertEquals("de-DE", podcast.language)
            assertEquals("Lorem Ipsum", podcast.generator)
            assertEquals("Lorem Ipsum", podcast.copyright)
            assertEquals("Lorem Ipsum", podcast.docs)
            assertEquals("editor@example.org", podcast.managingEditor)
            assertEquals("webmaster@example.org", podcast.webMaster)
            assertEquals(expectedImage, podcast.image)
        } ?: run {
            fail("channel not found")
        }
    }

    @Test
    fun testChannelMissingElements() {
        val incompleteChannel: Node? = nodeFromResource("channel", "/xml/channel-incomplete.xml")
        incompleteChannel?.let { node ->
            val builder = PodcastBuilder()
            parse(builder, node)
            val podcast: Podcast = builder.build()

            assertNull(podcast.image)
        } ?: run {
            fail("channel not found")
        }
    }

    @Test
    fun testParseItem() {
        item?.let { node ->
            val builder = EpisodeBuilder()
            parse(builder, node)
            val episode: Episode = builder.build()

            assertEquals("Lorem Ipsum", episode.title)
            assertEquals("http://example.org/episode1", episode.link)
            assertEquals("Lorem Ipsum", episode.description)
            assertEquals("author@example.org", episode.author)
            assertEquals(listOf("category1", "category2"), episode.categories)
            assertEquals("http://example.org/episode1/comments", episode.comments)
            assertEquals(expectedEnclosure, episode.enclosure)
            assertEquals(expectedGuid, episode.guid)
            assertEquals(expectedDate, episode.pubDate)
            assertEquals("http://example.org/rss", episode.source)
        } ?: run {
            fail("item not found")
        }
    }

    @Test
    fun testItemMissingElements() {
        val incompleteItem: Node? = nodeFromResource("item", "/xml/item-incomplete.xml")
        incompleteItem?.let { node ->
            val builder = EpisodeBuilder()
            parse(builder, node)
            val episode: Episode = builder.build()

            assertNull(episode.enclosure)
            assertNull(episode.guid)
        } ?: run {
            fail("item not found")
        }
    }
}
