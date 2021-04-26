package dev.stalla.parser

import dev.stalla.model.podcastindex.OpenStreetMapFeature
import dev.stalla.model.podcastindex.OpenStreetMapElementType
import dev.stalla.util.InternalAPI
import dev.stalla.util.asBigIntegerOrNull
import java.lang.StringBuilder
import java.math.BigInteger
import kotlin.contracts.contract

@InternalAPI
internal object OsmFeatureParser {

    private enum class ParsingMode {
        Type, Id, Revision
    }

    @InternalAPI
    internal fun parse(value: String?): OpenStreetMapFeature? {
        contract {
            returnsNotNull() implies (value != null)
        }

        if (value == null) return null

        val builder = OpenStreetMapFeature.builder()
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
                ParsingMode.Revision -> revisionBuffer.append(c)
            }
        }

        val idValue = idBuffer.stringOrNullIfEmpty().asBigIntegerOrNull() ?: return null
        builder.id(idValue)
        builder.revision(revisionBuffer.stringOrNullIfEmpty().asBigIntegerOrNull())

        return builder.build()
    }

    @OptIn(ExperimentalStdlibApi::class)
    private fun Char.isNoDigit(): Boolean = digitToIntOrNull() == null

    private fun String?.asBigIntegerOrNull(): BigInteger? {
        contract {
            returnsNotNull() implies (this@asBigIntegerOrNull != null)
        }

        if (this == null) return null
        return try {
            BigInteger(this)
        } catch (e: NumberFormatException) {
            null
        }
    }

    private fun StringBuilder.stringOrNullIfEmpty(): String? = if (isNotEmpty()) toString() else null

}
