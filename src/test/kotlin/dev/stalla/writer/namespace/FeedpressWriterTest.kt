package dev.stalla.writer.namespace

import assertk.assertAll
import assertk.assertThat
import dev.stalla.hasNoDifferences
import dev.stalla.model.podcast.aPodcast
import dev.stalla.model.podcast.aPodcastFeedpress
import org.junit.jupiter.api.Test

internal class FeedpressWriterTest : NamespaceWriterTest() {

    override val writer = FeedpressWriter

    @Test
    internal fun `should write the correct Feedpress tags to the channel when there is data to write`() {
        assertAll {
            writePodcastData("newsletterId") { element ->
                val diff = element.diffFromExpected("/rss/channel/feedpress:newsletterId")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("locale") { element ->
                val diff = element.diffFromExpected("/rss/channel/feedpress:locale")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("podcastId") { element ->
                val diff = element.diffFromExpected("/rss/channel/feedpress:podcastId")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("cssFile") { element ->
                val diff = element.diffFromExpected("/rss/channel/feedpress:cssFile")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("link") { element ->
                val diff = element.diffFromExpected("/rss/channel/feedpress:link")
                assertThat(diff).hasNoDifferences()
            }
        }
    }

    @Test
    internal fun `should not write Feedpress tags to the channel when there is no data to write`() {
        val podcast = aPodcast(feedpress = null)
        assertAll {
            assertTagIsNotWrittenToPodcast(podcast, "newsletterId")
            assertTagIsNotWrittenToPodcast(podcast, "locale")
            assertTagIsNotWrittenToPodcast(podcast, "podcastId")
            assertTagIsNotWrittenToPodcast(podcast, "cssFile")
            assertTagIsNotWrittenToPodcast(podcast, "link")
        }
    }

    @Test
    internal fun `should not write Feedpress tags to the channel when the data is blank`() {
        val podcast = aPodcast(feedpress = aPodcastFeedpress(" ", " ", " ", " ", " "))
        assertAll {
            assertTagIsNotWrittenToPodcast(podcast, "newsletterId")
            assertTagIsNotWrittenToPodcast(podcast, "locale")
            assertTagIsNotWrittenToPodcast(podcast, "podcastId")
            assertTagIsNotWrittenToPodcast(podcast, "cssFile")
            assertTagIsNotWrittenToPodcast(podcast, "link")
        }
    }

    @Test
    internal fun `should not write Feedpress tags to the channel when the data is empty`() {
        val podcast = aPodcast(feedpress = aPodcastFeedpress("", "", "", "", ""))
        assertAll {
            assertTagIsNotWrittenToPodcast(podcast, "newsletterId")
            assertTagIsNotWrittenToPodcast(podcast, "locale")
            assertTagIsNotWrittenToPodcast(podcast, "podcastId")
            assertTagIsNotWrittenToPodcast(podcast, "cssFile")
            assertTagIsNotWrittenToPodcast(podcast, "link")
        }
    }
}
