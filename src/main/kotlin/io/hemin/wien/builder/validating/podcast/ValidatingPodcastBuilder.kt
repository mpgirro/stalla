package io.hemin.wien.builder.validating.podcast

import io.hemin.wien.builder.HrefOnlyImageBuilder
import io.hemin.wien.builder.ITunesStyleCategoryBuilder
import io.hemin.wien.builder.LinkBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.builder.RssCategoryBuilder
import io.hemin.wien.builder.RssImageBuilder
import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.podcast.PodcastAtomBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.builder.podcast.PodcastFeedpressBuilder
import io.hemin.wien.builder.podcast.PodcastFyydBuilder
import io.hemin.wien.builder.podcast.PodcastGooglePlayBuilder
import io.hemin.wien.builder.podcast.PodcastITunesBuilder
import io.hemin.wien.builder.validating.ValidatingHrefOnlyImageBuilder
import io.hemin.wien.builder.validating.ValidatingITunesStyleCategoryBuilder
import io.hemin.wien.builder.validating.ValidatingLinkBuilder
import io.hemin.wien.builder.validating.ValidatingPersonBuilder
import io.hemin.wien.builder.validating.ValidatingRssCategoryBuilder
import io.hemin.wien.builder.validating.ValidatingRssImageBuilder
import io.hemin.wien.model.Podcast
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
    private var imageBuilder: RssImageBuilder? = null
    private val categoryBuilders: MutableList<RssCategoryBuilder> = mutableListOf()

    private val episodeBuilders: MutableList<EpisodeBuilder> = mutableListOf()

    override val iTunes: PodcastITunesBuilder = ValidatingPodcastITunesBuilder()

    override val atom: PodcastAtomBuilder = ValidatingPodcastAtomBuilder()

    override val fyyd: PodcastFyydBuilder = ValidatingPodcastFyydBuilder()

    override val feedpress: PodcastFeedpressBuilder = ValidatingPodcastFeedpressBuilder()

    override val googlePlay: PodcastGooglePlayBuilder = ValidatingPodcastGooglePlayBuilder()

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

    override fun createITunesCategoryBuilder(): ITunesStyleCategoryBuilder = ValidatingITunesStyleCategoryBuilder()

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
            image = imageBuilder?.build(),
            episodes = builtEpisodes,
            iTunes = iTunes.build(),
            atom = atom.build(),
            fyyd = fyyd.build(),
            feedpress = feedpress.build(),
            googlePlay = googlePlay.build(),
            categories = categoryBuilders.mapNotNull { it.build() }
        )
    }
}
