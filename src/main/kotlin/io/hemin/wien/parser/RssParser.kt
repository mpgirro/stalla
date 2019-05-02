package io.hemin.wien.parser

import io.hemin.wien.WienParser.Companion.toEpisode
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Image
import io.hemin.wien.model.builder.EpisodeBuilder
import io.hemin.wien.model.builder.ImageBuilder
import io.hemin.wien.model.builder.PodcastBuilder
import io.hemin.wien.util.NodeListWrapper
import org.w3c.dom.Node

/** Parser implementation for the RSS namespace. */
class RssParser : NamespaceParser {

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
            "guid"        -> episode.guid(toGuid(node))             // TODO <guid> can have a isPermanent attribute -> parse also!
            "pubDate"     -> episode.pubDate(toDate(node))
            "source"      -> episode.source(toText(node))
        }
    }

    /**
     * Extracts the data from an RSS `<enclosure>` element. All expected
     * attributes are contained within an [Episode.Enclosure] instance.
     *
     * @param node The DOM node representing the `<enclosure>` element.
     * @return The [Episode.Enclosure] instance with the `<enclosure>` elements data.
     */
    fun toEnclosure(node: Node): Episode.Enclosure = Episode.Enclosure(
        url    = attrValueByName(node, "url"),
        length = attrValueByName(node, "length")?.toLongOrNull(),
        type   = attrValueByName(node, "type")
    )

    fun toGuid(node: Node): Episode.Guid = Episode.Guid(
        value       = node.textContent,
        isPermalink = toBoolean(attrValueByName(node, "isPermaLink"))
    )

    fun toImage(node: Node): Image {
        val builder = ImageBuilder()
        for (child in NodeListWrapper.asList(node.childNodes)) {
            val value: String? = child.textContent
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
