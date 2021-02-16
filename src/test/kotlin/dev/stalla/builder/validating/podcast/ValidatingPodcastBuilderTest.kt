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
import dev.stalla.builder.podcast.PodcastBuilder
import dev.stalla.builder.validating.ValidatingHrefOnlyImageBuilder
import dev.stalla.builder.validating.ValidatingPersonBuilder
import dev.stalla.builder.validating.ValidatingRssCategoryBuilder
import dev.stalla.builder.validating.ValidatingRssImageBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodeBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodeEnclosureBuilder
import dev.stalla.dateTime
import dev.stalla.model.Podcast
import dev.stalla.model.atom.Atom
import dev.stalla.model.feedpress.Feedpress
import dev.stalla.model.fyyd.Fyyd
import dev.stalla.model.googleplay.PodcastGoogleplay
import dev.stalla.model.itunes.ItunesCategory
import dev.stalla.model.itunes.PodcastItunes
import dev.stalla.model.podcast.aPodcast
import dev.stalla.model.podcastindex.PodcastPodcastindex
import org.junit.jupiter.api.Test
import java.time.Month

internal class ValidatingPodcastBuilderTest {

    // "Fri, 16 Mar 2018 22:49:08 +0000"
    private val aPubDate = dateTime(year = 2018, month = Month.MARCH, day = 16, hour = 22, minute = 49, second = 8)

    // "Fri, 1 May 2020 12:55:22 +0000"
    private val aLastBuildDate = dateTime(year = 2020, month = Month.MAY, day = 1, hour = 12, minute = 55, second = 22)

    private val expectedImageBuilder = ValidatingRssImageBuilder()
        .url("image url")

    private val expectedEnclosureBuilder = ValidatingEpisodeEnclosureBuilder()
        .url("enclosure url")
        .length(123)
        .type("enclosure type")

    private val expectedEpisodeBuilder = ValidatingEpisodeBuilder()
        .title("episode title")
        .enclosureBuilder(expectedEnclosureBuilder)

    private val expectedAtomAuthorBuilder = ValidatingPersonBuilder().name("atom author")

    private val expectedITunesImageBuilder = ValidatingHrefOnlyImageBuilder().href("itunes image href")

    private val expectedItunesCategory: ItunesCategory = ItunesCategory.from("Science Fiction")!!

    private val expectedCategoryBuilders = listOf(
        ValidatingRssCategoryBuilder().category("category 1").domain("domain"),
        ValidatingRssCategoryBuilder().category("category 2")
    )

    private val expectedLockedBuilder = ValidatingPodcastPodcastindexLockedBuilder()
        .locked(true)
        .owner("owner@example.com")

    @Test
    internal fun `should not build a Podcast when the mandatory fields are missing`() {
        val podcastBuilder = ValidatingPodcastBuilder()

        assertAll {
            assertThat(podcastBuilder).prop(PodcastBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(podcastBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build a Podcast when the mandatory title is missing`() {
        val podcastBuilder = ValidatingPodcastBuilder()
            .link("link")
            .description("description")
            .language("language")
            .addEpisodeBuilder(expectedEpisodeBuilder)

        assertAll {
            assertThat(podcastBuilder).prop(PodcastBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(podcastBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build a Podcast when the mandatory link is missing`() {
        val podcastBuilder = ValidatingPodcastBuilder()
            .title("title")
            .description("description")
            .language("language")
            .addEpisodeBuilder(expectedEpisodeBuilder)

        assertAll {
            assertThat(podcastBuilder).prop(PodcastBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(podcastBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build a Podcast when the mandatory description is missing`() {
        val podcastBuilder = ValidatingPodcastBuilder()
            .title("title")
            .link("link")
            .language("language")
            .addEpisodeBuilder(expectedEpisodeBuilder)

        assertAll {
            assertThat(podcastBuilder).prop(PodcastBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(podcastBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build a Podcast when the mandatory language is missing`() {
        val podcastBuilder = ValidatingPodcastBuilder()
            .title("title")
            .link("link")
            .description("description")
            .addEpisodeBuilder(expectedEpisodeBuilder)

        assertAll {
            assertThat(podcastBuilder).prop(PodcastBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(podcastBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build a Podcast when there are no episodes`() {
        val podcastBuilder = ValidatingPodcastBuilder()
            .title("title")
            .link("link")
            .description("description")
            .language("language")

        assertAll {
            assertThat(podcastBuilder).prop(PodcastBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(podcastBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build a Podcast with all the mandatory fields`() {
        val podcastBuilder = ValidatingPodcastBuilder()
            .title("title")
            .link("link")
            .description("description")
            .language("language")
            .addEpisodeBuilder(expectedEpisodeBuilder)

        assertAll {
            assertThat(podcastBuilder).prop(PodcastBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastBuilder.build()).isNotNull().all {
                prop(Podcast::title).isEqualTo("title")
                prop(Podcast::link).isEqualTo("link")
                prop(Podcast::description).isEqualTo("description")
                prop(Podcast::pubDate).isNull()
                prop(Podcast::lastBuildDate).isNull()
                prop(Podcast::language).isEqualTo("language")
                prop(Podcast::generator).isNull()
                prop(Podcast::copyright).isNull()
                prop(Podcast::docs).isNull()
                prop(Podcast::managingEditor).isNull()
                prop(Podcast::webMaster).isNull()
                prop(Podcast::image).isNull()
                prop(Podcast::episodes).containsExactly(expectedEpisodeBuilder.build())
                prop(Podcast::itunes).isNull()
                prop(Podcast::atom).isNull()
                prop(Podcast::feedpress).isNull()
                prop(Podcast::fyyd).isNull()
                prop(Podcast::googleplay).isNull()
                prop(Podcast::podcast).isNull()
            }
        }
    }

    @Test
    internal fun `should build a Podcast with all the optional fields`() {
        val podcastBuilder = ValidatingPodcastBuilder()
            .title("title")
            .link("link")
            .description("description")
            .pubDate(aPubDate)
            .lastBuildDate(aLastBuildDate)
            .language("language")
            .generator("generator")
            .copyright("copyright")
            .docs("docs")
            .managingEditor("managingEditor")
            .webMaster("webMaster")
            .imageBuilder(expectedImageBuilder)
            .addEpisodeBuilder(expectedEpisodeBuilder)
            .addCategoryBuilder(expectedCategoryBuilders[0])
            .addCategoryBuilder(expectedCategoryBuilders[1])
            .apply {
                itunesBuilder.imageBuilder(expectedITunesImageBuilder)
                    .addCategory(expectedItunesCategory)
                    .explicit(false)
                atomBuilder.addAuthorBuilder(expectedAtomAuthorBuilder)
                feedpressBuilder.newsletterId("feedpress newsletterId")
                fyydBuilder.verify("fyyd verify")
                googleplayBuilder.description("play description")
                podcastPodcastindexBuilder.lockedBuilder(expectedLockedBuilder)
            }

        assertAll {
            assertThat(podcastBuilder).prop(PodcastBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastBuilder.build()).isNotNull().all {
                prop(Podcast::title).isEqualTo("title")
                prop(Podcast::link).isEqualTo("link")
                prop(Podcast::description).isEqualTo("description")
                prop(Podcast::pubDate).isEqualTo(aPubDate)
                prop(Podcast::lastBuildDate).isEqualTo(aLastBuildDate)
                prop(Podcast::language).isEqualTo("language")
                prop(Podcast::generator).isEqualTo("generator")
                prop(Podcast::copyright).isEqualTo("copyright")
                prop(Podcast::docs).isEqualTo("docs")
                prop(Podcast::managingEditor).isEqualTo("managingEditor")
                prop(Podcast::webMaster).isEqualTo("webMaster")
                prop(Podcast::image).isEqualTo(expectedImageBuilder.build())
                prop(Podcast::categories).containsExactly(expectedCategoryBuilders[0].build(), expectedCategoryBuilders[1].build())
                prop(Podcast::episodes).containsExactly(expectedEpisodeBuilder.build())
                prop(Podcast::itunes).isNotNull().prop(PodcastItunes::categories).containsExactly(expectedItunesCategory)
                prop(Podcast::atom).isNotNull().prop(Atom::authors)
                    .containsExactly(expectedAtomAuthorBuilder.build())
                prop(Podcast::feedpress).isNotNull().prop(Feedpress::newsletterId)
                    .isEqualTo("feedpress newsletterId")
                prop(Podcast::fyyd).isNotNull().prop(Fyyd::verify).isEqualTo("fyyd verify")
                prop(Podcast::googleplay).isNotNull().prop(PodcastGoogleplay::description).isEqualTo("play description")
                prop(Podcast::podcast).isNotNull().prop(PodcastPodcastindex::locked).isEqualTo(expectedLockedBuilder.build())
            }
        }
    }

    @Test
    internal fun `should populate a Podcast builder with all properties from an Podcast model`() {
        val podcast = aPodcast()
        val podcastBuilder = Podcast.builder().from(podcast)

        assertAll {
            assertThat(podcastBuilder).prop(PodcastBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(podcastBuilder.build()).isNotNull().isEqualTo(podcast)
        }
    }
}
