package dev.stalla.builder.validating.episode

import dev.stalla.builder.AtomBuilder
import dev.stalla.builder.AtomPersonBuilder
import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.LinkBuilder
import dev.stalla.builder.PodcastindexLocationBuilder
import dev.stalla.builder.PodcastindexPersonBuilder
import dev.stalla.builder.RssCategoryBuilder
import dev.stalla.builder.episode.EpisodeBitloveBuilder
import dev.stalla.builder.episode.EpisodeBuilder
import dev.stalla.builder.episode.EpisodeContentBuilder
import dev.stalla.builder.episode.EpisodeEnclosureBuilder
import dev.stalla.builder.episode.EpisodeGoogleplayBuilder
import dev.stalla.builder.episode.EpisodeGuidBuilder
import dev.stalla.builder.episode.EpisodeItunesBuilder
import dev.stalla.builder.episode.EpisodePodcastindexBuilder
import dev.stalla.builder.episode.EpisodePodcastindexChaptersBuilder
import dev.stalla.builder.episode.EpisodePodcastindexEpisodeBuilder
import dev.stalla.builder.episode.EpisodePodcastindexSeasonBuilder
import dev.stalla.builder.episode.EpisodePodcastindexSoundbiteBuilder
import dev.stalla.builder.episode.EpisodePodcastindexTranscriptBuilder
import dev.stalla.builder.episode.EpisodePodloveBuilder
import dev.stalla.builder.episode.EpisodePodloveSimpleChapterBuilder
import dev.stalla.builder.episode.ProvidingEpisodeBuilder
import dev.stalla.builder.validating.ValidatingAtomBuilder
import dev.stalla.builder.validating.ValidatingAtomPersonBuilder
import dev.stalla.builder.validating.ValidatingHrefOnlyImageBuilder
import dev.stalla.builder.validating.ValidatingLinkBuilder
import dev.stalla.builder.validating.ValidatingPodcastindexLocationBuilder
import dev.stalla.builder.validating.ValidatingPodcastindexPersonBuilder
import dev.stalla.builder.validating.ValidatingRssCategoryBuilder
import dev.stalla.builder.validating.checkRequiredProperty
import dev.stalla.builder.validating.checkRequiredValue
import dev.stalla.model.Episode
import dev.stalla.util.InternalAPI
import dev.stalla.util.asUnmodifiable
import java.time.temporal.TemporalAccessor

@InternalAPI
internal class ValidatingEpisodeBuilder : ProvidingEpisodeBuilder {

    private var title: String? = null
    private var enclosureBuilder: EpisodeEnclosureBuilder? = null

    private var link: String? = null
    private var description: String? = null
    private var author: String? = null
    private val categoryBuilders: MutableList<RssCategoryBuilder> = mutableListOf()
    private var comments: String? = null
    private var guidBuilder: EpisodeGuidBuilder? = null
    private var pubDate: TemporalAccessor? = null
    private var source: String? = null

    override val contentBuilder: EpisodeContentBuilder = ValidatingEpisodeContentBuilder()

    override val itunesBuilder: EpisodeItunesBuilder = ValidatingEpisodeItunesBuilder()

    override val atomBuilder: AtomBuilder = ValidatingAtomBuilder()

    override val podloveBuilder: EpisodePodloveBuilder = ValidatingEpisodePodloveBuilder()

    override val googleplayBuilder: EpisodeGoogleplayBuilder = ValidatingEpisodeGoogleplayBuilder()

    override val bitloveBuilder: EpisodeBitloveBuilder = ValidatingEpisodeBitloveBuilder()

    override val podcastindexBuilder: EpisodePodcastindexBuilder = ValidatingEpisodePodcastindexBuilder()

    override fun title(title: String): EpisodeBuilder = apply { this.title = title }

    override fun link(link: String?): EpisodeBuilder = apply { this.link = link }

    override fun description(description: String?): EpisodeBuilder = apply { this.description = description }

    override fun author(author: String?): EpisodeBuilder = apply { this.author = author }

    override fun addCategoryBuilder(categoryBuilder: RssCategoryBuilder): EpisodeBuilder =
        apply { categoryBuilders.add(categoryBuilder) }

    override fun comments(comments: String?): EpisodeBuilder = apply { this.comments = comments }

    override fun enclosureBuilder(enclosureBuilder: EpisodeEnclosureBuilder): EpisodeBuilder =
        apply { this.enclosureBuilder = enclosureBuilder }

    override fun guidBuilder(guidBuilder: EpisodeGuidBuilder?): EpisodeBuilder =
        apply { this.guidBuilder = guidBuilder }

    override fun pubDate(pubDate: TemporalAccessor?): EpisodeBuilder = apply { this.pubDate = pubDate }

    override fun source(source: String?): EpisodeBuilder = apply { this.source = source }

    override fun createEnclosureBuilder(): EpisodeEnclosureBuilder = ValidatingEpisodeEnclosureBuilder()

    override fun createGuidBuilder(): EpisodeGuidBuilder = ValidatingEpisodeGuidBuilder()

    override fun createLinkBuilder(): LinkBuilder = ValidatingLinkBuilder()

    override fun createAtomPersonBuilder(): AtomPersonBuilder = ValidatingAtomPersonBuilder()

    override fun createHrefOnlyImageBuilder(): HrefOnlyImageBuilder = ValidatingHrefOnlyImageBuilder()

    override fun createSimpleChapterBuilder(): EpisodePodloveSimpleChapterBuilder =
        ValidatingEpisodePodloveSimpleChapterBuilder()

    override fun createRssCategoryBuilder(): RssCategoryBuilder = ValidatingRssCategoryBuilder()

    override fun createTranscriptBuilder(): EpisodePodcastindexTranscriptBuilder =
        ValidatingEpisodePodcastindexTranscriptBuilder()

    override fun createChaptersBuilder(): EpisodePodcastindexChaptersBuilder =
        ValidatingEpisodePodcastindexChaptersBuilder()

    override fun createSoundbiteBuilder(): EpisodePodcastindexSoundbiteBuilder =
        ValidatingEpisodePodcastindexSoundbiteBuilder()

    override fun createPersonBuilder(): PodcastindexPersonBuilder =
        ValidatingPodcastindexPersonBuilder()

    override fun createLocationBuilder(): PodcastindexLocationBuilder =
        ValidatingPodcastindexLocationBuilder()

    override fun createSeasonBuilder(): EpisodePodcastindexSeasonBuilder =
        ValidatingEpisodePodcastindexSeasonBuilder()

    override fun createEpisodeBuilder(): EpisodePodcastindexEpisodeBuilder =
        ValidatingEpisodePodcastindexEpisodeBuilder()

    override val hasEnoughDataToBuild: Boolean
        get() = title != null && enclosureBuilder?.hasEnoughDataToBuild == true

    override fun build(): Episode? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Episode(
            title = checkRequiredProperty(::title),
            link = link,
            description = description,
            author = author,
            categories = categoryBuilders.mapNotNull { it.build() }.asUnmodifiable(),
            comments = comments,
            enclosure = checkRequiredValue(enclosureBuilder?.build(), "enclosure incomplete"),
            guid = guidBuilder?.build(),
            pubDate = pubDate,
            source = source,
            content = contentBuilder.build(),
            itunes = itunesBuilder.build(),
            atom = atomBuilder.build(),
            podlove = podloveBuilder.build(),
            googleplay = googleplayBuilder.build(),
            bitlove = bitloveBuilder.build(),
            podcastindex = podcastindexBuilder.build()
        )
    }

    @Suppress("ComplexMethod") // It's an equals
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ValidatingEpisodeBuilder) return false

        if (title != other.title) return false
        if (enclosureBuilder != other.enclosureBuilder) return false
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
        if (podcastindexBuilder != other.podcastindexBuilder) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + enclosureBuilder.hashCode()
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
        result = 31 * result + podcastindexBuilder.hashCode()
        return result
    }

    override fun toString(): String =
        "ValidatingEpisodeBuilder(title='$title', enclosureBuilder=$enclosureBuilder, link=$link, description=$description, author=$author, " +
            "categoryBuilders=$categoryBuilders, comments=$comments, guidBuilder=$guidBuilder, pubDate=$pubDate, source=$source, " +
            "contentBuilder=$contentBuilder, itunesBuilder=$itunesBuilder, atomBuilder=$atomBuilder, podloveBuilder=$podloveBuilder, " +
            "googleplayBuilder=$googleplayBuilder, bitloveBuilder=$bitloveBuilder, podcastindexBuilder=$podcastindexBuilder)"
}
