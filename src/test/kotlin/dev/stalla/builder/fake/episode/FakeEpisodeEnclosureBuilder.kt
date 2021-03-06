package dev.stalla.builder.fake.episode

import dev.stalla.builder.episode.EpisodeEnclosureBuilder
import dev.stalla.builder.fake.FakeBuilder
import dev.stalla.model.MediaType
import dev.stalla.model.rss.Enclosure

internal class FakeEpisodeEnclosureBuilder : FakeBuilder<Enclosure>(), EpisodeEnclosureBuilder {

    var urlValue: String? = null
    var lengthValue: Long = -1
    var typeValue: MediaType? = null

    override fun url(url: String): EpisodeEnclosureBuilder = apply { this.urlValue = url }

    override fun length(length: Long): EpisodeEnclosureBuilder = apply { this.lengthValue = length }

    override fun type(type: MediaType): EpisodeEnclosureBuilder = apply { this.typeValue = type }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakeEpisodeEnclosureBuilder) return false

        if (urlValue != other.urlValue) return false
        if (lengthValue != other.lengthValue) return false
        if (typeValue != other.typeValue) return false

        return true
    }

    override fun hashCode(): Int {
        var result = urlValue?.hashCode() ?: 0
        result = 31 * result + lengthValue.hashCode()
        result = 31 * result + (typeValue?.hashCode() ?: 0)
        return result
    }

    override fun toString() = "FakeEpisodeEnclosureBuilder(urlValue=$urlValue, lengthValue=$lengthValue, typeValue=$typeValue)"
}
