package io.hemin.wien.builder.fake.episode

import io.hemin.wien.builder.ImageBuilder
import io.hemin.wien.builder.LinkBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.episode.EpisodeEnclosureBuilder
import io.hemin.wien.builder.episode.EpisodeGuidBuilder
import io.hemin.wien.builder.episode.EpisodePodloveSimpleChapterBuilder
import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.builder.fake.FakeImageBuilder
import io.hemin.wien.builder.fake.FakeLinkBuilder
import io.hemin.wien.builder.fake.FakePersonBuilder
import io.hemin.wien.model.Episode
import java.time.temporal.TemporalAccessor

@Suppress("MemberVisibilityCanBePrivate", "Unused")
internal class FakeEpisodeBuilder : FakeBuilder<Episode>(), EpisodeBuilder {

    var titleValue: String? = null
    var enclosureBuilderValue: EpisodeEnclosureBuilder? = null

    var link: String? = null
    var description: String? = null
    var author: String? = null
    val categories: MutableList<String> = mutableListOf()
    var comments: String? = null
    var guidBuilder: EpisodeGuidBuilder? = null
    var pubDate: TemporalAccessor? = null
    var source: String? = null

    override val content: FakeEpisodeContentBuilder = FakeEpisodeContentBuilder()

    override val iTunes: FakeEpisodeITunesBuilder = FakeEpisodeITunesBuilder()

    override val atom: FakeEpisodeAtomBuilder = FakeEpisodeAtomBuilder()

    override val podlove: FakeEpisodePodloveBuilder = FakeEpisodePodloveBuilder()

    override val googlePlay: FakeEpisodeGooglePlayBuilder = FakeEpisodeGooglePlayBuilder()

    override val bitlove: FakeEpisodeBitloveBuilder = FakeEpisodeBitloveBuilder()

    override fun title(title: String): EpisodeBuilder = apply { this.titleValue = title }

    override fun link(link: String?): EpisodeBuilder = apply { this.link = link }

    override fun description(description: String?): EpisodeBuilder = apply { this.description = description }

    override fun author(author: String?): EpisodeBuilder = apply { this.author = author }

    override fun addCategory(category: String): EpisodeBuilder = apply {
        categories.add(category)
    }

    override fun comments(comments: String?): EpisodeBuilder = apply { this.comments = comments }

    override fun enclosureBuilder(enclosureBuilder: EpisodeEnclosureBuilder): EpisodeBuilder = apply {
        this.enclosureBuilderValue = enclosureBuilder
    }

    override fun guidBuilder(guidBuilder: EpisodeGuidBuilder?): EpisodeBuilder = apply { this.guidBuilder = guidBuilder }

    override fun pubDate(pubDate: TemporalAccessor?): EpisodeBuilder = apply { this.pubDate = pubDate }

    override fun source(source: String?): EpisodeBuilder = apply { this.source = source }

    override fun createEnclosureBuilder(): EpisodeEnclosureBuilder = FakeEpisodeEnclosureBuilder()

    override fun createGuidBuilder(): EpisodeGuidBuilder = FakeEpisodeGuidBuilder()

    override fun createLinkBuilder(): LinkBuilder = FakeLinkBuilder()

    override fun createPersonBuilder(): PersonBuilder = FakePersonBuilder()

    override fun createImageBuilder(): ImageBuilder = FakeImageBuilder()

    override fun createPodloveSimpleChapterBuilder(): EpisodePodloveSimpleChapterBuilder = FakeEpisodePodloveSimpleChapterBuilder()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakeEpisodeBuilder) return false

        if (titleValue != other.titleValue) return false
        if (enclosureBuilderValue != other.enclosureBuilderValue) return false
        if (link != other.link) return false
        if (description != other.description) return false
        if (author != other.author) return false
        if (categories != other.categories) return false
        if (comments != other.comments) return false
        if (guidBuilder != other.guidBuilder) return false
        if (pubDate != other.pubDate) return false
        if (source != other.source) return false
        if (content != other.content) return false
        if (iTunes != other.iTunes) return false
        if (atom != other.atom) return false
        if (podlove != other.podlove) return false
        if (googlePlay != other.googlePlay) return false
        if (bitlove != other.bitlove) return false

        return true
    }

    override fun hashCode(): Int {
        var result = titleValue?.hashCode() ?: 0
        result = 31 * result + (enclosureBuilderValue?.hashCode() ?: 0)
        result = 31 * result + (link?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (author?.hashCode() ?: 0)
        result = 31 * result + categories.hashCode()
        result = 31 * result + (comments?.hashCode() ?: 0)
        result = 31 * result + (guidBuilder?.hashCode() ?: 0)
        result = 31 * result + (pubDate?.hashCode() ?: 0)
        result = 31 * result + (source?.hashCode() ?: 0)
        result = 31 * result + content.hashCode()
        result = 31 * result + iTunes.hashCode()
        result = 31 * result + atom.hashCode()
        result = 31 * result + podlove.hashCode()
        result = 31 * result + googlePlay.hashCode()
        result = 31 * result + bitlove.hashCode()
        return result
    }

    override fun toString(): String =
        "FakeEpisodeBuilder(titleValue=$titleValue, enclosureBuilderValue=$enclosureBuilderValue, link=$link, description=$description, " +
                "author=$author, categories=$categories, comments=$comments, guidBuilder=$guidBuilder, pubDate=$pubDate, source=$source, " +
                "content=$content, iTunes=$iTunes, atom=$atom, podlove=$podlove, googlePlay=$googlePlay, bitlove=$bitlove)"
}
