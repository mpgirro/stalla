package dev.stalla.parser.namespace

import assertk.all
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEmpty
import assertk.assertions.prop
import dev.stalla.builder.fake.FakeAtomBuilder
import dev.stalla.builder.fake.FakeLinkBuilder
import dev.stalla.builder.fake.FakePersonBuilder
import dev.stalla.builder.fake.episode.FakeEpisodeBuilder
import dev.stalla.builder.fake.podcast.FakePodcastBuilder
import dev.stalla.dom.XmlRes
import dev.stalla.noneHasEnoughDataToBuild
import dev.stalla.parser.NamespaceParserTest
import org.junit.jupiter.api.Test
import org.w3c.dom.Node

internal class AtomParserTest : NamespaceParserTest() {

    override val parser = AtomParser

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

        assertThat(builder.atomBuilder, "atom podcast data").all {
            prop(FakeAtomBuilder::authorBuilders).containsExactly(expectedPersonBuilder)
            prop(FakeAtomBuilder::contributorBuilders).containsExactly(expectedPersonBuilder)
            prop(FakeAtomBuilder::linkBuilders).containsExactly(expectedLinkBuilder)
        }
    }

    @Test
    fun `should extract nothing from channel when atom data is all empty`() {
        val channel: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel")
        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.atomBuilder, "atom episode data").all {
            prop(FakeAtomBuilder::authorBuilders).noneHasEnoughDataToBuild()
            prop(FakeAtomBuilder::contributorBuilders).noneHasEnoughDataToBuild()
            prop(FakeAtomBuilder::linkBuilders).isEmpty()
        }
    }

    @Test
    fun `should extract nothing from channel when no atom data is present`() {
        val channel: Node = XmlRes("/xml/channel-incomplete.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.atomBuilder, "atom episode data").all {
            prop(FakeAtomBuilder::authorBuilders).isEmpty()
            prop(FakeAtomBuilder::contributorBuilders).isEmpty()
            prop(FakeAtomBuilder::linkBuilders).isEmpty()
        }
    }

    @Test
    fun `should extract all atom fields from item when present`() {
        val item: Node = XmlRes("/xml/item.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        item.parseItemChildNodes(builder)

        assertThat(builder.atomBuilder, "atom item data").all {
            prop(FakeAtomBuilder::authorBuilders).containsExactly(expectedPersonBuilder)
            prop(FakeAtomBuilder::contributorBuilders).containsExactly(expectedPersonBuilder)
            prop(FakeAtomBuilder::linkBuilders).containsExactly(expectedLinkBuilder)
        }
    }

    @Test
    fun `should extract nothing from item when no atom data is present`() {
        val item: Node = XmlRes("/xml/item-incomplete.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        item.parseItemChildNodes(builder)

        assertThat(builder.atomBuilder, "atom item data").all {
            prop(FakeAtomBuilder::authorBuilders).isEmpty()
            prop(FakeAtomBuilder::contributorBuilders).isEmpty()
            prop(FakeAtomBuilder::linkBuilders).isEmpty()
        }
    }

    @Test
    fun `should extract nothing from item when atom data is all empty`() {
        val item: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel/item")
        val builder = FakeEpisodeBuilder()
        item.parseItemChildNodes(builder)

        assertThat(builder.atomBuilder, "atom item data").all {
            prop(FakeAtomBuilder::authorBuilders).noneHasEnoughDataToBuild()
            prop(FakeAtomBuilder::contributorBuilders).noneHasEnoughDataToBuild()
            prop(FakeAtomBuilder::linkBuilders).isEmpty()
        }
    }
}
