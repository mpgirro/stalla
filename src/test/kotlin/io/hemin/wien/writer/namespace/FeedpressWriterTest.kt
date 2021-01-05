package io.hemin.wien.writer.namespace

import assertk.assertThat
import io.hemin.wien.hasNoDifferences
import io.hemin.wien.model.podcast.aPodcast
import org.junit.jupiter.api.Test

internal class FeedpressWriterTest : NamespaceWriterTest() {

    override val writer = FeedpressWriter()

    @Test
    internal fun `should write the correct feedpress tags to the channel when there is data to write`() {
        assertk.assertAll {
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
    internal fun `should not write feedpress tags to the channel when there is no data to write`() {
        assertk.assertAll {
            assertTagIsNotWrittenToPodcast(aPodcast(feedpress = null), "newsletterId")
            assertTagIsNotWrittenToPodcast(aPodcast(feedpress = null), "locale")
            assertTagIsNotWrittenToPodcast(aPodcast(feedpress = null), "podcastId")
            assertTagIsNotWrittenToPodcast(aPodcast(feedpress = null), "cssFile")
            assertTagIsNotWrittenToPodcast(aPodcast(feedpress = null), "link")
        }
    }
}
