package dev.stalla.util

import dev.stalla.model.itunes.ItunesCategory
import dev.stalla.model.itunes.NestedItunesCategory
import dev.stalla.model.itunes.SimpleItunesCategory

@InternalApi
internal val itunesCategoryMap: Map<String, ItunesCategory> = run {
    // Note that the map calls here do a necessary smart cast
    val categories = listOf(
        SimpleItunesCategory.values().map { it },
        NestedItunesCategory.values().map { it }
    )
    categories.flatten().map { it.categoryName.toLowerCase() to it }.toMap()
}

@InternalApi
internal fun findItunesCategory(category: String?): ItunesCategory? = category?.let {
    return itunesCategoryMap[it.toLowerCase()]
}
