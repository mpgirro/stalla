package dev.stalla.writer.namespace

import assertk.assertAll
import assertk.assertThat
import dev.stalla.hasNoDifferences
import dev.stalla.model.StyledDuration
import dev.stalla.model.episode.anEpisode
import dev.stalla.model.episode.anEpisodePodcastindex
import dev.stalla.model.episode.anEpisodePodcastindexChapters
import dev.stalla.model.episode.anEpisodePodcastindexSoundbite
import dev.stalla.model.episode.anEpisodePodcastindexTranscript
import dev.stalla.model.podcast.aPodcast
import dev.stalla.model.podcast.aPodcastPodcastindex
import dev.stalla.model.podcast.aPodcastPodcastindexFunding
import dev.stalla.model.podcast.aPodcastPodcastindexLocked
import org.junit.jupiter.api.Test
import java.time.Duration

internal class PodcastindexWriterTest : NamespaceWriterTest() {

    override val writer = PodcastindexWriter

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
        val podcast = aPodcast(podcastindex = null)
        assertAll {
            assertTagIsNotWrittenToPodcast(podcast, "locked")
            assertTagIsNotWrittenToPodcast(podcast, "funding")
        }
    }

    @Test
    internal fun `should not write podcast tags to the channel when the data is blank`() {
        val podcast = aPodcast(
            podcastindex = aPodcastPodcastindex(
                locked = aPodcastPodcastindexLocked(" "),
                funding = listOf(aPodcastPodcastindexFunding(" ", " "))
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
            podcastindex = aPodcastPodcastindex(
                locked = aPodcastPodcastindexLocked(""),
                funding = listOf(aPodcastPodcastindexFunding("", ""))
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
        val episode = anEpisode(podcastindex = null)
        assertAll {
            assertTagIsNotWrittenToEpisode(episode, "transcript")
            assertTagIsNotWrittenToEpisode(episode, "soundbite")
            assertTagIsNotWrittenToEpisode(episode, "chapters")
        }
    }

    @Test
    internal fun `should not write podcast tags to the item when the data is blank`() {
        val episode = anEpisode(
            podcastindex = anEpisodePodcastindex(
                transcripts = listOf(anEpisodePodcastindexTranscript(" ")),
                chapters = anEpisodePodcastindexChapters(" ")
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
            podcastindex = anEpisodePodcastindex(
                transcripts = listOf(anEpisodePodcastindexTranscript("")),
                chapters = anEpisodePodcastindexChapters("")
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
            podcastindex = anEpisodePodcastindex(
                soundbites = listOf(
                    anEpisodePodcastindexSoundbite(startTime = StyledDuration.secondsAndFraction(-1)),
                    anEpisodePodcastindexSoundbite(duration = StyledDuration.secondsAndFraction(0)),
                    anEpisodePodcastindexSoundbite(duration = StyledDuration.secondsAndFraction(-1, 1891))
                )
            )
        )
        assertAll {
            assertTagIsNotWrittenToEpisode(episode, "soundbite")
        }
    }
}
