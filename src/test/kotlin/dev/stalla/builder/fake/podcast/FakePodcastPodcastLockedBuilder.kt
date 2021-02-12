package dev.stalla.builder.fake.podcast

import dev.stalla.builder.fake.FakeBuilder
import dev.stalla.builder.podcast.PodcastPodcastLockedBuilder
import dev.stalla.model.podcastindex.Locked

internal class FakePodcastPodcastLockedBuilder : FakeBuilder<Locked>(), PodcastPodcastLockedBuilder {

    var owner: String? = null
    var locked: Boolean? = null

    override fun owner(owner: String): PodcastPodcastLockedBuilder = apply {
        this.owner = owner
    }

    override fun locked(locked: Boolean): PodcastPodcastLockedBuilder = apply {
        this.locked = locked
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakePodcastPodcastLockedBuilder) return false

        if (owner != other.owner) return false
        if (locked != other.locked) return false

        return true
    }

    override fun hashCode(): Int {
        var result = owner?.hashCode() ?: 0
        result = 31 * result + (locked?.hashCode() ?: 0)
        return result
    }

    override fun toString() = "FakePodcastPodcastLockedBuilder(owner=$owner, locked=$locked)"
}
