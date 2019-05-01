package io.hemin.wien.parser

import io.hemin.wien.WienParser.Companion.toEpisode
import io.hemin.wien.model.Episode
import io.hemin.wien.model.builder.EnclosureBuilder
import io.hemin.wien.model.builder.EpisodeBuilder
import io.hemin.wien.model.builder.PodcastBuilder
import org.w3c.dom.Node

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
            "guid"        -> episode.guid(toText(node))
            "pubDate"     -> episode.pubDate(toDate(node))
            "source"      -> episode.source(toText(node))
        }
    }

    /** Extracts the data from RSS `<enclosure>` element and
     * applies the values to the [EpisodeBuilder.enclosure] property. */
    fun toEnclosure(node: Node): Episode.Enclosure {

        fun value(name: String): String? = node.attributes.getNamedItem(name).textContent

        return EnclosureBuilder()
            .url(value("url"))
            .length(value("length")?.toLongOrNull())
            .type(value("type"))
            .build()
    }

}
