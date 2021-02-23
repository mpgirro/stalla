package dev.stalla.model.podlove

import dev.stalla.builder.episode.EpisodePodloveBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodePodloveBuilder
import dev.stalla.model.BuilderFactory

/**
 * Model class for data from elements of namespaces from the Podlove
 * standards family that are valid within `<item>` elements.
 *
 * @property simpleChapters List of data from the `<psc:chapter>` elements as [SimpleChapter] instances.
 */
public data class EpisodePodlove(
    val simpleChapters: List<SimpleChapter>
) {

    public companion object Factory : BuilderFactory<EpisodePodlove, EpisodePodloveBuilder> {

        /** Returns a builder implementation for building [EpisodePodlove] model instances. */
        @JvmStatic
        override fun builder(): EpisodePodloveBuilder = ValidatingEpisodePodloveBuilder()
    }
}
