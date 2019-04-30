package io.hemin.wien.model.builder

import com.google.common.collect.ImmutableList
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import java.time.ZonedDateTime

class PodcastBuilder : Builder<Podcast> {

    private var title: String?                 = null
    private var link: String?                  = null
    private var description: String?           = null
    private var pubDate: ZonedDateTime?        = null
    private var lastBuildDate: ZonedDateTime?  = null
    private var language: String?              = null
    private var generator: String?             = null
    private var copyright: String?             = null
    private var docs: String?                  = null
    private var managingEditor: String?        = null
    private var webMaster: String?             = null
    private val episodes: MutableList<Episode> = mutableListOf()

    fun title(title: String?) = apply { this.title = title }

    fun link(link: String?) = apply { this.link = link }

    fun description(description: String?) = apply { this.description = description }

    fun pubDate(pubDate: ZonedDateTime?) = apply { this.pubDate = pubDate }

    fun lastBuildDate(lastBuildDate: ZonedDateTime?) = apply { this.lastBuildDate = lastBuildDate }

    fun language(language: String?) = apply { this.language = language }

    fun generator(generator: String?) = apply { this.generator = generator }

    fun copyright(copyright: String?) = apply { this.copyright = copyright }

    fun docs(docs: String?) = apply { this.docs = docs }

    fun managingEditor(managingEditor: String?) = apply { this.managingEditor = managingEditor }

    fun webMaster(webMaster: String?) = apply { this.webMaster = webMaster }

    fun addEpisode(episode: Episode) = apply { this.episodes.add(episode) }

    override fun build() = Podcast(
        title          = title,
        link           = link,
        description    = description,
        pubDate        = pubDate,
        lastBuildDate  = lastBuildDate,
        language       = language,
        generator      = generator,
        copyright      = copyright,
        docs           = docs,
        managingEditor = managingEditor,
        webMaster      = webMaster,
        episodes       = ImmutableList.copyOf(episodes)
    )
}
