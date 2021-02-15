package dev.stalla.model.googleplay

import dev.stalla.builder.episode.EpisodeGoogleplayBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodeGoogleplayBuilder
import dev.stalla.model.BuilderFactory
import dev.stalla.model.HrefOnlyImage

/**
 * Model class for data from elements of the Google Play namespace that are valid within `<item>` elements.
 *
 * @property description The `<googleplay:description>` field text content.
 * @property explicit The `<googleplay:explicit>` field text content.
 * @property block The logical value of the `<googleplay:block>` field's text content.
 * @property image The data from the `<googleplay:image>` element as an [HrefOnlyImage].
 */
public data class EpisodeGoogleplay(
    override val description: String? = null,
    val explicit: ExplicitType? = null,
    override val block: Boolean,
    override val image: HrefOnlyImage? = null
) : GoogleplayBase {

    public companion object Factory : BuilderFactory<EpisodeGoogleplay, EpisodeGoogleplayBuilder> {

        /** Returns a builder implementation for building [EpisodeGoogleplay] model instances. */
        @JvmStatic
        override fun builder(): EpisodeGoogleplayBuilder = ValidatingEpisodeGoogleplayBuilder()
    }
}
