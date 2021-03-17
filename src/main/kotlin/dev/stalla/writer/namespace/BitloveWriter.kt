package dev.stalla.writer.namespace

import dev.stalla.dom.findElementByName
import dev.stalla.dom.setAttributeWithNS
import dev.stalla.model.Episode
import dev.stalla.model.Podcast
import dev.stalla.util.FeedNamespace
import dev.stalla.util.InternalAPI2
import dev.stalla.writer.NamespaceWriter
import org.w3c.dom.Element

/**
 * Writer implementation for the Bitlove namespace.
 *
 * The namespace URI is: `http://bitlove.org`
 */
@InternalAPI2
internal object BitloveWriter : NamespaceWriter() {

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
