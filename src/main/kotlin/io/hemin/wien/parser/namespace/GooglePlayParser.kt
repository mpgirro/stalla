package io.hemin.wien.parser.namespace

import io.hemin.wien.builder.ImageBuilder
import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
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
            "image" -> builder.googlePlay.imageBuilder(toImageBuilder(node, builder.createImageBuilder()))
            else -> pass
        }
    }

    override fun parse(builder: EpisodeBuilder, node: Node) = valid(node) {
        when (node.localName) {
            "description" -> builder.googlePlay.description(toText(node))
            "explicit" -> builder.googlePlay.explicit(toBoolean(node))
            "block" -> builder.googlePlay.block(toBoolean(node))
            "image" -> builder.googlePlay.imageBuilder(toImageBuilder(node, builder.createImageBuilder()))
            else -> pass
        }
    }

    private fun toImageBuilder(node: Node, imageBuilder: ImageBuilder): ImageBuilder? = valid(node) {
        val url: String? = attributeValueByName(node, "href")
        if (url.isNullOrBlank()) {
            null
        } else {
            imageBuilder.url(url)
        }
    }
}
