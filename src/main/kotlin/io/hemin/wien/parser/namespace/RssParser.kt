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
 * Note that RSS 2.0 feeds do not have a namespace URI specified. The document specification is described here:
 *
 * `http://www.rssboard.org/rss-2-0`
 */
internal class RssParser : NamespaceParser() {

    /** Standard RSS 2.0 elements do not have a namespace. This value is therefore null. */
    override val namespaceURI: String? = null

    override fun parse(builder: PodcastBuilder, node: Node) = valid(node) {
        when (node.localName) {
            "copyright" -> builder.copyright(toText(node))
            "description" -> {
                val description = toText(node) ?: return@valid
                builder.description(description)
            }
            "docs" -> builder.docs(toText(node))
            "generator" -> builder.generator(toText(node))
            "image" -> builder.imageBuilder(toImage(node, builder.createImageBuilder()))
            "language" -> {
                val language = toText(node) ?: return@valid
                builder.language(language)
            }
            "lastBuildDate" -> builder.lastBuildDate(toDate(node))
            "link" -> {
                val link = toText(node) ?: return@valid
                builder.link(link)
            }
            "managingEditor" -> builder.managingEditor(toText(node))
            "pubDate" -> builder.pubDate(toDate(node))
            "title" -> {
                val title = toText(node) ?: return@valid
                builder.title(title)
            }
            "webMaster" -> builder.webMaster(toText(node))
            "item" -> pass // Items are parsed by WienParser direcly
            else -> pass
        }
    }

    override fun parse(builder: EpisodeBuilder, node: Node) = valid(node) {
        when (node.localName) {
            "author" -> builder.author(toText(node))
            "category" -> {
                val category = toText(node) ?: return@valid
                builder.addCategory(category)
            }
            "comments" -> builder.comments(toText(node))
            "description" -> builder.description(toText(node))
            "enclosure" -> {
                val enclosure = toEnclosureBuilder(node, builder.createEnclosureBuilder()) ?: return@valid
                builder.enclosureBuilder(enclosure)
            }
            "guid" -> builder.guidBuilder(toGuidBuilder(node, builder.createGuidBuilder()))
            "link" -> builder.link(toText(node))
            "pubDate" -> builder.pubDate(toDate(node))
            "source" -> builder.source(toText(node))
            "title" -> {
                val title = toText(node) ?: return@valid
                builder.title(title)
            }
            else -> pass
        }
    }

    private fun toEnclosureBuilder(node: Node, enclosureBuilder: EpisodeEnclosureBuilder): EpisodeEnclosureBuilder? = valid(node) {
        val url = attributeValueByName(it, "url")
        val length = attributeValueByName(it, "length")?.toLongOrNull()
        val type = attributeValueByName(it, "type")

        if (url == null || length == null || type == null) return@valid null

        enclosureBuilder.url(url)
            .length(length)
            .type(type)
    }

    private fun toGuidBuilder(node: Node, builder: EpisodeGuidBuilder): EpisodeGuidBuilder? = valid(node) {
        val guid = toText(it) ?: return@valid null

        builder.textContent(guid)
            .isPermalink(toBoolean(attributeValueByName(it, "isPermaLink")))
    }

    private fun toImage(node: Node, builder: ImageBuilder): ImageBuilder? = valid(node) {
        for (child in node.childNodes.asListOfNodes()) {
            when (child.localName) {
                "description" -> builder.description(toText(child))
                "height" -> builder.height(toInt(child))
                "link" -> builder.link(toText(child))
                "title" -> builder.title(toText(child))
                "url" -> {
                    val url = toText(child) ?: continue
                    builder.url(url)
                }
                "width" -> builder.width(toInt(child))
            }
        }
        builder
    }
}
