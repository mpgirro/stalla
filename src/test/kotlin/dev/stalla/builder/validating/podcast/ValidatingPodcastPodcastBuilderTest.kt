package dev.stalla.builder.validating.podcast

import assertk.all
import assertk.assertAll
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import dev.stalla.builder.podcast.PodcastPodcastBuilder
import dev.stalla.model.podcast.aPodcastPodcast
import dev.stalla.model.podcastindex.PodcastPodcast
import org.junit.jupiter.api.Test

internal class ValidatingPodcastPodcastBuilderTest {

    private val expectedLockedBuilder = ValidatingPodcastPodcastLockedBuilder()
        .locked(true)
        .owner("owner@example.com")

    private val firstExpectedFundingBuilder = ValidatingPodcastPodcastFundingBuilder()
        .url("https://example.com/donate")
        .message("First funding")

    private val secondExpectedFundingBuilder = ValidatingPodcastPodcastFundingBuilder()
        .url("https://example.com/donate-more")
        .message("Second funding")

    @Test
    internal fun `should not build a Podcast Podcast with when all the fields are empty`() {
        val podcastPodcastBuilder = ValidatingPodcastPodcastBuilder()

        assertAll {
            assertThat(podcastPodcastBuilder).prop(PodcastPodcastBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(podcastPodcastBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build a Podcast Podcast with at least a locked builder`() {
        val podcastPodcastBuilder = ValidatingPodcastPodcastBuilder()
            .lockedBuilder(expectedLockedBuilder)

        assertAll {
            assertThat(podcastPodcastBuilder).prop(PodcastPodcastBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastPodcastBuilder.build()).isNotNull().prop(PodcastPodcast::locked).isEqualTo(expectedLockedBuilder.build())
        }
    }

    @Test
    internal fun `should not build a Podcast Podcast when there is only a locked builder that doesn't build`() {
        val podcastPodcastBuilder = ValidatingPodcastPodcastBuilder()
            .lockedBuilder(ValidatingPodcastPodcastLockedBuilder())

        assertAll {
            assertThat(podcastPodcastBuilder).prop(PodcastPodcastBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(podcastPodcastBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build a Podcast Podcast with at least one funding builder`() {
        val podcastPodcastBuilder = ValidatingPodcastPodcastBuilder()
            .addFundingBuilder(firstExpectedFundingBuilder)

        assertAll {
            assertThat(podcastPodcastBuilder).prop(PodcastPodcastBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastPodcastBuilder.build()).isNotNull()
                .prop(PodcastPodcast::funding).containsExactly(firstExpectedFundingBuilder.build())
        }
    }

    @Test
    internal fun `should not build a Podcast Podcast when there is only a funding builder that doesn't build`() {
        val podcastPodcastBuilder = ValidatingPodcastPodcastBuilder()
            .addFundingBuilder(ValidatingPodcastPodcastFundingBuilder())

        assertAll {
            assertThat(podcastPodcastBuilder).prop(PodcastPodcastBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(podcastPodcastBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build a Podcast Podcast with with all the added entries to its fields`() {
        val podcastPodcastBuilder = ValidatingPodcastPodcastBuilder()
            .lockedBuilder(expectedLockedBuilder)
            .addFundingBuilder(firstExpectedFundingBuilder)
            .addFundingBuilder(secondExpectedFundingBuilder)

        assertAll {
            assertThat(podcastPodcastBuilder).prop(PodcastPodcastBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastPodcastBuilder.build()).isNotNull().all {
                prop(PodcastPodcast::locked).isEqualTo(expectedLockedBuilder.build())
                prop(PodcastPodcast::funding).containsExactly(firstExpectedFundingBuilder.build(), secondExpectedFundingBuilder.build())
            }
        }
    }

    @Test
    internal fun `should populate a Podcastindex builder with all properties from an Podcastindex model`() {
        val podcastPodcast = aPodcastPodcast()
        val podcastPodcastBuilder = PodcastPodcast.builder().from(podcastPodcast)

        assertAll {
            assertThat(podcastPodcastBuilder).prop(PodcastPodcastBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastPodcastBuilder.build()).isNotNull().isEqualTo(podcastPodcast)
        }
    }
}
