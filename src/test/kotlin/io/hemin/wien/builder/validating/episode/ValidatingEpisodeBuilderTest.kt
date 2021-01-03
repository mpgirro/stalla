package io.hemin.wien.builder.validating.episode

import assertk.all
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import io.hemin.wien.builder.episode.EpisodeEnclosureBuilder
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Person
import org.junit.jupiter.api.Test
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

internal class ValidatingEpisodeBuilderTest {

    private val expectedDate: Date = Calendar.Builder()
        .setLocale(Locale.ENGLISH)
        .setTimeZone(TimeZone.getTimeZone("UTC"))
        .setDate(2018, Calendar.MARCH, 16) // "Fri, 16 Mar 2018 22:49:08 +0000"
        .setTimeOfDay(22, 49, 8)
        .build()
        .time
    private val expectedEnclosure = Episode.Enclosure(
        url = "enclosure url",
        length = 123,
        type = "enclosure type"
    )

    private val dummyEnclosureBuilder = DummyEpisodeEnclosureBuilder(expectedEnclosure)

    private val expectedAtomAuthor = Person("atom author")

    private val expectedChapter = Episode.Podlove.SimpleChapter(start = "start", title = "chapter title")

    @Test
    internal fun `should not build a episode when the mandatory fields are missing`() {
        val episodeBuilder = ValidatingEpisodeBuilder()

        assertThat(episodeBuilder.build()).isNull()
    }

    @Test
    internal fun `should not build a episode when the mandatory title is missing`() {
        val episodeBuilder = ValidatingEpisodeBuilder()
            .enclosureBuilder(dummyEnclosureBuilder)

        assertThat(episodeBuilder.build()).isNull()
    }

    @Test
    internal fun `should not build a episode when the mandatory enclosure is missing`() {
        val episodeBuilder = ValidatingEpisodeBuilder()
            .title("title")

        assertThat(episodeBuilder.build()).isNull()
    }

    @Test
    internal fun `should build a episode with all the mandatory fields`() {
        val episodeBuilder = ValidatingEpisodeBuilder()
            .title("title")
            .enclosureBuilder(dummyEnclosureBuilder)

        assertThat(episodeBuilder.build()).isNotNull().all {
            prop(Episode::title).isEqualTo("title")
            prop(Episode::link).isNull()
            prop(Episode::description).isNull()
            prop(Episode::author).isNull()
            prop(Episode::categories).isEmpty()
            prop(Episode::comments).isNull()
            prop(Episode::enclosure).isEqualTo(expectedEnclosure)
            prop(Episode::guid).isNull()
            prop(Episode::pubDate).isNull()
            prop(Episode::source).isNull()
            prop(Episode::content).isNull()
            prop(Episode::itunes).isNull()
            prop(Episode::atom).isNull()
            prop(Episode::podlove).isNull()
            prop(Episode::googlePlay).isNull()
            prop(Episode::bitlove).isNull()
        }
    }

    @Test
    internal fun `should build a episode with all the optional fields`() {
        val episodeBuilder = ValidatingEpisodeBuilder()
            .title("title")
            .link("link")
            .description("description")
            .author("author")
            .addCategory("category 1")
            .addCategory("category 2")
            .comments("comments")
            .enclosureBuilder(dummyEnclosureBuilder)
            .guidBuilder(ValidatingEpisodeGuidBuilder().textContent("guid"))
            .pubDate(expectedDate)
            .source("source")
            .apply {
                content.encoded("encoded")
                iTunes.title("iTunes title")
                atom.addAuthor(expectedAtomAuthor)
                podlove.addSimpleChapter(expectedChapter)
                googlePlay.description("play description")
                bitlove.guid("bitlove guid")
            }

        assertThat(episodeBuilder.build()).isNotNull().all {
            prop(Episode::title).isEqualTo("title")
            prop(Episode::link).isEqualTo("link")
            prop(Episode::description).isEqualTo("description")
            prop(Episode::author).isEqualTo("author")
            prop(Episode::categories).containsExactly("category 1", "category 2")
            prop(Episode::comments).isEqualTo("comments")
            prop(Episode::enclosure).isEqualTo(expectedEnclosure)
            prop(Episode::guid).isEqualTo(Episode.Guid("guid"))
            prop(Episode::pubDate).isEqualTo(expectedDate)
            prop(Episode::source).isEqualTo("source")
            prop(Episode::content).isNotNull().prop(Episode.Content::encoded).isEqualTo("encoded")
            prop(Episode::itunes).isNotNull().prop(Episode.ITunes::title).isEqualTo("iTunes title")
            prop(Episode::atom).isNotNull().prop(Episode.Atom::authors).containsExactly(expectedAtomAuthor)
            prop(Episode::podlove).isNotNull().prop(Episode.Podlove::simpleChapters).containsExactly(expectedChapter)
            prop(Episode::googlePlay).isNotNull().prop(Episode.GooglePlay::description).isEqualTo("play description")
            prop(Episode::bitlove).isNotNull().prop(Episode.Bitlove::guid).isEqualTo("bitlove guid")
        }
    }
}

private class DummyEpisodeEnclosureBuilder(private val enclosure: Episode.Enclosure) : EpisodeEnclosureBuilder {

    override fun url(url: String): EpisodeEnclosureBuilder = this

    override fun length(length: Long): EpisodeEnclosureBuilder = this

    override fun type(type: String): EpisodeEnclosureBuilder = this

    override fun build() = enclosure
}
