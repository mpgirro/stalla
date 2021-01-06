package io.hemin.wien.parser

import assertk.all
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEmpty
import assertk.assertions.prop
import io.hemin.wien.builder.fake.FakeLinkBuilder
import io.hemin.wien.builder.fake.FakePersonBuilder
import io.hemin.wien.builder.fake.episode.FakeEpisodeAtomBuilder
import io.hemin.wien.builder.fake.episode.FakeEpisodeBuilder
import io.hemin.wien.builder.fake.podcast.FakePodcastAtomBuilder
import io.hemin.wien.builder.fake.podcast.FakePodcastBuilder
import io.hemin.wien.dom.XmlRes
import io.hemin.wien.parser.namespace.AtomParser
import org.junit.jupiter.api.Test
import org.w3c.dom.Node

internal class AtomParserTest : NamespaceParserTest() {

    override val parser = AtomParser()

    private val expectedLinkBuilder = FakeLinkBuilder()
        .href("http://example.org/feed/m4a")
        .rel("self")
        .title("Lorem Ipsum")
        .type("application/rss+xml")

    private val expectedPersonBuilder = FakePersonBuilder()
        .name("Lorem Ipsum")
        .email("person@example.org")
        .uri("http://example.org")

    @Test
    fun `should extract all atom fields from channel when present`() {
        val channel: Node = XmlRes("/xml/channel.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.atom, "atom podcast data").all {
            prop(FakePodcastAtomBuilder::authorBuilders).containsExactly(expectedPersonBuilder)
            prop(FakePodcastAtomBuilder::contributorBuilders).containsExactly(expectedPersonBuilder)
            prop(FakePodcastAtomBuilder::linkBuilders).containsExactly(expectedLinkBuilder)
        }
    }

    @Test
    fun `should extract nothing from channel when atom data is all empty`() {
        val channel: Node = XmlRes("/xml/rss-all-empty.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.atom, "atom episode data").all {
            prop(FakePodcastAtomBuilder::authorBuilders).isEmpty()
            prop(FakePodcastAtomBuilder::contributorBuilders).isEmpty()
            prop(FakePodcastAtomBuilder::linkBuilders).isEmpty()
        }
    }

    @Test
    fun `should extract nothing from channel when no atom data is present`() {
        val channel: Node = XmlRes("/xml/channel-incomplete.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.atom, "atom episode data").all {
            prop(FakePodcastAtomBuilder::authorBuilders).isEmpty()
            prop(FakePodcastAtomBuilder::contributorBuilders).isEmpty()
            prop(FakePodcastAtomBuilder::linkBuilders).isEmpty()
        }
    }

    @Test
    fun `should extract all atom fields from item when present`() {
        val item: Node = XmlRes("/xml/item.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        item.parseItemChildNodes(builder)

        assertThat(builder.atom, "atom item data").all {
            prop(FakeEpisodeAtomBuilder::authorBuilders).containsExactly(expectedPersonBuilder)
            prop(FakeEpisodeAtomBuilder::contributorBuilders).containsExactly(expectedPersonBuilder)
            prop(FakeEpisodeAtomBuilder::linkBuilders).containsExactly(expectedLinkBuilder)
        }
    }

    @Test
    fun `should extract nothing from item when no atom data is present`() {
        val item: Node = XmlRes("/xml/item-incomplete.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        item.parseItemChildNodes(builder)

        assertThat(builder.atom, "atom item data").all {
            prop(FakeEpisodeAtomBuilder::authorBuilders).isEmpty()
            prop(FakeEpisodeAtomBuilder::contributorBuilders).isEmpty()
            prop(FakeEpisodeAtomBuilder::linkBuilders).isEmpty()
        }
    }
}
