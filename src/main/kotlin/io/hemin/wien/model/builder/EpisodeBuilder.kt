package io.hemin.wien.model.builder

import io.hemin.wien.model.Episode
import java.time.ZonedDateTime

class EpisodeBuilder : Builder<Episode> {

    private var title: String?                  = null
    private var link: String?                   = null
    private var description: String?            = null
    private var author: String?                 = null
    private val categories: MutableList<String> = mutableListOf()
    private var comments: String?               = null
    private var enclosure: Episode.Enclosure?   = null
    private var guid: String?                   = null
    private var pubDate: ZonedDateTime?         = null
    private var source: String?                 = null
    private var contentEncoded: String?         = null

    /** Set the title. */
    fun title(title: String?) = apply { this.title = title }

    /** Set the link. */
    fun link(link: String?) = apply { this.link = link }

    /** Set the description. */
    fun description(description: String?) = apply { this.description = description }

    /** Set the author. */
    fun author(author: String?) = apply { this.author = author }

    fun addCategory(category: String?) = apply {
        category?.let { this.categories.add(it) }
    }

    /** Set the comments. */
    fun comments(comments: String?) = apply { this.comments = comments }

    fun enclosure(enclosure: Episode.Enclosure) = apply { this.enclosure = enclosure }

    /** Set the GUID. */
    fun guid(guid: String?) = apply { this.guid = guid }

    /** Set the pubDate. */
    fun pubDate(pubDate: ZonedDateTime?) = apply { this.pubDate = pubDate }

    /** Set the source. */
    fun source(source: String?) = apply { this.source = source }

    fun contentEncoded(contentEncoded: String?) = apply { this.contentEncoded = contentEncoded }

    /** Returns an instance of [Episode] created from the fields set on this builder. */
    override fun build() = Episode(
        title          = title,
        link           = link,
        description    = description,
        author         = author,
        categories     = categories,
        comments       = comments,
        enclosure      = enclosure,
        guid           = guid,
        pubDate        = pubDate,
        source         = source,
        contentEncoded = contentEncoded
    )
}
