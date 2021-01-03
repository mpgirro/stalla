package io.hemin.wien.parser.namespace

import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.builder.validating.ValidatingLinkBuilder
import io.hemin.wien.builder.validating.ValidatingPersonBuilder
import io.hemin.wien.model.Link
import io.hemin.wien.model.Person
import io.hemin.wien.parser.NamespaceParser
import io.hemin.wien.util.NodeListWrapper.Companion.asListOfNodes
import org.w3c.dom.Node

/**
 * Parser implementation for the Atom namespace.
 *
 * The namespace URI is: `http://www.w3.org/2005/Atom`
 */
internal class AtomParser : NamespaceParser() {

    override val namespaceURI: String = "http://www.w3.org/2005/Atom"

    override fun parse(builder: PodcastBuilder, node: Node) = valid(node) {
        when (node.localName) {
            "contributor" -> {
                val person = toPerson(node) ?: return@valid
                builder.atom.addContributor(person)
            }
            "author" -> {
                val person = toPerson(node) ?: return@valid
                builder.atom.addAuthor(person)
            }
            "link" -> {
                val link = toLink(node) ?: return@valid
                builder.atom.addLink(link)
            }
            else -> pass
        }
    }

    override fun parse(builder: EpisodeBuilder, node: Node) = valid(node) {
        when (node.localName) {
            "contributor" -> {
                val person = toPerson(node) ?: return@valid
                builder.atom.addContributor(person)
            }
            "author" -> {
                val person = toPerson(node) ?: return@valid
                builder.atom.addAuthor(person)
            }
            "link" -> {
                val link = toLink(node) ?: return@valid
                builder.atom.addLink(link)
            }
            else -> pass
        }
    }

    /**
     * Transforms an <atom:link>` element into an instance of the [Link] model class.
     *
     * @param node The DOM node representing the `<itunes:link>` element.
     * @return The [Link] instance with the `<itunes:link>` elements data, or null if all data was empty.
     */
    private fun toLink(node: Node): Link? = valid(node) {
        val href = attributeValueByName(it, "href") ?: return@valid null

        ValidatingLinkBuilder()
            .href(href)
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
    private fun toPerson(node: Node): Person? = valid(node) {
        val builder = ValidatingPersonBuilder()
        for (child in node.childNodes.asListOfNodes()) {
            when (child.localName) {
                "name" -> {
                    val name = toText(child)
                    if (name != null) builder.name(name)
                }
                "email" -> builder.email(toText(child))
                "uri" -> builder.uri(toText(child))
            }
        }
        builder.build()
    }
}
