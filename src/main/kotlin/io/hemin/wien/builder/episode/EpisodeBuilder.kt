package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.builder.ImageBuilder
import io.hemin.wien.builder.LinkBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.model.Episode
import java.time.temporal.TemporalAccessor

internal interface EpisodeBuilder : Builder<Episode> {

    /** The builder for data from the Content namespace. */
    val content: EpisodeContentBuilder

    /** The builder for data from the iTunes namespace. */
    val iTunes: EpisodeITunesBuilder

    /** The builder for data from the Atom namespace. */
    val atom: EpisodeAtomBuilder

    /** The builder for data from namespaces of the Podlove standards. */
    val podlove: EpisodePodloveBuilder

    /** The builder for data from the Google Play namespace. */
    val googlePlay: EpisodeGooglePlayBuilder

    /** The builder for data from the Bitlove namespace. */
    val bitlove: EpisodeBitloveBuilder

    /** Set the title value. */
    fun title(title: String): EpisodeBuilder

    /** Set the link value. */
    fun link(link: String?): EpisodeBuilder

    /** Set the description value. */
    fun description(description: String?): EpisodeBuilder

    /** Set the author value. */
    fun author(author: String?): EpisodeBuilder

    /**
     * Adds a category to the list of categories.
     *
     * @param category The comment to add.
     */
    fun addCategory(category: String): EpisodeBuilder

    /** Set the comments value. */
    fun comments(comments: String?): EpisodeBuilder

    /**
     * Set the [EpisodeEnclosureBuilder].
     *
     * @param enclosureBuilder The [EpisodeEnclosureBuilder] used to initialize the
     * [Episode.enclosure] when [build] is called.
     */
    fun enclosureBuilder(enclosureBuilder: EpisodeEnclosureBuilder): EpisodeBuilder

    /**
     * Set the [EpisodeGuidBuilder].
     *
     * @param guidBuilder The [EpisodeGuidBuilder] used to initialize the
     * [Episode.guid] when [build] is called.
     */
    fun guidBuilder(guidBuilder: EpisodeGuidBuilder?): EpisodeBuilder

    /** Set the pubDate value. */
    fun pubDate(pubDate: TemporalAccessor?): EpisodeBuilder

    /** Set the source value. */
    fun source(source: String?): EpisodeBuilder

    /** Creates an instance of [EpisodeEnclosureBuilder] to use with this builder. */
    fun createEnclosureBuilder(): EpisodeEnclosureBuilder

    /** Creates an instance of [EpisodeGuidBuilder] to use with this builder. */
    fun createGuidBuilder(): EpisodeGuidBuilder

    /** Creates an instance of [LinkBuilder] to use with this builder. */
    fun createLinkBuilder(): LinkBuilder

    /** Creates an instance of [PersonBuilder] to use with this builder. */
    fun createPersonBuilder(): PersonBuilder

    /** Creates an instance of [ImageBuilder] to use with this builder. */
    fun createImageBuilder(): ImageBuilder

    /** Creates an instance of [EpisodePodloveSimpleChapterBuilder] to use with this builder. */
    fun createPodloveSimpleChapterBuilder(): EpisodePodloveSimpleChapterBuilder
}