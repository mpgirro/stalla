package io.hemin.wien.parser.namespace

import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.dom.parseAsInt
import io.hemin.wien.dom.textAsBooleanOrNull
import io.hemin.wien.dom.textOrNull
import io.hemin.wien.dom.toHrefOnlyImageBuilder
import io.hemin.wien.dom.toITunesCategoryBuilder
import io.hemin.wien.dom.toPersonBuilder
import io.hemin.wien.parser.NamespaceParser
import io.hemin.wien.util.FeedNamespace
import org.w3c.dom.Node

/**
 * Parser implementation for the iTunes namespace.
 *
 * The namespace URI is: `http://www.itunes.com/dtds/podcast-1.0.dtd`
 */
internal class ITunesParser : NamespaceParser() {

    override val namespace = FeedNamespace.ITUNES

    override fun Node.parseChannelData(builder: PodcastBuilder) {
        when (localName) {
            "author" -> builder.iTunesBuilder.author(ifCanBeParsed { textOrNull() })
            "block" -> {
                val block = ifCanBeParsed { textAsBooleanOrNull() } ?: return
                builder.iTunesBuilder.block(block)
            }
            "category" -> {
                val categoryBuilder = ifCanBeParsed {
                    toITunesCategoryBuilder(builder.createITunesStyleCategoryBuilder(), namespace)
                } ?: return
                builder.iTunesBuilder.addCategoryBuilder(categoryBuilder)
            }
            "complete" -> {
                val complete = ifCanBeParsed { textAsBooleanOrNull() } ?: return
                builder.iTunesBuilder.complete(complete)
            }
            "explicit" -> {
                val explicit = ifCanBeParsed { textAsBooleanOrNull() } ?: return
                builder.iTunesBuilder.explicit(explicit)
            }
            "image" -> {
                val image = ifCanBeParsed { toHrefOnlyImageBuilder(builder.createHrefOnlyImageBuilder()) } ?: return
                builder.iTunesBuilder.imageBuilder(image)
            }
            "keywords" -> builder.iTunesBuilder.keywords(ifCanBeParsed { textOrNull() })
            "owner" -> {
                val ownerBuilder = ifCanBeParsed { toOwnerBuilder(builder.createPersonBuilder()) }
                builder.iTunesBuilder.ownerBuilder(ownerBuilder)
            }
            "subtitle" -> builder.iTunesBuilder.subtitle(ifCanBeParsed { textOrNull() })
            "summary" -> builder.iTunesBuilder.summary(ifCanBeParsed { textOrNull() })
            "type" -> builder.iTunesBuilder.type(ifCanBeParsed { textOrNull() })
            "title" -> builder.iTunesBuilder.title(ifCanBeParsed { textOrNull() })
            "new-feed-url" -> builder.iTunesBuilder.newFeedUrl(ifCanBeParsed { textOrNull() })
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
                builder.iTunesBuilder.block(block)
            }
            "duration" -> builder.iTunesBuilder.duration(ifCanBeParsed { textOrNull() })
            "episode" -> builder.iTunesBuilder.episode(ifCanBeParsed { parseAsInt() })
            "episodeType" -> builder.iTunesBuilder.episodeType(ifCanBeParsed { textOrNull() })
            "explicit" -> {
                val explicit = ifCanBeParsed { textAsBooleanOrNull() } ?: return
                builder.iTunesBuilder.explicit(explicit)
            }
            "image" -> {
                val imageBuilder = toHrefOnlyImageBuilder(builder.createHrefOnlyImageBuilder())
                builder.iTunesBuilder.imageBuilder(imageBuilder)
            }
            "season" -> builder.iTunesBuilder.season(ifCanBeParsed { parseAsInt() })
            "title" -> builder.iTunesBuilder.title(ifCanBeParsed { textOrNull() })
            "author" -> builder.iTunesBuilder.author(ifCanBeParsed { textOrNull() })
            "subtitle" -> builder.iTunesBuilder.subtitle(ifCanBeParsed { textOrNull() })
            "summary" -> builder.iTunesBuilder.summary(ifCanBeParsed { textOrNull() })
            else -> pass
        }
    }
}
