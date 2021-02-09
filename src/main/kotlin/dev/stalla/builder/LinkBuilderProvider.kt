package dev.stalla.builder

public interface LinkBuilderProvider {

    /** Creates an instance of [LinkBuilder] to use with this builder. */
    public fun createLinkBuilder(): LinkBuilder
}
