package io.hemin.wien.util

import io.hemin.wien.builder.Builder

/** Check if all argument elements are not null */
@InternalApi
internal fun allNotNull(vararg elements: Any?): Boolean = elements.all { p -> p != null }

/** Check if at least one argument element is not null */
@InternalApi
internal fun anyNotNull(vararg elements: Any?): Boolean = elements.any { p -> p != null }

/** Check if all argument elements are null */
@InternalApi
internal fun allNull(vararg elements: Any?): Boolean = elements.all { p -> p == null }

/**
 * Calls [callback] when [model] is not null
 * @return The [this] context from which [whenNotNull] was called.
 */
@InternalApi
internal fun <T : Any, R : Builder<T>> R.whenNotNull(model: T?, callback: (T) -> Unit): R {
    model?.let(callback)
    return this
}
