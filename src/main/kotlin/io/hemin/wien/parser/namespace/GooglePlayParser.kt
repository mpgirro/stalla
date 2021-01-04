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

    override val namespaceURI: String = "http://www.google.com/schemas/play-podcasts/1.0"

    override fun parseChannelNode(builder: PodcastBuilder, node: Node) {
        when (node.localName) {
            "author" -> builder.googlePlay.author(textOrNull(node))
            "owner" -> builder.googlePlay.owner(textOrNull(node))
            "category" -> {
                val category = node.attributes.getNamedItem("text").textContent ?: return
                builder.googlePlay.addCategory(category)
            }
            "description" -> builder.googlePlay.description(textOrNull(node))
            "explicit" -> builder.googlePlay.explicit(textAsBooleanOrNull(node))
            "block" -> builder.googlePlay.block(textAsBooleanOrNull(node))
            "image" -> builder.googlePlay.imageBuilder(toImageBuilder(node, builder.createImageBuilder()))
            else -> pass
        }
    }

    override fun parseItemNode(builder: EpisodeBuilder, node: Node) {
        when (node.localName) {
            "description" -> builder.googlePlay.description(textOrNull(node))
            "explicit" -> builder.googlePlay.explicit(textAsBooleanOrNull(node))
            "block" -> builder.googlePlay.block(textAsBooleanOrNull(node))
            "image" -> builder.googlePlay.imageBuilder(toImageBuilder(node, builder.createImageBuilder()))
            else -> pass
        }
    }

    private fun toImageBuilder(node: Node, imageBuilder: ImageBuilder): ImageBuilder? = ifMatchesNamespace(node) {
        val url: String? = attributeValueByName(node, "href")
        if (url.isNullOrBlank()) {
            null
        } else {
            imageBuilder.url(url)
        }
    }
}
