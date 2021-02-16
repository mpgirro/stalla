package dev.stalla.model

internal interface TypeFactory<out T> {
    /**
     * Factory method for the instance of matching the [type] parameter.
     *
     * @param type The string representation of the instance.
     * @return The instance matching [type], or null if no matching instance exists.
     */
    fun of(type: String?): T?
}
