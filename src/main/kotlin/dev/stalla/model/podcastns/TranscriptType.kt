package dev.stalla.model.podcastns

/**
 * Supported transcript types. See the
 * [reference docs](https://github.com/Podcastindex-org/podcast-namespace/blob/main/transcripts/transcripts.md).
 */
public enum class TranscriptType(public val rawType: String) {

    /** Plain text, with no timing information. */
    PLAIN_TEXT("text/plain"),

    /** HTML, potentially with some timing information. */
    HTML("text/html"),

    /** JSON ,with full timing information. */
    JSON("application/json"),

    /** SRT, with full timing information. */
    SRT("application/srt");

    public companion object Factory {

        public fun from(rawType: String): TranscriptType? = values().find { it.rawType == rawType }
    }
}
