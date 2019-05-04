package io.hemin.wien.parser

import io.hemin.wien.builder.EpisodeBuilder
import io.hemin.wien.builder.ImageBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.builder.PodcastBuilder
import io.hemin.wien.model.Image
import io.hemin.wien.model.Person
import io.hemin.wien.util.NodeListWrapper
import io.hemin.wien.util.NodeListWrapper.Companion.asList
import org.w3c.dom.Node

/**
 * Parser implementation for the iTunes namespace.
 *
 * The namespace URI is: `http://www.itunes.com/dtds/podcast-1.0.dtd`
 */
class ItunesParser : NamespaceParser() {

    /**
     * The URI of the namespace processed by this parser.
     *
     * URI: `http://www.itunes.com/dtds/podcast-1.0.dtd`
     */
    override val namespaceURI: String? = "http://www.itunes.com/dtds/podcast-1.0.dtd"

    override fun parseImpl(builder: PodcastBuilder, node: Node) {
        when (node.localName) {
            "subtitle"   -> builder.itunes.subtitle(toText(node))
            "summary"    -> builder.itunes.summary(toText(node))
            "image"      -> builder.itunes.image(toImage(node))
            "keywords"   -> builder.itunes.keywords(toText(node))
            "author"     -> builder.itunes.author(toText(node))
            "categories" -> builder.itunes.addCategory(toText(node))
            "explicit"   -> builder.itunes.explicit(toBoolean(node))
            "block"      -> builder.itunes.block(toBoolean(node))
            "complete"   -> builder.itunes.complete(toBoolean(node))
            "type"       -> builder.itunes.type(toText(node))
            "owner"      -> builder.itunes.owner(toPerson(node))
        }
    }

    override fun parseImpl(builder: EpisodeBuilder, node: Node) {
        when (node.localName) {
            "title"       -> builder.itunes.title(toText(node))
            "duration"    -> builder.itunes.duration(toText(node))
            "image"       -> builder.itunes.image(toImage(node))
            "explicit"    -> builder.itunes.explicit(toBoolean(node))
            "block"       -> builder.itunes.block(toBoolean(node))
            "season"      -> builder.itunes.season(toInt(node))
            "builder"     -> builder.itunes.episode(toInt(node))
            "episodeType" -> builder.itunes.episodeType(toText(node))
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
        return if (url.isNullOrBlank()) {
            null
        }
        else {
            ImageBuilder()
                .url(url)
                .build()
        }
    }

    /**
     * Transforms an <itunes:owner>` element into an instance of the [Image] model class.
     *
     * @param node The DOM node representing the `<itunes:owner>` element.
     * @return The [Image] instance with the `<itunes:owner>` elements data, or null if all data was empty.
     */
    fun toPerson(node: Node): Person? {
        val builder = PersonBuilder()
        for (child in asList(node.childNodes)) {
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
