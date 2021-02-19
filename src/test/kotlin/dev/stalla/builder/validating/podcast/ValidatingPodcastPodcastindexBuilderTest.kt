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
import dev.stalla.builder.podcast.PodcastPodcastindexBuilder
import dev.stalla.model.podcast.aPodcastPodcastindex
import dev.stalla.model.podcastindex.PodcastPodcastindex
import org.junit.jupiter.api.Test

internal class ValidatingPodcastPodcastindexBuilderTest {

    private val expectedLockedBuilder = ValidatingPodcastPodcastindexLockedBuilder()
        .locked(true)
        .owner("owner@example.com")

    private val firstExpectedFundingBuilder = ValidatingPodcastPodcastindexFundingBuilder()
        .url("https://example.com/donate")
        .message("First funding")

    private val secondExpectedFundingBuilder = ValidatingPodcastPodcastindexFundingBuilder()
        .url("https://example.com/donate-more")
        .message("Second funding")

    @Test
    internal fun `should not build a Podcast Podcastindex with when all the fields are empty`() {
        val podcastPodcastBuilder = ValidatingPodcastPodcastindexBuilder()

        assertAll {
            assertThat(podcastPodcastBuilder).prop(PodcastPodcastindexBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(podcastPodcastBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build a Podcast Podcastindex with at least a locked builder`() {
        val podcastPodcastBuilder = ValidatingPodcastPodcastindexBuilder()
            .lockedBuilder(expectedLockedBuilder)

        assertAll {
            assertThat(podcastPodcastBuilder).prop(PodcastPodcastindexBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastPodcastBuilder.build()).isNotNull().prop(PodcastPodcastindex::locked).isEqualTo(expectedLockedBuilder.build())
        }
    }

    @Test
    internal fun `should not build a Podcast Podcastindex when there is only a locked builder that doesn't build`() {
        val podcastPodcastBuilder = ValidatingPodcastPodcastindexBuilder()
            .lockedBuilder(ValidatingPodcastPodcastindexLockedBuilder())

        assertAll {
            assertThat(podcastPodcastBuilder).prop(PodcastPodcastindexBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(podcastPodcastBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build a Podcast Podcastindext with at least one funding builder`() {
        val podcastPodcastBuilder = ValidatingPodcastPodcastindexBuilder()
            .addFundingBuilder(firstExpectedFundingBuilder)

        assertAll {
            assertThat(podcastPodcastBuilder).prop(PodcastPodcastindexBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastPodcastBuilder.build()).isNotNull()
                .prop(PodcastPodcastindex::funding).containsExactly(firstExpectedFundingBuilder.build())
        }
    }

    @Test
    internal fun `should not build a Podcast Podcastindex when there is only a funding builder that doesn't build`() {
        val podcastPodcastBuilder = ValidatingPodcastPodcastindexBuilder()
            .addFundingBuilder(ValidatingPodcastPodcastindexFundingBuilder())

        assertAll {
            assertThat(podcastPodcastBuilder).prop(PodcastPodcastindexBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(podcastPodcastBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build a Podcast Podcastindex with with all the added entries to its fields`() {
        val podcastPodcastBuilder = ValidatingPodcastPodcastindexBuilder()
            .lockedBuilder(expectedLockedBuilder)
            .addFundingBuilder(firstExpectedFundingBuilder)
            .addFundingBuilder(secondExpectedFundingBuilder)

        assertAll {
            assertThat(podcastPodcastBuilder).prop(PodcastPodcastindexBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastPodcastBuilder.build()).isNotNull().all {
                prop(PodcastPodcastindex::locked).isEqualTo(expectedLockedBuilder.build())
                prop(PodcastPodcastindex::funding).containsExactly(firstExpectedFundingBuilder.build(), secondExpectedFundingBuilder.build())
            }
        }
    }

    @Test
    internal fun `should populate a Podcast Podcastindex builder with all properties from an Podcast Podcastindex model`() {
        val podcastPodcast = aPodcastPodcastindex()
        val podcastPodcastBuilder = PodcastPodcastindex.builder().applyFrom(podcastPodcast)

        assertAll {
            assertThat(podcastPodcastBuilder).prop(PodcastPodcastindexBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastPodcastBuilder.build()).isNotNull().isEqualTo(podcastPodcast)
        }
    }
}
