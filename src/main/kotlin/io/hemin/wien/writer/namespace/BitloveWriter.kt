package io.hemin.wien.writer.namespace

import io.hemin.wien.dom.findElementByName
import io.hemin.wien.dom.setAttributeWithNS
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.writer.NamespaceWriter
import org.w3c.dom.Element

/**
 * Writer implementation for the Bitlove namespace.
 *
 * The namespace URI is: `http://bitlove.org`
 */
internal class BitloveWriter : NamespaceWriter() {

    override val namespace = FeedNamespace.BITLOVE

    override fun Element.appendPodcastData(podcast: Podcast) {
        // Nothing to do here
    }

    override fun Element.appendEpisodeData(episode: Episode) {
        val guid = episode.bitlove?.guid ?: return
        if (guid.isBlank()) return

        val enclosureElement = findElementByName("enclosure")
        requireNotNull(enclosureElement) { "This writer must execute after the episode <enclosure> has been written" }

        enclosureElement.setAttributeWithNS("guid", namespace) { value = guid.trim() }
    }
}
