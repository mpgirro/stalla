package io.hemin.wien.parser

import io.hemin.wien.builder.EpisodeBuilder
import io.hemin.wien.builder.EpisodePodloveSimpleChapterBuilder
import io.hemin.wien.builder.PodcastBuilder
import io.hemin.wien.model.Episode
import io.hemin.wien.util.NodeListWrapper.Companion.asListOfNodes
import org.w3c.dom.Node
import kotlin.streams.toList

/**
 * Parser implementation for the Podlove Simple Chapter namespace.
 *
 * The namespace URI is: `http://podlove.org/simple-chapters`
 */
class PodloveSimpleChapterParser : NamespaceParser() {

    override val namespaceURI: String = "http://podlove.org/simple-chapters"

    /** This module does not set any data in the [PodcastBuilder]. */
    override fun parse(builder: PodcastBuilder, node: Node) {}

    override fun parse(builder: EpisodeBuilder, node: Node) = valid(node) {
        when (node.localName) {
            "chapters" -> builder.podlove.addSimpleChapters(toPodloveSimpleChapters(node))
            else -> pass
        }
    }

    /**
     * Transforms a `<psc:chapters>` element into a list of [Episode.Podlove.SimpleChapter] model class instances.
     *
     * @param node The DOM node representing the `<psc:chapters>` element.
     * @return The list of extracted [Episode.Podlove.SimpleChapter] instances.
     */
    private fun toPodloveSimpleChapters(node: Node): List<Episode.Podlove.SimpleChapter>? = valid(node) {
        node.childNodes.asListOfNodes().stream()
            .filter { c -> c.localName == "chapter" }
            .map(::toPodloveSimpleChapter)
            .toList()
            .filterNotNull()
    }

    /**
     * Transforms a `<psc:chapter>` element into an instance of the [Episode.Podlove.SimpleChapter] model class.
     *
     * @param node The DOM node representing the `<psc:chapter>` element.
     * @return The [Episode.Podlove.SimpleChapter] instance with the `<psc:chapter>` elements data, or null if all data was empty.
     */
    private fun toPodloveSimpleChapter(node: Node): Episode.Podlove.SimpleChapter? = valid(node) {
        val start = attributeValueByName(node, "start")
        val title = attributeValueByName(node, "title")
        if (start == null || title == null) return@valid null

        EpisodePodloveSimpleChapterBuilder()
            .start(start)
            .title(title)
            .href(attributeValueByName(node, "href"))
            .image(attributeValueByName(node, "image"))
            .build()
    }
}
