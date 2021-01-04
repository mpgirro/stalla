package io.hemin.wien.parser.namespace

import io.hemin.wien.builder.ImageBuilder
import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.episode.EpisodeEnclosureBuilder
import io.hemin.wien.builder.episode.EpisodeGuidBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.parser.NamespaceParser
import io.hemin.wien.util.NodeListWrapper.Companion.asListOfNodes
import org.w3c.dom.Node

/**
 * Parser implementation for the RSS namespace.
 *
 * Note that RSS 2.0 feeds do not have a namespace URI, and thus [namespaceURI] is null.
 *
 * The RSS specification is described []here][http://www.rssboard.org/rss-2-0].
 */
internal class RssParser : NamespaceParser() {

    override val namespaceURI: String? = null

    override fun parseChannelNode(builder: PodcastBuilder, node: Node) {
        when (node.localName) {
            "copyright" -> builder.copyright(textOrNull(node))
            "description" -> {
                val description = textOrNull(node) ?: return
                builder.description(description)
            }
            "docs" -> builder.docs(textOrNull(node))
            "generator" -> builder.generator(textOrNull(node))
            "image" -> builder.imageBuilder(toImage(node, builder.createImageBuilder()))
            "language" -> {
                val language = textOrNull(node) ?: return
                builder.language(language)
            }
            "lastBuildDate" -> builder.lastBuildDate(toDate(node))
            "link" -> {
                val link = textOrNull(node) ?: return
                builder.link(link)
            }
            "managingEditor" -> builder.managingEditor(textOrNull(node))
            "pubDate" -> builder.pubDate(toDate(node))
            "title" -> {
                val title = textOrNull(node) ?: return
                builder.title(title)
            }
            "webMaster" -> builder.webMaster(textOrNull(node))
            "item" -> pass // Items are parsed by WienParser direcly
            else -> pass
        }
    }

    override fun parseItemNode(builder: EpisodeBuilder, node: Node) {
        when (node.localName) {
            "author" -> builder.author(textOrNull(node))
            "category" -> {
                val category = textOrNull(node) ?: return
                builder.addCategory(category)
            }
            "comments" -> builder.comments(textOrNull(node))
            "description" -> builder.description(textOrNull(node))
            "enclosure" -> {
                val enclosure = toEnclosureBuilder(node, builder.createEnclosureBuilder()) ?: return
                builder.enclosureBuilder(enclosure)
            }
            "guid" -> builder.guidBuilder(toGuidBuilder(node, builder.createGuidBuilder()))
            "link" -> builder.link(textOrNull(node))
            "pubDate" -> builder.pubDate(toDate(node))
            "source" -> builder.source(textOrNull(node))
            "title" -> {
                val title = textOrNull(node) ?: return
                builder.title(title)
            }
            else -> pass
        }
    }

    private fun toEnclosureBuilder(node: Node, enclosureBuilder: EpisodeEnclosureBuilder): EpisodeEnclosureBuilder? = ifMatchesNamespace(node) {
        val url = attributeValueByName(it, "url")
        val length = attributeValueByName(it, "length")?.toLongOrNull()
        val type = attributeValueByName(it, "type")

        if (url == null || length == null || type == null) return@ifMatchesNamespace null

        enclosureBuilder.url(url)
            .length(length)
            .type(type)
    }

    private fun toGuidBuilder(node: Node, builder: EpisodeGuidBuilder): EpisodeGuidBuilder? = ifMatchesNamespace(node) {
        val guid = textOrNull(it) ?: return@ifMatchesNamespace null

        builder.textContent(guid)
            .isPermalink(attributeValueByName(it, "isPermaLink").parseAsBooleanOrNull())
    }

    private fun toImage(node: Node, builder: ImageBuilder): ImageBuilder? = ifMatchesNamespace(node) {
        for (child in node.childNodes.asListOfNodes()) {
            when (child.localName) {
                "description" -> builder.description(textOrNull(child))
                "height" -> builder.height(toInt(child))
                "link" -> builder.link(textOrNull(child))
                "title" -> builder.title(textOrNull(child))
                "url" -> {
                    val url = textOrNull(child) ?: continue
                    builder.url(url)
                }
                "width" -> builder.width(toInt(child))
            }
        }
        builder
    }
}
