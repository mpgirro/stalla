package dev.stalla.parser.namespace

import assertk.all
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import dev.stalla.builder.fake.FakeHrefOnlyImageBuilder
import dev.stalla.builder.fake.FakeItunesStyleCategoryBuilder
import dev.stalla.builder.fake.FakePersonBuilder
import dev.stalla.builder.fake.episode.FakeEpisodeBuilder
import dev.stalla.builder.fake.episode.FakeEpisodeItunesBuilder
import dev.stalla.builder.fake.podcast.FakePodcastBuilder
import dev.stalla.builder.fake.podcast.FakePodcastItunesBuilder
import dev.stalla.dom.XmlRes
import dev.stalla.hasNotEnoughDataToBuild
import dev.stalla.model.itunes.EpisodeType
import dev.stalla.model.itunes.ShowType
import dev.stalla.noneHasEnoughDataToBuild
import dev.stalla.parser.NamespaceParserTest
import org.junit.jupiter.api.Test
import org.w3c.dom.Node

internal class ItunesParserTest : NamespaceParserTest() {

    override val parser = ItunesParser

    private val expectedPodcastImageBuilder = FakeHrefOnlyImageBuilder().href("http://example.org/podcast-cover.jpg")

    private val expectedEpisodeImageBuilder = FakeHrefOnlyImageBuilder().href("http://example.org/episode-cover.jpg")

    private val expectedOwnerBuilder = FakePersonBuilder()
        .name("Lorem Ipsum")
        .email("owner@example.org")

    @Test
    fun `should extract all itunes fields from channel when present`() {
        val node = XmlRes("/xml/channel.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        node.parseChannelChildNodes(builder)

        assertThat(builder.itunesBuilder, "channel.itunes").all {
            prop(FakePodcastItunesBuilder::author).isEqualTo("Lorem Ipsum")
            prop(FakePodcastItunesBuilder::ownerBuilder).isEqualTo(expectedOwnerBuilder)
            prop(FakePodcastItunesBuilder::categoryBuilders).containsExactly(
                FakeItunesStyleCategoryBuilder().category("Technology").subcategory("Tech News"),
                FakeItunesStyleCategoryBuilder().category("Society & Culture"),
                FakeItunesStyleCategoryBuilder().category("Technology").subcategory("Gadgets")
            )
            prop(FakePodcastItunesBuilder::subtitle).isEqualTo("Lorem Ipsum")
            prop(FakePodcastItunesBuilder::summary).isEqualTo("Lorem Ipsum")
            prop(FakePodcastItunesBuilder::keywords).isEqualTo("Lorem Ipsum")
            prop(FakePodcastItunesBuilder::explicit).isNotNull().isFalse()
            prop(FakePodcastItunesBuilder::block).isNotNull().isFalse()
            prop(FakePodcastItunesBuilder::complete).isNotNull().isFalse()
            prop(FakePodcastItunesBuilder::imageBuilderValue).isEqualTo(expectedPodcastImageBuilder)
            prop(FakePodcastItunesBuilder::type).isEqualTo(ShowType.EPISODIC)
            prop(FakePodcastItunesBuilder::title).isEqualTo("podcast itunes title")
            prop(FakePodcastItunesBuilder::newFeedUrl).isEqualTo("podcast itunes new-feed-url")
        }
    }

    @Test
    fun `should not extract itunes fields from channel when absent`() {
        val node = XmlRes("/xml/channel-incomplete.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        node.parseChannelChildNodes(builder)

        assertThat(builder.itunesBuilder, "channel.itunes").all {
            prop(FakePodcastItunesBuilder::author).isNull()
            prop(FakePodcastItunesBuilder::ownerBuilder).isNull()
            prop(FakePodcastItunesBuilder::categoryBuilders).isEmpty()
            prop(FakePodcastItunesBuilder::subtitle).isNull()
            prop(FakePodcastItunesBuilder::summary).isNull()
            prop(FakePodcastItunesBuilder::keywords).isNull()
            prop(FakePodcastItunesBuilder::explicit).isNull()
            prop(FakePodcastItunesBuilder::block).isNull()
            prop(FakePodcastItunesBuilder::complete).isNull()
            prop(FakePodcastItunesBuilder::imageBuilderValue).isNull()
            prop(FakePodcastItunesBuilder::type).isNull()
            prop(FakePodcastItunesBuilder::title).isNull()
            prop(FakePodcastItunesBuilder::newFeedUrl).isNull()
        }
    }

    @Test
    fun `should extract nothing from channel when itunes data is all empty`() {
        val channel: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel")
        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.itunesBuilder, "channel.itunes").all {
            prop(FakePodcastItunesBuilder::author).isNull()
            prop(FakePodcastItunesBuilder::ownerBuilder).isNotNull().hasNotEnoughDataToBuild()
            prop(FakePodcastItunesBuilder::categoryBuilders).noneHasEnoughDataToBuild()
            prop(FakePodcastItunesBuilder::subtitle).isNull()
            prop(FakePodcastItunesBuilder::summary).isNull()
            prop(FakePodcastItunesBuilder::keywords).isNull()
            prop(FakePodcastItunesBuilder::explicit).isNull()
            prop(FakePodcastItunesBuilder::block).isNull()
            prop(FakePodcastItunesBuilder::complete).isNull()
            prop(FakePodcastItunesBuilder::imageBuilderValue).isNotNull().hasNotEnoughDataToBuild()
            prop(FakePodcastItunesBuilder::type).isNull()
            prop(FakePodcastItunesBuilder::title).isNull()
            prop(FakePodcastItunesBuilder::newFeedUrl).isNull()
        }
    }

    @Test
    fun `should extract all itunes fields from item when present`() {
        val node = XmlRes("/xml/item.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        node.parseItemChildNodes(builder)

        assertThat(builder.itunesBuilder, "item.itunes").all {
            prop(FakeEpisodeItunesBuilder::title).isEqualTo("Lorem Ipsum")
            prop(FakeEpisodeItunesBuilder::duration).isEqualTo("03:24:27")
            prop(FakeEpisodeItunesBuilder::season).isEqualTo(1)
            prop(FakeEpisodeItunesBuilder::episode).isEqualTo(1)
            prop(FakeEpisodeItunesBuilder::explicit).isNotNull().isFalse()
            prop(FakeEpisodeItunesBuilder::block).isNotNull().isFalse()
            prop(FakeEpisodeItunesBuilder::imageBuilder).isEqualTo(expectedEpisodeImageBuilder)
            prop(FakeEpisodeItunesBuilder::episodeType).isEqualTo(EpisodeType.FULL)
            prop(FakeEpisodeItunesBuilder::author).isEqualTo("author")
            prop(FakeEpisodeItunesBuilder::subtitle).isEqualTo("subtitle")
            prop(FakeEpisodeItunesBuilder::summary).isEqualTo("summary")
        }
    }

    @Test
    fun `should not extract itunes fields from item when absent`() {
        val node = XmlRes("/xml/item-incomplete.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        node.parseItemChildNodes(builder)

        assertThat(builder.itunesBuilder, "item.itunes").all {
            prop(FakeEpisodeItunesBuilder::title).isNull()
            prop(FakeEpisodeItunesBuilder::duration).isNull()
            prop(FakeEpisodeItunesBuilder::season).isNull()
            prop(FakeEpisodeItunesBuilder::episode).isNull()
            prop(FakeEpisodeItunesBuilder::explicit).isNull()
            prop(FakeEpisodeItunesBuilder::block).isNull()
            prop(FakeEpisodeItunesBuilder::imageBuilder).isNull()
            prop(FakeEpisodeItunesBuilder::episodeType).isNull()
            prop(FakeEpisodeItunesBuilder::author).isNull()
            prop(FakeEpisodeItunesBuilder::subtitle).isNull()
            prop(FakeEpisodeItunesBuilder::summary).isNull()
        }
    }

    @Test
    fun `should extract nothing from item when itunes data is all empty`() {
        val channel: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel/item")
        val builder = FakeEpisodeBuilder()
        channel.parseItemChildNodes(builder)

        assertThat(builder.itunesBuilder, "item.itunes").all {
            prop(FakeEpisodeItunesBuilder::title).isNull()
            prop(FakeEpisodeItunesBuilder::duration).isNull()
            prop(FakeEpisodeItunesBuilder::season).isNull()
            prop(FakeEpisodeItunesBuilder::episode).isNull()
            prop(FakeEpisodeItunesBuilder::explicit).isNull()
            prop(FakeEpisodeItunesBuilder::block).isNull()
            prop(FakeEpisodeItunesBuilder::imageBuilder).isNotNull().hasNotEnoughDataToBuild()
            prop(FakeEpisodeItunesBuilder::episodeType).isNull()
            prop(FakeEpisodeItunesBuilder::author).isNull()
            prop(FakeEpisodeItunesBuilder::subtitle).isNull()
            prop(FakeEpisodeItunesBuilder::summary).isNull()
        }
    }
}
