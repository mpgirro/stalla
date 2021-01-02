package io.hemin.wien.parser

import io.hemin.wien.WienParser.Companion.toEpisode
import io.hemin.wien.builder.EpisodeBuilder
import io.hemin.wien.builder.EpisodeEnclosureBuilder
import io.hemin.wien.builder.EpisodeGuidBuilder
import io.hemin.wien.builder.ImageBuilder
import io.hemin.wien.builder.PodcastBuilder
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Image
import io.hemin.wien.util.NodeListWrapper.Companion.asListOfNodes
import org.w3c.dom.Node

/**
 * Parser implementation for the RSS namespace.
 *
 * Note that RSS 2.0 feeds do not have a namespace URI specified. The document specification is described here:
 *
 * `http://www.rssboard.org/rss-2-0`
 */
class RssParser : NamespaceParser() {

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
            "image" -> builder.image(toImage(node))
            "item" -> builder.addEpisode(toEpisode(node))
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
            else -> pass
        }
    }

    override fun parse(builder: EpisodeBuilder, node: Node) = valid(node) {
        when (node.localName) {
            "author" -> builder.author(toText(node))
            "category" -> builder.addCategory(toText(node))
            "comments" -> builder.comments(toText(node))
            "description" -> builder.description(toText(node))
            "enclosure" -> {
                val enclosure = toEnclosure(node) ?: return@valid
                builder.enclosure(enclosure)
            }
            "guid" -> builder.guid(toGuid(node))
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

    /**
     * Transforms an RSS `<enclosure>` element into an instance of its model class.
     *
     * @param node The DOM node representing the `<enclosure>` element.
     * @return The [Episode.Enclosure] instance with the `<enclosure>` elements data, or null if all data was empty.
     */
    private fun toEnclosure(node: Node): Episode.Enclosure? = valid(node) {
        val url = attributeValueByName(it, "url")
        val length = attributeValueByName(it, "length")?.toLongOrNull()
        val type = attributeValueByName(it, "type")

        if (url == null || length == null || type == null) return@valid null

        EpisodeEnclosureBuilder()
            .url(url)
            .length(length)
            .type(type)
            .build()
    }

    /**
     * Transforms an RSS `<guid>` element into an instance of its model class.
     *
     * @param node The DOM node representing the `<guid>` element.
     * @return The [Episode.Guid] instance with the `<guid>` elements data, or null if all data was empty.
     */
    private fun toGuid(node: Node): Episode.Guid? = valid(node) {
        EpisodeGuidBuilder()
            .textContent(toText(it))
            .isPermalink(toBoolean(attributeValueByName(it, "isPermaLink")))
            .build()
    }

    /**
     * Transforms an RSS `<image>` element into an instance of the [Image] model class.
     *
     * @param node The DOM node representing the `<image>` element.
     * @return The [Image] instance with the `<image>` elements data, or null if all data was empty.
     */
    private fun toImage(node: Node): Image? = valid(node) {
        val builder = ImageBuilder()
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
        builder.build()
    }
}
