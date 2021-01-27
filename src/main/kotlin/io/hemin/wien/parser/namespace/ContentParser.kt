package io.hemin.wien.parser.namespace

import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.dom.textOrNull
import io.hemin.wien.parser.NamespaceParser
import io.hemin.wien.util.FeedNamespace
import org.w3c.dom.Node

/**
 * Parser implementation for the Content namespace.
 *
 * The namespace URI is: `http://purl.org/rss/1.0/modules/content/`
 */
internal class ContentParser : NamespaceParser() {

    override val namespace = FeedNamespace.CONTENT

    override fun Node.parseChannelData(builder: PodcastBuilder) {
        // No-op
    }

    override fun Node.parseItemData(builder: EpisodeBuilder) {
        when (localName) {
            "encoded" -> {
                val encoded = ifCanBeParsed { textOrNull() } ?: return
                builder.contentBuilder.encoded(encoded)
            }
            else -> pass
        }
    }
}
