package dev.stalla.builder

import dev.stalla.model.itunes.ItunesStyleCategory
import dev.stalla.util.whenNotNull

/** Builder for constructing [ItunesStyleCategory] instances. */
public interface ITunesStyleCategoryBuilder : Builder<ItunesStyleCategory> {

    /** Set the category value. */
    public fun category(category: String): ITunesStyleCategoryBuilder

    /** Set the subcategory value. */
    public fun subcategory(subcategory: String?): ITunesStyleCategoryBuilder

    override fun from(model: ItunesStyleCategory?): ITunesStyleCategoryBuilder = whenNotNull(model) { category ->
        when (category) {
            is ItunesStyleCategory.Simple -> category(category.name)
            is ItunesStyleCategory.Nested -> {
                category(category.name)
                subcategory(category.subcategory.name)
            }
        }
    }
}
