package io.hemin.wien.parser.namespace

import io.hemin.wien.builder.ImageBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.builder.validating.ValidatingImageBuilder
import io.hemin.wien.builder.validating.ValidatingPersonBuilder
import io.hemin.wien.model.Image
import io.hemin.wien.parser.NamespaceParser
import io.hemin.wien.util.NodeListWrapper.Companion.asListOfNodes
import org.w3c.dom.Node

/**
 * Parser implementation for the iTunes namespace.
 *
 * The namespace URI is: `http://www.itunes.com/dtds/podcast-1.0.dtd`
 */
internal class ITunesParser : NamespaceParser() {

    /**
     * The URI of the namespace processed by this parser.
     *
     * URI: `http://www.itunes.com/dtds/podcast-1.0.dtd`
     */
    override val namespaceURI: String = "http://www.itunes.com/dtds/podcast-1.0.dtd"

    override fun parse(builder: PodcastBuilder, node: Node) = valid(node) {
        when (node.localName) {
            "author" -> builder.iTunes.author(toText(node))
            "block" -> builder.iTunes.block(toBoolean(node))
            "category" -> {
                val category = node.attributes.getNamedItem("text").textContent ?: return@valid
                builder.iTunes.addCategory(category)
            }
            "complete" -> builder.iTunes.complete(toBoolean(node))
            "explicit" -> {
                val explicit = toBoolean(node) ?: return@valid
                builder.iTunes.explicit(explicit)
            }
            "image" -> {
                val image = toImageBuilder(node, ValidatingImageBuilder()) ?: return@valid
                builder.iTunes.imageBuilder(image)
            }
            "keywords" -> builder.iTunes.keywords(toText(node))
            "owner" -> builder.iTunes.ownerBuilder(toPersonBuilder(node))
            "subtitle" -> builder.iTunes.subtitle(toText(node))
            "summary" -> builder.iTunes.summary(toText(node))
            "type" -> builder.iTunes.type(toText(node))
            "title" -> builder.iTunes.title(toText(node))
            "new-feed-url" -> builder.iTunes.newFeedUrl(toText(node))
            else -> pass
        }
    }

    override fun parse(builder: EpisodeBuilder, node: Node) = valid(node) {
        when (node.localName) {
            "block" -> builder.iTunes.block(toBoolean(node))
            "duration" -> builder.iTunes.duration(toText(node))
            "episode" -> builder.iTunes.episode(toInt(node))
            "episodeType" -> builder.iTunes.episodeType(toText(node))
            "explicit" -> builder.iTunes.explicit(toBoolean(node))
            "image" -> builder.iTunes.imageBuilder(toImageBuilder(node, ValidatingImageBuilder()))
            "season" -> builder.iTunes.season(toInt(node))
            "title" -> builder.iTunes.title(toText(node))
            else -> pass
        }
    }

    /**
     * Transforms an <itunes:image>` element into an instance of the [Image] model class.
     *
     * @param node The DOM node representing the `<itunes:image>` element.
     * @return The [Image] instance with the `<itunes:image>` elements data, or null if all data was empty.
     */
    private fun toImageBuilder(node: Node, imageBuilder: ValidatingImageBuilder): ImageBuilder? = valid(node) {
        val url: String? = attributeValueByName(node, "href")
        if (url.isNullOrBlank()) {
            null
        } else {
            imageBuilder
                .url(url)
        }
    }

    /**
     * Transforms an <itunes:owner>` element into an instance of the [Image] model class.
     *
     * @param node The DOM node representing the `<itunes:owner>` element.
     * @return The [Image] instance with the `<itunes:owner>` elements data, or null if all data was empty.
     */
    private fun toPersonBuilder(node: Node): PersonBuilder? = valid(node) {
        val builder = ValidatingPersonBuilder()
        for (child in node.childNodes.asListOfNodes()) {
            val value: String? = toText(child)
            when (child.localName) {
                "name" -> if (value != null) builder.name(value)
                "email" -> builder.email(value)
            }
        }
        builder
    }
}
