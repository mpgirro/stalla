package dev.stalla.parser.namespace

import dev.stalla.builder.PersonBuilder
import dev.stalla.builder.episode.EpisodeBuilder
import dev.stalla.builder.podcast.PodcastBuilder
import dev.stalla.dom.parseAsInt
import dev.stalla.dom.textAsBooleanOrNull
import dev.stalla.dom.textOrNull
import dev.stalla.dom.toHrefOnlyImageBuilder
import dev.stalla.dom.toItunesCategory
import dev.stalla.dom.toPersonBuilder
import dev.stalla.parser.NamespaceParser
import dev.stalla.util.FeedNamespace
import dev.stalla.util.InternalApi
import org.w3c.dom.Node

/**
 * Parser implementation for the iTunes namespace.
 *
 * The namespace URI is: `http://www.itunes.com/dtds/podcast-1.0.dtd`
 */
@InternalApi
internal object ItunesParser : NamespaceParser() {

    override val namespace = FeedNamespace.ITUNES

    override fun Node.parseChannelData(builder: PodcastBuilder) {
        when (localName) {
            "author" -> builder.itunesBuilder.author(ifCanBeParsed { textOrNull() })
            "block" -> {
                val block = ifCanBeParsed { textAsBooleanOrNull() } ?: return
                builder.itunesBuilder.block(block)
            }
            "category" -> {
                val category = ifCanBeParsed { toItunesCategory(namespace) } ?: return
                builder.itunesBuilder.addCategory(category)
            }
            "complete" -> {
                val complete = ifCanBeParsed { textAsBooleanOrNull() } ?: return
                builder.itunesBuilder.complete(complete)
            }
            "explicit" -> {
                val explicit = ifCanBeParsed { textAsBooleanOrNull() } ?: return
                builder.itunesBuilder.explicit(explicit)
            }
            "image" -> {
                val image = ifCanBeParsed { toHrefOnlyImageBuilder(builder.createHrefOnlyImageBuilder()) } ?: return
                builder.itunesBuilder.imageBuilder(image)
            }
            "keywords" -> builder.itunesBuilder.keywords(ifCanBeParsed { textOrNull() })
            "owner" -> {
                val ownerBuilder = ifCanBeParsed { toOwnerBuilder(builder.createPersonBuilder()) }
                builder.itunesBuilder.ownerBuilder(ownerBuilder)
            }
            "subtitle" -> builder.itunesBuilder.subtitle(ifCanBeParsed { textOrNull() })
            "summary" -> builder.itunesBuilder.summary(ifCanBeParsed { textOrNull() })
            "type" -> builder.itunesBuilder.type(ifCanBeParsed { textOrNull() })
            "title" -> builder.itunesBuilder.title(ifCanBeParsed { textOrNull() })
            "new-feed-url" -> builder.itunesBuilder.newFeedUrl(ifCanBeParsed { textOrNull() })
            else -> pass
        }
    }

    private fun Node.toOwnerBuilder(personBuilder: PersonBuilder): PersonBuilder {
        toPersonBuilder(personBuilder, namespace)
        personBuilder.uri(null) // <itunes:owner> tags don't support <uri> tags
        return personBuilder
    }

    override fun Node.parseItemData(builder: EpisodeBuilder) {
        when (localName) {
            "block" -> {
                val block = ifCanBeParsed { textAsBooleanOrNull() } ?: return
                builder.itunesBuilder.block(block)
            }
            "duration" -> builder.itunesBuilder.duration(ifCanBeParsed { textOrNull() })
            "episode" -> builder.itunesBuilder.episode(ifCanBeParsed { parseAsInt() })
            "episodeType" -> builder.itunesBuilder.episodeType(ifCanBeParsed { textOrNull() })
            "explicit" -> {
                val explicit = ifCanBeParsed { textAsBooleanOrNull() } ?: return
                builder.itunesBuilder.explicit(explicit)
            }
            "image" -> {
                val imageBuilder = toHrefOnlyImageBuilder(builder.createHrefOnlyImageBuilder())
                builder.itunesBuilder.imageBuilder(imageBuilder)
            }
            "season" -> builder.itunesBuilder.season(ifCanBeParsed { parseAsInt() })
            "title" -> builder.itunesBuilder.title(ifCanBeParsed { textOrNull() })
            "author" -> builder.itunesBuilder.author(ifCanBeParsed { textOrNull() })
            "subtitle" -> builder.itunesBuilder.subtitle(ifCanBeParsed { textOrNull() })
            "summary" -> builder.itunesBuilder.summary(ifCanBeParsed { textOrNull() })
            else -> pass
        }
    }
}
