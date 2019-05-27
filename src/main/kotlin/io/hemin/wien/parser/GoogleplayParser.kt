package io.hemin.wien.parser

import io.hemin.wien.builder.EpisodeBuilder
import io.hemin.wien.builder.ImageBuilder
import io.hemin.wien.builder.PodcastBuilder
import io.hemin.wien.model.Image
import org.w3c.dom.Node

/**
 * Parser implementation for the Google Play namespace.
 *
 * The namespace URI is: `http://www.google.com/schemas/play-podcasts/1.0`
 */
class GoogleplayParser : NamespaceParser() {

    /**
     * The URI of the namespace processed by this parser.
     *
     * URI: `http://www.google.com/schemas/play-podcasts/1.0`
     */
    override val namespaceURI: String? = "http://www.google.com/schemas/play-podcasts/1.0"

    override fun parseChannel(builder: PodcastBuilder, node: Node) {
        when (node.localName) {
            "author"      -> builder.googleplay.author(toText(node))
            "category"    -> builder.googleplay.addCategory(toText(node))
            "description" -> builder.googleplay.description(toText(node))
            "explicit"    -> builder.googleplay.explicit(toBoolean(node))
            "image"       -> builder.googleplay.image(toImage(node))
        }
    }

    override fun parseItem(builder: EpisodeBuilder, node: Node) {
        when (node.localName) {
            "description" -> builder.googleplay.description(toText(node))
            "duration"    -> builder.googleplay.duration(toText(node))
            "explicit"    -> builder.googleplay.explicit(toBoolean(node))
        }
    }

    /**
     * Transforms an <googleplay:image>` element into an instance of the [Image] model class.
     *
     * @param node The DOM node representing the `<googleplay:image>` element.
     * @return The [Image] instance with the `<googleplay:image>` elements data, or null if all data was empty.
     */
    fun toImage(node: Node): Image? = valid(node) {
        val url: String? = attributeValueByName(node, "href")
        if (url.isNullOrBlank()) {
            null
        }
        else {
            ImageBuilder()
                .url(url)
                .build()
        }
    }

}
