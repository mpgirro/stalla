package dev.stalla.parser

import dev.stalla.model.podcastindex.GeoLocation
import java.util.regex.Matcher
import java.util.regex.Pattern

internal object GeoUriParser {

    /*
    private val validParameterValueCharacters: Set<Char> by lazy {
        val valid = mutableSetOf<Char>()
        for (c in 'a'..'z') valid.add(c)
        for (c in 'A'..'Z') valid.add(c)
        for (c in '0'..'9') valid.add(c)
        for (c in "!$&'()*+-.:[]_~") valid.add(c)
        valid
    }
    */

    private val hexPattern: Pattern = Pattern.compile("(?i)%([0-9a-f]{2})")

    private const val PARAM_CRS = "crs"
    private const val PARAM_UNCERTAINTY = "u"

    @Suppress("ComplexMethod", "NestedBlockDepth")
    internal fun parse(value: String): GeoLocation? {
        // URI format: geo:LAT,LONG;prop1=value1;prop2=value2
        val scheme = "geo:"
        if (value.length < scheme.length || !value.substring(0, scheme.length).equals(scheme, ignoreCase = true)) {
            // not a geo URI
            return null
        }
        val builder = Builder()
        val buffer = StringBuilder()
        var paramName: String? = null
        var coordinatesDone = false
        for (i in scheme.length until value.length) {
            val c = value[i]
            if (c == ',' && !coordinatesDone) {
                handleEndOfCoordinate(buffer, builder)
                continue
            }
            if (c == ';') {
                if (coordinatesDone) {
                    handleEndOfParameter(buffer, paramName, builder)
                    paramName = null
                } else {
                    handleEndOfCoordinate(buffer, builder)
                    if (builder.coordB == null) {
                        return null
                    }
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
            handleEndOfParameter(buffer, paramName, builder)
        } else {
            handleEndOfCoordinate(buffer, builder)
            if (builder.coordB == null) {
                return null
            }
        }
        return builder.build()
    }

    private fun handleEndOfCoordinate(buffer: StringBuilder, builder: Builder) {
        val s: String = buffer.getAndClear()
        if (builder.coordA == null) {
            try {
                builder.coordA(s.toDouble())
            } catch (e: NumberFormatException) {
                return
            }
            return
        }
        if (builder.coordB == null) {
            try {
                builder.coordB(s.toDouble())
            } catch (e: NumberFormatException) {
                return
            }
            return
        }
        if (builder.coordC == null) {
            try {
                builder.coordC(s.toDouble())
            } catch (e: NumberFormatException) {
                return
            }
            return
        }
    }

    private fun addParameter(name: String, value: String, builder: Builder) {
        val decodedValue = decodeParameterValue(value)
        if (PARAM_CRS.equals(name, ignoreCase = true)) {
            builder.crs(decodedValue)
            return
        }
        if (PARAM_UNCERTAINTY.equals(name, ignoreCase = true)) {
            try {
                builder.uncertainty(decodedValue.toDouble())
                return
            } catch (e: NumberFormatException) {
                // if it can't be parsed, then treat it as an ordinary parameter
            }
        }
        builder.parameter(name, decodedValue)
    }

    /*
    private fun encodeParameterValue(value: String): String = StringBuilder().apply {
        for (i in value.indices) {
            val c = value[i]
            if (validParameterValueCharacters.contains(c)) {
                append(c)
            } else {
                val hex = c.toInt().toString(16)
                append('%')
                append(hex)
            }
        }
    }.toString()
    */

    private fun decodeParameterValue(value: String): String {
        val m: Matcher = hexPattern.matcher(value)
        val sb = StringBuffer()
        while (m.find()) {
            val hex: Int = m.group(1).toInt(16)
            m.appendReplacement(sb, hex.toChar().toString())
        }
        m.appendTail(sb)
        return sb.toString()
    }

    private fun handleEndOfParameter(buffer: StringBuilder, paramName: String?, builder: Builder) {
        val s = buffer.getAndClear()
        if (paramName == null) {
            if (s.isNotEmpty()) {
                addParameter(s, "", builder)
            }
            return
        }
        addParameter(paramName, s, builder)
    }

    private fun StringBuilder.getAndClear(): String {
        val string: String = toString()
        clear()
        return string
    }

    private class Builder {

        var coordA: Double? = null
            private set
        var coordB: Double? = null
            private set
        var coordC: Double? = null
            private set
        private var crs: String? = null
        private var uncertainty: Double? = null
        private var parameters: MutableMap<String, String> = mutableMapOf()

        fun coordA(coordA: Double): Builder = apply { this.coordA = coordA }

        fun coordB(coordB: Double): Builder = apply { this.coordB = coordB }

        fun coordC(coordC: Double?): Builder = apply { this.coordC = coordC }

        fun crs(crs: String?): Builder = apply { this.crs = crs }

        fun uncertainty(uncertainty: Double?): Builder = apply { this.uncertainty = uncertainty }

        fun parameter(name: String, value: String?): Builder = apply {
            if (value == null) {
                parameters.remove(name)
            } else {
                parameters[name] = value
            }
        }

        @Suppress("SwallowedException")
        fun build(): GeoLocation? = try {
            GeoLocation(
                coordA = coordA!!,
                coordB = coordB!!,
                coordC = coordC,
                crs = crs,
                uncertainty = uncertainty,
                parameters = parameters
            )
        } catch (ex: NullPointerException) {
            null
        }
    }
}
