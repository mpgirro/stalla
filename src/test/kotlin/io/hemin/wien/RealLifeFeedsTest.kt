package io.hemin.wien

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.exists
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import java.io.File

internal class RealLifeFeedsTest {

    private val resourceFilesPath = "/xml/real-life-feeds"

    @TestFactory
    fun dynamicTestsWithCollection(): Collection<DynamicTest> {
        val testFiles = allResourceFilesIn(resourceFilesPath)

        return testFiles.map { testFile ->
            DynamicTest.dynamicTest(
                "[${testFile.nameWithoutExtension}] should be able to parse, write and re-parse a real-life feed correctly"
            ) { testRealLifeFeed(testFile) }
        }
    }

    private fun testRealLifeFeed(file: File) {
        assertAll {
            val parsedPodcast = PodcastRssParser.parse(file)
            assertThat(parsedPodcast, "parsed podcast").isNotNull()

            val tmpFile = File.createTempFile("wien_test", "writer_output")
            PodcastRssWriter.writeRssFeed(parsedPodcast!!, tmpFile)

            assertThat(tmpFile, "written file").exists()
            assertThat(tmpFile, "written file").isNotEmpty()

            val reparsedPodcast = PodcastRssParser.parse(tmpFile)
            assertThat(reparsedPodcast, "written file for ${file.nameWithoutExtension} matches original Podcast").isEqualTo(parsedPodcast)

            tmpFile.delete()
        }
    }
}
