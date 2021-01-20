package io.hemin.wien.model.episode

import io.hemin.wien.dateTime
import io.hemin.wien.model.Atom
import io.hemin.wien.model.Episode
import io.hemin.wien.model.HrefOnlyImage
import io.hemin.wien.model.Link
import io.hemin.wien.model.Person
import io.hemin.wien.model.RssCategory
import io.hemin.wien.model.aLink
import io.hemin.wien.model.aPerson
import io.hemin.wien.model.anHrefOnlyImage
import io.hemin.wien.model.anRssCategory
import java.time.Duration
import java.time.Month
import java.time.temporal.TemporalAccessor
import java.util.Locale

internal fun anEpisode(
    title: String = "episode title",
    link: String? = "episode link",
    description: String? = "episode description",
    author: String? = "episode author",
    categories: List<RssCategory> = listOf(anRssCategory("episode category")),
    comments: String? = "episode comments",
    enclosure: Episode.Enclosure = anEpisodeEnclosure(),
    guid: Episode.Guid? = anEpisodeGuid(),
    pubDate: TemporalAccessor? = dateTime(year = 2020, month = Month.DECEMBER, day = 20, hour = 12, minute = 11, second = 10),
    source: String? = "episode source",
    content: Episode.Content? = anEpisodeContent(),
    iTunes: Episode.ITunes? = anEpisodeITunes(),
    atom: Atom? = anEpisodeAtom(),
    podlove: Episode.Podlove? = anEpisodePodlove(),
    googlePlay: Episode.GooglePlay? = anEpisodeGooglePlay(),
    bitlove: Episode.Bitlove? = anEpisodeBitlove(),
    podcast: Episode.Podcast? = anEpisodePodcast()
) = Episode(
    title,
    link,
    description,
    author,
    categories,
    comments,
    enclosure,
    guid,
    pubDate,
    source,
    content,
    iTunes,
    atom,
    podlove,
    googlePlay,
    bitlove,
    podcast
)

internal fun anEpisodeEnclosure(
    url: String = "episode enclosure url",
    length: Long = 777,
    type: String = "episode enclosure type"
) = Episode.Enclosure(url, length, type)

internal fun anEpisodeGuid(
    textContent: String = "episode guid textContent",
    isPermalink: Boolean? = false
) = Episode.Guid(textContent, isPermalink)

internal fun anEpisodeContent(
    encoded: String = "episode content encoded"
) = Episode.Content(encoded)

internal fun anEpisodeITunes(
    title: String? = "episode itunes title",
    duration: String? = "episode itunes duration",
    image: HrefOnlyImage? = anHrefOnlyImage(href = "episode itunes image url"),
    explicit: Boolean? = true,
    block: Boolean? = true,
    season: Int? = 2,
    episode: Int? = 3,
    episodeType: Episode.ITunes.EpisodeType? = Episode.ITunes.EpisodeType.FULL,
    author: String? = "episode itunes author",
    subtitle: String? = "episode itunes subtitle",
    summary: String? = "episode itunes summary"
) = Episode.ITunes(title, duration, image, explicit, block, season, episode, episodeType, author, subtitle, summary)

internal fun anEpisodeAtom(
    authors: List<Person> = listOf(aPerson("episode atom author name")),
    contributors: List<Person> = listOf(aPerson("episode atom contributor name")),
    links: List<Link> = listOf(aLink("episode atom link href"))
) = Atom(authors, contributors, links)

internal fun anEpisodePodlove(
    simpleChapters: List<Episode.Podlove.SimpleChapter> = listOf(aPodloveSimpleChapter())
) = Episode.Podlove(simpleChapters)

internal fun aPodloveSimpleChapter(
    start: String = "episode podlove simple chapter start",
    title: String = "episode podlove simple chapter title",
    href: String? = "episode podlove simple chapter href",
    image: String? = "episode podlove simple chapter image"
) = Episode.Podlove.SimpleChapter(start, title, href, image)

internal fun anEpisodeGooglePlay(
    description: String? = "episode googleplay description",
    explicit: Boolean? = true,
    block: Boolean? = true,
    image: HrefOnlyImage? = anHrefOnlyImage(href = "episode googleplay image url")
) = Episode.GooglePlay(description, explicit, block, image)

internal fun anEpisodeBitlove(
    guid: String = "episode bitlove guid"
) = Episode.Bitlove(guid)

internal fun anEpisodePodcast(
    transcripts: List<Episode.Podcast.Transcript> = listOf(anEpisodePodcastTranscript()),
    soundbites: List<Episode.Podcast.Soundbite> = listOf(anEpisodePodcastSoundbite()),
    chapters: Episode.Podcast.Chapters? = anEpisodePodcastChapters()
) = Episode.Podcast(transcripts, soundbites, chapters)

fun anEpisodePodcastTranscript(
    url: String = "episode podcast: transcript url",
    type: Episode.Podcast.Transcript.Type = Episode.Podcast.Transcript.Type.SRT,
    language: Locale? = Locale.ITALY,
    rel: String? = "captions"
) = Episode.Podcast.Transcript(url, type, language, rel)

fun anEpisodePodcastSoundbite(
    startTime: Duration = Duration.ofSeconds(1),
    duration: Duration = Duration.ofSeconds(15).plusMillis(123),
    title: String? = "episode podcast: soundbite title"
) = Episode.Podcast.Soundbite(startTime, duration, title)

internal fun anEpisodePodcastChapters(
    url: String = "episode podcast: chapters url",
    type: String = "episode podcast: chapters type"
) = Episode.Podcast.Chapters(url, type)
