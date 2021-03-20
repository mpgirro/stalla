package dev.stalla.model.googleplay

import dev.stalla.builder.episode.EpisodeGoogleplayBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodeGoogleplayBuilder
import dev.stalla.model.BuilderFactory
import dev.stalla.model.HrefOnlyImage

/**
 * Model class for data from elements of the Google Play namespace that are valid within `<item>` elements.
 *
 * Direct instantiation in Java is discouraged. Use the [builder][EpisodeGoogleplay.Factory.builder]
 * method to obtain an [EpisodeGoogleplayBuilder] instance for expressive construction instead.
 *
 * Properties are as defined in the
 * [XML Schema](https://www.google.com/schemas/play-podcasts/1.0/play-podcasts.xsd)
 * for the Google Play Podcasts extension
 *
 * @property author The `<googleplay:author>` element text content.
 * @property description The `<googleplay:description>` element text content.
 * @property explicit The value of the `<googleplay:explicit>` element text content as an [ExplicitType].
 * @property block The logical value of the `<googleplay:block>` element text content.
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
