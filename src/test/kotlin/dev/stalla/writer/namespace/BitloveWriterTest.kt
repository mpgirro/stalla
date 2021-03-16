package dev.stalla.writer.namespace

import assertk.assertAll
import assertk.assertThat
import dev.stalla.dom.appendElement
import dev.stalla.dom.findElementByName
import dev.stalla.hasNoAttribute
import dev.stalla.hasNoDifferences
import dev.stalla.model.anEpisode
import dev.stalla.model.anEpisodeBitlove
import org.junit.jupiter.api.Test

internal class BitloveWriterTest : NamespaceWriterTest() {

    override val writer = BitloveWriter

    @Test
    internal fun `should write a the correct Bitlove guid attribute to the item enclosure tag when there is data to write`() {
        val itemElement = createChannelElement().createItemElement()

        // We need to run the RSS writer first, to create the <enclosure> tag (among others)
        val episode = anEpisode()
        RssWriter.tryWritingEpisodeData(episode, itemElement)
        val enclosureItem = itemElement.findElementByName("enclosure")
            ?: throw IllegalStateException("The RssWriter did not create an <enclosure> tag, but was expected to")

        writer.tryWritingEpisodeData(episode, itemElement)
        val diff = enclosureItem.diffFromExpected("/rss/channel/item[1]/enclosure")
        assertThat(diff).hasNoDifferences()
    }

    @Test
    internal fun `should not write a Bitlove guid attribute to the item enclosure tag when there is no data to write`() {
        assertAll {
            val episodeWithoutBitlove = anEpisode(bitlove = null)
            assertTagIsNotWrittenToEpisode(episodeWithoutBitlove, "encoded")

            val itemElement = createChannelElement().createItemElement()
            val enclosureItem = itemElement.appendElement("enclosure")
            writer.tryWritingEpisodeData(episodeWithoutBitlove, itemElement)
            assertThat(enclosureItem).hasNoAttribute("guid", writer.namespace)
        }
    }

    @Test
    internal fun `should not write a Bitlove guid attribute to the item enclosure tag when the data is blank`() {
        assertAll {
            val episodeWithoutBitlove = anEpisode(bitlove = anEpisodeBitlove(" "))

            val itemElement = createChannelElement().createItemElement()
            val enclosureItem = itemElement.appendElement("enclosure")
            writer.tryWritingEpisodeData(episodeWithoutBitlove, itemElement)
            assertThat(enclosureItem).hasNoAttribute("guid", writer.namespace)
        }
    }

    @Test
    internal fun `should not write a Bitlove guid attribute to the item enclosure tag when the data is empty`() {
        assertAll {
            val episodeWithoutBitlove = anEpisode(bitlove = anEpisodeBitlove(""))

            val itemElement = createChannelElement().createItemElement()
            val enclosureItem = itemElement.appendElement("enclosure")
            writer.tryWritingEpisodeData(episodeWithoutBitlove, itemElement)
            assertThat(enclosureItem).hasNoAttribute("guid", writer.namespace)
        }
    }
}
