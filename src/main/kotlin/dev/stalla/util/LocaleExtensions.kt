package dev.stalla.util

import java.util.Locale

@InternalApi
internal val localeMap: Map<String, Locale> = Locale.getAvailableLocales().associateBy({ it.toLanguageTag() }, { it })

@InternalApi
internal fun Locale.isValidLocale(): Boolean = localeMap[this.toLanguageTag()] != null
