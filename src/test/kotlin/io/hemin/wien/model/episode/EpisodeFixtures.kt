package io.hemin.wien.model.episode

import io.hemin.wien.dateTime
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Image
import io.hemin.wien.model.Link
import io.hemin.wien.model.Person
import io.hemin.wien.model.aLink
import io.hemin.wien.model.aPerson
import io.hemin.wien.model.anImage
import java.time.Month
import java.time.temporal.TemporalAccessor

internal fun anEpisode(
    title: String = "episode title",
    link: String? = "episode link",
    description: String? = "episode description",
    author: String? = "episode author",
    categories: List<String> = listOf("episode category"),
    comments: String? = "episode comments",
    enclosure: Episode.Enclosure = anEpisodeEnclosure(),
    guid: Episode.Guid? = anEpisodeGuid(),
    pubDate: TemporalAccessor? = dateTime(year = 2020, month = Month.DECEMBER, day = 20, hour = 12, minute = 11, second = 10),
    source: String? = "episode source",
    content: Episode.Content? = anEpisodeContent(),
    iTunes: Episode.ITunes? = anEpisodeITunes(),
    atom: Episode.Atom? = anEpisodeAtom(),
    podlove: Episode.Podlove? = anEpisodePodlove(),
    googlePlay: Episode.GooglePlay? = anEpisodeGooglePlay(),
    bitlove: Episode.Bitlove? = anEpisodeBitlove()
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
    bitlove
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
    image: Image? = anImage(url = "episode itunes image url", title = null, link = null, width = null, height = null, description = null),
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
) = Episode.Atom(authors, contributors, links)

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
    image: Image? = anImage(url = "episode googleplay image url", title = null, link = null, width = null, height = null, description = null)
) = Episode.GooglePlay(description, explicit, block, image)

internal fun anEpisodeBitlove(
    guid: String = "episode bitlove guid"
) = Episode.Bitlove(guid)
