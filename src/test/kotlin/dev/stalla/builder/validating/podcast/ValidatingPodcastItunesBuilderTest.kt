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
import dev.stalla.builder.podcast.PodcastItunesBuilder
import dev.stalla.builder.validating.ValidatingHrefOnlyImageBuilder
import dev.stalla.builder.validating.ValidatingPersonBuilder
import dev.stalla.model.itunes.ItunesCategory
import dev.stalla.model.itunes.PodcastItunes
import dev.stalla.model.itunes.ShowType
import dev.stalla.model.podcast.aPodcastItunes
import org.junit.jupiter.api.Test

internal class ValidatingPodcastItunesBuilderTest {

    private val expectedImageBuilder = ValidatingHrefOnlyImageBuilder().href("image href")

    private val expectedPersonBuilder = ValidatingPersonBuilder().name("name")

    private val expectedItunesCategory = ItunesCategory.TECHNOLOGY

    private val otherExpectedItunesCategory = ItunesCategory.ARTS

    @Test
    internal fun `should not build a Podcast Itunes when all fields are missing`() {
        val podcastItunesBuilder = ValidatingPodcastItunesBuilder()

        assertAll {
            assertThat(podcastItunesBuilder).prop(PodcastItunesBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(podcastItunesBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build a valid Podcast Itunes when there are all the mandatory fields`() {
        val podcastItunesBuilder = ValidatingPodcastItunesBuilder()
            .imageBuilder(expectedImageBuilder)
            .explicit(false)
            .addCategory(expectedItunesCategory)

        assertAll {
            assertThat(podcastItunesBuilder).prop(PodcastItunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastItunesBuilder.build()).isNotNull().all {
                prop(PodcastItunes::explicit).isNotNull().isFalse()
                prop(PodcastItunes::subtitle).isNull()
                prop(PodcastItunes::summary).isNull()
                prop(PodcastItunes::keywords).isNull()
                prop(PodcastItunes::author).isNull()
                prop(PodcastItunes::categories).containsExactly(expectedItunesCategory)
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
    internal fun `should build a valid Podcast Itunes when there are all fields`() {
        val podcastItunesBuilder = ValidatingPodcastItunesBuilder()
            .explicit(true)
            .subtitle("subtitle")
            .summary("summary")
            .keywords("keywords")
            .author("author")
            .addCategory(expectedItunesCategory)
            .addCategory(otherExpectedItunesCategory)
            .block(false)
            .complete(false)
            .type(ShowType.SERIAL.type)
            .ownerBuilder(expectedPersonBuilder)
            .title("title")
            .newFeedUrl("newFeedUrl")
            .imageBuilder(expectedImageBuilder)

        assertAll {
            assertThat(podcastItunesBuilder).prop(PodcastItunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastItunesBuilder.build()).isNotNull().all {
                prop(PodcastItunes::explicit).isNotNull().isTrue()
                prop(PodcastItunes::subtitle).isEqualTo("subtitle")
                prop(PodcastItunes::summary).isEqualTo("summary")
                prop(PodcastItunes::keywords).isEqualTo("keywords")
                prop(PodcastItunes::author).isEqualTo("author")
                prop(PodcastItunes::categories).containsExactly(expectedItunesCategory, otherExpectedItunesCategory)
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
        val podcastItunes = aPodcastItunes()
        val podcastItunesBuilder = PodcastItunes.builder().applyFrom(podcastItunes)

        assertAll {
            assertThat(podcastItunesBuilder).prop(PodcastItunesBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastItunesBuilder.build()).isNotNull().isEqualTo(podcastItunes)
        }
    }
}
