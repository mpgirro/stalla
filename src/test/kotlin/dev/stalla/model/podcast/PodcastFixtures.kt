package dev.stalla.model.podcast

import dev.stalla.dateTime
import dev.stalla.model.Atom
import dev.stalla.model.Episode
import dev.stalla.model.HrefOnlyImage
import dev.stalla.model.Link
import dev.stalla.model.Person
import dev.stalla.model.Podcast
import dev.stalla.model.RssCategory
import dev.stalla.model.RssImage
import dev.stalla.model.aLink
import dev.stalla.model.aPerson
import dev.stalla.model.anHrefOnlyImage
import dev.stalla.model.anITunesCategory
import dev.stalla.model.anRssCategory
import dev.stalla.model.anRssImage
import dev.stalla.model.episode.anEpisode
import dev.stalla.model.itunes.ITunesStyleCategory
import dev.stalla.model.itunes.PodcastItunes
import dev.stalla.model.itunes.ShowType
import java.time.Month
import java.time.temporal.TemporalAccessor

internal fun aPodcast(
    title: String = "podcast title",
    link: String = "podcast link",
    description: String = "podcast description",
    pubDate: TemporalAccessor? = dateTime(year = 2020, month = Month.DECEMBER, day = 26, hour = 15, minute = 32, second = 22),
    lastBuildDate: TemporalAccessor? = dateTime(year = 2020, month = Month.DECEMBER, day = 22, hour = 8, minute = 11, second = 4),
    language: String = "language",
    generator: String? = "generator",
    copyright: String? = "copyright",
    docs: String? = "docs",
    managingEditor: String? = "managing editor",
    webMaster: String? = "web master",
    ttl: Int? = 123,
    image: RssImage? = anRssImage(url = "podcast image url"),
    episodes: List<Episode> = listOf(anEpisode()),
    iTunes: PodcastItunes? = aPodcastITunes(),
    atom: Atom? = aPodcastAtom(),
    fyyd: Podcast.Fyyd? = aPodcastFyyd(),
    feedpress: Podcast.Feedpress? = aPodcastFeedpress(),
    googlePlay: Podcast.GooglePlay? = aPodcastGooglePlay(),
    podcast: Podcast.Podcast? = aPodcastPodcast(),
    categories: List<RssCategory> = listOf(anRssCategory("podcast category"))
) = Podcast(
    title,
    link,
    description,
    pubDate,
    lastBuildDate,
    language,
    generator,
    copyright,
    docs,
    managingEditor,
    webMaster,
    ttl,
    image,
    episodes,
    iTunes,
    atom,
    fyyd,
    feedpress,
    googlePlay,
    categories,
    podcast
)

internal fun aPodcastITunes(
    subtitle: String? = "podcast itunes subtitle",
    summary: String? = "podcast itunes summary",
    image: HrefOnlyImage = anHrefOnlyImage(href = "podcast itunes image url"),
    keywords: String? = "podcast itunes keywords",
    author: String? = "podcast itunes author",
    categories: List<ITunesStyleCategory> = listOf(anITunesCategory("podcast itunes category", "podcast itunes subcategory")),
    explicit: Boolean = true,
    block: Boolean = true,
    complete: Boolean = true,
    type: ShowType? = ShowType.EPISODIC,
    owner: Person? = aPerson("podcast itunes owner name", uri = null),
    title: String? = "podcast itunes title",
    newFeedUrl: String? = "podcast itunes newFeedUrl"
) = PodcastItunes(subtitle, summary, image, keywords, author, categories, explicit, block, complete, type, owner, title, newFeedUrl)

internal fun aPodcastAtom(
    authors: List<Person> = listOf(aPerson("podcast atom author name")),
    contributors: List<Person> = listOf(aPerson("podcast atom contributor name")),
    links: List<Link> = listOf(aLink("podcast atom link href"))
) = Atom(authors, contributors, links)

internal fun aPodcastFyyd(
    verify: String = "podcast fyyd verify"
) = Podcast.Fyyd(verify)

internal fun aPodcastFeedpress(
    newsletterId: String? = "podcast feedpress newsletterId",
    locale: String? = "podcast feedpress locale",
    podcastId: String? = "podcast feedpress podcastId",
    cssFile: String? = "podcast feedpress cssFile",
    link: String? = "podcast feedpress link"
) = Podcast.Feedpress(newsletterId, locale, podcastId, cssFile, link)

internal fun aPodcastGooglePlay(
    author: String? = "podcast googleplay author",
    owner: String? = "podcast googleplay owner",
    categories: List<ITunesStyleCategory> = listOf(anITunesCategory("podcast googleplay category", "podcast googleplay subcategory")),
    description: String? = "podcast googleplay description",
    explicit: Boolean? = true,
    block: Boolean = true,
    image: HrefOnlyImage? = anHrefOnlyImage(href = "podcast googleplay image url")
) = Podcast.GooglePlay(author, owner, categories, description, explicit, block, image)

internal fun aPodcastPodcast(
    locked: Podcast.Podcast.Locked? = aPodcastPodcastLocked(),
    funding: List<Podcast.Podcast.Funding> = listOf(aPodcastPodcastFunding())
) = Podcast.Podcast(locked, funding)

internal fun aPodcastPodcastLocked(
    owner: String = "podcast podcast: locked owner",
    locked: Boolean = true
) = Podcast.Podcast.Locked(owner, locked)

internal fun aPodcastPodcastFunding(
    url: String = "podcast podcast: funding url",
    message: String = "podcast podcast: funding message"
) = Podcast.Podcast.Funding(url, message)
