package dev.stalla.model.bitlove

import dev.stalla.builder.episode.EpisodeBitloveBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodeBitloveBuilder
import dev.stalla.model.BuilderFactory

/**
 * Model class for data from elements of the Bitlove namespace that are valid within `<item>` elements.
 *
 * @property guid The GUID attribute for the RSS enclosure element.
 */
public data class Bitlove(
    val guid: String
) {

    public companion object Factory : BuilderFactory<Bitlove, EpisodeBitloveBuilder> {

        /** Returns a builder implementation for building [Bitlove] model instances. */
        @JvmStatic
        override fun builder(): EpisodeBitloveBuilder = ValidatingEpisodeBitloveBuilder()
    }
}
