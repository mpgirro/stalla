package dev.stalla.util

/** Annotation class indicating an experimental API that should not be relied on yet. */
@RequiresOptIn(
    level = RequiresOptIn.Level.WARNING,
    message = "This API is experimental. It may change or behave different in future releases."
)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.TYPEALIAS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.FIELD,
    AnnotationTarget.CONSTRUCTOR
)
public annotation class ExperimentalAPI
