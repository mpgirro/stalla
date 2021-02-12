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
import dev.stalla.builder.podcast.PodcastItunesBuilder2
import dev.stalla.builder.validating.ValidatingHrefOnlyImageBuilder
import dev.stalla.builder.validating.ValidatingItunesStyleCategoryBuilder2
import dev.stalla.builder.validating.ValidatingPersonBuilder
import dev.stalla.model.itunes.PodcastItunes2
import dev.stalla.model.itunes.ShowType
import dev.stalla.model.podcast.aPodcastItunes
import org.junit.jupiter.api.Test

internal class ValidatingPodcastItunesBuilder2Test {

    private val expectedImageBuilder = ValidatingHrefOnlyImageBuilder().href("image href")

    private val expectedPersonBuilder = ValidatingPersonBuilder().name("name")

    private val expectedITunesCategoryBuilder = ValidatingItunesStyleCategoryBuilder2()
        .category("itunes category 1")
        .subcategory("itunes subcategory")

    private val otherExpectedITunesCategoryBuilder = ValidatingItunesStyleCategoryBuilder2()
        .category("itunes category 2")

    @Test
    internal fun `should not build a Podcast ITunes when all fields are missing`() {
        val podcastITunesBuilder = ValidatingPodcastItunesBuilder2()

        assertAll {
            assertThat(podcastITunesBuilder).prop(PodcastItunesBuilder2::hasEnoughDataToBuild).isFalse()

            assertThat(podcastITunesBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build a valid Podcast ITunes when there are all the mandatory fields`() {
        val podcastITunesBuilder = ValidatingPodcastItunesBuilder2()
            .imageBuilder(expectedImageBuilder)
            .explicit(false)
            .addCategoryBuilder(expectedITunesCategoryBuilder)

        assertAll {
            assertThat(podcastITunesBuilder).prop(PodcastItunesBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(podcastITunesBuilder.build()).isNotNull().all {
                prop(PodcastItunes2::explicit).isNotNull().isFalse()
                prop(PodcastItunes2::subtitle).isNull()
                prop(PodcastItunes2::summary).isNull()
                prop(PodcastItunes2::keywords).isNull()
                prop(PodcastItunes2::author).isNull()
                prop(PodcastItunes2::categories).containsExactly(expectedITunesCategoryBuilder.build())
                prop(PodcastItunes2::block).isFalse()
                prop(PodcastItunes2::complete).isFalse()
                prop(PodcastItunes2::type).isNull()
                prop(PodcastItunes2::owner).isNull()
                prop(PodcastItunes2::title).isNull()
                prop(PodcastItunes2::newFeedUrl).isNull()
                prop(PodcastItunes2::image).isEqualTo(expectedImageBuilder.build())
            }
        }
    }

    @Test
    internal fun `should build a valid Podcast ITunes when there are all fields`() {
        val podcastITunesBuilder = ValidatingPodcastItunesBuilder2()
            .explicit(true)
            .subtitle("subtitle")
            .summary("summary")
            .keywords("keywords")
            .author("author")
            .addCategoryBuilder(expectedITunesCategoryBuilder)
            .addCategoryBuilder(otherExpectedITunesCategoryBuilder)
            .block(false)
            .complete(false)
            .type(ShowType.SERIAL.type)
            .ownerBuilder(expectedPersonBuilder)
            .title("title")
            .newFeedUrl("newFeedUrl")
            .imageBuilder(expectedImageBuilder)

        assertAll {
            assertThat(podcastITunesBuilder).prop(PodcastItunesBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(podcastITunesBuilder.build()).isNotNull().all {
                prop(PodcastItunes2::explicit).isNotNull().isTrue()
                prop(PodcastItunes2::subtitle).isEqualTo("subtitle")
                prop(PodcastItunes2::summary).isEqualTo("summary")
                prop(PodcastItunes2::keywords).isEqualTo("keywords")
                prop(PodcastItunes2::author).isEqualTo("author")
                prop(PodcastItunes2::categories).containsExactly(expectedITunesCategoryBuilder.build(), otherExpectedITunesCategoryBuilder.build())
                prop(PodcastItunes2::block).isNotNull().isFalse()
                prop(PodcastItunes2::complete).isNotNull().isFalse()
                prop(PodcastItunes2::type).isEqualTo(ShowType.SERIAL)
                prop(PodcastItunes2::owner).isEqualTo(expectedPersonBuilder.build())
                prop(PodcastItunes2::title).isEqualTo("title")
                prop(PodcastItunes2::newFeedUrl).isEqualTo("newFeedUrl")
                prop(PodcastItunes2::image).isEqualTo(expectedImageBuilder.build())
            }
        }
    }

    @Test
    internal fun `should populate a Podcast Itunes builder with all properties from an Podcast Itunes model`() {
        val podcastITunes = aPodcastItunes()
        val podcastITunesBuilder = PodcastItunes2.builder().from(podcastITunes)

        assertAll {
            assertThat(podcastITunesBuilder).prop(PodcastItunesBuilder2::hasEnoughDataToBuild).isTrue()

            assertThat(podcastITunesBuilder.build()).isNotNull().isEqualTo(podcastITunes)
        }
    }
}
