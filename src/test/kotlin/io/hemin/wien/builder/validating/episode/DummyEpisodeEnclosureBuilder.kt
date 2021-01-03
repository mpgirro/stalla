package io.hemin.wien.builder.validating.episode

import io.hemin.wien.builder.episode.EpisodeEnclosureBuilder
import io.hemin.wien.model.Episode

internal class DummyEpisodeEnclosureBuilder(private val enclosure: Episode.Enclosure) : EpisodeEnclosureBuilder {

    override fun url(url: String): EpisodeEnclosureBuilder = this

    override fun length(length: Long): EpisodeEnclosureBuilder = this

    override fun type(type: String): EpisodeEnclosureBuilder = this

    override fun build() = enclosure
}
