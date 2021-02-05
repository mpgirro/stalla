package io.hemin.wien.util

import io.hemin.wien.builder.ITunesStyleCategoryBuilder
import io.hemin.wien.builder.RssCategoryBuilder
import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.episode.EpisodeEnclosureBuilder
import io.hemin.wien.builder.episode.EpisodePodcastSoundbiteBuilder
import io.hemin.wien.builder.episode.EpisodePodcastTranscriptBuilder
import io.hemin.wien.builder.episode.EpisodePodloveSimpleChapterBuilder
import io.hemin.wien.builder.podcast.PodcastPodcastFundingBuilder
import io.hemin.wien.model.Episode
import io.hemin.wien.model.ITunesStyleCategory
import io.hemin.wien.model.Podcast
import io.hemin.wien.model.RssCategory

/** Transforms this list into a list of [RssCategoryBuilder] */
@JvmName("asRssCategoryBuilders")
internal fun List<RssCategory>.asBuilders(): List<RssCategoryBuilder> = this.map(RssCategory.builder()::from)

/** Transforms this list into a list of [ITunesStyleCategoryBuilder] */
@JvmName("asItunesCategoryBuilders")
internal fun List<ITunesStyleCategory>.asBuilders(): List<ITunesStyleCategoryBuilder> = this.map(ITunesStyleCategory.builder()::from)

/** Transforms this list into a list of [EpisodeEnclosureBuilder] */
@JvmName("asEnclosureBuilders")
internal fun List<Episode.Enclosure>.asBuilders(): List<EpisodeEnclosureBuilder> = this.map(Episode.Enclosure.builder()::from)

/** Transforms this list into a list of [EpisodePodcastSoundbiteBuilder] */
@JvmName("asSoundbiteBuilders")
internal fun List<Episode.Podcast.Soundbite>.asBuilders(): List<EpisodePodcastSoundbiteBuilder> = this.map(Episode.Podcast.Soundbite.builder()::from)

/** Transforms this list into a list of [EpisodePodcastTranscriptBuilder] */
@JvmName("asTranscriptBuilders")
internal fun List<Episode.Podcast.Transcript>.asBuilders(): List<EpisodePodcastTranscriptBuilder> =
    this.map(Episode.Podcast.Transcript.builder()::from)

/** Transforms this list into a list of [PodcastPodcastFundingBuilder] */
@JvmName("asFundingBuilders")
internal fun List<Podcast.Podcast.Funding>.asBuilders(): List<PodcastPodcastFundingBuilder> = this.map(Podcast.Podcast.Funding.builder()::from)

/** Transforms this list into a list of [EpisodePodloveSimpleChapterBuilder] */
@JvmName("asSimpleChapterBuilders")
internal fun List<Episode.Podlove.SimpleChapter>.asBuilders(): List<EpisodePodloveSimpleChapterBuilder> =
    this.map(Episode.Podlove.SimpleChapter.builder()::from)

/** Transforms this list into a list of [EpisodeBuilder] */
@JvmName("asEpisodeBuilders")
internal fun List<Episode>.asBuilders(): List<EpisodeBuilder> = this.map(Episode.builder()::from)
