package io.hemin.wien.model.builder

import com.google.common.collect.ImmutableList
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import java.time.ZonedDateTime

data class PodcastBuilder(
    var title: String?                 = null,
    var link: String?                  = null,
    var description: String?           = null,
    var pubDate: ZonedDateTime?        = null,
    var lastBuildDate: ZonedDateTime?  = null,
    var language: String?              = null,
    var generator: String?             = null,
    var copyright: String?             = null,
    var docs: String?                  = null,
    var managingEditor: String?        = null,
    var webMaster: String?             = null,
    val episodes: MutableList<Episode> = mutableListOf()
) : Builder<Podcast> {
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
