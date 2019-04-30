package io.hemin.wien.model.builder

import io.hemin.wien.model.Episode

data class EnclosureBuilder(
    var url: String? = null,
    var length: Long? = null,
    var type: String? = null
) : Builder<Episode.Enclosure> {
    fun url(url: String?) = apply { this.url = url }
    fun length(length: Long?) = apply { this.length = length }
    fun type(type: String?) = apply { this.type = type }
    override fun build() = Episode.Enclosure(
        url    = url,
        length = length,
        type   = type
    )
}
