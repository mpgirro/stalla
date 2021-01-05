package io.hemin.wien.model

internal fun anRssImage(
    url: String = "image url",
    title: String = "image title",
    link: String = "image link",
    width: Int? = 123,
    height: Int? = 456,
    description: String? = "image description"
) = RssImage(url, title, link, width, height, description)

internal fun anHrefOnlyImage(
    href: String = "image href"
) = HrefOnlyImage(href)

internal fun aPerson(
    name: String = "person name",
    email: String? = "person email",
    uri: String? = "person uri"
) = Person(name, email, uri)

internal fun aLink(
    href: String = "link href",
    hrefLang: String? = "link hrefLang",
    hrefResolved: String? = "link hrefResolved",
    length: String? = "link length",
    rel: String? = "link rel",
    title: String? = "link title",
    type: String? = "link type"
) = Link(href, hrefLang, hrefResolved, length, rel, title, type)
