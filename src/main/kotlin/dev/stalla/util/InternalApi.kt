package dev.stalla.util

/** Annotation class indicating an internal API that should only be used at your own risk. */
@RequiresOptIn(
    level = RequiresOptIn.Level.ERROR,
    message = "This API is meant to be internal, please do not use it. Or, use it, but don't complain when it breaks :)"
)
public annotation class InternalApi
