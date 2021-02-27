package dev.stalla.model.podlove

import dev.stalla.builder.episode.EpisodePodloveSimpleChapterBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodePodloveSimpleChapterBuilder
import dev.stalla.model.BuilderFactory

/**
 * Model class for data from `<psc:chapter>` elements of the Podlove
 * Simple Chapter namespace that are valid within `<item>` elements.
 *
 * @property start The value of the chapter's `start` attribute.
 * @property title The value of the chapter's `title` attribute.
 * @property href The value of the chapter's `href` attribute.
 * @property image The value of the chapter's `image` attribute.
 */
public data class SimpleChapter(
    val start: String,
    val title: String,
    val href: String? = null,
    val image: String? = null
) {

    /** Provides a builder for the [SimpleChapter] class. */
    public companion object Factory : BuilderFactory<SimpleChapter, EpisodePodloveSimpleChapterBuilder> {

        /** Returns a builder implementation for building [SimpleChapter] model instances. */
        @JvmStatic
        override fun builder(): EpisodePodloveSimpleChapterBuilder = ValidatingEpisodePodloveSimpleChapterBuilder()
    }
}
