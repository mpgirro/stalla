package io.hemin.wien.util

import io.hemin.wien.builder.Builder

/** Check if all argument elements are not null */
internal fun <T> Builder<T>.allNotNull(vararg elements: Any?): Boolean = elements.all { p -> p != null }

/** Check if at least one argument element is not null */
internal fun <T> Builder<T>.anyNotNull(vararg elements: Any?): Boolean = elements.any { p -> p != null }

/** Check if all argument elements are null */
internal fun <T> Builder<T>.allNull(vararg elements: Any?): Boolean = elements.all { p -> p == null }
