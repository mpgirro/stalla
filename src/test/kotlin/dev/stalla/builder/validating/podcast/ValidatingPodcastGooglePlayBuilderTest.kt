package dev.stalla.builder.validating.podcast

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
import dev.stalla.builder.podcast.PodcastGooglePlayBuilder
import dev.stalla.builder.validating.ValidatingHrefOnlyImageBuilder
import dev.stalla.builder.validating.ValidatingITunesStyleCategoryBuilder
import dev.stalla.model.googleplay.PodcastGoogleplay
import dev.stalla.model.podcast.aPodcastGooglePlay
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
                prop(PodcastGoogleplay::author).isEqualTo("author")
                prop(PodcastGoogleplay::owner).isNull()
                prop(PodcastGoogleplay::categories).isEmpty()
                prop(PodcastGoogleplay::description).isNull()
                prop(PodcastGoogleplay::explicit).isNull()
                prop(PodcastGoogleplay::block).isFalse()
                prop(PodcastGoogleplay::image).isNull()
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
                prop(PodcastGoogleplay::author).isNull()
                prop(PodcastGoogleplay::owner).isEqualTo("owner")
                prop(PodcastGoogleplay::categories).isEmpty()
                prop(PodcastGoogleplay::description).isNull()
                prop(PodcastGoogleplay::explicit).isNull()
                prop(PodcastGoogleplay::block).isFalse()
                prop(PodcastGoogleplay::image).isNull()
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
                prop(PodcastGoogleplay::author).isNull()
                prop(PodcastGoogleplay::owner).isNull()
                prop(PodcastGoogleplay::categories).containsExactly(expectedITunesCategoryBuilder.build())
                prop(PodcastGoogleplay::description).isNull()
                prop(PodcastGoogleplay::explicit).isNull()
                prop(PodcastGoogleplay::block).isFalse()
                prop(PodcastGoogleplay::image).isNull()
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
                prop(PodcastGoogleplay::author).isNull()
                prop(PodcastGoogleplay::owner).isNull()
                prop(PodcastGoogleplay::categories).isEmpty()
                prop(PodcastGoogleplay::description).isEqualTo("description")
                prop(PodcastGoogleplay::explicit).isNull()
                prop(PodcastGoogleplay::block).isFalse()
                prop(PodcastGoogleplay::image).isNull()
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
                prop(PodcastGoogleplay::author).isNull()
                prop(PodcastGoogleplay::owner).isNull()
                prop(PodcastGoogleplay::categories).isEmpty()
                prop(PodcastGoogleplay::description).isNull()
                prop(PodcastGoogleplay::explicit).isNotNull().isTrue()
                prop(PodcastGoogleplay::block).isFalse()
                prop(PodcastGoogleplay::image).isNull()
            }
        }
    }

    @Test
    internal fun `should build a valid Podcast GooglePlay when there is only a block`() {
        val podcastGooglePlayBuilder = ValidatingPodcastGooglePlayBuilder()
            .block(true)

        assertAll {
            assertThat(podcastGooglePlayBuilder).prop(PodcastGooglePlayBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastGooglePlayBuilder.build()).isNotNull().all {
                prop(PodcastGoogleplay::author).isNull()
                prop(PodcastGoogleplay::owner).isNull()
                prop(PodcastGoogleplay::categories).isEmpty()
                prop(PodcastGoogleplay::description).isNull()
                prop(PodcastGoogleplay::explicit).isNull()
                prop(PodcastGoogleplay::block).isTrue()
                prop(PodcastGoogleplay::image).isNull()
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
                prop(PodcastGoogleplay::author).isNull()
                prop(PodcastGoogleplay::owner).isNull()
                prop(PodcastGoogleplay::categories).isEmpty()
                prop(PodcastGoogleplay::description).isNull()
                prop(PodcastGoogleplay::explicit).isNull()
                prop(PodcastGoogleplay::block).isFalse()
                prop(PodcastGoogleplay::image).isEqualTo(expectedImageBuilder.build())
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
                prop(PodcastGoogleplay::author).isEqualTo("author")
                prop(PodcastGoogleplay::owner).isEqualTo("owner")
                prop(PodcastGoogleplay::categories).containsExactly(
                    expectedITunesCategoryBuilder.build(),
                    otherExpectedITunesCategoryBuilder.build()
                )
                prop(PodcastGoogleplay::description).isEqualTo("description")
                prop(PodcastGoogleplay::explicit).isNotNull().isTrue()
                prop(PodcastGoogleplay::block).isFalse()
                prop(PodcastGoogleplay::image).isEqualTo(expectedImageBuilder.build())
            }
        }
    }

    @Test
    internal fun `should populate a Podcast GooglePlay builder with all properties from an Podcast GooglePlay model`() {
        val podcastGooglePlay = aPodcastGooglePlay()
        val podcastGooglePlayBuilder = PodcastGoogleplay.builder().from(podcastGooglePlay)

        assertAll {
            assertThat(podcastGooglePlayBuilder).prop(PodcastGooglePlayBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastGooglePlayBuilder.build()).isNotNull().isEqualTo(podcastGooglePlay)
        }
    }
}
