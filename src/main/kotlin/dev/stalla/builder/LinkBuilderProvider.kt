package dev.stalla.builder

import dev.stalla.util.InternalAPI2

@InternalAPI2
internal interface LinkBuilderProvider {

    /** Creates an instance of [LinkBuilder] to use with this builder. */
    fun createLinkBuilder(): LinkBuilder
}
