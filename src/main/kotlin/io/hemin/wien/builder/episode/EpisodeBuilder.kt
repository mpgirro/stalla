package io.hemin.wien.builder.episode

import io.hemin.wien.builder.Builder
import io.hemin.wien.model.Episode
import java.util.Date

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
    fun enclosure(enclosureBuilder: EpisodeEnclosureBuilder): EpisodeBuilder

    /** Set the Guid. */
    fun guid(guid: Episode.Guid?): EpisodeBuilder

    /** Set the pubDate value. */
    fun pubDate(pubDate: Date?): EpisodeBuilder

    /** Set the source value. */
    fun source(source: String?): EpisodeBuilder

    /** Creates an instance of [EpisodeEnclosureBuilder] to use with this builder. */
    fun createEnclosureBuilder(): EpisodeEnclosureBuilder
}
