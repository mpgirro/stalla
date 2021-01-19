package io.hemin.wien.builder.fake.episode

import io.hemin.wien.builder.HrefOnlyImageBuilder
import io.hemin.wien.builder.ITunesStyleCategoryBuilder
import io.hemin.wien.builder.LinkBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.builder.RssCategoryBuilder
import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.episode.EpisodeEnclosureBuilder
import io.hemin.wien.builder.episode.EpisodeGuidBuilder
import io.hemin.wien.builder.episode.EpisodePodcastBuilder
import io.hemin.wien.builder.episode.EpisodePodcastChaptersBuilder
import io.hemin.wien.builder.episode.EpisodePodcastSoundbiteBuilder
import io.hemin.wien.builder.episode.EpisodePodcastTranscriptBuilder
import io.hemin.wien.builder.episode.EpisodePodloveSimpleChapterBuilder
import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.builder.fake.FakeHrefOnlyImageBuilder
import io.hemin.wien.builder.fake.FakeITunesStyleCategoryBuilder
import io.hemin.wien.builder.fake.FakeLinkBuilder
import io.hemin.wien.builder.fake.FakePersonBuilder
import io.hemin.wien.builder.fake.FakeRssCategoryBuilder
import io.hemin.wien.model.Episode
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

    override val iTunesBuilder: FakeEpisodeITunesBuilder = FakeEpisodeITunesBuilder()

    override val atomBuilder: FakeEpisodeAtomBuilder = FakeEpisodeAtomBuilder()

    override val podloveBuilder: FakeEpisodePodloveBuilder = FakeEpisodePodloveBuilder()

    override val googlePlayBuilder: FakeEpisodeGooglePlayBuilder = FakeEpisodeGooglePlayBuilder()

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

    override fun createITunesStyleCategoryBuilder(): ITunesStyleCategoryBuilder = FakeITunesStyleCategoryBuilder()

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
        if (iTunesBuilder != other.iTunesBuilder) return false
        if (atomBuilder != other.atomBuilder) return false
        if (podloveBuilder != other.podloveBuilder) return false
        if (googlePlayBuilder != other.googlePlayBuilder) return false
        if (bitloveBuilder != other.bitloveBuilder) return false

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
        result = 31 * result + iTunesBuilder.hashCode()
        result = 31 * result + atomBuilder.hashCode()
        result = 31 * result + podloveBuilder.hashCode()
        result = 31 * result + googlePlayBuilder.hashCode()
        result = 31 * result + bitloveBuilder.hashCode()
        return result
    }

    override fun toString() =
        "FakeEpisodeBuilder(titleValue=$titleValue, enclosureBuilderValue=$enclosureBuilderValue, link=$link, description=$description, " +
            "author=$author, categories=$categoryBuilders, comments=$comments, guidBuilder=$guidBuilder, pubDate=$pubDate, source=$source, " +
            "content=$contentBuilder, iTunes=$iTunesBuilder, atom=$atomBuilder, podlove=$podloveBuilder, googlePlay=$googlePlayBuilder, bitlove=$bitloveBuilder)"
}
