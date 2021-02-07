package io.hemin.wien.parser.namespace

import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.dom.textAsBooleanOrNull
import io.hemin.wien.dom.textOrNull
import io.hemin.wien.dom.toHrefOnlyImageBuilder
import io.hemin.wien.dom.toITunesCategoryBuilder
import io.hemin.wien.parser.NamespaceParser
import io.hemin.wien.util.FeedNamespace
import org.w3c.dom.Node

/**
 * Parser implementation for the Google Play namespace.
 *
 * The namespace URI is: `http://www.google.com/schemas/play-podcasts/1.0`
 */
internal object GooglePlayParser : NamespaceParser() {

    override val namespace = FeedNamespace.GOOGLE_PLAY

    override fun Node.parseChannelData(builder: PodcastBuilder) {
        when (localName) {
            "author" -> builder.googlePlayBuilder.author(ifCanBeParsed { textOrNull() })
            "owner" -> builder.googlePlayBuilder.owner(ifCanBeParsed { textOrNull() })
            "category" -> {
                val categoryBuilder = builder.createITunesStyleCategoryBuilder()
                val category = ifCanBeParsed { toITunesCategoryBuilder(categoryBuilder, namespace) } ?: return
                builder.googlePlayBuilder.addCategoryBuilder(category)
            }
            "description" -> builder.googlePlayBuilder.description(ifCanBeParsed { textOrNull() })
            "explicit" -> {
                val explicit = ifCanBeParsed { textAsBooleanOrNull() } ?: return
                builder.googlePlayBuilder.explicit(explicit)
            }
            "block" -> {
                val block = ifCanBeParsed { textAsBooleanOrNull() } ?: return
                builder.googlePlayBuilder.block(block)
            }
            "image" -> {
                val imageBuilder = ifCanBeParsed { toHrefOnlyImageBuilder(builder.createHrefOnlyImageBuilder()) }
                builder.googlePlayBuilder.imageBuilder(imageBuilder)
            }
            else -> pass
        }
    }

    override fun Node.parseItemData(builder: EpisodeBuilder) {
        when (localName) {
            "description" -> builder.googlePlayBuilder.description(ifCanBeParsed { textOrNull() })
            "explicit" -> {
                val explicit = ifCanBeParsed { textAsBooleanOrNull() } ?: return
                builder.googlePlayBuilder.explicit(explicit)
            }
            "block" -> {
                val block = ifCanBeParsed { textAsBooleanOrNull() } ?: return
                builder.googlePlayBuilder.block(block)
            }
            "image" -> {
                val imageBuilder = ifCanBeParsed { toHrefOnlyImageBuilder(builder.createHrefOnlyImageBuilder()) }
                builder.googlePlayBuilder.imageBuilder(imageBuilder)
            }
            else -> pass
        }
    }
}
