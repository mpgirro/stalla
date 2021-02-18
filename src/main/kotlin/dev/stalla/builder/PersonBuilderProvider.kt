package dev.stalla.builder

import dev.stalla.util.InternalApi

@InternalApi
internal interface PersonBuilderProvider {

    /** Creates an instance of [PersonBuilder] to use with this builder. */
    fun createPersonBuilder(): PersonBuilder
}
