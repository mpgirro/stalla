package io.hemin.wien.builder.validating.podcast

import assertk.all
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import io.hemin.wien.model.Podcast
import org.junit.jupiter.api.Test

internal class ValidatingPodcastFyydBuilderTest {

    @Test
    internal fun `should not build a Podcast Fyyd when the mandatory fields are missing`() {
        val podcastBuilder = ValidatingPodcastFyydBuilder()

        assertThat(podcastBuilder.build()).isNull()
    }

    @Test
    internal fun `should build a valid Podcast Fyyd when there are all fields`() {
        val podcastBuilder = ValidatingPodcastFyydBuilder()
            .verify("verify")

        assertThat(podcastBuilder.build()).isNotNull().all {
            prop(Podcast.Fyyd::verify).isEqualTo("verify")
        }
    }
}
