package dev.stalla.parser.namespace

import dev.stalla.builder.episode.EpisodePodloveSimpleChapterBuilder
import dev.stalla.builder.episode.ProvidingEpisodeBuilder
import dev.stalla.builder.podcast.ProvidingPodcastBuilder
import dev.stalla.dom.asListOfNodes
import dev.stalla.dom.getAttributeValueByName
import dev.stalla.parser.NamespaceParser
import dev.stalla.util.FeedNamespace
import dev.stalla.util.InternalApi
import org.w3c.dom.Node

/**
 * Parser implementation for the Podlove Simple Chapter namespace.
 *
 * The namespace URI is: `http://podlove.org/simple-chapters`
 */
@InternalApi
internal object PodloveSimpleChapterParser : NamespaceParser() {

    override val namespace = FeedNamespace.PODLOVE_SIMPLE_CHAPTER

    override fun Node.parseChannelData(builder: ProvidingPodcastBuilder) {
        // No-op
    }

    override fun Node.parseItemData(builder: ProvidingEpisodeBuilder) {
        when (localName) {
            "chapters" -> ifCanBeParsed { toPodloveSimpleChapterBuilders(builder) }
                ?.let(builder.podloveBuilder::addAllSimpleChapterBuilder)
            else -> pass
        }
    }

    private fun Node.toPodloveSimpleChapterBuilders(
        builder: ProvidingEpisodeBuilder
    ): List<EpisodePodloveSimpleChapterBuilder> =
        childNodes.asListOfNodes().asSequence()
            .filter { c -> c.localName == "chapter" }
            .map { node ->
                node.ifCanBeParsed {
                    toPodloveSimpleChapterBuilder(builder.createPodloveSimpleChapterBuilder())
                }
            }
            .filterNotNull()
            .toList()

    private fun Node.toPodloveSimpleChapterBuilder(
        chapterBuilder: EpisodePodloveSimpleChapterBuilder
    ): EpisodePodloveSimpleChapterBuilder? {
        val start = getAttributeValueByName("start")
        val title = getAttributeValueByName("title")
        if (start == null || title == null) return null

        return chapterBuilder.start(start)
            .title(title)
            .href(getAttributeValueByName("href"))
            .image(getAttributeValueByName("image"))
    }
}
