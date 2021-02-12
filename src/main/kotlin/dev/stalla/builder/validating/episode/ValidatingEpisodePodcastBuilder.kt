package dev.stalla.builder.validating.episode

import dev.stalla.builder.episode.EpisodePodcastBuilder
import dev.stalla.builder.episode.EpisodePodcastChaptersBuilder
import dev.stalla.builder.episode.EpisodePodcastSoundbiteBuilder
import dev.stalla.builder.episode.EpisodePodcastTranscriptBuilder
import dev.stalla.model.podcastns.EpisodePodcast

internal class ValidatingEpisodePodcastBuilder : EpisodePodcastBuilder {

    private var chaptersBuilderValue: EpisodePodcastChaptersBuilder? = null
    private val transcriptBuilders: MutableList<EpisodePodcastTranscriptBuilder> = mutableListOf()
    private val soundbiteBuilders: MutableList<EpisodePodcastSoundbiteBuilder> = mutableListOf()

    override fun chaptersBuilder(chaptersBuilder: EpisodePodcastChaptersBuilder): EpisodePodcastBuilder = apply {
        this.chaptersBuilderValue = chaptersBuilder
    }

    override fun addSoundbiteBuilder(soundbiteBuilder: EpisodePodcastSoundbiteBuilder): EpisodePodcastBuilder = apply {
        soundbiteBuilders.add(soundbiteBuilder)
    }

    override fun addTranscriptBuilder(transcriptBuilder: EpisodePodcastTranscriptBuilder): EpisodePodcastBuilder = apply {
        transcriptBuilders.add(transcriptBuilder)
    }

    override val hasEnoughDataToBuild: Boolean
        get() = chaptersBuilderValue?.hasEnoughDataToBuild == true ||
            transcriptBuilders.any { it.hasEnoughDataToBuild } ||
            soundbiteBuilders.any { it.hasEnoughDataToBuild }

    override fun build(): EpisodePodcast? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return EpisodePodcast(
            transcripts = transcriptBuilders.mapNotNull { it.build() },
            soundbites = soundbiteBuilders.mapNotNull { it.build() },
            chapters = chaptersBuilderValue?.build()
        )
    }
}
