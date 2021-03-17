package dev.stalla.model

import dev.stalla.builder.Builder
import dev.stalla.util.InternalAPI2

/**
 * Interface for companion objects of model classes
 * to provide a builder implementation for the model.
 *
 * @param D The type of the model.
 * @param B The type of the builder implementation for the model type [D].
 */
@InternalAPI2
internal interface BuilderFactory<D, out B : Builder<D>> {

    /** Returns a builder implementation [B] for [D]. */
    fun builder(): B
}
