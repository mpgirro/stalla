package io.hemin.wien.builder.podcast

import io.hemin.wien.builder.Builder
import io.hemin.wien.builder.HrefOnlyImageBuilder
import io.hemin.wien.builder.ITunesStyleCategoryBuilder
import io.hemin.wien.builder.LinkBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.builder.RssCategoryBuilder
import io.hemin.wien.builder.RssImageBuilder
import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.model.Podcast
import java.time.temporal.TemporalAccessor

internal interface PodcastBuilder : Builder<Podcast> {

    /** The builder for data from the iTunes namespace. */
    val iTunes: PodcastITunesBuilder

    /** The builder for data from the Atom namespace. */
    val atom: PodcastAtomBuilder

    /** The builder for data from the Fyyd namespace. */
    val fyyd: PodcastFyydBuilder

    /** The builder for data from the Feedpress namespace. */
    val feedpress: PodcastFeedpressBuilder

    /** The builder for data from the Google Play namespace. */
    val googlePlay: PodcastGooglePlayBuilder

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
    fun createITunesCategoryBuilder(): ITunesStyleCategoryBuilder
}
