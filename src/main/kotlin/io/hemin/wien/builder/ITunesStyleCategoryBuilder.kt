package io.hemin.wien.builder

import io.hemin.wien.model.ITunesStyleCategory

/** Builder for constructing [ITunesStyleCategory] instances. */
interface ITunesStyleCategoryBuilder : Builder<ITunesStyleCategory> {

    /** Set the category value. */
    fun category(category: String): ITunesStyleCategoryBuilder

    /** Set the subcategory value. */
    fun subcategory(subcategory: String?): ITunesStyleCategoryBuilder
}
