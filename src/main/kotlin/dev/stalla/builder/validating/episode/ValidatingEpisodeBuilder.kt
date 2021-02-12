package dev.stalla.builder.validating.episode

import dev.stalla.builder.AtomBuilder
import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.ItunesStyleCategoryBuilder2
import dev.stalla.builder.LinkBuilder
import dev.stalla.builder.PersonBuilder
import dev.stalla.builder.RssCategoryBuilder
import dev.stalla.builder.episode.EpisodeBitloveBuilder
import dev.stalla.builder.episode.EpisodeBuilder
import dev.stalla.builder.episode.EpisodeContentBuilder
import dev.stalla.builder.episode.EpisodeEnclosureBuilder
import dev.stalla.builder.episode.EpisodeGoogleplayBuilder2
import dev.stalla.builder.episode.EpisodeGuidBuilder
import dev.stalla.builder.episode.EpisodeItunesBuilder2
import dev.stalla.builder.episode.EpisodePodcastBuilder
import dev.stalla.builder.episode.EpisodePodcastChaptersBuilder
import dev.stalla.builder.episode.EpisodePodcastSoundbiteBuilder
import dev.stalla.builder.episode.EpisodePodcastTranscriptBuilder
import dev.stalla.builder.episode.EpisodePodloveBuilder
import dev.stalla.builder.episode.EpisodePodloveSimpleChapterBuilder
import dev.stalla.builder.validating.ValidatingAtomBuilder
import dev.stalla.builder.validating.ValidatingHrefOnlyImageBuilder
import dev.stalla.builder.validating.ValidatingItunesStyleCategoryBuilder2
import dev.stalla.builder.validating.ValidatingLinkBuilder
import dev.stalla.builder.validating.ValidatingPersonBuilder
import dev.stalla.builder.validating.ValidatingRssCategoryBuilder
import dev.stalla.model.Episode
import java.time.temporal.TemporalAccessor

internal class ValidatingEpisodeBuilder : EpisodeBuilder {

    private lateinit var titleValue: String
    private lateinit var enclosureBuilderValue: EpisodeEnclosureBuilder

    private var link: String? = null
    private var description: String? = null
    private var author: String? = null
    private val categoryBuilders: MutableList<RssCategoryBuilder> = mutableListOf()
    private var comments: String? = null
    private var guidBuilder: EpisodeGuidBuilder? = null
    private var pubDate: TemporalAccessor? = null
    private var source: String? = null

    override val contentBuilder: EpisodeContentBuilder = ValidatingEpisodeContentBuilder()

    override val itunesBuilder: EpisodeItunesBuilder2 = ValidatingEpisodeItunesBuilder2()

    override val atomBuilder: AtomBuilder = ValidatingAtomBuilder()

    override val podloveBuilder: EpisodePodloveBuilder = ValidatingEpisodePodloveBuilder()

    override val googleplayBuilder: EpisodeGoogleplayBuilder2 = ValidatingEpisodeGoogleplayBuilder2()

    override val bitloveBuilder: EpisodeBitloveBuilder = ValidatingEpisodeBitloveBuilder()

    override val podcastBuilder: EpisodePodcastBuilder = ValidatingEpisodePodcastBuilder()

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

    override fun createEnclosureBuilder(): EpisodeEnclosureBuilder = ValidatingEpisodeEnclosureBuilder()

    override fun createGuidBuilder(): EpisodeGuidBuilder = ValidatingEpisodeGuidBuilder()

    override fun createLinkBuilder(): LinkBuilder = ValidatingLinkBuilder()

    override fun createPersonBuilder(): PersonBuilder = ValidatingPersonBuilder()

    override fun createHrefOnlyImageBuilder(): HrefOnlyImageBuilder = ValidatingHrefOnlyImageBuilder()

    override fun createPodloveSimpleChapterBuilder(): EpisodePodloveSimpleChapterBuilder = ValidatingEpisodePodloveSimpleChapterBuilder()

    override fun createRssCategoryBuilder(): RssCategoryBuilder = ValidatingRssCategoryBuilder()

    override fun createITunesStyleCategoryBuilder(): ItunesStyleCategoryBuilder2 = ValidatingItunesStyleCategoryBuilder2()

    override fun createEpisodePodcastTranscriptBuilder(): EpisodePodcastTranscriptBuilder = ValidatingEpisodePodcastTranscriptBuilder()

    override fun createEpisodePodcastChaptersBuilder(): EpisodePodcastChaptersBuilder = ValidatingEpisodePodcastChaptersBuilder()

    override fun createEpisodePodcastSoundbiteBuilder(): EpisodePodcastSoundbiteBuilder = ValidatingEpisodePodcastSoundbiteBuilder()

    override val hasEnoughDataToBuild: Boolean
        get() = ::titleValue.isInitialized && (::enclosureBuilderValue.isInitialized && enclosureBuilderValue.hasEnoughDataToBuild)

    override fun build(): Episode? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        val enclosure = enclosureBuilderValue.build()
            ?: throw IllegalStateException("Cannot build the enclosure, while hasEnoughDataToBuild == true")

        return Episode(
            title = titleValue,
            link = link,
            description = description,
            author = author,
            categories = categoryBuilders.mapNotNull { it.build() },
            comments = comments,
            enclosure = enclosure,
            guid = guidBuilder?.build(),
            pubDate = pubDate,
            source = source,
            content = contentBuilder.build(),
            itunes = itunesBuilder.build(),
            atom = atomBuilder.build(),
            podlove = podloveBuilder.build(),
            googleplay = googleplayBuilder.build(),
            bitlove = bitloveBuilder.build(),
            podcast = podcastBuilder.build()
        )
    }
}
