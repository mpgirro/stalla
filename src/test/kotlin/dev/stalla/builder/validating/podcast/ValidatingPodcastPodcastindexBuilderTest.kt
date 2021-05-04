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
import dev.stalla.builder.validating.ValidatingPodcastindexLocationBuilder
import dev.stalla.builder.validating.ValidatingPodcastindexPersonBuilder
import dev.stalla.model.aPodcastPodcastindex
import dev.stalla.model.podcastindex.GeographicLocation
import dev.stalla.model.podcastindex.OpenStreetMapElement
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

    private val firstExpectedPersonBuilder = ValidatingPodcastindexPersonBuilder()
        .name("First name")
        .role("First role")
        .group("First group")
        .img("First img")
        .href("First href")

    private val secondExpectedPersonBuilder = ValidatingPodcastindexPersonBuilder()
        .name("Second name")
        .role("Second role")
        .group("Second group")
        .img("Second img")
        .href("Second href")

    private val expectedLocationBuilder = ValidatingPodcastindexLocationBuilder()
        .name("Location name")
        .geo(GeographicLocation.of("geo:1,2,3"))
        .osm(OpenStreetMapElement.of("R123"))

    @Test
    internal fun `should not build a Podcast Podcastindex with when all the fields are empty`() {
        val podcastPodcastindexBuilder = ValidatingPodcastPodcastindexBuilder()

        assertAll {
            assertThat(podcastPodcastindexBuilder).prop(PodcastPodcastindexBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(podcastPodcastindexBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build a Podcast Podcastindex with only a valid locked builder`() {
        val podcastPodcastindexBuilder = ValidatingPodcastPodcastindexBuilder()
            .lockedBuilder(expectedLockedBuilder)

        assertAll {
            assertThat(podcastPodcastindexBuilder).prop(PodcastPodcastindexBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastPodcastindexBuilder.build()).isNotNull().prop(PodcastPodcastindex::locked).isEqualTo(expectedLockedBuilder.build())
        }
    }

    @Test
    internal fun `should not build a Podcast Podcastindex with only a locked builder that doesn't build`() {
        val podcastPodcastindexBuilder = ValidatingPodcastPodcastindexBuilder()
            .lockedBuilder(ValidatingPodcastPodcastindexLockedBuilder())

        assertAll {
            assertThat(podcastPodcastindexBuilder).prop(PodcastPodcastindexBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(podcastPodcastindexBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build a Podcast Podcastindex with at least one valid funding builder`() {
        val podcastPodcastindexBuilder = ValidatingPodcastPodcastindexBuilder()
            .addFundingBuilder(firstExpectedFundingBuilder)

        assertAll {
            assertThat(podcastPodcastindexBuilder).prop(PodcastPodcastindexBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastPodcastindexBuilder.build()).isNotNull()
                .prop(PodcastPodcastindex::funding).containsExactly(firstExpectedFundingBuilder.build())
        }
    }

    @Test
    internal fun `should not build a Podcast Podcastindex with only a funding builder that doesn't build`() {
        val podcastPodcastindexBuilder = ValidatingPodcastPodcastindexBuilder()
            .addFundingBuilder(ValidatingPodcastPodcastindexFundingBuilder())

        assertAll {
            assertThat(podcastPodcastindexBuilder).prop(PodcastPodcastindexBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(podcastPodcastindexBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build a Podcast Podcastindex with only a valid person builder`() {
        val podcastPodcastindexBuilder = ValidatingPodcastPodcastindexBuilder()
            .addPersonBuilder(firstExpectedPersonBuilder)

        assertAll {
            assertThat(podcastPodcastindexBuilder).prop(PodcastPodcastindexBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastPodcastindexBuilder.build()).isNotNull()
                .prop(PodcastPodcastindex::persons).containsExactly(firstExpectedPersonBuilder.build())
        }
    }

    @Test
    internal fun `should not build a Podcast Podcastindex when there is only one person builder that doesn't build`() {
        val podcastPodcastindexBuilder = ValidatingPodcastPodcastindexBuilder()
            .addPersonBuilder(ValidatingPodcastindexPersonBuilder())

        assertAll {
            assertThat(podcastPodcastindexBuilder).prop(PodcastPodcastindexBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(podcastPodcastindexBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build a Podcast Podcastindex with only a valid location builder`() {
        val podcastPodcastindexBuilder = ValidatingPodcastPodcastindexBuilder()
            .locationBuilder(expectedLocationBuilder)

        assertAll {
            assertThat(podcastPodcastindexBuilder).prop(PodcastPodcastindexBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastPodcastindexBuilder.build()).isNotNull()
                .prop(PodcastPodcastindex::location).isEqualTo(expectedLocationBuilder.build())
        }
    }

    @Test
    internal fun `should not build a Podcast Podcastindex with only a location builder that doesn't build`() {
        val podcastPodcastindexBuilder = ValidatingPodcastPodcastindexBuilder()
            .locationBuilder(ValidatingPodcastindexLocationBuilder())

        assertAll {
            assertThat(podcastPodcastindexBuilder).prop(PodcastPodcastindexBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(podcastPodcastindexBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build a Podcast Podcastindex with all the added entries to its fields`() {
        val podcastPodcastindexBuilder = ValidatingPodcastPodcastindexBuilder()
            .lockedBuilder(expectedLockedBuilder)
            .addFundingBuilder(firstExpectedFundingBuilder)
            .addFundingBuilder(secondExpectedFundingBuilder)
            .addPersonBuilder(firstExpectedPersonBuilder)
            .addPersonBuilder(secondExpectedPersonBuilder)
            .locationBuilder(expectedLocationBuilder)

        assertAll {
            assertThat(podcastPodcastindexBuilder).prop(PodcastPodcastindexBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastPodcastindexBuilder.build()).isNotNull().all {
                prop(PodcastPodcastindex::locked).isEqualTo(expectedLockedBuilder.build())
                prop(PodcastPodcastindex::funding).containsExactly(firstExpectedFundingBuilder.build(), secondExpectedFundingBuilder.build())
                prop(PodcastPodcastindex::persons).containsExactly(firstExpectedPersonBuilder.build(), secondExpectedPersonBuilder.build())
                prop(PodcastPodcastindex::location).isEqualTo(expectedLocationBuilder.build())
            }
        }
    }

    @Test
    internal fun `should populate a Podcast Podcastindex builder with all properties from a Podcast Podcastindex model`() {
        val podcastPodcastindex = aPodcastPodcastindex()
        val podcastPodcastindexBuilder = PodcastPodcastindex.builder().applyFrom(podcastPodcastindex)

        assertAll {
            assertThat(podcastPodcastindexBuilder).prop(PodcastPodcastindexBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastPodcastindexBuilder.build()).isNotNull().isEqualTo(podcastPodcastindex)
        }
    }
}
