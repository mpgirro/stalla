package dev.stalla.model

import dev.stalla.model.atom.AtomPerson
import dev.stalla.model.atom.Link
import dev.stalla.model.googleplay.GoogleplayCategory
import dev.stalla.model.itunes.ItunesCategory
import dev.stalla.model.rss.RssCategory
import dev.stalla.model.rss.RssImage

@JvmOverloads
internal fun anRssImage(
    url: String = "image url",
    title: String = "image title",
    link: String = "image link",
    width: Int? = 123,
    height: Int? = 456,
    description: String? = "image description"
) = RssImage(url, title, link, width, height, description)

@JvmOverloads
internal fun anHrefOnlyImage(
    href: String = "image href"
) = HrefOnlyImage(href)

@JvmOverloads
internal fun anAtomPerson(
    name: String = "person name",
    email: String? = "person email",
    uri: String? = "person uri"
) = AtomPerson(name, email, uri)

@JvmOverloads
internal fun aLink(
    href: String = "link href",
    hrefLang: String? = "link hrefLang",
    hrefResolved: String? = "link hrefResolved",
    length: String? = "link length",
    rel: String? = "link rel",
    title: String? = "link title",
    type: MediaType? = MediaType.HTML
) = Link(href, hrefLang, hrefResolved, length, rel, title, type)

@JvmOverloads
internal fun anRssCategory(
    category: String = "rss category",
    domain: String? = "rss category domain"
) = RssCategory(category, domain)

@JvmOverloads
internal fun anItunesCategory(
    category: ItunesCategory = ItunesCategory.SCIENCE_FICTION
) = category

@JvmOverloads
internal fun aGoogleplayCategory(
    category: GoogleplayCategory = GoogleplayCategory.NEWS_AND_POLITICS
) = category
