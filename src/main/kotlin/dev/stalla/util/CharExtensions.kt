package dev.stalla.util

/** Returns `true` if this is either a single quote (`'`) or double quote (`"`) symbol. */
@InternalAPI
internal fun Char.isQuoteSymbol(): Boolean = this == '"' || this == '\''
