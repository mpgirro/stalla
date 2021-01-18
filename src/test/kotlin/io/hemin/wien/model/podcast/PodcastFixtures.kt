package io.hemin.wien.model.podcast

import io.hemin.wien.dateTime
import io.hemin.wien.model.Episode
import io.hemin.wien.model.HrefOnlyImage
import io.hemin.wien.model.ITunesStyleCategory
import io.hemin.wien.model.Link
import io.hemin.wien.model.Person
import io.hemin.wien.model.Podcast
import io.hemin.wien.model.RssCategory
import io.hemin.wien.model.RssImage
import io.hemin.wien.model.aLink
import io.hemin.wien.model.aPerson
import io.hemin.wien.model.anHrefOnlyImage
import io.hemin.wien.model.anITunesCategory
import io.hemin.wien.model.anRssCategory
import io.hemin.wien.model.anRssImage
import io.hemin.wien.model.episode.anEpisode
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
    image: RssImage? = anRssImage(url = "podcast image url"),
    episodes: List<Episode> = listOf(anEpisode()),
    iTunes: Podcast.ITunes? = aPodcastITunes(),
    atom: Podcast.Atom? = aPodcastAtom(),
    fyyd: Podcast.Fyyd? = aPodcastFyyd(),
    feedpress: Podcast.Feedpress? = aPodcastFeedpress(),
    googlePlay: Podcast.GooglePlay? = aPodcastGooglePlay(),
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
    image,
    episodes,
    iTunes,
    atom,
    fyyd,
    feedpress,
    googlePlay,
    categories
)

internal fun aPodcastITunes(
    subtitle: String? = "podcast itunes subtitle",
    summary: String? = "podcast itunes summary",
    image: HrefOnlyImage = anHrefOnlyImage(href = "podcast itunes image url"),
    keywords: String? = "podcast itunes keywords",
    author: String? = "podcast itunes author",
    categories: List<ITunesStyleCategory> = listOf(anITunesCategory("podcast itunes category", "podcast itunes subcategory")),
    explicit: Boolean = true,
    block: Boolean? = true,
    complete: Boolean? = true,
    type: Podcast.ITunes.ShowType? = Podcast.ITunes.ShowType.EPISODIC,
    owner: Person? = aPerson("podcast itunes owner name", uri = null),
    title: String? = "podcast itunes title",
    newFeedUrl: String? = "podcast itunes newFeedUrl"
) = Podcast.ITunes(subtitle, summary, image, keywords, author, categories, explicit, block, complete, type, owner, title, newFeedUrl)

internal fun aPodcastAtom(
    authors: List<Person> = listOf(aPerson("podcast atom author name")),
    contributors: List<Person> = listOf(aPerson("podcast atom contributor name")),
    links: List<Link> = listOf(aLink("podcast atom link href"))
) = Podcast.Atom(authors, contributors, links)

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
    block: Boolean? = true,
    image: HrefOnlyImage? = anHrefOnlyImage(href = "podcast googleplay image url")
) = Podcast.GooglePlay(author, owner, categories, description, explicit, block, image)
