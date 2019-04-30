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
    val source: String?,
    val contentEncoded: String?
) {

    class Enclosure(
        val url: String?,
        val length: Long?,
        val type: String?
    ) {
        override fun toString(): String {
            return "Enclosure(url=$url, length=$length, type=$type)"
        }
    }

    override fun toString(): String {
        return "Episode(title=$title, link=$link, description=$description, author=$author, categories=$categories, comments=$comments, enclosure=$enclosure, guid=$guid, pubDate=$pubDate, source=$source, contentEncoded=<truncated>)"
    }

}
