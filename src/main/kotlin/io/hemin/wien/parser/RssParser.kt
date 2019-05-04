package io.hemin.wien.parser

import io.hemin.wien.WienParser.Companion.toEpisode
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Image
import io.hemin.wien.builder.*
import io.hemin.wien.util.NodeListWrapper
import io.hemin.wien.util.NodeListWrapper.Companion.asList
import org.w3c.dom.Node

/** Parser implementation for the RSS namespace. */
class RssParser : NamespaceParser() {

    /** Standard RSS elements do not have a namespace. This value is therefore null. */
    override val namespaceURI: String? = null

    override fun parseImpl(builder: PodcastBuilder, node: Node) {
        when (node.localName) {
            "title"          -> builder.title(toText(node))
            "link"           -> builder.link(toText(node))
            "description"    -> builder.description(toText(node))
            "pubDate"        -> builder.pubDate(toDate(node))
            "lastBuildDate"  -> builder.lastBuildDate(toDate(node))
            "language"       -> builder.language(toText(node))
            "generator"      -> builder.generator(toText(node))
            "copyright"      -> builder.copyright(toText(node))
            "docs"           -> builder.docs(toText(node))
            "managingEditor" -> builder.managingEditor(toText(node))
            "webMaster"      -> builder.webMaster(toText(node))
            "image"          -> builder.image(toImage(node))
            "item"           -> builder.addEpisode(toEpisode(node))
        }
    }

    override fun parseImpl(builder: EpisodeBuilder, node: Node) {
        when (node.localName) {
            "title"       -> builder.title(toText(node))
            "link"        -> builder.link(toText(node))
            "description" -> builder.description(toText(node))
            "author"      -> builder.author(toText(node))
            "category"    -> builder.addCategory(toText(node))
            "comments"    -> builder.comments(toText(node))
            "enclosure"   -> builder.enclosure(toEnclosure(node))
            "guid"        -> builder.guid(toGuid(node))
            "pubDate"     -> builder.pubDate(toDate(node))
            "source"      -> builder.source(toText(node))
        }
    }

    /**
     * Transforms an RSS `<enclosure>` element into an instance of its model class.
     *
     * @param node The DOM node representing the `<enclosure>` element.
     * @return The [Episode.Enclosure] instance with the `<enclosure>` elements data, or null if all data was empty.
     */
    fun toEnclosure(node: Node): Episode.Enclosure? =
        EpisodeEnclosureBuilder()
            .url(attributeValueByName(node, "url"))
            .length(attributeValueByName(node, "length")?.toLongOrNull())
            .type(attributeValueByName(node, "type"))
            .build()

    /**
     * Transforms an RSS `<guid>` element into an instance of its model class.
     *
     * @param node The DOM node representing the `<guid>` element.
     * @return The [Episode.Guid] instance with the `<guid>` elements data, or null if all data was empty.
     */
    fun toGuid(node: Node): Episode.Guid? =
        EpisodeGuidBuilder()
            .textContent(toText(node))
            .isPermalink(toBoolean(attributeValueByName(node, "isPermaLink")))
            .build()

    /**
     * Transforms an RSS `<image>` element into an instance of the [Image] model class.
     *
     * @param node The DOM node representing the `<image>` element.
     * @return The [Image] instance with the `<image>` elements data, or null if all data was empty.
     */
    fun toImage(node: Node): Image? {
        val builder = ImageBuilder()
        for (child in asList(node.childNodes)) {
            when(child.localName) {
                "url"         -> builder.url(toText(child))
                "title"       -> builder.title(toText(child))
                "link"        -> builder.link(toText(child))
                "width"       -> builder.width(toInt(child))
                "height"      -> builder.height(toInt(child))
                "description" -> builder.description(toText(child))
            }
        }
        return builder.build()
    }

}
