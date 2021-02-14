package dev.stalla.builder

import dev.stalla.model.googleplay.GoogleplayCategory
import dev.stalla.util.whenNotNull

/** Builder for constructing [GoogleplayCategoryBuilder] instances. */
public interface GoogleplayCategoryBuilder : Builder<GoogleplayCategory> {

    /** Set the category value. */
    public fun category(category: String): GoogleplayCategoryBuilder

    /** Set the subcategory value. */
    public fun subcategory(subcategory: String?): GoogleplayCategoryBuilder

    override fun from(model: GoogleplayCategory?): GoogleplayCategoryBuilder = whenNotNull(model) { category ->
        when (category) {
            is GoogleplayCategory.Simple -> category(category.name)
            is GoogleplayCategory.Nested -> {
                category(category.name)
                subcategory(category.subcategory.name)
            }
        }
    }
}
