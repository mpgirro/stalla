package io.hemin.wien.writer.namespace

import assertk.assertThat
import io.hemin.wien.hasNoDifferences
import io.hemin.wien.model.episode.anEpisode
import org.junit.jupiter.api.Test

internal class ContentWriterTest : NamespaceWriterTest() {

    override val writer = ContentWriter()

    @Test
    internal fun `should write a the correct content_encoded tag to the item when there is data to write`() {
        writeEpisodeData("encoded") { element ->
            val diff = element.diffFromExpected("/rss/channel/item[1]/content:encoded")
            assertThat(diff).hasNoDifferences()
        }
    }

    @Test
    internal fun `should not write a content_encoded tag to the item when there is no data to write`() {
        assertTagIsNotWrittenToEpisode(anEpisode(content = null), "encoded")
    }
}
