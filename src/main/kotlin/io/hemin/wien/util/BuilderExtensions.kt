package io.hemin.wien.util

/** Check if all argument elements are not null */
internal fun allNotNull(vararg elements: Any?): Boolean = elements.all { p -> p != null }

/** Check if at least one argument element is not null */
internal fun anyNotNull(vararg elements: Any?): Boolean = elements.any { p -> p != null }

/** Check if all argument elements are null */
internal fun allNull(vararg elements: Any?): Boolean = elements.all { p -> p == null }
