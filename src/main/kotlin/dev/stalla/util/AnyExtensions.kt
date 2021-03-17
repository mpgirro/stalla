package dev.stalla.util

import kotlin.contracts.contract

/** Check if all argument elements are not null. */
@InternalAPI
internal fun allNotNull(
    a: Any?,
    b: Any? = Unit,
    c: Any? = Unit,
    d: Any? = Unit
): Boolean {
    contract {
        returns(true) implies (a != null && b != null && c != null && d != null)
    }
    return a != null && b != null && c != null && d != null
}

/** Check if at least one argument element is not null. */
@InternalAPI
internal fun anyNotNull(vararg elements: Any?): Boolean = elements.any { p -> p != null }

/** Check if all argument elements are null. */
@InternalAPI
internal fun allNull(
    a: Any?,
    b: Any? = null,
    c: Any? = null,
    d: Any? = null
): Boolean {
    contract {
        returns(true) implies (a == null && b == null && c == null && d == null)
    }
    return a == null && b == null && c == null && d == null
}

/** Check if at least one argument element is null. */
@InternalAPI
internal fun anyNull(vararg elements: Any?): Boolean = elements.any { p -> p == null }
