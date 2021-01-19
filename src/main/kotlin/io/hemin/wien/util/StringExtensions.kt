package io.hemin.wien.util

/** Returns the trimmed string, or null if it was blank to begin with. */
internal fun String?.trimmedOrNullIfBlank(): String? {
    if (this == null) return null
    val trimmed = trim()
    return if (trimmed.isNotEmpty()) trimmed else null
}

/** Returns `true` when this string is neither `null` nor blank. */
internal fun String?.isNeitherNullNorBlank(): Boolean = this != null && isNotBlank()
