package dev.stalla.builder

import dev.stalla.model.ITunesStyleCategory
import dev.stalla.util.whenNotNull

/** Builder for constructing [ITunesStyleCategory] instances. */
public interface ITunesStyleCategoryBuilder : Builder<ITunesStyleCategory> {

    /** Set the category value. */
    public fun category(category: String): ITunesStyleCategoryBuilder

    /** Set the subcategory value. */
    public fun subcategory(subcategory: String?): ITunesStyleCategoryBuilder

    override fun from(model: ITunesStyleCategory?): ITunesStyleCategoryBuilder = whenNotNull(model) { category ->
        when (category) {
            is ITunesStyleCategory.Simple -> category(category.name)
            is ITunesStyleCategory.Nested -> {
                category(category.name)
                subcategory(category.subcategory.name)
            }
        }
    }
}
