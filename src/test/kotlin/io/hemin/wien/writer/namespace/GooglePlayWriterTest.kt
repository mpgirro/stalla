package io.hemin.wien.writer.namespace

import assertk.assertAll
import assertk.assertThat
import io.hemin.wien.hasNoDifferences
import io.hemin.wien.model.episode.anEpisode
import io.hemin.wien.model.podcast.aPodcast
import org.junit.jupiter.api.Test

internal class GooglePlayWriterTest : NamespaceWriterTest() {

    override val writer = GooglePlayWriter()

    @Test
    internal fun `should write the correct googleplay tags to the channel when there is data to write`() {
        assertAll {
            writePodcastData("author") { element ->
                val diff = element.diffFromExpected("/rss/channel/googleplay:author")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("owner") { element ->
                val diff = element.diffFromExpected("/rss/channel/googleplay:owner")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("category") { element ->
                val diff = element.diffFromExpected("/rss/channel/googleplay:category")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("description") { element ->
                val diff = element.diffFromExpected("/rss/channel/googleplay:description")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("explicit") { element ->
                val diff = element.diffFromExpected("/rss/channel/googleplay:explicit")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("block") { element ->
                val diff = element.diffFromExpected("/rss/channel/googleplay:block")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("image") { element ->
                val diff = element.diffFromExpected("/rss/channel/googleplay:image")
                assertThat(diff).hasNoDifferences()
            }
        }
    }

    @Test
    internal fun `should not write googleplay tags to the channel when there is no data to write`() {
        assertAll {
            assertTagIsNotWrittenToPodcast(aPodcast(googlePlay = null), "author")
            assertTagIsNotWrittenToPodcast(aPodcast(googlePlay = null), "owner")
            assertTagIsNotWrittenToPodcast(aPodcast(googlePlay = null), "category")
            assertTagIsNotWrittenToPodcast(aPodcast(googlePlay = null), "description")
            assertTagIsNotWrittenToPodcast(aPodcast(googlePlay = null), "explicit")
            assertTagIsNotWrittenToPodcast(aPodcast(googlePlay = null), "block")
            assertTagIsNotWrittenToPodcast(aPodcast(googlePlay = null), "image")
        }
    }

    @Test
    internal fun `should write the correct googleplay tags to the item when there is data to write`() {
        assertAll {
            writeEpisodeData("description") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/googleplay:description")
                assertThat(diff).hasNoDifferences()
            }
            writeEpisodeData("explicit") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/googleplay:explicit")
                assertThat(diff).hasNoDifferences()
            }
            writeEpisodeData("block") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/googleplay:block")
                assertThat(diff).hasNoDifferences()
            }
            writeEpisodeData("image") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/googleplay:image")
                assertThat(diff).hasNoDifferences()
            }
        }
    }

    @Test
    internal fun `should not write googleplay tags to the item when there is no data to write`() {
        assertAll {
            assertTagIsNotWrittenToEpisode(anEpisode(googlePlay = null), "description")
            assertTagIsNotWrittenToEpisode(anEpisode(googlePlay = null), "explicit")
            assertTagIsNotWrittenToEpisode(anEpisode(googlePlay = null), "block")
            assertTagIsNotWrittenToEpisode(anEpisode(googlePlay = null), "image")
        }
    }
}
