package dev.stalla.writer.namespace

import assertk.all
import assertk.assertAll
import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isInstanceOf
import dev.stalla.hasNoAttribute
import dev.stalla.hasNoDifferences
import dev.stalla.hasTextContent
import dev.stalla.hasValue
import dev.stalla.model.MediaType
import dev.stalla.model.aPodcast
import dev.stalla.model.anEpisode
import dev.stalla.model.anEpisodeEnclosure
import dev.stalla.model.anEpisodeGuid
import dev.stalla.model.anRssCategory
import dev.stalla.model.anRssImage
import dev.stalla.util.FeedNamespace
import org.junit.jupiter.api.Test
import org.w3c.dom.Element
import java.util.Locale

internal class RssWriterTest : NamespaceWriterTest() {

    override val writer = RssWriter

    @Test
    internal fun `should write the correct RSS tags to the channel when there is data to write`() {
        assertAll {
            writePodcastData("title") { element ->
                val diff = element.diffFromExpected("/rss/channel/title")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("link") { element ->
                val diff = element.diffFromExpected("/rss/channel/link")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("generator") { element ->
                val diff = element.diffFromExpected("/rss/channel/generator")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("description") { element ->
                val diff = element.diffFromExpected("/rss/channel/description")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("category") { element ->
                val diff = element.diffFromExpected("/rss/channel/category")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("pubDate") { element ->
                val diff = element.diffFromExpected("/rss/channel/pubDate")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("lastBuildDate") { element ->
                val diff = element.diffFromExpected("/rss/channel/lastBuildDate")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("language") { element ->
                val diff = element.diffFromExpected("/rss/channel/language")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("copyright") { element ->
                val diff = element.diffFromExpected("/rss/channel/copyright")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("docs") { element ->
                val diff = element.diffFromExpected("/rss/channel/docs")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("managingEditor") { element ->
                val diff = element.diffFromExpected("/rss/channel/managingEditor")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("webMaster") { element ->
                val diff = element.diffFromExpected("/rss/channel/webMaster")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("image") { element ->
                val diff = element.diffFromExpected("/rss/channel/image")
                assertThat(diff).hasNoDifferences()
            }
        }
    }

    @Test
    internal fun `should not write RSS tags to the channel when there is no data to write`() {
        val podcast = aPodcast(
            title = "title",
            link = "link",
            description = "description",
            pubDate = null,
            lastBuildDate = null,
            language = Locale.GERMAN,
            copyright = null,
            docs = null,
            managingEditor = null,
            webMaster = null,
            image = null,
            generator = null
        )
        assertAll {
            writePodcastData("title") { element ->
                val diff = element.diffFromExpected("/rss/channel/title")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("link") { element ->
                val diff = element.diffFromExpected("/rss/channel/link")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("description") { element ->
                val diff = element.diffFromExpected("/rss/channel/description")
                assertThat(diff).hasNoDifferences()
            }
            assertTagIsNotWrittenToPodcast(podcast, "pubDate")
            assertTagIsNotWrittenToPodcast(podcast, "lastBuildDate")
            writePodcastData("language") { element ->
                val diff = element.diffFromExpected("/rss/channel/language")
                assertThat(diff).hasNoDifferences()
            }
            assertTagIsNotWrittenToPodcast(podcast, "copyright")
            assertTagIsNotWrittenToPodcast(podcast, "docs")
            assertTagIsNotWrittenToPodcast(podcast, "managingEditor")
            assertTagIsNotWrittenToPodcast(podcast, "webMaster")
            assertTagIsNotWrittenToPodcast(podcast, "image")
        }
    }

    @Test
    internal fun `should not write RSS tags to the channel when the data is blank`() {
        val podcast = aPodcast(
            title = " ",
            link = " ",
            description = " ",
            pubDate = null,
            lastBuildDate = null,
            copyright = " ",
            docs = " ",
            managingEditor = " ",
            webMaster = " ",
            image = anRssImage(" "),
            generator = " "
        )
        assertAll {
            assertTagIsNotWrittenToPodcast(podcast, "title")
            assertTagIsNotWrittenToPodcast(podcast, "link")
            assertTagIsNotWrittenToPodcast(podcast, "description")
            assertTagIsNotWrittenToPodcast(podcast, "pubDate")
            assertTagIsNotWrittenToPodcast(podcast, "lastBuildDate")
            assertTagIsNotWrittenToPodcast(podcast, "copyright")
            assertTagIsNotWrittenToPodcast(podcast, "docs")
            assertTagIsNotWrittenToPodcast(podcast, "managingEditor")
            assertTagIsNotWrittenToPodcast(podcast, "webMaster")
            assertTagIsNotWrittenToPodcast(podcast, "image")
        }
    }

    @Test
    internal fun `should not write RSS tags to the channel when the data is empty`() {
        val podcast = aPodcast(
            title = "",
            link = "",
            description = "",
            pubDate = null,
            lastBuildDate = null,
            copyright = "",
            docs = "",
            managingEditor = "",
            webMaster = "",
            image = anRssImage(""),
            generator = ""
        )
        assertAll {
            assertTagIsNotWrittenToPodcast(podcast, "title")
            assertTagIsNotWrittenToPodcast(podcast, "link")
            assertTagIsNotWrittenToPodcast(podcast, "description")
            assertTagIsNotWrittenToPodcast(podcast, "pubDate")
            assertTagIsNotWrittenToPodcast(podcast, "lastBuildDate")
            assertTagIsNotWrittenToPodcast(podcast, "copyright")
            assertTagIsNotWrittenToPodcast(podcast, "docs")
            assertTagIsNotWrittenToPodcast(podcast, "managingEditor")
            assertTagIsNotWrittenToPodcast(podcast, "webMaster")
            assertTagIsNotWrittenToPodcast(podcast, "image")
        }
    }

    @Test
    internal fun `should write the correct RSS tags to the item when there is data to write`() {
        assertAll {
            writeEpisodeData("title") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/title")
                assertThat(diff).hasNoDifferences()
            }
            writeEpisodeData("link") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/link")
                assertThat(diff).hasNoDifferences()
            }
            writeEpisodeData("description") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/description")
                assertThat(diff).hasNoDifferences()
            }
            writeEpisodeData("author") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/author")
                assertThat(diff).hasNoDifferences()
            }
            writeEpisodeData("category") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/category")
                assertThat(diff).hasNoDifferences()
            }
            writeEpisodeData("comments") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/comments")
                assertThat(diff).hasNoDifferences()
            }
            writeEpisodeData("enclosure") { element ->
                assertThat(element).all {
                    hasAttribute("url").hasValue("episode enclosure url")
                    hasAttribute("length").hasValue("777")
                    hasAttribute("type").hasValue(MediaType.MPEG_AUDIO.toString())
                    hasNoAttribute("guid", FeedNamespace.BITLOVE)
                }
            }
            writeEpisodeData("guid") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/guid")
                assertThat(diff).hasNoDifferences()
            }
            writeEpisodeData("pubDate") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/pubDate")
                assertThat(diff).hasNoDifferences()
            }
            writeEpisodeData("source") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/source")
                assertThat(diff).hasNoDifferences()
            }
        }
    }

    @Test
    internal fun `should not write RSS tags to the item when there is no data to write`() {
        val episode = anEpisode(
            title = "title",
            link = null,
            description = null,
            author = null,
            categories = emptyList(),
            comments = null,
            enclosure = anEpisodeEnclosure("url", 123, MediaType.MPEG_AUDIO),
            guid = null,
            pubDate = null,
            source = null
        )
        assertAll {
            writeEpisodeData("title", episode = episode) { element ->
                assertThat(element).hasTextContent("title")
            }
            assertTagIsNotWrittenToEpisode(episode, "link")
            assertTagIsNotWrittenToEpisode(episode, "description")
            assertTagIsNotWrittenToEpisode(episode, "author")
            assertTagIsNotWrittenToEpisode(episode, "category")
            assertTagIsNotWrittenToEpisode(episode, "comments")
            writeEpisodeData("enclosure", episode = episode) { element ->
                assertThat(element).all {
                    hasAttribute("url").hasValue("url")
                    hasAttribute("length").hasValue("123")
                    hasAttribute("type").hasValue("audio/mpeg")
                }
            }
            assertTagIsNotWrittenToEpisode(episode, "guid")
            assertTagIsNotWrittenToEpisode(episode, "pubDate")
            assertTagIsNotWrittenToEpisode(episode, "source")
        }
    }

    @Test
    internal fun `should not write RSS tags to the item when the data is blank`() {
        val episode = anEpisode(
            title = " ",
            link = " ",
            description = " ",
            author = " ",
            categories = listOf(anRssCategory(" ", " "), anRssCategory("category 2", " ")),
            comments = " ",
            enclosure = anEpisodeEnclosure(" ", 123, MediaType.ANY),
            guid = anEpisodeGuid(" "),
            pubDate = null,
            source = " "
        )
        assertAll {
            assertTagIsNotWrittenToEpisode(episode, "title")
            assertTagIsNotWrittenToEpisode(episode, "link")
            assertTagIsNotWrittenToEpisode(episode, "description")
            assertTagIsNotWrittenToEpisode(episode, "author")
            writeEpisodeDataXPathMultiple("//category", episode = episode) { elements ->
                assertThat(elements).all {
                    hasSize(1)
                    transform { it.first() }.isInstanceOf(Element::class).all {
                        hasTextContent("category 2")
                        hasNoAttribute("domain")
                    }
                }
            }
            assertTagIsNotWrittenToEpisode(episode, "comments")
            assertTagIsNotWrittenToEpisode(episode, "guid")
            assertTagIsNotWrittenToEpisode(episode, "enclosure")
            assertTagIsNotWrittenToEpisode(episode, "pubDate")
            assertTagIsNotWrittenToEpisode(episode, "source")
        }
    }

    @Test
    internal fun `should not write RSS tags to the item when the data is empty`() {
        val episode = anEpisode(
            title = "",
            link = "",
            description = "",
            author = "",
            categories = listOf(anRssCategory("", ""), anRssCategory("category 2", "")),
            comments = "",
            enclosure = anEpisodeEnclosure("", 123, MediaType.MPEG_AUDIO),
            guid = anEpisodeGuid(""),
            pubDate = null,
            source = ""
        )
        assertAll {
            assertTagIsNotWrittenToEpisode(episode, "title")
            assertTagIsNotWrittenToEpisode(episode, "link")
            assertTagIsNotWrittenToEpisode(episode, "description")
            assertTagIsNotWrittenToEpisode(episode, "author")
            writeEpisodeDataXPathMultiple("//category", episode = episode) { elements ->
                assertThat(elements).all {
                    hasSize(1)
                    transform { it.first() }.isInstanceOf(Element::class).all {
                        hasTextContent("category 2")
                        hasNoAttribute("domain")
                    }
                }
            }
            assertTagIsNotWrittenToEpisode(episode, "comments")
            assertTagIsNotWrittenToEpisode(episode, "guid")
            assertTagIsNotWrittenToEpisode(episode, "enclosure")
            assertTagIsNotWrittenToEpisode(episode, "pubDate")
            assertTagIsNotWrittenToEpisode(episode, "source")
        }
    }
}
