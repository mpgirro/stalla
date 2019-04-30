package io.hemin.wien.model.builder

import io.hemin.wien.model.Episode
import java.time.ZonedDateTime

data class EpisodeBuilder(
    var title: String?                  = null,
    var link: String?                   = null,
    var description: String?            = null,
    var author: String?                 = null,
    val categories: MutableList<String> = mutableListOf(),
    var comments: String?               = null,
    var enclosure: Episode.Enclosure?   = null,
    var guid: String?                   = null,
    var pubDate: ZonedDateTime?         = null,
    var source: String?                 = null,
    var contentEncoded: String?         = null

) : Builder<Episode> {
    fun title(title: String?) = apply { this.title = title }
    fun link(link: String?) = apply { this.link = link }
    fun description(description: String?) = apply { this.description = description }
    fun author(author: String?) = apply { this.author = author }
    fun addCategory(category: String?) = apply {
        category?.let { this.categories.add(it) }
    }
    fun comments(comments: String?) = apply { this.comments = comments }
    fun enclosure(enclosure: Episode.Enclosure) = apply { this.enclosure = enclosure }
    fun guid(guid: String?) = apply { this.guid = guid }
    fun pubDate(pubDate: ZonedDateTime?) = apply { this.pubDate = pubDate }
    fun source(source: String?) = apply { this.source = source }
    fun contentEncoded(contentEncoded: String?) = apply { this.contentEncoded = contentEncoded }
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
