package io.hemin.wien.util

/** Check if all argument elements are not null */
internal fun allNotNull(vararg elements: Any?): Boolean = elements.all { p -> p != null }

/** Check if at least one argument element is not null */
internal fun anyNotNull(vararg elements: Any?): Boolean = elements.any { p -> p != null }

/** Check if all argument elements are null */
internal fun allNull(vararg elements: Any?): Boolean = elements.all { p -> p == null }

/**
 * Calls [callback] when [model] is not null
 * @return The [this] context from which [whenNotNull] was called.
 */
internal fun <T : Any, R> R.whenNotNull(model: T?, callback: (T) -> Unit): R {
    model?.let(callback)
    return this
}
