package dev.stalla.model.podcastindex

import dev.stalla.model.MediaType
import dev.stalla.model.TypeFactory
import dev.stalla.model.podcastindex.TranscriptType.Factory
import dev.stalla.model.podcastindex.TranscriptType.Factory.HTML
import dev.stalla.model.podcastindex.TranscriptType.Factory.JSON
import dev.stalla.model.podcastindex.TranscriptType.Factory.PLAIN_TEXT
import dev.stalla.model.podcastindex.TranscriptType.Factory.SRT
import java.util.Locale
import kotlin.reflect.KVisibility
import kotlin.reflect.full.declaredMemberProperties

/**
 * Supported transcript types encountered within the `<podcast:transcript>`
 * element within an `<item>` element, modeled as a finite set sealed class.
 * See the [reference docs](https://github.com/Podcastindex-org/podcast-namespace/blob/main/transcripts/transcripts.md)
 * for more information.
 *
 * The available transcript types are modeled according to the table below.
 * This classes [companion object][Factory] exposed a reference for each instance.
 * All instances are also valid instances of [MediaType] and guaranteed to be valid
 * [IANA media types](http://www.iana.org/assignments/media-types/media-types.xhtml).
 *
 * | Media Type         | Property     |
 * |--------------------|--------------|
 * | `text/plain`       | [PLAIN_TEXT] |
 * | `text/html`        | [HTML]       |
 * | `application/json` | [JSON]       |
 * | `application/srt`  | [SRT]        |
 *
 * @property type The type part of the transcript type.
 * @property subtype The subtype part of the transcript type.
 *
 * @see TranscriptType.Factory Provides a factory method for type instances.
 * @see MediaType
 * @since 1.0.0
 */
public sealed class TranscriptType(
    override val type: String,
    override val subtype: String
) : MediaType(type, subtype) {

    private object PlainText : TranscriptType("text", "plain")
    private object Html : TranscriptType("text", "html")
    private object Json : TranscriptType("application", "json")
    private object Srt : TranscriptType("application", "srt")

    /** Gets an instance of [TranscriptType] from a raw value. */
    public companion object Factory : TypeFactory<TranscriptType> {

        private val valueMap: Map<String, TranscriptType> by lazy {
            val values: List<TranscriptType> = Factory::class.declaredMemberProperties
                .filter { member -> member.visibility == KVisibility.PUBLIC }
                .mapNotNull { member -> member.getter.call(this) }
                .filterIsInstance<TranscriptType>()

            values.associateBy({ it.toString().toLowerCase(Locale.ROOT) }, { it })
        }

        @JvmStatic
        override fun of(rawValue: String?): TranscriptType? = rawValue?.let { value ->
            valueMap[value.toLowerCase(Locale.ROOT)]
        }

        /** Plain text, with no timing information. See [MediaType.PLAIN_TEXT]. */
        @JvmField
        public val PLAIN_TEXT: TranscriptType = PlainText

        /** HTML, potentially with some timing information. See [MediaType.HTML]. */
        @JvmField
        public val HTML: TranscriptType = Html

        /** JSON, with full timing information. See [MediaType.JSON]. */
        @JvmField
        public val JSON: TranscriptType = Json

        /** SRT, with full timing information. See [MediaType.SRT]. */
        @JvmField
        public val SRT: TranscriptType = Srt
    }
}
