package io.hemin.wien.parser

import io.hemin.wien.model.Episode
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
    val expactedEnclosure: Episode.Enclosure = Episode.Enclosure(
        url    = "http://example.org/episode1.m4a",
        length = 78589133,
        type   = "audio/mp4")

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
            assertEquals("editor@example.com", p.managingEditor, "managingEditor was not as expected")
            assertEquals("webmaster@example.com", p.webMaster, "webMaster was not as expected")
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
            assertEquals(expactedDate, e.pubDate, "pubDate was not as expected")
            assertEquals("1fa609024fdf097", e.guid, "guid was not as expected")
            assertEquals(expactedEnclosure, e.enclosure, "expactedEnclosure was not as expected")
            // TODO more fields
        } ?: run {
            fail("item not found")
        }
    }

}
