package dev.stalla.model.bitlove

import dev.stalla.builder.episode.EpisodeBitloveBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodeBitloveBuilder
import dev.stalla.model.BuilderFactory

/**
 * Model class for data from elements of the Bitlove namespace that are valid within `<item>` elements.
 *
 * Use the [builder][Bitlove.Factory.builder] method to obtain an [EpisodeBitloveBuilder] instance
 * for expressive construction in Java.
 *
 * @property guid The `bitlove:guid` attribute for the RSS `<enclosure>` element.
 *
 * @since 1.0.0
 */
public data class Bitlove(
    val guid: String
) {

    /** Provides a builder for the [Bitlove] class. */
    public companion object Factory : BuilderFactory<Bitlove, EpisodeBitloveBuilder> {

        /** Returns a builder implementation for building [Bitlove] model instances. */
        @JvmStatic
        override fun builder(): EpisodeBitloveBuilder = ValidatingEpisodeBitloveBuilder()
    }
}
