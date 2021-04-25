package dev.stalla.builder.validating.episode

import dev.stalla.builder.PodcastindexLocationBuilder
import dev.stalla.builder.episode.EpisodePodcastindexBuilder
import dev.stalla.builder.episode.EpisodePodcastindexChaptersBuilder
import dev.stalla.builder.episode.EpisodePodcastindexSoundbiteBuilder
import dev.stalla.builder.episode.EpisodePodcastindexTranscriptBuilder
import dev.stalla.model.podcastindex.EpisodePodcastindex
import dev.stalla.util.InternalAPI
import dev.stalla.util.asUnmodifiable

@InternalAPI
internal class ValidatingEpisodePodcastindexBuilder : EpisodePodcastindexBuilder {

    private var chaptersBuilderValue: EpisodePodcastindexChaptersBuilder? = null
    private var locationBuilderValue: PodcastindexLocationBuilder? = null
    private val transcriptBuilders: MutableList<EpisodePodcastindexTranscriptBuilder> = mutableListOf()
    private val soundbiteBuilders: MutableList<EpisodePodcastindexSoundbiteBuilder> = mutableListOf()

    override fun chaptersBuilder(chaptersBuilder: EpisodePodcastindexChaptersBuilder): EpisodePodcastindexBuilder =
        apply { this.chaptersBuilderValue = chaptersBuilder }

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

    override fun locationBuilder(locationBuilder: PodcastindexLocationBuilder): EpisodePodcastindexBuilder =
        apply { this.locationBuilderValue = locationBuilder }

    override val hasEnoughDataToBuild: Boolean
        get() = chaptersBuilderValue?.hasEnoughDataToBuild == true ||
            transcriptBuilders.any { it.hasEnoughDataToBuild } ||
            soundbiteBuilders.any { it.hasEnoughDataToBuild } ||
            locationBuilderValue?.hasEnoughDataToBuild == true

    override fun build(): EpisodePodcastindex? {
        if (!hasEnoughDataToBuild) return null

        return EpisodePodcastindex(
            transcripts = transcriptBuilders.mapNotNull { it.build() }.asUnmodifiable(),
            soundbites = soundbiteBuilders.mapNotNull { it.build() }.asUnmodifiable(),
            chapters = chaptersBuilderValue?.build(),
            location = locationBuilderValue?.build()
        )
    }
}
