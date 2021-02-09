package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.model.Episode
import dev.stalla.util.whenNotNull

/** Builder for constructing [Episode.Content] instances. */
public interface EpisodeContentBuilder : Builder<Episode.Content> {

    /** Set the encoded value. */
    public fun encoded(encoded: String): EpisodeContentBuilder

    override fun from(model: Episode.Content?): EpisodeContentBuilder = whenNotNull(model) { content ->
        encoded(content.encoded)
    }
}
