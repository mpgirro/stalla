package dev.stalla.model.podcastns

import dev.stalla.builder.episode.EpisodePodcastChaptersBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodePodcastChaptersBuilder
import dev.stalla.model.BuilderFactory

/**
 * The chapters information for the episode. See the
 * [reference docs](https://github.com/Podcastindex-org/podcast-namespace/blob/main/docs/1.0.md#chapters).
 *
 * @param url The URL for the chapters information.
 * @param type The MIME type of the chapters file. JSON (`application/json+chapters`) is the preferred format.
 */
public data class Chapters(
    val url: String,
    val type: String
) {

    public companion object Factory : BuilderFactory<Chapters, EpisodePodcastChaptersBuilder> {

        /** Returns a builder implementation for building [Chapters] model instances. */
        @JvmStatic
        override fun builder(): EpisodePodcastChaptersBuilder = ValidatingEpisodePodcastChaptersBuilder()
    }
}
