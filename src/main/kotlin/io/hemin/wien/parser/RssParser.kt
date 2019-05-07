package io.hemin.wien.parser

import io.hemin.wien.WienParser.Companion.toEpisode
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Image
import io.hemin.wien.builder.*
import io.hemin.wien.util.NodeListWrapper
import io.hemin.wien.util.NodeListWrapper.Companion.asList
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

    override fun parseChannel(builder: PodcastBuilder, node: Node) {
        when (node.localName) {
            "copyright"      -> builder.copyright(toText(node))
            "description"    -> builder.description(toText(node))
            "docs"           -> builder.docs(toText(node))
            "generator"      -> builder.generator(toText(node))
            "image"          -> builder.image(toImage(node))
            "item"           -> builder.addEpisode(toEpisode(node))
            "language"       -> builder.language(toText(node))
            "lastBuildDate"  -> builder.lastBuildDate(toDate(node))
            "link"           -> builder.link(toText(node))
            "managingEditor" -> builder.managingEditor(toText(node))
            "pubDate"        -> builder.pubDate(toDate(node))
            "title"          -> builder.title(toText(node))
            "webMaster"      -> builder.webMaster(toText(node))

        }
    }

    override fun parseItem(builder: EpisodeBuilder, node: Node) {
        when (node.localName) {
            "author"      -> builder.author(toText(node))
            "category"    -> builder.addCategory(toText(node))
            "comments"    -> builder.comments(toText(node))
            "description" -> builder.description(toText(node))
            "enclosure"   -> builder.enclosure(toEnclosure(node))
            "guid"        -> builder.guid(toGuid(node))
            "link"        -> builder.link(toText(node))
            "pubDate"     -> builder.pubDate(toDate(node))
            "source"      -> builder.source(toText(node))
            "title"       -> builder.title(toText(node))
        }
    }

    /**
     * Transforms an RSS `<enclosure>` element into an instance of its model class.
     *
     * @param node The DOM node representing the `<enclosure>` element.
     * @return The [Episode.Enclosure] instance with the `<enclosure>` elements data, or null if all data was empty.
     */
    fun toEnclosure(node: Node): Episode.Enclosure? = valid(node) {
        EpisodeEnclosureBuilder()
            .url(attributeValueByName(it, "url"))
            .length(attributeValueByName(it, "length")?.toLongOrNull())
            .type(attributeValueByName(it, "type"))
            .build()
    }


    /**
     * Transforms an RSS `<guid>` element into an instance of its model class.
     *
     * @param node The DOM node representing the `<guid>` element.
     * @return The [Episode.Guid] instance with the `<guid>` elements data, or null if all data was empty.
     */
    fun toGuid(node: Node): Episode.Guid? = valid(node) {
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
    fun toImage(node: Node): Image? = valid(node) {
        val builder = ImageBuilder()
        for (child in asList(node.childNodes)) {
            when(child.localName) {
                "description" -> builder.description(toText(child))
                "height"      -> builder.height(toInt(child))
                "link"        -> builder.link(toText(child))
                "title"       -> builder.title(toText(child))
                "url"         -> builder.url(toText(child))
                "width"       -> builder.width(toInt(child))
            }
        }
        builder.build()
    }

}
