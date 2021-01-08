package io.hemin.wien.builder.validating.podcast

import assertk.all
import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import io.hemin.wien.builder.podcast.PodcastFeedpressBuilder
import io.hemin.wien.model.Podcast
import org.junit.jupiter.api.Test

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
                prop(Podcast.Feedpress::newsletterId).isEqualTo("newsletterId")
                prop(Podcast.Feedpress::locale).isNull()
                prop(Podcast.Feedpress::podcastId).isNull()
                prop(Podcast.Feedpress::cssFile).isNull()
                prop(Podcast.Feedpress::link).isNull()
            }
        }
    }

    @Test
    internal fun `should build a valid Podcast Feedpress when there is only a locale`() {
        val podcastFeedpressBuilder = ValidatingPodcastFeedpressBuilder()
            .locale("locale")

        assertAll {
            assertThat(podcastFeedpressBuilder).prop(PodcastFeedpressBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastFeedpressBuilder.build()).isNotNull().all {
                prop(Podcast.Feedpress::newsletterId).isNull()
                prop(Podcast.Feedpress::locale).isEqualTo("locale")
                prop(Podcast.Feedpress::podcastId).isNull()
                prop(Podcast.Feedpress::cssFile).isNull()
                prop(Podcast.Feedpress::link).isNull()
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
                prop(Podcast.Feedpress::newsletterId).isNull()
                prop(Podcast.Feedpress::locale).isNull()
                prop(Podcast.Feedpress::podcastId).isEqualTo("podcastId")
                prop(Podcast.Feedpress::cssFile).isNull()
                prop(Podcast.Feedpress::link).isNull()
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
                prop(Podcast.Feedpress::newsletterId).isNull()
                prop(Podcast.Feedpress::locale).isNull()
                prop(Podcast.Feedpress::podcastId).isNull()
                prop(Podcast.Feedpress::cssFile).isEqualTo("cssFile")
                prop(Podcast.Feedpress::link).isNull()
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
                prop(Podcast.Feedpress::newsletterId).isNull()
                prop(Podcast.Feedpress::locale).isNull()
                prop(Podcast.Feedpress::podcastId).isNull()
                prop(Podcast.Feedpress::cssFile).isNull()
                prop(Podcast.Feedpress::link).isEqualTo("link")
            }
        }
    }

    @Test
    internal fun `should build a valid Podcast Feedpress when there are all fields`() {
        val podcastFeedpressBuilder = ValidatingPodcastFeedpressBuilder()
            .newsletterId("newsletterId")
            .locale("locale")
            .podcastId("podcastId")
            .cssFile("cssFile")
            .link("link")

        assertAll {
            assertThat(podcastFeedpressBuilder).prop(PodcastFeedpressBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastFeedpressBuilder.build()).isNotNull().all {
                prop(Podcast.Feedpress::newsletterId).isEqualTo("newsletterId")
                prop(Podcast.Feedpress::locale).isEqualTo("locale")
                prop(Podcast.Feedpress::podcastId).isEqualTo("podcastId")
                prop(Podcast.Feedpress::cssFile).isEqualTo("cssFile")
                prop(Podcast.Feedpress::link).isEqualTo("link")
            }
        }
    }
}
