package dev.stalla.builder.validating.episode

import assertk.all
import assertk.assertAll
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import dev.stalla.builder.episode.EpisodeBuilder
import dev.stalla.builder.validating.ValidatingPersonBuilder
import dev.stalla.builder.validating.ValidatingRssCategoryBuilder
import dev.stalla.dateTime
import dev.stalla.model.Atom
import dev.stalla.model.Episode
import dev.stalla.model.episode.anEpisode
import dev.stalla.model.itunes.EpisodeItunes
import org.junit.jupiter.api.Test
import java.time.Month

internal class ValidatingEpisodeBuilderTest {

    private val expectedDate = dateTime(year = 2018, month = Month.MARCH, day = 16, hour = 22, minute = 49, second = 8)

    private val expectedEnclosureBuilder = ValidatingEpisodeEnclosureBuilder()
        .url("enclosure url")
        .length(123)
        .type("enclosure type")

    private val expectedAtomAuthorBuilder = ValidatingPersonBuilder().name("atom author")

    private val expectedSimpleChapterBuilder = ValidatingEpisodePodloveSimpleChapterBuilder()
        .start("start")
        .title("chapter title")

    private val expectedCategoryBuilders = listOf(
        ValidatingRssCategoryBuilder().category("category 1").domain("domain"),
        ValidatingRssCategoryBuilder().category("category 2")
    )

    private val expectedPodcastChaptersBuilder = ValidatingEpisodePodcastChaptersBuilder()
        .url("https://example.com/episode/chapters.json")
        .type("application/json+chapters")

    @Test
    internal fun `should not build a episode when the mandatory fields are missing`() {
        val episodeBuilder = ValidatingEpisodeBuilder()

        assertAll {
            assertThat(episodeBuilder).prop(EpisodeBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodeBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build a episode when the mandatory title is missing`() {
        val episodeBuilder = ValidatingEpisodeBuilder()
            .enclosureBuilder(expectedEnclosureBuilder)

        assertAll {
            assertThat(episodeBuilder).prop(EpisodeBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodeBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build a episode when the mandatory enclosure is missing`() {
        val episodeBuilder = ValidatingEpisodeBuilder()
            .title("title")

        assertAll {
            assertThat(episodeBuilder).prop(EpisodeBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodeBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build a episode with all the mandatory fields`() {
        val episodeBuilder = ValidatingEpisodeBuilder()
            .title("title")
            .enclosureBuilder(expectedEnclosureBuilder)

        assertAll {
            assertThat(episodeBuilder).prop(EpisodeBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeBuilder.build()).isNotNull().all {
                prop(Episode::title).isEqualTo("title")
                prop(Episode::link).isNull()
                prop(Episode::description).isNull()
                prop(Episode::author).isNull()
                prop(Episode::categories).isEmpty()
                prop(Episode::comments).isNull()
                prop(Episode::enclosure).isEqualTo(expectedEnclosureBuilder.build())
                prop(Episode::guid).isNull()
                prop(Episode::pubDate).isNull()
                prop(Episode::source).isNull()
                prop(Episode::content).isNull()
                prop(Episode::iTunes).isNull()
                prop(Episode::atom).isNull()
                prop(Episode::podlove).isNull()
                prop(Episode::googlePlay).isNull()
                prop(Episode::bitlove).isNull()
                prop(Episode::podcast).isNull()
            }
        }
    }

    @Test
    internal fun `should build a episode with all the optional fields`() {
        val episodeBuilder = ValidatingEpisodeBuilder()
            .title("title")
            .link("link")
            .description("description")
            .author("author")
            .addCategoryBuilder(expectedCategoryBuilders[0])
            .addCategoryBuilder(expectedCategoryBuilders[1])
            .comments("comments")
            .enclosureBuilder(expectedEnclosureBuilder)
            .guidBuilder(ValidatingEpisodeGuidBuilder().textContent("guid"))
            .pubDate(expectedDate)
            .source("source")
            .apply {
                contentBuilder.encoded("encoded")
                iTunesBuilder.title("iTunes title")
                atomBuilder.addAuthorBuilder(expectedAtomAuthorBuilder)
                podloveBuilder.addSimpleChapterBuilder(expectedSimpleChapterBuilder)
                googlePlayBuilder.description("play description")
                bitloveBuilder.guid("bitlove guid")
                podcastBuilder.chaptersBuilder(expectedPodcastChaptersBuilder)
            }

        assertAll {
            assertThat(episodeBuilder).prop(EpisodeBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeBuilder.build()).isNotNull().all {
                prop(Episode::title).isEqualTo("title")
                prop(Episode::link).isEqualTo("link")
                prop(Episode::description).isEqualTo("description")
                prop(Episode::author).isEqualTo("author")
                prop(Episode::categories).containsExactly(expectedCategoryBuilders[0].build(), expectedCategoryBuilders[1].build())
                prop(Episode::comments).isEqualTo("comments")
                prop(Episode::enclosure).isEqualTo(expectedEnclosureBuilder.build())
                prop(Episode::guid).isEqualTo(Episode.Guid("guid"))
                prop(Episode::pubDate).isEqualTo(expectedDate)
                prop(Episode::source).isEqualTo("source")
                prop(Episode::content).isNotNull().prop(Episode.Content::encoded).isEqualTo("encoded")
                prop(Episode::iTunes).isNotNull().prop(EpisodeItunes::title).isEqualTo("iTunes title")
                prop(Episode::atom).isNotNull().prop(Atom::authors).containsExactly(expectedAtomAuthorBuilder.build())
                prop(Episode::podlove).isNotNull().prop(Episode.Podlove::simpleChapters).containsExactly(expectedSimpleChapterBuilder.build())
                prop(Episode::googlePlay).isNotNull().prop(Episode.GooglePlay::description).isEqualTo("play description")
                prop(Episode::bitlove).isNotNull().prop(Episode.Bitlove::guid).isEqualTo("bitlove guid")
                prop(Episode::podcast).isNotNull().prop(Episode.Podcast::chapters).isEqualTo(expectedPodcastChaptersBuilder.build())
            }
        }
    }

    @Test
    internal fun `should populate an Episode builder with all properties from an Episode model`() {
        val episode = anEpisode()
        val episodeBuilder = Episode.builder().from(episode)

        assertAll {
            assertThat(episodeBuilder).prop(EpisodeBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeBuilder.build()).isNotNull().isEqualTo(episode)
        }
    }
}
