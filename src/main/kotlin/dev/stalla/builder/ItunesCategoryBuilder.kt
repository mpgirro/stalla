package dev.stalla.builder

import dev.stalla.model.itunes.ItunesCategory
import dev.stalla.model.itunes.NestedItunesCategory
import dev.stalla.model.itunes.SimpleItunesCategory
import dev.stalla.util.whenNotNull

/** Builder for constructing [ItunesCategory] instances. */
public interface ItunesCategoryBuilder : Builder<ItunesCategory> {

    /** Set the category value. */
    public fun category(category: String): ItunesCategoryBuilder

    /** Set the subcategory value. */
    public fun subcategory(subcategory: String?): ItunesCategoryBuilder

    override fun from(model: ItunesCategory?): ItunesCategoryBuilder = whenNotNull(model) { category ->
        when (category) {
            is SimpleItunesCategory -> {

            }
            is NestedItunesCategory -> {

            }
        }
        /*
        when (category) {
            is ItunesCategory.Simple -> category(category.name)
            is ItunesCategory.Nested -> {
                category(category.name)
                subcategory(category.subcategory.name)
            }
        }
        */
    }
}
