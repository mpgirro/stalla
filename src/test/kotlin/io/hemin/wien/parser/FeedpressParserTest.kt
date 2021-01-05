package io.hemin.wien.parser

import assertk.all
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import assertk.assertions.prop
import io.hemin.wien.builder.fake.podcast.FakePodcastBuilder
import io.hemin.wien.builder.fake.podcast.FakePodcastFeedpressBuilder
import io.hemin.wien.nodeFromResource
import io.hemin.wien.parser.namespace.FeedpressParser
import org.junit.jupiter.api.Test

internal class FeedpressParserTest : NamespaceParserTest() {

    override val parser: NamespaceParser = FeedpressParser()

    @Test
    fun `should extract feedpress data from channel when present`() {
        val node = nodeFromResource("channel", "/xml/channel.xml")
        val builder = FakePodcastBuilder()
        node.parseChannelChildNodes(builder)

        assertThat(builder.feedpress, "channel feedpress data").all {
            prop(FakePodcastFeedpressBuilder::newsletterIdValue).isEqualTo("abc123")
            prop(FakePodcastFeedpressBuilder::localeValue).isEqualTo("en")
            prop(FakePodcastFeedpressBuilder::podcastIdValue).isEqualTo("xyz123")
            prop(FakePodcastFeedpressBuilder::cssFileValue).isEqualTo("http://example.org/style.css")
            prop(FakePodcastFeedpressBuilder::linkValue).isEqualTo("http://example.org/my-link")
        }
    }

    @Test
    fun `should not extract feedpress data from channel when present`() {
        val node = nodeFromResource("channel", "/xml/channel-incomplete.xml")
        val builder = FakePodcastBuilder()
        node.parseChannelChildNodes(builder)

        assertThat(builder.feedpress, "channel feedpress data").all {
            prop(FakePodcastFeedpressBuilder::newsletterIdValue).isNull()
            prop(FakePodcastFeedpressBuilder::localeValue).isNull()
            prop(FakePodcastFeedpressBuilder::podcastIdValue).isNull()
            prop(FakePodcastFeedpressBuilder::cssFileValue).isNull()
            prop(FakePodcastFeedpressBuilder::linkValue).isNull()
        }
    }
}
