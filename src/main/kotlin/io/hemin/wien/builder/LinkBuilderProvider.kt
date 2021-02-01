package io.hemin.wien.builder

interface LinkBuilderProvider {

    /** Creates an instance of [LinkBuilder] to use with this builder. */
    fun createLinkBuilder(): LinkBuilder
}
