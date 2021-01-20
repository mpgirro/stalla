package io.hemin.wien.writer.namespace

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.hasSize
import io.hemin.wien.hasNoChildren
import io.hemin.wien.hasNoDifferences
import io.hemin.wien.hasValue
import io.hemin.wien.model.HrefOnlyImage
import io.hemin.wien.model.ITunesStyleCategory
import io.hemin.wien.model.anHrefOnlyImage
import io.hemin.wien.model.episode.anEpisode
import io.hemin.wien.model.episode.anEpisodeGooglePlay
import io.hemin.wien.model.podcast.aPodcast
import io.hemin.wien.model.podcast.aPodcastGooglePlay
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
    internal fun `should not write googleplay tags to the channel when the data is blank`() {
        val categories = listOf(
            ITunesStyleCategory.Simple(" "),
            ITunesStyleCategory.Nested(" ", ITunesStyleCategory.Simple("subcategory")),
            ITunesStyleCategory.Nested("nested", ITunesStyleCategory.Simple(" "))
        )
        val podcast = aPodcast(
            googlePlay = aPodcastGooglePlay(
                author = " ",
                owner = " ",
                description = " ",
                categories = categories,
                image = HrefOnlyImage(" ")
            )
        )
        assertAll {
            assertTagIsNotWrittenToPodcast(podcast, "author")
            assertTagIsNotWrittenToPodcast(podcast, "owner")
            writePodcastDataXPathMultiple("//googleplay:category", podcast) { elements ->
                assertThat(elements).hasSize(1)
            }
            writePodcastData("category", podcast = podcast) { element ->
                assertThat(element, "category element").hasAttribute("text", namespace = null).hasValue("nested")
                assertThat(element, "category element children").hasNoChildren()
            }
            assertTagIsNotWrittenToPodcast(podcast, "description")
            assertTagIsNotWrittenToPodcast(podcast, "explicit")
            assertTagIsNotWrittenToPodcast(podcast, "block")
            assertTagIsNotWrittenToPodcast(podcast, "image")
        }
    }

    @Test
    internal fun `should not write googleplay tags to the channel when the data is empty`() {
        val categories = listOf(
            ITunesStyleCategory.Simple(""),
            ITunesStyleCategory.Nested("", ITunesStyleCategory.Simple("subcategory")),
            ITunesStyleCategory.Nested("nested", ITunesStyleCategory.Simple(""))
        )
        val podcast = aPodcast(
            googlePlay = aPodcastGooglePlay(
                author = "",
                owner = "",
                description = "",
                categories = categories,
                image = HrefOnlyImage("")
            )
        )
        assertAll {
            assertTagIsNotWrittenToPodcast(podcast, "author")
            assertTagIsNotWrittenToPodcast(podcast, "owner")
            writePodcastDataXPathMultiple("//googleplay:category", podcast) { elements ->
                assertThat(elements).hasSize(1)
            }
            writePodcastData("category", podcast = podcast) { element ->
                assertThat(element, "category element").hasAttribute("text", namespace = null).hasValue("nested")
                assertThat(element, "category element children").hasNoChildren()
            }
            assertTagIsNotWrittenToPodcast(podcast, "description")
            assertTagIsNotWrittenToPodcast(podcast, "explicit")
            assertTagIsNotWrittenToPodcast(podcast, "block")
            assertTagIsNotWrittenToPodcast(podcast, "image")
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
        val episode = anEpisode(googlePlay = null)
        assertAll {
            assertTagIsNotWrittenToEpisode(episode, "description")
            assertTagIsNotWrittenToEpisode(episode, "explicit")
            assertTagIsNotWrittenToEpisode(episode, "block")
            assertTagIsNotWrittenToEpisode(episode, "image")
        }
    }

    @Test
    internal fun `should not write googleplay tags to the item when the data is blank`() {
        val episode = anEpisode(googlePlay = anEpisodeGooglePlay(description = " ", image = anHrefOnlyImage(" ")))
        assertAll {
            assertTagIsNotWrittenToEpisode(episode, "description")
            assertTagIsNotWrittenToEpisode(episode, "explicit")
            assertTagIsNotWrittenToEpisode(episode, "block")
            assertTagIsNotWrittenToEpisode(episode, "image")
        }
    }

    @Test
    internal fun `should not write googleplay tags to the item when the data is empty`() {
        val episode = anEpisode(googlePlay = anEpisodeGooglePlay(description = "", image = anHrefOnlyImage("")))
        assertAll {
            assertTagIsNotWrittenToEpisode(episode, "description")
            assertTagIsNotWrittenToEpisode(episode, "explicit")
            assertTagIsNotWrittenToEpisode(episode, "block")
            assertTagIsNotWrittenToEpisode(episode, "image")
        }
    }
}
