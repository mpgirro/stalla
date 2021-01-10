package io.hemin.wien.writer.namespace

import io.hemin.wien.dom.appendElement
import io.hemin.wien.dom.appendRssCategoryElements
import io.hemin.wien.dom.appendRssImageElement
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.BooleanStringStyle
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.util.asBooleanString
import io.hemin.wien.util.asDateString
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

    override fun Element.appendPodcastData(podcast: Podcast) {
        appendElement("title") { textContent = podcast.title }
        appendElement("link") { textContent = podcast.link }
        appendElement("description") { textContent = podcast.description }

        if (podcast.pubDate != null) {
            appendElement("pubDate") { textContent = podcast.pubDate.asDateString() }
        }

        if (podcast.lastBuildDate != null) {
            appendElement("lastBuildDate") { textContent = podcast.lastBuildDate.asDateString() }
        }

        if (podcast.generator != null) {
            appendElement("generator") { textContent = podcast.generator }
        }

        appendElement("language") { textContent = podcast.language }

        if (podcast.copyright != null) {
            appendElement("copyright") { textContent = podcast.copyright }
        }

        if (podcast.docs != null) {
            appendElement("docs") { textContent = podcast.docs }
        }

        if (podcast.managingEditor != null) {
            appendElement("managingEditor") { textContent = podcast.managingEditor }
        }

        if (podcast.webMaster != null) {
            appendElement("webMaster") { textContent = podcast.webMaster }
        }

        if (podcast.image != null) appendRssImageElement(podcast.image)
    }

    override fun Element.appendEpisodeData(episode: Episode) {
        appendElement("title") { textContent = episode.title }

        if (episode.link != null) {
            appendElement("link") { textContent = episode.link }
        }

        if (episode.description != null) {
            appendElement("description") { textContent = episode.description }
        }

        if (episode.author != null) {
            appendElement("author") { textContent = episode.author }
        }

        appendRssCategoryElements(episode.categories)

        if (episode.comments != null) {
            appendElement("comments") { textContent = episode.comments }
        }

        appendEnclosureElement(episode.enclosure)

        if (episode.guid != null) {
            appendGuidElement(episode.guid)
        }

        if (episode.pubDate != null) {
            appendElement("pubDate") { textContent = episode.pubDate.asDateString() }
        }

        if (episode.source != null) {
            appendElement("source") { textContent = episode.source }
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
