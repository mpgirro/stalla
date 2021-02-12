package dev.stalla.model.googleplay

import dev.stalla.builder.episode.EpisodeGoogleplayBuilder2
import dev.stalla.builder.validating.episode.ValidatingEpisodeGoogleplayBuilder2
import dev.stalla.model.BuilderFactory
import dev.stalla.model.HrefOnlyImage

/**
 * Model class for data from elements of the Google Play namespace that are valid within `<item>` elements.
 *
 * @property description The `<googleplay:description>` field text content.
 * @property explicit The logical value of the `<googleplay:explicit>` field's text content.
 * @property block The logical value of the `<googleplay:block>` field's text content.
 * @property image The data from the `<googleplay:image>` element as an [HrefOnlyImage].
 */
public data class EpisodeGoogleplay2(
    override val description: String? = null,
    override val explicit: Boolean? = null,
    override val block: Boolean,
    override val image: HrefOnlyImage? = null
) : GoogleplayBase2 {

    public companion object Factory : BuilderFactory<EpisodeGoogleplay2, EpisodeGoogleplayBuilder2> {

        /** Returns a builder implementation for building [EpisodeGoogleplay2] model instances. */
        @JvmStatic
        override fun builder(): EpisodeGoogleplayBuilder2 = ValidatingEpisodeGoogleplayBuilder2()
    }
}
