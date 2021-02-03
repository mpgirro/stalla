package io.hemin.wien.builder.podcast

import io.hemin.wien.builder.AtomBuilder
import io.hemin.wien.builder.Builder
import io.hemin.wien.builder.HrefOnlyImageBuilder
import io.hemin.wien.builder.ITunesStyleCategoryBuilder
import io.hemin.wien.builder.LinkBuilderProvider
import io.hemin.wien.builder.PersonBuilderProvider
import io.hemin.wien.builder.RssCategoryBuilder
import io.hemin.wien.builder.RssImageBuilder
import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import io.hemin.wien.model.RssCategory
import io.hemin.wien.model.RssImage
import io.hemin.wien.util.whenNotNull
import java.time.temporal.TemporalAccessor

/** Builder for constructing [Podcast] instances. */
interface PodcastBuilder : Builder<Podcast>, PersonBuilderProvider, LinkBuilderProvider {

    /** The builder for data from the iTunes namespace. */
    val iTunesBuilder: PodcastITunesBuilder

    /** The builder for data from the Atom namespace. */
    val atomBuilder: AtomBuilder

    /** The builder for data from the Fyyd namespace. */
    val fyydBuilder: PodcastFyydBuilder

    /** The builder for data from the Feedpress namespace. */
    val feedpressBuilder: PodcastFeedpressBuilder

    /** The builder for data from the Google Play namespace. */
    val googlePlayBuilder: PodcastGooglePlayBuilder

    /** Set the Podcast namespace builder. */
    val podcastBuilder: PodcastPodcastBuilder

    /** Set the title value. */
    fun title(title: String): PodcastBuilder

    /** Set the link value. */
    fun link(link: String): PodcastBuilder

    /** Set the description value. */
    fun description(description: String): PodcastBuilder

    /** Set the pubDate value. */
    fun pubDate(pubDate: TemporalAccessor?): PodcastBuilder

    /** Set the lastBuildDate value. */
    fun lastBuildDate(lastBuildDate: TemporalAccessor?): PodcastBuilder

    /** Set the language value. */
    fun language(language: String): PodcastBuilder

    /** Set the generator value. */
    fun generator(generator: String?): PodcastBuilder

    /** Set the copyright value. */
    fun copyright(copyright: String?): PodcastBuilder

    /** Set the docs value. */
    fun docs(docs: String?): PodcastBuilder

    /** Set the managingEditor value. */
    fun managingEditor(managingEditor: String?): PodcastBuilder

    /** Set the webMaster value. */
    fun webMaster(webMaster: String?): PodcastBuilder

    /** Set the [RssImageBuilder]. */
    fun imageBuilder(imageBuilder: RssImageBuilder?): PodcastBuilder

    /** Adds an [EpisodeBuilder] to the list of episode builders. */
    fun addEpisodeBuilder(episodeBuilder: EpisodeBuilder): PodcastBuilder

    /** Adds multiple [EpisodeBuilder] to the list of episode builders. */
    fun addEpisodeBuilders(episodeBuilders: List<EpisodeBuilder>): PodcastBuilder = apply {
        episodeBuilders.forEach { episodeBuilder -> addEpisodeBuilder(episodeBuilder) }
    }

    /** Adds an [RssCategoryBuilder] to the list of category builders. */
    fun addCategoryBuilder(categoryBuilder: RssCategoryBuilder): PodcastBuilder

    /** Adds multiple [RssCategoryBuilder] to the list of category builders. */
    fun addCategoryBuilders(categoryBuilders: List<RssCategoryBuilder>): PodcastBuilder = apply {
        categoryBuilders.forEach { categoryBuilder -> addCategoryBuilder(categoryBuilder) }
    }

    /** Creates an instance of [RssImageBuilder] to use with this builder. */
    fun createRssImageBuilder(): RssImageBuilder

    /** Creates an instance of [HrefOnlyImageBuilder] to use with this builder. */
    fun createHrefOnlyImageBuilder(): HrefOnlyImageBuilder

    /** Creates an instance of [RssCategoryBuilder] to use with this builder. */
    fun createRssCategoryBuilder(): RssCategoryBuilder

    /** Creates an instance of [ITunesStyleCategoryBuilder] to use with this builder. */
    fun createITunesStyleCategoryBuilder(): ITunesStyleCategoryBuilder

    /** Creates an instance of [PodcastPodcastLockedBuilder] to use with this builder. */
    fun createPodcastPodcastLockedBuilder(): PodcastPodcastLockedBuilder

    /** Creates an instance of [PodcastPodcastFundingBuilder] to use with this builder. */
    fun createPodcastPodcastFundingBuilder(): PodcastPodcastFundingBuilder

    override fun from(model: Podcast?): PodcastBuilder = whenNotNull(model) { podcast ->
        iTunesBuilder.from(podcast.iTunes)
        atomBuilder.from(podcast.atom)
        fyydBuilder.from(podcast.fyyd)
        feedpressBuilder.from(podcast.feedpress)
        googlePlayBuilder.from(podcast.googlePlay)
        podcastBuilder.from(podcast.podcast)
        title(podcast.title)
        link(podcast.link)
        description(podcast.description)
        pubDate(podcast.pubDate)
        lastBuildDate(podcast.lastBuildDate)
        language(podcast.language)
        generator(podcast.generator)
        copyright(podcast.copyright)
        docs(podcast.docs)
        managingEditor(podcast.managingEditor)
        webMaster(podcast.webMaster)
        imageBuilder(RssImage.builder().from(podcast.image))
        addEpisodeBuilders(podcast.episodes.map { episode -> Episode.builder().from(episode) })
        addCategoryBuilders(podcast.categories.map { category -> RssCategory.builder().from(category) })
    }
}
