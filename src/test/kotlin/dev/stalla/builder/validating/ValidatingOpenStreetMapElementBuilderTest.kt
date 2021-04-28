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
import dev.stalla.builder.OpenStreetMapElementBuilder
import dev.stalla.model.aPodcastindexOpenStreetMapElement
import dev.stalla.model.podcastindex.OpenStreetMapElement
import dev.stalla.model.podcastindex.OpenStreetMapElementType
import org.junit.jupiter.api.Test

internal class ValidatingOpenStreetMapElementBuilderTest {

    @Test
    internal fun `should not build an OpenStreetMap element when the mandatory fields are missing`() {
        val osmBuilder = ValidatingOpenStreetMapElementBuilder()

        assertAll {
            assertThat(osmBuilder).prop(OpenStreetMapElementBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(osmBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build a OpenStreetMap element with all the mandatory href field`() {
        val osmBuilder = ValidatingOpenStreetMapElementBuilder()
            .type(OpenStreetMapElementType.Relation)
            .id(1L)

        assertAll {
            assertThat(osmBuilder).prop(OpenStreetMapElementBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(osmBuilder.build()).isNotNull().all {
                prop(OpenStreetMapElement::type).isEqualTo(OpenStreetMapElementType.Relation)
                prop(OpenStreetMapElement::id).isEqualTo(1L)
            }
        }
    }

    @Test
    internal fun `should populate a OpenStreetMap element builder with all properties from an GeographicLocation model`() {
        val osm = aPodcastindexOpenStreetMapElement()
        val osmBuilder = OpenStreetMapElement.builder().applyFrom(osm)

        assertAll {
            assertThat(osmBuilder).prop(OpenStreetMapElementBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(osmBuilder.build()).isNotNull().isEqualTo(osm)
        }
    }
}
