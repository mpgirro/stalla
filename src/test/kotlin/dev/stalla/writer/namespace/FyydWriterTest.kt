package dev.stalla.writer.namespace

import assertk.assertThat
import dev.stalla.hasNoDifferences
import dev.stalla.model.aPodcast
import dev.stalla.model.aPodcastFyyd
import org.junit.jupiter.api.Test

internal class FyydWriterTest : NamespaceWriterTest() {

    override val writer = FyydWriter

    @Test
    internal fun `should write a the correct Fyyd verify tag to the channel when there is data to write`() {
        writePodcastData("verify") { element ->
            val diff = element.diffFromExpected("/rss/channel/fyyd:verify")
            assertThat(diff).hasNoDifferences()
        }
    }

    @Test
    internal fun `should not write a Fyyd verify tag to the item when there is no data to write`() {
        assertTagIsNotWrittenToPodcast(aPodcast(fyyd = null), "verify")
    }

    @Test
    internal fun `should not write a Fyyd verify tag to the item when the data is blank`() {
        assertTagIsNotWrittenToPodcast(aPodcast(fyyd = aPodcastFyyd(" ")), "verify")
    }

    @Test
    internal fun `should not write a Fyyd verify tag to the item when the data is empty`() {
        assertTagIsNotWrittenToPodcast(aPodcast(fyyd = aPodcastFyyd("")), "verify")
    }
}
