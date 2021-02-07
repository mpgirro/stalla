package io.hemin.wien.writer.namespace

import io.hemin.wien.dom.appendElement
import io.hemin.wien.dom.appendRssCategoryElements
import io.hemin.wien.dom.appendRssImageElement
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.BooleanStringStyle
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.util.InternalApi
import io.hemin.wien.util.asBooleanString
import io.hemin.wien.util.asDateString
import io.hemin.wien.util.isNeitherNullNorBlank
import io.hemin.wien.writer.NamespaceWriter
import org.w3c.dom.Element

/**
 * Writer implementation for the RSS namespace.
 *
 * Note that RSS 2.0 feeds do not have a namespace URI specified. The document specification is described here:
 *
 * `http://www.rssboard.org/rss-2-0`
 */
@InternalApi
internal object RssWriter : NamespaceWriter() {

    /** Standard RSS 2.0 elements do not have a namespace. This value is therefore null. */
    override val namespace: FeedNamespace? = null

    override fun Element.appendPodcastData(podcast: Podcast) {
        if (podcast.title.isNotBlank()) {
            appendElement("title") { textContent = podcast.title.trim() }
        }

        if (podcast.link.isNotBlank()) {
            appendElement("link") { textContent = podcast.link.trim() }
        }

        if (podcast.description.isNotBlank()) {
            appendElement("description") { textContent = podcast.description.trim() }
        }

        if (podcast.pubDate != null) {
            appendElement("pubDate") { textContent = podcast.pubDate.asDateString() }
        }

        if (podcast.lastBuildDate != null) {
            appendElement("lastBuildDate") { textContent = podcast.lastBuildDate.asDateString() }
        }

        if (podcast.generator.isNeitherNullNorBlank()) {
            appendElement("generator") { textContent = podcast.generator?.trim() }
        }

        if (podcast.language.isNotBlank()) {
            appendElement("language") { textContent = podcast.language.trim() }
        }

        if (podcast.copyright.isNeitherNullNorBlank()) {
            appendElement("copyright") { textContent = podcast.copyright?.trim() }
        }

        if (podcast.docs.isNeitherNullNorBlank()) {
            appendElement("docs") { textContent = podcast.docs?.trim() }
        }

        if (podcast.managingEditor.isNeitherNullNorBlank()) {
            appendElement("managingEditor") { textContent = podcast.managingEditor?.trim() }
        }

        if (podcast.webMaster.isNeitherNullNorBlank()) {
            appendElement("webMaster") { textContent = podcast.webMaster?.trim() }
        }

        if (podcast.ttl != null) {
            appendElement("ttl") { textContent = podcast.ttl.toString() }
        }

        appendRssCategoryElements(podcast.categories)

        if (podcast.image != null) appendRssImageElement(podcast.image)
    }

    override fun Element.appendEpisodeData(episode: Episode) {
        if (episode.title.isNotBlank()) {
            appendElement("title") { textContent = episode.title.trim() }
        }

        if (episode.link.isNeitherNullNorBlank()) {
            appendElement("link") { textContent = episode.link?.trim() }
        }

        if (episode.description.isNeitherNullNorBlank()) {
            appendElement("description") { textContent = episode.description?.trim() }
        }

        if (episode.author.isNeitherNullNorBlank()) {
            appendElement("author") { textContent = episode.author?.trim() }
        }

        appendRssCategoryElements(episode.categories)

        if (episode.comments.isNeitherNullNorBlank()) {
            appendElement("comments") { textContent = episode.comments?.trim() }
        }

        appendEnclosureElement(episode.enclosure)

        if (episode.guid != null) {
            appendGuidElement(episode.guid)
        }

        if (episode.pubDate != null) {
            appendElement("pubDate") { textContent = episode.pubDate.asDateString() }
        }

        if (episode.source.isNeitherNullNorBlank()) {
            appendElement("source") { textContent = episode.source?.trim() }
        }
    }

    private fun Element.appendEnclosureElement(enclosure: Episode.Enclosure) {
        if (enclosure.url.isBlank() || enclosure.type.isBlank()) return
        appendElement("enclosure") {
            setAttribute("url", enclosure.url.trim())
            setAttribute("length", enclosure.length.toString())
            setAttribute("type", enclosure.type.trim())
        }
    }

    private fun Element.appendGuidElement(guid: Episode.Guid) {
        if (guid.guid.isBlank()) return
        appendElement("guid") {
            if (guid.isPermalink != null) {
                setAttribute("isPermalink", guid.isPermalink.asBooleanString(BooleanStringStyle.TRUE_FALSE))
            }
            textContent = guid.guid.trim()
        }
    }
}
