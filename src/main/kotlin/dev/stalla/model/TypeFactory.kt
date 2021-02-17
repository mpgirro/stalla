package dev.stalla.model

internal interface TypeFactory<out T> {
    /**
     * Factory method that returns the instance matching the [rawValue] parameter, if any.
     *
     * @param rawValue The string representation of the instance.
     * @return The instance matching [rawValue], or `null` if no matching instance exists.
     */
    fun of(rawValue: String?): T?
}
