package dev.stalla.model.itunes

import dev.stalla.builder.episode.EpisodeItunesBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodeItunesBuilder
import dev.stalla.model.BuilderFactory
import dev.stalla.model.HrefOnlyImage
import dev.stalla.model.StyledDuration

/**
 * Model class for data from elements of the iTunes namespace that are valid within `<item>` elements.
 *
 * Direct instantiation in Java is discouraged. Use the [builder][EpisodeItunes.Factory.builder]
 * method to obtain a [EpisodeItunesBuilder] instance for expressive construction instead.
 *
 * @property title The `<itunes:title>` element text content.
 * @property duration The value of the `<itunes:duration>` element text content as a [StyledDuration].
 * @property image The data from the `<itunes:image>` element as an [HrefOnlyImage].
 * @property explicit The logical value of the `<itunes:explicit>` element text content.
 * @property block The logical value of the `<itunes:block>` element text content.
 * @property season The numeric value of the `<itunes:season>` element text content.
 * @property episode The numeric value of the `<itunes:episode>` element text content.
 * @property episodeType The value of the `<itunes:episodeType>` element text content as an [EpisodeType].
 * @property author The `<itunes:author>` element text content.
 * @property subtitle The `<itunes:subtitle>` element text content.
 * @property summary The `<itunes:summary>` element text content.
 *
 * @since 1.0.0
 */
public data class EpisodeItunes(
    val title: String? = null,
    val duration: StyledDuration? = null,
    val image: HrefOnlyImage? = null,
    val explicit: Boolean? = null,
    val block: Boolean,
    val season: Int? = null,
    val episode: Int? = null,
    val episodeType: EpisodeType? = null,
    val author: String? = null,
    val subtitle: String? = null,
    val summary: String? = null
) {

    /** Provides a builder for the [EpisodeItunes] class. */
    public companion object Factory : BuilderFactory<EpisodeItunes, EpisodeItunesBuilder> {

        /** Returns a builder implementation for building [EpisodeItunes] model instances. */
        @JvmStatic
        override fun builder(): EpisodeItunesBuilder = ValidatingEpisodeItunesBuilder()
    }
}
