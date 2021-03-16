package dev.stalla.writer.namespace

import assertk.assertThat
import dev.stalla.hasNoDifferences
import dev.stalla.model.anEpisode
import dev.stalla.model.anEpisodeContent
import org.junit.jupiter.api.Test

internal class ContentWriterTest : NamespaceWriterTest() {

    override val writer = ContentWriter

    @Test
    internal fun `should write a the correct Content encoded tag to the item when there is data to write`() {
        writeEpisodeData("encoded") { element ->
            val diff = element.diffFromExpected("/rss/channel/item[1]/content:encoded")
            assertThat(diff).hasNoDifferences()
        }
    }

    @Test
    internal fun `should not write a Content encoded tag to the item when there is no data to write`() {
        assertTagIsNotWrittenToEpisode(anEpisode(content = null), "encoded")
    }

    @Test
    internal fun `should not write a Content encoded tag to the item when the data is blank`() {
        assertTagIsNotWrittenToEpisode(anEpisode(content = anEpisodeContent(" ")), "encoded")
    }

    @Test
    internal fun `should not write a Content encoded tag to the item when the data is empty`() {
        assertTagIsNotWrittenToEpisode(anEpisode(content = anEpisodeContent("")), "encoded")
    }
}
