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

    private val expectedPodcastLinkBuilder = FakeLinkBuilder()
        .href("podcast atom link href")
        .rel("podcast atom link rel")
        .title("podcast atom link title")
        .type("podcast atom link type")

    private val expectedPodcastAuthorBuilder = FakePersonBuilder()
        .name("podcast atom author name")
        .email("podcast atom author email")
        .uri("podcast atom author uri")

    private val expectedPodcastContributorBuilder = FakePersonBuilder()
        .name("podcast atom contributor name")
        .email("podcast atom contributor email")
        .uri("podcast atom contributor uri")

    private val expectedEpisodeLinkBuilder = FakeLinkBuilder()
        .href("episode atom link href")
        .rel("episode atom link rel")
        .title("episode atom link title")
        .type("episode atom link type")

    private val expectedEpisodeAuthorBuilder = FakePersonBuilder()
        .name("episode atom author name")
        .email("episode atom author email")
        .uri("episode atom author uri")

    private val expectedEpisodeContributorBuilder = FakePersonBuilder()
        .name("episode atom contributor name")
        .email("episode atom contributor email")
        .uri("episode atom contributor uri")

    @Test
    fun `should extract all Atom fields from channel when present`() {
        val channel: Node = XmlRes("/xml/channel.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.atomBuilder, "channel.atom").all {
            prop(FakeAtomBuilder::authorBuilders).containsExactly(expectedPodcastAuthorBuilder)
            prop(FakeAtomBuilder::contributorBuilders).containsExactly(expectedPodcastContributorBuilder)
            prop(FakeAtomBuilder::linkBuilders).containsExactly(expectedPodcastLinkBuilder)
        }
    }

    @Test
    fun `should extract nothing from channel when Atom data is all empty`() {
        val channel: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel")
        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.atomBuilder, "channel.atom").all {
            prop(FakeAtomBuilder::authorBuilders).noneHasEnoughDataToBuild()
            prop(FakeAtomBuilder::contributorBuilders).noneHasEnoughDataToBuild()
            prop(FakeAtomBuilder::linkBuilders).isEmpty()
        }
    }

    @Test
    fun `should extract nothing from channel when no Atom data is present`() {
        val channel: Node = XmlRes("/xml/channel-incomplete.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.atomBuilder, "channel.atom").all {
            prop(FakeAtomBuilder::authorBuilders).isEmpty()
            prop(FakeAtomBuilder::contributorBuilders).isEmpty()
            prop(FakeAtomBuilder::linkBuilders).isEmpty()
        }
    }

    @Test
    fun `should extract all Atom fields from item when present`() {
        val item: Node = XmlRes("/xml/item.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        item.parseItemChildNodes(builder)

        assertThat(builder.atomBuilder, "item.atom").all {
            prop(FakeAtomBuilder::authorBuilders).containsExactly(expectedEpisodeAuthorBuilder)
            prop(FakeAtomBuilder::contributorBuilders).containsExactly(expectedEpisodeContributorBuilder)
            prop(FakeAtomBuilder::linkBuilders).containsExactly(expectedEpisodeLinkBuilder)
        }
    }

    @Test
    fun `should extract nothing from item when no Atom data is present`() {
        val item: Node = XmlRes("/xml/item-incomplete.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        item.parseItemChildNodes(builder)

        assertThat(builder.atomBuilder, "item.atom").all {
            prop(FakeAtomBuilder::authorBuilders).isEmpty()
            prop(FakeAtomBuilder::contributorBuilders).isEmpty()
            prop(FakeAtomBuilder::linkBuilders).isEmpty()
        }
    }

    @Test
    fun `should extract nothing from item when Atom data is all empty`() {
        val item: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel/item")
        val builder = FakeEpisodeBuilder()
        item.parseItemChildNodes(builder)

        assertThat(builder.atomBuilder, "item.atom").all {
            prop(FakeAtomBuilder::authorBuilders).noneHasEnoughDataToBuild()
            prop(FakeAtomBuilder::contributorBuilders).noneHasEnoughDataToBuild()
            prop(FakeAtomBuilder::linkBuilders).isEmpty()
        }
    }
}
