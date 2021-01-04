package io.hemin.wien.builder.validating.podcast

import assertk.all
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import io.hemin.wien.model.Podcast
import org.junit.jupiter.api.Test

internal class ValidatingPodcastFeedpressBuilderTest {

    @Test
    internal fun `should not build a Podcast Feedpress when all fields are missing`() {
        val podcastBuilder = ValidatingPodcastFeedpressBuilder()

        assertThat(podcastBuilder.build()).isNull()
    }

    @Test
    internal fun `should build a valid Podcast Feedpress when there is only a newsletterId`() {
        val podcastBuilder = ValidatingPodcastFeedpressBuilder()
            .newsletterId("newsletterId")

        assertThat(podcastBuilder.build()).isNotNull().all {
            prop(Podcast.Feedpress::newsletterId).isEqualTo("newsletterId")
            prop(Podcast.Feedpress::locale).isNull()
            prop(Podcast.Feedpress::podcastId).isNull()
            prop(Podcast.Feedpress::cssFile).isNull()
            prop(Podcast.Feedpress::link).isNull()
        }
    }

    @Test
    internal fun `should build a valid Podcast Feedpress when there is only a locale`() {
        val podcastBuilder = ValidatingPodcastFeedpressBuilder()
            .locale("locale")

        assertThat(podcastBuilder.build()).isNotNull().all {
            prop(Podcast.Feedpress::newsletterId).isNull()
            prop(Podcast.Feedpress::locale).isEqualTo("locale")
            prop(Podcast.Feedpress::podcastId).isNull()
            prop(Podcast.Feedpress::cssFile).isNull()
            prop(Podcast.Feedpress::link).isNull()
        }
    }

    @Test
    internal fun `should build a valid Podcast Feedpress when there is only a podcastId`() {
        val podcastBuilder = ValidatingPodcastFeedpressBuilder()
            .podcastId("podcastId")

        assertThat(podcastBuilder.build()).isNotNull().all {
            prop(Podcast.Feedpress::newsletterId).isNull()
            prop(Podcast.Feedpress::locale).isNull()
            prop(Podcast.Feedpress::podcastId).isEqualTo("podcastId")
            prop(Podcast.Feedpress::cssFile).isNull()
            prop(Podcast.Feedpress::link).isNull()
        }
    }

    @Test
    internal fun `should build a valid Podcast Feedpress when there is only a cssFile`() {
        val podcastBuilder = ValidatingPodcastFeedpressBuilder()
            .cssFile("cssFile")

        assertThat(podcastBuilder.build()).isNotNull().all {
            prop(Podcast.Feedpress::newsletterId).isNull()
            prop(Podcast.Feedpress::locale).isNull()
            prop(Podcast.Feedpress::podcastId).isNull()
            prop(Podcast.Feedpress::cssFile).isEqualTo("cssFile")
            prop(Podcast.Feedpress::link).isNull()
        }
    }

    @Test
    internal fun `should build a valid Podcast Feedpress when there is only a link`() {
        val podcastBuilder = ValidatingPodcastFeedpressBuilder()
            .link("link")

        assertThat(podcastBuilder.build()).isNotNull().all {
            prop(Podcast.Feedpress::newsletterId).isNull()
            prop(Podcast.Feedpress::locale).isNull()
            prop(Podcast.Feedpress::podcastId).isNull()
            prop(Podcast.Feedpress::cssFile).isNull()
            prop(Podcast.Feedpress::link).isEqualTo("link")
        }
    }

    @Test
    internal fun `should build a valid Podcast Feedpress when there are all fields`() {
        val podcastBuilder = ValidatingPodcastFeedpressBuilder()
            .newsletterId("newsletterId")
            .locale("locale")
            .podcastId("podcastId")
            .cssFile("cssFile")
            .link("link")

        assertThat(podcastBuilder.build()).isNotNull().all {
            prop(Podcast.Feedpress::newsletterId).isEqualTo("newsletterId")
            prop(Podcast.Feedpress::locale).isEqualTo("locale")
            prop(Podcast.Feedpress::podcastId).isEqualTo("podcastId")
            prop(Podcast.Feedpress::cssFile).isEqualTo("cssFile")
            prop(Podcast.Feedpress::link).isEqualTo("link")
        }
    }
}
