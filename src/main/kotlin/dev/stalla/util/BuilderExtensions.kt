package dev.stalla.util

import dev.stalla.builder.Builder

/**
 * Calls [callback] when [model] is not null.
 * @return The [this] context from which [whenNotNull] was called.
 */
@InternalApi
internal fun <T : Any, R : Builder<T>> R.whenNotNull(model: T?, callback: (T) -> Unit): R {
    model?.let(callback)
    return this
}
