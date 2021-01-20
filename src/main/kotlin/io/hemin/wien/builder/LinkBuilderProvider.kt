package io.hemin.wien.builder

internal interface LinkBuilderProvider {

    /** Creates an instance of [LinkBuilder] to use with this builder. */
    fun createLinkBuilder(): LinkBuilder
}
