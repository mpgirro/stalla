package io.hemin.wien.writer.namespace

import assertk.assertAll
import assertk.assertThat
import io.hemin.wien.hasNoDifferences
import io.hemin.wien.model.episode.anEpisode
import io.hemin.wien.model.episode.anEpisodePodcast
import io.hemin.wien.model.episode.anEpisodePodcastChapters
import io.hemin.wien.model.episode.anEpisodePodcastSoundbite
import io.hemin.wien.model.episode.anEpisodePodcastTranscript
import io.hemin.wien.model.podcast.aPodcast
import io.hemin.wien.model.podcast.aPodcastPodcast
import io.hemin.wien.model.podcast.aPodcastPodcastFunding
import io.hemin.wien.model.podcast.aPodcastPodcastLocked
import org.junit.jupiter.api.Test
import java.time.Duration

internal class PodcastNamespaceWriterTest : NamespaceWriterTest() {

    override val writer = PodcastNamespaceWriter()

    @Test
    internal fun `should write the correct podcast tags to the channel when there is data to write`() {
        assertAll {
            writePodcastData("locked") { element ->
                val diff = element.diffFromExpected("/rss/channel/podcast:locked")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("funding") { element ->
                val diff = element.diffFromExpected("/rss/channel/podcast:funding")
                assertThat(diff).hasNoDifferences()
            }
        }
    }

    @Test
    internal fun `should not write podcast tags to the channel when there is no data to write`() {
        val podcast = aPodcast(podcast = null)
        assertAll {
            assertTagIsNotWrittenToPodcast(podcast, "locked")
            assertTagIsNotWrittenToPodcast(podcast, "funding")
        }
    }

    @Test
    internal fun `should not write podcast tags to the channel when the data is blank`() {
        val podcast = aPodcast(
            podcast = aPodcastPodcast(
                locked = aPodcastPodcastLocked(" "),
                funding = listOf(aPodcastPodcastFunding(" ", " "))
            )
        )
        assertAll {
            assertTagIsNotWrittenToPodcast(podcast, "locked")
            assertTagIsNotWrittenToPodcast(podcast, "funding")
        }
    }

    @Test
    internal fun `should not write podcast tags to the channel when the data is empty`() {
        val podcast = aPodcast(
            podcast = aPodcastPodcast(
                locked = aPodcastPodcastLocked(""),
                funding = listOf(aPodcastPodcastFunding("", ""))
            )
        )
        assertAll {
            assertTagIsNotWrittenToPodcast(podcast, "locked")
            assertTagIsNotWrittenToPodcast(podcast, "funding")
        }
    }

    @Test
    internal fun `should write the correct podcast tags to the item when there is data to write`() {
        assertAll {
            writeEpisodeData("transcript") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/podcast:transcript")
                assertThat(diff).hasNoDifferences()
            }
            writeEpisodeData("soundbite") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/podcast:soundbite")
                assertThat(diff).hasNoDifferences()
            }
            writeEpisodeData("chapters") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/podcast:chapters")
                assertThat(diff).hasNoDifferences()
            }
        }
    }

    @Test
    internal fun `should not write podcast tags to the item when there is no data to write`() {
        val episode = anEpisode(podcast = null)
        assertAll {
            assertTagIsNotWrittenToEpisode(episode, "transcript")
            assertTagIsNotWrittenToEpisode(episode, "soundbite")
            assertTagIsNotWrittenToEpisode(episode, "chapters")
        }
    }

    @Test
    internal fun `should not write podcast tags to the item when the data is blank`() {
        val episode = anEpisode(
            podcast = anEpisodePodcast(
                transcripts = listOf(anEpisodePodcastTranscript(" ")),
                chapters = anEpisodePodcastChapters(" ")
            )
        )
        assertAll {
            assertTagIsNotWrittenToEpisode(episode, "transcript")
            assertTagIsNotWrittenToEpisode(episode, "chapters")
        }
    }

    @Test
    internal fun `should not write podcast tags to the item when the data is empty`() {
        val episode = anEpisode(
            podcast = anEpisodePodcast(
                transcripts = listOf(anEpisodePodcastTranscript("")),
                chapters = anEpisodePodcastChapters("")
            )
        )
        assertAll {
            assertTagIsNotWrittenToEpisode(episode, "transcript")
            assertTagIsNotWrittenToEpisode(episode, "chapters")
        }
    }

    @Test
    internal fun `should not write podcast soundbite tags to the item when the durations are invalid`() {
        val episode = anEpisode(
            podcast = anEpisodePodcast(
                soundbites = listOf(
                    anEpisodePodcastSoundbite(startTime = Duration.ofSeconds(-1)),
                    anEpisodePodcastSoundbite(duration = Duration.ZERO),
                    anEpisodePodcastSoundbite(duration = Duration.ofSeconds(-1))
                )
            )
        )
        assertAll {
            assertTagIsNotWrittenToEpisode(episode, "soundbite")
        }
    }
}
