package io.hemin.wien.writer.namespace

import io.hemin.wien.dom.appendElement
import io.hemin.wien.dom.appendRssCategoryElements
import io.hemin.wien.dom.appendRssImageElement
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.BooleanStringStyle
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.util.asBooleanString
import io.hemin.wien.writer.NamespaceWriter
import org.w3c.dom.Element

/**
 * Writer implementation for the RSS namespace.
 *
 * Note that RSS 2.0 feeds do not have a namespace URI specified. The document specification is described here:
 *
 * `http://www.rssboard.org/rss-2-0`
 */
internal class RssWriter : NamespaceWriter() {

    /** Standard RSS 2.0 elements do not have a namespace. This value is therefore null. */
    override val namespace: FeedNamespace? = null

    override fun writeChannelData(channel: Podcast, element: Element) {
        element.appendElement("title") { textContent = channel.title }
        element.appendElement("link") { textContent = channel.link }
        element.appendElement("description") { textContent = channel.description }

        if (channel.pubDate != null) {
            element.appendElement("pubDate") { textContent = channel.pubDate.asDateString() }
        }

        if (channel.lastBuildDate != null) {
            element.appendElement("lastBuildDate") { textContent = channel.lastBuildDate.asDateString() }
        }

        if (channel.generator != null) {
            element.appendElement("generator") { textContent = channel.generator }
        }

        element.appendElement("language") { textContent = channel.language }

        if (channel.copyright != null) {
            element.appendElement("copyright") { textContent = channel.copyright }
        }

        if (channel.docs != null) {
            element.appendElement("docs") { textContent = channel.docs }
        }

        if (channel.managingEditor != null) {
            element.appendElement("managingEditor") { textContent = channel.managingEditor }
        }

        if (channel.webMaster != null) {
            element.appendElement("webMaster") { textContent = channel.webMaster }
        }

        if (channel.image != null) element.appendRssImageElement(channel.image)
    }

    override fun writeItemData(episode: Episode, element: Element) {
        element.appendElement("title") { textContent = episode.title }

        if (episode.link != null) {
            element.appendElement("link") { textContent = episode.link }
        }

        if (episode.description != null) {
            element.appendElement("description") { textContent = episode.description }
        }

        if (episode.author != null) {
            element.appendElement("author") { textContent = episode.author }
        }

        element.appendRssCategoryElements(episode.categories)

        if (episode.comments != null) {
            element.appendElement("comments") { textContent = episode.comments }
        }

        element.appendEnclosureElement(episode.enclosure)

        if (episode.guid != null) {
            element.appendGuidElement(episode.guid)
        }

        if (episode.pubDate != null) {
            element.appendElement("pubDate") { textContent = episode.pubDate.asDateString() }
        }

        if (episode.source != null) {
            element.appendElement("source") { textContent = episode.source }
        }
    }

    private fun Element.appendEnclosureElement(enclosure: Episode.Enclosure) {
        appendElement("enclosure") {
            setAttribute("url", enclosure.url)
            setAttribute("length", enclosure.length.toString())
            setAttribute("type", enclosure.type)
        }
    }

    private fun Element.appendGuidElement(guid: Episode.Guid) {
        appendElement("guid") {
            if (guid.isPermalink != null) {
                setAttribute("isPermalink", guid.isPermalink.asBooleanString(BooleanStringStyle.TRUE_FALSE))
            }
            textContent = guid.textContent
        }
    }
}
