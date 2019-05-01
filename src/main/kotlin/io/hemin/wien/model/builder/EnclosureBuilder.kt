package io.hemin.wien.model.builder

import io.hemin.wien.model.Episode

/** Builder class for [Episode.Enclosure] instances. */
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

    /**
     * Creates an instance of [Episode.Enclosure] with the properties set in this builder.
     *
     * @return The create instance.
     */
    override fun build() = Episode.Enclosure(
        url    = url,
        length = length,
        type   = type
    )
}
