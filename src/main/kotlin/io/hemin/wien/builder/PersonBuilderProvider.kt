package io.hemin.wien.builder

internal interface PersonBuilderProvider {

    /** Creates an instance of [PersonBuilder] to use with this builder. */
    fun createPersonBuilder(): PersonBuilder
}
