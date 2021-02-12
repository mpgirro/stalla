package dev.stalla.writer.namespace

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.hasSize
import dev.stalla.hasNoChildren
import dev.stalla.hasNoDifferences
import dev.stalla.hasTextContent
import dev.stalla.hasValue
import dev.stalla.model.anHrefOnlyImage
import dev.stalla.model.episode.anEpisode
import dev.stalla.model.episode.anEpisodeItunes
import dev.stalla.model.itunes.ItunesCategory
import dev.stalla.model.podcast.aPodcast
import dev.stalla.model.podcast.aPodcastItunes
import org.junit.jupiter.api.Test

internal class ItunesWriterTest : NamespaceWriterTest() {

    override val writer = ItunesWriter

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
        val podcast = aPodcast(itunes = null)
        assertAll {
            assertTagIsNotWrittenToPodcast(podcast, "author")
            assertTagIsNotWrittenToPodcast(podcast, "category")
            assertTagIsNotWrittenToPodcast(podcast, "complete")
            assertTagIsNotWrittenToPodcast(podcast, "keywords")
            assertTagIsNotWrittenToPodcast(podcast, "owner")
            assertTagIsNotWrittenToPodcast(podcast, "subtitle")
            assertTagIsNotWrittenToPodcast(podcast, "summary")
            assertTagIsNotWrittenToPodcast(podcast, "type")
            assertTagIsNotWrittenToPodcast(podcast, "image")
            assertTagIsNotWrittenToPodcast(podcast, "explicit")
            assertTagIsNotWrittenToPodcast(podcast, "title")
            assertTagIsNotWrittenToPodcast(podcast, "block")
        }
    }

    @Test
    internal fun `should not write itunes tags to the channel when the data is blank`() {
        val categories = listOf(
            ItunesCategory.Simple(" "),
            ItunesCategory.Nested(" ", ItunesCategory.Simple("subcategory")),
            ItunesCategory.Nested("nested", ItunesCategory.Simple(" "))
        )
        val podcast = aPodcast(
            itunes = aPodcastItunes(
                subtitle = " ",
                summary = " ",
                image = anHrefOnlyImage(" "),
                keywords = " ",
                author = " ",
                categories = categories,
                type = null,
                owner = null,
                title = " ",
                newFeedUrl = " ",
                block = false,
                complete = false,
                explicit = false
            )
        )
        assertAll {
            assertTagIsNotWrittenToPodcast(podcast, "author")
            writePodcastDataXPathMultiple("//itunes:category", podcast) { elements ->
                assertThat(elements).hasSize(1)
            }
            writePodcastData("category", podcast = podcast) { element ->
                assertThat(element, "category element").hasAttribute("text", namespace = null).hasValue("nested")
                assertThat(element, "category element children").hasNoChildren()
            }
            assertTagIsNotWrittenToPodcast(podcast, "complete")
            assertTagIsNotWrittenToPodcast(podcast, "keywords")
            assertTagIsNotWrittenToPodcast(podcast, "owner")
            assertTagIsNotWrittenToPodcast(podcast, "subtitle")
            assertTagIsNotWrittenToPodcast(podcast, "summary")
            assertTagIsNotWrittenToPodcast(podcast, "type")
            assertTagIsNotWrittenToPodcast(podcast, "image")
            writePodcastData("explicit", podcast = podcast) { element ->
                assertThat(element, "explicit element").hasTextContent("false")
            }
            assertTagIsNotWrittenToPodcast(podcast, "title")
            assertTagIsNotWrittenToPodcast(podcast, "block")
        }
    }

    @Test
    internal fun `should not write itunes tags to the channel when the data is empty`() {
        val categories = listOf(
            ItunesCategory.Simple(""),
            ItunesCategory.Nested("", ItunesCategory.Simple("subcategory")),
            ItunesCategory.Nested("nested", ItunesCategory.Simple(""))
        )
        val podcast = aPodcast(
            itunes = aPodcastItunes(
                subtitle = "",
                summary = "",
                image = anHrefOnlyImage(""),
                keywords = "",
                author = "",
                categories = categories,
                type = null,
                owner = null,
                title = "",
                newFeedUrl = "",
                block = false,
                complete = false,
                explicit = false
            )
        )
        assertAll {
            assertTagIsNotWrittenToPodcast(podcast, "author")
            writePodcastDataXPathMultiple("//itunes:category", podcast) { elements ->
                assertThat(elements).hasSize(1)
            }
            writePodcastData("category", podcast = podcast) { element ->
                assertThat(element, "category element").hasAttribute("text", namespace = null).hasValue("nested")
                assertThat(element, "category element children").hasNoChildren()
            }
            assertTagIsNotWrittenToPodcast(podcast, "complete")
            assertTagIsNotWrittenToPodcast(podcast, "keywords")
            assertTagIsNotWrittenToPodcast(podcast, "owner")
            assertTagIsNotWrittenToPodcast(podcast, "subtitle")
            assertTagIsNotWrittenToPodcast(podcast, "summary")
            assertTagIsNotWrittenToPodcast(podcast, "type")
            assertTagIsNotWrittenToPodcast(podcast, "image")
            writePodcastData("explicit", podcast = podcast) { element ->
                assertThat(element, "explicit element").hasTextContent("false")
            }
            assertTagIsNotWrittenToPodcast(podcast, "title")
            assertTagIsNotWrittenToPodcast(podcast, "block")
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
        val episode = anEpisode(itunes = null)
        assertAll {
            assertTagIsNotWrittenToEpisode(episode, "duration")
            assertTagIsNotWrittenToEpisode(episode, "season")
            assertTagIsNotWrittenToEpisode(episode, "episode")
            assertTagIsNotWrittenToEpisode(episode, "episodeType")
            assertTagIsNotWrittenToEpisode(episode, "image")
            assertTagIsNotWrittenToEpisode(episode, "explicit")
            assertTagIsNotWrittenToEpisode(episode, "title")
            assertTagIsNotWrittenToEpisode(episode, "block")
        }
    }

    @Test
    internal fun `should not write itunes tags to the item when the data is blank`() {
        val episode = anEpisode(
            itunes = anEpisodeItunes(
                title = " ",
                duration = " ",
                image = anHrefOnlyImage(" "),
                season = null,
                episode = null,
                episodeType = null,
                author = " ",
                subtitle = " ",
                summary = " ",
                explicit = null,
                block = false
            )
        )
        assertAll {
            assertTagIsNotWrittenToEpisode(episode, "duration")
            assertTagIsNotWrittenToEpisode(episode, "season")
            assertTagIsNotWrittenToEpisode(episode, "episode")
            assertTagIsNotWrittenToEpisode(episode, "episodeType")
            assertTagIsNotWrittenToEpisode(episode, "image")
            assertTagIsNotWrittenToEpisode(episode, "title")
            assertTagIsNotWrittenToEpisode(episode, "explicit")
            assertTagIsNotWrittenToEpisode(episode, "block")
        }
    }

    @Test
    internal fun `should not write itunes tags to the item when the data is empty`() {
        val episode = anEpisode(
            itunes = anEpisodeItunes(
                title = "",
                duration = "",
                image = anHrefOnlyImage(""),
                season = null,
                episode = null,
                episodeType = null,
                author = "",
                subtitle = "",
                summary = "",
                explicit = null,
                block = false
            )
        )
        assertAll {
            assertTagIsNotWrittenToEpisode(episode, "duration")
            assertTagIsNotWrittenToEpisode(episode, "season")
            assertTagIsNotWrittenToEpisode(episode, "episode")
            assertTagIsNotWrittenToEpisode(episode, "episodeType")
            assertTagIsNotWrittenToEpisode(episode, "image")
            assertTagIsNotWrittenToEpisode(episode, "explicit")
            assertTagIsNotWrittenToEpisode(episode, "title")
            assertTagIsNotWrittenToEpisode(episode, "block")
        }
    }
}
