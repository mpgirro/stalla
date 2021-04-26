package dev.stalla.model

import dev.stalla.dateTime
import dev.stalla.model.atom.Atom
import dev.stalla.model.atom.AtomPerson
import dev.stalla.model.atom.Link
import dev.stalla.model.feedpress.Feedpress
import dev.stalla.model.fyyd.Fyyd
import dev.stalla.model.googleplay.GoogleplayCategory
import dev.stalla.model.googleplay.PodcastGoogleplay
import dev.stalla.model.itunes.ItunesCategory
import dev.stalla.model.itunes.ItunesOwner
import dev.stalla.model.itunes.PodcastItunes
import dev.stalla.model.itunes.ShowType
import dev.stalla.model.podcastindex.Funding
import dev.stalla.model.podcastindex.Locked
import dev.stalla.model.podcastindex.PodcastPodcastindex
import dev.stalla.model.podcastindex.PodcastindexLocation
import dev.stalla.model.podcastindex.PodcastindexPerson
import dev.stalla.model.rss.RssCategory
import dev.stalla.model.rss.RssImage
import java.time.Month
import java.time.temporal.TemporalAccessor
import java.util.Locale

@JvmOverloads
internal fun aPodcast(
    title: String = "podcast title",
    link: String = "podcast link",
    description: String = "podcast description",
    pubDate: TemporalAccessor? = dateTime(year = 2020, month = Month.DECEMBER, day = 26, hour = 15, minute = 32, second = 22),
    lastBuildDate: TemporalAccessor? = dateTime(year = 2020, month = Month.DECEMBER, day = 22, hour = 8, minute = 11, second = 4),
    language: Locale = Locale.GERMAN,
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
    podcastindex: PodcastPodcastindex? = aPodcastPodcastindex(),
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
    podcastindex
)

@JvmOverloads
internal fun aPodcastItunes(
    subtitle: String? = "podcast itunes subtitle",
    summary: String? = "podcast itunes summary",
    image: HrefOnlyImage = anHrefOnlyImage(href = "podcast itunes image url"),
    keywords: String? = "podcast itunes keywords",
    author: String? = "podcast itunes author",
    categories: List<ItunesCategory> = listOf(anItunesCategory()),
    explicit: Boolean = true,
    block: Boolean = true,
    complete: Boolean = true,
    type: ShowType? = ShowType.EPISODIC,
    owner: ItunesOwner? = anPodcastItunesOwner("podcast itunes owner name"),
    title: String? = "podcast itunes title",
    newFeedUrl: String? = "podcast itunes newFeedUrl"
) = PodcastItunes(subtitle, summary, image, keywords, author, categories, explicit, block, complete, type, owner, title, newFeedUrl)

@JvmOverloads
internal fun anPodcastItunesOwner(
    name: String = "podcast itunes owner name",
    email: String = "podcast itunes owner email"
) = ItunesOwner(name, email)

@JvmOverloads
internal fun aPodcastAtom(
    authors: List<AtomPerson> = listOf(anAtomPerson("podcast atom author name")),
    contributors: List<AtomPerson> = listOf(anAtomPerson("podcast atom contributor name")),
    links: List<Link> = listOf(aLink("podcast atom link href"))
) = Atom(authors, contributors, links)

@JvmOverloads
internal fun aPodcastFyyd(
    verify: String = "podcast fyyd verify"
) = Fyyd(verify)

@JvmOverloads
internal fun aPodcastFeedpress(
    newsletterId: String? = "podcast feedpress newsletterId",
    locale: Locale? = Locale.GERMAN,
    podcastId: String? = "podcast feedpress podcastId",
    cssFile: String? = "podcast feedpress cssFile",
    link: String? = "podcast feedpress link"
) = Feedpress(newsletterId, locale, podcastId, cssFile, link)

@JvmOverloads
internal fun aPodcastGoogleplay(
    author: String? = "podcast googleplay author",
    email: String? = "podcast googleplay email",
    categories: List<GoogleplayCategory> = listOf(aGoogleplayCategory()),
    description: String? = "podcast googleplay description",
    explicit: Boolean? = true,
    block: Boolean = true,
    image: HrefOnlyImage? = anHrefOnlyImage(href = "podcast googleplay image url"),
    newFeedUrl: String? = "podcast googleplay newFeedUrl"
) = PodcastGoogleplay(author, email, categories, description, explicit, block, image, newFeedUrl)

@JvmOverloads
internal fun aPodcastPodcastindex(
    locked: Locked? = aPodcastPodcastindexLocked(),
    funding: List<Funding> = listOf(aPodcastPodcastindexFunding()),
    persons: List<PodcastindexPerson> = listOf(aPodcastindexPerson()),
    location: PodcastindexLocation? = aPodcastindexLocation()
) = PodcastPodcastindex(locked, funding)

@JvmOverloads
internal fun aPodcastPodcastindexLocked(
    owner: String = "podcast podcastindex locked owner",
    locked: Boolean = true
) = Locked(owner, locked)

@JvmOverloads
internal fun aPodcastPodcastindexFunding(
    url: String = "podcast podcastindex funding url",
    message: String = "podcast podcastindex funding message"
) = Funding(url, message)
