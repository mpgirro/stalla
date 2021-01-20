package io.hemin.wien.writer.namespace

import io.hemin.wien.dom.appendElement
import io.hemin.wien.dom.appendHrefOnlyImageElement
import io.hemin.wien.dom.appendITunesStyleCategoryElements
import io.hemin.wien.dom.appendYesElementIfTrue
import io.hemin.wien.dom.appendYesNoElement
import io.hemin.wien.model.Episode
import io.hemin.wien.model.GooglePlayBase
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.util.isNeitherNullNorBlank
import io.hemin.wien.writer.NamespaceWriter
import org.w3c.dom.Element

/**
 * Writer implementation for the Google Play namespace.
 *
 * The namespace URI is: `http://www.google.com/schemas/play-podcasts/1.0`
 */
internal class GooglePlayWriter : NamespaceWriter() {

    override val namespace = FeedNamespace.GOOGLE_PLAY

    override fun Element.appendPodcastData(podcast: Podcast) {
        val play = podcast.googlePlay ?: return

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
        val play = episode.googlePlay ?: return

        appendCommonElements(play)
    }

    private fun Element.appendCommonElements(play: GooglePlayBase) {
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
