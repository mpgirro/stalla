package io.hemin.wien.builder

import com.google.common.collect.ImmutableList

/**
 * Interface for builder implementations.
 *
 * @param T The type that a builder implementation creates instances for.
 */
internal interface Builder<T> {

    /**
     * Creates a model instance with the properties set in this builder.
     *
     * @return The created model instance.
     */
    fun build(): T?

    /**
     * This property is `true` when the builder has been provided with enough data to be able to
     * [build] the final model, That means that when this property is `true` the [build] method
     * will return a non-null instance of the model, and when this is `false`, it will always
     * return null.
     *
     * Note that this has a slightly different meaning for builders for models that have no
     * mandatory data, and builders for models that have some mandatory fields.
     * If the resulting model [T] has no mandatory fields, this property will be `true` as soon
     * as any of the fields is set to a non-null value. If the resulting model [T] has one or
     * more mandatory fields, this property will be `true` only after _all_ the mandatory
     * fields are set.
     */
    val hasEnoughDataToBuild: Boolean

    /** Check if all argument elements are not null */
    fun allNotNull(vararg elements: Any?): Boolean = elements.all { p -> p != null }

    /** Check if at least one argument element is not null */
    fun anyNotNull(vararg elements: Any?): Boolean = elements.any { p -> p != null }

    /** Check if all argument elements are null */
    fun allNull(vararg elements: Any?): Boolean = elements.all { p -> p == null }

    /** Creates an immutable copy of a given collection as a list. */
    fun <T : Any> immutableCopyOf(collection: Collection<T?>): ImmutableList<T> {
        val nonNulls: List<T> = collection.filterNotNull()
            .toList()
        return ImmutableList.copyOf(nonNulls)
    }
}
