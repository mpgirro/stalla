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
import dev.stalla.builder.podcast.PodcastFeedpressBuilder
import dev.stalla.model.aPodcastFeedpress
import dev.stalla.model.feedpress.Feedpress
import org.junit.jupiter.api.Test
import java.util.Locale

internal class ValidatingPodcastFeedpressBuilderTest {

    @Test
    internal fun `should not build a Podcast Feedpress when all fields are missing`() {
        val podcastFeedpressBuilder = ValidatingPodcastFeedpressBuilder()

        assertAll {
            assertThat(podcastFeedpressBuilder).prop(PodcastFeedpressBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(podcastFeedpressBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build a valid Podcast Feedpress when there is only a newsletterId`() {
        val podcastFeedpressBuilder = ValidatingPodcastFeedpressBuilder()
            .newsletterId("newsletterId")

        assertAll {
            assertThat(podcastFeedpressBuilder).prop(PodcastFeedpressBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastFeedpressBuilder.build()).isNotNull().all {
                prop(Feedpress::newsletterId).isEqualTo("newsletterId")
                prop(Feedpress::locale).isNull()
                prop(Feedpress::podcastId).isNull()
                prop(Feedpress::cssFile).isNull()
                prop(Feedpress::link).isNull()
            }
        }
    }

    @Test
    internal fun `should build a valid Podcast Feedpress when there is only a locale`() {
        val podcastFeedpressBuilder = ValidatingPodcastFeedpressBuilder()
            .locale(Locale.GERMAN)

        assertAll {
            assertThat(podcastFeedpressBuilder).prop(PodcastFeedpressBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastFeedpressBuilder.build()).isNotNull().all {
                prop(Feedpress::newsletterId).isNull()
                prop(Feedpress::locale).isEqualTo(Locale.GERMAN)
                prop(Feedpress::podcastId).isNull()
                prop(Feedpress::cssFile).isNull()
                prop(Feedpress::link).isNull()
            }
        }
    }

    @Test
    internal fun `should build a valid Podcast Feedpress when there is only a podcastId`() {
        val podcastFeedpressBuilder = ValidatingPodcastFeedpressBuilder()
            .podcastId("podcastId")

        assertAll {
            assertThat(podcastFeedpressBuilder).prop(PodcastFeedpressBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastFeedpressBuilder.build()).isNotNull().all {
                prop(Feedpress::newsletterId).isNull()
                prop(Feedpress::locale).isNull()
                prop(Feedpress::podcastId).isEqualTo("podcastId")
                prop(Feedpress::cssFile).isNull()
                prop(Feedpress::link).isNull()
            }
        }
    }

    @Test
    internal fun `should build a valid Podcast Feedpress when there is only a cssFile`() {
        val podcastFeedpressBuilder = ValidatingPodcastFeedpressBuilder()
            .cssFile("cssFile")

        assertAll {
            assertThat(podcastFeedpressBuilder).prop(PodcastFeedpressBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastFeedpressBuilder.build()).isNotNull().all {
                prop(Feedpress::newsletterId).isNull()
                prop(Feedpress::locale).isNull()
                prop(Feedpress::podcastId).isNull()
                prop(Feedpress::cssFile).isEqualTo("cssFile")
                prop(Feedpress::link).isNull()
            }
        }
    }

    @Test
    internal fun `should build a valid Podcast Feedpress when there is only a link`() {
        val podcastFeedpressBuilder = ValidatingPodcastFeedpressBuilder()
            .link("link")

        assertAll {
            assertThat(podcastFeedpressBuilder).prop(PodcastFeedpressBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastFeedpressBuilder.build()).isNotNull().all {
                prop(Feedpress::newsletterId).isNull()
                prop(Feedpress::locale).isNull()
                prop(Feedpress::podcastId).isNull()
                prop(Feedpress::cssFile).isNull()
                prop(Feedpress::link).isEqualTo("link")
            }
        }
    }

    @Test
    internal fun `should build a valid Podcast Feedpress when there are all fields`() {
        val podcastFeedpressBuilder = ValidatingPodcastFeedpressBuilder()
            .newsletterId("newsletterId")
            .locale(Locale.GERMAN)
            .podcastId("podcastId")
            .cssFile("cssFile")
            .link("link")

        assertAll {
            assertThat(podcastFeedpressBuilder).prop(PodcastFeedpressBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastFeedpressBuilder.build()).isNotNull().all {
                prop(Feedpress::newsletterId).isEqualTo("newsletterId")
                prop(Feedpress::locale).isEqualTo(Locale.GERMAN)
                prop(Feedpress::podcastId).isEqualTo("podcastId")
                prop(Feedpress::cssFile).isEqualTo("cssFile")
                prop(Feedpress::link).isEqualTo("link")
            }
        }
    }

    @Test
    internal fun `should populate a Podcast Feedpress builder with all properties from an Podcast Feedpress model`() {
        val podcastFeedpress = aPodcastFeedpress()
        val podcastFeedpressBuilder = Feedpress.builder().applyFrom(podcastFeedpress)

        assertAll {
            assertThat(podcastFeedpressBuilder).prop(PodcastFeedpressBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastFeedpressBuilder.build()).isNotNull().isEqualTo(podcastFeedpress)
        }
    }
}
