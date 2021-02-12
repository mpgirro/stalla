package dev.stalla.builder.fake.podcast

import dev.stalla.builder.fake.FakeBuilder
import dev.stalla.builder.podcast.PodcastFyydBuilder
import dev.stalla.model.fyyd.Fyyd

internal class FakePodcastFyydBuilder : FakeBuilder<Fyyd>(), PodcastFyydBuilder {

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
