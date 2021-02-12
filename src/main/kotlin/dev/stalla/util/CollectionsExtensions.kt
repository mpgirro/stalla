package dev.stalla.util

import dev.stalla.builder.ItunesStyleCategoryBuilder
import dev.stalla.builder.RssCategoryBuilder
import dev.stalla.builder.episode.EpisodeBuilder
import dev.stalla.builder.episode.EpisodeEnclosureBuilder
import dev.stalla.builder.episode.EpisodePodcastSoundbiteBuilder
import dev.stalla.builder.episode.EpisodePodcastTranscriptBuilder
import dev.stalla.builder.episode.EpisodePodloveSimpleChapterBuilder
import dev.stalla.builder.podcast.PodcastPodcastFundingBuilder
import dev.stalla.model.Episode
import dev.stalla.model.itunes.ItunesStyleCategory
import dev.stalla.model.podcastindex.Funding
import dev.stalla.model.podcastindex.Soundbite
import dev.stalla.model.podcastindex.Transcript
import dev.stalla.model.podlove.SimpleChapter
import dev.stalla.model.rss.Enclosure
import dev.stalla.model.rss.RssCategory

/** Transforms this list into a list of [RssCategoryBuilder] */
@InternalApi
@JvmName("asRssCategoryBuilders")
internal fun List<RssCategory>.asBuilders(): List<RssCategoryBuilder> = map(RssCategory.builder()::from)

/** Transforms this list into a list of [ItunesStyleCategoryBuilder] */
@InternalApi
@JvmName("asItunesCategoryBuilders")
internal fun List<ItunesStyleCategory>.asBuilders(): List<ItunesStyleCategoryBuilder> = map(ItunesStyleCategory.builder()::from)

/** Transforms this list into a list of [EpisodeEnclosureBuilder] */
@InternalApi
@JvmName("asEnclosureBuilders")
internal fun List<Enclosure>.asBuilders(): List<EpisodeEnclosureBuilder> = map(Enclosure.builder()::from)

/** Transforms this list into a list of [EpisodePodcastSoundbiteBuilder] */
@InternalApi
@JvmName("asSoundbiteBuilders")
internal fun List<Soundbite>.asBuilders(): List<EpisodePodcastSoundbiteBuilder> = map(Soundbite.builder()::from)

/** Transforms this list into a list of [EpisodePodcastTranscriptBuilder] */
@InternalApi
@JvmName("asTranscriptBuilders")
internal fun List<Transcript>.asBuilders(): List<EpisodePodcastTranscriptBuilder> =
    map(Transcript.builder()::from)

/** Transforms this list into a list of [PodcastPodcastFundingBuilder] */
@InternalApi
@JvmName("asFundingBuilders")
internal fun List<Funding>.asBuilders(): List<PodcastPodcastFundingBuilder> = map(Funding.builder()::from)

/** Transforms this list into a list of [EpisodePodloveSimpleChapterBuilder] */
@InternalApi
@JvmName("asSimpleChapterBuilders")
internal fun List<SimpleChapter>.asBuilders(): List<EpisodePodloveSimpleChapterBuilder> =
    map(SimpleChapter.builder()::from)

/** Transforms this list into a list of [EpisodeBuilder] */
@InternalApi
@JvmName("asEpisodeBuilders")
internal fun List<Episode>.asBuilders(): List<EpisodeBuilder> = map(Episode.builder()::from)
