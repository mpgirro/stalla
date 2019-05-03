package io.hemin.wien.parser

import io.hemin.wien.builder.EpisodeBuilder
import io.hemin.wien.builder.ImageBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.builder.PodcastBuilder
import io.hemin.wien.model.Image
import io.hemin.wien.model.Person
import io.hemin.wien.util.NodeListWrapper
import org.w3c.dom.Node

class ItunesParser : NamespaceParser() {

    override val namespaceURI: String? = "http://www.itunes.com/dtds/podcast-1.0.dtd"

    override fun parseImpl(podcast: PodcastBuilder, node: Node) {
        when (node.localName) {
            "subtitle"   -> podcast.itunes.subtitle(toText(node))
            "summary"    -> podcast.itunes.summary(toText(node))
            "image"      -> podcast.itunes.image(toImage(node))
            "keywords"   -> podcast.itunes.keywords(toText(node))
            "author"     -> podcast.itunes.author(toText(node))
            "categories" -> podcast.itunes.addCategory(toText(node))
            "explicit"   -> podcast.itunes.explicit(toBoolean(node))
            "block"      -> podcast.itunes.block(toBoolean(node))
            "complete"   -> podcast.itunes.complete(toBoolean(node))
            "type"       -> podcast.itunes.type(toText(node))
            "owner"      -> podcast.itunes.owner(toPerson(node))
        }
    }

    override fun parseImpl(episode: EpisodeBuilder, node: Node) {
        when (node.localName) {
            "title"       -> episode.itunes.title(toText(node))
            "duration"    -> episode.itunes.duration(toText(node))
            "image"       -> episode.itunes.image(toImage(node))
            "explicit"    -> episode.itunes.explicit(toBoolean(node))
            "block"       -> episode.itunes.block(toBoolean(node))
            "season"      -> episode.itunes.season(toInt(node))
            "episode"     -> episode.itunes.episode(toInt(node))
            "episodeType" -> episode.itunes.episodeType(toText(node))
        }
    }

    /**
     * Transforms an <itunes:image>` element into an instance of the [Image] model class.
     *
     * @param node The DOM node representing the `<itunes:image>` element.
     * @return The [Image] instance with the `<itunes:image>` elements data, or null if all data was empty.
     */
    fun toImage(node: Node): Image? {
        val url: String? = attributeValueByName(node, "href")
        if (url.isNullOrBlank()) {
            return null
        }
        else {
            val builder = ImageBuilder()
            builder.url(url)
            return builder.build()
        }
    }

    fun toPerson(node: Node): Person? {
        val builder = PersonBuilder()
        for (child in NodeListWrapper.asList(node.childNodes)) {
            val value: String? = toText(child)
            when(child.localName) {
                "name"  -> builder.name(value)
                "email" -> builder.email(value)
                "uri"   -> builder.uri(value)
            }
        }
        return builder.build()
    }

}
