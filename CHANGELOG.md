# CHANGELOG

## v1.1.0 (under development)

* Add support for PodcastIndex namespace phase 2 tags
* Use `ExplicitType` for the Google Play `explicit` attribute at the `Podcast` level, too (see API changes table below)

### Notable API changes

All API changes are subject to deprecation, with automatic migrations provided. Deprecated properties will be removed entirely in a future release —
refer to the table below, and the properties themselves for the removal schedule.

The APIs that have been changed or deprecated since v1.0.0 are:

API that has changed | What has changed | Notes
 --- | --- | ---
`MediaType.match(MediaType?)` | Renamed to `matches(MediaType?)` | Scheduled for removal in v2.0.0.
`MediaType.match(String)` | Renamed to `matches(String)` | Scheduled for removal in v2.0.0.
`PodcastBuilder.podcastPodcastindexBuilder` | Renamed to `podcastindexBuilder` | Scheduled for removal in v2.0.0.
`PodcastGoogleplay.explicit` | Renamed to `explicitType`, type changed to `ExplicitType` | The `explicit` property type is scheduled for removal in v2.0.0, and the `explicitType` property will be renamed to `explicit`.
`PodcastGoogleplay(..., explicit, ...)` | Replaced `explicit` parameter with `explicitType` | The `explicit`-based constructor type is scheduled for removal in v2.0.0.

## v1.0.0

* Refactor model structure (no inner classes) into separate classes and packages
* Fix remaining issues in XML namespace support with respect to their specifications
* Narrow down accepted value for iTunes and Google Play categories, duration values, media types, and locale by using dedicated types
* Ensure Java API expose static methods and delcares exceptions

## v0.10.0

* Adds feed writing support
* Adds additional elements for RSS and iTunes namespace
* Adds support for Podcastindex namespace
* Adds a huge number of unit tests
* Adds builder factory methods to model companion objects
* Changes to java.time representation

## v0.9.0

* Adds support for the Bitlove namespace

## v0.8.0

* Adds support for the Google Play namespace

## v0.7.0

* Adds support for the Feedpress namespace

## v0.6.0

* Adds support for the Fyyd namespace

## v0.5.1

* Fixes the URI for the Podlove Simple Chapter namespace.
* Adds [Travis-CI](https://travis-ci.org/mpgirro/wien), [Codacy](https://app.codacy.com/project/mpgirro/wien),
  and [CodeCov](https://codecov.io/gh/mpgirro/wien) setup.

## v0.5.0

* Adds support for the Podlove Simple Chapter namespace

## v0.4.0

* Adds support for the Atom namespace

## v0.3.1

* Improves public parser API
* Extends test coverage

## v0.3.0

* Adds support for the iTunes namespace
* Adds Unit testing
* Adds code documentation
* Adds support for missing RSS elements

## v0.2.2

* Fixes parsing

## v0.2.1

* Fixes usage of the Content parser

## v0.2.0

* Adds support for the Content namespace

## v0.1.0

* Adds support for the RSS standard elements
