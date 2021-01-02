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
internal class AtomParserTest : NamespaceParserTest() {

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
        channel?.let { node ->
            val builder = PodcastBuilder()
            parse(builder, node)

            builder.build().atom?.let { atom ->
                assertTrue(atom.authors.contains(expectedPerson))
                assertTrue(atom.contributors.contains(expectedPerson))
                assertTrue(atom.links.contains(expectedLink))
            } ?: run {
                fail("Podcast Atom data not extracted")
            }
        } ?: run {
            fail("channel not found")
        }
    }

    @Test
    fun testParseItemAtom() {
        item?.let { node ->
            val builder = EpisodeBuilder()
            parse(builder, node)

            builder.build().atom?.let { atom ->
                assertEquals(1, atom.authors.size)
                assertTrue(atom.authors.contains(expectedPerson))

                assertEquals(1, atom.contributors.size)
                assertTrue(atom.contributors.contains(expectedPerson))

                assertEquals(1, atom.links.size)
                assertTrue(atom.links.contains(expectedLink))
            } ?: run {
                fail("Episode Atom data not extracted")
            }
        } ?: run {
            fail("item not found")
        }
    }
}
