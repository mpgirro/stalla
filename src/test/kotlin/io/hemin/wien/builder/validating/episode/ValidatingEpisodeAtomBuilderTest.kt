package io.hemin.wien.builder.validating.episode

import assertk.all
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Link
import io.hemin.wien.model.Person
import org.junit.jupiter.api.Test

internal class ValidatingEpisodeAtomBuilderTest {

    private val aPerson = Person(name = "name")

    private val aLink = Link(href = "href")

    @Test
    internal fun `should not build an Episode Atom with when all the fields are empty`() {
        val episodeAtomBuilder = ValidatingEpisodeAtomBuilder()

        assertThat(episodeAtomBuilder.build()).isNull()
    }

    @Test
    internal fun `should build an Episode Atom with at least an author`() {
        val episodeAtomBuilder = ValidatingEpisodeAtomBuilder()
            .addAuthor(aPerson)

        assertThat(episodeAtomBuilder.build()).isNotNull().all {
            prop(Episode.Atom::authors).containsExactly(aPerson)
        }
    }

    @Test
    internal fun `should build an Episode Atom with at least a contributor`() {
        val episodeAtomBuilder = ValidatingEpisodeAtomBuilder()
            .addContributor(aPerson)

        assertThat(episodeAtomBuilder.build()).isNotNull().all {
            prop(Episode.Atom::contributors).containsExactly(aPerson)
        }
    }

    @Test
    internal fun `should build an Episode Atom with at least a link`() {
        val episodeAtomBuilder = ValidatingEpisodeAtomBuilder()
            .addLink(aLink)

        assertThat(episodeAtomBuilder.build()).isNotNull().all {
            prop(Episode.Atom::links).containsExactly(aLink)
        }
    }

    @Test
    internal fun `should build an Episode Atom with with all the added entries to its fields`() {
        val episodeAtomBuilder = ValidatingEpisodeAtomBuilder()
            .addAuthor(Person("author 1"))
            .addAuthor(Person("author 2"))
            .addContributor(Person("contributor 1"))
            .addContributor(Person("contributor 2"))
            .addContributor(Person("contributor 3"))
            .addLink(Link("link 1"))
            .addLink(Link("link 2"))

        assertThat(episodeAtomBuilder.build()).isNotNull().all {
            prop(Episode.Atom::authors).containsExactly(Person("author 1"), Person("author 2"))
            prop(Episode.Atom::contributors).containsExactly(Person("contributor 1"), Person("contributor 2"), Person("contributor 3"))
            prop(Episode.Atom::links).containsExactly(Link("link 1"), Link("link 2"))
        }
    }
}

