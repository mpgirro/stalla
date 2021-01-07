package io.hemin.wien.parser

import assertk.all
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import io.hemin.wien.builder.fake.FakeHrefOnlyImageBuilder
import io.hemin.wien.builder.fake.FakeITunesStyleCategoryBuilder
import io.hemin.wien.builder.fake.FakePersonBuilder
import io.hemin.wien.builder.fake.episode.FakeEpisodeBuilder
import io.hemin.wien.builder.fake.episode.FakeEpisodeITunesBuilder
import io.hemin.wien.builder.fake.podcast.FakePodcastBuilder
import io.hemin.wien.builder.fake.podcast.FakePodcastITunesBuilder
import io.hemin.wien.dom.XmlRes
import io.hemin.wien.hasNotEnoughDataToBuild
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import io.hemin.wien.noneHasEnoughDataToBuild
import io.hemin.wien.parser.namespace.ITunesParser
import org.junit.jupiter.api.Test
import org.w3c.dom.Node

internal class ITunesParserTest : NamespaceParserTest() {

    override val parser = ITunesParser()

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

        assertThat(builder.iTunes, "channel.itunes").all {
            prop(FakePodcastITunesBuilder::author).isEqualTo("Lorem Ipsum")
            prop(FakePodcastITunesBuilder::ownerBuilder).isEqualTo(expectedOwnerBuilder)
            prop(FakePodcastITunesBuilder::categoryBuilders).containsExactly(
                FakeITunesStyleCategoryBuilder().category("Technology").subcategory("Tech News"),
                FakeITunesStyleCategoryBuilder().category("Society & Culture"),
                FakeITunesStyleCategoryBuilder().category("Technology").subcategory("Gadgets")
            )
            prop(FakePodcastITunesBuilder::subtitle).isEqualTo("Lorem Ipsum")
            prop(FakePodcastITunesBuilder::summary).isEqualTo("Lorem Ipsum")
            prop(FakePodcastITunesBuilder::keywords).isEqualTo("Lorem Ipsum")
            prop(FakePodcastITunesBuilder::explicit).isNotNull().isFalse()
            prop(FakePodcastITunesBuilder::block).isNotNull().isFalse()
            prop(FakePodcastITunesBuilder::complete).isNotNull().isFalse()
            prop(FakePodcastITunesBuilder::imageBuilderValue).isEqualTo(expectedPodcastImageBuilder)
            prop(FakePodcastITunesBuilder::type).isEqualTo(Podcast.ITunes.ShowType.EPISODIC)
            prop(FakePodcastITunesBuilder::title).isEqualTo("podcast itunes title")
            prop(FakePodcastITunesBuilder::newFeedUrl).isEqualTo("podcast itunes new-feed-url")
        }
    }

    @Test
    fun `should not extract itunes fields from channel when absent`() {
        val node = XmlRes("/xml/channel-incomplete.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        node.parseChannelChildNodes(builder)

        assertThat(builder.iTunes, "channel.itunes").all {
            prop(FakePodcastITunesBuilder::author).isNull()
            prop(FakePodcastITunesBuilder::ownerBuilder).isNull()
            prop(FakePodcastITunesBuilder::categoryBuilders).isEmpty()
            prop(FakePodcastITunesBuilder::subtitle).isNull()
            prop(FakePodcastITunesBuilder::summary).isNull()
            prop(FakePodcastITunesBuilder::keywords).isNull()
            prop(FakePodcastITunesBuilder::explicit).isNull()
            prop(FakePodcastITunesBuilder::block).isNull()
            prop(FakePodcastITunesBuilder::complete).isNull()
            prop(FakePodcastITunesBuilder::imageBuilderValue).isNull()
            prop(FakePodcastITunesBuilder::type).isNull()
            prop(FakePodcastITunesBuilder::title).isNull()
            prop(FakePodcastITunesBuilder::newFeedUrl).isNull()
        }
    }

    @Test
    fun `should extract nothing from channel when itunes data is all empty`() {
        val channel: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel")
        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.iTunes, "channel.itunes").all {
            prop(FakePodcastITunesBuilder::author).isNull()
            prop(FakePodcastITunesBuilder::ownerBuilder).isNotNull().hasNotEnoughDataToBuild()
            prop(FakePodcastITunesBuilder::categoryBuilders).noneHasEnoughDataToBuild()
            prop(FakePodcastITunesBuilder::subtitle).isNull()
            prop(FakePodcastITunesBuilder::summary).isNull()
            prop(FakePodcastITunesBuilder::keywords).isNull()
            prop(FakePodcastITunesBuilder::explicit).isNull()
            prop(FakePodcastITunesBuilder::block).isNull()
            prop(FakePodcastITunesBuilder::complete).isNull()
            prop(FakePodcastITunesBuilder::imageBuilderValue).isNotNull().hasNotEnoughDataToBuild()
            prop(FakePodcastITunesBuilder::type).isNull()
            prop(FakePodcastITunesBuilder::title).isNull()
            prop(FakePodcastITunesBuilder::newFeedUrl).isNull()
        }
    }

    @Test
    fun `should extract all itunes fields from item when present`() {
        val node = XmlRes("/xml/item.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        node.parseItemChildNodes(builder)

        assertThat(builder.iTunes, "item.itunes").all {
            prop(FakeEpisodeITunesBuilder::title).isEqualTo("Lorem Ipsum")
            prop(FakeEpisodeITunesBuilder::duration).isEqualTo("03:24:27")
            prop(FakeEpisodeITunesBuilder::season).isEqualTo(1)
            prop(FakeEpisodeITunesBuilder::episode).isEqualTo(1)
            prop(FakeEpisodeITunesBuilder::explicit).isNotNull().isFalse()
            prop(FakeEpisodeITunesBuilder::block).isNotNull().isFalse()
            prop(FakeEpisodeITunesBuilder::imageBuilder).isEqualTo(expectedEpisodeImageBuilder)
            prop(FakeEpisodeITunesBuilder::episodeType).isEqualTo(Episode.ITunes.EpisodeType.FULL)
            prop(FakeEpisodeITunesBuilder::author).isEqualTo("author")
            prop(FakeEpisodeITunesBuilder::subtitle).isEqualTo("subtitle")
            prop(FakeEpisodeITunesBuilder::summary).isEqualTo("summary")
        }
    }

    @Test
    fun `should not extract itunes fields from item when absent`() {
        val node = XmlRes("/xml/item-incomplete.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        node.parseItemChildNodes(builder)

        assertThat(builder.iTunes, "item.itunes").all {
            prop(FakeEpisodeITunesBuilder::title).isNull()
            prop(FakeEpisodeITunesBuilder::duration).isNull()
            prop(FakeEpisodeITunesBuilder::season).isNull()
            prop(FakeEpisodeITunesBuilder::episode).isNull()
            prop(FakeEpisodeITunesBuilder::explicit).isNull()
            prop(FakeEpisodeITunesBuilder::block).isNull()
            prop(FakeEpisodeITunesBuilder::imageBuilder).isNull()
            prop(FakeEpisodeITunesBuilder::episodeType).isNull()
            prop(FakeEpisodeITunesBuilder::author).isNull()
            prop(FakeEpisodeITunesBuilder::subtitle).isNull()
            prop(FakeEpisodeITunesBuilder::summary).isNull()
        }
    }

    @Test
    fun `should extract nothing from item when itunes data is all empty`() {
        val channel: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel/item")
        val builder = FakeEpisodeBuilder()
        channel.parseItemChildNodes(builder)

        assertThat(builder.iTunes, "item.itunes").all {
            prop(FakeEpisodeITunesBuilder::title).isNull()
            prop(FakeEpisodeITunesBuilder::duration).isNull()
            prop(FakeEpisodeITunesBuilder::season).isNull()
            prop(FakeEpisodeITunesBuilder::episode).isNull()
            prop(FakeEpisodeITunesBuilder::explicit).isNull()
            prop(FakeEpisodeITunesBuilder::block).isNull()
            prop(FakeEpisodeITunesBuilder::imageBuilder).isNotNull().hasNotEnoughDataToBuild()
            prop(FakeEpisodeITunesBuilder::episodeType).isNull()
            prop(FakeEpisodeITunesBuilder::author).isNull()
            prop(FakeEpisodeITunesBuilder::subtitle).isNull()
            prop(FakeEpisodeITunesBuilder::summary).isNull()
        }
    }
}
