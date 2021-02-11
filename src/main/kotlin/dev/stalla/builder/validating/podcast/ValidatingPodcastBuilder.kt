package dev.stalla.builder.validating.podcast

import dev.stalla.builder.AtomBuilder
import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.ItunesStyleCategoryBuilder
import dev.stalla.builder.LinkBuilder
import dev.stalla.builder.PersonBuilder
import dev.stalla.builder.RssCategoryBuilder
import dev.stalla.builder.RssImageBuilder
import dev.stalla.builder.episode.EpisodeBuilder
import dev.stalla.builder.podcast.PodcastBuilder
import dev.stalla.builder.podcast.PodcastFeedpressBuilder
import dev.stalla.builder.podcast.PodcastFyydBuilder
import dev.stalla.builder.podcast.PodcastGoogleplayBuilder
import dev.stalla.builder.podcast.PodcastItunesBuilder
import dev.stalla.builder.podcast.PodcastPodcastBuilder
import dev.stalla.builder.podcast.PodcastPodcastFundingBuilder
import dev.stalla.builder.podcast.PodcastPodcastLockedBuilder
import dev.stalla.builder.validating.ValidatingAtomBuilder
import dev.stalla.builder.validating.ValidatingHrefOnlyImageBuilder
import dev.stalla.builder.validating.ValidatingItunesStyleCategoryBuilder
import dev.stalla.builder.validating.ValidatingLinkBuilder
import dev.stalla.builder.validating.ValidatingPersonBuilder
import dev.stalla.builder.validating.ValidatingRssCategoryBuilder
import dev.stalla.builder.validating.ValidatingRssImageBuilder
import dev.stalla.model.Podcast
import java.time.temporal.TemporalAccessor

internal class ValidatingPodcastBuilder : PodcastBuilder {

    private lateinit var titleValue: String
    private lateinit var linkValue: String
    private lateinit var descriptionValue: String
    private lateinit var languageValue: String

    private var pubDate: TemporalAccessor? = null
    private var lastBuildDate: TemporalAccessor? = null
    private var generator: String? = null
    private var copyright: String? = null
    private var docs: String? = null
    private var managingEditor: String? = null
    private var webMaster: String? = null
    private var ttl: Int? = null
    private var imageBuilder: RssImageBuilder? = null
    private val categoryBuilders: MutableList<RssCategoryBuilder> = mutableListOf()

    private val episodeBuilders: MutableList<EpisodeBuilder> = mutableListOf()

    override val itunesBuilder: PodcastItunesBuilder = ValidatingPodcastItunesBuilder()

    override val atomBuilder: AtomBuilder = ValidatingAtomBuilder()

    override val fyydBuilder: PodcastFyydBuilder = ValidatingPodcastFyydBuilder()

    override val feedpressBuilder: PodcastFeedpressBuilder = ValidatingPodcastFeedpressBuilder()

    override val googleplayBuilder: PodcastGoogleplayBuilder = ValidatingPodcastGoogleplayBuilder()

    override val podcastBuilder: PodcastPodcastBuilder = ValidatingPodcastPodcastBuilder()

    override fun title(title: String): PodcastBuilder = apply { this.titleValue = title }

    override fun link(link: String): PodcastBuilder = apply { this.linkValue = link }

    override fun description(description: String): PodcastBuilder = apply { this.descriptionValue = description }

    override fun pubDate(pubDate: TemporalAccessor?): PodcastBuilder = apply { this.pubDate = pubDate }

    override fun lastBuildDate(lastBuildDate: TemporalAccessor?): PodcastBuilder = apply { this.lastBuildDate = lastBuildDate }

    override fun language(language: String): PodcastBuilder = apply { this.languageValue = language }

    override fun generator(generator: String?): PodcastBuilder = apply { this.generator = generator }

    override fun copyright(copyright: String?): PodcastBuilder = apply { this.copyright = copyright }

    override fun docs(docs: String?): PodcastBuilder = apply { this.docs = docs }

    override fun managingEditor(managingEditor: String?): PodcastBuilder = apply { this.managingEditor = managingEditor }

    override fun webMaster(webMaster: String?): PodcastBuilder = apply { this.webMaster = webMaster }

    override fun ttl(ttl: Int?): PodcastBuilder = apply { this.ttl = ttl }

    override fun imageBuilder(imageBuilder: RssImageBuilder?): PodcastBuilder = apply { this.imageBuilder = imageBuilder }

    override fun addEpisodeBuilder(episodeBuilder: EpisodeBuilder): PodcastBuilder = apply {
        episodeBuilders.add(episodeBuilder)
    }

    override fun addCategoryBuilder(categoryBuilder: RssCategoryBuilder): PodcastBuilder = apply {
        categoryBuilders.add(categoryBuilder)
    }

    override fun createRssImageBuilder(): RssImageBuilder = ValidatingRssImageBuilder()

    override fun createHrefOnlyImageBuilder(): HrefOnlyImageBuilder = ValidatingHrefOnlyImageBuilder()

    override fun createLinkBuilder(): LinkBuilder = ValidatingLinkBuilder()

    override fun createPersonBuilder(): PersonBuilder = ValidatingPersonBuilder()

    override fun createRssCategoryBuilder(): RssCategoryBuilder = ValidatingRssCategoryBuilder()

    override fun createITunesStyleCategoryBuilder(): ItunesStyleCategoryBuilder = ValidatingItunesStyleCategoryBuilder()

    override fun createPodcastPodcastLockedBuilder(): PodcastPodcastLockedBuilder = ValidatingPodcastPodcastLockedBuilder()

    override fun createPodcastPodcastFundingBuilder(): PodcastPodcastFundingBuilder = ValidatingPodcastPodcastFundingBuilder()

    override val hasEnoughDataToBuild: Boolean
        get() = episodeBuilders.any { it.hasEnoughDataToBuild } &&
            ::titleValue.isInitialized && ::descriptionValue.isInitialized &&
            ::linkValue.isInitialized && ::languageValue.isInitialized

    override fun build(): Podcast? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        val builtEpisodes = episodeBuilders.mapNotNull { it.build() }
        return Podcast(
            title = titleValue,
            link = linkValue,
            description = descriptionValue,
            pubDate = pubDate,
            lastBuildDate = lastBuildDate,
            language = languageValue,
            generator = generator,
            copyright = copyright,
            docs = docs,
            managingEditor = managingEditor,
            webMaster = webMaster,
            ttl = ttl,
            image = imageBuilder?.build(),
            episodes = builtEpisodes,
            itunes = itunesBuilder.build(),
            atom = atomBuilder.build(),
            fyyd = fyydBuilder.build(),
            feedpress = feedpressBuilder.build(),
            googleplay = googleplayBuilder.build(),
            categories = categoryBuilders.mapNotNull { it.build() },
            podcast = podcastBuilder.build()
        )
    }
}
