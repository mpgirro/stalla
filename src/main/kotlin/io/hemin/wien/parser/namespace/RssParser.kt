package io.hemin.wien.parser.namespace

import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.episode.EpisodeEnclosureBuilder
import io.hemin.wien.builder.episode.EpisodeGuidBuilder
import io.hemin.wien.builder.parseAsBooleanOrNull
import io.hemin.wien.builder.parseAsTemporalAccessor
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.builder.textOrNull
import io.hemin.wien.builder.toRssImageBuilder
import io.hemin.wien.parser.NamespaceParser
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.util.getAttributeValueByName
import org.w3c.dom.Element
import org.w3c.dom.Node

/**
 * Parser implementation for the RSS namespace.
 *
 * Note that RSS 2.0 feeds do not have a namespace URI, and thus [namespace] is null.
 *
 * The RSS specification is described []here][http://www.rssboard.org/rss-2-0].
 */
internal class RssParser : NamespaceParser() {

    override val namespace: FeedNamespace? = null

    override fun parseChannelNode(builder: PodcastBuilder, node: Node) {
        if (node !is Element) return

        when (node.localName) {
            "copyright" -> builder.copyright(node.ifCanBeParsed { textOrNull() })
            "description" -> {
                val description = node.ifCanBeParsed { textOrNull() } ?: return
                builder.description(description)
            }
            "docs" -> builder.docs(node.ifCanBeParsed { textOrNull() })
            "generator" -> builder.generator(node.ifCanBeParsed { textOrNull() })
            "image" -> builder.imageBuilder(node.ifCanBeParsed { toRssImageBuilder(builder.createRssImageBuilder()) })
            "language" -> {
                val language = node.ifCanBeParsed { textOrNull() } ?: return
                builder.language(language)
            }
            "lastBuildDate" -> builder.lastBuildDate(node.ifCanBeParsed { parseAsTemporalAccessor() })
            "link" -> {
                val link = node.ifCanBeParsed { textOrNull() } ?: return
                builder.link(link)
            }
            "managingEditor" -> builder.managingEditor(node.ifCanBeParsed { textOrNull() })
            "pubDate" -> builder.pubDate(node.ifCanBeParsed { parseAsTemporalAccessor() })
            "title" -> {
                val title = node.ifCanBeParsed { textOrNull() } ?: return
                builder.title(title)
            }
            "webMaster" -> builder.webMaster(node.ifCanBeParsed { textOrNull() })
            "item" -> pass // Items are parsed by the root parser direcly
        }
    }

    override fun parseItemNode(builder: EpisodeBuilder, node: Node) {
        if (node !is Element) return

        when (node.localName) {
            "author" -> builder.author(node.ifCanBeParsed { textOrNull() })
            "category" -> {
                val category = node.ifCanBeParsed { textOrNull() } ?: return
                builder.addCategory(category)
            }
            "comments" -> builder.comments(node.ifCanBeParsed { textOrNull() })
            "description" -> builder.description(node.ifCanBeParsed { textOrNull() })
            "enclosure" -> {
                val enclosure = node.ifCanBeParsed { toEnclosureBuilder(builder.createEnclosureBuilder()) } ?: return
                builder.enclosureBuilder(enclosure)
            }
            "guid" -> builder.guidBuilder(node.ifCanBeParsed { toGuidBuilder(builder.createGuidBuilder()) })
            "link" -> builder.link(node.ifCanBeParsed { textOrNull() })
            "pubDate" -> builder.pubDate(node.ifCanBeParsed { parseAsTemporalAccessor() })
            "source" -> builder.source(node.ifCanBeParsed { textOrNull() })
            "title" -> {
                val title = node.ifCanBeParsed { textOrNull() } ?: return
                builder.title(title)
            }
        }
    }

    private fun Node.toEnclosureBuilder(enclosureBuilder: EpisodeEnclosureBuilder): EpisodeEnclosureBuilder? = ifCanBeParsed {
        val url = getAttributeValueByName("url")
        val length = getAttributeValueByName("length")?.toLongOrNull()
        val type = getAttributeValueByName("type")

        if (url == null || length == null || type == null) return@ifCanBeParsed null

        enclosureBuilder.url(url)
            .length(length)
            .type(type)
    }

    private fun Node.toGuidBuilder(builder: EpisodeGuidBuilder): EpisodeGuidBuilder? = ifCanBeParsed {
        val guid = ifCanBeParsed { textOrNull() } ?: return@ifCanBeParsed null

        val isPermalink = getAttributeValueByName("isPermalink")
            ?: getAttributeValueByName("isPermaLink")

        builder.textContent(guid)
            .isPermalink(isPermalink.parseAsBooleanOrNull())
    }
}
