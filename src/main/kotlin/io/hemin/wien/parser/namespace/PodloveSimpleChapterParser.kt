package io.hemin.wien.parser.namespace

import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.episode.EpisodePodloveSimpleChapterBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.builder.validating.episode.ValidatingEpisodePodloveSimpleChapterBuilder
import io.hemin.wien.parser.NamespaceParser
import io.hemin.wien.util.NodeListWrapper.Companion.asListOfNodes
import org.w3c.dom.Node
import kotlin.streams.toList

/**
 * Parser implementation for the Podlove Simple Chapter namespace.
 *
 * The namespace URI is: `http://podlove.org/simple-chapters`
 */
internal class PodloveSimpleChapterParser : NamespaceParser() {

    override val namespaceURI: String = "http://podlove.org/simple-chapters"

    /** This module does not set any data in the [PodcastBuilder]. */
    override fun parse(builder: PodcastBuilder, node: Node) {}

    override fun parse(builder: EpisodeBuilder, node: Node) = valid(node) {
        when (node.localName) {
            "chapters" -> {
                val chapters = toPodloveSimpleChapterBuilders(node) ?: return@valid
                builder.podlove.addSimpleChapterBuilders(chapters)
            }
            else -> pass
        }
    }

    private fun toPodloveSimpleChapterBuilders(node: Node): List<EpisodePodloveSimpleChapterBuilder>? = valid(node) {
        node.childNodes.asListOfNodes().stream()
            .filter { c -> c.localName == "chapter" }
            .map(::toPodloveSimpleChapterBuilder)
            .toList()
            .filterNotNull()
    }

    private fun toPodloveSimpleChapterBuilder(node: Node): EpisodePodloveSimpleChapterBuilder? = valid(node) {
        val start = attributeValueByName(node, "start")
        val title = attributeValueByName(node, "title")
        if (start == null || title == null) return@valid null

        ValidatingEpisodePodloveSimpleChapterBuilder()
            .start(start)
            .title(title)
            .href(attributeValueByName(node, "href"))
            .image(attributeValueByName(node, "image"))
    }
}
