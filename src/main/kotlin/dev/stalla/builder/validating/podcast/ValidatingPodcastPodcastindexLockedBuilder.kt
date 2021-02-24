package dev.stalla.builder.validating.podcast

import dev.stalla.builder.podcast.PodcastPodcastindexLockedBuilder
import dev.stalla.model.podcastindex.Locked
import dev.stalla.util.InternalApi

@InternalApi
internal class ValidatingPodcastPodcastindexLockedBuilder : PodcastPodcastindexLockedBuilder {

    private lateinit var ownerValue: String
    private var locked: Boolean? = null

    override fun owner(owner: String): PodcastPodcastindexLockedBuilder = apply { this.ownerValue = owner }

    override fun locked(locked: Boolean): PodcastPodcastindexLockedBuilder = apply { this.locked = locked }

    override val hasEnoughDataToBuild: Boolean
        get() = ::ownerValue.isInitialized && locked != null

    override fun build(): Locked? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Locked(
            owner = ownerValue,
            locked = locked
                ?: throw IllegalStateException("The locked flag is not set, while hasEnoughDataToBuild == true")
        )
    }
}
