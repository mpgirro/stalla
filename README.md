# WIEN : Podcast Metadata Parser

[![Build Status](https://travis-ci.org/mpgirro/wien.svg?branch=master)](https://travis-ci.org/mpgirro/wien)
[![Coverage Status](https://coveralls.io/repos/github/mpgirro/wien/badge.svg?branch=master)](https://coveralls.io/github/mpgirro/wien?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/66d3c5df2fbf4c9aaabe66e52a847cdd)](https://www.codacy.com/app/mpgirro/wien?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=mpgirro/wien&amp;utm_campaign=Badge_Grade)
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fmpgirro%2Fwien.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2Fmpgirro%2Fwien?ref=badge_shield)

An RSS 2.0 feed parser for extracting Podcast metadata on the JVM. This library is written in Kotlin, but fully Java compatible.

## ⚠️ Early development status warning

WIEN is at a very early stage and may change entirely. You __should not use it in production yet__.

## Supported standards

- [x] [RSS 2.0](http://www.rssboard.org/rss-2-0)
- [x] [Atom](https://tools.ietf.org/html/rfc4287)
- [x] [iTunes](https://help.apple.com/itc/podcasts_connect/#/itcb54353390)
- [x] [Content](http://web.resource.org/rss/1.0/modules/content/) (RDF Site Summary 1.0 Module)
- [x] [Podlove Simple Chapters](https://podlove.org/simple-chapters/)
- [x] Bitlove
- [x] Fyyd
- [x] [Feedpress](https://feed.press/xmlns)
- [x] [Google Play](https://developers.google.com/search/reference/podcast/rss-feed)
- [ ] [Dublin Core](http://purl.org/dc/elements/1.1/) (properties in the `/elements/1.1/` namespace)
- [ ] [Media RSS](http://www.rssboard.org/media-rss) ([example feed](https://gist.github.com/misener/7dd9b587b468aea1ae5a))
- [ ] [Podcastindex.org](https://github.com/Podcastindex-org/podcast-namespace) ([example feed](https://github.com/Podcastindex-org/podcast-namespace/blob/main/example.xml))

Feel free to open an issue if WIEN is missing support for a relevant namespace. Please describe why you feel that this namespace is relevant in the Podcast ecosystem, and ideally provide a link to an existing feed using this namespace.

## Usage

### Parsing an RSS feed

To parse an RSS feed, you need to use the [`PodcastRssParser`](src/main/kotlin/io/hemin/wien/PodcastRssParser.kt) object.
Parsing an RSS feed is as easy as picking the overload of `parse()` that fits your needs:

```kotlin
val rssFeedFile = File("/my/path/rss.xml")
val podcast = PodcastRssParser.parse(rssFeedFile)
```

The `parse()` function will return a parsed `Podcast?`, which may be `null` if the parsing fails for whatever reason.

### Writing an RSS feed

To write an RSS feed, you need to use the [`PodcastRssWriter`](src/main/kotlin/io/hemin/wien/PodcastRssWriter.kt) object.
Parsing an RSS feed is as easy as picking the overload of `parse()` that fits your needs:

```kotlin
val rssFeedFile = File("/my/path/rss.xml")
val podcast: Podcast = // ...
PodcastRssWriter.writeRssFeed(podcast, rssFeedFile)
```

The `writeRssFeed()` function will throw an exception if the parsing fails for whatever reason.

### Documentation

The project uses [Dokka](https://github.com/Kotlin/dokka) to generate its documentation from the KDoc comments in the code. If you want to generate a fresh version of the documentation, use one of the Dokka Gradle tasks:

```bash
$ ./gradlew dokkaHtml
$ ./gradlew dokkaJavadoc
$ ./gradlew dokkaGfm
$ ./gradlew dokkaJekyll
```

## Looking for Atom feed support?

Use [ROME](https://github.com/rometools/rome) instead. It supports both RSS and Atom feed parsing, and provides a unified result interface for the extracted information.

We've provided several modules to extend ROME for additional XML namespaces relevant for Podcast feeds. However, the ROME developers unfortunatelly have not merged the pull requests. Therefore, this library does not support the same range of information extraction as WIEN does.

Also note that certain standard information are only supported by either RSS 2.0 or Atom 1.0 feeds, but not available in both feed types.

## License

Wien is released under the [BSD 3-clause license](LICENSE).
