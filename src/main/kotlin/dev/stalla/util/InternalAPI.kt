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
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.FIELD,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.LOCAL_VARIABLE,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.TYPEALIAS,
    AnnotationTarget.VALUE_PARAMETER
)
public annotation class InternalAPI
