package dev.stalla.parser.namespace

import dev.stalla.builder.episode.ProvidingEpisodeBuilder
import dev.stalla.builder.podcast.ProvidingPodcastBuilder
import dev.stalla.dom.textAsBooleanOrNull
import dev.stalla.dom.textOrNull
import dev.stalla.dom.toGoogleplayCategory
import dev.stalla.dom.toHrefOnlyImageBuilder
import dev.stalla.parser.NamespaceParser
import dev.stalla.util.FeedNamespace
import dev.stalla.util.InternalApi
import org.w3c.dom.Node

/**
 * Parser implementation for the Google Play namespace.
 *
 * The namespace URI is: `http://www.google.com/schemas/play-podcasts/1.0`
 */
@InternalApi
internal object GoogleplayParser : NamespaceParser() {

    override val namespace = FeedNamespace.GOOGLE_PLAY

    override fun Node.parseChannelData(builder: ProvidingPodcastBuilder) {
        when (localName) {
            "author" -> builder.googleplayBuilder.author(ifCanBeParsed { textOrNull() })
            "owner" -> builder.googleplayBuilder.owner(ifCanBeParsed { textOrNull() })
            "category" -> ifCanBeParsed { toGoogleplayCategory() }
                ?.let { category -> builder.googleplayBuilder.addCategory(category) }
            "description" -> builder.googleplayBuilder.description(ifCanBeParsed { textOrNull() })
            "explicit" -> ifCanBeParsed { textAsBooleanOrNull() }
                ?.let { explicit -> builder.googleplayBuilder.explicit(explicit) }
            "block" -> ifCanBeParsed { textAsBooleanOrNull() }
                ?.let { block -> builder.googleplayBuilder.block(block) }
            "image" -> ifCanBeParsed { toHrefOnlyImageBuilder(builder.createHrefOnlyImageBuilder()) }
                ?.let { imageBuilder -> builder.googleplayBuilder.imageBuilder(imageBuilder) }
            "new-feed-url" -> builder.googleplayBuilder.newFeedUrl(ifCanBeParsed { textOrNull() })
            else -> pass
        }
    }

    override fun Node.parseItemData(builder: ProvidingEpisodeBuilder) {
        when (localName) {
            "author" -> builder.googleplayBuilder.author(ifCanBeParsed { textOrNull() })
            "description" -> builder.googleplayBuilder.description(ifCanBeParsed { textOrNull() })
            "explicit" -> builder.googleplayBuilder.explicit(ifCanBeParsed { textOrNull() })
            "block" -> ifCanBeParsed { textAsBooleanOrNull() }
                ?.let { block -> builder.googleplayBuilder.block(block) }
            "image" -> ifCanBeParsed { toHrefOnlyImageBuilder(builder.createHrefOnlyImageBuilder()) }
                ?.let { imageBuilder -> builder.googleplayBuilder.imageBuilder(imageBuilder) }
            else -> pass
        }
    }
}
