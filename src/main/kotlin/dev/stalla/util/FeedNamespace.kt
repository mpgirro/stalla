package dev.stalla.util

@InternalAPI
internal enum class FeedNamespace(
    val prefix: String,
    val uri: String,
    val otherUris: List<String> = emptyList()
) {
    ATOM("atom", "http://www.w3.org/2005/Atom"),
    BITLOVE("bitlove", "http://bitlove.org"),
    CONTENT("content", "http://purl.org/rss/1.0/modules/content/"),
    FEEDPRESS("feedpress", "https://feed.press/xmlns"),
    FYYD("fyyd", "https://fyyd.de/fyyd-ns/"),
    GOOGLE_PLAY("googleplay", "http://www.google.com/schemas/play-podcasts/1.0"),
    ITUNES("itunes", "http://www.itunes.com/dtds/podcast-1.0.dtd"),
    PODLOVE_SIMPLE_CHAPTER("psc", "http://podlove.org/simple-chapters"),
    PODCAST(
        prefix = "podcast",
        uri = "https://podcastindex.org/namespace/1.0",
        otherUris = listOf("https://github.com/Podcastindex-org/podcast-namespace/blob/main/docs/1.0.md")
    );

    val allUris = listOf(uri) + otherUris

    companion object {

        fun FeedNamespace?.matches(uri: String?): Boolean {
            val normalizedUri = uri?.normalizeUri()
            return when {
                this == null -> normalizedUri == null
                this.uri.normalizeUri() == normalizedUri -> true
                otherUris.isEmpty() -> false
                else -> otherUris.any { it.normalizeUri() == normalizedUri }
            }
        }

        private fun String.normalizeUri() = substringAfter("://").removeSuffix("/")
    }
}
