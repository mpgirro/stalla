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
            "author" -> builder.iTunes.author(node.textOrNull())
            "block" -> builder.iTunes.block(node.textAsBooleanOrNull())
            "category" -> {
                val category = node.attributes.getNamedItem("text").textContent ?: return
                builder.iTunes.addCategory(category)
            }
            "complete" -> builder.iTunes.complete(node.textAsBooleanOrNull())
            "explicit" -> {
                val explicit = node.textAsBooleanOrNull() ?: return
                builder.iTunes.explicit(explicit)
            }
            "image" -> {
                val image = toImageBuilder(node, builder.createImageBuilder()) ?: return
                builder.iTunes.imageBuilder(image)
            }
            "keywords" -> builder.iTunes.keywords(node.textOrNull())
            "owner" -> builder.iTunes.ownerBuilder(toPersonBuilder(node, builder.createPersonBuilder()))
            "subtitle" -> builder.iTunes.subtitle(node.textOrNull())
            "summary" -> builder.iTunes.summary(node.textOrNull())
            "type" -> builder.iTunes.type(node.textOrNull())
            "title" -> builder.iTunes.title(node.textOrNull())
            "new-feed-url" -> builder.iTunes.newFeedUrl(node.textOrNull())
            else -> pass
        }
    }

    override fun parseItemNode(builder: EpisodeBuilder, node: Node) {
        when (node.localName) {
            "block" -> builder.iTunes.block(node.textAsBooleanOrNull())
            "duration" -> builder.iTunes.duration(node.textOrNull())
            "episode" -> builder.iTunes.episode(node.toInt())
            "episodeType" -> builder.iTunes.episodeType(node.textOrNull())
            "explicit" -> builder.iTunes.explicit(node.textAsBooleanOrNull())
            "image" -> builder.iTunes.imageBuilder(toImageBuilder(node, builder.createImageBuilder()))
            "season" -> builder.iTunes.season(node.toInt())
            "title" -> builder.iTunes.title(node.textOrNull())
            "author" -> builder.iTunes.author(node.textOrNull())
            "subtitle" -> builder.iTunes.subtitle(node.textOrNull())
            "summary" -> builder.iTunes.summary(node.textOrNull())
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

    private fun toPersonBuilder(node: Node, personBuilder: PersonBuilder): PersonBuilder? = node.ifMatchesNamespace() {
        for (child in node.childNodes.asListOfNodes()) {
            val value: String? = child.textOrNull()
            when (child.localName) {
                "name" -> if (value != null) personBuilder.name(value)
                "email" -> personBuilder.email(value)
            }
        }
        personBuilder
    }
}
