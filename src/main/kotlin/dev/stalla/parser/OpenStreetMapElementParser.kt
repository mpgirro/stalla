package dev.stalla.parser

import dev.stalla.model.podcastindex.OpenStreetMapElement
import dev.stalla.model.podcastindex.OpenStreetMapElementType
import dev.stalla.util.InternalAPI
import kotlin.contracts.contract

@InternalAPI
internal object OpenStreetMapElementParser {

    private enum class ParsingMode {
        Type, Id, Revision
    }

    @InternalAPI
    internal fun parse(value: String?): OpenStreetMapElement? {
        contract {
            returnsNotNull() implies (value != null)
        }

        if (value == null) return null

        val builder = OpenStreetMapElement.builder()
        val idBuffer = StringBuilder()
        val revisionBuffer = StringBuilder()
        var mode = ParsingMode.Type

        for (c in value) {
            when (mode) {
                ParsingMode.Type -> {
                    val type = OpenStreetMapElementType.of(c) ?: return null
                    builder.type(type)
                    mode = ParsingMode.Id
                }
                ParsingMode.Id -> when {
                    c == '#' -> mode = ParsingMode.Revision
                    c.isNoDigit() -> return null
                    else -> idBuffer.append(c)
                }
                ParsingMode.Revision -> when {
                    c.isNoDigit() -> return null
                    else -> revisionBuffer.append(c)
                }
            }
        }

        val id = idBuffer.stringOrNullIfEmpty()?.toLong() ?: return null
        val revision = revisionBuffer.stringOrNullIfEmpty()?.toInt()

        return builder
            .id(id)
            .revision(revision)
            .build()
    }

    private fun Char.isNotDigit(): Boolean = digitToIntOrNull() == null

    private fun StringBuilder.stringOrNullIfEmpty(): String? = if (isNotEmpty()) toString() else null
}
