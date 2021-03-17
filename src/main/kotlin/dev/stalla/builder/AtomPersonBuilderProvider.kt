package dev.stalla.builder

import dev.stalla.util.InternalAPI

@InternalAPI
internal interface AtomPersonBuilderProvider {

    /** Creates an instance of [AtomPersonBuilder] to use with this builder. */
    fun createAtomPersonBuilder(): AtomPersonBuilder
}
