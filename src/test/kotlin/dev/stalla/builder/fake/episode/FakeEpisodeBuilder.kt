package dev.stalla.builder.fake.episode

import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.ItunesStyleCategoryBuilder
import dev.stalla.builder.LinkBuilder
import dev.stalla.builder.PersonBuilder
import dev.stalla.builder.RssCategoryBuilder
import dev.stalla.builder.episode.EpisodeBuilder
import dev.stalla.builder.episode.EpisodeEnclosureBuilder
import dev.stalla.builder.episode.EpisodeGuidBuilder
import dev.stalla.builder.episode.EpisodePodcastChaptersBuilder
import dev.stalla.builder.episode.EpisodePodcastSoundbiteBuilder
import dev.stalla.builder.episode.EpisodePodcastTranscriptBuilder
import dev.stalla.builder.episode.EpisodePodloveSimpleChapterBuilder
import dev.stalla.builder.fake.FakeAtomBuilder
import dev.stalla.builder.fake.FakeBuilder
import dev.stalla.builder.fake.FakeHrefOnlyImageBuilder
import dev.stalla.builder.fake.FakeItunesStyleCategoryBuilder
import dev.stalla.builder.fake.FakeLinkBuilder
import dev.stalla.builder.fake.FakePersonBuilder
import dev.stalla.builder.fake.FakeRssCategoryBuilder
import dev.stalla.model.Episode
import java.time.temporal.TemporalAccessor

internal class FakeEpisodeBuilder : FakeBuilder<Episode>(), EpisodeBuilder {

    var titleValue: String? = null
    var enclosureBuilderValue: EpisodeEnclosureBuilder? = null

    var link: String? = null
    var description: String? = null
    var author: String? = null
    val categoryBuilders: MutableList<RssCategoryBuilder> = mutableListOf()
    var comments: String? = null
    var guidBuilder: EpisodeGuidBuilder? = null
    var pubDate: TemporalAccessor? = null
    var source: String? = null

    override val contentBuilder: FakeEpisodeContentBuilder = FakeEpisodeContentBuilder()

    override val itunesBuilder: FakeEpisodeItunesBuilder = FakeEpisodeItunesBuilder()

    override val atomBuilder: FakeAtomBuilder = FakeAtomBuilder()

    override val podloveBuilder: FakeEpisodePodloveBuilder = FakeEpisodePodloveBuilder()

    val googleplayBuilder: FakeEpisodeGoogleplayBuilder = FakeEpisodeGoogleplayBuilder()

    override val bitloveBuilder: FakeEpisodeBitloveBuilder = FakeEpisodeBitloveBuilder()

    override val podcastBuilder: FakeEpisodePodcastBuilder = FakeEpisodePodcastBuilder()

    override fun title(title: String): EpisodeBuilder = apply { this.titleValue = title }

    override fun link(link: String?): EpisodeBuilder = apply { this.link = link }

    override fun description(description: String?): EpisodeBuilder = apply { this.description = description }

    override fun author(author: String?): EpisodeBuilder = apply { this.author = author }

    override fun addCategoryBuilder(categoryBuilder: RssCategoryBuilder): EpisodeBuilder = apply {
        categoryBuilders.add(categoryBuilder)
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

    override fun createHrefOnlyImageBuilder(): HrefOnlyImageBuilder = FakeHrefOnlyImageBuilder()

    override fun createPodloveSimpleChapterBuilder(): EpisodePodloveSimpleChapterBuilder = FakeEpisodePodloveSimpleChapterBuilder()

    override fun createRssCategoryBuilder(): RssCategoryBuilder = FakeRssCategoryBuilder()

    override fun createITunesStyleCategoryBuilder(): ItunesStyleCategoryBuilder = FakeItunesStyleCategoryBuilder()

    override fun createEpisodePodcastTranscriptBuilder(): EpisodePodcastTranscriptBuilder = FakeEpisodePodcastTranscriptBuilder()

    override fun createEpisodePodcastChaptersBuilder(): EpisodePodcastChaptersBuilder = FakeEpisodePodcastChaptersBuilder()

    override fun createEpisodePodcastSoundbiteBuilder(): EpisodePodcastSoundbiteBuilder = FakeEpisodePodcastSoundbiteBuilder()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FakeEpisodeBuilder) return false

        if (titleValue != other.titleValue) return false
        if (enclosureBuilderValue != other.enclosureBuilderValue) return false
        if (link != other.link) return false
        if (description != other.description) return false
        if (author != other.author) return false
        if (categoryBuilders != other.categoryBuilders) return false
        if (comments != other.comments) return false
        if (guidBuilder != other.guidBuilder) return false
        if (pubDate != other.pubDate) return false
        if (source != other.source) return false
        if (contentBuilder != other.contentBuilder) return false
        if (itunesBuilder != other.itunesBuilder) return false
        if (atomBuilder != other.atomBuilder) return false
        if (podloveBuilder != other.podloveBuilder) return false
        if (googleplayBuilder != other.googleplayBuilder) return false
        if (bitloveBuilder != other.bitloveBuilder) return false
        if (podcastBuilder != other.podcastBuilder) return false

        return true
    }

    override fun hashCode(): Int {
        var result = titleValue?.hashCode() ?: 0
        result = 31 * result + (enclosureBuilderValue?.hashCode() ?: 0)
        result = 31 * result + (link?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (author?.hashCode() ?: 0)
        result = 31 * result + categoryBuilders.hashCode()
        result = 31 * result + (comments?.hashCode() ?: 0)
        result = 31 * result + (guidBuilder?.hashCode() ?: 0)
        result = 31 * result + (pubDate?.hashCode() ?: 0)
        result = 31 * result + (source?.hashCode() ?: 0)
        result = 31 * result + contentBuilder.hashCode()
        result = 31 * result + itunesBuilder.hashCode()
        result = 31 * result + atomBuilder.hashCode()
        result = 31 * result + podloveBuilder.hashCode()
        result = 31 * result + googleplayBuilder.hashCode()
        result = 31 * result + bitloveBuilder.hashCode()
        result = 31 * result + podcastBuilder.hashCode()
        return result
    }

    override fun toString() =
        "FakeEpisodeBuilder(titleValue=$titleValue, enclosureBuilderValue=$enclosureBuilderValue, link=$link, description=$description, " +
            "author=$author, categoryBuilders=$categoryBuilders, comments=$comments, guidBuilder=$guidBuilder, pubDate=$pubDate, source=$source, " +
            "contentBuilder=$contentBuilder, iTunesBuilder=$itunesBuilder, atomBuilder=$atomBuilder, podloveBuilder=$podloveBuilder, " +
            "googlePlayBuilder=$googleplayBuilder, bitloveBuilder=$bitloveBuilder, podcastBuilder=$podcastBuilder)"
}
