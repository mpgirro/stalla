# WIEN


An RSS/Atom feed parser for extracting maximum Podcast metadata on the JVM


### ⚠️ Early development status warning


WIEN is at a very early stage and may change entirely. You __should not use it in production__.


## Supported XML namespaces

* RSS
* Atom
* iTunes
* Content
* Podlove Simple Chapters (planned)
* Bitlove (planned)
* Fyyd (planned)
* Feedpress (planned)

Feel free to open an issue WIEN is missing support for a relevant namespace. Please describe why you feel that this namespace is relevant to the Podcast ecosystem.


## Looking for Atom feed support?


Use [ROME](https://github.com/rometools/rome) instead. It supports both RSS and Atom feed parsing, and provides a unified result interface for the extracted informations. 

We've provided several modules to extend ROME for additional XML namespaces relevant for Podcast feeds. However, the ROME developers unfortunatelly have not merged the pull requests. Therefore this library does not support the same range of information extraction as WIEN does. 

Also note that certain standard informations are only supported by either RSS 2.0 or Atom 1.0 feeds, but not available in both feed types.


## Development


Compile:

    gradle compileKotlin
    
Generate docs with Dokka:

    gradle dokka
