package dev.stalla

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.exists
import assertk.assertions.isEqualTo
import dev.stalla.model.aPodcast
import org.junit.jupiter.api.Test
import java.io.File

internal class PodcastRssWriterTest {

    @Test
    internal fun `should write a feed to a file correctly`() {
        val file = File.createTempFile("stalla_test", "writer_output")

        val podcast = aPodcast()
        PodcastRssWriter.write(podcast, file)

        assertAll {
            assertThat(file, "written file").exists()
            assertThat(file, "written file").isNotEmpty()

            val reparsedPodcast = PodcastRssParser.parse(file)
            assertThat(reparsedPodcast, "written file matches original Podcast").isEqualTo(podcast)

            file.delete()
        }
    }

    @Test
    internal fun `should write a feed to an outputStream correctly`() {
        val file = File.createTempFile("stalla_test", "writer_output")

        val podcast = aPodcast()
        file.outputStream().use { PodcastRssWriter.write(podcast, it) }

        assertAll {
            assertThat(file, "written file").exists()
            assertThat(file, "written file").isNotEmpty()

            val reparsedPodcast = PodcastRssParser.parse(file)
            assertThat(reparsedPodcast, "written file matches original Podcast").isEqualTo(podcast)

            file.delete()
        }
    }

    @Test
    internal fun `should write a feed to a Writer correctly`() {
        val file = File.createTempFile("stalla_test", "writer_output")

        val podcast = aPodcast()
        file.bufferedWriter().use { PodcastRssWriter.write(podcast, it) }

        assertAll {
            assertThat(file, "written file").exists()
            assertThat(file, "written file").isNotEmpty()

            val reparsedPodcast = PodcastRssParser.parse(file)
            assertThat(reparsedPodcast, "written file matches original Podcast").isEqualTo(podcast)

            file.delete()
        }
    }
}
