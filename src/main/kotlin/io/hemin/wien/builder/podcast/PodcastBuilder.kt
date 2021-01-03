package io.hemin.wien.builder.podcast

import io.hemin.wien.builder.Builder
import io.hemin.wien.builder.ImageBuilder
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import java.util.Date

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
    fun pubDate(pubDate: Date?): PodcastBuilder

    /** Set the lastBuildDate value. */
    fun lastBuildDate(lastBuildDate: Date?): PodcastBuilder

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
    fun imageBuilder(imageBuilder: ImageBuilder?): PodcastBuilder

    /**
     * Adds an [Episode] to the list of episodes.
     *
     * @param episode The [Episode] to add.
     */
    fun addEpisode(episode: Episode): PodcastBuilder

    /** Creates an instance of [ImageBuilder] to use with this builder. */
    fun createImageBuilder(): ImageBuilder
}
