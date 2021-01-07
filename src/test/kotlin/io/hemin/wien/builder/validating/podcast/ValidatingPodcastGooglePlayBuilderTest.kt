package io.hemin.wien.builder.validating.podcast

import assertk.all
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import io.hemin.wien.builder.validating.ValidatingHrefOnlyImageBuilder
import io.hemin.wien.builder.validating.ValidatingRssImageBuilder
import io.hemin.wien.model.Podcast
import org.junit.jupiter.api.Test

internal class ValidatingPodcastGooglePlayBuilderTest {

    private val expectedImageBuilder = ValidatingHrefOnlyImageBuilder().href("image href")

    @Test
    internal fun `should not build a Podcast GooglePlay when all fields are missing`() {
        val podcastBuilder = ValidatingPodcastGooglePlayBuilder()

        assertThat(podcastBuilder.build()).isNull()
    }

    @Test
    internal fun `should build a valid Podcast GooglePlay when there is only an author`() {
        val podcastBuilder = ValidatingPodcastGooglePlayBuilder()
            .author("author")

        assertThat(podcastBuilder.build()).isNotNull().all {
            prop(Podcast.GooglePlay::author).isEqualTo("author")
            prop(Podcast.GooglePlay::owner).isNull()
            prop(Podcast.GooglePlay::categories).isEmpty()
            prop(Podcast.GooglePlay::description).isNull()
            prop(Podcast.GooglePlay::explicit).isNull()
            prop(Podcast.GooglePlay::block).isNull()
            prop(Podcast.GooglePlay::image).isNull()
        }
    }

    @Test
    internal fun `should build a valid Podcast GooglePlay when there is only an owner`() {
        val podcastBuilder = ValidatingPodcastGooglePlayBuilder()
            .owner("owner")

        assertThat(podcastBuilder.build()).isNotNull().all {
            prop(Podcast.GooglePlay::author).isNull()
            prop(Podcast.GooglePlay::owner).isEqualTo("owner")
            prop(Podcast.GooglePlay::categories).isEmpty()
            prop(Podcast.GooglePlay::description).isNull()
            prop(Podcast.GooglePlay::explicit).isNull()
            prop(Podcast.GooglePlay::block).isNull()
            prop(Podcast.GooglePlay::image).isNull()
        }
    }

    @Test
    internal fun `should build a valid Podcast GooglePlay when there is only a category`() {
        val podcastBuilder = ValidatingPodcastGooglePlayBuilder()
            .addCategory("category 1")

        assertThat(podcastBuilder.build()).isNotNull().all {
            prop(Podcast.GooglePlay::author).isNull()
            prop(Podcast.GooglePlay::owner).isNull()
            prop(Podcast.GooglePlay::categories).containsExactly("category 1")
            prop(Podcast.GooglePlay::description).isNull()
            prop(Podcast.GooglePlay::explicit).isNull()
            prop(Podcast.GooglePlay::block).isNull()
            prop(Podcast.GooglePlay::image).isNull()
        }
    }

    @Test
    internal fun `should build a valid Podcast GooglePlay when there is only a description`() {
        val podcastBuilder = ValidatingPodcastGooglePlayBuilder()
            .description("description")

        assertThat(podcastBuilder.build()).isNotNull().all {
            prop(Podcast.GooglePlay::author).isNull()
            prop(Podcast.GooglePlay::owner).isNull()
            prop(Podcast.GooglePlay::categories).isEmpty()
            prop(Podcast.GooglePlay::description).isEqualTo("description")
            prop(Podcast.GooglePlay::explicit).isNull()
            prop(Podcast.GooglePlay::block).isNull()
            prop(Podcast.GooglePlay::image).isNull()
        }
    }

    @Test
    internal fun `should build a valid Podcast GooglePlay when there is only an explicit`() {
        val podcastBuilder = ValidatingPodcastGooglePlayBuilder()
            .explicit(true)

        assertThat(podcastBuilder.build()).isNotNull().all {
            prop(Podcast.GooglePlay::author).isNull()
            prop(Podcast.GooglePlay::owner).isNull()
            prop(Podcast.GooglePlay::categories).isEmpty()
            prop(Podcast.GooglePlay::description).isNull()
            prop(Podcast.GooglePlay::explicit).isNotNull().isTrue()
            prop(Podcast.GooglePlay::block).isNull()
            prop(Podcast.GooglePlay::image).isNull()
        }
    }

    @Test
    internal fun `should build a valid Podcast GooglePlay when there is only a block`() {
        val podcastBuilder = ValidatingPodcastGooglePlayBuilder()
            .block(false)

        assertThat(podcastBuilder.build()).isNotNull().all {
            prop(Podcast.GooglePlay::author).isNull()
            prop(Podcast.GooglePlay::owner).isNull()
            prop(Podcast.GooglePlay::categories).isEmpty()
            prop(Podcast.GooglePlay::description).isNull()
            prop(Podcast.GooglePlay::explicit).isNull()
            prop(Podcast.GooglePlay::block).isNotNull().isFalse()
            prop(Podcast.GooglePlay::image).isNull()
        }
    }

    @Test
    internal fun `should build a valid Podcast GooglePlay when there is only an image`() {
        val podcastBuilder = ValidatingPodcastGooglePlayBuilder()
            .imageBuilder(expectedImageBuilder)

        assertThat(podcastBuilder.build()).isNotNull().all {
            prop(Podcast.GooglePlay::author).isNull()
            prop(Podcast.GooglePlay::owner).isNull()
            prop(Podcast.GooglePlay::categories).isEmpty()
            prop(Podcast.GooglePlay::description).isNull()
            prop(Podcast.GooglePlay::explicit).isNull()
            prop(Podcast.GooglePlay::block).isNull()
            prop(Podcast.GooglePlay::image).isEqualTo(expectedImageBuilder.build())
        }
    }

    @Test
    internal fun `should build a valid Podcast GooglePlay when there are all fields`() {
        val podcastBuilder = ValidatingPodcastGooglePlayBuilder()
            .author("author")
            .owner("owner")
            .addCategory("category 1")
            .addCategory("category 2")
            .description("description")
            .explicit(true)
            .block(false)
            .imageBuilder(expectedImageBuilder)

        assertThat(podcastBuilder.build()).isNotNull().all {
            prop(Podcast.GooglePlay::author).isEqualTo("author")
            prop(Podcast.GooglePlay::owner).isEqualTo("owner")
            prop(Podcast.GooglePlay::categories).containsExactly("category 1", "category 2")
            prop(Podcast.GooglePlay::description).isEqualTo("description")
            prop(Podcast.GooglePlay::explicit).isNotNull().isTrue()
            prop(Podcast.GooglePlay::block).isNotNull().isFalse()
            prop(Podcast.GooglePlay::image).isEqualTo(expectedImageBuilder.build())
        }
    }
}
