package dev.stalla.builder.validating.podcast

import dev.stalla.builder.AtomBuilder
import dev.stalla.builder.AtomPersonBuilder
import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.LinkBuilder
import dev.stalla.builder.PodcastindexLocationBuilder
import dev.stalla.builder.PodcastindexPersonBuilder
import dev.stalla.builder.RssCategoryBuilder
import dev.stalla.builder.RssImageBuilder
import dev.stalla.builder.episode.EpisodeBuilder
import dev.stalla.builder.podcast.PodcastBuilder
import dev.stalla.builder.podcast.PodcastFeedpressBuilder
import dev.stalla.builder.podcast.PodcastFyydBuilder
import dev.stalla.builder.podcast.PodcastGoogleplayBuilder
import dev.stalla.builder.podcast.PodcastItunesBuilder
import dev.stalla.builder.podcast.PodcastItunesOwnerBuilder
import dev.stalla.builder.podcast.PodcastPodcastindexBuilder
import dev.stalla.builder.podcast.PodcastPodcastindexFundingBuilder
import dev.stalla.builder.podcast.PodcastPodcastindexLockedBuilder
import dev.stalla.builder.podcast.ProvidingPodcastBuilder
import dev.stalla.builder.validating.ValidatingAtomBuilder
import dev.stalla.builder.validating.ValidatingAtomPersonBuilder
import dev.stalla.builder.validating.ValidatingHrefOnlyImageBuilder
import dev.stalla.builder.validating.ValidatingLinkBuilder
import dev.stalla.builder.validating.ValidatingPodcastindexLocationBuilder
import dev.stalla.builder.validating.ValidatingPodcastindexPersonBuilder
import dev.stalla.builder.validating.ValidatingRssCategoryBuilder
import dev.stalla.builder.validating.ValidatingRssImageBuilder
import dev.stalla.builder.validating.checkRequiredProperty
import dev.stalla.model.Podcast
import dev.stalla.util.InternalAPI
import dev.stalla.util.asUnmodifiable
import java.time.temporal.TemporalAccessor
import java.util.Locale

@InternalAPI
internal class ValidatingPodcastBuilder : ProvidingPodcastBuilder {

    private var title: String? = null
    private var link: String? = null
    private var description: String? = null
    private var language: Locale? = null

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

    override val podcastindexBuilder: PodcastPodcastindexBuilder = ValidatingPodcastPodcastindexBuilder()

    override val podcastPodcastindexBuilder: PodcastPodcastindexBuilder by this::podcastindexBuilder

    override fun title(title: String): PodcastBuilder = apply { this.title = title }

    override fun link(link: String): PodcastBuilder = apply { this.link = link }

    override fun description(description: String): PodcastBuilder = apply { this.description = description }

    override fun pubDate(pubDate: TemporalAccessor?): PodcastBuilder = apply { this.pubDate = pubDate }

    override fun lastBuildDate(lastBuildDate: TemporalAccessor?): PodcastBuilder =
        apply { this.lastBuildDate = lastBuildDate }

    override fun language(language: Locale): PodcastBuilder = apply { this.language = language }

    override fun generator(generator: String?): PodcastBuilder = apply { this.generator = generator }

    override fun copyright(copyright: String?): PodcastBuilder = apply { this.copyright = copyright }

    override fun docs(docs: String?): PodcastBuilder = apply { this.docs = docs }

    override fun managingEditor(managingEditor: String?): PodcastBuilder =
        apply { this.managingEditor = managingEditor }

    override fun webMaster(webMaster: String?): PodcastBuilder = apply { this.webMaster = webMaster }

    override fun ttl(ttl: Int?): PodcastBuilder = apply { this.ttl = ttl }

    override fun imageBuilder(imageBuilder: RssImageBuilder?): PodcastBuilder =
        apply { this.imageBuilder = imageBuilder }

    override fun addEpisodeBuilder(episodeBuilder: EpisodeBuilder): PodcastBuilder =
        apply { episodeBuilders.add(episodeBuilder) }

    override fun addCategoryBuilder(categoryBuilder: RssCategoryBuilder): PodcastBuilder =
        apply { categoryBuilders.add(categoryBuilder) }

    override fun createRssImageBuilder(): RssImageBuilder = ValidatingRssImageBuilder()

    override fun createHrefOnlyImageBuilder(): HrefOnlyImageBuilder = ValidatingHrefOnlyImageBuilder()

    override fun createLinkBuilder(): LinkBuilder = ValidatingLinkBuilder()

    override fun createAtomPersonBuilder(): AtomPersonBuilder = ValidatingAtomPersonBuilder()

    override fun createRssCategoryBuilder(): RssCategoryBuilder = ValidatingRssCategoryBuilder()

    override fun createItunesOwnerBuilder(): PodcastItunesOwnerBuilder = ValidatingPodcastItunesOwnerBuilder()

    override fun createLockedBuilder(): PodcastPodcastindexLockedBuilder =
        ValidatingPodcastPodcastindexLockedBuilder()

    override fun createFundingBuilder(): PodcastPodcastindexFundingBuilder =
        ValidatingPodcastPodcastindexFundingBuilder()

    override fun createPersonBuilder(): PodcastindexPersonBuilder =
        ValidatingPodcastindexPersonBuilder()

    override fun createLocationBuilder(): PodcastindexLocationBuilder =
        ValidatingPodcastindexLocationBuilder()

    override val hasEnoughDataToBuild: Boolean
        get() = episodeBuilders.any { it.hasEnoughDataToBuild } &&
            title != null && description != null &&
            link != null && language != null

    override fun build(): Podcast? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Podcast(
            title = checkRequiredProperty(::title),
            link = checkRequiredProperty(::link),
            description = checkRequiredProperty(::description),
            pubDate = pubDate,
            lastBuildDate = lastBuildDate,
            language = checkRequiredProperty(::language),
            generator = generator,
            copyright = copyright,
            docs = docs,
            managingEditor = managingEditor,
            webMaster = webMaster,
            ttl = ttl,
            image = imageBuilder?.build(),
            episodes = episodeBuilders.mapNotNull { it.build() }.asUnmodifiable(),
            itunes = itunesBuilder.build(),
            atom = atomBuilder.build(),
            fyyd = fyydBuilder.build(),
            feedpress = feedpressBuilder.build(),
            googleplay = googleplayBuilder.build(),
            categories = categoryBuilders.mapNotNull { it.build() }.asUnmodifiable(),
            podcastindex = podcastindexBuilder.build()
        )
    }

    @Suppress("ComplexMethod") // It's an equals
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ValidatingPodcastBuilder) return false

        if (title != other.title) return false
        if (link != other.link) return false
        if (description != other.description) return false
        if (language != other.language) return false
        if (pubDate != other.pubDate) return false
        if (lastBuildDate != other.lastBuildDate) return false
        if (generator != other.generator) return false
        if (copyright != other.copyright) return false
        if (docs != other.docs) return false
        if (managingEditor != other.managingEditor) return false
        if (webMaster != other.webMaster) return false
        if (ttl != other.ttl) return false
        if (imageBuilder != other.imageBuilder) return false
        if (categoryBuilders != other.categoryBuilders) return false
        if (episodeBuilders != other.episodeBuilders) return false
        if (itunesBuilder != other.itunesBuilder) return false
        if (atomBuilder != other.atomBuilder) return false
        if (fyydBuilder != other.fyydBuilder) return false
        if (feedpressBuilder != other.feedpressBuilder) return false
        if (googleplayBuilder != other.googleplayBuilder) return false
        if (podcastPodcastindexBuilder != other.podcastPodcastindexBuilder) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + link.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + language.hashCode()
        result = 31 * result + (pubDate?.hashCode() ?: 0)
        result = 31 * result + (lastBuildDate?.hashCode() ?: 0)
        result = 31 * result + (generator?.hashCode() ?: 0)
        result = 31 * result + (copyright?.hashCode() ?: 0)
        result = 31 * result + (docs?.hashCode() ?: 0)
        result = 31 * result + (managingEditor?.hashCode() ?: 0)
        result = 31 * result + (webMaster?.hashCode() ?: 0)
        result = 31 * result + (ttl ?: 0)
        result = 31 * result + (imageBuilder?.hashCode() ?: 0)
        result = 31 * result + categoryBuilders.hashCode()
        result = 31 * result + episodeBuilders.hashCode()
        result = 31 * result + itunesBuilder.hashCode()
        result = 31 * result + atomBuilder.hashCode()
        result = 31 * result + fyydBuilder.hashCode()
        result = 31 * result + feedpressBuilder.hashCode()
        result = 31 * result + googleplayBuilder.hashCode()
        result = 31 * result + podcastPodcastindexBuilder.hashCode()
        return result
    }

    override fun toString(): String =
        "ValidatingPodcastBuilder(title='$title', link='$link', description='$description', language=$language, pubDate=$pubDate, " +
            "lastBuildDate=$lastBuildDate, generator=$generator, copyright=$copyright, docs=$docs, managingEditor=$managingEditor, " +
            "webMaster=$webMaster, ttl=$ttl, imageBuilder=$imageBuilder, categoryBuilders=$categoryBuilders, episodeBuilders=$episodeBuilders, " +
            "itunesBuilder=$itunesBuilder, atomBuilder=$atomBuilder, fyydBuilder=$fyydBuilder, feedpressBuilder=$feedpressBuilder, " +
            "googleplayBuilder=$googleplayBuilder, podcastPodcastindexBuilder=$podcastPodcastindexBuilder)"
}
