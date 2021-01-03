package io.hemin.wien.parser.namespace

import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.builder.validating.ValidatingImageBuilder
import io.hemin.wien.model.Image
import io.hemin.wien.parser.NamespaceParser
import org.w3c.dom.Node

/**
 * Parser implementation for the Google Play namespace.
 *
 * The namespace URI is: `http://www.google.com/schemas/play-podcasts/1.0`
 */
internal class GooglePlayParser : NamespaceParser() {

    /**
     * The URI of the namespace processed by this parser.
     *
     * URI: `http://www.google.com/schemas/play-podcasts/1.0`
     */
    override val namespaceURI: String = "http://www.google.com/schemas/play-podcasts/1.0"

    override fun parse(builder: PodcastBuilder, node: Node) = valid(node) {
        when (node.localName) {
            "author" -> builder.googlePlay.author(toText(node))
            "owner" -> builder.googlePlay.owner(toText(node))
            "category" -> {
                val category = node.attributes.getNamedItem("text").textContent ?: return@valid
                builder.googlePlay.addCategory(category)
            }
            "description" -> builder.googlePlay.description(toText(node))
            "explicit" -> builder.googlePlay.explicit(toBoolean(node))
            "block" -> builder.googlePlay.block(toBoolean(node))
            "image" -> builder.googlePlay.image(toImage(node))
            else -> pass
        }
    }

    override fun parse(builder: EpisodeBuilder, node: Node) = valid(node) {
        when (node.localName) {
            "description" -> builder.googlePlay.description(toText(node))
            "explicit" -> builder.googlePlay.explicit(toBoolean(node))
            "block" -> builder.googlePlay.block(toBoolean(node))
            "image" -> builder.googlePlay.image(toImage(node))
            else -> pass
        }
    }

    /**
     * Transforms an <googleplay:image>` element into an instance of the [Image] model class.
     *
     * @param node The DOM node representing the `<googleplay:image>` element.
     * @return The [Image] instance with the `<googleplay:image>` elements data, or null if all data was empty.
     */
    private fun toImage(node: Node): Image? = valid(node) {
        val url: String? = attributeValueByName(node, "href")
        if (url.isNullOrBlank()) {
            null
        } else {
            ValidatingImageBuilder()
                .url(url)
                .build()
        }
    }
}
