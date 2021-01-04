package io.hemin.wien.parser.namespace

import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.episode.EpisodePodloveSimpleChapterBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
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

    override fun parseChannelNode(builder: PodcastBuilder, node: Node) {
        // No-op
    }

    override fun parseItemNode(builder: EpisodeBuilder, node: Node) {
        when (node.localName) {
            "chapters" -> {
                val chapters = toPodloveSimpleChapterBuilders(node, builder) ?: return
                builder.podlove.addSimpleChapterBuilders(chapters)
            }
            else -> pass
        }
    }

    private fun toPodloveSimpleChapterBuilders(node: Node, builder: EpisodeBuilder): List<EpisodePodloveSimpleChapterBuilder>? = ifMatchesNamespace(node) {
        node.childNodes.asListOfNodes().stream()
            .filter { c -> c.localName == "chapter" }
            .map { it.toPodloveSimpleChapterBuilder(builder.createPodloveSimpleChapterBuilder()) }
            .toList()
            .filterNotNull()
    }

    private fun Node.toPodloveSimpleChapterBuilder(chapterBuilder: EpisodePodloveSimpleChapterBuilder): EpisodePodloveSimpleChapterBuilder? =
        ifMatchesNamespace(this) {
            val start = attributeValueByName(this, "start")
            val title = attributeValueByName(this, "title")
            if (start == null || title == null) return@ifMatchesNamespace null

            chapterBuilder.start(start)
                .title(title)
                .href(attributeValueByName(this, "href"))
                .image(attributeValueByName(this, "image"))
        }
}
