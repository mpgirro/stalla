package io.hemin.wien.parser.namespace

import io.hemin.wien.builder.LinkBuilder
import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.builder.toPersonBuilder
import io.hemin.wien.parser.NamespaceParser
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.util.getAttributeValueByName
import org.w3c.dom.Node

/**
 * Parser implementation for the Atom namespace.
 *
 * The namespace URI is: `http://www.w3.org/2005/Atom`
 */
internal class AtomParser : NamespaceParser() {

    override val namespace = FeedNamespace.ATOM

    override fun parseChannelNode(builder: PodcastBuilder, node: Node) {
        when (node.localName) {
            "contributor" -> {
                val personBuilder = node.ifCanBeParsed { toPersonBuilder(builder.createPersonBuilder(), namespace) } ?: return
                builder.atom.addContributorBuilder(personBuilder)
            }
            "author" -> {
                val personBuilder = node.ifCanBeParsed { toPersonBuilder(builder.createPersonBuilder(), namespace) } ?: return
                builder.atom.addAuthorBuilder(personBuilder)
            }
            "link" -> {
                val linkBuilder = node.ifCanBeParsed { toLinkBuilder(builder.createLinkBuilder()) } ?: return
                builder.atom.addLinkBuilder(linkBuilder)
            }
            else -> pass
        }
    }

    override fun parseItemNode(builder: EpisodeBuilder, node: Node) {
        when (node.localName) {
            "contributor" -> {
                val personBuilder = node.ifCanBeParsed { toPersonBuilder(builder.createPersonBuilder(), namespace) } ?: return
                builder.atom.addContributorBuilder(personBuilder)
            }
            "author" -> {
                val personBuilder = node.ifCanBeParsed { toPersonBuilder(builder.createPersonBuilder(), namespace) } ?: return
                builder.atom.addAuthorBuilder(personBuilder)
            }
            "link" -> {
                val linkBuilder = node.ifCanBeParsed { toLinkBuilder(builder.createLinkBuilder()) } ?: return
                builder.atom.addLinkBuilder(linkBuilder)
            }
            else -> pass
        }
    }

    private fun Node.toLinkBuilder(linkBuilder: LinkBuilder): LinkBuilder? = ifCanBeParsed {
        val href = getAttributeValueByName("href") ?: return@ifCanBeParsed null

        linkBuilder
            .href(href)
            .hrefLang(getAttributeValueByName("hrefLang"))
            .hrefResolved(getAttributeValueByName("hrefResolved"))
            .length(getAttributeValueByName("length"))
            .rel(getAttributeValueByName("rel"))
            .title(getAttributeValueByName("title"))
            .type(getAttributeValueByName("type"))
    }
}
