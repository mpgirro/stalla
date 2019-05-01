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

    /** Set the title. */
    fun title(title: String?) = apply { this.title = title }

    /** Set the link. */
    fun link(link: String?) = apply { this.link = link }

    /** Set the description. */
    fun description(description: String?) = apply { this.description = description }

    /** Set the pubDate. */
    fun pubDate(pubDate: ZonedDateTime?) = apply { this.pubDate = pubDate }

    /** Set the lastBuildDate. */
    fun lastBuildDate(lastBuildDate: ZonedDateTime?) = apply { this.lastBuildDate = lastBuildDate }

    /** Set the language. */
    fun language(language: String?) = apply { this.language = language }

    /** Set the generator. */
    fun generator(generator: String?) = apply { this.generator = generator }

    /** Set the copyright. */
    fun copyright(copyright: String?) = apply { this.copyright = copyright }

    /** Set the docs. */
    fun docs(docs: String?) = apply { this.docs = docs }

    /** Set the managingEditor. */
    fun managingEditor(managingEditor: String?) = apply { this.managingEditor = managingEditor }

    /** Set the webMaster. */
    fun webMaster(webMaster: String?) = apply { this.webMaster = webMaster }

    /** Adds an [io.hemin.wien.model.Episode] to the list of Episodes. */
    fun addEpisode(episode: Episode) = apply { this.episodes.add(episode) }

    /** Returns an instance of [Podcast] created from the fields set on this builder. */
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
