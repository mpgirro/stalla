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
import dev.stalla.builder.fake.FakeITunesStyleCategoryBuilder
import dev.stalla.builder.fake.episode.FakeEpisodeBuilder
import dev.stalla.builder.fake.episode.FakeEpisodeGooglePlayBuilder
import dev.stalla.builder.fake.podcast.FakePodcastBuilder
import dev.stalla.builder.fake.podcast.FakePodcastGooglePlayBuilder
import dev.stalla.dom.XmlRes
import dev.stalla.hasNotEnoughDataToBuild
import dev.stalla.noneHasEnoughDataToBuild
import dev.stalla.parser.NamespaceParserTest
import org.junit.jupiter.api.Test
import org.w3c.dom.Node

internal class GooglePlayParserTest : NamespaceParserTest() {

    override val parser = GooglePlayParser

    private val expectedPodcastImageBuilder = FakeHrefOnlyImageBuilder().href("http://example.org/podcast-cover.jpg")

    private val expectedEpisodeImageBuilder = FakeHrefOnlyImageBuilder().href("http://example.org/episode-cover.jpg")

    @Test
    fun `should extract all google play fields from channel when present`() {
        val node = XmlRes("/xml/channel.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        node.parseChannelChildNodes(builder)

        assertThat(builder.googlePlayBuilder, "channel.googleplay").all {
            prop(FakePodcastGooglePlayBuilder::author).isEqualTo("Lorem Ipsum")
            prop(FakePodcastGooglePlayBuilder::owner).isEqualTo("email@example.org")
            prop(FakePodcastGooglePlayBuilder::categoryBuilders).containsExactly(FakeITunesStyleCategoryBuilder().category("Technology"))
            prop(FakePodcastGooglePlayBuilder::description).isEqualTo("Lorem Ipsum")
            prop(FakePodcastGooglePlayBuilder::explicit).isNotNull().isFalse()
            prop(FakePodcastGooglePlayBuilder::block).isNotNull().isFalse()
            prop(FakePodcastGooglePlayBuilder::imageBuilder).isEqualTo(expectedPodcastImageBuilder)
        }
    }

    @Test
    fun `should not extract google play fields from channel when absent`() {
        val node = XmlRes("/xml/channel-incomplete.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        node.parseChannelChildNodes(builder)

        assertThat(builder.googlePlayBuilder, "channel.googleplay").all {
            prop(FakePodcastGooglePlayBuilder::author).isNull()
            prop(FakePodcastGooglePlayBuilder::owner).isNull()
            prop(FakePodcastGooglePlayBuilder::categoryBuilders).isEmpty()
            prop(FakePodcastGooglePlayBuilder::description).isNull()
            prop(FakePodcastGooglePlayBuilder::explicit).isNull()
            prop(FakePodcastGooglePlayBuilder::block).isNull()
            prop(FakePodcastGooglePlayBuilder::imageBuilder).isNull()
        }
    }

    @Test
    fun `should extract nothing from channel when googleplay data is all empty`() {
        val channel: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel")
        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.googlePlayBuilder, "channel.googleplay").all {
            prop(FakePodcastGooglePlayBuilder::author).isNull()
            prop(FakePodcastGooglePlayBuilder::owner).isNull()
            prop(FakePodcastGooglePlayBuilder::categoryBuilders).noneHasEnoughDataToBuild()
            prop(FakePodcastGooglePlayBuilder::description).isNull()
            prop(FakePodcastGooglePlayBuilder::explicit).isNull()
            prop(FakePodcastGooglePlayBuilder::block).isNull()
            prop(FakePodcastGooglePlayBuilder::imageBuilder).isNotNull().hasNotEnoughDataToBuild()
        }
    }

    @Test
    fun `should extract all google play fields from item when present`() {
        val node = XmlRes("/xml/item.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        node.parseItemChildNodes(builder)

        assertThat(builder.googlePlayBuilder, "item.googleplay").all {
            prop(FakeEpisodeGooglePlayBuilder::description).isEqualTo("Lorem Ipsum")
            prop(FakeEpisodeGooglePlayBuilder::explicit).isNotNull().isFalse()
            prop(FakeEpisodeGooglePlayBuilder::block).isNotNull().isFalse()
            prop(FakeEpisodeGooglePlayBuilder::imageBuilder).isEqualTo(expectedEpisodeImageBuilder)
        }
    }

    @Test
    fun `should not extract google play fields from item when absent`() {
        val node = XmlRes("/xml/item-incomplete.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        node.parseItemChildNodes(builder)

        assertThat(builder.googlePlayBuilder, "item.googleplay").all {
            prop(FakeEpisodeGooglePlayBuilder::description).isNull()
            prop(FakeEpisodeGooglePlayBuilder::explicit).isNull()
            prop(FakeEpisodeGooglePlayBuilder::block).isNull()
            prop(FakeEpisodeGooglePlayBuilder::imageBuilder).isNull()
        }
    }

    @Test
    fun `should extract nothing from item when googleplay data is all empty`() {
        val channel: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel/item")
        val builder = FakeEpisodeBuilder()
        channel.parseItemChildNodes(builder)

        assertThat(builder.googlePlayBuilder, "item.googleplay").all {
            prop(FakeEpisodeGooglePlayBuilder::description).isNull()
            prop(FakeEpisodeGooglePlayBuilder::explicit).isNull()
            prop(FakeEpisodeGooglePlayBuilder::block).isNull()
            prop(FakeEpisodeGooglePlayBuilder::imageBuilder).isNotNull().hasNotEnoughDataToBuild()
        }
    }
}
