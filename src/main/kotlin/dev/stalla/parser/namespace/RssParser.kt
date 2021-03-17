package dev.stalla.parser.namespace

import dev.stalla.builder.episode.EpisodeEnclosureBuilder
import dev.stalla.builder.episode.EpisodeGuidBuilder
import dev.stalla.builder.episode.ProvidingEpisodeBuilder
import dev.stalla.builder.podcast.ProvidingPodcastBuilder
import dev.stalla.dom.getAttributeValueByName
import dev.stalla.dom.parseAsBooleanOrNull
import dev.stalla.dom.parseAsInt
import dev.stalla.dom.parseAsLocaleOrNull
import dev.stalla.dom.parseAsMediaTypeOrNull
import dev.stalla.dom.parseAsTemporalAccessor
import dev.stalla.dom.textOrNull
import dev.stalla.dom.toRssCategoryBuilder
import dev.stalla.dom.toRssImageBuilder
import dev.stalla.parser.NamespaceParser
import dev.stalla.parser.namespace.RssParser.namespace
import dev.stalla.util.FeedNamespace
import dev.stalla.util.InternalAPI2
import dev.stalla.util.allNotNull
import org.w3c.dom.Element
import org.w3c.dom.Node

/**
 * Parser implementation for the RSS namespace.
 *
 * Note that RSS 2.0 feeds do not have a namespace URI, and thus [namespace] is null.
 *
 * The RSS specification is described [here](http://www.rssboard.org/rss-2-0).
 */
@InternalAPI2
internal object RssParser : NamespaceParser() {

    override val namespace: FeedNamespace? = null

    @Suppress("ComplexMethod")
    override fun Node.parseChannelData(builder: ProvidingPodcastBuilder) {
        if (this !is Element) return

        when (localName) {
            "copyright" -> builder.copyright(ifCanBeParsed { textOrNull() })
            "description" -> {
                val description = ifCanBeParsed { textOrNull() } ?: return
                builder.description(description)
            }
            "docs" -> builder.docs(ifCanBeParsed { textOrNull() })
            "generator" -> builder.generator(ifCanBeParsed { textOrNull() })
            "image" -> builder.imageBuilder(ifCanBeParsed { toRssImageBuilder(builder.createRssImageBuilder()) })
            "language" -> {
                val language = ifCanBeParsed { parseAsLocaleOrNull() } ?: return
                builder.language(language)
            }
            "lastBuildDate" -> builder.lastBuildDate(ifCanBeParsed { parseAsTemporalAccessor() })
            "link" -> {
                val link = ifCanBeParsed { textOrNull() } ?: return
                builder.link(link)
            }
            "managingEditor" -> builder.managingEditor(ifCanBeParsed { textOrNull() })
            "pubDate" -> builder.pubDate(ifCanBeParsed { parseAsTemporalAccessor() })
            "title" -> {
                val title = ifCanBeParsed { textOrNull() } ?: return
                builder.title(title)
            }
            "webMaster" -> builder.webMaster(ifCanBeParsed { textOrNull() })
            "ttl" -> builder.ttl(ifCanBeParsed { parseAsInt() })
            "category" -> {
                val categoryBuilder = ifCanBeParsed {
                    toRssCategoryBuilder(builder.createRssCategoryBuilder())
                } ?: return
                builder.addCategoryBuilder(categoryBuilder)
            }
            "item" -> pass // Items are parsed by the root parser direcly
        }
    }

    @Suppress("ComplexMethod")
    override fun Node.parseItemData(builder: ProvidingEpisodeBuilder) {
        if (this !is Element) return

        when (localName) {
            "author" -> builder.author(ifCanBeParsed { textOrNull() })
            "category" -> {
                val categoryBuilder = ifCanBeParsed {
                    toRssCategoryBuilder(builder.createRssCategoryBuilder())
                } ?: return
                builder.addCategoryBuilder(categoryBuilder)
            }
            "comments" -> builder.comments(ifCanBeParsed { textOrNull() })
            "description" -> builder.description(ifCanBeParsed { textOrNull() })
            "enclosure" -> {
                val enclosure = ifCanBeParsed {
                    toEnclosureBuilder(builder.createEnclosureBuilder())
                } ?: return
                builder.enclosureBuilder(enclosure)
            }
            "guid" -> builder.guidBuilder(ifCanBeParsed { toGuidBuilder(builder.createGuidBuilder()) })
            "link" -> builder.link(ifCanBeParsed { textOrNull() })
            "pubDate" -> builder.pubDate(ifCanBeParsed { parseAsTemporalAccessor() })
            "source" -> builder.source(ifCanBeParsed { textOrNull() })
            "title" -> {
                val title = ifCanBeParsed { textOrNull() } ?: return
                builder.title(title)
            }
        }
    }

    private fun Node.toEnclosureBuilder(builder: EpisodeEnclosureBuilder): EpisodeEnclosureBuilder? = ifCanBeParsed {
        val url = getAttributeValueByName("url")
        val length = getAttributeValueByName("length")?.toLongOrNull()
        val type = getAttributeValueByName("type").parseAsMediaTypeOrNull()

        if (!allNotNull(url, length, type)) return@ifCanBeParsed builder

        builder.url(url)
            .length(length)
            .type(type)
    }

    private fun Node.toGuidBuilder(builder: EpisodeGuidBuilder): EpisodeGuidBuilder? = ifCanBeParsed {
        val guid = ifCanBeParsed { textOrNull() } ?: return@ifCanBeParsed builder

        val isPermalink = getAttributeValueByName("isPermalink")
            ?: getAttributeValueByName("isPermaLink")

        builder.textContent(guid)
            .isPermalink(isPermalink.parseAsBooleanOrNull())
    }
}
