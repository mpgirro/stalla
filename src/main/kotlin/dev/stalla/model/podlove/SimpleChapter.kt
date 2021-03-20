package dev.stalla.model.podlove

import dev.stalla.builder.episode.EpisodePodloveSimpleChapterBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodePodloveSimpleChapterBuilder
import dev.stalla.model.BuilderFactory

/**
 * Model class for data from `<psc:chapter>` elements of the Podlove
 * Simple Chapter namespace that are valid within `<item>` elements.
 *
 * Direct instantiation in Java is discouraged. Use the [builder][SimpleChapter.Factory.builder] method
 * to obtain an [EpisodePodloveSimpleChapterBuilder] instance for expressive construction instead.
 *
 * @property start The value of the chapter's `start` attribute text content.
 * @property title The value of the chapter's `title` attribute text content.
 * @property href The value of the chapter's `href` attribute text content.
 * @property image The value of the chapter's `image` attribute text content.
 *
 * @since 1.0.0
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
