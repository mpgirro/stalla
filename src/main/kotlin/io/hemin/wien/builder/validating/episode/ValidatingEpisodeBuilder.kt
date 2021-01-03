package io.hemin.wien.builder.validating.episode

import io.hemin.wien.builder.episode.EpisodeAtomBuilder
import io.hemin.wien.builder.episode.EpisodeBitloveBuilder
import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.episode.EpisodeContentBuilder
import io.hemin.wien.builder.episode.EpisodeEnclosureBuilder
import io.hemin.wien.builder.episode.EpisodeGooglePlayBuilder
import io.hemin.wien.builder.episode.EpisodeITunesBuilder
import io.hemin.wien.builder.episode.EpisodePodloveBuilder
import io.hemin.wien.model.Episode
import java.util.Date

/** Builder class for [Episode] instances. */
internal class ValidatingEpisodeBuilder : EpisodeBuilder {

    private lateinit var titleValue: String
    private lateinit var enclosureValue: EpisodeEnclosureBuilder

    private var link: String? = null
    private var description: String? = null
    private var author: String? = null
    private val categories: MutableList<String> = mutableListOf()
    private var comments: String? = null
    private var guid: Episode.Guid? = null
    private var pubDate: Date? = null
    private var source: String? = null

    /** The builder for data from the Content namespace. */
    override val content: EpisodeContentBuilder = ValidatingEpisodeContentBuilder()

    /** The builder for data from the iTunes namespace. */
    override val iTunes: EpisodeITunesBuilder = ValidatingEpisodeITunesBuilder()

    /** The builder for data from the Atom namespace. */
    override val atom: EpisodeAtomBuilder = ValidatingEpisodeAtomBuilder()

    /** The builder for data from namespaces of the Podlove standards. */
    override val podlove: EpisodePodloveBuilder = ValidatingEpisodePodloveBuilder()

    /** The builder for data from the Google Play namespace. */
    override val googlePlay: EpisodeGooglePlayBuilder = ValidatingEpisodeGooglePlayBuilder()

    /** The builder for data from the Bitlove namespace. */
    override val bitlove: EpisodeBitloveBuilder = ValidatingEpisodeBitloveBuilder()

    /** Set the title value. */
    override fun title(title: String): EpisodeBuilder = apply { this.titleValue = title }

    /** Set the link value. */
    override fun link(link: String?): EpisodeBuilder = apply { this.link = link }

    /** Set the description value. */
    override fun description(description: String?): EpisodeBuilder = apply { this.description = description }

    /** Set the author value. */
    override fun author(author: String?): EpisodeBuilder = apply { this.author = author }

    /**
     * Adds a category to the list of categories.
     *
     * @param category The comment to add.
     */
    override fun addCategory(category: String): EpisodeBuilder = apply {
        categories.add(category)
    }

    /** Set the comments value. */
    override fun comments(comments: String?): EpisodeBuilder = apply { this.comments = comments }

    override fun enclosure(enclosureBuilder: EpisodeEnclosureBuilder): EpisodeBuilder = apply { this.enclosureValue = enclosureBuilder }

    /** Set the Guid. */
    override fun guid(guid: Episode.Guid?): EpisodeBuilder = apply { this.guid = guid }

    /** Set the pubDate value. */
    override fun pubDate(pubDate: Date?): EpisodeBuilder = apply { this.pubDate = pubDate }

    /** Set the source value. */
    override fun source(source: String?): EpisodeBuilder = apply { this.source = source }

    override fun createEnclosureBuilder(): EpisodeEnclosureBuilder = ValidatingEpisodeEnclosureBuilder()

    override fun build(): Episode? {
        val enclosure = enclosureValue.build()
        if (enclosure == null || !::titleValue.isInitialized || !::enclosureValue.isInitialized) {
            return null
        }

        return Episode(
            title = titleValue,
            link = link,
            description = description,
            author = author,
            categories = immutableCopyOf(categories),
            comments = comments,
            enclosure = enclosure,
            guid = guid,
            pubDate = pubDate,
            source = source,
            content = content.build(),
            itunes = iTunes.build(),
            atom = atom.build(),
            podlove = podlove.build(),
            googlePlay = googlePlay.build(),
            bitlove = bitlove.build()
        )
    }
}
