package io.hemin.wien.parser

import io.hemin.wien.builder.EpisodeBuilder
import io.hemin.wien.builder.LinkBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.builder.PodcastBuilder
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.w3c.dom.Node

class AtomParserTest : NamespaceParserTest() {

    override val parser = AtomParser()

    val channel: Node? = nodeFromResource("channel", "/xml/channel.xml")
    val item: Node? = nodeFromResource("item", "/xml/item.xml")

    val expectedLink = LinkBuilder()
        .href("http://example.org/feed/m4a")
        .rel("self")
        .title("Lorem Ipsum")
        .type("application/rss+xml")
        .build()
    val expectedPerson = PersonBuilder()
        .name("Lorem Ipsum")
        .email("person@example.org")
        .uri("http://example.org")
        .build()

    @Test
    fun testParseChannelAtom() {
        channel?.let {
            val builder = PodcastBuilder()
            parse(builder, it)

            builder.build().atom?.let {
                assertTrue(it.authors.contains(expectedPerson))
                assertTrue(it.contributors.contains(expectedPerson))
                assertTrue(it.links.contains(expectedLink))
            }
        } ?: run {
            fail("channel not found")
        }
    }

    @Test
    fun testParseItemAtom() {
        item?.let {
            val builder = EpisodeBuilder()
            parse(builder, it)

            builder.build().atom?.let {
                assertTrue(it.authors.contains(expectedPerson))
                assertTrue(it.contributors.contains(expectedPerson))
                assertTrue(it.links.contains(expectedLink))
            }
        } ?: run {
            fail("item not found")
        }
    }

}
