package dev.stalla.model

import dev.stalla.util.InternalAPI

/**
 * Interface for companion objects of finite set types (enum or sealed class)
 * to provide a factory method for finite set type instances.
 *
 * @param T The type of the companion objects.
 */
@InternalAPI
internal interface TypeFactory<out T> {

    /**
     * Factory method that returns the instance matching the [rawValue] parameter, if any.
     * This method is case insensitive.
     *
     * @param rawValue The string representation of the instance.
     * @return The instance matching [rawValue], or `null` if no matching instance exists.
     */
    fun of(rawValue: String?): T?
}
