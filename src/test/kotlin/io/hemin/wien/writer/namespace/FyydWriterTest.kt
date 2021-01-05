package io.hemin.wien.writer.namespace

import assertk.assertThat
import io.hemin.wien.hasNoDifferences
import io.hemin.wien.model.episode.anEpisode
import io.hemin.wien.model.podcast.aPodcast
import org.junit.jupiter.api.Test

internal class FyydWriterTest : NamespaceWriterTest() {

    override val writer = FyydWriter()

    @Test
    internal fun `should write a the correct fyyd_verify tag to the channel when there is data to write`() {
        writePodcastData("verify") { element ->
            val diff = element.diffFromExpected("/rss/channel/fyyd:verify")
            assertThat(diff).hasNoDifferences()
        }
    }

    @Test
    internal fun `should not write a fyyd_verify tag to the item when there is no data to write`() {
        assertTagIsNotWrittenToPodcast(aPodcast(fyyd = null), "verify")
    }
}
