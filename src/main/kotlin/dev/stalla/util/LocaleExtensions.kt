package dev.stalla.util

import java.util.Locale

/**
 * Maps all available [Locale] language tags to their [Locale] instance.
 *
 * @see Locale.getAvailableLocales
 * @see Locale.toLanguageTag
 */
@InternalApi
internal val localeMap: Map<String, Locale> = Locale.getAvailableLocales().associateBy({ it.toLanguageTag() }, { it })

/**
 * Returns `true` when this [Locale]'s language tag is defined in the available locales.
 *
 * @see Locale.getAvailableLocales
 * @see Locale.toLanguageTag
 */
@InternalApi
internal fun Locale.isValidLocale(): Boolean = localeMap[this.toLanguageTag()] != null
