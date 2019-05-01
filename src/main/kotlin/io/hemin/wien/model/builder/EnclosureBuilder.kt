package io.hemin.wien.model.builder

import io.hemin.wien.model.Episode

class EnclosureBuilder : Builder<Episode.Enclosure> {

    private var url: String?  = null
    private var length: Long? = null
    private var type: String? = null

    /** Set the url. */
    fun url(url: String?) = apply { this.url = url }

    /** Set the length. */
    fun length(length: Long?) = apply { this.length = length }

    /** Set the type. */
    fun type(type: String?) = apply { this.type = type }

    /** Returns an instance of [Episode.Enclosure]
     * created from the fields set on this builder. */
    override fun build() = Episode.Enclosure(
        url    = url,
        length = length,
        type   = type
    )
}
