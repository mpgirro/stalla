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
import dev.stalla.builder.podcast.PodcastGoogleplayBuilder2
import dev.stalla.builder.validating.ValidatingHrefOnlyImageBuilder
import dev.stalla.builder.validating.ValidatingItunesStyleCategoryBuilder2
import dev.stalla.model.googleplay.PodcastGoogleplay2
import dev.stalla.model.podcast.aPodcastGoogleplay
import org.junit.jupiter.api.Test

internal class ValidatingPodcastGoogleplayBuilder2Test {

    private val expectedImageBuilder = ValidatingHrefOnlyImageBuilder().href("image href")

    private val expectedITunesCategoryBuilder = ValidatingItunesStyleCategoryBuilder2()
        .category("googleplay category")
        .subcategory("googleplay subcategory")

    private val otherExpectedITunesCategoryBuilder = ValidatingItunesStyleCategoryBuilder2()
        .category("googleplay category 2")

    @Test
    internal fun `should not build a Podcast GooglePlay when all fields are missing`() {
        val podcastGooglePlayBuilder = ValidatingPodcastGoogleplayBuilder2()

        assertAll {
            assertThat(podcastGooglePlayBuilder).prop(PodcastGoogleplayBuilder2::hasEnoughDataToBuild).isFalse()

            assertThat(podcastGooglePlayBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build a valid Podcast GooglePlay when there is only an author`() {
        val podcastGooglePlayBuilder = ValidatingPodcastGoogleplayBuilder2()
            .author("author")

        assertAll {
            assertThat(podcastGooglePlayBuilder).prop(PodcastGoogleplayBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(podcastGooglePlayBuilder.build()).isNotNull().all {
                prop(PodcastGoogleplay2::author).isEqualTo("author")
                prop(PodcastGoogleplay2::owner).isNull()
                prop(PodcastGoogleplay2::categories).isEmpty()
                prop(PodcastGoogleplay2::description).isNull()
                prop(PodcastGoogleplay2::explicit).isNull()
                prop(PodcastGoogleplay2::block).isFalse()
                prop(PodcastGoogleplay2::image).isNull()
            }
        }
    }

    @Test
    internal fun `should build a valid Podcast GooglePlay when there is only an owner`() {
        val podcastGooglePlayBuilder = ValidatingPodcastGoogleplayBuilder2()
            .owner("owner")

        assertAll {
            assertThat(podcastGooglePlayBuilder).prop(PodcastGoogleplayBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(podcastGooglePlayBuilder.build()).isNotNull().all {
                prop(PodcastGoogleplay2::author).isNull()
                prop(PodcastGoogleplay2::owner).isEqualTo("owner")
                prop(PodcastGoogleplay2::categories).isEmpty()
                prop(PodcastGoogleplay2::description).isNull()
                prop(PodcastGoogleplay2::explicit).isNull()
                prop(PodcastGoogleplay2::block).isFalse()
                prop(PodcastGoogleplay2::image).isNull()
            }
        }
    }

    @Test
    internal fun `should build a valid Podcast GooglePlay when there is only a category`() {
        val podcastGooglePlayBuilder = ValidatingPodcastGoogleplayBuilder2()
            .addCategoryBuilder(expectedITunesCategoryBuilder)

        assertAll {
            assertThat(podcastGooglePlayBuilder).prop(PodcastGoogleplayBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(podcastGooglePlayBuilder.build()).isNotNull().all {
                prop(PodcastGoogleplay2::author).isNull()
                prop(PodcastGoogleplay2::owner).isNull()
                prop(PodcastGoogleplay2::categories).containsExactly(expectedITunesCategoryBuilder.build())
                prop(PodcastGoogleplay2::description).isNull()
                prop(PodcastGoogleplay2::explicit).isNull()
                prop(PodcastGoogleplay2::block).isFalse()
                prop(PodcastGoogleplay2::image).isNull()
            }
        }
    }

    @Test
    internal fun `should build a valid Podcast GooglePlay when there is only a description`() {
        val podcastGooglePlayBuilder = ValidatingPodcastGoogleplayBuilder2()
            .description("description")

        assertAll {
            assertThat(podcastGooglePlayBuilder).prop(PodcastGoogleplayBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(podcastGooglePlayBuilder.build()).isNotNull().all {
                prop(PodcastGoogleplay2::author).isNull()
                prop(PodcastGoogleplay2::owner).isNull()
                prop(PodcastGoogleplay2::categories).isEmpty()
                prop(PodcastGoogleplay2::description).isEqualTo("description")
                prop(PodcastGoogleplay2::explicit).isNull()
                prop(PodcastGoogleplay2::block).isFalse()
                prop(PodcastGoogleplay2::image).isNull()
            }
        }
    }

    @Test
    internal fun `should build a valid Podcast GooglePlay when there is only an explicit`() {
        val podcastGooglePlayBuilder = ValidatingPodcastGoogleplayBuilder2()
            .explicit(true)

        assertAll {
            assertThat(podcastGooglePlayBuilder).prop(PodcastGoogleplayBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(podcastGooglePlayBuilder.build()).isNotNull().all {
                prop(PodcastGoogleplay2::author).isNull()
                prop(PodcastGoogleplay2::owner).isNull()
                prop(PodcastGoogleplay2::categories).isEmpty()
                prop(PodcastGoogleplay2::description).isNull()
                prop(PodcastGoogleplay2::explicit).isNotNull().isTrue()
                prop(PodcastGoogleplay2::block).isFalse()
                prop(PodcastGoogleplay2::image).isNull()
            }
        }
    }

    @Test
    internal fun `should build a valid Podcast GooglePlay when there is only a block`() {
        val podcastGooglePlayBuilder = ValidatingPodcastGoogleplayBuilder2()
            .block(true)

        assertAll {
            assertThat(podcastGooglePlayBuilder).prop(PodcastGoogleplayBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(podcastGooglePlayBuilder.build()).isNotNull().all {
                prop(PodcastGoogleplay2::author).isNull()
                prop(PodcastGoogleplay2::owner).isNull()
                prop(PodcastGoogleplay2::categories).isEmpty()
                prop(PodcastGoogleplay2::description).isNull()
                prop(PodcastGoogleplay2::explicit).isNull()
                prop(PodcastGoogleplay2::block).isTrue()
                prop(PodcastGoogleplay2::image).isNull()
            }
        }
    }

    @Test
    internal fun `should build a valid Podcast GooglePlay when there is only an image`() {
        val podcastGooglePlayBuilder = ValidatingPodcastGoogleplayBuilder2()
            .imageBuilder(expectedImageBuilder)

        assertAll {
            assertThat(podcastGooglePlayBuilder).prop(PodcastGoogleplayBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(podcastGooglePlayBuilder.build()).isNotNull().all {
                prop(PodcastGoogleplay2::author).isNull()
                prop(PodcastGoogleplay2::owner).isNull()
                prop(PodcastGoogleplay2::categories).isEmpty()
                prop(PodcastGoogleplay2::description).isNull()
                prop(PodcastGoogleplay2::explicit).isNull()
                prop(PodcastGoogleplay2::block).isFalse()
                prop(PodcastGoogleplay2::image).isEqualTo(expectedImageBuilder.build())
            }
        }
    }

    @Test
    internal fun `should build a valid Podcast GooglePlay when there are all fields`() {
        val podcastGooglePlayBuilder = ValidatingPodcastGoogleplayBuilder2()
            .author("author")
            .owner("owner")
            .addCategoryBuilder(expectedITunesCategoryBuilder)
            .addCategoryBuilder(otherExpectedITunesCategoryBuilder)
            .description("description")
            .explicit(true)
            .block(false)
            .imageBuilder(expectedImageBuilder)

        assertAll {
            assertThat(podcastGooglePlayBuilder).prop(PodcastGoogleplayBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(podcastGooglePlayBuilder.build()).isNotNull().all {
                prop(PodcastGoogleplay2::author).isEqualTo("author")
                prop(PodcastGoogleplay2::owner).isEqualTo("owner")
                prop(PodcastGoogleplay2::categories).containsExactly(
                    expectedITunesCategoryBuilder.build(),
                    otherExpectedITunesCategoryBuilder.build()
                )
                prop(PodcastGoogleplay2::description).isEqualTo("description")
                prop(PodcastGoogleplay2::explicit).isNotNull().isTrue()
                prop(PodcastGoogleplay2::block).isFalse()
                prop(PodcastGoogleplay2::image).isEqualTo(expectedImageBuilder.build())
            }
        }
    }

    @Test
    internal fun `should populate a Podcast GooglePlay builder with all properties from an Podcast GooglePlay model`() {
        val podcastGooglePlay = aPodcastGoogleplay()
        val podcastGooglePlayBuilder = PodcastGoogleplay2.builder().from(podcastGooglePlay)

        assertAll {
            assertThat(podcastGooglePlayBuilder).prop(PodcastGoogleplayBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(podcastGooglePlayBuilder.build()).isNotNull().isEqualTo(podcastGooglePlay)
        }
    }
}
