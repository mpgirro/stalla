package io.hemin.wien.writer.namespace

import assertk.assertAll
import assertk.assertThat
import io.hemin.wien.hasNoDifferences
import io.hemin.wien.model.episode.anEpisode
import io.hemin.wien.model.podcast.aPodcast
import org.junit.jupiter.api.Test

internal class ITunesWriterTest: NamespaceWriterTest() {

    override val writer = ITunesWriter()

    @Test
    internal fun `should write the correct itunes tags to the channel when there is data to write`() {
        assertAll {
            writePodcastData("author") { element ->
                val diff = element.diffFromExpected("/rss/channel/itunes:author")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("category") { element ->
                val diff = element.diffFromExpected("/rss/channel/itunes:category")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("complete") { element ->
                val diff = element.diffFromExpected("/rss/channel/itunes:complete")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("keywords") { element ->
                val diff = element.diffFromExpected("/rss/channel/itunes:keywords")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("owner") { element ->
                val diff = element.diffFromExpected("/rss/channel/itunes:owner")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("subtitle") { element ->
                val diff = element.diffFromExpected("/rss/channel/itunes:subtitle")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("summary") { element ->
                val diff = element.diffFromExpected("/rss/channel/itunes:summary")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("type") { element ->
                val diff = element.diffFromExpected("/rss/channel/itunes:type")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("image") { element ->
                val diff = element.diffFromExpected("/rss/channel/itunes:image")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("explicit") { element ->
                val diff = element.diffFromExpected("/rss/channel/itunes:explicit")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("title") { element ->
                val diff = element.diffFromExpected("/rss/channel/itunes:title")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("block") { element ->
                val diff = element.diffFromExpected("/rss/channel/itunes:block")
                assertThat(diff).hasNoDifferences()
            }
        }
    }

    @Test
    internal fun `should not write itunes tags to the channel when there is no data to write`() {
        assertAll {
            assertTagIsNotWrittenToPodcast(aPodcast(iTunes = null), "author")
            assertTagIsNotWrittenToPodcast(aPodcast(iTunes = null), "category")
            assertTagIsNotWrittenToPodcast(aPodcast(iTunes = null), "complete")
            assertTagIsNotWrittenToPodcast(aPodcast(iTunes = null), "keywords")
            assertTagIsNotWrittenToPodcast(aPodcast(iTunes = null), "owner")
            assertTagIsNotWrittenToPodcast(aPodcast(iTunes = null), "subtitle")
            assertTagIsNotWrittenToPodcast(aPodcast(iTunes = null), "summary")
            assertTagIsNotWrittenToPodcast(aPodcast(iTunes = null), "type")
            assertTagIsNotWrittenToPodcast(aPodcast(iTunes = null), "image")
            assertTagIsNotWrittenToPodcast(aPodcast(iTunes = null), "explicit")
            assertTagIsNotWrittenToPodcast(aPodcast(iTunes = null), "title")
            assertTagIsNotWrittenToPodcast(aPodcast(iTunes = null), "block")
        }
    }

    @Test
    internal fun `should write the correct itunes tags to the item when there is data to write`() {
        assertAll {
            writeEpisodeData("duration") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/itunes:duration")
                assertThat(diff).hasNoDifferences()
            }
            writeEpisodeData("season") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/itunes:season")
                assertThat(diff).hasNoDifferences()
            }
            writeEpisodeData("episode") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/itunes:episode")
                assertThat(diff).hasNoDifferences()
            }
            writeEpisodeData("episodeType") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/itunes:episodeType")
                assertThat(diff).hasNoDifferences()
            }
            writeEpisodeData("image") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/itunes:image")
                assertThat(diff).hasNoDifferences()
            }
            writeEpisodeData("explicit") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/itunes:explicit")
                assertThat(diff).hasNoDifferences()
            }
            writeEpisodeData("title") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/itunes:title")
                assertThat(diff).hasNoDifferences()
            }
            writeEpisodeData("block") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/itunes:block")
                assertThat(diff).hasNoDifferences()
            }
        }
    }

    @Test
    internal fun `should not write itunes tags to the item when there is no data to write`() {
        assertAll {
            assertTagIsNotWrittenToEpisode(anEpisode(iTunes = null), "duration")
            assertTagIsNotWrittenToEpisode(anEpisode(iTunes = null), "season")
            assertTagIsNotWrittenToEpisode(anEpisode(iTunes = null), "episode")
            assertTagIsNotWrittenToEpisode(anEpisode(iTunes = null), "episodeType")
            assertTagIsNotWrittenToEpisode(anEpisode(iTunes = null), "image")
            assertTagIsNotWrittenToEpisode(anEpisode(iTunes = null), "explicit")
            assertTagIsNotWrittenToEpisode(anEpisode(iTunes = null), "title")
            assertTagIsNotWrittenToEpisode(anEpisode(iTunes = null), "block")
        }
    }
}
