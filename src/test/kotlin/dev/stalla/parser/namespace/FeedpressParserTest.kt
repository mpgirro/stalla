package dev.stalla.parser.namespace

import assertk.all
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import assertk.assertions.prop
import dev.stalla.builder.fake.podcast.FakePodcastBuilder
import dev.stalla.builder.fake.podcast.FakePodcastFeedpressBuilder
import dev.stalla.dom.XmlRes
import dev.stalla.parser.NamespaceParserTest
import org.junit.jupiter.api.Test
import org.w3c.dom.Node
import java.util.Locale

internal class FeedpressParserTest : NamespaceParserTest() {

    override val parser = FeedpressParser

    @Test
    fun `should extract Feedpress data from channel when present`() {
        val node = XmlRes("/xml/channel.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        node.parseChannelChildNodes(builder)

        assertThat(builder.feedpressBuilder, "channel.feedpress").all {
            prop(FakePodcastFeedpressBuilder::newsletterIdValue).isEqualTo("feedpres newsletterid")
            prop(FakePodcastFeedpressBuilder::localeValue).isEqualTo(Locale.GERMAN)
            prop(FakePodcastFeedpressBuilder::podcastIdValue).isEqualTo("feedpress podcastid")
            prop(FakePodcastFeedpressBuilder::cssFileValue).isEqualTo("feedpress cssfile")
            prop(FakePodcastFeedpressBuilder::linkValue).isEqualTo("feedpress link")
        }
    }

    @Test
    fun `should not extract Feedpress data from channel when present`() {
        val node = XmlRes("/xml/channel-incomplete.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        node.parseChannelChildNodes(builder)

        assertThat(builder.feedpressBuilder, "channel.feedpress").all {
            prop(FakePodcastFeedpressBuilder::newsletterIdValue).isNull()
            prop(FakePodcastFeedpressBuilder::localeValue).isNull()
            prop(FakePodcastFeedpressBuilder::podcastIdValue).isNull()
            prop(FakePodcastFeedpressBuilder::cssFileValue).isNull()
            prop(FakePodcastFeedpressBuilder::linkValue).isNull()
        }
    }

    @Test
    fun `should extract nothing from channel when Feedpress data is all empty`() {
        val channel: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel")
        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.feedpressBuilder, "channel.feedpress").all {
            prop(FakePodcastFeedpressBuilder::newsletterIdValue).isNull()
            prop(FakePodcastFeedpressBuilder::localeValue).isNull()
            prop(FakePodcastFeedpressBuilder::podcastIdValue).isNull()
            prop(FakePodcastFeedpressBuilder::cssFileValue).isNull()
            prop(FakePodcastFeedpressBuilder::linkValue).isNull()
        }
    }
}
