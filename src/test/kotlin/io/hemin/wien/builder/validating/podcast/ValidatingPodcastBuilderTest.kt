package io.hemin.wien.builder.validating.podcast

import assertk.all
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import io.hemin.wien.builder.validating.ValidatingImageBuilder
import io.hemin.wien.builder.validating.ValidatingPersonBuilder
import io.hemin.wien.builder.validating.episode.ValidatingEpisodeBuilder
import io.hemin.wien.builder.validating.episode.ValidatingEpisodeEnclosureBuilder
import io.hemin.wien.model.Podcast
import org.junit.jupiter.api.Test
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

internal class ValidatingPodcastBuilderTest {

    private val aPubDate: Date = Calendar.Builder()
        .setLocale(Locale.ENGLISH)
        .setTimeZone(TimeZone.getTimeZone("UTC"))
        .setDate(2018, Calendar.MARCH, 16) // "Fri, 16 Mar 2018 22:49:08 +0000"
        .setTimeOfDay(22, 49, 8)
        .build()
        .time

    private val aLastBuildDate: Date = Calendar.Builder()
        .setLocale(Locale.ENGLISH)
        .setTimeZone(TimeZone.getTimeZone("UTC"))
        .setDate(2020, Calendar.MAY, 1) // "Fri, 16 Mar 2018 22:49:08 +0000"
        .setTimeOfDay(12, 55, 22)
        .build()
        .time

    private val expectedImageBuilder = ValidatingImageBuilder()
        .url("image url")

    private val expectedEnclosureBuilder = ValidatingEpisodeEnclosureBuilder()
        .url("enclosure url")
        .length(123)
        .type("enclosure type")

    private val expectedEpisodeBuilder = ValidatingEpisodeBuilder()
        .title("episode title")
        .enclosureBuilder(expectedEnclosureBuilder)

    private val expectedAtomAuthorBuilder = ValidatingPersonBuilder().name("atom author")

    private val expectedITunesImageBuilder = ValidatingImageBuilder().url("itunes image url")

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
                    .addCategory("test")
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
            prop(Podcast::iTunes).isNotNull().prop(Podcast.ITunes::categories).containsExactly("test")
            prop(Podcast::atom).isNotNull().prop(Podcast.Atom::authors).containsExactly(expectedAtomAuthorBuilder.build())
            prop(Podcast::feedpress).isNotNull().prop(Podcast.Feedpress::newsletterId).isEqualTo("feedpress newsletterId")
            prop(Podcast::fyyd).isNotNull().prop(Podcast.Fyyd::verify).isEqualTo("fyyd verify")
            prop(Podcast::googlePlay).isNotNull().prop(Podcast.GooglePlay::description).isEqualTo("play description")
        }
    }
}
