package io.hemin.wien.model.builder

import io.hemin.wien.model.Episode

class EpisodeGuidBuilder : Builder<Episode.Guid>() {

    private var value: String?      = null
    private var permalink: Boolean? = null

    fun value(value: String?) = apply { this.value = value }

    fun permalink(permalink: Boolean?) = apply { this.permalink = permalink }

    override fun build(): Episode.Guid? {
        return if (anyNotNull(value, permalink))
            Episode.Guid(
                value     = value,
                permalink = permalink
            )
        else null
    }


}
