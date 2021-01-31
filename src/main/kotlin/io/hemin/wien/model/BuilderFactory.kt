package io.hemin.wien.model

import io.hemin.wien.builder.Builder

internal interface BuilderFactory<D, out B : Builder<D>> {
    /** Returns a builder implementation [B] for [D]. */
    fun builder(): B
}
