package io.hemin.wien.builder.fake.episode

import io.hemin.wien.builder.episode.EpisodePodcastBuilder
import io.hemin.wien.builder.episode.EpisodePodcastChaptersBuilder
import io.hemin.wien.builder.episode.EpisodePodcastSoundbiteBuilder
import io.hemin.wien.builder.episode.EpisodePodcastTranscriptBuilder
import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.model.Episode

internal class FakeEpisodePodcastBuilder : FakeBuilder<Episode.Podcast>(), EpisodePodcastBuilder {

    var chaptersBuilderValue: EpisodePodcastChaptersBuilder? = null

    val transcriptBuilders: MutableList<EpisodePodcastTranscriptBuilder> = mutableListOf()
    val soundbiteBuilders: MutableList<EpisodePodcastSoundbiteBuilder> = mutableListOf()

    override fun chaptersBuilder(chaptersBuilder: EpisodePodcastChaptersBuilder): EpisodePodcastBuilder = apply {
        this.chaptersBuilderValue = chaptersBuilder
    }

    override fun addSoundbiteBuilder(soundbiteBuilder: EpisodePodcastSoundbiteBuilder): EpisodePodcastBuilder = apply {
        soundbiteBuilders.add(soundbiteBuilder)
    }

    override fun addTranscriptBuilder(transcriptBuilder: EpisodePodcastTranscriptBuilder): EpisodePodcastBuilder = apply {
        transcriptBuilders.add(transcriptBuilder)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakeEpisodePodcastBuilder) return false

        if (chaptersBuilderValue != other.chaptersBuilderValue) return false
        if (transcriptBuilders != other.transcriptBuilders) return false
        if (soundbiteBuilders != other.soundbiteBuilders) return false

        return true
    }

    override fun hashCode(): Int {
        var result = chaptersBuilderValue?.hashCode() ?: 0
        result = 31 * result + transcriptBuilders.hashCode()
        result = 31 * result + soundbiteBuilders.hashCode()
        return result
    }

    override fun toString() =
        "FakeEpisodePodcastBuilder(chaptersBuilder=$chaptersBuilderValue, transcriptBuilders=$transcriptBuilders, soundbiteBuilders=$soundbiteBuilders)"
}
