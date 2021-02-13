package dev.stalla.util

import dev.stalla.builder.GoogleplayCategoryBuilder
import dev.stalla.builder.RssCategoryBuilder
import dev.stalla.builder.episode.EpisodeBuilder
import dev.stalla.builder.episode.EpisodeEnclosureBuilder
import dev.stalla.builder.episode.EpisodePodcastindexSoundbiteBuilder
import dev.stalla.builder.episode.EpisodePodcastindexTranscriptBuilder
import dev.stalla.builder.episode.EpisodePodloveSimpleChapterBuilder
import dev.stalla.builder.podcast.PodcastPodcastindexFundingBuilder
import dev.stalla.model.Episode
import dev.stalla.model.googleplay.GoogleplayCategory
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

/** Transforms this list into a list of [ItunesCategoryBuilder] */
//@InternalApi
//@JvmName("asItunesCategoryBuilders")
//internal fun List<ItunesCategory>.asBuilders(): List<ItunesCategoryBuilder> = map(ItunesCategory.builder()::from)

/** Transforms this list into a list of [GoogleplayCategoryBuilder] */
@InternalApi
@JvmName("asGoogleplayCategoryBuilders")
internal fun List<GoogleplayCategory>.asBuilders(): List<GoogleplayCategoryBuilder> = map(GoogleplayCategory.builder()::from)

/** Transforms this list into a list of [EpisodeEnclosureBuilder] */
@InternalApi
@JvmName("asEnclosureBuilders")
internal fun List<Enclosure>.asBuilders(): List<EpisodeEnclosureBuilder> = map(Enclosure.builder()::from)

/** Transforms this list into a list of [EpisodePodcastindexSoundbiteBuilder] */
@InternalApi
@JvmName("asSoundbiteBuilders")
internal fun List<Soundbite>.asBuilders(): List<EpisodePodcastindexSoundbiteBuilder> = map(Soundbite.builder()::from)

/** Transforms this list into a list of [EpisodePodcastindexTranscriptBuilder] */
@InternalApi
@JvmName("asTranscriptBuilders")
internal fun List<Transcript>.asBuilders(): List<EpisodePodcastindexTranscriptBuilder> =
    map(Transcript.builder()::from)

/** Transforms this list into a list of [PodcastPodcastindexFundingBuilder] */
@InternalApi
@JvmName("asFundingBuilders")
internal fun List<Funding>.asBuilders(): List<PodcastPodcastindexFundingBuilder> = map(Funding.builder()::from)

/** Transforms this list into a list of [EpisodePodloveSimpleChapterBuilder] */
@InternalApi
@JvmName("asSimpleChapterBuilders")
internal fun List<SimpleChapter>.asBuilders(): List<EpisodePodloveSimpleChapterBuilder> =
    map(SimpleChapter.builder()::from)

/** Transforms this list into a list of [EpisodeBuilder] */
@InternalApi
@JvmName("asEpisodeBuilders")
internal fun List<Episode>.asBuilders(): List<EpisodeBuilder> = map(Episode.builder()::from)
