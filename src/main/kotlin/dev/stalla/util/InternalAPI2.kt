package dev.stalla.util

/**
 * Annotation class indicating an internal API that should only be used at your own risk.
 *
 * @since 1.0.0
 */
@RequiresOptIn(
    level = RequiresOptIn.Level.ERROR,
    message = "This API is meant to be internal, please do not use it. Or, use it, but don't complain when it breaks :)"
)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.TYPEALIAS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.FIELD,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.PROPERTY_SETTER
)
public annotation class InternalAPI2
