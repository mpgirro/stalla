package dev.stalla.builder

import dev.stalla.model.itunes.ItunesStyleCategory
import dev.stalla.util.whenNotNull

/** Builder for constructing [ItunesStyleCategory] instances. */
public interface ItunesStyleCategoryBuilder : Builder<ItunesStyleCategory> {

    /** Set the category value. */
    public fun category(category: String): ItunesStyleCategoryBuilder

    /** Set the subcategory value. */
    public fun subcategory(subcategory: String?): ItunesStyleCategoryBuilder

    override fun from(model: ItunesStyleCategory?): ItunesStyleCategoryBuilder = whenNotNull(model) { category ->
        when (category) {
            is ItunesStyleCategory.Simple -> category(category.name)
            is ItunesStyleCategory.Nested -> {
                category(category.name)
                subcategory(category.subcategory.name)
            }
        }
    }
}
