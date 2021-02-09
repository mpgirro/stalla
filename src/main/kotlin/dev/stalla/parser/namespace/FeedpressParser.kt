package dev.stalla.parser.namespace

import dev.stalla.builder.episode.EpisodeBuilder
import dev.stalla.builder.podcast.PodcastBuilder
import dev.stalla.dom.textOrNull
import dev.stalla.parser.NamespaceParser
import dev.stalla.util.FeedNamespace
import dev.stalla.util.InternalApi
import org.w3c.dom.Node

/**
 * Parser implementation for the Feedpress namespace.
 *
 * The namespace URI is: `https://feed.press/xmlns`
 */
@InternalApi
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
