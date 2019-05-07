package io.hemin.wien.builder

import com.google.common.collect.ImmutableList
import io.hemin.wien.model.Episode
import java.util.*

/** Builder class for [Episode] instances. */
class EpisodeBuilder : Builder<Episode> {

    private var title: String?                  = null
    private var link: String?                   = null
    private var description: String?            = null
    private var author: String?                 = null
    private val categories: MutableList<String> = mutableListOf()
    private var comments: String?               = null
    private var enclosure: Episode.Enclosure?   = null
    private var guid: Episode.Guid?             = null
    private var pubDate: Date?                  = null
    private var source: String?                 = null

    /** The builder for data from the Content namespace. */
    val content: EpisodeContentBuilder = EpisodeContentBuilder()

    /** The builder for data from the iTunes namespace. */
    val itunes: EpisodeItunesBuilder = EpisodeItunesBuilder()

    /** The builder for data from the Atom namespace. */
    val atom: EpisodeAtomBuilder = EpisodeAtomBuilder()

    /** Set the title. */
    fun title(title: String?) = apply { this.title = title }

    /** Set the link. */
    fun link(link: String?) = apply { this.link = link }

    /** Set the description. */
    fun description(description: String?) = apply { this.description = description }

    /** Set the author. */
    fun author(author: String?) = apply { this.author = author }

    /**
     * Adds a comment to the list of comments.
     *
     * @param comment The comment to add.
     */
    fun addCategory(category: String?) = apply {
        category?.let { this.categories.add(it) }
    }

    /** Set the comments. */
    fun comments(comments: String?) = apply { this.comments = comments }

    /**
     * Set the Enclosure.
     *
     * @param enclosure The data of an `<enclosure>` element held in a [Episode.Enclosure].
     */
    fun enclosure(enclosure: Episode.Enclosure?) = apply { this.enclosure = enclosure }

    /** Set the Guid. */
    fun guid(guid: Episode.Guid?) = apply { this.guid = guid }

    /** Set the pubDate. */
    fun pubDate(pubDate: Date?) = apply { this.pubDate = pubDate }

    /** Set the source. */
    fun source(source: String?) = apply { this.source = source }

    /**
     * Creates an instance of [Episode] with the properties set in this builder.
     *
     * @return The create instance.
     */
    override fun build() = Episode(
        title       = title,
        link        = link,
        description = description,
        author      = author,
        categories  = immutableCopyOf(categories),
        comments    = comments,
        enclosure   = enclosure,
        guid        = guid,
        pubDate     = pubDate,
        source      = source,
        content     = content.build(),
        itunes      = itunes.build(),
        atom        = atom.build()
    )

}
