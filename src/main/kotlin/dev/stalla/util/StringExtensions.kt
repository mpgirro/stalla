package dev.stalla.util

/** Returns the trimmed string, or null if it was blank to begin with. */
@InternalApi
internal fun String?.trimmedOrNullIfBlank(): String? {
    if (this == null) return null
    return trim().ifEmpty { null }
}

/** Returns `true` when this string is neither `null` nor blank. */
@InternalApi
internal fun String?.isNeitherNullNorBlank(): Boolean = this != null && isNotBlank()
