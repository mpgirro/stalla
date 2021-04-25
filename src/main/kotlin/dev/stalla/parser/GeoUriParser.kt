package dev.stalla.parser

import dev.stalla.builder.GeoLocationBuilder
import dev.stalla.model.podcastindex.GeoLocation
import dev.stalla.util.InternalAPI
import java.util.regex.Pattern

/**
 * Parser implementation for [GeoLocation] values, as defined in [RFC 5870](https://tools.ietf.org/html/rfc5870).
 *
 * The parsing logic is inspired by the
 * [GeoUri](https://github.com/mangstadt/ez-vcard/blob/master/src/main/java/ezvcard/util/GeoUri.java)
 * class of the [ez-vcard](https://github.com/mangstadt/ez-vcard) project.
 * Special thanks to the ez-vcard contributors.
 */
@InternalAPI
internal object GeoUriParser {

    private val hexPattern: Pattern = Pattern.compile("(?i)%([0-9a-f]{2})")

    @InternalAPI
    @Suppress("ComplexMethod", "NestedBlockDepth")
    internal fun parse(value: String): GeoLocation? {
        // URI format: geo:LAT,LONG;prop1=value1;prop2=value2
        val scheme = "geo:"
        if (value.length < scheme.length || !value.substring(0, scheme.length).equals(scheme, ignoreCase = true)) {
            // not a geo URI
            return null
        }
        val builder = GeoLocation.builder()
        val buffer = StringBuilder()
        var paramName: String? = null
        var coordinatesDone = false
        for (i in scheme.length until value.length) {
            val c = value[i]
            if (c == ',' && !coordinatesDone) {
                builder.handleEndOfCoordinate(buffer)
                continue
            }
            if (c == ';') {
                if (coordinatesDone) {
                    builder.handleEndOfParameter(buffer, paramName)
                    paramName = null
                } else {
                    builder.handleEndOfCoordinate(buffer)
                    if (!builder.hasCoordB()) return null
                    coordinatesDone = true
                }
                continue
            }
            if (c == '=' && coordinatesDone && paramName == null) {
                paramName = buffer.getAndClear()
                continue
            }
            buffer.append(c)
        }
        if (coordinatesDone) {
            builder.handleEndOfParameter(buffer, paramName)
        } else {
            builder.handleEndOfCoordinate(buffer)
            if (!builder.hasCoordB()) return null
        }
        return builder.build()
    }

    private fun String.asDoubleOrNull(): Double? = try {
        toDouble()
    } catch (e: NumberFormatException) {
        null
    }

    private fun GeoLocationBuilder.handleEndOfCoordinate(buffer: StringBuilder) {
        val s: String = buffer.getAndClear()
        if (!hasCoordA()) {
            val a = s.asDoubleOrNull() ?: return
            coordA(a)
            return
        }
        if (!hasCoordB()) {
            val b = s.asDoubleOrNull() ?: return
            coordB(b)
            return
        }
        if (!hasCoordC()) {
            val c = s.asDoubleOrNull() ?: return
            coordC(c)
            return
        }
    }

    private fun GeoLocationBuilder.handleEndOfParameter(buffer: StringBuilder, paramName: String?) {
        val s = buffer.getAndClear()
        if (paramName == null) {
            if (s.isNotEmpty()) {
                addParameterDecodeValue(s, "")
            }
            return
        }
        addParameterDecodeValue(paramName, s)
    }

    private fun GeoLocationBuilder.addParameterDecodeValue(name: String, value: String?) {
        if (value == null) {
            removeParameter(name)
            return
        }
        addParameter(name, decodeParameterValue(value))
    }

    private fun decodeParameterValue(value: String): String = StringBuffer().apply {
        val matcher = hexPattern.matcher(value)
        while (matcher.find()) {
            val hex: Int = matcher.group(1).toInt(16)
            matcher.appendReplacement(this, hex.toChar().toString())
        }
        matcher.appendTail(this)
    }.toString()

    private fun StringBuilder.getAndClear(): String {
        val string: String = toString()
        clear()
        return string
    }
}
