package io.hemin.wien.parser.namespace

import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.parseAsInt
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.builder.textAsBooleanOrNull
import io.hemin.wien.builder.textOrNull
import io.hemin.wien.builder.toHrefOnlyImageBuilder
import io.hemin.wien.builder.toPersonBuilder
import io.hemin.wien.parser.NamespaceParser
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.util.getAttributeValueByName
import org.w3c.dom.Node

/**
 * Parser implementation for the iTunes namespace.
 *
 * The namespace URI is: `http://www.itunes.com/dtds/podcast-1.0.dtd`
 */
internal class ITunesParser : NamespaceParser() {

    override val namespace = FeedNamespace.ITUNES

    override fun parseChannelNode(builder: PodcastBuilder, node: Node) {
        when (node.localName) {
            "author" -> builder.iTunes.author(node.ifCanBeParsed { textOrNull() })
            "block" -> builder.iTunes.block(node.ifCanBeParsed { textAsBooleanOrNull() })
            "category" -> {
                val category = node.ifCanBeParsed { getAttributeValueByName("text") }?: return
                builder.iTunes.addCategory(category)
            }
            "complete" -> builder.iTunes.complete(node.ifCanBeParsed { textAsBooleanOrNull() })
            "explicit" -> {
                val explicit = node.ifCanBeParsed { textAsBooleanOrNull() } ?: return
                builder.iTunes.explicit(explicit)
            }
            "image" -> {
                val image = node.ifCanBeParsed { toHrefOnlyImageBuilder(builder.createHrefOnlyImageBuilder()) }?: return
                builder.iTunes.imageBuilder(image)
            }
            "keywords" -> builder.iTunes.keywords(node.ifCanBeParsed { textOrNull() })
            "owner" -> {
                val ownerBuilder = node.ifCanBeParsed { toOwnerBuilder(builder.createPersonBuilder()) }
                builder.iTunes.ownerBuilder(ownerBuilder)
            }
            "subtitle" -> builder.iTunes.subtitle(node.ifCanBeParsed { textOrNull() })
            "summary" -> builder.iTunes.summary(node.ifCanBeParsed { textOrNull() })
            "type" -> builder.iTunes.type(node.ifCanBeParsed { textOrNull() })
            "title" -> builder.iTunes.title(node.ifCanBeParsed { textOrNull() })
            "new-feed-url" -> builder.iTunes.newFeedUrl(node.ifCanBeParsed { textOrNull() })
            else -> pass
        }
    }

    private fun Node.toOwnerBuilder(personBuilder: PersonBuilder): PersonBuilder {
        toPersonBuilder(personBuilder, namespace)
        personBuilder.uri(null) // <itunes:owner> tags don't support <uri> tags
        return personBuilder
    }

    override fun parseItemNode(builder: EpisodeBuilder, node: Node) {
        when (node.localName) {
            "block" -> builder.iTunes.block(node.ifCanBeParsed { textAsBooleanOrNull() })
            "duration" -> builder.iTunes.duration(node.ifCanBeParsed { textOrNull() })
            "episode" -> builder.iTunes.episode(node.ifCanBeParsed { parseAsInt() })
            "episodeType" -> builder.iTunes.episodeType(node.ifCanBeParsed { textOrNull() })
            "explicit" -> builder.iTunes.explicit(node.ifCanBeParsed { textAsBooleanOrNull() })
            "image" -> {
                val imageBuilder = node.toHrefOnlyImageBuilder(builder.createHrefOnlyImageBuilder())
                builder.iTunes.imageBuilder(imageBuilder)
            }
            "season" -> builder.iTunes.season(node.ifCanBeParsed { parseAsInt() })
            "title" -> builder.iTunes.title(node.ifCanBeParsed { textOrNull() })
            "author" -> builder.iTunes.author(node.ifCanBeParsed { textOrNull() })
            "subtitle" -> builder.iTunes.subtitle(node.ifCanBeParsed { textOrNull() })
            "summary" -> builder.iTunes.summary(node.ifCanBeParsed { textOrNull() })
            else -> pass
        }
    }
}
