package io.hemin.wien.writer.namespace

import io.hemin.wien.dom.appendElement
import io.hemin.wien.dom.appendPersonElement
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Link
import io.hemin.wien.model.Person
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.util.isNeitherNullNorBlank
import io.hemin.wien.writer.NamespaceWriter
import org.w3c.dom.Element

/**
 * Writer implementation for the Atom namespace.
 *
 * The namespace URI is: `http://www.w3.org/2005/Atom`
 */
internal class AtomWriter : NamespaceWriter() {

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

    private fun Element.appendPersonElements(tagName: String, persons: List<Person>) {
        for (person in persons) {
            appendPersonElement(tagName, person, namespace)
        }
    }

    private fun Element.appendLinkElements(links: List<Link>) {
        for (link in links) {
            if (link.href.isBlank()) continue

            appendElement("link", namespace) {
                setAttribute("href", link.href)

                if (link.hrefLang.isNeitherNullNorBlank()) {
                    setAttribute("hrefLang", link.hrefLang)
                }

                if (link.hrefResolved.isNeitherNullNorBlank()) {
                    setAttribute("hrefResolved", link.hrefResolved)
                }

                if (link.length.isNeitherNullNorBlank()) {
                    setAttribute("length", link.length)
                }

                if (link.rel.isNeitherNullNorBlank()) {
                    setAttribute("rel", link.rel)
                }

                if (link.title.isNeitherNullNorBlank()) {
                    setAttribute("title", link.title)
                }

                if (link.type.isNeitherNullNorBlank()) {
                    setAttribute("type", link.type)
                }
            }
        }
    }
}
