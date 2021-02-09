package dev.stalla.model

import dev.stalla.builder.Builder

internal interface BuilderFactory<D, out B : Builder<D>> {
    /** Returns a builder implementation [B] for [D]. */
    fun builder(): B
}
