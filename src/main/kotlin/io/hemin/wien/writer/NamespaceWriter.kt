package io.hemin.wien.writer

import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.FeedNamespace
import org.w3c.dom.Element
import org.w3c.dom.Node
import java.time.temporal.TemporalAccessor

/** Base class for XML namespace writer implementations. */
internal abstract class NamespaceWriter {

    /** The URI of the namespace written to by this writer. */
    abstract val namespace: FeedNamespace?

    /**
     * Writes data from the channel data model into the tags for the
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
        writeChannelData(podcast, element)
    }

    /**
     * Writes data from the channel data model into the tags for the
     * XML namespace defined by [namespace].
     * Note: the [element] has already been validated to be a `<channel>`.
     *
     * @param channel The podcast data to write.
     */
    protected abstract fun writeChannelData(channel: Podcast, element: Element)

    /**
     * Writes data from the item data model into the tags for the
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
        writeItemData(episode, element)
    }

    /**
     * Writes data from the item data model into the tags for the
     * XML namespace defined by [namespace].
     * Note: the [element] has already been validated to be a `<item>`.
     *
     * @param episode The episode data to write.
     * @param element The element to append the data to.
     */
    protected abstract fun writeItemData(episode: Episode, element: Element)

    /**
     * Converts a [TemporalAccessor] value to a RFC2822 [String] representation.
     *
     * @return The string representing the value in the chosen style.
     */
    protected fun TemporalAccessor.asDateString() = DateFormatter.formatAsRfc2822(this)

    /**
     * Should return `true` when this writer can write to a given node. Returns true when the node
     * name matches the [expectedNodeName], and its [Node.getNamespaceURI] is `null`.
     */
    private fun Node.canWriteToNode(expectedNodeName: String) = nodeName == expectedNodeName && namespaceURI == null
}
