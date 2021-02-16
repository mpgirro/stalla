package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.model.content.Content
import dev.stalla.util.whenNotNull

/** Builder for constructing [Content] instances. */
public interface EpisodeContentBuilder : Builder<Content> {

    /** Set the encoded value. */
    public fun encoded(encoded: String): EpisodeContentBuilder

    override fun applyFrom(prototype: Content?): EpisodeContentBuilder = whenNotNull(prototype) { content ->
        encoded(content.encoded)
    }
}
