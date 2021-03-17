package dev.stalla.util

import dev.stalla.builder.RssCategoryBuilder
import dev.stalla.builder.episode.EpisodeBuilder
import dev.stalla.builder.episode.EpisodeEnclosureBuilder
import dev.stalla.builder.episode.EpisodePodcastindexSoundbiteBuilder
import dev.stalla.builder.episode.EpisodePodcastindexTranscriptBuilder
import dev.stalla.builder.episode.EpisodePodloveSimpleChapterBuilder
import dev.stalla.builder.podcast.PodcastPodcastindexFundingBuilder
import dev.stalla.model.Episode
import dev.stalla.model.podcastindex.Funding
import dev.stalla.model.podcastindex.Soundbite
import dev.stalla.model.podcastindex.Transcript
import dev.stalla.model.podlove.SimpleChapter
import dev.stalla.model.rss.Enclosure
import dev.stalla.model.rss.RssCategory
import java.util.Collections

/** Transforms this list into a list of [RssCategoryBuilder]. */
@InternalAPI2
@JvmName("asRssCategoryBuilders")
internal fun List<RssCategory>.asBuilders(): List<RssCategoryBuilder> = map(RssCategory.builder()::applyFrom)

/** Transforms this list into a list of [EpisodeEnclosureBuilder]. */
@InternalAPI2
@JvmName("asEnclosureBuilders")
internal fun List<Enclosure>.asBuilders(): List<EpisodeEnclosureBuilder> = map(Enclosure.builder()::applyFrom)

/** Transforms this list into a list of [EpisodePodcastindexSoundbiteBuilder]. */
@InternalAPI2
@JvmName("asSoundbiteBuilders")
internal fun List<Soundbite>.asBuilders(): List<EpisodePodcastindexSoundbiteBuilder> =
    map(Soundbite.builder()::applyFrom)

/** Transforms this list into a list of [EpisodePodcastindexTranscriptBuilder]. */
@InternalAPI2
@JvmName("asTranscriptBuilders")
internal fun List<Transcript>.asBuilders(): List<EpisodePodcastindexTranscriptBuilder> =
    map(Transcript.builder()::applyFrom)

/** Transforms this list into a list of [PodcastPodcastindexFundingBuilder]. */
@InternalAPI2
@JvmName("asFundingBuilders")
internal fun List<Funding>.asBuilders(): List<PodcastPodcastindexFundingBuilder> = map(Funding.builder()::applyFrom)

/** Transforms this list into a list of [EpisodePodloveSimpleChapterBuilder]. */
@InternalAPI2
@JvmName("asSimpleChapterBuilders")
internal fun List<SimpleChapter>.asBuilders(): List<EpisodePodloveSimpleChapterBuilder> =
    map(SimpleChapter.builder()::applyFrom)

/** Transforms this list into a list of [EpisodeBuilder]. */
@InternalAPI2
@JvmName("asEpisodeBuilders")
internal fun List<Episode>.asBuilders(): List<EpisodeBuilder> = map(Episode.builder()::applyFrom)

/** Returns an unmodifiable view of this list. */
@InternalAPI2
internal fun <T> List<T>.asUnmodifiable(): List<T> = Collections.unmodifiableList(this)
