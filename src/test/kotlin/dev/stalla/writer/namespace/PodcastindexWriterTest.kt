package dev.stalla.writer.namespace

import assertk.assertAll
import assertk.assertThat
import dev.stalla.hasNoDifferences
import dev.stalla.model.StyledDuration
import dev.stalla.model.aPodcast
import dev.stalla.model.aPodcastPodcastindex
import dev.stalla.model.aPodcastPodcastindexFunding
import dev.stalla.model.aPodcastPodcastindexLocation
import dev.stalla.model.aPodcastPodcastindexLocked
import dev.stalla.model.aPodcastPodcastindexPerson
import dev.stalla.model.anEpisode
import dev.stalla.model.anEpisodePodcastindex
import dev.stalla.model.anEpisodePodcastindexChapters
import dev.stalla.model.anEpisodePodcastindexSoundbite
import dev.stalla.model.anEpisodePodcastindexTranscript
import org.junit.jupiter.api.Test

internal class PodcastindexWriterTest : NamespaceWriterTest() {

    override val writer = PodcastindexWriter

    @Test
    internal fun `should write the correct Podcastindex tags to the channel when there is data to write`() {
        assertAll {
            writePodcastData("locked") { element ->
                val diff = element.diffFromExpected("/rss/channel/podcast:locked")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("funding") { element ->
                val diff = element.diffFromExpected("/rss/channel/podcast:funding")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("person") { element ->
                val diff = element.diffFromExpected("/rss/channel/podcast:person")
                assertThat(diff).hasNoDifferences()
            }
            writePodcastData("location") { element ->
                val diff = element.diffFromExpected("/rss/channel/podcast:location")
                assertThat(diff).hasNoDifferences()
            }
        }
    }

    @Test
    internal fun `should not write Podcastindex tags to the channel when there is no data to write`() {
        val podcast = aPodcast(podcastindex = null)
        assertAll {
            assertTagIsNotWrittenToPodcast(podcast, "locked")
            assertTagIsNotWrittenToPodcast(podcast, "funding")
            assertTagIsNotWrittenToPodcast(podcast, "person")
            assertTagIsNotWrittenToPodcast(podcast, "location")
        }
    }

    @Test
    internal fun `should not write Podcastindex tags to the channel when the data is blank`() {
        val podcast = aPodcast(
            podcastindex = aPodcastPodcastindex(
                locked = aPodcastPodcastindexLocked(" "),
                funding = listOf(aPodcastPodcastindexFunding(" ", " ")),
                persons = listOf(aPodcastPodcastindexPerson(" ", " ", " ", " ", " ")),
                location = aPodcastPodcastindexLocation(" ")
            )
        )
        assertAll {
            assertTagIsNotWrittenToPodcast(podcast, "locked")
            assertTagIsNotWrittenToPodcast(podcast, "funding")
            assertTagIsNotWrittenToPodcast(podcast, "person")
            assertTagIsNotWrittenToPodcast(podcast, "location")
        }
    }

    @Test
    internal fun `should not write Podcastindex tags to the channel when the data is empty`() {
        val podcast = aPodcast(
            podcastindex = aPodcastPodcastindex(
                locked = aPodcastPodcastindexLocked(""),
                funding = listOf(aPodcastPodcastindexFunding("", "")),
                persons = listOf(aPodcastPodcastindexPerson("", "", "", "", "")),
                location = aPodcastPodcastindexLocation("")
            )
        )
        assertAll {
            assertTagIsNotWrittenToPodcast(podcast, "locked")
            assertTagIsNotWrittenToPodcast(podcast, "funding")
            assertTagIsNotWrittenToPodcast(podcast, "person")
            assertTagIsNotWrittenToPodcast(podcast, "location")
        }
    }

    @Test
    internal fun `should write the correct Podcastindex tags to the item when there is data to write`() {
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
            writeEpisodeData("person") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/podcast:person")
                assertThat(diff).hasNoDifferences()
            }
            writeEpisodeData("location") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/podcast:location")
                assertThat(diff).hasNoDifferences()
            }
            writeEpisodeData("season") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/podcast:season")
                assertThat(diff).hasNoDifferences()
            }
            writeEpisodeData("episode") { element ->
                val diff = element.diffFromExpected("/rss/channel/item[1]/podcast:episode")
                assertThat(diff).hasNoDifferences()
            }
        }
    }

    @Test
    internal fun `should not write Podcastindex tags to the item when there is no data to write`() {
        val episode = anEpisode(podcastindex = null)
        assertAll {
            assertTagIsNotWrittenToEpisode(episode, "transcript")
            assertTagIsNotWrittenToEpisode(episode, "soundbite")
            assertTagIsNotWrittenToEpisode(episode, "chapters")
            assertTagIsNotWrittenToEpisode(episode, "person")
            assertTagIsNotWrittenToEpisode(episode, "location")
            assertTagIsNotWrittenToEpisode(episode, "season")
            assertTagIsNotWrittenToEpisode(episode, "episode")
        }
    }

    @Test
    internal fun `should not write Podcastindex tags to the item when the data is blank`() {
        val episode = anEpisode(
            podcastindex = anEpisodePodcastindex(
                transcripts = listOf(anEpisodePodcastindexTranscript(" ")),
                chapters = anEpisodePodcastindexChapters(" "),
                persons = listOf(aPodcastPodcastindexPerson(" ", " ", " ", " ", " ")),
                location = aPodcastPodcastindexLocation(" ")
            )
        )
        assertAll {
            assertTagIsNotWrittenToEpisode(episode, "transcript")
            assertTagIsNotWrittenToEpisode(episode, "chapters")
            assertTagIsNotWrittenToEpisode(episode, "person")
            assertTagIsNotWrittenToEpisode(episode, "location")
        }
    }

    @Test
    internal fun `should not write Podcastindex tags to the item when the data is empty`() {
        val episode = anEpisode(
            podcastindex = anEpisodePodcastindex(
                transcripts = listOf(anEpisodePodcastindexTranscript("")),
                chapters = anEpisodePodcastindexChapters(""),
                persons = listOf(aPodcastPodcastindexPerson("", "", "", "", "")),
                location = aPodcastPodcastindexLocation("")
            )
        )
        assertAll {
            assertTagIsNotWrittenToEpisode(episode, "transcript")
            assertTagIsNotWrittenToEpisode(episode, "chapters")
            assertTagIsNotWrittenToEpisode(episode, "person")
            assertTagIsNotWrittenToEpisode(episode, "location")
        }
    }

    @Test
    internal fun `should not write Podcastindex Soundbite tags to the item when the durations are invalid`() {
        val episode = anEpisode(
            podcastindex = anEpisodePodcastindex(
                soundbites = listOf(
                    anEpisodePodcastindexSoundbite(startTime = StyledDuration.secondsAndFraction(1, positive = false)),
                    anEpisodePodcastindexSoundbite(duration = StyledDuration.secondsAndFraction(0)),
                    anEpisodePodcastindexSoundbite(duration = StyledDuration.secondsAndFraction(1, 1891, positive = false))
                )
            )
        )
        assertAll {
            assertTagIsNotWrittenToEpisode(episode, "soundbite")
        }
    }
}
