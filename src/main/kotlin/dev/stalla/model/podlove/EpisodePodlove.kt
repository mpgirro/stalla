package dev.stalla.model.podlove

import dev.stalla.builder.episode.EpisodePodloveBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodePodloveBuilder
import dev.stalla.model.BuilderFactory
import dev.stalla.model.atom.Atom.Factory.builder
import dev.stalla.model.atom.AtomPerson.Factory.builder
import dev.stalla.model.podlove.EpisodePodlove.Factory.builder

/**
 * Model class for data from elements of namespaces from the Podlove
 * standards family that are valid within `<item>` elements.
 *
 * Direct instantiation from Java is discouraged. Use the [builder] method
 * to obtain a builder instance for expressive construction instead.
 *
 * @property simpleChapters List of data from the `<psc:chapter>` elements as [SimpleChapter] instances.
 *
 * @since 1.0.0
 */
public data class EpisodePodlove(
    val simpleChapters: List<SimpleChapter>
) {

    /** Provides a builder for the [EpisodePodlove] class. */
    public companion object Factory : BuilderFactory<EpisodePodlove, EpisodePodloveBuilder> {

        /** Returns a builder implementation for building [EpisodePodlove] model instances. */
        @JvmStatic
        override fun builder(): EpisodePodloveBuilder = ValidatingEpisodePodloveBuilder()
    }
}
