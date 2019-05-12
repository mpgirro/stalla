# WIEN : Podcast Metadata Parser

[![Build Status](https://travis-ci.org/mpgirro/wien.svg?branch=master)](https://travis-ci.org/mpgirro/wien)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/66d3c5df2fbf4c9aaabe66e52a847cdd)](https://www.codacy.com/app/mpgirro/wien?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=mpgirro/wien&amp;utm_campaign=Badge_Grade)
[![codecov](https://codecov.io/gh/mpgirro/wien/branch/master/graph/badge.svg)](https://codecov.io/gh/mpgirro/wien)

An RSS 2.0 feed parser for extracting Podcast metadata on the JVM. This library is written in Kotlin, but fully Java compatible.

## ⚠️ Early development status warning

WIEN is at a very early stage and may change entirely. You __should not use it in production yet__.

## Supported standards

  - [x] [RSS 2.0](http://www.rssboard.org/rss-2-0)
  - [x] [Atom](https://tools.ietf.org/html/rfc4287)
  - [x] [iTunes](https://help.apple.com/itc/podcasts_connect/#/itcb54353390)
  - [x] [Content](http://web.resource.org/rss/1.0/modules/content/) (RDF Site Summary 1.0 Module)
  - [x] [Podlove Simple Chapters](https://podlove.org/simple-chapters/) (planned)
  - [ ] Bitlove (planned)
  - [ ] Fyyd (planned)
  - [ ] Feedpress (planned)

Feel free to open an issue if WIEN is missing support for a relevant namespace. Please describe why you feel that this namespace is relevant in the Podcast ecosystem, and ideally provide a link to an existing feed using this namespace.

## Looking for Atom feed support?

Use [ROME](https://github.com/rometools/rome) instead. It supports both RSS and Atom feed parsing, and provides a unified result interface for the extracted informations. 

We've provided several modules to extend ROME for additional XML namespaces relevant for Podcast feeds. However, the ROME developers unfortunatelly have not merged the pull requests. Therefore this library does not support the same range of information extraction as WIEN does. 

Also note that certain standard informations are only supported by either RSS 2.0 or Atom 1.0 feeds, but not available in both feed types.

## Development

Compile:

    gradle compileKotlin
    
Generate docs with Dokka:

    gradle dokka
