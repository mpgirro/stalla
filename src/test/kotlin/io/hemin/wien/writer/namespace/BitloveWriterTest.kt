package io.hemin.wien.writer.namespace

import assertk.Assert
import assertk.assertAll
import assertk.assertThat
import assertk.fail
import io.hemin.wien.hasNoAttribute
import io.hemin.wien.hasNoDifferences
import io.hemin.wien.model.episode.anEpisode
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.util.appendElement
import io.hemin.wien.util.findElementByName
import org.junit.jupiter.api.Test
import org.w3c.dom.Node

internal class BitloveWriterTest : NamespaceWriterTest() {

    override val writer = BitloveWriter()

    @Test
    internal fun `should write a the correct bitlove_guid attribute to the item enclosure tag when there is data to write`() {
        val itemElement = createChannelElement().createItemElement()

        // We need to run the RSS writer first, to create the <enclosure> tag (among others)
        val episode = anEpisode()
        RssWriter().tryWritingEpisodeData(episode, itemElement)
        val enclosureItem = itemElement.findElementByName("enclosure")
            ?: throw IllegalStateException("The RssWriter did not create an <enclosure> tag, but was expected to")

        writer.tryWritingEpisodeData(episode, itemElement)
        val diff = enclosureItem.diffFromExpected("/rss/channel/item[1]/enclosure")
        assertThat(diff).hasNoDifferences()
    }

    @Test
    internal fun `should not write a bitlove_guid attribute to the item enclosure tag when there is no data to write`() {
        assertAll {
            val episodeWithoutBitlove = anEpisode(bitlove = null)
            assertTagIsNotWrittenToEpisode(episodeWithoutBitlove, "encoded")

            val itemElement = createChannelElement().createItemElement()
            val enclosureItem = itemElement.appendElement("enclosure")
            writer.tryWritingEpisodeData(episodeWithoutBitlove, itemElement)
            assertThat(enclosureItem).hasNoAttribute("guid", writer.namespace)
        }
    }
}
