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

    private val expectedPodcastImageBuilder = FakeHrefOnlyImageBuilder().href("http://example.org/podcast-cover.jpg")

    private val expectedEpisodeImageBuilder = FakeHrefOnlyImageBuilder().href("http://example.org/episode-cover.jpg")

    @Test
    fun `should extract all google play fields from channel when present`() {
        val node = XmlRes("/xml/channel.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        node.parseChannelChildNodes(builder)

        assertThat(builder.googleplayBuilder, "channel.googleplay").all {
            prop(FakePodcastGoogleplayBuilder::author).isEqualTo("Lorem Ipsum")
            prop(FakePodcastGoogleplayBuilder::owner).isEqualTo("email@example.org")
            prop(FakePodcastGoogleplayBuilder::categories).containsExactly(GoogleplayCategory.NewsAndPolitics)
            prop(FakePodcastGoogleplayBuilder::description).isEqualTo("Lorem Ipsum")
            prop(FakePodcastGoogleplayBuilder::explicit).isNotNull().isFalse()
            prop(FakePodcastGoogleplayBuilder::block).isNotNull().isFalse()
            prop(FakePodcastGoogleplayBuilder::imageBuilder).isEqualTo(expectedPodcastImageBuilder)
        }
    }

    @Test
    fun `should not extract google play fields from channel when absent`() {
        val node = XmlRes("/xml/channel-incomplete.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        node.parseChannelChildNodes(builder)

        assertThat(builder.googleplayBuilder, "channel.googleplay").all {
            prop(FakePodcastGoogleplayBuilder::author).isNull()
            prop(FakePodcastGoogleplayBuilder::owner).isNull()
            prop(FakePodcastGoogleplayBuilder::categories).isEmpty()
            prop(FakePodcastGoogleplayBuilder::description).isNull()
            prop(FakePodcastGoogleplayBuilder::explicit).isNull()
            prop(FakePodcastGoogleplayBuilder::block).isNull()
            prop(FakePodcastGoogleplayBuilder::imageBuilder).isNull()
        }
    }

    @Test
    fun `should extract nothing from channel when googleplay data is all empty`() {
        val channel: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel")
        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.googleplayBuilder, "channel.googleplay").all {
            prop(FakePodcastGoogleplayBuilder::author).isNull()
            prop(FakePodcastGoogleplayBuilder::owner).isNull()
            prop(FakePodcastGoogleplayBuilder::categories).isEmpty()
            prop(FakePodcastGoogleplayBuilder::description).isNull()
            prop(FakePodcastGoogleplayBuilder::explicit).isNull()
            prop(FakePodcastGoogleplayBuilder::block).isNull()
            prop(FakePodcastGoogleplayBuilder::imageBuilder).isNotNull().hasNotEnoughDataToBuild()
        }
    }

    @Test
    fun `should extract all google play fields from item when present`() {
        val node = XmlRes("/xml/item.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        node.parseItemChildNodes(builder)

        assertThat(builder.googleplayBuilder, "item.googleplay").all {
            prop(FakeEpisodeGoogleplayBuilder::author).isEqualTo("Lorem Ipsum")
            prop(FakeEpisodeGoogleplayBuilder::description).isEqualTo("Lorem Ipsum")
            prop(FakeEpisodeGoogleplayBuilder::explicit).isNotNull().isEqualTo(ExplicitType.CLEAN)
            prop(FakeEpisodeGoogleplayBuilder::block).isNotNull().isFalse()
            prop(FakeEpisodeGoogleplayBuilder::imageBuilder).isEqualTo(expectedEpisodeImageBuilder)
        }
    }

    @Test
    fun `should not extract google play fields from item when absent`() {
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
    fun `should extract nothing from item when googleplay data is all empty`() {
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
