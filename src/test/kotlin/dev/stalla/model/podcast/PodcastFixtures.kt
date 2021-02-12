package dev.stalla.model.podcast

import dev.stalla.dateTime
import dev.stalla.model.Atom
import dev.stalla.model.Episode
import dev.stalla.model.HrefOnlyImage
import dev.stalla.model.Person
import dev.stalla.model.Podcast
import dev.stalla.model.aLink
import dev.stalla.model.aPerson
import dev.stalla.model.anHrefOnlyImage
import dev.stalla.model.anItunesCategory
import dev.stalla.model.anRssCategory
import dev.stalla.model.anRssImage
import dev.stalla.model.atom.Link
import dev.stalla.model.episode.anEpisode
import dev.stalla.model.feedpress.Feedpress
import dev.stalla.model.fyyd.Fyyd
import dev.stalla.model.googleplay.PodcastGoogleplay
import dev.stalla.model.itunes.ItunesStyleCategory
import dev.stalla.model.itunes.PodcastItunes
import dev.stalla.model.itunes.ShowType
import dev.stalla.model.podcastns.Funding
import dev.stalla.model.podcastns.Locked
import dev.stalla.model.podcastns.PodcastPodcast
import dev.stalla.model.rss.RssCategory
import dev.stalla.model.rss.RssImage
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
    itunes: PodcastItunes? = aPodcastItunes(),
    atom: Atom? = aPodcastAtom(),
    fyyd: Fyyd? = aPodcastFyyd(),
    feedpress: Feedpress? = aPodcastFeedpress(),
    googleplay: PodcastGoogleplay? = aPodcastGoogleplay(),
    podcast: PodcastPodcast? = aPodcastPodcast(),
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
    itunes,
    atom,
    fyyd,
    feedpress,
    googleplay,
    categories,
    podcast
)

internal fun aPodcastItunes(
    subtitle: String? = "podcast itunes subtitle",
    summary: String? = "podcast itunes summary",
    image: HrefOnlyImage = anHrefOnlyImage(href = "podcast itunes image url"),
    keywords: String? = "podcast itunes keywords",
    author: String? = "podcast itunes author",
    categories: List<ItunesStyleCategory> = listOf(anItunesCategory("podcast itunes category", "podcast itunes subcategory")),
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
) = Fyyd(verify)

internal fun aPodcastFeedpress(
    newsletterId: String? = "podcast feedpress newsletterId",
    locale: String? = "podcast feedpress locale",
    podcastId: String? = "podcast feedpress podcastId",
    cssFile: String? = "podcast feedpress cssFile",
    link: String? = "podcast feedpress link"
) = Feedpress(newsletterId, locale, podcastId, cssFile, link)

internal fun aPodcastGoogleplay(
    author: String? = "podcast googleplay author",
    owner: String? = "podcast googleplay owner",
    categories: List<ItunesStyleCategory> = listOf(anItunesCategory("podcast googleplay category", "podcast googleplay subcategory")),
    description: String? = "podcast googleplay description",
    explicit: Boolean? = true,
    block: Boolean = true,
    image: HrefOnlyImage? = anHrefOnlyImage(href = "podcast googleplay image url")
) = PodcastGoogleplay(author, owner, categories, description, explicit, block, image)

internal fun aPodcastPodcast(
    locked: Locked? = aPodcastPodcastLocked(),
    funding: List<Funding> = listOf(aPodcastPodcastFunding())
) = PodcastPodcast(locked, funding)

internal fun aPodcastPodcastLocked(
    owner: String = "podcast podcast: locked owner",
    locked: Boolean = true
) = Locked(owner, locked)

internal fun aPodcastPodcastFunding(
    url: String = "podcast podcast: funding url",
    message: String = "podcast podcast: funding message"
) = Funding(url, message)
