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
public fun List<RssCategory>.asBuilders(): List<RssCategoryBuilder> = map(RssCategory.builder()::from)

/** Transforms this list into a list of [ITunesStyleCategoryBuilder] */
@JvmName("asItunesCategoryBuilders")
public fun List<ITunesStyleCategory>.asBuilders(): List<ITunesStyleCategoryBuilder> = map(ITunesStyleCategory.builder()::from)

/** Transforms this list into a list of [EpisodeEnclosureBuilder] */
@JvmName("asEnclosureBuilders")
public fun List<Episode.Enclosure>.asBuilders(): List<EpisodeEnclosureBuilder> = map(Episode.Enclosure.builder()::from)

/** Transforms this list into a list of [EpisodePodcastSoundbiteBuilder] */
@JvmName("asSoundbiteBuilders")
public fun List<Episode.Podcast.Soundbite>.asBuilders(): List<EpisodePodcastSoundbiteBuilder> = map(Episode.Podcast.Soundbite.builder()::from)

/** Transforms this list into a list of [EpisodePodcastTranscriptBuilder] */
@JvmName("asTranscriptBuilders")
public fun List<Episode.Podcast.Transcript>.asBuilders(): List<EpisodePodcastTranscriptBuilder> =
    map(Episode.Podcast.Transcript.builder()::from)

/** Transforms this list into a list of [PodcastPodcastFundingBuilder] */
@JvmName("asFundingBuilders")
public fun List<Podcast.Podcast.Funding>.asBuilders(): List<PodcastPodcastFundingBuilder> = map(Podcast.Podcast.Funding.builder()::from)

/** Transforms this list into a list of [EpisodePodloveSimpleChapterBuilder] */
@JvmName("asSimpleChapterBuilders")
public fun List<Episode.Podlove.SimpleChapter>.asBuilders(): List<EpisodePodloveSimpleChapterBuilder> =
    map(Episode.Podlove.SimpleChapter.builder()::from)

/** Transforms this list into a list of [EpisodeBuilder] */
@JvmName("asEpisodeBuilders")
public fun List<Episode>.asBuilders(): List<EpisodeBuilder> = map(Episode.builder()::from)
