package io.hemin.wien.model.builder

/**
 * Base for builder implementations.
 *
 * @param T The type that a builder implementation creates instances for.
 */
abstract class Builder<out T> {

    /**
     * Creates an instance of [T] with the properties set in this builder.
     *
     * @return The create instance.
     */
    abstract fun build(): T?

    protected fun somePresent(vararg elements: Any?): Boolean = elements.any { p -> p != null }

}

