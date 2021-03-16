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
import dev.stalla.builder.podcast.PodcastPodcastindexFundingBuilder
import dev.stalla.model.aPodcastPodcastindexFunding
import dev.stalla.model.podcastindex.Funding
import org.junit.jupiter.api.Test

internal class ValidatingPodcastPodcastindexFundingBuilderTest {

    @Test
    internal fun `should not build a Podcast Podcastindex Funding with when all the fields are missing`() {
        val fundingBuilder = ValidatingPodcastPodcastindexFundingBuilder()

        assertAll {
            assertThat(fundingBuilder).prop(PodcastPodcastindexFundingBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(fundingBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build a Podcast Podcastindex Funding with when the url field is missing`() {
        val fundingBuilder = ValidatingPodcastPodcastindexFundingBuilder()
            .message("Send me money please, kthxbye")

        assertAll {
            assertThat(fundingBuilder).prop(PodcastPodcastindexFundingBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(fundingBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build a Podcast Podcastindex Funding with when the message field is missing`() {
        val fundingBuilder = ValidatingPodcastPodcastindexFundingBuilder()
            .url("https://example.com/donate")

        assertAll {
            assertThat(fundingBuilder).prop(PodcastPodcastindexFundingBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(fundingBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build a Podcast Podcastindex Funding with with all the added entries to its fields`() {
        val fundingBuilder = ValidatingPodcastPodcastindexFundingBuilder()
            .url("https://example.com/donate")
            .message("Send me money please, kthxbye")

        assertAll {
            assertThat(fundingBuilder).prop(PodcastPodcastindexFundingBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(fundingBuilder.build()).isNotNull().all {
                prop(Funding::url).isEqualTo("https://example.com/donate")
                prop(Funding::message).isEqualTo("Send me money please, kthxbye")
            }
        }
    }

    @Test
    internal fun `should populate a Podcast Podcastindex Funding builder with all properties from an Podcast Podcastindex Funding model`() {
        val funding = aPodcastPodcastindexFunding()
        val fundingBuilder = Funding.builder().applyFrom(funding)

        assertAll {
            assertThat(fundingBuilder).prop(PodcastPodcastindexFundingBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(fundingBuilder.build()).isNotNull().isEqualTo(funding)
        }
    }
}
