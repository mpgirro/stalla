package io.hemin.wien.parser.namespace

import io.hemin.wien.builder.LinkBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
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
                val personBuilder = toPersonBuilder(node, builder.createPersonBuilder()) ?: return@valid
                builder.atom.addContributorBuilder(personBuilder)
            }
            "author" -> {
                val personBuilder = toPersonBuilder(node, builder.createPersonBuilder()) ?: return@valid
                builder.atom.addAuthorBuilder(personBuilder)
            }
            "link" -> {
                val linkBuilder = toLinkBuilder(node, builder.createLinkBuilder()) ?: return@valid
                builder.atom.addLinkBuilder(linkBuilder)
            }
            else -> pass
        }
    }

    override fun parse(builder: EpisodeBuilder, node: Node) = valid(node) {
        when (node.localName) {
            "contributor" -> {
                val person = toPersonBuilder(node, builder.createPersonBuilder()) ?: return@valid
                builder.atom.addContributorBuilder(person)
            }
            "author" -> {
                val person = toPersonBuilder(node, builder.createPersonBuilder()) ?: return@valid
                builder.atom.addAuthorBuilder(person)
            }
            "link" -> {
                val link = toLinkBuilder(node, builder.createLinkBuilder()) ?: return@valid
                builder.atom.addLinkBuilder(link)
            }
            else -> pass
        }
    }

    private fun toLinkBuilder(node: Node, linkBuilder: LinkBuilder): LinkBuilder? = valid(node) {
        val href = attributeValueByName(it, "href") ?: return@valid null

        linkBuilder
            .href(href)
            .hrefLang(attributeValueByName(it, "hrefLang"))
            .hrefResolved(attributeValueByName(it, "hrefResolved"))
            .length(attributeValueByName(it, "length"))
            .rel(attributeValueByName(it, "rel"))
            .title(attributeValueByName(it, "title"))
            .type(attributeValueByName(it, "type"))
    }

    private fun toPersonBuilder(node: Node, personBuilder: PersonBuilder): PersonBuilder? = valid(node) {
        for (child in node.childNodes.asListOfNodes()) {
            when (child.localName) {
                "name" -> {
                    val name = toText(child)
                    if (name != null) personBuilder.name(name)
                }
                "email" -> personBuilder.email(toText(child))
                "uri" -> personBuilder.uri(toText(child))
            }
        }
        personBuilder
    }
}
