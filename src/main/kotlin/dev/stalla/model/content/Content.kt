package dev.stalla.model.content

import dev.stalla.builder.episode.EpisodeContentBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodeContentBuilder
import dev.stalla.model.BuilderFactory

/**
 * Model class for data from elements of the Content namespace that are valid within `<item>` elements.
 *
 * @property encoded The text content of the `<content:encoded>` element.
 */
public data class Content(
    val encoded: String
) {

    /** Provides a builder for the [Content] class. */
    public companion object Factory : BuilderFactory<Content, EpisodeContentBuilder> {

        /** Returns a builder implementation for building [Content] model instances. */
        @JvmStatic
        override fun builder(): EpisodeContentBuilder = ValidatingEpisodeContentBuilder()
    }
}
