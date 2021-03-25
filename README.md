<h1 align="center">
  Stalla
</h1>

<h3 align="center">
	<img src="https://github.com/mpgirro/stalla/blob/master/.idea/icon.png?raw=true" width="128px" alt="Stalla logo" />
	<br/>
	Podcast RSS Feed metadata Parser and Writer
</h3>

<div align="center">

![GitHub Workflow Status](https://img.shields.io/github/workflow/status/mpgirro/stalla/Buildbot)
[![Coverage Status](https://coveralls.io/repos/github/mpgirro/stalla/badge.svg?branch=master)](https://coveralls.io/github/mpgirro/stalla?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/66d3c5df2fbf4c9aaabe66e52a847cdd)](https://www.codacy.com/app/mpgirro/stalla?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=mpgirro/stalla&amp;utm_campaign=Badge_Grade)

</div>
An RSS 2.0 feed parser and writer library for Podcast metadata on the JVM. This library is written in Kotlin and has a Java-friendly API.

## Supported standards

- [x] [RSS 2.0](http://www.rssboard.org/rss-2-0)
- [x] [Atom](https://tools.ietf.org/html/rfc4287) (RFC 4287) – selected elements, no feed support
- [x] [iTunes](https://help.apple.com/itc/podcasts_connect/#/itcb54353390) (Apple Podcast Connect)
- [x] [Content](http://purl.org/rss/1.0/modules/content/) (RDF Site Summary 1.0 Module)
- [x] [Podlove Simple Chapters](https://podlove.org/simple-chapters/)
- [x] Bitlove
- [x] Fyyd
- [x] [Feedpress](https://feed.press/xmlns)
- [x] [Google Play](https://developers.google.com/search/reference/podcast/rss-feed)
- [x] [Podcastindex.org v1](https://github.com/Podcastindex-org/podcast-namespace) ([example feed](https://github.com/Podcastindex-org/podcast-namespace/blob/main/example.xml))

Scheduled:
- [] [Podcastindex.org v2](https://github.com/Podcastindex-org/podcast-namespace) ([example feed](https://github.com/Podcastindex-org/podcast-namespace/blob/main/example.xml))
- [ ] [Spotify](https://drive.google.com/file/d/1KDY1zbRc6J2tkNvhniagor_qcH-pp2T0/view)
- [ ] [Dublin Core](http://purl.org/dc/elements/1.1/) ([RFC 5013](https://tools.ietf.org/html/rfc5013)) – properties in the `/elements/1.1/` namespace
- [ ] [Media RSS](http://www.rssboard.org/media-rss) ([example feed](https://gist.github.com/misener/7dd9b587b468aea1ae5a))

Feel free to open an issue if Stalla is missing support for a relevant namespace. Please describe why you feel that this namespace is relevant in the Podcast ecosystem, and ideally provide a link to an existing feed using this namespace.

## Usage

### Parsing an RSS feed

To parse an RSS feed, you need to use the [`PodcastRssParser`](src/main/kotlin/dev/stalla/PodcastRssParser.kt) object.
Parsing an RSS feed is as easy as picking the overload of `parse()` that fits your needs:

```kotlin
val rssFeedFile = File("/my/path/rss.xml")
val podcast = PodcastRssParser.parse(rssFeedFile)
```

The `parse()` function will return a parsed `Podcast?`, which may be `null` if the parsing fails for whatever reason.

### Writing an RSS feed

To write an RSS feed, you need to use the [`PodcastRssWriter`](src/main/kotlin/dev/stalla/PodcastRssWriter.kt) object.
Writing an RSS feed is as easy as picking the overload of `writeRssFeed()` that fits your needs:

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

Use [ROME](https://github.com/rometools/rome) instead. It supports both RSS 2.0 and Atom 1.0 feeds and provides a unified interface for parsing and writing. We've contributed several ROME modules to support additional XML namespaces relevant for Podcast feeds. However, ROME does not cover the same range of metadata elements, and we no longer contribute to this library.  Also note that certain base feed information parts are only supported by either RSS 2.0 or Atom 1.0 feeds, but not available in both feed types.

## License

Stalla is released under the [BSD 3-clause license](LICENSE).
