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
import dev.stalla.builder.fake.FakeItunesStyleCategoryBuilder2
import dev.stalla.builder.fake.FakePersonBuilder
import dev.stalla.builder.fake.episode.FakeEpisodeBuilder
import dev.stalla.builder.fake.episode.FakeEpisodeItunesBuilder2
import dev.stalla.builder.fake.podcast.FakePodcastBuilder
import dev.stalla.builder.fake.podcast.FakePodcastItunesBuilder2
import dev.stalla.dom.XmlRes
import dev.stalla.hasNotEnoughDataToBuild
import dev.stalla.model.itunes.EpisodeType
import dev.stalla.model.itunes.ShowType
import dev.stalla.noneHasEnoughDataToBuild
import dev.stalla.parser.NamespaceParserTest
import org.junit.jupiter.api.Test
import org.w3c.dom.Node

internal class ItunesParser2Test : NamespaceParserTest() {

    override val parser = ItunesParser2

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
            prop(FakePodcastItunesBuilder2::author).isEqualTo("Lorem Ipsum")
            prop(FakePodcastItunesBuilder2::ownerBuilder).isEqualTo(expectedOwnerBuilder)
            prop(FakePodcastItunesBuilder2::categoryBuilders).containsExactly(
                FakeItunesStyleCategoryBuilder2().category("Technology").subcategory("Tech News"),
                FakeItunesStyleCategoryBuilder2().category("Society & Culture"),
                FakeItunesStyleCategoryBuilder2().category("Technology").subcategory("Gadgets")
            )
            prop(FakePodcastItunesBuilder2::subtitle).isEqualTo("Lorem Ipsum")
            prop(FakePodcastItunesBuilder2::summary).isEqualTo("Lorem Ipsum")
            prop(FakePodcastItunesBuilder2::keywords).isEqualTo("Lorem Ipsum")
            prop(FakePodcastItunesBuilder2::explicit).isNotNull().isFalse()
            prop(FakePodcastItunesBuilder2::block).isNotNull().isFalse()
            prop(FakePodcastItunesBuilder2::complete).isNotNull().isFalse()
            prop(FakePodcastItunesBuilder2::imageBuilderValue).isEqualTo(expectedPodcastImageBuilder)
            prop(FakePodcastItunesBuilder2::type).isEqualTo(ShowType.EPISODIC)
            prop(FakePodcastItunesBuilder2::title).isEqualTo("podcast itunes title")
            prop(FakePodcastItunesBuilder2::newFeedUrl).isEqualTo("podcast itunes new-feed-url")
        }
    }

    @Test
    fun `should not extract itunes fields from channel when absent`() {
        val node = XmlRes("/xml/channel-incomplete.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        node.parseChannelChildNodes(builder)

        assertThat(builder.itunesBuilder, "channel.itunes").all {
            prop(FakePodcastItunesBuilder2::author).isNull()
            prop(FakePodcastItunesBuilder2::ownerBuilder).isNull()
            prop(FakePodcastItunesBuilder2::categoryBuilders).isEmpty()
            prop(FakePodcastItunesBuilder2::subtitle).isNull()
            prop(FakePodcastItunesBuilder2::summary).isNull()
            prop(FakePodcastItunesBuilder2::keywords).isNull()
            prop(FakePodcastItunesBuilder2::explicit).isNull()
            prop(FakePodcastItunesBuilder2::block).isNull()
            prop(FakePodcastItunesBuilder2::complete).isNull()
            prop(FakePodcastItunesBuilder2::imageBuilderValue).isNull()
            prop(FakePodcastItunesBuilder2::type).isNull()
            prop(FakePodcastItunesBuilder2::title).isNull()
            prop(FakePodcastItunesBuilder2::newFeedUrl).isNull()
        }
    }

    @Test
    fun `should extract nothing from channel when itunes data is all empty`() {
        val channel: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel")
        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.itunesBuilder, "channel.itunes").all {
            prop(FakePodcastItunesBuilder2::author).isNull()
            prop(FakePodcastItunesBuilder2::ownerBuilder).isNotNull().hasNotEnoughDataToBuild()
            prop(FakePodcastItunesBuilder2::categoryBuilders).noneHasEnoughDataToBuild()
            prop(FakePodcastItunesBuilder2::subtitle).isNull()
            prop(FakePodcastItunesBuilder2::summary).isNull()
            prop(FakePodcastItunesBuilder2::keywords).isNull()
            prop(FakePodcastItunesBuilder2::explicit).isNull()
            prop(FakePodcastItunesBuilder2::block).isNull()
            prop(FakePodcastItunesBuilder2::complete).isNull()
            prop(FakePodcastItunesBuilder2::imageBuilderValue).isNotNull().hasNotEnoughDataToBuild()
            prop(FakePodcastItunesBuilder2::type).isNull()
            prop(FakePodcastItunesBuilder2::title).isNull()
            prop(FakePodcastItunesBuilder2::newFeedUrl).isNull()
        }
    }

    @Test
    fun `should extract all itunes fields from item when present`() {
        val node = XmlRes("/xml/item.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        node.parseItemChildNodes(builder)

        assertThat(builder.itunesBuilder, "item.itunes").all {
            prop(FakeEpisodeItunesBuilder2::title).isEqualTo("Lorem Ipsum")
            prop(FakeEpisodeItunesBuilder2::duration).isEqualTo("03:24:27")
            prop(FakeEpisodeItunesBuilder2::season).isEqualTo(1)
            prop(FakeEpisodeItunesBuilder2::episode).isEqualTo(1)
            prop(FakeEpisodeItunesBuilder2::explicit).isNotNull().isFalse()
            prop(FakeEpisodeItunesBuilder2::block).isNotNull().isFalse()
            prop(FakeEpisodeItunesBuilder2::imageBuilder).isEqualTo(expectedEpisodeImageBuilder)
            prop(FakeEpisodeItunesBuilder2::episodeType).isEqualTo(EpisodeType.FULL)
            prop(FakeEpisodeItunesBuilder2::author).isEqualTo("author")
            prop(FakeEpisodeItunesBuilder2::subtitle).isEqualTo("subtitle")
            prop(FakeEpisodeItunesBuilder2::summary).isEqualTo("summary")
        }
    }

    @Test
    fun `should not extract itunes fields from item when absent`() {
        val node = XmlRes("/xml/item-incomplete.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        node.parseItemChildNodes(builder)

        assertThat(builder.itunesBuilder, "item.itunes").all {
            prop(FakeEpisodeItunesBuilder2::title).isNull()
            prop(FakeEpisodeItunesBuilder2::duration).isNull()
            prop(FakeEpisodeItunesBuilder2::season).isNull()
            prop(FakeEpisodeItunesBuilder2::episode).isNull()
            prop(FakeEpisodeItunesBuilder2::explicit).isNull()
            prop(FakeEpisodeItunesBuilder2::block).isNull()
            prop(FakeEpisodeItunesBuilder2::imageBuilder).isNull()
            prop(FakeEpisodeItunesBuilder2::episodeType).isNull()
            prop(FakeEpisodeItunesBuilder2::author).isNull()
            prop(FakeEpisodeItunesBuilder2::subtitle).isNull()
            prop(FakeEpisodeItunesBuilder2::summary).isNull()
        }
    }

    @Test
    fun `should extract nothing from item when itunes data is all empty`() {
        val channel: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel/item")
        val builder = FakeEpisodeBuilder()
        channel.parseItemChildNodes(builder)

        assertThat(builder.itunesBuilder, "item.itunes").all {
            prop(FakeEpisodeItunesBuilder2::title).isNull()
            prop(FakeEpisodeItunesBuilder2::duration).isNull()
            prop(FakeEpisodeItunesBuilder2::season).isNull()
            prop(FakeEpisodeItunesBuilder2::episode).isNull()
            prop(FakeEpisodeItunesBuilder2::explicit).isNull()
            prop(FakeEpisodeItunesBuilder2::block).isNull()
            prop(FakeEpisodeItunesBuilder2::imageBuilder).isNotNull().hasNotEnoughDataToBuild()
            prop(FakeEpisodeItunesBuilder2::episodeType).isNull()
            prop(FakeEpisodeItunesBuilder2::author).isNull()
            prop(FakeEpisodeItunesBuilder2::subtitle).isNull()
            prop(FakeEpisodeItunesBuilder2::summary).isNull()
        }
    }
}
