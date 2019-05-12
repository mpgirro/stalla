package io.hemin.wien.parser

import io.hemin.wien.builder.EpisodeBuilder
import io.hemin.wien.builder.LinkBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.builder.PodcastBuilder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.w3c.dom.Node

/** Provides unit tests for [AtomParser]. */
class AtomParserTest : NamespaceParserTest() {

    override val parser = AtomParser()

    private val channel: Node? = nodeFromResource("channel", "/xml/channel.xml")
    private val item: Node? = nodeFromResource("item", "/xml/item.xml")

    private val expectedLink = LinkBuilder()
        .href("http://example.org/feed/m4a")
        .rel("self")
        .title("Lorem Ipsum")
        .type("application/rss+xml")
        .build()
    private val expectedPerson = PersonBuilder()
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
            } ?: run {
                fail("Podcast Atom data not extracted")
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
                assertEquals(1, it.authors.size)
                assertTrue(it.authors.contains(expectedPerson))

                assertEquals(1, it.contributors.size)
                assertTrue(it.contributors.contains(expectedPerson))

                assertEquals(1, it.links.size)
                assertTrue(it.links.contains(expectedLink))
            } ?: run {
                fail("Episode Atom data not extracted")
            }
        } ?: run {
            fail("item not found")
        }
    }

}
