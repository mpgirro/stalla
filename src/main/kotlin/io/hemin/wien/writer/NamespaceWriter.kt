package io.hemin.wien.writer

import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.util.InternalApi
import org.w3c.dom.Element
import org.w3c.dom.Node

/** Base class for XML namespace writer implementations. */
@InternalApi
internal abstract class NamespaceWriter {

    /** The URI of the namespace written to by this writer. */
    abstract val namespace: FeedNamespace?

    /**
     * Writes data from the [Podcast] data model into the tags for the
     * XML namespace defined by [namespace].
     * This function will throw if the [element] does not satisfy
     * the writer's [canWriteToNode], which checks if the element is
     * really a `<channel>`.
     *
     * @param podcast The podcast data to write.
     * @param element The element to append the data to.
     */
    fun tryWritingPodcastData(podcast: Podcast, element: Element) {
        require(element.canWriteToNode("channel")) { "This function can only write data to <channel> nodes" }
        element.appendPodcastData(podcast)
    }

    /**
     * Appends data from the [Podcast] data model into the tags for the
     * XML namespace defined by [namespace].
     * Note: the [this@appendPodcastData] has already been validated to be a `<channel>`.
     *
     * @param podcast The podcast data to write.
     */
    protected abstract fun Element.appendPodcastData(podcast: Podcast)

    /**
     * Writes data from the [Episode] data model into the tags for the
     * XML namespace defined by [namespace].
     * This function will throw if the [element] does not satisfy
     * the writer's [canWriteToNode], which checks if the element is
     * really an `<item>`.
     *
     * @param episode The episode data to write.
     * @param element The element to append the data to.
     */
    fun tryWritingEpisodeData(episode: Episode, element: Element) {
        require(element.canWriteToNode("item")) { "This function can only write data to <item> nodes" }
        element.appendEpisodeData(episode)
    }

    /**
     * Appends data from the [Episode] data model into the tags for the
     * XML namespace defined by [namespace].
     * Note: the [this@appendEpisodeData] has already been validated to be a `<item>`.
     *
     * @param episode The episode data to write.
     */
    protected abstract fun Element.appendEpisodeData(episode: Episode)

    /**
     * Should return `true` when this writer can write to a given node. Returns true when the node
     * name matches the [expectedNodeName], and its [Node.getNamespaceURI] is `null`.
     */
    private fun Node.canWriteToNode(expectedNodeName: String) = nodeName == expectedNodeName && namespaceURI == null
}
