package dev.stalla.builder.podcast

import dev.stalla.builder.Builder
import dev.stalla.model.itunes.ItunesOwner
import dev.stalla.util.whenNotNull

/** Builder for constructing [ItunesOwner] instances. */
public interface PodcastItunesOwnerBuilder : Builder<ItunesOwner> {

    /** Set the name value. */
    public fun name(name: String): PodcastItunesOwnerBuilder

    /** Set the email value. */
    public fun email(email: String): PodcastItunesOwnerBuilder

    override fun applyFrom(prototype: ItunesOwner?): PodcastItunesOwnerBuilder =
        whenNotNull(prototype) { owner ->
            name(owner.name)
            email(owner.email)
        }
}
