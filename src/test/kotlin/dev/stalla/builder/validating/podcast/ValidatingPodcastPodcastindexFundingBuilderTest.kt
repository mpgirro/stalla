package dev.stalla.builder.validating.podcast

import assertk.all
import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import dev.stalla.builder.podcast.PodcastPodcastFundingBuilder
import dev.stalla.model.podcast.aPodcastPodcastFunding
import dev.stalla.model.podcastindex.Funding
import org.junit.jupiter.api.Test

internal class ValidatingPodcastPodcastindexFundingBuilderTest {

    @Test
    internal fun `should not build a Podcast Podcast Funding with when all the fields are missing`() {
        val fundingBuilder = ValidatingPodcastPodcastFundingBuilder()

        assertAll {
            assertThat(fundingBuilder).prop(PodcastPodcastFundingBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(fundingBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build a Podcast Podcast Funding with when the url field is missing`() {
        val fundingBuilder = ValidatingPodcastPodcastFundingBuilder()
            .message("Send me money please, kthxbye")

        assertAll {
            assertThat(fundingBuilder).prop(PodcastPodcastFundingBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(fundingBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build a Podcast Podcast Funding with when the message field is missing`() {
        val fundingBuilder = ValidatingPodcastPodcastFundingBuilder()
            .url("https://example.com/donate")

        assertAll {
            assertThat(fundingBuilder).prop(PodcastPodcastFundingBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(fundingBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build a Podcast Podcast Funding with with all the added entries to its fields`() {
        val fundingBuilder = ValidatingPodcastPodcastFundingBuilder()
            .url("https://example.com/donate")
            .message("Send me money please, kthxbye")

        assertAll {
            assertThat(fundingBuilder).prop(PodcastPodcastFundingBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(fundingBuilder.build()).isNotNull().all {
                prop(Funding::url).isEqualTo("https://example.com/donate")
                prop(Funding::message).isEqualTo("Send me money please, kthxbye")
            }
        }
    }

    @Test
    internal fun `should populate a Podcastindex Funding builder with all properties from an Podcastindex Funding model`() {
        val funding = aPodcastPodcastFunding()
        val fundingBuilder = Funding.builder().from(funding)

        assertAll {
            assertThat(fundingBuilder).prop(PodcastPodcastFundingBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(fundingBuilder.build()).isNotNull().isEqualTo(funding)
        }
    }
}
