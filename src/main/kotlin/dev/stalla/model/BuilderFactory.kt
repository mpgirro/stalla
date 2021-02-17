package dev.stalla.model

import dev.stalla.builder.Builder

/**
 * Interface for companion objects of model classes
 * to provide a builder implementation for the model.
 *
 * @param D The type of the model.
 * @param B The type of the builder implementation for the model type [D].
 */
internal interface BuilderFactory<D, out B : Builder<D>> {

    /** Returns a builder implementation [B] for [D]. */
    fun builder(): B
}
