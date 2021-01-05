package io.hemin.wien.builder.fake.podcast

import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.builder.podcast.PodcastFyydBuilder
import io.hemin.wien.model.Podcast

@Suppress("MemberVisibilityCanBePrivate", "Unused")
internal class FakePodcastFyydBuilder : FakeBuilder<Podcast.Fyyd>(), PodcastFyydBuilder {

    var verifyValue: String? = null

    override fun verify(verify: String): PodcastFyydBuilder = apply { this.verifyValue = verify }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakePodcastFyydBuilder) return false

        if (verifyValue != other.verifyValue) return false

        return true
    }

    override fun hashCode(): Int {
        return verifyValue?.hashCode() ?: 0
    }

    override fun toString() = "FakePodcastFyydBuilder(verifyValue=$verifyValue)"
}
