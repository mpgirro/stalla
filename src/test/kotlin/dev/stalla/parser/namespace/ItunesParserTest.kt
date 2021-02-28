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
import dev.stalla.builder.fake.episode.FakeEpisodeBuilder
import dev.stalla.builder.fake.episode.FakeEpisodeItunesBuilder
import dev.stalla.builder.fake.podcast.FakePodcastBuilder
import dev.stalla.builder.fake.podcast.FakePodcastItunesBuilder
import dev.stalla.builder.fake.podcast.FakePodcastItunesOwnerBuilder
import dev.stalla.dom.XmlRes
import dev.stalla.hasNotEnoughDataToBuild
import dev.stalla.model.StyledDuration
import dev.stalla.model.itunes.EpisodeType
import dev.stalla.model.itunes.ItunesCategory
import dev.stalla.model.itunes.ShowType
import dev.stalla.parser.NamespaceParserTest
import org.junit.jupiter.api.Test
import org.w3c.dom.Node

internal class ItunesParserTest : NamespaceParserTest() {

    override val parser = ItunesParser

    private val expectedPodcastImageBuilder = FakeHrefOnlyImageBuilder().href("podcast itunes image href")

    private val expectedEpisodeImageBuilder = FakeHrefOnlyImageBuilder().href("episode itunes image href")

    private val expectedOwnerBuilder = FakePodcastItunesOwnerBuilder()
        .name("podcast itunes owner name")
        .email("podcast itunes owner email")

    @Test
    fun `should extract all Itunes fields from channel when present`() {
        val node = XmlRes("/xml/channel.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        node.parseChannelChildNodes(builder)

        assertThat(builder.itunesBuilder, "channel.itunes").all {
            prop(FakePodcastItunesBuilder::author).isEqualTo("podcast itunes author")
            prop(FakePodcastItunesBuilder::ownerBuilder).isEqualTo(expectedOwnerBuilder)
            prop(FakePodcastItunesBuilder::categories).containsExactly(
                ItunesCategory.TECH_NEWS,
                ItunesCategory.SOCIETY_AND_CULTURE,
                ItunesCategory.SCIENCE_FICTION
            )
            prop(FakePodcastItunesBuilder::subtitle).isEqualTo("podcast itunes subtitle")
            prop(FakePodcastItunesBuilder::summary).isEqualTo("podcast itunes summary")
            prop(FakePodcastItunesBuilder::keywords).isEqualTo("podcast itunes keywords")
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
    fun `should not extract Itunes fields from channel when absent`() {
        val node = XmlRes("/xml/channel-incomplete.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        node.parseChannelChildNodes(builder)

        assertThat(builder.itunesBuilder, "channel.itunes").all {
            prop(FakePodcastItunesBuilder::author).isNull()
            prop(FakePodcastItunesBuilder::ownerBuilder).isNull()
            prop(FakePodcastItunesBuilder::categories).isEmpty()
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
    fun `should extract nothing from channel when Itunes data is all empty`() {
        val channel: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel")
        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.itunesBuilder, "channel.itunes").all {
            prop(FakePodcastItunesBuilder::author).isNull()
            prop(FakePodcastItunesBuilder::ownerBuilder).isNotNull().hasNotEnoughDataToBuild()
            prop(FakePodcastItunesBuilder::categories).isEmpty()
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
    fun `should extract all Itunes fields from item when present`() {
        val node = XmlRes("/xml/item.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        node.parseItemChildNodes(builder)

        assertThat(builder.itunesBuilder, "item.itunes").all {
            prop(FakeEpisodeItunesBuilder::title).isEqualTo("episode itunes title")
            prop(FakeEpisodeItunesBuilder::duration).isEqualTo(StyledDuration.hoursMinutesSeconds(3, 24, 27))
            prop(FakeEpisodeItunesBuilder::season).isEqualTo(1)
            prop(FakeEpisodeItunesBuilder::episode).isEqualTo(1)
            prop(FakeEpisodeItunesBuilder::explicit).isNotNull().isFalse()
            prop(FakeEpisodeItunesBuilder::block).isNotNull().isFalse()
            prop(FakeEpisodeItunesBuilder::imageBuilder).isEqualTo(expectedEpisodeImageBuilder)
            prop(FakeEpisodeItunesBuilder::episodeType).isEqualTo(EpisodeType.FULL)
            prop(FakeEpisodeItunesBuilder::author).isEqualTo("episode itunes author")
            prop(FakeEpisodeItunesBuilder::subtitle).isEqualTo("episode itunes subtitle")
            prop(FakeEpisodeItunesBuilder::summary).isEqualTo("episode itunes summary")
        }
    }

    @Test
    fun `should not extract Itunes fields from item when absent`() {
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
    fun `should extract nothing from item when Itunes data is all empty`() {
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

    @Test
    fun `should not extract an Itunes category tag from the channel when the value is invalid`() {
        val channel: Node = XmlRes("/xml/channel-invalid.xml").rootNodeByName("channel")

        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.itunesBuilder, "channel.itunes").all {
            prop(FakePodcastItunesBuilder::categories).isEmpty()
        }
    }

    @Test
    fun `should not extract an Itunes explicit tag from the channel when the value is invalid`() {
        val channel: Node = XmlRes("/xml/channel-invalid.xml").rootNodeByName("channel")

        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.itunesBuilder, "channel.itunes").all {
            prop(FakePodcastItunesBuilder::explicit).isNull()
        }
    }

    @Test
    fun `should not extract an Itunes block tag from the channel when the value is invalid`() {
        val channel: Node = XmlRes("/xml/channel-invalid.xml").rootNodeByName("channel")

        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.itunesBuilder, "channel.itunes").all {
            prop(FakePodcastItunesBuilder::block).isNull()
        }
    }

    @Test
    fun `should not extract an Itunes complete tag from the channel when the value is invalid`() {
        val channel: Node = XmlRes("/xml/channel-invalid.xml").rootNodeByName("channel")

        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.itunesBuilder, "channel.itunes").all {
            prop(FakePodcastItunesBuilder::complete).isNull()
        }
    }

    @Test
    fun `should not extract an Itunes type tag from the channel when the value is invalid`() {
        val channel: Node = XmlRes("/xml/channel-invalid.xml").rootNodeByName("channel")

        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.itunesBuilder, "channel.itunes").all {
            prop(FakePodcastItunesBuilder::type).isNull()
        }
    }

    @Test
    fun `should not extract an Itunes duration tag from the item when the value is invalid`() {
        val item: Node = XmlRes("/xml/item-invalid.xml").rootNodeByName("item")

        val builder = FakeEpisodeBuilder()
        item.parseItemChildNodes(builder)

        assertThat(builder.itunesBuilder, "item.itunes").all {
            prop(FakeEpisodeItunesBuilder::duration).isNull()
        }
    }

    @Test
    fun `should not extract an Itunes explicit tag from the item when the value is invalid`() {
        val item: Node = XmlRes("/xml/item-invalid.xml").rootNodeByName("item")

        val builder = FakeEpisodeBuilder()
        item.parseItemChildNodes(builder)

        assertThat(builder.itunesBuilder, "item.itunes").all {
            prop(FakeEpisodeItunesBuilder::explicit).isNull()
        }
    }

    @Test
    fun `should not extract an Itunes block tag from the item when the value is invalid`() {
        val item: Node = XmlRes("/xml/item-invalid.xml").rootNodeByName("item")

        val builder = FakeEpisodeBuilder()
        item.parseItemChildNodes(builder)

        assertThat(builder.itunesBuilder, "item.itunes").all {
            prop(FakeEpisodeItunesBuilder::block).isNull()
        }
    }

    @Test
    fun `should not extract an Itunes episodeType tag from the item when the value is invalid`() {
        val item: Node = XmlRes("/xml/item-invalid.xml").rootNodeByName("item")

        val builder = FakeEpisodeBuilder()
        item.parseItemChildNodes(builder)

        assertThat(builder.itunesBuilder, "item.itunes").all {
            prop(FakeEpisodeItunesBuilder::episodeType).isNull()
        }
    }
}
