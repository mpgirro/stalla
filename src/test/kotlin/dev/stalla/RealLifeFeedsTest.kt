package dev.stalla

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.exists
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.io.File

@ExtendWith(TemporaryFileParameterResolver::class)
internal class RealLifeFeedsTest {

    private class RealLifeFeedsProvider : ArgumentsProvider by arguments(*allResourceFilesIn(resourceFilesPath).toTypedArray())

    @ParameterizedTest(name = "Can parse, write and re-parse a real-life feed: {0}")
    @ArgumentsSource(RealLifeFeedsProvider::class)
    fun `should be able to parse, write and re-parse a real-life feed correctly`(testFile: File, @TemporaryFile tmpFile: File) {
        assertAll {
            val parsedPodcast = PodcastRssParser.parse(testFile)
            assertThat(parsedPodcast, "parsed podcast").isNotNull()

            PodcastRssWriter.write(parsedPodcast!!, tmpFile)

            assertThat(tmpFile, "written file").exists()
            assertThat(tmpFile, "written file").isNotEmpty()

            val reparsedPodcast = PodcastRssParser.parse(tmpFile)
            assertThat(reparsedPodcast, "written file for ${testFile.nameWithoutExtension} matches original Podcast").isEqualTo(parsedPodcast)
        }
    }

    companion object {
        const val resourceFilesPath = "xml/real-life-feeds"
    }
}
