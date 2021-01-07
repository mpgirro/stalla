package io.hemin.wien.builder.validating.podcast

import assertk.all
import assertk.assertAll
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import io.hemin.wien.builder.podcast.PodcastGooglePlayBuilder
import io.hemin.wien.builder.validating.ValidatingHrefOnlyImageBuilder
import io.hemin.wien.builder.validating.ValidatingITunesStyleCategoryBuilder
import io.hemin.wien.model.Podcast
import org.junit.jupiter.api.Test

internal class ValidatingPodcastGooglePlayBuilderTest {

    private val expectedImageBuilder = ValidatingHrefOnlyImageBuilder().href("image href")

    private val expectedITunesCategoryBuilder = ValidatingITunesStyleCategoryBuilder()
        .category("googleplay category")
        .subcategory("googleplay subcategory")

    private val otherExpectedITunesCategoryBuilder = ValidatingITunesStyleCategoryBuilder()
        .category("googleplay category 2")

    @Test
    internal fun `should not build a Podcast GooglePlay when all fields are missing`() {
        val podcastGooglePlayBuilder = ValidatingPodcastGooglePlayBuilder()

        assertAll {
            assertThat(podcastGooglePlayBuilder).prop(PodcastGooglePlayBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(podcastGooglePlayBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build a valid Podcast GooglePlay when there is only an author`() {
        val podcastGooglePlayBuilder = ValidatingPodcastGooglePlayBuilder()
            .author("author")

        assertAll {
            assertThat(podcastGooglePlayBuilder).prop(PodcastGooglePlayBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastGooglePlayBuilder.build()).isNotNull().all {
                prop(Podcast.GooglePlay::author).isEqualTo("author")
                prop(Podcast.GooglePlay::owner).isNull()
                prop(Podcast.GooglePlay::categories).isEmpty()
                prop(Podcast.GooglePlay::description).isNull()
                prop(Podcast.GooglePlay::explicit).isNull()
                prop(Podcast.GooglePlay::block).isNull()
                prop(Podcast.GooglePlay::image).isNull()
            }
        }
    }

    @Test
    internal fun `should build a valid Podcast GooglePlay when there is only an owner`() {
        val podcastGooglePlayBuilder = ValidatingPodcastGooglePlayBuilder()
            .owner("owner")

        assertAll {
            assertThat(podcastGooglePlayBuilder).prop(PodcastGooglePlayBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastGooglePlayBuilder.build()).isNotNull().all {
                prop(Podcast.GooglePlay::author).isNull()
                prop(Podcast.GooglePlay::owner).isEqualTo("owner")
                prop(Podcast.GooglePlay::categories).isEmpty()
                prop(Podcast.GooglePlay::description).isNull()
                prop(Podcast.GooglePlay::explicit).isNull()
                prop(Podcast.GooglePlay::block).isNull()
                prop(Podcast.GooglePlay::image).isNull()
            }
        }
    }

    @Test
    internal fun `should build a valid Podcast GooglePlay when there is only a category`() {
        val podcastGooglePlayBuilder = ValidatingPodcastGooglePlayBuilder()
            .addCategoryBuilder(expectedITunesCategoryBuilder)

        assertAll {
            assertThat(podcastGooglePlayBuilder).prop(PodcastGooglePlayBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastGooglePlayBuilder.build()).isNotNull().all {
                prop(Podcast.GooglePlay::author).isNull()
                prop(Podcast.GooglePlay::owner).isNull()
                prop(Podcast.GooglePlay::categories).containsExactly(expectedITunesCategoryBuilder.build())
                prop(Podcast.GooglePlay::description).isNull()
                prop(Podcast.GooglePlay::explicit).isNull()
                prop(Podcast.GooglePlay::block).isNull()
                prop(Podcast.GooglePlay::image).isNull()
            }
        }
    }

    @Test
    internal fun `should build a valid Podcast GooglePlay when there is only a description`() {
        val podcastGooglePlayBuilder = ValidatingPodcastGooglePlayBuilder()
            .description("description")

        assertAll {
            assertThat(podcastGooglePlayBuilder).prop(PodcastGooglePlayBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastGooglePlayBuilder.build()).isNotNull().all {
                prop(Podcast.GooglePlay::author).isNull()
                prop(Podcast.GooglePlay::owner).isNull()
                prop(Podcast.GooglePlay::categories).isEmpty()
                prop(Podcast.GooglePlay::description).isEqualTo("description")
                prop(Podcast.GooglePlay::explicit).isNull()
                prop(Podcast.GooglePlay::block).isNull()
                prop(Podcast.GooglePlay::image).isNull()
            }
        }
    }

    @Test
    internal fun `should build a valid Podcast GooglePlay when there is only an explicit`() {
        val podcastGooglePlayBuilder = ValidatingPodcastGooglePlayBuilder()
            .explicit(true)

        assertAll {
            assertThat(podcastGooglePlayBuilder).prop(PodcastGooglePlayBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastGooglePlayBuilder.build()).isNotNull().all {
                prop(Podcast.GooglePlay::author).isNull()
                prop(Podcast.GooglePlay::owner).isNull()
                prop(Podcast.GooglePlay::categories).isEmpty()
                prop(Podcast.GooglePlay::description).isNull()
                prop(Podcast.GooglePlay::explicit).isNotNull().isTrue()
                prop(Podcast.GooglePlay::block).isNull()
                prop(Podcast.GooglePlay::image).isNull()
            }
        }
    }

    @Test
    internal fun `should build a valid Podcast GooglePlay when there is only a block`() {
        val podcastGooglePlayBuilder = ValidatingPodcastGooglePlayBuilder()
            .block(false)

        assertAll {
            assertThat(podcastGooglePlayBuilder).prop(PodcastGooglePlayBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastGooglePlayBuilder.build()).isNotNull().all {
                prop(Podcast.GooglePlay::author).isNull()
                prop(Podcast.GooglePlay::owner).isNull()
                prop(Podcast.GooglePlay::categories).isEmpty()
                prop(Podcast.GooglePlay::description).isNull()
                prop(Podcast.GooglePlay::explicit).isNull()
                prop(Podcast.GooglePlay::block).isNotNull().isFalse()
                prop(Podcast.GooglePlay::image).isNull()
            }
        }
    }

    @Test
    internal fun `should build a valid Podcast GooglePlay when there is only an image`() {
        val podcastGooglePlayBuilder = ValidatingPodcastGooglePlayBuilder()
            .imageBuilder(expectedImageBuilder)

        assertAll {
            assertThat(podcastGooglePlayBuilder).prop(PodcastGooglePlayBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastGooglePlayBuilder.build()).isNotNull().all {
                prop(Podcast.GooglePlay::author).isNull()
                prop(Podcast.GooglePlay::owner).isNull()
                prop(Podcast.GooglePlay::categories).isEmpty()
                prop(Podcast.GooglePlay::description).isNull()
                prop(Podcast.GooglePlay::explicit).isNull()
                prop(Podcast.GooglePlay::block).isNull()
                prop(Podcast.GooglePlay::image).isEqualTo(expectedImageBuilder.build())
            }
        }
    }

    @Test
    internal fun `should build a valid Podcast GooglePlay when there are all fields`() {
        val podcastGooglePlayBuilder = ValidatingPodcastGooglePlayBuilder()
            .author("author")
            .owner("owner")
            .addCategoryBuilder(expectedITunesCategoryBuilder)
            .addCategoryBuilder(otherExpectedITunesCategoryBuilder)
            .description("description")
            .explicit(true)
            .block(false)
            .imageBuilder(expectedImageBuilder)

        assertAll {
            assertThat(podcastGooglePlayBuilder).prop(PodcastGooglePlayBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastGooglePlayBuilder.build()).isNotNull().all {
                prop(Podcast.GooglePlay::author).isEqualTo("author")
                prop(Podcast.GooglePlay::owner).isEqualTo("owner")
                prop(Podcast.GooglePlay::categories).containsExactly(
                    expectedITunesCategoryBuilder.build(),
                    otherExpectedITunesCategoryBuilder.build()
                )
                prop(Podcast.GooglePlay::description).isEqualTo("description")
                prop(Podcast.GooglePlay::explicit).isNotNull().isTrue()
                prop(Podcast.GooglePlay::block).isNotNull().isFalse()
                prop(Podcast.GooglePlay::image).isEqualTo(expectedImageBuilder.build())
            }
        }
    }
}
