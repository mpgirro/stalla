package io.hemin.wien.builder.validating.episode

import io.hemin.wien.builder.episode.EpisodePodcastBuilder
import io.hemin.wien.builder.episode.EpisodePodcastChaptersBuilder
import io.hemin.wien.builder.episode.EpisodePodcastSoundbiteBuilder
import io.hemin.wien.builder.episode.EpisodePodcastTranscriptBuilder
import io.hemin.wien.model.Episode

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

    override fun build(): Episode.Podcast? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Episode.Podcast(
            transcripts = transcriptBuilders.mapNotNull { it.build() },
            soundbites = soundbiteBuilders.mapNotNull { it.build() },
            chapters = chaptersBuilderValue?.build()
        )
    }
}
