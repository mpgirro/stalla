package dev.stalla.util

/** Returns `true` if this is either a single quote (`'`) or double quote (`"`) symbol. */
@InternalApi
internal fun Char.isQuoteSymbol(): Boolean = this == '"' || this == '\''
