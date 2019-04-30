package io.hemin.wien.model

import java.time.ZonedDateTime

class Episode(
    val title: String?,
    val link: String?,
    val description: String?,
    val author: String?, // TODO turn to Person?
    val categories: List<String>, // TODO make Category class? can look like: <category domain="http://www.fool.com/cusips">MSFT</category>
    val comments: String?,
    val enclosure: Episode.Enclosure?,
    val guid: String?,
    val pubDate: ZonedDateTime?,
    val source: String?
) : Model {

    data class Builder(
        var title: String? = null,
        var link: String? = null,
        var description: String? = null,
        var author: String? = null,
        val categories: MutableList<String> = mutableListOf<String>(),
        var comments: String? = null,
        var enclosure: Episode.Enclosure? = null,
        var guid: String? = null,
        var pubDate: ZonedDateTime? = null,
        var source: String? = null

    ) : ModelBuilder<Episode> {
        fun title(title: String?) = apply { this.title = title }
        fun link(link: String?) = apply { this.link = link }
        fun description(description: String?) = apply { this.description = description }
        fun author(author: String?) = apply { this.author = author }
        fun addCategory(category: String?) = apply {
            category?.let { this.categories.add(it) }
        }
        fun comments(comments: String?) = apply { this.comments = comments }
        fun enclosure(enclosure: Episode.Enclosure) = apply { this.enclosure = enclosure }
        fun enclosure(url: String?, length: Long?, type: String?) = apply {
            this.enclosure = Episode.Enclosure(url = url, length = length, type = type)
        }
        fun guid(guid: String?) = apply { this.guid = guid }
        fun pubDate(pubDate: ZonedDateTime?) = apply { this.pubDate = pubDate }
        fun source(source: String?) = apply { this.source = source }
        override fun build() = Episode(
            title       = title,
            link        = link,
            description = description,
            author      = author,
            categories  = categories,
            comments    = comments,
            enclosure   = enclosure,
            guid        = guid,
            pubDate     = pubDate,
            source      = source
        )
    }

    class Enclosure(
        val url: String?,
        val length: Long?,
        val type: String?
    ) : Model {

        data class Builder(
            var url: String? = null,
            var length: Long? = null,
            var type: String? = null
        ) : ModelBuilder<Enclosure> {
            fun url(url: String?) = apply { this.url = url }
            fun length(length: Long?) = apply { this.length = length }
            fun type(type: String?) = apply { this.type = type }
            override fun build() = Episode.Enclosure(
                url    = url,
                length = length,
                type   = type
            )
        }

        override fun toString(): String {
            return "Enclosure(url=$url, length=$length, type=$type)"
        }
    }

    override fun toString(): String {
        return "Episode(title=$title, link=$link, description=$description, author=$author, categories=$categories, comments=$comments, enclosure=$enclosure, guid=$guid, pubDate=$pubDate, source=$source)"
    }

}
