package dev.stalla.parser.namespace

import dev.stalla.builder.episode.ProvidingEpisodeBuilder
import dev.stalla.builder.podcast.ProvidingPodcastBuilder
import dev.stalla.dom.parseAsLocaleOrNull
import dev.stalla.dom.textOrNull
import dev.stalla.parser.NamespaceParser
import dev.stalla.util.FeedNamespace
import dev.stalla.util.InternalAPI
import org.w3c.dom.Node

/**
 * Parser implementation for the Feedpress namespace.
 *
 * The namespace URI is: `https://feed.press/xmlns`
 */
@InternalAPI
internal object FeedpressParser : NamespaceParser() {

    override val namespace = FeedNamespace.FEEDPRESS

    override fun Node.parseChannelData(builder: ProvidingPodcastBuilder) {
        when (localName) {
            "newsletterId" -> builder.feedpressBuilder.newsletterId(ifCanBeParsed { textOrNull() })
            "locale" -> {
                val locale = ifCanBeParsed { parseAsLocaleOrNull() } ?: return
                builder.feedpressBuilder.locale(locale)
            }
            "podcastId" -> builder.feedpressBuilder.podcastId(ifCanBeParsed { textOrNull() })
            "cssFile" -> builder.feedpressBuilder.cssFile(ifCanBeParsed { textOrNull() })
            "link" -> builder.feedpressBuilder.link(ifCanBeParsed { textOrNull() })
            else -> pass
        }
    }

    override fun Node.parseItemData(builder: ProvidingEpisodeBuilder) {
        // No-op
    }
}
