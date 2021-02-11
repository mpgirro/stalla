package dev.stalla.builder.podcast

import dev.stalla.builder.AtomBuilder
import dev.stalla.builder.Builder
import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.ItunesStyleCategoryBuilder
import dev.stalla.builder.LinkBuilderProvider
import dev.stalla.builder.PersonBuilderProvider
import dev.stalla.builder.RssCategoryBuilder
import dev.stalla.builder.RssImageBuilder
import dev.stalla.builder.episode.EpisodeBuilder
import dev.stalla.model.Podcast
import dev.stalla.model.rss.RssImage
import dev.stalla.util.asBuilders
import dev.stalla.util.whenNotNull
import java.time.temporal.TemporalAccessor

/** Builder for constructing [Podcast] instances. */
public interface PodcastBuilder : Builder<Podcast>, PersonBuilderProvider, LinkBuilderProvider {

    /** The builder for data from the iTunes namespace. */
    public val itunesBuilder: PodcastItunesBuilder

    /** The builder for data from the Atom namespace. */
    public val atomBuilder: AtomBuilder

    /** The builder for data from the Fyyd namespace. */
    public val fyydBuilder: PodcastFyydBuilder

    /** The builder for data from the Feedpress namespace. */
    public val feedpressBuilder: PodcastFeedpressBuilder

    /** The builder for data from the Google Play namespace. */
    public val googlePlayBuilder: PodcastGoogleplayBuilder

    /** Set the Podcast namespace builder. */
    public val podcastBuilder: PodcastPodcastBuilder

    /** Set the title value. */
    public fun title(title: String): PodcastBuilder

    /** Set the link value. */
    public fun link(link: String): PodcastBuilder

    /** Set the description value. */
    public fun description(description: String): PodcastBuilder

    /** Set the pubDate value. */
    public fun pubDate(pubDate: TemporalAccessor?): PodcastBuilder

    /** Set the lastBuildDate value. */
    public fun lastBuildDate(lastBuildDate: TemporalAccessor?): PodcastBuilder

    /** Set the language value. */
    public fun language(language: String): PodcastBuilder

    /** Set the generator value. */
    public fun generator(generator: String?): PodcastBuilder

    /** Set the copyright value. */
    public fun copyright(copyright: String?): PodcastBuilder

    /** Set the docs value. */
    public fun docs(docs: String?): PodcastBuilder

    /** Set the managingEditor value. */
    public fun managingEditor(managingEditor: String?): PodcastBuilder

    /** Set the webMaster value. */
    public fun webMaster(webMaster: String?): PodcastBuilder

    /** Set the time to live (ttl) value. */
    public fun ttl(ttl: Int?): PodcastBuilder

    /** Set the [RssImageBuilder]. */
    public fun imageBuilder(imageBuilder: RssImageBuilder?): PodcastBuilder

    /** Adds an [EpisodeBuilder] to the list of episode builders. */
    public fun addEpisodeBuilder(episodeBuilder: EpisodeBuilder): PodcastBuilder

    /** Adds multiple [EpisodeBuilder] to the list of episode builders. */
    public fun addEpisodeBuilders(episodeBuilders: List<EpisodeBuilder>): PodcastBuilder = apply {
        episodeBuilders.forEach(::addEpisodeBuilder)
    }

    /** Adds an [RssCategoryBuilder] to the list of category builders. */
    public fun addCategoryBuilder(categoryBuilder: RssCategoryBuilder): PodcastBuilder

    /** Adds multiple [RssCategoryBuilder] to the list of category builders. */
    public fun addCategoryBuilders(categoryBuilders: List<RssCategoryBuilder>): PodcastBuilder = apply {
        categoryBuilders.forEach(::addCategoryBuilder)
    }

    /** Creates an instance of [RssImageBuilder] to use with this builder. */
    public fun createRssImageBuilder(): RssImageBuilder

    /** Creates an instance of [HrefOnlyImageBuilder] to use with this builder. */
    public fun createHrefOnlyImageBuilder(): HrefOnlyImageBuilder

    /** Creates an instance of [RssCategoryBuilder] to use with this builder. */
    public fun createRssCategoryBuilder(): RssCategoryBuilder

    /** Creates an instance of [ItunesStyleCategoryBuilder] to use with this builder. */
    public fun createITunesStyleCategoryBuilder(): ItunesStyleCategoryBuilder

    /** Creates an instance of [PodcastPodcastLockedBuilder] to use with this builder. */
    public fun createPodcastPodcastLockedBuilder(): PodcastPodcastLockedBuilder

    /** Creates an instance of [PodcastPodcastFundingBuilder] to use with this builder. */
    public fun createPodcastPodcastFundingBuilder(): PodcastPodcastFundingBuilder

    override fun from(model: Podcast?): PodcastBuilder = whenNotNull(model) { podcast ->
        itunesBuilder.from(podcast.itunes)
        atomBuilder.from(podcast.atom)
        fyydBuilder.from(podcast.fyyd)
        feedpressBuilder.from(podcast.feedpress)
        googlePlayBuilder.from(podcast.googleplay)
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
        ttl(podcast.ttl)
        imageBuilder(RssImage.builder().from(podcast.image))
        addEpisodeBuilders(podcast.episodes.asBuilders())
        addCategoryBuilders(podcast.categories.asBuilders())
    }
}
