package io.hemin.wien.parser.namespace

import io.hemin.wien.builder.ImageBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.parser.NamespaceParser
import io.hemin.wien.util.NodeListWrapper.Companion.asListOfNodes
import org.w3c.dom.Node

/**
 * Parser implementation for the iTunes namespace.
 *
 * The namespace URI is: `http://www.itunes.com/dtds/podcast-1.0.dtd`
 */
internal class ITunesParser : NamespaceParser() {

    override val namespaceURI: String = "http://www.itunes.com/dtds/podcast-1.0.dtd"

    override fun parseChannelNode(builder: PodcastBuilder, node: Node) {
        when (node.localName) {
            "author" -> builder.iTunes.author(textOrNull(node))
            "block" -> builder.iTunes.block(textAsBooleanOrNull(node))
            "category" -> {
                val category = node.attributes.getNamedItem("text").textContent ?: return
                builder.iTunes.addCategory(category)
            }
            "complete" -> builder.iTunes.complete(textAsBooleanOrNull(node))
            "explicit" -> {
                val explicit = textAsBooleanOrNull(node) ?: return
                builder.iTunes.explicit(explicit)
            }
            "image" -> {
                val image = toImageBuilder(node, builder.createImageBuilder()) ?: return
                builder.iTunes.imageBuilder(image)
            }
            "keywords" -> builder.iTunes.keywords(textOrNull(node))
            "owner" -> builder.iTunes.ownerBuilder(toPersonBuilder(node, builder.createPersonBuilder()))
            "subtitle" -> builder.iTunes.subtitle(textOrNull(node))
            "summary" -> builder.iTunes.summary(textOrNull(node))
            "type" -> builder.iTunes.type(textOrNull(node))
            "title" -> builder.iTunes.title(textOrNull(node))
            "new-feed-url" -> builder.iTunes.newFeedUrl(textOrNull(node))
            else -> pass
        }
    }

    override fun parseItemNode(builder: EpisodeBuilder, node: Node) {
        when (node.localName) {
            "block" -> builder.iTunes.block(textAsBooleanOrNull(node))
            "duration" -> builder.iTunes.duration(textOrNull(node))
            "episode" -> builder.iTunes.episode(toInt(node))
            "episodeType" -> builder.iTunes.episodeType(textOrNull(node))
            "explicit" -> builder.iTunes.explicit(textAsBooleanOrNull(node))
            "image" -> builder.iTunes.imageBuilder(toImageBuilder(node, builder.createImageBuilder()))
            "season" -> builder.iTunes.season(toInt(node))
            "title" -> builder.iTunes.title(textOrNull(node))
            "author" -> builder.iTunes.author(textOrNull(node))
            "subtitle" -> builder.iTunes.subtitle(textOrNull(node))
            "summary" -> builder.iTunes.summary(textOrNull(node))
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

    private fun toPersonBuilder(node: Node, personBuilder: PersonBuilder): PersonBuilder? = ifMatchesNamespace(node) {
        for (child in node.childNodes.asListOfNodes()) {
            val value: String? = textOrNull(child)
            when (child.localName) {
                "name" -> if (value != null) personBuilder.name(value)
                "email" -> personBuilder.email(value)
            }
        }
        personBuilder
    }
}
