package dev.stalla.util

import dev.stalla.model.itunes.ItunesCategory
import dev.stalla.model.itunes.NestedItunesCategory
import dev.stalla.model.itunes.SimpleItunesCategory

@InternalApi
internal val itunesCategoryMap: Map<String, ItunesCategory> = run {
    val simpleCategories: List<ItunesCategory> = SimpleItunesCategory.values().toList()
    val nestedCategories: List<ItunesCategory> = NestedItunesCategory.values().toList()
    val allCategories = simpleCategories + nestedCategories
    allCategories.associateBy({ it.categoryName.toLowerCase() }, { it })
}

@InternalApi
internal fun findItunesCategory(category: String?): ItunesCategory? = category?.let {
    return itunesCategoryMap[it.toLowerCase()]
}
