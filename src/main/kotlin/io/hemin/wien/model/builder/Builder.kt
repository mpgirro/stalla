package io.hemin.wien.model.builder

/**
 * Interface for builder implementations.
 *
 * @param T The type that a builder implementation creates instances for.
 */
interface Builder<out T> {

    companion object {

        /** Check if all argument elements are not null */
        fun allNotNull(vararg elements: Any?): Boolean = elements.all { p -> p != null }

        /** Check if at least one argument element is not null */
        fun anyNotNull(vararg elements: Any?): Boolean = elements.any { p -> p != null }

    }

    /**
     * Creates an instance of [T] with the properties set in this builder.
     *
     * @return The create instance.
     */
    fun build(): T?

}

