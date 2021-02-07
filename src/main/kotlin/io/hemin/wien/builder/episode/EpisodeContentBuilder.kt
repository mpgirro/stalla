package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Episode
import io.hemin.wien.util.whenNotNull

/** Builder for constructing [Episode.Content] instances. */
public interface EpisodeContentBuilder : Builder<Episode.Content> {

    /** Set the encoded value. */
    public fun encoded(encoded: String): EpisodeContentBuilder

    override fun from(model: Episode.Content?): EpisodeContentBuilder = whenNotNull(model) { content ->
        encoded(content.encoded)
    }
}
