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
            "copyright" -> builder.copyright(node.textOrNull())
            "description" -> {
                val description = node.textOrNull() ?: return
                builder.description(description)
            }
            "docs" -> builder.docs(node.textOrNull())
            "generator" -> builder.generator(node.textOrNull())
            "image" -> builder.imageBuilder(toImage(node, builder.createImageBuilder()))
            "language" -> {
                val language = node.textOrNull() ?: return
                builder.language(language)
            }
            "lastBuildDate" -> builder.lastBuildDate(node.toDate())
            "link" -> {
                val link = node.textOrNull() ?: return
                builder.link(link)
            }
            "managingEditor" -> builder.managingEditor(node.textOrNull())
            "pubDate" -> builder.pubDate(node.toDate())
            "title" -> {
                val title = node.textOrNull() ?: return
                builder.title(title)
            }
            "webMaster" -> builder.webMaster(node.textOrNull())
            "item" -> pass // Items are parsed by WienParser direcly
            else -> pass
        }
    }

    override fun parseItemNode(builder: EpisodeBuilder, node: Node) {
        when (node.localName) {
            "author" -> builder.author(node.textOrNull())
            "category" -> {
                val category = node.textOrNull() ?: return
                builder.addCategory(category)
            }
            "comments" -> builder.comments(node.textOrNull())
            "description" -> builder.description(node.textOrNull())
            "enclosure" -> {
                val enclosure = toEnclosureBuilder(node, builder.createEnclosureBuilder()) ?: return
                builder.enclosureBuilder(enclosure)
            }
            "guid" -> builder.guidBuilder(toGuidBuilder(node, builder.createGuidBuilder()))
            "link" -> builder.link(node.textOrNull())
            "pubDate" -> builder.pubDate(node.toDate())
            "source" -> builder.source(node.textOrNull())
            "title" -> {
                val title = node.textOrNull() ?: return
                builder.title(title)
            }
            else -> pass
        }
    }

    private fun toEnclosureBuilder(node: Node, enclosureBuilder: EpisodeEnclosureBuilder): EpisodeEnclosureBuilder? = node.ifMatchesNamespace() {
        val url = it.attributeValueByName("url")
        val length = it.attributeValueByName("length")?.toLongOrNull()
        val type = it.attributeValueByName("type")

        if (url == null || length == null || type == null) return@ifMatchesNamespace null

        enclosureBuilder.url(url)
            .length(length)
            .type(type)
    }

    private fun toGuidBuilder(node: Node, builder: EpisodeGuidBuilder): EpisodeGuidBuilder? = node.ifMatchesNamespace() {
        val guid = it.textOrNull() ?: return@ifMatchesNamespace null

        builder.textContent(guid)
            .isPermalink(it.attributeValueByName("isPermaLink").parseAsBooleanOrNull())
    }

    private fun toImage(node: Node, builder: ImageBuilder): ImageBuilder? = node.ifMatchesNamespace() {
        for (child in node.childNodes.asListOfNodes()) {
            when (child.localName) {
                "description" -> builder.description(child.textOrNull())
                "height" -> builder.height(child.toInt())
                "link" -> builder.link(child.textOrNull())
                "title" -> builder.title(child.textOrNull())
                "url" -> {
                    val url = child.textOrNull() ?: continue
                    builder.url(url)
                }
                "width" -> builder.width(child.toInt())
            }
        }
        builder
    }
}
