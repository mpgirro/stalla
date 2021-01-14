package io.hemin.wien.builder

import io.hemin.wien.model.ITunesStyleCategory

interface ITunesStyleCategoryBuilder : Builder<ITunesStyleCategory> {

    /** Set the category value. */
    fun category(category: String): ITunesStyleCategoryBuilder

    /** Set the subcategory value. */
    fun subcategory(subcategory: String?): ITunesStyleCategoryBuilder
}
