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
import dev.stalla.model.aPodcastindexGeoLocation
import dev.stalla.model.aPodcastindexOpenStreetMapElement
import dev.stalla.model.anEpisodePodcastindexLocation
import dev.stalla.model.podcastindex.PodcastindexLocation
import org.junit.jupiter.api.Test

internal class ValidatingPodcastindexLocationBuilderTest {

    @Test
    internal fun `should not build an Podcastindex Location with when all the fields are missing`() {
        val locationBuilder = ValidatingPodcastindexLocationBuilder()

        assertAll {
            assertThat(locationBuilder).prop(PodcastindexLocationBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(locationBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build an Podcastindex Location with when the name field is missing`() {
        val locationBuilder = ValidatingPodcastindexLocationBuilder()
            .geo(aPodcastindexGeoLocation())

        assertAll {
            assertThat(locationBuilder).prop(PodcastindexLocationBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(locationBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Podcastindex Location with all the mandatory fields`() {
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
    internal fun `should build an Podcastindex Location with all the added entries to its fields`() {
        val locationBuilder = ValidatingPodcastindexLocationBuilder()
            .name("name")
            .geo(aPodcastindexGeoLocation())
            .osm(aPodcastindexOpenStreetMapElement())

        assertAll {
            assertThat(locationBuilder).prop(PodcastindexLocationBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(locationBuilder.build()).isNotNull().all {
                prop(PodcastindexLocation::name).isEqualTo("name")
                prop(PodcastindexLocation::geo).isEqualTo(aPodcastindexGeoLocation())
                prop(PodcastindexLocation::osm).isEqualTo(aPodcastindexOpenStreetMapElement())
            }
        }
    }

    @Test
    internal fun `should populate an Podcastindex Location builder with all properties from an Podcastindex Location model`() {
        val location = anEpisodePodcastindexLocation()
        val locationBuilder = PodcastindexLocation.builder().applyFrom(location)

        assertAll {
            assertThat(locationBuilder).prop(PodcastindexLocationBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(locationBuilder.build()).isNotNull().isEqualTo(location)
        }
    }
}
