package io.hemin.wien.builder.validating.episode

import io.hemin.wien.builder.HrefOnlyImageBuilder
import io.hemin.wien.builder.ITunesStyleCategoryBuilder
import io.hemin.wien.builder.LinkBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.builder.RssCategoryBuilder
import io.hemin.wien.builder.episode.EpisodeAtomBuilder
import io.hemin.wien.builder.episode.EpisodeBitloveBuilder
import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.episode.EpisodeContentBuilder
import io.hemin.wien.builder.episode.EpisodeEnclosureBuilder
import io.hemin.wien.builder.episode.EpisodeGooglePlayBuilder
import io.hemin.wien.builder.episode.EpisodeGuidBuilder
import io.hemin.wien.builder.episode.EpisodeITunesBuilder
import io.hemin.wien.builder.episode.EpisodePodcastBuilder
import io.hemin.wien.builder.episode.EpisodePodcastChaptersBuilder
import io.hemin.wien.builder.episode.EpisodePodcastSoundbiteBuilder
import io.hemin.wien.builder.episode.EpisodePodcastTranscriptBuilder
import io.hemin.wien.builder.episode.EpisodePodloveBuilder
import io.hemin.wien.builder.episode.EpisodePodloveSimpleChapterBuilder
import io.hemin.wien.builder.validating.ValidatingHrefOnlyImageBuilder
import io.hemin.wien.builder.validating.ValidatingITunesStyleCategoryBuilder
import io.hemin.wien.builder.validating.ValidatingLinkBuilder
import io.hemin.wien.builder.validating.ValidatingPersonBuilder
import io.hemin.wien.builder.validating.ValidatingRssCategoryBuilder
import io.hemin.wien.model.Episode
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

    override val iTunesBuilder: EpisodeITunesBuilder = ValidatingEpisodeITunesBuilder()

    override val atomBuilder: EpisodeAtomBuilder = ValidatingEpisodeAtomBuilder()

    override val podloveBuilder: EpisodePodloveBuilder = ValidatingEpisodePodloveBuilder()

    override val googlePlayBuilder: EpisodeGooglePlayBuilder = ValidatingEpisodeGooglePlayBuilder()

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

    override fun createITunesStyleCategoryBuilder(): ITunesStyleCategoryBuilder = ValidatingITunesStyleCategoryBuilder()

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
            iTunes = iTunesBuilder.build(),
            atom = atomBuilder.build(),
            podlove = podloveBuilder.build(),
            googlePlay = googlePlayBuilder.build(),
            bitlove = bitloveBuilder.build(),
            podcast = podcastBuilder.build()
        )
    }
}
