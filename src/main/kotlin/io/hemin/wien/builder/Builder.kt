package io.hemin.wien.builder

import com.google.common.collect.ImmutableList

/**
 * Interface for builder implementations.
 *
 * @param T The type that a builder implementation creates instances for.
 */
interface Builder<T> {

    /**
     * Creates a model instance with the properties set in this builder.
     *
     * @return The created model instance.
     */
    fun build(): T?

    /** Check if all argument elements are not null */
    fun allNotNull(vararg elements: Any?): Boolean = elements.all { p -> p != null }

    /** Check if at least one argument element is not null */
    fun anyNotNull(vararg elements: Any?): Boolean = elements.any { p -> p != null }

    /** Creates an immutable copy of a given collection as a list. */
    fun <T : Any> immutableCopyOf(collection: Collection<T?>): ImmutableList<T> {
        val nonNulls: List<T> = collection
            .filterNotNull()
            .toList()
        return ImmutableList.copyOf(nonNulls)
    }
}
