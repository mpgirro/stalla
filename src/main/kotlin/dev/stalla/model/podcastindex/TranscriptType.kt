package dev.stalla.model.podcastindex

import dev.stalla.model.TypeFactory

/**
 * Enum model for the finite value set encountered within the `<podcast:transcript>`
 * element within a `<item>` element. See the
 * [reference docs](https://github.com/Podcastindex-org/podcast-namespace/blob/main/transcripts/transcripts.md)
 * for more information on supported transcript types.
 * .
 */
public enum class TranscriptType(public val type: String) {

    /** Plain text, with no timing information. */
    PLAIN_TEXT("text/plain"),

    /** HTML, potentially with some timing information. */
    HTML("text/html"),

    /** JSON ,with full timing information. */
    JSON("application/json"),

    /** SRT, with full timing information. */
    SRT("application/srt");

    public companion object Factory : TypeFactory<TranscriptType> {

        @JvmStatic
        override fun of(rawValue: String?): TranscriptType? = rawValue?.let {
            values().find { t -> t.type.equals(it, ignoreCase = true) }
        }
    }
}
