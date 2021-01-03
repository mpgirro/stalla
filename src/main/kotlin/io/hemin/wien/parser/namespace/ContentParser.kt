package io.hemin.wien.parser.namespace

import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.parser.NamespaceParser
import org.w3c.dom.Node

/**
 * Parser implementation for the Content namespace.
 *
 * The namespace URI is: `http://purl.org/rss/1.0/modules/content/`
 */
internal class ContentParser : NamespaceParser() {

    /**
     * The URI of the namespace processed by this parser.
     *
     * URI: `http://purl.org/rss/1.0/modules/content/`
     */
    override val namespaceURI: String = "http://purl.org/rss/1.0/modules/content/"

    /** This module does not set any data in the [ValidatingPodcastBuilder]. */
    override fun parse(builder: PodcastBuilder, node: Node) {}

    override fun parse(builder: EpisodeBuilder, node: Node) = valid(node) {
        when (node.localName) {
            "encoded" -> {
                val encoded = toText(node)
                if (encoded != null) builder.content.encoded(encoded)
            }
            else -> pass
        }
    }
}
