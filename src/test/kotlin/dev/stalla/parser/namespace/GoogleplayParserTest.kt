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
import dev.stalla.builder.fake.episode.FakeEpisodeGoogleplayBuilder
import dev.stalla.builder.fake.podcast.FakePodcastBuilder
import dev.stalla.builder.fake.podcast.FakePodcastGoogleplayBuilder
import dev.stalla.dom.XmlRes
import dev.stalla.hasNotEnoughDataToBuild
import dev.stalla.model.googleplay.ExplicitType
import dev.stalla.model.googleplay.GoogleplayCategory
import dev.stalla.parser.NamespaceParserTest
import org.junit.jupiter.api.Test
import org.w3c.dom.Node

internal class GoogleplayParserTest : NamespaceParserTest() {

    override val parser = GoogleplayParser

    private val expectedPodcastImageBuilder = FakeHrefOnlyImageBuilder().href("podcast googleplay image href")

    private val expectedEpisodeImageBuilder = FakeHrefOnlyImageBuilder().href("episode googleplay image href")

    @Test
    fun `should extract all Googleplay fields from channel when present`() {
        val node = XmlRes("/xml/channel.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        node.parseChannelChildNodes(builder)

        assertThat(builder.googleplayBuilder, "channel.googleplay").all {
            prop(FakePodcastGoogleplayBuilder::author).isEqualTo("podcast googleplay author")
            prop(FakePodcastGoogleplayBuilder::email).isEqualTo("podcast googleplay email")
            prop(FakePodcastGoogleplayBuilder::categories).containsExactly(GoogleplayCategory.NEWS_AND_POLITICS)
            prop(FakePodcastGoogleplayBuilder::description).isEqualTo("podcast googleplay description")
            prop(FakePodcastGoogleplayBuilder::explicit).isNotNull().isFalse()
            prop(FakePodcastGoogleplayBuilder::block).isNotNull().isFalse()
            prop(FakePodcastGoogleplayBuilder::imageBuilder).isEqualTo(expectedPodcastImageBuilder)
            prop(FakePodcastGoogleplayBuilder::newFeedUrl).isEqualTo("podcast googleplay new-feed-url")
        }
    }

    @Test
    fun `should not extract Googleplay fields from channel when absent`() {
        val node = XmlRes("/xml/channel-incomplete.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        node.parseChannelChildNodes(builder)

        assertThat(builder.googleplayBuilder, "channel.googleplay").all {
            prop(FakePodcastGoogleplayBuilder::author).isNull()
            prop(FakePodcastGoogleplayBuilder::email).isNull()
            prop(FakePodcastGoogleplayBuilder::categories).isEmpty()
            prop(FakePodcastGoogleplayBuilder::description).isNull()
            prop(FakePodcastGoogleplayBuilder::explicit).isNull()
            prop(FakePodcastGoogleplayBuilder::block).isNull()
            prop(FakePodcastGoogleplayBuilder::imageBuilder).isNull()
            prop(FakePodcastGoogleplayBuilder::newFeedUrl).isNull()
        }
    }

    @Test
    fun `should extract nothing from channel when Googleplay data is all empty`() {
        val channel: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel")
        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.googleplayBuilder, "channel.googleplay").all {
            prop(FakePodcastGoogleplayBuilder::author).isNull()
            prop(FakePodcastGoogleplayBuilder::email).isNull()
            prop(FakePodcastGoogleplayBuilder::categories).isEmpty()
            prop(FakePodcastGoogleplayBuilder::description).isNull()
            prop(FakePodcastGoogleplayBuilder::explicit).isNull()
            prop(FakePodcastGoogleplayBuilder::block).isNull()
            prop(FakePodcastGoogleplayBuilder::imageBuilder).isNotNull().hasNotEnoughDataToBuild()
            prop(FakePodcastGoogleplayBuilder::newFeedUrl).isNull()
        }
    }

    @Test
    fun `should extract all Googleplay fields from item when present`() {
        val node = XmlRes("/xml/item.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        node.parseItemChildNodes(builder)

        assertThat(builder.googleplayBuilder, "item.googleplay").all {
            prop(FakeEpisodeGoogleplayBuilder::author).isEqualTo("episode googleplay author")
            prop(FakeEpisodeGoogleplayBuilder::description).isEqualTo("episode googleplay description")
            prop(FakeEpisodeGoogleplayBuilder::explicit).isNotNull().isEqualTo(ExplicitType.CLEAN)
            prop(FakeEpisodeGoogleplayBuilder::block).isNotNull().isFalse()
            prop(FakeEpisodeGoogleplayBuilder::imageBuilder).isEqualTo(expectedEpisodeImageBuilder)
        }
    }

    @Test
    fun `should not extract Googleplay fields from item when absent`() {
        val node = XmlRes("/xml/item-incomplete.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        node.parseItemChildNodes(builder)

        assertThat(builder.googleplayBuilder, "item.googleplay").all {
            prop(FakeEpisodeGoogleplayBuilder::author).isNull()
            prop(FakeEpisodeGoogleplayBuilder::description).isNull()
            prop(FakeEpisodeGoogleplayBuilder::explicit).isNull()
            prop(FakeEpisodeGoogleplayBuilder::block).isNull()
            prop(FakeEpisodeGoogleplayBuilder::imageBuilder).isNull()
        }
    }

    @Test
    fun `should extract nothing from item when Googleplay data is all empty`() {
        val channel: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel/item")
        val builder = FakeEpisodeBuilder()
        channel.parseItemChildNodes(builder)

        assertThat(builder.googleplayBuilder, "item.googleplay").all {
            prop(FakeEpisodeGoogleplayBuilder::author).isNull()
            prop(FakeEpisodeGoogleplayBuilder::description).isNull()
            prop(FakeEpisodeGoogleplayBuilder::explicit).isNull()
            prop(FakeEpisodeGoogleplayBuilder::block).isNull()
            prop(FakeEpisodeGoogleplayBuilder::imageBuilder).isNotNull().hasNotEnoughDataToBuild()
        }
    }
}
