package io.hemin.wien.builder

import io.hemin.wien.model.ITunesStyleCategory

internal interface ITunesCategoryBuilder : Builder<ITunesStyleCategory> {

    /** Set the category value. */
    fun category(category: String): ITunesCategoryBuilder

    /** Set the subcategory value. */
    fun subcategory(subcategory: String?): ITunesCategoryBuilder
}
