package io.hemin.wien.parser.namespace

import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.dom.textOrNull
import io.hemin.wien.parser.NamespaceParser
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.util.InternalApi
import org.w3c.dom.Node

/**
 * Parser implementation for the Fyyd namespace.
 *
 * The namespace URI is: `https://fyyd.de/fyyd-ns/`
 */
@InternalApi
internal object FyydParser : NamespaceParser() {

    override val namespace = FeedNamespace.FYYD

    override fun Node.parseChannelData(builder: PodcastBuilder) {
        when (localName) {
            "verify" -> {
                val verify = ifCanBeParsed { textOrNull() } ?: return
                builder.fyydBuilder.verify(verify)
            }
            else -> pass
        }
    }

    override fun Node.parseItemData(builder: EpisodeBuilder) {
        // No-op
    }
}
