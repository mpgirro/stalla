package io.hemin.wien.builder.podcast

import io.hemin.wien.builder.Builder
import io.hemin.wien.builder.HrefOnlyImageBuilder
import io.hemin.wien.builder.ITunesStyleCategoryBuilder
import io.hemin.wien.builder.LinkBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.builder.RssCategoryBuilder
import io.hemin.wien.builder.RssImageBuilder
import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import java.time.temporal.TemporalAccessor

internal interface PodcastBuilder : Builder<Podcast> {

    /** The builder for data from the iTunes namespace. */
    val iTunesBuilder: PodcastITunesBuilder

    /** The builder for data from the Atom namespace. */
    val atomBuilder: PodcastAtomBuilder

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

    /** Set the Image builder. */
    fun imageBuilder(imageBuilder: RssImageBuilder?): PodcastBuilder

    /**
     * Adds an [EpisodeBuilder] to the list of episodes.
     *
     * @param episodeBuilder The [EpisodeBuilder] to add.
     */
    fun addEpisodeBuilder(episodeBuilder: EpisodeBuilder): PodcastBuilder

    /**
     * Adds a category to the list of categories.
     *
     * @param categoryBuilder The The [RssCategoryBuilder] used to initialize the
     * [Episode.categories] items when [build] is called.
     */
    fun addCategoryBuilder(categoryBuilder: RssCategoryBuilder): PodcastBuilder

    /** Creates an instance of [RssImageBuilder] to use with this builder. */
    fun createRssImageBuilder(): RssImageBuilder

    /** Creates an instance of [HrefOnlyImageBuilder] to use with this builder. */
    fun createHrefOnlyImageBuilder(): HrefOnlyImageBuilder

    /** Creates an instance of [LinkBuilder] to use with this builder. */
    fun createLinkBuilder(): LinkBuilder

    /** Creates an instance of [PersonBuilder] to use with this builder. */
    fun createPersonBuilder(): PersonBuilder

    /** Creates an instance of [RssCategoryBuilder] to use with this builder. */
    fun createRssCategoryBuilder(): RssCategoryBuilder

    /** Creates an instance of [ITunesStyleCategoryBuilder] to use with this builder. */
    fun createITunesStyleCategoryBuilder(): ITunesStyleCategoryBuilder

    /** Creates an instance of [PodcastPodcastLockedBuilder] to use with this builder. */
    fun createPodcastPodcastLockedBuilder(): PodcastPodcastLockedBuilder

    /** Creates an instance of [PodcastPodcastFundingBuilder] to use with this builder. */
    fun createPodcastPodcastFundingBuilder(): PodcastPodcastFundingBuilder
}
