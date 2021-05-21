package dev.stalla.writer.namespace

import dev.stalla.dom.appendElement
import dev.stalla.dom.appendGoogleplayCategoryElements
import dev.stalla.dom.appendHrefOnlyImageElement
import dev.stalla.dom.appendYesElementIfTrue
import dev.stalla.model.Episode
import dev.stalla.model.Podcast
import dev.stalla.util.FeedNamespace
import dev.stalla.util.InternalAPI
import dev.stalla.util.isNeitherNullNorBlank
import dev.stalla.writer.NamespaceWriter
import org.w3c.dom.Element

/**
 * Writer implementation for the Google Play namespace.
 *
 * The namespace URI is: `http://www.google.com/schemas/play-podcasts/1.0`
 */
@InternalAPI
internal object GoogleplayWriter : NamespaceWriter() {

    override val namespace = FeedNamespace.GOOGLE_PLAY

    override fun Element.appendPodcastData(podcast: Podcast) {
        val play = podcast.googleplay ?: return

        if (play.email.isNeitherNullNorBlank()) {
            appendElement("email", namespace) { textContent = play.email.trim() }
        }

        if (play.explicitType != null) {
            appendElement("explicit", namespace) { textContent = play.explicitType.type.trim() }
        }

        if (play.newFeedUrl.isNeitherNullNorBlank()) {
            appendElement("newFeedUrl", namespace) { textContent = play.newFeedUrl.trim() }
        }

        appendGoogleplayCategoryElements(play.categories, namespace)

        if (play.author.isNeitherNullNorBlank()) {
            appendElement("author", namespace) { textContent = play.author.trim() }
        }

        if (play.description.isNeitherNullNorBlank()) {
            appendElement("description", namespace) { textContent = play.description.trim() }
        }

        appendYesElementIfTrue("block", play.block, namespace)

        if (play.image != null) {
            appendHrefOnlyImageElement(play.image, namespace)
        }
    }

    override fun Element.appendEpisodeData(episode: Episode) {
        val play = episode.googleplay ?: return

        if (play.explicit != null) {
            appendElement("explicit", namespace) { textContent = play.explicit.type.trim() }
        }

        if (play.author.isNeitherNullNorBlank()) {
            appendElement("author", namespace) { textContent = play.author.trim() }
        }

        if (play.description.isNeitherNullNorBlank()) {
            appendElement("description", namespace) { textContent = play.description.trim() }
        }

        appendYesElementIfTrue("block", play.block, namespace)

        if (play.image != null) {
            appendHrefOnlyImageElement(play.image, namespace)
        }
    }
}
