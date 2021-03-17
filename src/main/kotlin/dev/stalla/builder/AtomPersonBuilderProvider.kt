package dev.stalla.builder

import dev.stalla.util.InternalAPI2

@InternalAPI2
internal interface AtomPersonBuilderProvider {

    /** Creates an instance of [AtomPersonBuilder] to use with this builder. */
    fun createAtomPersonBuilder(): AtomPersonBuilder
}
