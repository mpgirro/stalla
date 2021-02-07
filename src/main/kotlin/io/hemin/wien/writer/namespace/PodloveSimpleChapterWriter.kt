package io.hemin.wien.writer.namespace

import io.hemin.wien.dom.appendElement
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.util.isNeitherNullNorBlank
import io.hemin.wien.writer.NamespaceWriter
import org.w3c.dom.Element

/**
 * Writer implementation for the Podlove Simple Chapter namespace.
 *
 * The namespace URI is: `http://podlove.org/simple-chapters`
 */
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
