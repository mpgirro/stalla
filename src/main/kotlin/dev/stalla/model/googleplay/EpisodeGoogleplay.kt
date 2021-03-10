package dev.stalla.model.googleplay

import dev.stalla.builder.episode.EpisodeGoogleplayBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodeGoogleplayBuilder
import dev.stalla.model.BuilderFactory
import dev.stalla.model.HrefOnlyImage

/**
 * Model class for data from elements of the Google Play namespace that are valid within `<item>` elements.
 *
 * Properties are as defined in the
 * [XML Schema](https://www.google.com/schemas/play-podcasts/1.0/play-podcasts.xsd)
 * for the Google Play Podcasts extension
 *
 * @property author The `<googleplay:author>` field text content.
 * @property description The `<googleplay:description>` field text content.
 * @property explicit The value of the `<googleplay:explicit>` field text content as an [ExplicitType].
 * @property block The logical value of the `<googleplay:block>` field's text content.
 * @property image The data from the `<googleplay:image>` element as an [HrefOnlyImage].
 *
 * @since 1.0.0
 */
public data class EpisodeGoogleplay(
    val author: String? = null,
    val description: String? = null,
    val explicit: ExplicitType? = null,
    val block: Boolean,
    val image: HrefOnlyImage? = null
) {

    /** Provides a builder for the [EpisodeGoogleplay] class. */
    public companion object Factory : BuilderFactory<EpisodeGoogleplay, EpisodeGoogleplayBuilder> {

        /** Returns a builder implementation for building [EpisodeGoogleplay] model instances. */
        @JvmStatic
        override fun builder(): EpisodeGoogleplayBuilder = ValidatingEpisodeGoogleplayBuilder()
    }
}
