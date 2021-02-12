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
import dev.stalla.builder.fake.episode.FakeEpisodeBuilder
import dev.stalla.builder.fake.episode.FakeEpisodeGoogleplayBuilder2
import dev.stalla.builder.fake.podcast.FakePodcastBuilder
import dev.stalla.builder.fake.podcast.FakePodcastGoogleplayBuilder2
import dev.stalla.dom.XmlRes
import dev.stalla.hasNotEnoughDataToBuild
import dev.stalla.noneHasEnoughDataToBuild
import dev.stalla.parser.NamespaceParserTest
import org.junit.jupiter.api.Test
import org.w3c.dom.Node

internal class GoogleplayParser2Test : NamespaceParserTest() {

    override val parser = GoogleplayParser2

    private val expectedPodcastImageBuilder = FakeHrefOnlyImageBuilder().href("http://example.org/podcast-cover.jpg")

    private val expectedEpisodeImageBuilder = FakeHrefOnlyImageBuilder().href("http://example.org/episode-cover.jpg")

    @Test
    fun `should extract all google play fields from channel when present`() {
        val node = XmlRes("/xml/channel.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        node.parseChannelChildNodes(builder)

        assertThat(builder.googleplayBuilder, "channel.googleplay").all {
            prop(FakePodcastGoogleplayBuilder2::author).isEqualTo("Lorem Ipsum")
            prop(FakePodcastGoogleplayBuilder2::owner).isEqualTo("email@example.org")
            prop(FakePodcastGoogleplayBuilder2::categoryBuilders).containsExactly(FakeItunesStyleCategoryBuilder2().category("Technology"))
            prop(FakePodcastGoogleplayBuilder2::description).isEqualTo("Lorem Ipsum")
            prop(FakePodcastGoogleplayBuilder2::explicit).isNotNull().isFalse()
            prop(FakePodcastGoogleplayBuilder2::block).isNotNull().isFalse()
            prop(FakePodcastGoogleplayBuilder2::imageBuilder).isEqualTo(expectedPodcastImageBuilder)
        }
    }

    @Test
    fun `should not extract google play fields from channel when absent`() {
        val node = XmlRes("/xml/channel-incomplete.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        node.parseChannelChildNodes(builder)

        assertThat(builder.googleplayBuilder, "channel.googleplay").all {
            prop(FakePodcastGoogleplayBuilder2::author).isNull()
            prop(FakePodcastGoogleplayBuilder2::owner).isNull()
            prop(FakePodcastGoogleplayBuilder2::categoryBuilders).isEmpty()
            prop(FakePodcastGoogleplayBuilder2::description).isNull()
            prop(FakePodcastGoogleplayBuilder2::explicit).isNull()
            prop(FakePodcastGoogleplayBuilder2::block).isNull()
            prop(FakePodcastGoogleplayBuilder2::imageBuilder).isNull()
        }
    }

    @Test
    fun `should extract nothing from channel when googleplay data is all empty`() {
        val channel: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel")
        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.googleplayBuilder, "channel.googleplay").all {
            prop(FakePodcastGoogleplayBuilder2::author).isNull()
            prop(FakePodcastGoogleplayBuilder2::owner).isNull()
            prop(FakePodcastGoogleplayBuilder2::categoryBuilders).noneHasEnoughDataToBuild()
            prop(FakePodcastGoogleplayBuilder2::description).isNull()
            prop(FakePodcastGoogleplayBuilder2::explicit).isNull()
            prop(FakePodcastGoogleplayBuilder2::block).isNull()
            prop(FakePodcastGoogleplayBuilder2::imageBuilder).isNotNull().hasNotEnoughDataToBuild()
        }
    }

    @Test
    fun `should extract all google play fields from item when present`() {
        val node = XmlRes("/xml/item.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        node.parseItemChildNodes(builder)

        assertThat(builder.googleplayBuilder, "item.googleplay").all {
            prop(FakeEpisodeGoogleplayBuilder2::description).isEqualTo("Lorem Ipsum")
            prop(FakeEpisodeGoogleplayBuilder2::explicit).isNotNull().isFalse()
            prop(FakeEpisodeGoogleplayBuilder2::block).isNotNull().isFalse()
            prop(FakeEpisodeGoogleplayBuilder2::imageBuilder).isEqualTo(expectedEpisodeImageBuilder)
        }
    }

    @Test
    fun `should not extract google play fields from item when absent`() {
        val node = XmlRes("/xml/item-incomplete.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        node.parseItemChildNodes(builder)

        assertThat(builder.googleplayBuilder, "item.googleplay").all {
            prop(FakeEpisodeGoogleplayBuilder2::description).isNull()
            prop(FakeEpisodeGoogleplayBuilder2::explicit).isNull()
            prop(FakeEpisodeGoogleplayBuilder2::block).isNull()
            prop(FakeEpisodeGoogleplayBuilder2::imageBuilder).isNull()
        }
    }

    @Test
    fun `should extract nothing from item when googleplay data is all empty`() {
        val channel: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel/item")
        val builder = FakeEpisodeBuilder()
        channel.parseItemChildNodes(builder)

        assertThat(builder.googleplayBuilder, "item.googleplay").all {
            prop(FakeEpisodeGoogleplayBuilder2::description).isNull()
            prop(FakeEpisodeGoogleplayBuilder2::explicit).isNull()
            prop(FakeEpisodeGoogleplayBuilder2::block).isNull()
            prop(FakeEpisodeGoogleplayBuilder2::imageBuilder).isNotNull().hasNotEnoughDataToBuild()
        }
    }
}
