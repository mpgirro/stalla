package dev.stalla.model

import dev.stalla.dateTime
import dev.stalla.model.atom.Atom
import dev.stalla.model.atom.AtomPerson
import dev.stalla.model.atom.Link
import dev.stalla.model.bitlove.Bitlove
import dev.stalla.model.content.Content
import dev.stalla.model.googleplay.EpisodeGoogleplay
import dev.stalla.model.googleplay.ExplicitType
import dev.stalla.model.itunes.EpisodeItunes
import dev.stalla.model.itunes.EpisodeType
import dev.stalla.model.podcastindex.Chapters
import dev.stalla.model.podcastindex.EpisodePodcastindex
import dev.stalla.model.podcastindex.GeographicLocation
import dev.stalla.model.podcastindex.OpenStreetMapElement
import dev.stalla.model.podcastindex.PodcastindexEpisode
import dev.stalla.model.podcastindex.PodcastindexLocation
import dev.stalla.model.podcastindex.PodcastindexPerson
import dev.stalla.model.podcastindex.PodcastindexSeason
import dev.stalla.model.podcastindex.Soundbite
import dev.stalla.model.podcastindex.Transcript
import dev.stalla.model.podcastindex.TranscriptType
import dev.stalla.model.podlove.EpisodePodlove
import dev.stalla.model.podlove.SimpleChapter
import dev.stalla.model.rss.Enclosure
import dev.stalla.model.rss.Guid
import dev.stalla.model.rss.RssCategory
import java.time.Month
import java.time.temporal.TemporalAccessor
import java.util.Locale

@JvmOverloads
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
    podcastindex: EpisodePodcastindex? = anEpisodePodcastindex()
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
    podcastindex
)

@JvmOverloads
internal fun anEpisodeEnclosure(
    url: String = "episode enclosure url",
    length: Long = 777,
    type: MediaType = MediaType.MPEG_AUDIO
) = Enclosure(url, length, type)

@JvmOverloads
internal fun anEpisodeGuid(
    textContent: String = "episode guid textContent",
    isPermalink: Boolean? = false
) = Guid(textContent, isPermalink)

@JvmOverloads
internal fun anEpisodeContent(
    encoded: String = "episode content encoded"
) = Content(encoded)

@JvmOverloads
internal fun anEpisodeItunes(
    title: String? = "episode itunes title",
    duration: StyledDuration? = StyledDuration.of("123"),
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

@JvmOverloads
internal fun anEpisodeAtom(
    authors: List<AtomPerson> = listOf(anAtomPerson("episode atom author name")),
    contributors: List<AtomPerson> = listOf(anAtomPerson("episode atom contributor name")),
    links: List<Link> = listOf(aLink("episode atom link href"))
) = Atom(authors, contributors, links)

@JvmOverloads
internal fun anEpisodePodlove(
    simpleChapters: List<SimpleChapter> = listOf(aPodloveSimpleChapter())
) = EpisodePodlove(simpleChapters)

@JvmOverloads
internal fun aPodloveSimpleChapter(
    start: String = "episode podlove simple chapter start",
    title: String = "episode podlove simple chapter title",
    href: String? = "episode podlove simple chapter href",
    image: String? = "episode podlove simple chapter image"
) = SimpleChapter(start, title, href, image)

@JvmOverloads
internal fun anEpisodeGoogleplay(
    author: String? = "episode googleplay author",
    description: String? = "episode googleplay description",
    explicit: ExplicitType? = ExplicitType.YES,
    block: Boolean = true,
    image: HrefOnlyImage? = anHrefOnlyImage(href = "episode googleplay image url")
) = EpisodeGoogleplay(author, description, explicit, block, image)

@JvmOverloads
internal fun anEpisodeBitlove(
    guid: String = "episode bitlove guid"
) = Bitlove(guid)

@JvmOverloads
internal fun anEpisodePodcastindex(
    transcripts: List<Transcript> = listOf(anEpisodePodcastindexTranscript()),
    soundbites: List<Soundbite> = listOf(anEpisodePodcastindexSoundbite()),
    chapters: Chapters? = anEpisodePodcastindexChapters(),
    persons: List<PodcastindexPerson> = listOf(anEpisodePodcastindexPerson()),
    location: PodcastindexLocation? = anEpisodePodcastindexLocation(),
    season: PodcastindexSeason? = anEpisodePodcastindexSeason(),
    episode: PodcastindexEpisode? = anEpisodePodcastindexEpisode()
) = EpisodePodcastindex(transcripts, soundbites, chapters, persons, location, season, episode)

@JvmOverloads
internal fun anEpisodePodcastindexTranscript(
    url: String = "episode podcastindex transcript url",
    type: TranscriptType = TranscriptType.SRT,
    language: Locale? = Locale.ITALY,
    rel: String? = "captions"
) = Transcript(url, type, language, rel)

@JvmOverloads
internal fun anEpisodePodcastindexSoundbite(
    startTime: StyledDuration.SecondsAndFraction = StyledDuration.secondsAndFraction(1),
    duration: StyledDuration.SecondsAndFraction = StyledDuration.secondsAndFraction(15, 123_000_000),
    title: String? = "episode podcastindex soundbite title"
) = Soundbite(startTime, duration, title)

@JvmOverloads
internal fun anEpisodePodcastindexChapters(
    url: String = "episode podcastindex chapters url",
    type: MediaType = MediaType.JSON_CHAPTERS
) = Chapters(url, type)

@JvmOverloads
internal fun anEpisodePodcastindexPerson(
    name: String = "episode podcastindex person name",
    role: String? = "episode podcastindex person role",
    group: String? = "episode podcastindex person group",
    img: String? = "episode podcastindex person img",
    href: String? = "episode podcastindex person href"
) = PodcastindexPerson(name, role, group, img, href)

@JvmOverloads
internal fun anEpisodePodcastindexLocation(
    name: String = "episode podcastindex location name",
    geo: GeographicLocation? = aPodcastindexGeoLocation(),
    osm: OpenStreetMapElement? = aPodcastindexOpenStreetMapElement()
) = PodcastindexLocation(name, geo, osm)

@JvmOverloads
internal fun anEpisodePodcastindexSeason(
    number: Int = 1,
    name: String = "episode podcastindex season name"
) = PodcastindexSeason(number, name)

@JvmOverloads
internal fun anEpisodePodcastindexEpisode(
    number: Double = 1.0,
    display: String = "episode podcastindex episode display"
) = PodcastindexEpisode(number, display)
