package dev.stalla.writer.namespace

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.hasSize
import dev.stalla.hasNoAttribute
import dev.stalla.hasNoDifferences
import dev.stalla.hasTextContent
import dev.stalla.hasValue
import dev.stalla.model.aLink
import dev.stalla.model.aPerson
import dev.stalla.model.episode.anEpisode
import dev.stalla.model.episode.anEpisodeAtom
import dev.stalla.model.podcast.aPodcast
import dev.stalla.model.podcast.aPodcastAtom
import org.junit.jupiter.api.Test

internal class AtomWriterTest : NamespaceWriterTest() {

    override val writer = AtomWriter

    @Test
    internal fun `should write the correct atom tags to the channel when there is data to write`() {
        assertAll {
            writePodcastData("author") { element ->
                val diff = element.diffFromExpected("/rss/channel/atom:author[1]")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("contributor") { element ->
                val diff = element.diffFromExpected("/rss/channel/atom:contributor[1]")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("link") { element ->
                val diff = element.diffFromExpected("/rss/channel/atom:link[1]")
                assertThat(diff).hasNoDifferences()
            }
        }
    }

    @Test
    internal fun `should not write atom tags to the channel when there is no data to write`() {
        val podcast = aPodcast(atom = null)
        assertAll {
            assertTagIsNotWrittenToPodcast(podcast, "author")
            assertTagIsNotWrittenToPodcast(podcast, "contributor")
            assertTagIsNotWrittenToPodcast(podcast, "link")
        }
    }

    @Test
    internal fun `should not write atom tags to the channel when the data is all blank`() {
        val podcast = aPodcast(
            atom = aPodcastAtom(
                authors = listOf(aPerson(" ", " ", " "), aPerson("", "", "")),
                contributors = listOf(aPerson(" ", " ", " "), aPerson("", "", "")),
                links = listOf(
                    aLink(" ", " ", " ", " ", " ", " ", " "),
                    aLink("", "", "", "", "", "", "")
                )
            )
        )
        assertAll {
            assertTagIsNotWrittenToPodcast(podcast, "author")
            assertTagIsNotWrittenToPodcast(podcast, "contributor")
            assertTagIsNotWrittenToPodcast(podcast, "link")
        }
    }

    @Test
    internal fun `should not write atom subtags to the channel when their data is blank`() {
        val podcast = aPodcast(
            atom = aPodcastAtom(
                authors = listOf(
                    aPerson("author 1", " ", " "), aPerson("author 2", "", "")
                ),
                contributors = listOf(
                    aPerson("contrib 1", " ", " "), aPerson("contrib 2", "", "")
                ),
                links = listOf(
                    aLink("link 1", " ", " ", " ", " ", " ", " "),
                    aLink("link 2", "", "", "", "", "", "")
                )
            )
        )
        assertAll {
            writePodcastDataXPathMultiple("//atom:author", podcast) { elements ->
                assertThat(elements).hasSize(2)
            }
            writePodcastDataXPath("//atom:author[1]", podcast = podcast) { element ->
                assertThat(element).containsElement("name").hasTextContent("author 1")
                assertThat(element).doesNotContainElement("email")
                assertThat(element).doesNotContainElement("uri")
            }
            writePodcastDataXPath("//atom:author[2]", podcast = podcast) { element ->
                assertThat(element).containsElement("name").hasTextContent("author 2")
                assertThat(element).doesNotContainElement("email")
                assertThat(element).doesNotContainElement("uri")
            }
            writePodcastDataXPathMultiple("//atom:contributor", podcast) { elements ->
                assertThat(elements).hasSize(2)
            }
            writePodcastDataXPath("//atom:contributor[1]", podcast = podcast) { element ->
                assertThat(element).containsElement("name").hasTextContent("contrib 1")
                assertThat(element).doesNotContainElement("email")
                assertThat(element).doesNotContainElement("uri")
            }
            writePodcastDataXPath("//atom:contributor[2]", podcast = podcast) { element ->
                assertThat(element).containsElement("name").hasTextContent("contrib 2")
                assertThat(element).doesNotContainElement("email")
                assertThat(element).doesNotContainElement("uri")
            }
            writePodcastDataXPathMultiple("//atom:link", podcast) { elements ->
                assertThat(elements).hasSize(2)
            }
            writePodcastDataXPath("//atom:link[1]", podcast = podcast) { element ->
                assertThat(element).hasAttribute("href", namespace = null).hasValue("link 1")
                assertThat(element).hasNoAttribute("hrefLang", namespace = null)
                assertThat(element).hasNoAttribute("hrefResolved", namespace = null)
                assertThat(element).hasNoAttribute("length", namespace = null)
                assertThat(element).hasNoAttribute("rel", namespace = null)
                assertThat(element).hasNoAttribute("title", namespace = null)
                assertThat(element).hasNoAttribute("type", namespace = null)
            }
            writePodcastDataXPath("//atom:link[2]", podcast = podcast) { element ->
                assertThat(element).hasAttribute("href", namespace = null).hasValue("link 2")
                assertThat(element).hasNoAttribute("hrefLang", namespace = null)
                assertThat(element).hasNoAttribute("hrefResolved", namespace = null)
                assertThat(element).hasNoAttribute("length", namespace = null)
                assertThat(element).hasNoAttribute("rel", namespace = null)
                assertThat(element).hasNoAttribute("title", namespace = null)
                assertThat(element).hasNoAttribute("type", namespace = null)
            }
        }
    }

    @Test
    internal fun `should write the correct atom tags to the item when there is data to write`() {
        assertAll {
            writeEpisodeData("author") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/atom:author[1]")
                assertThat(diff).hasNoDifferences()
            }
            writeEpisodeData("contributor") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/atom:contributor[1]")
                assertThat(diff).hasNoDifferences()
            }
            writeEpisodeData("link") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/atom:link[1]")
                assertThat(diff).hasNoDifferences()
            }
        }
    }

    @Test
    internal fun `should not write atom tags to the item when there is no data to write`() {
        assertAll {
            assertTagIsNotWrittenToEpisode(anEpisode(atom = null), "author")
            assertTagIsNotWrittenToEpisode(anEpisode(atom = null), "contributor")
            assertTagIsNotWrittenToEpisode(anEpisode(atom = null), "link")
        }
    }

    @Test
    internal fun `should not write atom tags to the item when the data is all blank`() {
        val episode = anEpisode(
            atom = anEpisodeAtom(
                authors = listOf(
                    aPerson(" ", " ", " "), aPerson("", "", "")
                ),
                contributors = listOf(
                    aPerson(" ", " ", " "), aPerson("", "", "")
                ),
                links = listOf(
                    aLink(" ", " ", " ", " ", " ", " ", " "),
                    aLink("", "", "", "", "", "", "")
                )
            )
        )
        assertAll {
            assertTagIsNotWrittenToEpisode(episode, "author")
            assertTagIsNotWrittenToEpisode(episode, "contributor")
            assertTagIsNotWrittenToEpisode(episode, "link")
        }
    }

    @Test
    internal fun `should not write atom subtags to the item when their data is blank`() {
        val episode = anEpisode(
            atom = anEpisodeAtom(
                authors = listOf(
                    aPerson("author 1", " ", " "), aPerson("author 2", "", "")
                ),
                contributors = listOf(
                    aPerson("contrib 1", " ", " "), aPerson("contrib 2", "", "")
                ),
                links = listOf(
                    aLink("link 1", " ", " ", " ", " ", " ", " "),
                    aLink("link 2", "", "", "", "", "", "")
                )
            )
        )
        assertAll {
            writeEpisodeDataXPathMultiple("//atom:author", episode) { elements ->
                assertThat(elements).hasSize(2)
            }
            writeEpisodeDataXPath("//atom:author[1]", episode = episode) { element ->
                assertThat(element).containsElement("name").hasTextContent("author 1")
                assertThat(element).doesNotContainElement("email")
                assertThat(element).doesNotContainElement("uri")
            }
            writeEpisodeDataXPath("//atom:author[2]", episode = episode) { element ->
                assertThat(element).containsElement("name").hasTextContent("author 2")
                assertThat(element).doesNotContainElement("email")
                assertThat(element).doesNotContainElement("uri")
            }
            writeEpisodeDataXPathMultiple("//atom:contributor", episode) { elements ->
                assertThat(elements).hasSize(2)
            }
            writeEpisodeDataXPath("//atom:contributor[1]", episode = episode) { element ->
                assertThat(element).containsElement("name").hasTextContent("contrib 1")
                assertThat(element).doesNotContainElement("email")
                assertThat(element).doesNotContainElement("uri")
            }
            writeEpisodeDataXPath("//atom:contributor[2]", episode = episode) { element ->
                assertThat(element).containsElement("name").hasTextContent("contrib 2")
                assertThat(element).doesNotContainElement("email")
                assertThat(element).doesNotContainElement("uri")
            }
            writeEpisodeDataXPathMultiple("//atom:link", episode) { elements ->
                assertThat(elements).hasSize(2)
            }
            writeEpisodeDataXPath("//atom:link[1]", episode = episode) { element ->
                assertThat(element).hasAttribute("href", namespace = null).hasValue("link 1")
                assertThat(element).hasNoAttribute("hrefLang", namespace = null)
                assertThat(element).hasNoAttribute("hrefResolved", namespace = null)
                assertThat(element).hasNoAttribute("length", namespace = null)
                assertThat(element).hasNoAttribute("rel", namespace = null)
                assertThat(element).hasNoAttribute("title", namespace = null)
                assertThat(element).hasNoAttribute("type", namespace = null)
            }
            writeEpisodeDataXPath("//atom:link[2]", episode = episode) { element ->
                assertThat(element).hasAttribute("href", namespace = null).hasValue("link 2")
                assertThat(element).hasNoAttribute("hrefLang", namespace = null)
                assertThat(element).hasNoAttribute("hrefResolved", namespace = null)
                assertThat(element).hasNoAttribute("length", namespace = null)
                assertThat(element).hasNoAttribute("rel", namespace = null)
                assertThat(element).hasNoAttribute("title", namespace = null)
                assertThat(element).hasNoAttribute("type", namespace = null)
            }
        }
    }
}
