package io.hemin.wien.builder.validating.podcast

import assertk.all
import assertk.assertAll
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import io.hemin.wien.builder.episode.EpisodePodloveBuilder
import io.hemin.wien.builder.podcast.PodcastAtomBuilder
import io.hemin.wien.builder.validating.ValidatingLinkBuilder
import io.hemin.wien.builder.validating.ValidatingPersonBuilder
import io.hemin.wien.model.Link
import io.hemin.wien.model.Person
import io.hemin.wien.model.Podcast
import org.junit.jupiter.api.Test

internal class ValidatingPodcastAtomBuilderTest {

    private val expectedPersonBuilder = ValidatingPersonBuilder().name("name")

    private val expectedLinkBuilder = ValidatingLinkBuilder().href("href")

    @Test
    internal fun `should not build a Podcast Atom with when all the fields are empty`() {
        val podcastAtomBuilder = ValidatingPodcastAtomBuilder()

        assertAll {
            assertThat(podcastAtomBuilder).prop(PodcastAtomBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(podcastAtomBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build a Podcast Atom with at least an author`() {
        val podcastAtomBuilder = ValidatingPodcastAtomBuilder()
            .addAuthorBuilder(expectedPersonBuilder)

        assertAll {
            assertThat(podcastAtomBuilder).prop(PodcastAtomBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastAtomBuilder.build()).isNotNull().all {
                prop(Podcast.Atom::authors).containsExactly(expectedPersonBuilder.build())
            }
        }
    }

    @Test
    internal fun `should build a Podcast Atom with at least a contributor`() {
        val podcastAtomBuilder = ValidatingPodcastAtomBuilder()
            .addContributorBuilder(expectedPersonBuilder)

        assertAll {
            assertThat(podcastAtomBuilder).prop(PodcastAtomBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastAtomBuilder.build()).isNotNull().all {
                prop(Podcast.Atom::contributors).containsExactly(expectedPersonBuilder.build())
            }
        }
    }

    @Test
    internal fun `should build a Podcast Atom with at least a link`() {
        val podcastAtomBuilder = ValidatingPodcastAtomBuilder()
            .addLinkBuilder(expectedLinkBuilder)

        assertAll {
            assertThat(podcastAtomBuilder).prop(PodcastAtomBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastAtomBuilder.build()).isNotNull().all {
                prop(Podcast.Atom::links).containsExactly(expectedLinkBuilder.build())
            }
        }
    }

    @Test
    internal fun `should build a Podcast Atom with with all the added entries to its fields`() {
        val podcastAtomBuilder = ValidatingPodcastAtomBuilder()
            .addAuthorBuilder(ValidatingPersonBuilder().name("author 1"))
            .addAuthorBuilder(ValidatingPersonBuilder().name("author 2"))
            .addContributorBuilder(ValidatingPersonBuilder().name("contributor 1"))
            .addContributorBuilder(ValidatingPersonBuilder().name("contributor 2"))
            .addContributorBuilder(ValidatingPersonBuilder().name("contributor 3"))
            .addLinkBuilder(ValidatingLinkBuilder().href("link 1"))
            .addLinkBuilder(ValidatingLinkBuilder().href("link 2"))

        assertAll {
            assertThat(podcastAtomBuilder).prop(PodcastAtomBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastAtomBuilder.build()).isNotNull().all {
                prop(Podcast.Atom::authors).containsExactly(Person("author 1"), Person("author 2"))
                prop(Podcast.Atom::contributors).containsExactly(Person("contributor 1"), Person("contributor 2"), Person("contributor 3"))
                prop(Podcast.Atom::links).containsExactly(Link("link 1"), Link("link 2"))
            }
        }
    }
}
