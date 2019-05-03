package io.hemin.wien.parser

import io.hemin.wien.WienParser.Companion.toEpisode
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Image
import io.hemin.wien.builder.*
import io.hemin.wien.util.NodeListWrapper
import org.w3c.dom.Node

/** Parser implementation for the RSS namespace. */
class RssParser : NamespaceParser {

    /** Standard RSS elements do not have a namespace. This value is therefore null. */
    override val namespaceURI: String? = null

    override fun parse(podcast: PodcastBuilder, node: Node) {
        when (node.localName) {
            "title"          -> podcast.title(toText(node))
            "link"           -> podcast.link(toText(node))
            "description"    -> podcast.description(toText(node))
            "pubDate"        -> podcast.pubDate(toDate(node))
            "lastBuildDate"  -> podcast.lastBuildDate(toDate(node))
            "language"       -> podcast.language(toText(node))
            "generator"      -> podcast.generator(toText(node))
            "copyright"      -> podcast.copyright(toText(node))
            "docs"           -> podcast.docs(toText(node))
            "managingEditor" -> podcast.managingEditor(toText(node))
            "webMaster"      -> podcast.webMaster(toText(node))
            "image"          -> podcast.image(toImage(node))
            "item"           -> podcast.addEpisode(toEpisode(node))
        }
    }

    override fun parse(episode: EpisodeBuilder, node: Node) {
        when (node.localName) {
            "title"       -> episode.title(toText(node))
            "link"        -> episode.link(toText(node))
            "description" -> episode.description(toText(node))
            "author"      -> episode.author(toText(node))
            "category"    -> episode.addCategory(toText(node))
            "comments"    -> episode.comments(toText(node))
            "enclosure"   -> episode.enclosure(toEnclosure(node))
            "guid"        -> episode.guid(toGuid(node))
            "pubDate"     -> episode.pubDate(toDate(node))
            "source"      -> episode.source(toText(node))
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
     * Transforms an RSS `<image>` element into an instance of its model class.
     *
     * @param node The DOM node representing the `<enclosure>` element.
     * @return The [Image] instance with the `<image>` elements data, or null if all data was empty.
     */
    fun toImage(node: Node): Image? {
        val builder = ImageBuilder()
        for (child in NodeListWrapper.asList(node.childNodes)) {
            val value: String? = toText(child)
            when(child.localName) {
                "url"         -> builder.url(value)
                "title"       -> builder.title(value)
                "link"        -> builder.link(value)
                "width"       -> builder.width(value?.toInt())
                "height"      -> builder.height(value?.toInt())
                "description" -> builder.description(value)
            }
        }
        return builder.build()
    }

}
