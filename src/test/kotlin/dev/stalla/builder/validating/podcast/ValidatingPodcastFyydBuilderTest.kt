package dev.stalla.builder.validating.podcast

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import dev.stalla.builder.podcast.PodcastFyydBuilder
import dev.stalla.model.fyyd.Fyyd
import dev.stalla.model.podcast.aPodcastFyyd
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

            assertThat(podcastFyydBuilder.build()).isNotNull().prop(Fyyd::verify).isEqualTo("verify")
        }
    }

    @Test
    internal fun `should populate a Podcast Feedpress builder with all properties from an Podcast Feedpress model`() {
        val podcastFyyd = aPodcastFyyd()
        val podcastFyydBuilder = Fyyd.builder().from(podcastFyyd)

        assertAll {
            assertThat(podcastFyydBuilder).prop(PodcastFyydBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastFyydBuilder.build()).isNotNull().isEqualTo(podcastFyyd)
        }
    }
}
