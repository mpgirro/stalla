package io.hemin.wien.writer.namespace

import assertk.assertAll
import assertk.assertThat
import io.hemin.wien.hasNoDifferences
import io.hemin.wien.model.episode.anEpisode
import io.hemin.wien.model.podcast.aPodcast
import org.junit.jupiter.api.Test

internal class AtomWriterTest : NamespaceWriterTest() {

    override val writer = AtomWriter()

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
        assertAll {
            assertTagIsNotWrittenToPodcast(aPodcast(atom = null), "author")
            assertTagIsNotWrittenToPodcast(aPodcast(atom = null), "contributor")
            assertTagIsNotWrittenToPodcast(aPodcast(atom = null), "link")
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
}
