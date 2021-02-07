package io.hemin.wien.parser.namespace

import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.dom.textOrNull
import io.hemin.wien.parser.NamespaceParser
import io.hemin.wien.util.FeedNamespace
import org.w3c.dom.Node

/**
 * Parser implementation for the Feedpress namespace.
 *
 * The namespace URI is: `https://feed.press/xmlns`
 */
internal object FeedpressParser : NamespaceParser() {

    override val namespace = FeedNamespace.FEEDPRESS

    override fun Node.parseChannelData(builder: PodcastBuilder) {
        when (localName) {
            "newsletterId" -> builder.feedpressBuilder.newsletterId(ifCanBeParsed { textOrNull() })
            "locale" -> builder.feedpressBuilder.locale(ifCanBeParsed { textOrNull() })
            "podcastId" -> builder.feedpressBuilder.podcastId(ifCanBeParsed { textOrNull() })
            "cssFile" -> builder.feedpressBuilder.cssFile(ifCanBeParsed { textOrNull() })
            "link" -> builder.feedpressBuilder.link(ifCanBeParsed { textOrNull() })
            else -> pass
        }
    }

    override fun Node.parseItemData(builder: EpisodeBuilder) {
        // No-op
    }
}
