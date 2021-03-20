package dev.stalla

@MustBeDocumented
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class TemporaryFile(val deleteOnExit: Boolean = true)
