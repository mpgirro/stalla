<h1 align="center">
  Stalla
</h1>

<h3 align="center">
	<img src="https://github.com/mpgirro/stalla/blob/master/.idea/icon.png?raw=true" width="128px" alt="Stalla logo" />
	<br/>
	Podcast RSS Feed metadata Parser and Writer
</h3>

<div align="center">

[![](https://img.shields.io/maven-central/v/dev.stalla/stalla)](https://mvnrepository.com/artifact/dev.stalla/stalla)
![GitHub Workflow Status](https://img.shields.io/github/workflow/status/mpgirro/stalla/Buildbot)
[![Coverage Status](https://coveralls.io/repos/github/mpgirro/stalla/badge.svg?branch=master)](https://coveralls.io/github/mpgirro/stalla?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/66d3c5df2fbf4c9aaabe66e52a847cdd)](https://www.codacy.com/app/mpgirro/stalla?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=mpgirro/stalla&amp;utm_campaign=Badge_Grade)

</div>
An RSS 2.0 feed parser and writer library for Podcast metadata on the JVM. This library is written in Kotlin and has a Java-friendly API.

## Table of Contents

* [Supported standards](#supported-standards)
* [Usage](#usage)
  * [Distribution](#distribution)
  * [Parsing an RSS feed](#parsing-an-rss-feed)
  * [Writing an RSS feed](#writing-an-rss-feed)
  * [Data model](#data-model)
    * [Model construction in Kotlin](#model-construction-in-kotlin)
    * [Model construction in Java](#model-construction-in-java)
    * [Enumerated types](#enumerated-types)
  * [Convenience features](#convenience-features)
    * [Builder factory: builder() method](#builder-factory-builder-method)
    * [Builder mutation: applyFrom() method](#builder-mutation-applyfrom-method)
    * [Type factory: of() method](#type-factory-of-method)
  * [Documentation](#documentation)
* [License](#license)

## Supported standards

- [RSS 2.0](http://www.rssboard.org/rss-2-0)
- [Atom](https://tools.ietf.org/html/rfc4287) (RFC 4287) – selected elements, no feed support
- [iTunes](https://help.apple.com/itc/podcasts_connect/#/itcb54353390) (Apple Podcast Connect)
- [Content](http://purl.org/rss/1.0/modules/content/) (RDF Site Summary 1.0 Module)
- [Podlove Simple Chapters](https://podlove.org/simple-chapters/)
- [Podcastindex.org v1](https://github.com/Podcastindex-org/podcast-namespace) ([example feed](https://github.com/Podcastindex-org/podcast-namespace/blob/main/example.xml))
- [Google Play](https://developers.google.com/search/reference/podcast/rss-feed)
- [Feedpress](https://feed.press/xmlns)
- Bitlove
- Fyyd

Scheduled:

- [Podcastindex.org v2](https://github.com/Podcastindex-org/podcast-namespace)
  in #49 ([example feed](https://github.com/Podcastindex-org/podcast-namespace/blob/main/example.xml))
- [Spotify](https://drive.google.com/file/d/1KDY1zbRc6J2tkNvhniagor_qcH-pp2T0/view) in #29
- [Dublin Core](http://purl.org/dc/elements/1.1/) ([RFC 5013](https://tools.ietf.org/html/rfc5013)) in #28 – properties in the `/elements/1.1/`
  namespace
- [Media RSS](http://www.rssboard.org/media-rss) in #30 ([example feed](https://gist.github.com/misener/7dd9b587b468aea1ae5a))

Feel free to open an issue if Stalla is missing support for a relevant namespace.

## Usage

### Distribution

Stalla is distributed via [Maven Central](https://search.maven.org/search?q=g:dev.stalla%20AND%20a:stalla).

Maven:

```
<dependency>
  <groupId>dev.stalla</groupId>
  <artifactId>stalla</artifactId>
  <version>1.0.0</version>
</dependency>
```

Gradle Kotlin DSL:

```
implementation("dev.stalla:stalla:1.0.0")
```

### Parsing an RSS feed

To parse an RSS feed, you need to use the [`PodcastRssParser`](src/main/kotlin/dev/stalla/PodcastRssParser.kt) object. Parsing an RSS feed is as easy
as picking the overload of `parse()` that fits your needs:

```kotlin
val rssFeedFile = File("/my/path/rss.xml")
val podcast = PodcastRssParser.parse(rssFeedFile)
```

The `parse()` function will return a parsed `Podcast?`, which may be `null` if parsing was unsuccessful. It will also throw an exception if parsing
fails.

In Java, all overloads of `parse()` are available as static methods.

### Writing an RSS feed

To write an RSS feed, you need to use the [`PodcastRssWriter`](src/main/kotlin/dev/stalla/PodcastRssWriter.kt) object. Writing an RSS feed is as easy
as picking the overload of `write()` that fits your needs:

```kotlin
val rssFeedFile = File("/my/path/rss.xml")
val podcast: Podcast = // ...
  PodcastRssWriter.writeRssFeed(podcast, rssFeedFile)
```

The `write()` function will throw an exception if writing fails for whatever reason.

In Java, all overloads of `write()` are available as static methods.

### Data model

The two central entities in Stalla's entire data model are `Podcast` (abstracting the RSS `<channel>`) and `Episode` (abstracting an RSS `<item>`).

#### Model construction in Kotlin

All model types are Kotlin [data classes](https://kotlinlang.org/docs/data-classes.html). Their properties are always un-reassignable (`val`), and the
constructors have default values for optional fields (`null`) and lists (`emptyList()`).

```kotlin
val someEpisode: Episode = // ...
val podcast = Podcast(
  title = "The Stalla Show",
  description = "A show about Stalla development",
  link = "https://stalla.dev/podcast",
  language = Locale.ENGLISH,
  episodes = listOf(firstEpisode)
)
```

#### Model construction in Java

⚠️ Direct instantiation of model class objects is discouraged in Java.

The Java language does not provide named arguments. Some of Stallas model classes take huge parameter lists. To mitigate this limitation, every model
class provides a static `builder()` method that returns a builder instance enabling expressive construction.

```java
Episode someEpisode= // ...
Podcast podcast = Podcast.builder()
  .title("The Stalla Show")
  .description("A show about Stalla development")
  .link("https://stalla.dev/podcast")
  .language(Locale.ENGLISH)
  .addEpisodeBuilder(firstEpisode)
  .build();
```

Note that builders validate upon calling `build()`, i.e. the constructed model instance may be `null` if:

* not all required constructor properties of the backing data class have been initialized in the builder (technical requirement by the type system).
* not all mandatory information bas been provided with respect to the backing specification (RSS or other XML namespace). For example, a `Podcast`
  needs at least one `Episode` (i.e. one `<item>` within the `<channel>`), so while technically an empty list satisfies the technical requirement
  (required, not`null`), it violates however the logical requirement (at least one element).

#### Enumerated types

The supported XML namespaces declare several elements that only accept a finite set of predefined values. In Stalla, these values are usually modeled
via enum classes (e.g. `GoogleplayCategory` or `TranscriptType`). However, some elements, like the Categories of the iTunes namespace, have a more
complex structure (e.g. a hierarchy) that cannot be modeled via enums. These types still try to provide an interface that is close to that of enums
for consistent usage. For example, `ItunesCategory` exposed all possible values as members of the companion object (Kotlin) or as static class members
(Java).

Note that the instances exposed by `MediaType` are not the finite set of possible values, but only predefined instances for convenience.

### Convenience features

#### Builder factory: builder() method

All model classes expose a `builder()` method as a member of the companion object (Kotlin) or as a static class method (Java). These methods return an
implementation of the respective model classes builder interface. The [builder pattern](https://en.wikipedia.org/wiki/Builder_pattern) enables
expressive object construction using named attribute initializers, and compensate for the lack of named constructor arguments in the Java language.

#### Builder mutation: applyFrom() method

All builders expose an `applyFrom(model)` method that takes an instance of the builder's model class as argument, and returns a builder instance. The
method applies all values of the provided model to the builder's state. This is therefore a mutating operation. The returned builder instance is the
mutated state of the builder on which the `applyFrom` method is called, and not a new instance. The operation does not result in any structural
sharing, e.g. references of lists, etc.

The generic use case of this method is the convenient construction of a new model object based on the properties of an existing model instance:

```java
Episode oldEpisode = // ...
Episode newEpisode = Episode.builder()
  .applyFrom(oldEpisode)
  .title("The latest episode")
  .build();
```

#### Type factory: of() method

All enumerated types expose an `of(String)` method as a member of the companion object (Kotlin) or as a static class method (Java). This method is a
factory and produces an instance of the type if possible, or `null`. In contrast to the enum classes `valueOf()` standard method, `of()` is
case-insensitive and also available for non enum class enumerated types (e.g. `ItunesCategory`).

Additionally, Stalla has some types that validate a string for certain patterns, e.g. `MediaType` models media types as defined by
[RFC 2046](https://tools.ietf.org/html/rfc2046), and `StyledDuration` describes values in several supported time formats (`14:02:56` or `5:23`
or `33.833`). These types also provide the `of()` method as the only means to obtain an instance of the class.

### Generate the documentation

The project uses [Dokka](https://github.com/Kotlin/dokka) to generate its documentation from the KDoc comments in the code. If you want to generate a
fresh version of the documentation, use one of the Dokka Gradle tasks:

```bash
$ ./gradlew dokkaHtml
$ ./gradlew dokkaJavadoc
$ ./gradlew dokkaGfm
$ ./gradlew dokkaJekyll
```

## License

Stalla is released under the [BSD 3-clause license](LICENSE).
