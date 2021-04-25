package dev.stalla.parser

import dev.stalla.model.podcastindex.OpenStreetMapFeature
import dev.stalla.model.podcastindex.OsmType
import dev.stalla.util.InternalAPI
import java.lang.StringBuilder
import java.math.BigInteger
import kotlin.contracts.contract

@InternalAPI
internal object OsmFeatureParser {

    private enum class ParsingMode {
        Type, Id, Revision
    }

    internal fun parse(value: String?): OpenStreetMapFeature? {
        contract {
            returnsNotNull() implies (value != null)
        }

        if (value == null) return null

        val builder = Builder()
        val idBuffer = StringBuilder()
        val revisionBuffer = StringBuilder()
        var mode = ParsingMode.Type

        for (c in value) {
            when (mode) {
                ParsingMode.Type -> {
                    builder.type(c.toString())
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

        builder.id(idBuffer.stringOrNullIfEmpty())
        builder.revision(revisionBuffer.stringOrNullIfEmpty())

        return builder.build()
    }

    @OptIn(ExperimentalStdlibApi::class)
    private fun Char.isNoDigit(): Boolean = digitToIntOrNull() == null

    private fun String.asBigIntegerOrNull(): BigInteger? = try {
        BigInteger(this)
    } catch (e: NumberFormatException) {
        null
    }

    private fun StringBuilder.stringOrNullIfEmpty(): String? = if (isNotEmpty()) toString() else null

    private class Builder {

        private var type: String? = null
        private var id: String? = null
        private var revision: String? = null

        fun type(type: String?): Builder = apply { this.type = type }

        fun id(id: String?): Builder = apply { this.id = id }

        fun revision(revision: String?): Builder = apply { this.revision = revision }

        fun build(): OpenStreetMapFeature? {
            return OpenStreetMapFeature(
                type = OsmType.of(type) ?: return null,
                id = id?.asBigIntegerOrNull() ?: return null,
                revision = revision
            )
        }

    }

}
