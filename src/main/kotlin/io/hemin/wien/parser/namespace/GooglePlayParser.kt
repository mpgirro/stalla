package io.hemin.wien.parser.namespace

import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.builder.textAsBooleanOrNull
import io.hemin.wien.builder.textOrNull
import io.hemin.wien.builder.toHrefOnlyImageBuilder
import io.hemin.wien.builder.toITunesCategoryBuilder
import io.hemin.wien.parser.NamespaceParser
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.util.getAttributeValueByName
import org.w3c.dom.Node

/**
 * Parser implementation for the Google Play namespace.
 *
 * The namespace URI is: `http://www.google.com/schemas/play-podcasts/1.0`
 */
internal class GooglePlayParser : NamespaceParser() {

    override val namespace = FeedNamespace.GOOGLE_PLAY

    override fun parseChannelNode(builder: PodcastBuilder, node: Node) {
        when (node.localName) {
            "author" -> builder.googlePlay.author(node.ifCanBeParsed { textOrNull() })
            "owner" -> builder.googlePlay.owner(node.ifCanBeParsed { textOrNull() })
            "category" -> {
                val categoryBuilder = builder.createITunesCategoryBuilder()
                val category = node.ifCanBeParsed { toITunesCategoryBuilder(categoryBuilder, namespace) } ?: return
                builder.googlePlay.addCategoryBuilder(category)
            }
            "description" -> builder.googlePlay.description(node.ifCanBeParsed { textOrNull() })
            "explicit" -> builder.googlePlay.explicit(node.ifCanBeParsed { textAsBooleanOrNull() })
            "block" -> builder.googlePlay.block(node.ifCanBeParsed { textAsBooleanOrNull() })
            "image" -> {
                val imageBuilder = node.ifCanBeParsed { toHrefOnlyImageBuilder(builder.createHrefOnlyImageBuilder()) }
                builder.googlePlay.imageBuilder(imageBuilder)
            }
            else -> pass
        }
    }

    override fun parseItemNode(builder: EpisodeBuilder, node: Node) {
        when (node.localName) {
            "description" -> builder.googlePlay.description(node.ifCanBeParsed { textOrNull() })
            "explicit" -> builder.googlePlay.explicit(node.ifCanBeParsed { textAsBooleanOrNull() })
            "block" -> builder.googlePlay.block(node.ifCanBeParsed { textAsBooleanOrNull() })
            "image" -> {
                val imageBuilder = node.ifCanBeParsed { toHrefOnlyImageBuilder(builder.createHrefOnlyImageBuilder()) }
                builder.googlePlay.imageBuilder(imageBuilder)
            }
            else -> pass
        }
    }
}
