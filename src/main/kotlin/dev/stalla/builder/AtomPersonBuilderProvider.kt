package dev.stalla.builder

import dev.stalla.util.InternalApi

@InternalApi
internal interface AtomPersonBuilderProvider {

    /** Creates an instance of [AtomPersonBuilder] to use with this builder. */
    fun createAtomPersonBuilder(): AtomPersonBuilder
}
