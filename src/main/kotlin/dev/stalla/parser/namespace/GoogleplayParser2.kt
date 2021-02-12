package dev.stalla.parser.namespace

import dev.stalla.builder.episode.EpisodeBuilder
import dev.stalla.builder.podcast.PodcastBuilder
import dev.stalla.dom.textAsBooleanOrNull
import dev.stalla.dom.textOrNull
import dev.stalla.dom.toHrefOnlyImageBuilder
import dev.stalla.dom.toITunesCategoryBuilder
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
internal object GoogleplayParser2 : NamespaceParser() {

    override val namespace = FeedNamespace.GOOGLE_PLAY

    override fun Node.parseChannelData(builder: PodcastBuilder) {
        when (localName) {
            "author" -> builder.googleplayBuilder.author(ifCanBeParsed { textOrNull() })
            "owner" -> builder.googleplayBuilder.owner(ifCanBeParsed { textOrNull() })
            "category" -> {
                val categoryBuilder = builder.createITunesStyleCategoryBuilder()
                val category = ifCanBeParsed { toITunesCategoryBuilder(categoryBuilder, namespace) } ?: return
                builder.googleplayBuilder.addCategoryBuilder(category)
            }
            "description" -> builder.googleplayBuilder.description(ifCanBeParsed { textOrNull() })
            "explicit" -> {
                val explicit = ifCanBeParsed { textAsBooleanOrNull() } ?: return
                builder.googleplayBuilder.explicit(explicit)
            }
            "block" -> {
                val block = ifCanBeParsed { textAsBooleanOrNull() } ?: return
                builder.googleplayBuilder.block(block)
            }
            "image" -> {
                val imageBuilder = ifCanBeParsed { toHrefOnlyImageBuilder(builder.createHrefOnlyImageBuilder()) }
                builder.googleplayBuilder.imageBuilder(imageBuilder)
            }
            else -> pass
        }
    }

    override fun Node.parseItemData(builder: EpisodeBuilder) {
        when (localName) {
            "description" -> builder.googleplayBuilder.description(ifCanBeParsed { textOrNull() })
            "explicit" -> {
                val explicit = ifCanBeParsed { textAsBooleanOrNull() } ?: return
                builder.googleplayBuilder.explicit(explicit)
            }
            "block" -> {
                val block = ifCanBeParsed { textAsBooleanOrNull() } ?: return
                builder.googleplayBuilder.block(block)
            }
            "image" -> {
                val imageBuilder = ifCanBeParsed { toHrefOnlyImageBuilder(builder.createHrefOnlyImageBuilder()) }
                builder.googleplayBuilder.imageBuilder(imageBuilder)
            }
            else -> pass
        }
    }
}
