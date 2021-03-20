package dev.stalla.util

import dev.stalla.builder.Builder
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Calls [callback] when [model] is not null.
 * @return The [this] context from which [whenNotNull] was called.
 */
@InternalAPI
internal fun <T : Any, R : Builder<T>> R.whenNotNull(model: T?, callback: (T) -> Unit): R {
    contract {
        callsInPlace(callback, InvocationKind.AT_MOST_ONCE)
    }
    model?.let(callback)
    return this
}
