package io.hemin.wien.writer.namespace

import io.hemin.wien.dom.appendElement
import io.hemin.wien.dom.appendHrefOnlyImageElement
import io.hemin.wien.dom.appendITunesCategoryElements
import io.hemin.wien.dom.appendYesElementIfTrue
import io.hemin.wien.model.Episode
import io.hemin.wien.model.GooglePlayBase
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.FeedNamespace
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

        if (play.author != null) {
            appendElement("author", namespace) { textContent = play.author }
        }

        if (play.owner != null) {
            appendElement("owner", namespace) { textContent = play.owner }
        }

        appendITunesCategoryElements(play.categories, namespace)

        appendCommonElements(play)
    }

    override fun Element.appendEpisodeData(episode: Episode) {
        val play = episode.googlePlay ?: return

        appendCommonElements(play)
    }

    private fun Element.appendCommonElements(play: GooglePlayBase) {
        val description = play.description
        if (description != null) {
            appendElement("description", namespace) { textContent = description }
        }

        val explicit = play.explicit
        if (explicit != null) {
            appendYesElementIfTrue("explicit", explicit, namespace)
        }

        val block = play.block
        if (block != null) {
            appendYesElementIfTrue("block", block, namespace)
        }

        val image = play.image
        if (image != null) {
            appendHrefOnlyImageElement(image, namespace)
        }
    }
}
