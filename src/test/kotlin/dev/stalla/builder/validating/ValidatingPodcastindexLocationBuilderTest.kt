package dev.stalla.builder.validating

import assertk.all
import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import dev.stalla.builder.PodcastindexLocationBuilder
import dev.stalla.model.aPodcastindexGeographicLocation
import dev.stalla.model.aPodcastindexOpenStreetMapElement
import dev.stalla.model.anEpisodePodcastindexLocation
import dev.stalla.model.podcastindex.PodcastindexLocation
import org.junit.jupiter.api.Test

internal class ValidatingPodcastindexLocationBuilderTest {

    @Test
    internal fun `should not build a Podcastindex Location when all the fields are missing`() {
        val locationBuilder = ValidatingPodcastindexLocationBuilder()

        assertAll {
            assertThat(locationBuilder).prop(PodcastindexLocationBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(locationBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build a Podcastindex Location when the mandatory name field is missing`() {
        val locationBuilder = ValidatingPodcastindexLocationBuilder()
            .geo(aPodcastindexGeographicLocation())

        assertAll {
            assertThat(locationBuilder).prop(PodcastindexLocationBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(locationBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build a Podcastindex Location with only the mandatory name field`() {
        val locationBuilder = ValidatingPodcastindexLocationBuilder()
            .name("name")

        assertAll {
            assertThat(locationBuilder).prop(PodcastindexLocationBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(locationBuilder.build()).isNotNull().all {
                prop(PodcastindexLocation::name).isEqualTo("name")
                prop(PodcastindexLocation::geo).isNull()
                prop(PodcastindexLocation::osm).isNull()
            }
        }
    }

    @Test
    internal fun `should build a Podcastindex Location with all fields filled in correctly`() {
        val locationBuilder = ValidatingPodcastindexLocationBuilder()
            .name("name")
            .geo(aPodcastindexGeographicLocation())
            .osm(aPodcastindexOpenStreetMapElement())

        assertAll {
            assertThat(locationBuilder).prop(PodcastindexLocationBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(locationBuilder.build()).isNotNull().all {
                prop(PodcastindexLocation::name).isEqualTo("name")
                prop(PodcastindexLocation::geo).isEqualTo(aPodcastindexGeographicLocation())
                prop(PodcastindexLocation::osm).isEqualTo(aPodcastindexOpenStreetMapElement())
            }
        }
    }

    @Test
    internal fun `should populate a Podcastindex Location builder with all properties from a Podcastindex Location model`() {
        val location = anEpisodePodcastindexLocation()
        val locationBuilder = PodcastindexLocation.builder().applyFrom(location)

        assertAll {
            assertThat(locationBuilder).prop(PodcastindexLocationBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(locationBuilder.build()).isNotNull().isEqualTo(location)
        }
    }
}
