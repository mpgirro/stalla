package dev.stalla.builder

import dev.stalla.model.itunes.ItunesStyleCategory2
import dev.stalla.util.whenNotNull

/** Builder for constructing [ItunesStyleCategory2] instances. */
public interface ItunesStyleCategoryBuilder2 : Builder<ItunesStyleCategory2> {

    /** Set the category value. */
    public fun category(category: String): ItunesStyleCategoryBuilder2

    /** Set the subcategory value. */
    public fun subcategory(subcategory: String?): ItunesStyleCategoryBuilder2

    override fun from(model: ItunesStyleCategory2?): ItunesStyleCategoryBuilder2 = whenNotNull(model) { category ->
        when (category) {
            is ItunesStyleCategory2.Simple -> category(category.name)
            is ItunesStyleCategory2.Nested -> {
                category(category.name)
                subcategory(category.subcategory.name)
            }
        }
    }
}
