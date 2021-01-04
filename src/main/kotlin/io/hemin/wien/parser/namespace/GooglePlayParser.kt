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
            "author" -> builder.googlePlay.author(node.textOrNull())
            "owner" -> builder.googlePlay.owner(node.textOrNull())
            "category" -> {
                val category = node.attributes.getNamedItem("text").textContent ?: return
                builder.googlePlay.addCategory(category)
            }
            "description" -> builder.googlePlay.description(node.textOrNull())
            "explicit" -> builder.googlePlay.explicit(node.textAsBooleanOrNull())
            "block" -> builder.googlePlay.block(node.textAsBooleanOrNull())
            "image" -> builder.googlePlay.imageBuilder(toImageBuilder(node, builder.createImageBuilder()))
            else -> pass
        }
    }

    override fun parseItemNode(builder: EpisodeBuilder, node: Node) {
        when (node.localName) {
            "description" -> builder.googlePlay.description(node.textOrNull())
            "explicit" -> builder.googlePlay.explicit(node.textAsBooleanOrNull())
            "block" -> builder.googlePlay.block(node.textAsBooleanOrNull())
            "image" -> builder.googlePlay.imageBuilder(toImageBuilder(node, builder.createImageBuilder()))
            else -> pass
        }
    }

    private fun toImageBuilder(node: Node, imageBuilder: ImageBuilder): ImageBuilder? = node.ifMatchesNamespace() {
        val url: String? = node.attributeValueByName("href")
        if (url.isNullOrBlank()) {
            null
        } else {
            imageBuilder.url(url)
        }
    }
}
