package io.hemin.wien.parser

import io.hemin.wien.builder.EpisodeBuilder
import io.hemin.wien.builder.EpisodePodloveSimpleChapterBuilder
import io.hemin.wien.builder.PodcastBuilder
import io.hemin.wien.model.Episode
import io.hemin.wien.util.NodeListWrapper.Companion.asList
import org.w3c.dom.Node
import kotlin.streams.toList

class PodloveSimpleChapterParser : NamespaceParser() {

    override val namespaceURI: String? = "http://podlove.org/simple-chapters"

    override fun parseChannel(builder: PodcastBuilder, node: Node) { }

    override fun parseItem(builder: EpisodeBuilder, node: Node) {
        when (node.localName) {
            "chapters" -> builder.podlove.addSimpleChapters(toPodloveSimpleChapters(node))
        }
    }

    fun toPodloveSimpleChapters(node: Node): List<Episode.Podlove.SimpleChapter>? = valid(node) {
        asList(node.childNodes).stream()
            .filter { c -> c.localName == "chapter" }
            .map(::toPodloveSimpleChapter)
            .toList()
            .filterNotNull()
    }

    fun toPodloveSimpleChapter(node: Node): Episode.Podlove.SimpleChapter? = valid(node) {
        EpisodePodloveSimpleChapterBuilder()
            .start(attributeValueByName(node, "start"))
            .title(attributeValueByName(node, "title"))
            .href(attributeValueByName(node, "href"))
            .image(attributeValueByName(node, "image"))
            .build()
    }

}
