package io.hemin.wien.writer.namespace

import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.util.appendElement
import io.hemin.wien.writer.NamespaceWriter
import org.w3c.dom.Element

/**
 * Writer implementation for the Podlove Simple Chapter namespace.
 *
 * The namespace URI is: `http://podlove.org/simple-chapters`
 */
internal class PodloveSimpleChapterWriter : NamespaceWriter() {

    override val namespace = FeedNamespace.PODLOVE_SIMPLE_CHAPTER

    override fun writeChannelData(channel: Podcast, element: Element) {
        // Nothing to do here
    }

    override fun writeItemData(episode: Episode, element: Element) {
        val chapters = episode.podlove?.simpleChapters ?: return

        element.appendElement("chapters", namespace) {
            for (chapter in chapters) {
                appendChapterElement(chapter)
            }
        }
    }

    private fun Element.appendChapterElement(chapter: Episode.Podlove.SimpleChapter) {
        appendElement("chapter", namespace) {
            setAttribute("start", chapter.start)
            setAttribute("title", chapter.title)

            if (chapter.href != null) setAttribute("href", chapter.href)
            if (chapter.image != null) setAttribute("image", chapter.image)
        }
    }
}
