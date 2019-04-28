package io.hemin.wien.model

class Episode(
    val title: String?,
    val link: String?
) {

    data class Builder(
        var title: String? = null,
        var link: String? = null
    ) {
        fun title(title: String?) = apply { this.title = title }
        fun link(link: String?) = apply { this.link = link }
        fun build() = Episode(title, link)
    }

    override fun toString(): String {
        return "Episode(title=$title, link=$link)"
    }

}
