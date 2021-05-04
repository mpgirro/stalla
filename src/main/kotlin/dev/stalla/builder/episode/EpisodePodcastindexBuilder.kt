package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.builder.PodcastindexLocationBuilder
import dev.stalla.builder.PodcastindexPersonBuilder
import dev.stalla.model.podcastindex.Chapters
import dev.stalla.model.podcastindex.EpisodePodcastindex
import dev.stalla.model.podcastindex.PodcastindexEpisode
import dev.stalla.model.podcastindex.PodcastindexLocation
import dev.stalla.model.podcastindex.PodcastindexSeason
import dev.stalla.util.asBuilders
import dev.stalla.util.whenNotNull

/**
 * Builder for constructing [EpisodePodcastindex] instances.
 *
 * @since 1.0.0
 */
public interface EpisodePodcastindexBuilder : Builder<EpisodePodcastindex> {

    /**
     * Set the [EpisodePodcastindexChaptersBuilder] for the Podcastindex namespace `<podcast:chapters>` info.
     */
    public fun chaptersBuilder(chaptersBuilder: EpisodePodcastindexChaptersBuilder): EpisodePodcastindexBuilder

    /**
     * Adds the [EpisodePodcastindexSoundbiteBuilder] for the
     * `<podcast:soundbite>` info to the list of soundbite builders.
     */
    public fun addSoundbiteBuilder(soundbiteBuilder: EpisodePodcastindexSoundbiteBuilder): EpisodePodcastindexBuilder

    /**
     * Adds all of the [EpisodePodcastindexSoundbiteBuilder] for the
     * `<podcast:soundbite>` info to the list of soundbite builders.
     */
    public fun addAllSoundbiteBuilders(
        soundbiteBuilders: List<EpisodePodcastindexSoundbiteBuilder>
    ): EpisodePodcastindexBuilder = apply {
        soundbiteBuilders.forEach(::addSoundbiteBuilder)
    }

    /**
     * Adds the [EpisodePodcastindexTranscriptBuilder] for the
     * `<podcast:transcript>` info to the list of transcript builders.
     */
    public fun addTranscriptBuilder(
        transcriptBuilder: EpisodePodcastindexTranscriptBuilder
    ): EpisodePodcastindexBuilder

    /**
     * Adds all of the [EpisodePodcastindexTranscriptBuilder] for the
     * `<podcast:transcript>` info to the list of transcript builders.
     */
    public fun addAllTranscriptBuilders(
        transcriptBuilders: List<EpisodePodcastindexTranscriptBuilder>
    ): EpisodePodcastindexBuilder = apply {
        transcriptBuilders.forEach(::addTranscriptBuilder)
    }

    /**
     * Adds the [PodcastindexPersonBuilder] for the
     * `<podcast:person>` info to the list of person builders.
     */
    public fun addPersonBuilder(personBuilder: PodcastindexPersonBuilder): EpisodePodcastindexBuilder

    /**
     * Adds all of the [PodcastindexPersonBuilder] for the
     * `<podcast:person>` info to the list of person builders.
     */
    public fun addAllPersonBuilders(
        personBuilders: List<PodcastindexPersonBuilder>
    ): EpisodePodcastindexBuilder = apply {
        personBuilders.forEach(::addPersonBuilder)
    }

    /**
     * Set the [PodcastindexLocationBuilder] for the Podcastindex namespace `<podcast:location>` info.
     */
    public fun locationBuilder(locationBuilder: PodcastindexLocationBuilder): EpisodePodcastindexBuilder

    /**
     * Set the [EpisodePodcastindexSeasonBuilder] for the Podcastindex namespace `<podcast:season>` info.
     */
    public fun seasonBuilder(seasonBuilder: EpisodePodcastindexSeasonBuilder): EpisodePodcastindexBuilder

    /**
     * Set the [EpisodePodcastindexEpisodeBuilder] for the Podcastindex namespace `<podcast:episode>` info.
     */
    public fun episodeBuilder(episodeBuilder: EpisodePodcastindexEpisodeBuilder): EpisodePodcastindexBuilder

    override fun applyFrom(prototype: EpisodePodcastindex?): EpisodePodcastindexBuilder =
        whenNotNull(prototype) { podcast ->
            chaptersBuilder(Chapters.builder().applyFrom(podcast.chapters))
            addAllSoundbiteBuilders(podcast.soundbites.asBuilders())
            addAllTranscriptBuilders(podcast.transcripts.asBuilders())
            addAllPersonBuilders(podcast.persons.asBuilders())
            locationBuilder(PodcastindexLocation.builder().applyFrom(podcast.location))
            seasonBuilder(PodcastindexSeason.builder().applyFrom(podcast.season))
            episodeBuilder(PodcastindexEpisode.builder().applyFrom(podcast.episode))
        }
}
