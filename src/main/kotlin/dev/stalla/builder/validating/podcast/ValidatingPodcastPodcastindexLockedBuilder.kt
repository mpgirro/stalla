package dev.stalla.builder.validating.podcast

import dev.stalla.builder.podcast.PodcastPodcastindexLockedBuilder
import dev.stalla.builder.validating.checkRequiredProperty
import dev.stalla.model.podcastindex.Locked
import dev.stalla.util.InternalAPI

@InternalAPI
internal class ValidatingPodcastPodcastindexLockedBuilder : PodcastPodcastindexLockedBuilder {

    private var owner: String? = null
    private var locked: Boolean? = null

    override fun owner(owner: String): PodcastPodcastindexLockedBuilder = apply { this.owner = owner }

    override fun locked(locked: Boolean): PodcastPodcastindexLockedBuilder = apply { this.locked = locked }

    override val hasEnoughDataToBuild: Boolean
        get() = owner != null && locked != null

    override fun build(): Locked? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Locked(
            owner = checkRequiredProperty(::owner),
            locked = checkRequiredProperty(::locked)
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ValidatingPodcastPodcastindexLockedBuilder) return false

        if (owner != other.owner) return false
        if (locked != other.locked) return false

        return true
    }

    override fun hashCode(): Int {
        var result = owner.hashCode()
        result = 31 * result + (locked?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String = "ValidatingPodcastPodcastindexLockedBuilder(owner='$owner', locked=$locked)"
}
