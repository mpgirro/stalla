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
import dev.stalla.builder.PodcastindexPersonBuilder
import dev.stalla.model.aPodcastindexPerson
import dev.stalla.model.podcastindex.PodcastindexPerson
import org.junit.jupiter.api.Test

internal class ValidatingPodcastindexPersonBuilderTest {

    @Test
    internal fun `should not build an Podcastindex Person with when all the fields are missing`() {
        val personBuilder = ValidatingPodcastindexPersonBuilder()

        assertAll {
            assertThat(personBuilder).prop(PodcastindexPersonBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(personBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build an Podcastindex Person with when the name field is missing`() {
        val personBuilder = ValidatingPodcastindexPersonBuilder()
            .role("role")

        assertAll {
            assertThat(personBuilder).prop(PodcastindexPersonBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(personBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Podcastindex Person with all the mandatory fields`() {
        val personBuilder = ValidatingPodcastindexPersonBuilder()
            .name("name")

        assertAll {
            assertThat(personBuilder).prop(PodcastindexPersonBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(personBuilder.build()).isNotNull().all {
                prop(PodcastindexPerson::name).isEqualTo("name")
                prop(PodcastindexPerson::role).isNull()
                prop(PodcastindexPerson::group).isNull()
                prop(PodcastindexPerson::img).isNull()
                prop(PodcastindexPerson::href).isNull()
            }
        }
    }

    @Test
    internal fun `should build an Podcastindex Person with all the added entries to its fields`() {
        val personBuilder = ValidatingPodcastindexPersonBuilder()
            .name("name")
            .role("role")
            .group("group")
            .img("img")
            .href("href")

        assertAll {
            assertThat(personBuilder).prop(PodcastindexPersonBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(personBuilder.build()).isNotNull().all {
                prop(PodcastindexPerson::name).isEqualTo("name")
                prop(PodcastindexPerson::role).isEqualTo("role")
                prop(PodcastindexPerson::group).isEqualTo("group")
                prop(PodcastindexPerson::img).isEqualTo("img")
                prop(PodcastindexPerson::href).isEqualTo("href")
            }
        }
    }

    @Test
    internal fun `should populate an Podcastindex Person builder with all properties from an Podcastindex Person model`() {
        val person = aPodcastindexPerson()
        val personBuilder = PodcastindexPerson.builder().applyFrom(person)

        assertAll {
            assertThat(personBuilder).prop(PodcastindexPersonBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(personBuilder.build()).isNotNull().isEqualTo(person)
        }
    }

}
