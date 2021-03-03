package dev.stalla.util

/** Check if all argument elements are not null. */
@InternalApi
internal fun allNotNull(vararg elements: Any?): Boolean = elements.all { p -> p != null }

/** Check if at least one argument element is not null. */
@InternalApi
internal fun anyNotNull(vararg elements: Any?): Boolean = elements.any { p -> p != null }

/** Check if all argument elements are null. */
@InternalApi
internal fun allNull(vararg elements: Any?): Boolean = elements.all { p -> p == null }

/** Check if at least one argument element is null. */
@InternalApi
internal fun anyNull(vararg elements: Any?): Boolean = elements.any { p -> p == null }
