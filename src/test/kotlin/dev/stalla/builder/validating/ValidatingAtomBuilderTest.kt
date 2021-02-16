package dev.stalla.builder.validating

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
import dev.stalla.builder.AtomBuilder
import dev.stalla.model.Person
import dev.stalla.model.atom.Atom
import dev.stalla.model.atom.Link
import dev.stalla.model.episode.anEpisodeAtom
import org.junit.jupiter.api.Test

internal class ValidatingAtomBuilderTest {

    private val expectedPersonBuilder = ValidatingPersonBuilder().name("name")

    private val expectedLinkBuilder = ValidatingLinkBuilder().href("href")

    @Test
    internal fun `should not build an Episode Atom with when all the fields are empty`() {
        val episodeAtomBuilder = ValidatingAtomBuilder()

        assertAll {
            assertThat(episodeAtomBuilder).prop(AtomBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodeAtomBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode Atom with at least an author`() {
        val episodeAtomBuilder = ValidatingAtomBuilder()
            .addAuthorBuilder(expectedPersonBuilder)

        assertAll {
            assertThat(episodeAtomBuilder).prop(AtomBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeAtomBuilder.build()).isNotNull().prop(Atom::authors).containsExactly(expectedPersonBuilder.build())
        }
    }

    @Test
    internal fun `should build an Episode Atom with at least a contributor`() {
        val episodeAtomBuilder = ValidatingAtomBuilder()
            .addContributorBuilder(expectedPersonBuilder)

        assertAll {
            assertThat(episodeAtomBuilder).prop(AtomBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeAtomBuilder.build()).isNotNull().prop(Atom::contributors).containsExactly(expectedPersonBuilder.build())
        }
    }

    @Test
    internal fun `should build an Episode Atom with at least a link`() {
        val episodeAtomBuilder = ValidatingAtomBuilder()
            .addLinkBuilder(expectedLinkBuilder)

        assertAll {
            assertThat(episodeAtomBuilder).prop(AtomBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeAtomBuilder.build()).isNotNull().prop(Atom::links).containsExactly(expectedLinkBuilder.build())
        }
    }

    @Test
    internal fun `should build an Episode Atom with with all the added entries to its fields`() {
        val episodeAtomBuilder = ValidatingAtomBuilder()
            .addAuthorBuilder(ValidatingPersonBuilder().name("author 1"))
            .addAuthorBuilder(ValidatingPersonBuilder().name("author 2"))
            .addContributorBuilder(ValidatingPersonBuilder().name("contributor 1"))
            .addContributorBuilder(ValidatingPersonBuilder().name("contributor 2"))
            .addContributorBuilder(ValidatingPersonBuilder().name("contributor 3"))
            .addLinkBuilder(ValidatingLinkBuilder().href("link 1"))
            .addLinkBuilder(ValidatingLinkBuilder().href("link 2"))

        assertAll {
            assertThat(episodeAtomBuilder).prop(AtomBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeAtomBuilder.build()).isNotNull().all {
                prop(Atom::authors).containsExactly(Person("author 1"), Person("author 2"))
                prop(Atom::contributors).containsExactly(Person("contributor 1"), Person("contributor 2"), Person("contributor 3"))
                prop(Atom::links).containsExactly(Link("link 1"), Link("link 2"))
            }
        }
    }

    @Test
    internal fun `should populate an Atom builder with all properties from an Atom model`() {
        val atom = anEpisodeAtom()
        val atomBuilder = Atom.builder().applyFrom(atom)

        assertAll {
            assertThat(atomBuilder).prop(AtomBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(atomBuilder.build()).isNotNull().isEqualTo(atom)
        }
    }
}
