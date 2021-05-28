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
            locationBuilder?.hasEnoughDataToBuild == true ||
            seasonBuilder?.hasEnoughDataToBuild == true ||
            episodeBuilder?.hasEnoughDataToBuild == true ||
            transcriptBuilders.any { it.hasEnoughDataToBuild } ||
            soundbiteBuilders.any { it.hasEnoughDataToBuild } ||
            personBuilders.any { it.hasEnoughDataToBuild }

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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ValidatingEpisodePodcastindexBuilder) return false

        if (chaptersBuilder != other.chaptersBuilder) return false
        if (locationBuilder != other.locationBuilder) return false
        if (seasonBuilder != other.seasonBuilder) return false
        if (episodeBuilder != other.episodeBuilder) return false
        if (transcriptBuilders != other.transcriptBuilders) return false
        if (soundbiteBuilders != other.soundbiteBuilders) return false
        if (personBuilders != other.personBuilders) return false

        return true
    }

    override fun hashCode(): Int {
        var result = chaptersBuilder?.hashCode() ?: 0
        result = 31 * result + (locationBuilder?.hashCode() ?: 0)
        result = 31 * result + (seasonBuilder?.hashCode() ?: 0)
        result = 31 * result + (episodeBuilder?.hashCode() ?: 0)
        result = 31 * result + transcriptBuilders.hashCode()
        result = 31 * result + soundbiteBuilders.hashCode()
        result = 31 * result + personBuilders.hashCode()
        return result
    }

    override fun toString(): String =
        "ValidatingEpisodePodcastindexBuilder(chaptersBuilder=$chaptersBuilder, locationBuilder=$locationBuilder, seasonBuilder=$seasonBuilder, " +
            "episodeBuilder=$episodeBuilder, transcriptBuilders=$transcriptBuilders, soundbiteBuilders=$soundbiteBuilders, " +
            "personBuilders=$personBuilders)"
}
