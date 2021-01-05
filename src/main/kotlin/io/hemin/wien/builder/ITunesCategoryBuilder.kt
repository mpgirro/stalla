package io.hemin.wien.builder

import io.hemin.wien.model.Category

internal interface ITunesCategoryBuilder : Builder<Category.ITunes> {

    /** Set the category value. */
    fun category(category: String): ITunesCategoryBuilder

    /** Set the subcategory value. */
    fun subcategory(subcategory: String?): ITunesCategoryBuilder
}
