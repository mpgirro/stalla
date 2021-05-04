package dev.stalla.builder.validating.episode

import dev.stalla.builder.PodcastindexLocationBuilder
import dev.stalla.builder.PodcastindexPersonBuilder
import dev.stalla.builder.episode.EpisodePodcastindexBuilder
import dev.stalla.builder.episode.EpisodePodcastindexChaptersBuilder
import dev.stalla.builder.episode.EpisodePodcastindexEpisodeBuilder
import dev.stalla.builder.episode.EpisodePodcastindexSeasonBuilder
import dev.stalla.builder.episode.EpisodePodcastindexSoundbiteBuilder
import dev.stalla.builder.episode.EpisodePodcastindexTranscriptBuilder
import dev.stalla.model.podcastindex.EpisodePodcastindex
import dev.stalla.util.InternalAPI
import dev.stalla.util.asUnmodifiable

@InternalAPI
internal class ValidatingEpisodePodcastindexBuilder : EpisodePodcastindexBuilder {

    private var chaptersBuilder: EpisodePodcastindexChaptersBuilder? = null
    private var locationBuilder: PodcastindexLocationBuilder? = null
    private var seasonBuilder: EpisodePodcastindexSeasonBuilder? = null
    private var episodeBuilder: EpisodePodcastindexEpisodeBuilder? = null
    private val transcriptBuilders: MutableList<EpisodePodcastindexTranscriptBuilder> = mutableListOf()
    private val soundbiteBuilders: MutableList<EpisodePodcastindexSoundbiteBuilder> = mutableListOf()
    private val personBuilders: MutableList<PodcastindexPersonBuilder> = mutableListOf()

    override fun chaptersBuilder(chaptersBuilder: EpisodePodcastindexChaptersBuilder): EpisodePodcastindexBuilder =
        apply { this.chaptersBuilder = chaptersBuilder }

    override fun addSoundbiteBuilder(
        soundbiteBuilder: EpisodePodcastindexSoundbiteBuilder
    ): EpisodePodcastindexBuilder = apply {
        soundbiteBuilders.add(soundbiteBuilder)
    }

    override fun addTranscriptBuilder(
        transcriptBuilder: EpisodePodcastindexTranscriptBuilder
    ): EpisodePodcastindexBuilder = apply {
        transcriptBuilders.add(transcriptBuilder)
    }

    override fun addPersonBuilder(personBuilder: PodcastindexPersonBuilder): EpisodePodcastindexBuilder =
        apply { this.personBuilders.add(personBuilder) }

    override fun locationBuilder(locationBuilder: PodcastindexLocationBuilder): EpisodePodcastindexBuilder =
        apply { this.locationBuilder = locationBuilder }

    override fun seasonBuilder(seasonBuilder: EpisodePodcastindexSeasonBuilder): EpisodePodcastindexBuilder =
        apply { this.seasonBuilder = seasonBuilder }

    override fun episodeBuilder(episodeBuilder: EpisodePodcastindexEpisodeBuilder): EpisodePodcastindexBuilder =
        apply { this.episodeBuilder = episodeBuilder }

    override val hasEnoughDataToBuild: Boolean
        get() = chaptersBuilder?.hasEnoughDataToBuild == true ||
            transcriptBuilders.any { it.hasEnoughDataToBuild } ||
            soundbiteBuilders.any { it.hasEnoughDataToBuild } ||
            personBuilders.any { it.hasEnoughDataToBuild } ||
            locationBuilder?.hasEnoughDataToBuild == true ||
            seasonBuilder?.hasEnoughDataToBuild == true ||
            episodeBuilder?.hasEnoughDataToBuild == true

    override fun build(): EpisodePodcastindex? {
        if (!hasEnoughDataToBuild) return null

        return EpisodePodcastindex(
            transcripts = transcriptBuilders.mapNotNull { it.build() }.asUnmodifiable(),
            soundbites = soundbiteBuilders.mapNotNull { it.build() }.asUnmodifiable(),
            chapters = chaptersBuilder?.build(),
            persons = personBuilders.mapNotNull { it.build() }.asUnmodifiable(),
            location = locationBuilder?.build(),
            season = seasonBuilder?.build(),
            episode = episodeBuilder?.build()
        )
    }
}
