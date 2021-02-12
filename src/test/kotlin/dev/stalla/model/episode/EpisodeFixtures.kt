package dev.stalla.model.episode

import dev.stalla.dateTime
import dev.stalla.model.Atom
import dev.stalla.model.Episode
import dev.stalla.model.HrefOnlyImage
import dev.stalla.model.Person
import dev.stalla.model.aLink
import dev.stalla.model.aPerson
import dev.stalla.model.anHrefOnlyImage
import dev.stalla.model.anRssCategory
import dev.stalla.model.atom.Link
import dev.stalla.model.bitlove.Bitlove
import dev.stalla.model.content.Content
import dev.stalla.model.googleplay.EpisodeGoogleplay
import dev.stalla.model.itunes.EpisodeItunes
import dev.stalla.model.itunes.EpisodeType
import dev.stalla.model.podcastns.Chapters
import dev.stalla.model.podcastns.EpisodePodcast
import dev.stalla.model.podcastns.Soundbite
import dev.stalla.model.podcastns.Transcript
import dev.stalla.model.podcastns.TranscriptType
import dev.stalla.model.podlove.EpisodePodlove
import dev.stalla.model.podlove.SimpleChapter
import dev.stalla.model.rss.Enclosure
import dev.stalla.model.rss.Guid
import dev.stalla.model.rss.RssCategory
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
    enclosure: Enclosure = anEpisodeEnclosure(),
    guid: Guid? = anEpisodeGuid(),
    pubDate: TemporalAccessor? = dateTime(year = 2020, month = Month.DECEMBER, day = 20, hour = 12, minute = 11, second = 10),
    source: String? = "episode source",
    content: Content? = anEpisodeContent(),
    itunes: EpisodeItunes? = anEpisodeItunes(),
    atom: Atom? = anEpisodeAtom(),
    podlove: EpisodePodlove? = anEpisodePodlove(),
    googleplay: EpisodeGoogleplay? = anEpisodeGoogleplay(),
    bitlove: Bitlove? = anEpisodeBitlove(),
    podcast: EpisodePodcast? = anEpisodePodcast()
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
    itunes,
    atom,
    podlove,
    googleplay,
    bitlove,
    podcast
)

internal fun anEpisodeEnclosure(
    url: String = "episode enclosure url",
    length: Long = 777,
    type: String = "episode enclosure type"
) = Enclosure(url, length, type)

internal fun anEpisodeGuid(
    textContent: String = "episode guid textContent",
    isPermalink: Boolean? = false
) = Guid(textContent, isPermalink)

internal fun anEpisodeContent(
    encoded: String = "episode content encoded"
) = Content(encoded)

internal fun anEpisodeItunes(
    title: String? = "episode itunes title",
    duration: String? = "episode itunes duration",
    image: HrefOnlyImage? = anHrefOnlyImage(href = "episode itunes image url"),
    explicit: Boolean? = true,
    block: Boolean = true,
    season: Int? = 2,
    episode: Int? = 3,
    episodeType: EpisodeType? = EpisodeType.FULL,
    author: String? = "episode itunes author",
    subtitle: String? = "episode itunes subtitle",
    summary: String? = "episode itunes summary"
) = EpisodeItunes(title, duration, image, explicit, block, season, episode, episodeType, author, subtitle, summary)

internal fun anEpisodeAtom(
    authors: List<Person> = listOf(aPerson("episode atom author name")),
    contributors: List<Person> = listOf(aPerson("episode atom contributor name")),
    links: List<Link> = listOf(aLink("episode atom link href"))
) = Atom(authors, contributors, links)

internal fun anEpisodePodlove(
    simpleChapters: List<SimpleChapter> = listOf(aPodloveSimpleChapter())
) = EpisodePodlove(simpleChapters)

internal fun aPodloveSimpleChapter(
    start: String = "episode podlove simple chapter start",
    title: String = "episode podlove simple chapter title",
    href: String? = "episode podlove simple chapter href",
    image: String? = "episode podlove simple chapter image"
) = SimpleChapter(start, title, href, image)

internal fun anEpisodeGoogleplay(
    description: String? = "episode googleplay description",
    explicit: Boolean? = true,
    block: Boolean = true,
    image: HrefOnlyImage? = anHrefOnlyImage(href = "episode googleplay image url")
) = EpisodeGoogleplay(description, explicit, block, image)

internal fun anEpisodeBitlove(
    guid: String = "episode bitlove guid"
) = Bitlove(guid)

internal fun anEpisodePodcast(
    transcripts: List<Transcript> = listOf(anEpisodePodcastTranscript()),
    soundbites: List<Soundbite> = listOf(anEpisodePodcastSoundbite()),
    chapters: Chapters? = anEpisodePodcastChapters()
) = EpisodePodcast(transcripts, soundbites, chapters)

fun anEpisodePodcastTranscript(
    url: String = "episode podcast: transcript url",
    type: TranscriptType = TranscriptType.SRT,
    language: Locale? = Locale.ITALY,
    rel: String? = "captions"
) = Transcript(url, type, language, rel)

fun anEpisodePodcastSoundbite(
    startTime: Duration = Duration.ofSeconds(1),
    duration: Duration = Duration.ofSeconds(15).plusMillis(123),
    title: String? = "episode podcast: soundbite title"
) = Soundbite(startTime, duration, title)

internal fun anEpisodePodcastChapters(
    url: String = "episode podcast: chapters url",
    type: String = "episode podcast: chapters type"
) = Chapters(url, type)
