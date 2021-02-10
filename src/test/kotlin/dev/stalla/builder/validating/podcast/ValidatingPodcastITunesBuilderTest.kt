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
import dev.stalla.builder.podcast.PodcastITunesBuilder
import dev.stalla.builder.validating.ValidatingHrefOnlyImageBuilder
import dev.stalla.builder.validating.ValidatingITunesStyleCategoryBuilder
import dev.stalla.builder.validating.ValidatingPersonBuilder
import dev.stalla.model.itunes.PodcastItunes
import dev.stalla.model.itunes.ShowType
import dev.stalla.model.podcast.aPodcastITunes
import org.junit.jupiter.api.Test

internal class ValidatingPodcastITunesBuilderTest {

    private val expectedImageBuilder = ValidatingHrefOnlyImageBuilder().href("image href")

    private val expectedPersonBuilder = ValidatingPersonBuilder().name("name")

    private val expectedITunesCategoryBuilder = ValidatingITunesStyleCategoryBuilder()
        .category("itunes category 1")
        .subcategory("itunes subcategory")

    private val otherExpectedITunesCategoryBuilder = ValidatingITunesStyleCategoryBuilder()
        .category("itunes category 2")

    @Test
    internal fun `should not build a Podcast ITunes when all fields are missing`() {
        val podcastITunesBuilder = ValidatingPodcastITunesBuilder()

        assertAll {
            assertThat(podcastITunesBuilder).prop(PodcastITunesBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(podcastITunesBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build a valid Podcast ITunes when there are all the mandatory fields`() {
        val podcastITunesBuilder = ValidatingPodcastITunesBuilder()
            .imageBuilder(expectedImageBuilder)
            .explicit(false)
            .addCategoryBuilder(expectedITunesCategoryBuilder)

        assertAll {
            assertThat(podcastITunesBuilder).prop(PodcastITunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastITunesBuilder.build()).isNotNull().all {
                prop(PodcastItunes::explicit).isNotNull().isFalse()
                prop(PodcastItunes::subtitle).isNull()
                prop(PodcastItunes::summary).isNull()
                prop(PodcastItunes::keywords).isNull()
                prop(PodcastItunes::author).isNull()
                prop(PodcastItunes::categories).containsExactly(expectedITunesCategoryBuilder.build())
                prop(PodcastItunes::block).isFalse()
                prop(PodcastItunes::complete).isFalse()
                prop(PodcastItunes::type).isNull()
                prop(PodcastItunes::owner).isNull()
                prop(PodcastItunes::title).isNull()
                prop(PodcastItunes::newFeedUrl).isNull()
                prop(PodcastItunes::image).isEqualTo(expectedImageBuilder.build())
            }
        }
    }

    @Test
    internal fun `should build a valid Podcast ITunes when there are all fields`() {
        val podcastITunesBuilder = ValidatingPodcastITunesBuilder()
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
            assertThat(podcastITunesBuilder).prop(PodcastITunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastITunesBuilder.build()).isNotNull().all {
                prop(PodcastItunes::explicit).isNotNull().isTrue()
                prop(PodcastItunes::subtitle).isEqualTo("subtitle")
                prop(PodcastItunes::summary).isEqualTo("summary")
                prop(PodcastItunes::keywords).isEqualTo("keywords")
                prop(PodcastItunes::author).isEqualTo("author")
                prop(PodcastItunes::categories).containsExactly(expectedITunesCategoryBuilder.build(), otherExpectedITunesCategoryBuilder.build())
                prop(PodcastItunes::block).isNotNull().isFalse()
                prop(PodcastItunes::complete).isNotNull().isFalse()
                prop(PodcastItunes::type).isEqualTo(ShowType.SERIAL)
                prop(PodcastItunes::owner).isEqualTo(expectedPersonBuilder.build())
                prop(PodcastItunes::title).isEqualTo("title")
                prop(PodcastItunes::newFeedUrl).isEqualTo("newFeedUrl")
                prop(PodcastItunes::image).isEqualTo(expectedImageBuilder.build())
            }
        }
    }

    @Test
    internal fun `should populate a Podcast Itunes builder with all properties from an Podcast Itunes model`() {
        val podcastITunes = aPodcastITunes()
        val podcastITunesBuilder = PodcastItunes.builder().from(podcastITunes)

        assertAll {
            assertThat(podcastITunesBuilder).prop(PodcastITunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastITunesBuilder.build()).isNotNull().isEqualTo(podcastITunes)
        }
    }
}
