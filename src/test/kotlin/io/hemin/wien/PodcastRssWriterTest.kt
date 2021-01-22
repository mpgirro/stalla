package io.hemin.wien

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.exists
import assertk.assertions.isEqualTo
import io.hemin.wien.model.podcast.aPodcast
import org.junit.jupiter.api.Test
import java.io.File

internal class PodcastRssWriterTest {

    @Test
    internal fun `should write a feed to a file correctly`() {
        val file = File.createTempFile("wien_test", "writer_output")

        val podcast = aPodcast()
        PodcastRssWriter.writeRssFeed(podcast, file)

        assertAll {
            assertThat(file, "written file").exists()
            assertThat(file, "written file").isNotEmpty()

            val reparsedPodcast = PodcastRssParser.parse(file)
            assertThat(reparsedPodcast, "written file matches original Podcast").isEqualTo(podcast)

            file.delete()
        }
    }
}
