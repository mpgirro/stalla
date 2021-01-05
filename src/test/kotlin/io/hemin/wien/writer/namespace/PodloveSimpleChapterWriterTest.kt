package io.hemin.wien.writer.namespace

import assertk.assertThat
import io.hemin.wien.hasNoDifferences
import io.hemin.wien.model.episode.anEpisode
import org.junit.jupiter.api.Test

internal class PodloveSimpleChapterWriterTest : NamespaceWriterTest() {

    override val writer = PodloveSimpleChapterWriter()

    @Test
    internal fun `should write the correct psc tags to the item when there is data to write`() {
        writeEpisodeData("chapters") { element ->
            val diff = element.diffFromExpected("/rss/channel/item[1]/psc:chapters[1]")
            assertThat(diff).hasNoDifferences()
        }
    }

    @Test
    internal fun `should not write psc tags to the item when there is no data to write`() {
        assertTagIsNotWrittenToEpisode(anEpisode(podlove = null), "chapters")
    }
}
