package dev.stalla.model

/**
 * Represents a single value parameter
 * @property name of parameter
 * @property value of parameter
 */
public data class MediaTypeParameter(val name: String, val value: String) {
    override fun equals(other: Any?): Boolean {
        return other is MediaTypeParameter &&
            other.name.equals(name, ignoreCase = true) &&
            other.value.equals(value, ignoreCase = true)
    }

    override fun hashCode(): Int {
        var result = name.toLowerCase().hashCode()
        result += 31 * result + value.toLowerCase().hashCode()
        return result
    }
}
