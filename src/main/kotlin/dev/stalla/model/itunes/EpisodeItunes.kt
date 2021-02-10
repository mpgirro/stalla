package dev.stalla.model.itunes

import dev.stalla.builder.episode.EpisodeITunesBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodeITunesBuilder
import dev.stalla.model.BuilderFactory
import dev.stalla.model.HrefOnlyImage

/**
 * Model class for data from elements of the iTunes namespace that are valid within `<item>` elements.
 *
 * @property title The `<itunes:title>` field text content.
 * @property duration The `<itunes:duration>` field text content.
 * @property image The data from the `<itunes:image>` element as an [HrefOnlyImage].
 * @property explicit The logical value of the `<itunes:explicit>` field's text content.
 * @property block The logical value of the `<itunes:block>` field's text content.
 * @property season The numeric value of the `<itunes:season>` field's text content.
 * @property episode The numeric value of the `<itunes:episode>` field's text content.
 * @property episodeType The `<itunes:episodeType>` field text content.
 */
public data class EpisodeItunes(
    override val title: String? = null,
    val duration: String? = null,
    override val image: HrefOnlyImage? = null,
    val explicit: Boolean? = null,
    override val block: Boolean,
    val season: Int? = null,
    val episode: Int? = null,
    val episodeType: EpisodeType? = null,
    override val author: String? = null,
    override val subtitle: String? = null,
    override val summary: String? = null
) : ITunesBase {

    public companion object Factory : BuilderFactory<EpisodeItunes, EpisodeITunesBuilder> {

        /** Returns a builder implementation for building [EpisodeItunes] model instances. */
        @JvmStatic
        override fun builder(): EpisodeITunesBuilder = ValidatingEpisodeITunesBuilder()
    }
}
