package io.hemin.wien.parser

import io.hemin.wien.builder.EpisodeBuilder
import io.hemin.wien.builder.LinkBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.builder.PodcastBuilder
import io.hemin.wien.model.Link
import io.hemin.wien.model.Person
import io.hemin.wien.util.NodeListWrapper.Companion.asList
import org.w3c.dom.Node

class AtomParser : NamespaceParser() {

    override val namespaceURI: String? = "http://www.w3.org/2005/Atom"

    override fun parseChannel(builder: PodcastBuilder, node: Node) {
        when (node.localName) {
            "contributor" -> builder.atom.addContributor(toPerson(node))
            "author"      -> builder.atom.addAuthor(toPerson(node))
            "link"        -> builder.atom.addLink(toLink(node))
        }
    }

    override fun parseItem(builder: EpisodeBuilder, node: Node) {
        when (node.localName) {
            "contributor" -> builder.atom.addContributor(toPerson(node))
            "author"      -> builder.atom.addAuthor(toPerson(node))
            "link"        -> builder.atom.addLink(toLink(node))
        }
    }

    fun toLink(node: Node): Link? =
        LinkBuilder()
            .href(attributeValueByName(node, "href"))
            .hrefLang(attributeValueByName(node, "hrefLang"))
            .hrefResolved(attributeValueByName(node, "hrefResolved"))
            .length(attributeValueByName(node, "length"))
            .rel(attributeValueByName(node, "rel"))
            .title(attributeValueByName(node, "title"))
            .type(attributeValueByName(node, "type"))
            .build()

    fun toPerson(node: Node): Person? {
        val builder = PersonBuilder()
        for (child in asList(node.childNodes)) {
            when(child.localName) {
                "name"  -> builder.name(toText(child))
                "email" -> builder.email(toText(child))
                "uri"   -> builder.uri(toText(child))
            }
        }
        return builder.build()
    }

}
