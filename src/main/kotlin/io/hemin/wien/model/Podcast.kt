package io.hemin.wien.model

import com.google.common.collect.ImmutableList

class Podcast(
    val title: String?,
    val link: String?,
    val episodes: List<Episode>
) {

    data class Builder(
        var title: String? = null,
        var link: String? = null,
        var episodes: MutableList<Episode> = mutableListOf<Episode>()
    ) {
        fun title(title: String?) = apply { this.title = title }
        fun link(link: String?) = apply { this.link = link }
        fun addEpisode(episode: Episode) = apply { this.episodes.add(episode) }
        fun build() = Podcast(title, link, ImmutableList.copyOf(episodes))
    }

    override fun toString(): String {
        return "Podcast(title=$title, link=$link, episodes=$episodes)"
    }


}
