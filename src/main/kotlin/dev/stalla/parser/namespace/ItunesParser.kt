package dev.stalla.parser.namespace

import dev.stalla.builder.PersonBuilder
import dev.stalla.builder.episode.ProvidingEpisodeBuilder
import dev.stalla.builder.podcast.ProvidingPodcastBuilder
import dev.stalla.dom.parseAsInt
import dev.stalla.dom.textAsBooleanOrNull
import dev.stalla.dom.textOrNull
import dev.stalla.dom.toHrefOnlyImageBuilder
import dev.stalla.dom.toItunesCategory
import dev.stalla.dom.toPersonBuilder
import dev.stalla.model.StyledDuration
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

    @Suppress("ComplexMethod")
    override fun Node.parseChannelData(builder: ProvidingPodcastBuilder) {
        when (localName) {
            "author" -> builder.itunesBuilder.author(ifCanBeParsed { textOrNull() })
            "block" -> ifCanBeParsed { textAsBooleanOrNull() }?.let { block -> builder.itunesBuilder.block(block) }
            "category" -> ifCanBeParsed { toItunesCategory(namespace) }
                ?.let { category -> builder.itunesBuilder.addCategory(category) }
            "complete" -> ifCanBeParsed { textAsBooleanOrNull() }
                ?.let { complete -> builder.itunesBuilder.complete(complete) }
            "explicit" -> ifCanBeParsed { textAsBooleanOrNull() }
                ?.let { explicit -> builder.itunesBuilder.explicit(explicit) }
            "image" -> ifCanBeParsed { toHrefOnlyImageBuilder(builder.createHrefOnlyImageBuilder()) }
                ?.let { image -> builder.itunesBuilder.imageBuilder(image) }
            "keywords" -> builder.itunesBuilder.keywords(ifCanBeParsed { textOrNull() })
            "owner" -> ifCanBeParsed { toOwnerBuilder(builder.createPersonBuilder()) }
                ?.let { ownerBuilder -> builder.itunesBuilder.ownerBuilder(ownerBuilder) }
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

    @Suppress("ComplexMethod")
    override fun Node.parseItemData(builder: ProvidingEpisodeBuilder) {
        when (localName) {
            "block" -> ifCanBeParsed { textAsBooleanOrNull() }
                ?.let { block -> builder.itunesBuilder.block(block) }
            "duration" -> builder.itunesBuilder.duration(ifCanBeParsed { StyledDuration.of(textOrNull()) })
            "episode" -> builder.itunesBuilder.episode(ifCanBeParsed { parseAsInt() })
            "episodeType" -> builder.itunesBuilder.episodeType(ifCanBeParsed { textOrNull() })
            "explicit" -> ifCanBeParsed { textAsBooleanOrNull() }
                ?.let { explicit -> builder.itunesBuilder.explicit(explicit) }
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
