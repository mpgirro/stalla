package dev.stalla.model

internal interface TypeFactory<out T> {
    /**
     * Factory method for the instance of matching the [rawValue] parameter.
     *
     * @param rawValue The string representation of the instance.
     * @return The instance matching [rawValue], or null if no matching instance exists.
     */
    fun of(rawValue: String?): T?
}
