package dev.stalla.writer.namespace

import dev.stalla.dom.appendElement
import dev.stalla.dom.appendHrefOnlyImageElement
import dev.stalla.dom.appendITunesStyleCategoryElements
import dev.stalla.dom.appendYesElementIfTrue
import dev.stalla.dom.appendYesNoElement
import dev.stalla.model.Episode
import dev.stalla.model.Podcast
import dev.stalla.model.googleplay.GoogleplayBase
import dev.stalla.util.FeedNamespace
import dev.stalla.util.InternalApi
import dev.stalla.util.isNeitherNullNorBlank
import dev.stalla.writer.NamespaceWriter
import org.w3c.dom.Element

/**
 * Writer implementation for the Google Play namespace.
 *
 * The namespace URI is: `http://www.google.com/schemas/play-podcasts/1.0`
 */
@InternalApi
internal object GoogleplayWriter : NamespaceWriter() {

    override val namespace = FeedNamespace.GOOGLE_PLAY

    override fun Element.appendPodcastData(podcast: Podcast) {
        val play = podcast.googleplay ?: return

        if (play.author.isNeitherNullNorBlank()) {
            appendElement("author", namespace) { textContent = play.author?.trim() }
        }

        if (play.owner.isNeitherNullNorBlank()) {
            appendElement("owner", namespace) { textContent = play.owner?.trim() }
        }

        appendITunesStyleCategoryElements(play.categories, namespace)

        appendCommonElements(play)
    }

    override fun Element.appendEpisodeData(episode: Episode) {
        val play = episode.googleplay ?: return

        appendCommonElements(play)
    }

    private fun Element.appendCommonElements(play: GoogleplayBase) {
        val description = play.description
        if (description.isNeitherNullNorBlank()) {
            appendElement("description", namespace) { textContent = description?.trim() }
        }

        val explicit = play.explicit
        if (explicit != null) {
            appendYesNoElement("explicit", explicit, namespace)
        }

        appendYesElementIfTrue("block", play.block, namespace)

        val image = play.image
        if (image != null) {
            appendHrefOnlyImageElement(image, namespace)
        }
    }
}
