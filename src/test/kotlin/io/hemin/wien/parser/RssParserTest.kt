package io.hemin.wien.parser

import io.hemin.wien.model.Episode
import io.hemin.wien.model.Image
import io.hemin.wien.model.Podcast
import io.hemin.wien.model.builder.EpisodeBuilder
import io.hemin.wien.model.builder.PodcastBuilder
import org.w3c.dom.Node
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class RssParserTest : NamespaceParserTest() {

    override val parser = RssParser()

    val channel: Node? = nodeFromResource("channel", "/xml/channel.xml")
    val item: Node? = nodeFromResource("item", "/xml/item.xml")

    val expactedDate: Date? = DateParser.parse("Fri, 16 Mar 2018 22:49:08 +0000")
    val expactedEnclosure = Episode.Enclosure(
        url    = "http://example.org/episode1.m4a",
        length = 78589133,
        type   = "audio/mp4")
    val expectedGuid = Episode.Guid(
        value       = "1fa609024fdf097",
        permalink = true
    )
    val expectedImage = Image(
        url         = "http://example.org/podcast-cover.jpg",
        title       = "Lorem Ipsum",
        link        = "http://example.org",
        width       = 600,
        height      = 600,
        description = "Lorem Ipsum"
    )

    @Test
    fun testParseChannel() {
        channel?.let {
            val builder = PodcastBuilder()
            parse(builder, it)
            val p: Podcast = builder.build()

            assertEquals("Lorem Ipsum", p.title, "title was not as expected")
            assertEquals("http://example.org", p.link, "link was not as expected")
            assertEquals("Lorem Ipsum", p.description, "description was not as expected")
            assertEquals(expactedDate, p.pubDate, "pubDate was not as expected")
            assertEquals(expactedDate, p.lastBuildDate, "lastBuildDate was not as expected")
            assertEquals("de-DE", p.language, "language was not as expected")
            assertEquals("Lorem Ipsum", p.generator, "generator was not as expected")
            assertEquals("Lorem Ipsum", p.copyright, "copyright was not as expected")
            assertEquals("Lorem Ipsum", p.docs, "docs was not as expected")
            assertEquals("editor@example.org", p.managingEditor, "managingEditor was not as expected")
            assertEquals("webmaster@example.org", p.webMaster, "webMaster was not as expected")
            assertEquals(expectedImage, p.image, "image was not as expected")
        } ?: run {
            fail("channel not found")
        }
    }

    @Test
    fun testParseItem() {
        item?.let {
            val builder = EpisodeBuilder()
            parse(builder, it)
            val e: Episode = builder.build()

            assertEquals("Lorem Ipsum", e.title, "title was not as expected")
            assertEquals("http://example.org/episode1", e.link, "link was not as expected")
            assertEquals("Lorem Ipsum", e.description, "description was not as expected")
            assertEquals("author@example.org", e.author, "author was not as expected")
            assertEquals(listOf("category1","category2"), e.categories, "categories was not as expected")
            assertEquals("http://example.org/episode1/comments", e.comments, "comments was not as expected")
            assertEquals(expactedEnclosure, e.enclosure, "expactedEnclosure was not as expected")
            assertEquals(expectedGuid, e.guid, "guid was not as expected")
            assertEquals(expactedDate, e.pubDate, "pubDate was not as expected")
            assertEquals("http://example.org/rss", e.source, "source was not as expected")
        } ?: run {
            fail("item not found")
        }
    }

}
