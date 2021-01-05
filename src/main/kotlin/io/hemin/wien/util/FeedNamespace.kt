package io.hemin.wien.util

internal enum class FeedNamespace(
    val prefix: String,
    val uri: String
) {
    ATOM("atom", "http://www.w3.org/2005/Atom"),
    BITLOVE("bitlove", "http://bitlove.org"),
    CONTENT("content", "http://purl.org/rss/1.0/modules/content/"),
    FEEDPRESS("feedpress", "https://feed.press/xmlns"),
    FYYD("fyyd", "https://fyyd.de/fyyd-ns/"),
    GOOGLE_PLAY("googleplay", "http://www.google.com/schemas/play-podcasts/1.0"),
    ITUNES("itunes", "http://www.itunes.com/dtds/podcast-1.0.dtd"),
    PODLOVE_SIMPLE_CHAPTER("psc", "http://podlove.org/simple-chapters")
}
