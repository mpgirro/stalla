package dev.stalla.writer.namespace

import dev.stalla.dom.appendElement
import dev.stalla.model.Episode
import dev.stalla.model.Podcast
import dev.stalla.util.FeedNamespace
import dev.stalla.util.InternalApi
import dev.stalla.util.isNeitherNullNorBlank
import dev.stalla.writer.NamespaceWriter
import org.w3c.dom.Element

/**
 * Writer implementation for the Podlove Simple Chapter namespace.
 *
 * The namespace URI is: `http://podlove.org/simple-chapters`
 */
@InternalApi
internal object PodloveSimpleChapterWriter : NamespaceWriter() {

    override val namespace = FeedNamespace.PODLOVE_SIMPLE_CHAPTER

    override fun Element.appendPodcastData(podcast: Podcast) {
        // Nothing to do here
    }

    override fun Element.appendEpisodeData(episode: Episode) {
        val chapters = episode.podlove?.simpleChapters ?: return
        val validChapters = chapters.filter { it.isValid() }
        if (validChapters.isEmpty()) return

        appendElement("chapters", namespace) {
            for (chapter in validChapters) {
                appendChapterElement(chapter)
            }
        }
    }

    private fun Element.appendChapterElement(chapter: Episode.Podlove.SimpleChapter) {
        appendElement("chapter", namespace) {
            setAttribute("start", chapter.start.trim())
            setAttribute("title", chapter.title.trim())

            if (chapter.href.isNeitherNullNorBlank()) {
                setAttribute("href", chapter.href?.trim())
            }
            if (chapter.image.isNeitherNullNorBlank()) {
                setAttribute("image", chapter.image?.trim())
            }
        }
    }

    private fun Episode.Podlove.SimpleChapter.isValid() = start.isNotBlank() && title.isNotBlank()
}
