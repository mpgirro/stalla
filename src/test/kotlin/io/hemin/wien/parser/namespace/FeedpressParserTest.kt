package io.hemin.wien.parser.namespace

import assertk.all
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import assertk.assertions.prop
import io.hemin.wien.builder.fake.podcast.FakePodcastBuilder
import io.hemin.wien.builder.fake.podcast.FakePodcastFeedpressBuilder
import io.hemin.wien.dom.XmlRes
import io.hemin.wien.parser.NamespaceParserTest
import org.junit.jupiter.api.Test
import org.w3c.dom.Node

internal class FeedpressParserTest : NamespaceParserTest() {

    override val parser = FeedpressParser

    @Test
    fun `should extract feedpress data from channel when present`() {
        val node = XmlRes("/xml/channel.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        node.parseChannelChildNodes(builder)

        assertThat(builder.feedpressBuilder, "channel feedpress data").all {
            prop(FakePodcastFeedpressBuilder::newsletterIdValue).isEqualTo("abc123")
            prop(FakePodcastFeedpressBuilder::localeValue).isEqualTo("en")
            prop(FakePodcastFeedpressBuilder::podcastIdValue).isEqualTo("xyz123")
            prop(FakePodcastFeedpressBuilder::cssFileValue).isEqualTo("http://example.org/style.css")
            prop(FakePodcastFeedpressBuilder::linkValue).isEqualTo("http://example.org/my-link")
        }
    }

    @Test
    fun `should not extract feedpress data from channel when present`() {
        val node = XmlRes("/xml/channel-incomplete.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        node.parseChannelChildNodes(builder)

        assertThat(builder.feedpressBuilder, "channel feedpress data").all {
            prop(FakePodcastFeedpressBuilder::newsletterIdValue).isNull()
            prop(FakePodcastFeedpressBuilder::localeValue).isNull()
            prop(FakePodcastFeedpressBuilder::podcastIdValue).isNull()
            prop(FakePodcastFeedpressBuilder::cssFileValue).isNull()
            prop(FakePodcastFeedpressBuilder::linkValue).isNull()
        }
    }

    @Test
    fun `should extract nothing from channel when feedpress data is all empty`() {
        val channel: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel")
        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.feedpressBuilder, "channel feedpress data").all {
            prop(FakePodcastFeedpressBuilder::newsletterIdValue).isNull()
            prop(FakePodcastFeedpressBuilder::localeValue).isNull()
            prop(FakePodcastFeedpressBuilder::podcastIdValue).isNull()
            prop(FakePodcastFeedpressBuilder::cssFileValue).isNull()
            prop(FakePodcastFeedpressBuilder::linkValue).isNull()
        }
    }
}
