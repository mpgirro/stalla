package io.hemin.wien.builder.fake.podcast

import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.builder.podcast.PodcastFyydBuilder
import io.hemin.wien.model.Podcast

@Suppress("MemberVisibilityCanBePrivate", "Unused")
internal class FakePodcastFyydBuilder : FakeBuilder<Podcast.Fyyd>(), PodcastFyydBuilder {

    var verifyValue: String? = null

    /** Set the verify value. */
    override fun verify(verify: String): PodcastFyydBuilder = apply { this.verifyValue = verify }
}
