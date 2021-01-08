package io.hemin.wien.builder.validating.episode

import assertk.all
import assertk.assertAll
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import io.hemin.wien.builder.episode.EpisodeAtomBuilder
import io.hemin.wien.builder.validating.ValidatingLinkBuilder
import io.hemin.wien.builder.validating.ValidatingPersonBuilder
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Link
import io.hemin.wien.model.Person
import org.junit.jupiter.api.Test

internal class ValidatingEpisodeAtomBuilderTest {

    private val expectedPersonBuilder = ValidatingPersonBuilder().name("name")

    private val expectedLinkBuilder = ValidatingLinkBuilder().href("href")

    @Test
    internal fun `should not build an Episode Atom with when all the fields are empty`() {
        val episodeAtomBuilder = ValidatingEpisodeAtomBuilder()

        assertAll {
            assertThat(episodeAtomBuilder).prop(EpisodeAtomBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodeAtomBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode Atom with at least an author`() {
        val episodeAtomBuilder = ValidatingEpisodeAtomBuilder()
            .addAuthorBuilder(expectedPersonBuilder)

        assertAll {
            assertThat(episodeAtomBuilder).prop(EpisodeAtomBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeAtomBuilder.build()).isNotNull().prop(Episode.Atom::authors).containsExactly(expectedPersonBuilder.build())
        }
    }

    @Test
    internal fun `should build an Episode Atom with at least a contributor`() {
        val episodeAtomBuilder = ValidatingEpisodeAtomBuilder()
            .addContributorBuilder(expectedPersonBuilder)

        assertAll {
            assertThat(episodeAtomBuilder).prop(EpisodeAtomBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeAtomBuilder.build()).isNotNull().prop(Episode.Atom::contributors).containsExactly(expectedPersonBuilder.build())
        }
    }

    @Test
    internal fun `should build an Episode Atom with at least a link`() {
        val episodeAtomBuilder = ValidatingEpisodeAtomBuilder()
            .addLinkBuilder(expectedLinkBuilder)

        assertAll {
            assertThat(episodeAtomBuilder).prop(EpisodeAtomBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeAtomBuilder.build()).isNotNull().prop(Episode.Atom::links).containsExactly(expectedLinkBuilder.build())
        }
    }

    @Test
    internal fun `should build an Episode Atom with with all the added entries to its fields`() {
        val episodeAtomBuilder = ValidatingEpisodeAtomBuilder()
            .addAuthorBuilder(ValidatingPersonBuilder().name("author 1"))
            .addAuthorBuilder(ValidatingPersonBuilder().name("author 2"))
            .addContributorBuilder(ValidatingPersonBuilder().name("contributor 1"))
            .addContributorBuilder(ValidatingPersonBuilder().name("contributor 2"))
            .addContributorBuilder(ValidatingPersonBuilder().name("contributor 3"))
            .addLinkBuilder(ValidatingLinkBuilder().href("link 1"))
            .addLinkBuilder(ValidatingLinkBuilder().href("link 2"))

        assertAll {
            assertThat(episodeAtomBuilder).prop(EpisodeAtomBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeAtomBuilder.build()).isNotNull().all {
                prop(Episode.Atom::authors).containsExactly(Person("author 1"), Person("author 2"))
                prop(Episode.Atom::contributors).containsExactly(Person("contributor 1"), Person("contributor 2"), Person("contributor 3"))
                prop(Episode.Atom::links).containsExactly(Link("link 1"), Link("link 2"))
            }
        }
    }
}
