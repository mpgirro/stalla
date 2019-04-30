package io.hemin.wien.model

import java.time.ZonedDateTime

class Podcast(
    val title: String?,
    val link: String?,
    val description: String?,
    val pubDate: ZonedDateTime?,
    val lastBuildDate: ZonedDateTime?,
    val language: String?,
    val generator: String?,
    val copyright: String?,
    val docs: String?,
    val managingEditor: String?,
    val webMaster: String?,
    //val image: Image?, // TODO
    val episodes: List<Episode>
) {
    override fun toString(): String {
        return "Podcast(title=$title, link=$link, description=$description, pubDate=$pubDate, lastBuildDate=$lastBuildDate, language=$language, generator=$generator, copyright=$copyright, docs=$docs, managingEditor=$managingEditor, webMaster=$webMaster, episodes=$episodes)"
    }
}
