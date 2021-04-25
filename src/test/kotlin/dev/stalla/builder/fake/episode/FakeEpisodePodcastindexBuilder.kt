package dev.stalla.builder.fake.episode

import dev.stalla.builder.PodcastindexLocationBuilder
import dev.stalla.builder.episode.EpisodePodcastindexBuilder
import dev.stalla.builder.episode.EpisodePodcastindexChaptersBuilder
import dev.stalla.builder.episode.EpisodePodcastindexSoundbiteBuilder
import dev.stalla.builder.episode.EpisodePodcastindexTranscriptBuilder
import dev.stalla.builder.fake.FakeBuilder
import dev.stalla.model.podcastindex.EpisodePodcastindex

internal class FakeEpisodePodcastindexBuilder : FakeBuilder<EpisodePodcastindex>(), EpisodePodcastindexBuilder {

    var chaptersBuilderValue: EpisodePodcastindexChaptersBuilder? = null
    var locationBuilderValue: PodcastindexLocationBuilder? = null
    val transcriptBuilders: MutableList<EpisodePodcastindexTranscriptBuilder> = mutableListOf()
    val soundbiteBuilders: MutableList<EpisodePodcastindexSoundbiteBuilder> = mutableListOf()

    override fun chaptersBuilder(chaptersBuilder: EpisodePodcastindexChaptersBuilder): EpisodePodcastindexBuilder = apply {
        this.chaptersBuilderValue = chaptersBuilder
    }

    override fun addSoundbiteBuilder(soundbiteBuilder: EpisodePodcastindexSoundbiteBuilder): EpisodePodcastindexBuilder = apply {
        soundbiteBuilders.add(soundbiteBuilder)
    }

    override fun addTranscriptBuilder(transcriptBuilder: EpisodePodcastindexTranscriptBuilder): EpisodePodcastindexBuilder = apply {
        transcriptBuilders.add(transcriptBuilder)
    }

    override fun locationBuilder(locationBuilder: PodcastindexLocationBuilder): EpisodePodcastindexBuilder = apply {
        this.locationBuilderValue = locationBuilder
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FakeEpisodePodcastindexBuilder

        if (chaptersBuilderValue != other.chaptersBuilderValue) return false
        if (locationBuilderValue != other.locationBuilderValue) return false
        if (transcriptBuilders != other.transcriptBuilders) return false
        if (soundbiteBuilders != other.soundbiteBuilders) return false

        return true
    }

    override fun hashCode(): Int {
        var result = chaptersBuilderValue?.hashCode() ?: 0
        result = 31 * result + (locationBuilderValue?.hashCode() ?: 0)
        result = 31 * result + transcriptBuilders.hashCode()
        result = 31 * result + soundbiteBuilders.hashCode()
        return result
    }

    override fun toString(): String {
        return "FakeEpisodePodcastindexBuilder(chaptersBuilderValue=$chaptersBuilderValue, locationBuilderValue=$locationBuilderValue, transcriptBuilders=$transcriptBuilders, soundbiteBuilders=$soundbiteBuilders)"
    }
}
