package io.hemin.wien.writer.namespace

import io.hemin.wien.model.Episode
import io.hemin.wien.model.Link
import io.hemin.wien.model.Person
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.util.appendElement
import io.hemin.wien.util.appendPersonElement
import io.hemin.wien.writer.NamespaceWriter
import org.w3c.dom.Element

/**
 * Writer implementation for the Atom namespace.
 *
 * The namespace URI is: `http://www.w3.org/2005/Atom`
 */
internal class AtomWriter : NamespaceWriter() {

    override val namespace = FeedNamespace.ATOM

    override fun writeChannelData(channel: Podcast, element: Element) {
        val atom = channel.atom ?: return

        element.appendPersonElements("contributor", atom.contributors)
        element.appendPersonElements("author", atom.authors)
        element.appendLinkElements(atom.links)
    }

    override fun writeItemData(episode: Episode, element: Element) {
        val atom = episode.atom ?: return

        element.appendPersonElements("contributor", atom.contributors)
        element.appendPersonElements("author", atom.authors)
        element.appendLinkElements(atom.links)
    }

    private fun Element.appendPersonElements(tagName: String, persons: List<Person>) {
        for (person in persons) {
            appendPersonElement(tagName, person, namespace)
        }
    }

    private fun Element.appendLinkElements(links: List<Link>) {
        for (link in links) {
            appendElement("link", namespace) {
                setAttribute("href", link.href)

                if (link.hrefLang != null) {
                    setAttribute("hrefLang", link.hrefLang)
                }

                if (link.hrefResolved != null) {
                    setAttribute("hrefResolved", link.hrefResolved)
                }

                if (link.length != null) {
                    setAttribute("length", link.length)
                }

                if (link.rel != null) {
                    setAttribute("rel", link.rel)
                }

                if (link.title != null) {
                    setAttribute("title", link.title)
                }

                if (link.type != null) {
                    setAttribute("type", link.type)
                }
            }
        }
    }
}
