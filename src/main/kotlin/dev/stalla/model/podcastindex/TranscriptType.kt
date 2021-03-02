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
 * The available Media Types are modeled according to the table below.
 * This classes [companion object][Factory] exposed a reference for each instance.
 * All instances are valid [MediaType]s.
 *
 * | Media Type         | Property     |
 * |--------------------|--------------|
 * | `text/plain`       | [PLAIN_TEXT] |
 * | `text/html`        | [HTML]       |
 * | `application/json` | [JSON]       |
 * | `application/srt`  | [SRT]        |
 *
 * @param type The type part of the media type.
 * @param subtype The subtype part of the media type.
 */


public sealed class TranscriptType(
    override val type: String,
    override val subtype: String
) : MediaType(type, subtype) {

    // Workaround to the "local class cannot extend a sealed class" restriction
    private abstract class Instance protected constructor(
        override val type: String,
        override val subtype: String
    ) : TranscriptType(type, subtype)

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
            return valueMap[value.toLowerCase(Locale.ROOT)]
        }

        @JvmField
        public val PLAIN_TEXT: TranscriptType = object : Instance("text", "plain") {}

        @JvmField
        public val HTML: TranscriptType = object : Instance("text", "html") {}

        @JvmField
        public val JSON: TranscriptType = object : Instance("application", "json") {}

        @JvmField
        public val SRT: TranscriptType = object : Instance("application", "srt") {}

    }

}
