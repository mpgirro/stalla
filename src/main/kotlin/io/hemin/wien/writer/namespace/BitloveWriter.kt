package io.hemin.wien.writer.namespace

import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.writer.NamespaceWriter
import io.hemin.wien.util.asElement
import io.hemin.wien.util.asListOfNodes
import io.hemin.wien.util.findElementByName
import io.hemin.wien.util.setAttributeWithNS
import org.w3c.dom.Element

/**
 * Writer implementation for the Bitlove namespace.
 *
 * The namespace URI is: `http://bitlove.org`
 */
internal class BitloveWriter : NamespaceWriter() {

    override val namespace = FeedNamespace.BITLOVE

    override fun writeChannelData(channel: Podcast, element: Element) {
        // Nothing to do here
    }

    override fun writeItemData(episode: Episode, element: Element) {
        val guid = episode.bitlove?.guid ?: return

        val enclosureElement = element.findElementByName("enclosure")
        requireNotNull(enclosureElement) { "This writer must execute after the episode <enclosure> has been written" }

        enclosureElement.setAttributeWithNS("guid", namespace) { value = guid }
    }
}
