package dev.stalla.builder.validating

import dev.stalla.builder.Builder
import dev.stalla.util.InternalAPI
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.isAccessible

/**
 * Checks if the given nullable property, which is semantically required, is null.
 *
 * @param property The nullable but semantically required property to check.
 * @param clarification A short message to append to the exception message in case the property value
 * is null. Defaults to `${propertyName} is missing`.
 * @return If the property value is null, it throws an [IllegalArgumentException]; otherwise, it
 * returns the non-null value of the property.
 */
@InternalAPI
@Suppress("unused") // The receiver is used to limit the scope of this function
internal fun <T> Builder<*>.checkRequiredProperty(
    property: KProperty<T?>,
    clarification: String? = "${property.name} is missing"
) = checkRequiredValue(property.obtainValue(), clarification)

private fun <T> KProperty<T?>.obtainValue(): T? {
    val wasAccessible = isAccessible
    isAccessible = true
    val value = call()
    isAccessible = wasAccessible
    return value
}

/**
 * Checks if the given nullable value, which is semantically required not to be null, is null.
 *
 * @param value The nullable but semantically required value to check.
 * @param clarification A short message to append to the exception message in case the value is
 * null. Will be skipped if null or blank.
 * @return If the value is null, it throws an [IllegalArgumentException]; otherwise, it returns
 * the non-null value itself.
 */
@InternalAPI
@Suppress("unused") // The receiver is used to limit the scope of this function
internal fun <T> Builder<*>.checkRequiredValue(
    value: T?,
    clarification: String?
) = requireNotNull(value) {
    buildString {
        val entityName = this::class.simpleName?.substringBeforeLast("Builder")
            ?: "entity (ANONYMOUS)"
        append("Cannot build the $entityName even though hasEnoughDataToBuild == true")
        if (!clarification.isNullOrBlank()) {
            append(" (")
            append(clarification)
            append(")")
        }
    }
}
