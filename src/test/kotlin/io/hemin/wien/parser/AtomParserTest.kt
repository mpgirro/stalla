package io.hemin.wien.parser

import assertk.all
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEmpty
import assertk.assertions.prop
import io.hemin.wien.builder.fake.episode.FakeEpisodeAtomBuilder
import io.hemin.wien.builder.fake.episode.FakeEpisodeBuilder
import io.hemin.wien.builder.fake.podcast.FakePodcastAtomBuilder
import io.hemin.wien.builder.fake.podcast.FakePodcastBuilder
import io.hemin.wien.model.Link
import io.hemin.wien.model.Person
import io.hemin.wien.parser.namespace.AtomParser
import org.junit.jupiter.api.Test
import org.w3c.dom.Node

/** Provides unit tests for [AtomParser]. */
internal class AtomParserTest : NamespaceParserTest() {

    override val parser = AtomParser()

    private val expectedLink = Link(
        href = "http://example.org/feed/m4a",
        rel = "self",
        title = "Lorem Ipsum",
        type = "application/rss+xml"
    )

    private val expectedPerson = Person(
        name = "Lorem Ipsum",
        email = "person@example.org",
        uri = "http://example.org"
    )

    @Test
    fun `should extract all atom fields from channel when present`() {
        val channel: Node = nodeFromResource("channel", "/xml/channel.xml")
        val builder = FakePodcastBuilder()
        parseChannelNode(builder, channel)

        assertThat(builder.atom, "atom podcast data").all {
            prop(FakePodcastAtomBuilder::authors).containsExactly(expectedPerson)
            prop(FakePodcastAtomBuilder::contributors).containsExactly(expectedPerson)
            prop(FakePodcastAtomBuilder::links).containsExactly(expectedLink)
        }
    }

    @Test
    fun `should extract nothing from channel when no atom data is present`() {
        val channel: Node = nodeFromResource("channel", "/xml/channel-incomplete.xml")
        val builder = FakePodcastBuilder()
        parseChannelNode(builder, channel)

        assertThat(builder.atom, "atom episode data").all {
            prop(FakePodcastAtomBuilder::authors).isEmpty()
            prop(FakePodcastAtomBuilder::contributors).isEmpty()
            prop(FakePodcastAtomBuilder::links).isEmpty()
        }
    }

    @Test
    fun `should extract all atom fields from item when present`() {
        val item: Node = nodeFromResource("item", "/xml/item.xml")
        val builder = FakeEpisodeBuilder()
        parseItemNode(builder, item)

        assertThat(builder.atom, "atom item data").all {
            prop(FakeEpisodeAtomBuilder::authors).containsExactly(expectedPerson)
            prop(FakeEpisodeAtomBuilder::contributors).containsExactly(expectedPerson)
            prop(FakeEpisodeAtomBuilder::links).containsExactly(expectedLink)
        }
    }

    @Test
    fun `should extract nothing from item when no atom data is present`() {
        val item: Node = nodeFromResource("item", "/xml/item-incomplete.xml")
        val builder = FakeEpisodeBuilder()
        parseItemNode(builder, item)

        assertThat(builder.atom, "atom item data").all {
            prop(FakeEpisodeAtomBuilder::authors).isEmpty()
            prop(FakeEpisodeAtomBuilder::contributors).isEmpty()
            prop(FakeEpisodeAtomBuilder::links).isEmpty()
        }
    }
}
