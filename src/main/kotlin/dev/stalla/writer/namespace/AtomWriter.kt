package dev.stalla.writer.namespace

import dev.stalla.dom.appendAtomPersonElement
import dev.stalla.dom.appendElement
import dev.stalla.model.Episode
import dev.stalla.model.Podcast
import dev.stalla.model.atom.AtomPerson
import dev.stalla.model.atom.Link
import dev.stalla.util.FeedNamespace
import dev.stalla.util.InternalAPI2
import dev.stalla.util.isNeitherNullNorBlank
import dev.stalla.writer.NamespaceWriter
import org.w3c.dom.Element

/**
 * Writer implementation for the Atom namespace.
 *
 * The namespace URI is: `http://www.w3.org/2005/Atom`
 */
@InternalAPI2
internal object AtomWriter : NamespaceWriter() {

    override val namespace = FeedNamespace.ATOM

    override fun Element.appendPodcastData(podcast: Podcast) {
        val atom = podcast.atom ?: return

        appendPersonElements("contributor", atom.contributors)
        appendPersonElements("author", atom.authors)
        appendLinkElements(atom.links)
    }

    override fun Element.appendEpisodeData(episode: Episode) {
        val atom = episode.atom ?: return

        appendPersonElements("contributor", atom.contributors)
        appendPersonElements("author", atom.authors)
        appendLinkElements(atom.links)
    }

    private fun Element.appendPersonElements(tagName: String, persons: List<AtomPerson>) {
        for (person in persons) {
            appendAtomPersonElement(tagName, person, namespace)
        }
    }

    private fun Element.appendLinkElements(links: List<Link>) {
        for (link in links) {
            if (link.href.isBlank()) continue

            appendElement("link", namespace) {
                setAttribute("href", link.href.trim())

                if (link.hrefLang.isNeitherNullNorBlank()) {
                    setAttribute("hrefLang", link.hrefLang.trim())
                }

                if (link.hrefResolved.isNeitherNullNorBlank()) {
                    setAttribute("hrefResolved", link.hrefResolved.trim())
                }

                if (link.length.isNeitherNullNorBlank()) {
                    setAttribute("length", link.length.trim())
                }

                if (link.rel.isNeitherNullNorBlank()) {
                    setAttribute("rel", link.rel.trim())
                }

                if (link.title.isNeitherNullNorBlank()) {
                    setAttribute("title", link.title.trim())
                }

                if (link.type != null) {
                    setAttribute("type", link.type.toString())
                }
            }
        }
    }
}
