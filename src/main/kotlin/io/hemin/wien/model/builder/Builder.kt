package io.hemin.wien.model.builder

/**
 * Interface for builder classes.
 *
 * @param T The type that a builder implementation creates instances for.
 */
interface Builder<out T> {

    /** Returns an instance of the type parameter created from the fields set on this builder. */
    fun build(): T

}

