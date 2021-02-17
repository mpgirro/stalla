package dev.stalla.util

/**
 * Indicates how a boolean value should be written as a string.
 */
@InternalApi
internal enum class BooleanStringStyle(val trueValue: String, val falseValue: String?) {
    /** A true value will be written as `true`, a false value as `false`. */
    TRUE_FALSE("true", "false"),
    /**
     * A true value will be written as `yes`, a false value as `null`. Useful for cases
     * when false values should cause a node to be omitted, as it only supports `yes`.
     */
    YES_NULL("yes", null),
    /**
     * A true value will be written as `yes`, a false value as `no`.
     */
    YES_NO("yes", "no")
}

/** Returns [BooleanStringStyle] representation of this matching [style] */
@InternalApi
internal fun Boolean.asBooleanString(style: BooleanStringStyle) =
    if (this) style.trueValue else style.falseValue
