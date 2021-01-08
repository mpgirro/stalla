package io.hemin.wien.builder.validating.podcast

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import io.hemin.wien.builder.podcast.PodcastFyydBuilder
import io.hemin.wien.model.Podcast
import org.junit.jupiter.api.Test

internal class ValidatingPodcastFyydBuilderTest {

    @Test
    internal fun `should not build a Podcast Fyyd when the mandatory fields are missing`() {
        val podcastFyydBuilder = ValidatingPodcastFyydBuilder()

        assertAll {
            assertThat(podcastFyydBuilder).prop(PodcastFyydBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(podcastFyydBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build a valid Podcast Fyyd when there are all fields`() {
        val podcastFyydBuilder = ValidatingPodcastFyydBuilder()
            .verify("verify")

        assertAll {
            assertThat(podcastFyydBuilder).prop(PodcastFyydBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastFyydBuilder.build()).isNotNull().prop(Podcast.Fyyd::verify).isEqualTo("verify")
        }
    }
}
