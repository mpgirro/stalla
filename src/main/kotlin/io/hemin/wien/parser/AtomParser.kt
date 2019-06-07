package io.hemin.wien.parser

import io.hemin.wien.builder.EpisodeBuilder
import io.hemin.wien.builder.LinkBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.builder.PodcastBuilder
import io.hemin.wien.model.Link
import io.hemin.wien.model.Person
import io.hemin.wien.util.NodeListWrapper.Companion.asList
import org.w3c.dom.Node

/**
 * Parser implementation for the Atom namespace.
 *
 * The namespace URI is: `http://www.w3.org/2005/Atom`
 */
class AtomParser : NamespaceParser() {

    override val namespaceURI: String? = "http://www.w3.org/2005/Atom"

    override fun parse(builder: PodcastBuilder, node: Node) = valid(node) {
        when (node.localName) {
            "contributor" -> builder.atom.addContributor(toPerson(node))
            "author"      -> builder.atom.addAuthor(toPerson(node))
            "link"        -> builder.atom.addLink(toLink(node))
            else          -> pass
        }
    }

    override fun parse(builder: EpisodeBuilder, node: Node) = valid(node) {
        when (node.localName) {
            "contributor" -> builder.atom.addContributor(toPerson(node))
            "author"      -> builder.atom.addAuthor(toPerson(node))
            "link"        -> builder.atom.addLink(toLink(node))
            else          -> pass
        }
    }

    /**
     * Transforms an <atom:link>` element into an instance of the [Link] model class.
     *
     * @param node The DOM node representing the `<itunes:link>` element.
     * @return The [Link] instance with the `<itunes:link>` elements data, or null if all data was empty.
     */
    fun toLink(node: Node): Link? = valid(node) {
        LinkBuilder()
            .href(attributeValueByName(it, "href"))
            .hrefLang(attributeValueByName(it, "hrefLang"))
            .hrefResolved(attributeValueByName(it, "hrefResolved"))
            .length(attributeValueByName(it, "length"))
            .rel(attributeValueByName(it, "rel"))
            .title(attributeValueByName(it, "title"))
            .type(attributeValueByName(it, "type"))
            .build()
    }

    /**
     * Transforms an <atom:link>` element into an instance of the [Person] model class.
     *
     * @param node The DOM node representing the `<itunes:link>` element.
     * @return The [Link] instance with the `<itunes:link>` elements data, or null if all data was empty.
     */
    fun toPerson(node: Node): Person? = valid(node) {
        val builder = PersonBuilder()
        for (child in asList(node.childNodes)) {
            when(child.localName) {
                "name"  -> builder.name(toText(child))
                "email" -> builder.email(toText(child))
                "uri"   -> builder.uri(toText(child))
            }
        }
        builder.build()
    }

}
