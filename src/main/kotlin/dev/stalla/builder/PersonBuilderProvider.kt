package dev.stalla.builder

public interface PersonBuilderProvider {

    /** Creates an instance of [PersonBuilder] to use with this builder. */
    public fun createPersonBuilder(): PersonBuilder
}
