package io.hemin.wien.builder.validating.podcast

import assertk.all
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import io.hemin.wien.builder.validating.ValidatingHrefOnlyImageBuilder
import io.hemin.wien.builder.validating.ValidatingITunesCategoryBuilder
import io.hemin.wien.builder.validating.ValidatingRssImageBuilder
import io.hemin.wien.builder.validating.ValidatingPersonBuilder
import io.hemin.wien.builder.validating.episode.ValidatingEpisodeBuilder
import io.hemin.wien.builder.validating.episode.ValidatingEpisodeEnclosureBuilder
import io.hemin.wien.dateTime
import io.hemin.wien.model.Podcast
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

    private val expectedITunesCategoryBuilder = ValidatingITunesCategoryBuilder()
        .category("itunes category")
        .subcategory("itunes subcategory")

    @Test
    internal fun `should not build a Podcast when the mandatory fields are missing`() {
        val podcastBuilder = ValidatingPodcastBuilder()

        assertThat(podcastBuilder.build()).isNull()
    }

    @Test
    internal fun `should not build a Podcast when the mandatory title is missing`() {
        val podcastBuilder = ValidatingPodcastBuilder()
            .link("link")
            .description("description")
            .language("language")
            .addEpisodeBuilder(expectedEpisodeBuilder)

        assertThat(podcastBuilder.build()).isNull()
    }

    @Test
    internal fun `should not build a Podcast when the mandatory link is missing`() {
        val podcastBuilder = ValidatingPodcastBuilder()
            .title("title")
            .description("description")
            .language("language")
            .addEpisodeBuilder(expectedEpisodeBuilder)

        assertThat(podcastBuilder.build()).isNull()
    }

    @Test
    internal fun `should not build a Podcast when the mandatory description is missing`() {
        val podcastBuilder = ValidatingPodcastBuilder()
            .title("title")
            .link("link")
            .language("language")
            .addEpisodeBuilder(expectedEpisodeBuilder)

        assertThat(podcastBuilder.build()).isNull()
    }

    @Test
    internal fun `should not build a Podcast when the mandatory language is missing`() {
        val podcastBuilder = ValidatingPodcastBuilder()
            .title("title")
            .link("link")
            .description("description")
            .addEpisodeBuilder(expectedEpisodeBuilder)

        assertThat(podcastBuilder.build()).isNull()
    }

    @Test
    internal fun `should not build a Podcast when there are no episodes`() {
        val podcastBuilder = ValidatingPodcastBuilder()
            .title("title")
            .link("link")
            .description("description")
            .language("language")

        assertThat(podcastBuilder.build()).isNull()
    }

    @Test
    internal fun `should build a Podcast with all the mandatory fields`() {
        val podcastBuilder = ValidatingPodcastBuilder()
            .title("title")
            .link("link")
            .description("description")
            .language("language")
            .addEpisodeBuilder(expectedEpisodeBuilder)

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
            prop(Podcast::iTunes).isNull()
            prop(Podcast::atom).isNull()
            prop(Podcast::feedpress).isNull()
            prop(Podcast::fyyd).isNull()
            prop(Podcast::googlePlay).isNull()
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
            .apply {
                iTunes.imageBuilder(expectedITunesImageBuilder)
                    .addCategoryBuilder(expectedITunesCategoryBuilder)
                    .explicit(false)
                atom.addAuthorBuilder(expectedAtomAuthorBuilder)
                feedpress.newsletterId("feedpress newsletterId")
                fyyd.verify("fyyd verify")
                googlePlay.description("play description")
            }

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
            prop(Podcast::episodes).containsExactly(expectedEpisodeBuilder.build())
            prop(Podcast::iTunes).isNotNull().prop(Podcast.ITunes::categories).containsExactly(expectedITunesCategoryBuilder.build())
            prop(Podcast::atom).isNotNull().prop(Podcast.Atom::authors)
                .containsExactly(expectedAtomAuthorBuilder.build())
            prop(Podcast::feedpress).isNotNull().prop(Podcast.Feedpress::newsletterId)
                .isEqualTo("feedpress newsletterId")
            prop(Podcast::fyyd).isNotNull().prop(Podcast.Fyyd::verify).isEqualTo("fyyd verify")
            prop(Podcast::googlePlay).isNotNull().prop(Podcast.GooglePlay::description).isEqualTo("play description")
        }
    }
}
