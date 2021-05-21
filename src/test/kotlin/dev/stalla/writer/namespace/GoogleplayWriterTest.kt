package dev.stalla.writer.namespace

import assertk.assertAll
import assertk.assertThat
import dev.stalla.hasNoDifferences
import dev.stalla.model.HrefOnlyImage
import dev.stalla.model.aPodcast
import dev.stalla.model.aPodcastGoogleplay
import dev.stalla.model.anEpisode
import dev.stalla.model.anEpisodeGoogleplay
import dev.stalla.model.anHrefOnlyImage
import org.junit.jupiter.api.Test

internal class GoogleplayWriterTest : NamespaceWriterTest() {

    override val writer = GoogleplayWriter

    @Test
    internal fun `should write the correct Googleplay tags to the channel when there is data to write`() {
        assertAll {
            writePodcastData("author") { element ->
                val diff = element.diffFromExpected("/rss/channel/googleplay:author")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("email") { element ->
                val diff = element.diffFromExpected("/rss/channel/googleplay:email")
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
            writePodcastData("newFeedUrl") { element ->
                val diff = element.diffFromExpected("/rss/channel/googleplay:newFeedUrl")
                assertThat(diff).hasNoDifferences()
            }
        }
    }

    @Test
    internal fun `should not write Googleplay tags to the channel when there is no data to write`() {
        assertAll {
            assertTagIsNotWrittenToPodcast(aPodcast(googleplay = null), "author")
            assertTagIsNotWrittenToPodcast(aPodcast(googleplay = null), "email")
            assertTagIsNotWrittenToPodcast(aPodcast(googleplay = null), "category")
            assertTagIsNotWrittenToPodcast(aPodcast(googleplay = null), "description")
            assertTagIsNotWrittenToPodcast(aPodcast(googleplay = null), "explicit")
            assertTagIsNotWrittenToPodcast(aPodcast(googleplay = null), "block")
            assertTagIsNotWrittenToPodcast(aPodcast(googleplay = null), "image")
            assertTagIsNotWrittenToPodcast(aPodcast(googleplay = null), "newFeedUrl")
        }
    }

    @Test
    internal fun `should not write Googleplay tags to the channel when the data is blank`() {
        val podcast = aPodcast(
            googleplay = aPodcastGoogleplay(
                author = " ",
                email = " ",
                description = " ",
                categories = emptyList(),
                image = HrefOnlyImage(" "),
                block = false,
                explicitType = null,
                newFeedUrl = " "
            )
        )
        assertAll {
            assertTagIsNotWrittenToPodcast(podcast, "author")
            assertTagIsNotWrittenToPodcast(podcast, "email")
            assertTagIsNotWrittenToPodcast(podcast, "category")
            assertTagIsNotWrittenToPodcast(podcast, "description")
            assertTagIsNotWrittenToPodcast(podcast, "explicit")
            assertTagIsNotWrittenToPodcast(podcast, "block")
            assertTagIsNotWrittenToPodcast(podcast, "image")
            assertTagIsNotWrittenToPodcast(podcast, "new-feed-url")
        }
    }

    @Test
    internal fun `should not write Googleplay tags to the channel when the data is empty`() {
        val podcast = aPodcast(
            googleplay = aPodcastGoogleplay(
                author = "",
                email = "",
                description = "",
                categories = emptyList(),
                image = HrefOnlyImage(""),
                block = false,
                explicitType = null,
                newFeedUrl = ""
            )
        )
        assertAll {
            assertTagIsNotWrittenToPodcast(podcast, "author")
            assertTagIsNotWrittenToPodcast(podcast, "email")
            assertTagIsNotWrittenToPodcast(podcast, "category")
            assertTagIsNotWrittenToPodcast(podcast, "description")
            assertTagIsNotWrittenToPodcast(podcast, "explicit")
            assertTagIsNotWrittenToPodcast(podcast, "block")
            assertTagIsNotWrittenToPodcast(podcast, "image")
            assertTagIsNotWrittenToPodcast(podcast, "new-feed-url")
        }
    }

    @Test
    internal fun `should write the correct Googleplay tags to the item when there is data to write`() {
        assertAll {
            writeEpisodeData("author") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/googleplay:author")
                assertThat(diff).hasNoDifferences()
            }
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
    internal fun `should not write Googleplay tags to the item when there is no data to write`() {
        val episode = anEpisode(googleplay = null)
        assertAll {
            assertTagIsNotWrittenToEpisode(episode, "author")
            assertTagIsNotWrittenToEpisode(episode, "description")
            assertTagIsNotWrittenToEpisode(episode, "explicit")
            assertTagIsNotWrittenToEpisode(episode, "block")
            assertTagIsNotWrittenToEpisode(episode, "image")
        }
    }

    @Test
    internal fun `should not write Googleplay tags to the item when the data is blank`() {
        val episode = anEpisode(
            googleplay = anEpisodeGoogleplay(
                author = " ",
                description = " ",
                image = anHrefOnlyImage(" "),
                block = false,
                explicit = null
            )
        )
        assertAll {
            assertTagIsNotWrittenToEpisode(episode, "author")
            assertTagIsNotWrittenToEpisode(episode, "description")
            assertTagIsNotWrittenToEpisode(episode, "explicit")
            assertTagIsNotWrittenToEpisode(episode, "block")
            assertTagIsNotWrittenToEpisode(episode, "image")
        }
    }

    @Test
    internal fun `should not write Googleplay tags to the item when the data is empty`() {
        val episode = anEpisode(
            googleplay = anEpisodeGoogleplay(
                author = "",
                description = "",
                image = anHrefOnlyImage(""),
                block = false,
                explicit = null
            )
        )
        assertAll {
            assertTagIsNotWrittenToEpisode(episode, "author")
            assertTagIsNotWrittenToEpisode(episode, "description")
            assertTagIsNotWrittenToEpisode(episode, "explicit")
            assertTagIsNotWrittenToEpisode(episode, "block")
            assertTagIsNotWrittenToEpisode(episode, "image")
        }
    }
}
