package io.hemin.wien.builder

import io.hemin.wien.model.Episode
import io.hemin.wien.model.Image
import io.hemin.wien.model.Podcast
import java.util.*

/** Builder class for [Podcast] instances. */
class PodcastBuilder : Builder<Podcast> {

    private var title: String?                 = null
    private var link: String?                  = null
    private var description: String?           = null
    private var pubDate: Date?                 = null
    private var lastBuildDate: Date?           = null
    private var language: String?              = null
    private var generator: String?             = null
    private var copyright: String?             = null
    private var docs: String?                  = null
    private var managingEditor: String?        = null
    private var webMaster: String?             = null
    private var image: Image?                  = null
    private val episodes: MutableList<Episode> = mutableListOf()

    /** The builder for data from the iTunes namespace. */
    val itunes: PodcastItunesBuilder = PodcastItunesBuilder()

    /** The builder for data from the Atom namespace. */
    val atom: PodcastAtomBuilder = PodcastAtomBuilder()

    /** The builder for data from the Fyyd namespace. */
    val fyyd: PodcastFyydBuilder = PodcastFyydBuilder()

    /** The builder for data from the Feedpress namespace. */
    val feedpress: PodcastFeedpressBuilder = PodcastFeedpressBuilder()

    /** The builder for data from the Google Play namespace. */
    val googleplay: PodcastGoogleplayBuilder = PodcastGoogleplayBuilder()

    /** Set the title value. */
    fun title(title: String?) = apply { this.title = title }

    /** Set the link value. */
    fun link(link: String?) = apply { this.link = link }

    /** Set the description value. */
    fun description(description: String?) = apply { this.description = description }

    /** Set the pubDate value. */
    fun pubDate(pubDate: Date?) = apply { this.pubDate = pubDate }

    /** Set the lastBuildDate value. */
    fun lastBuildDate(lastBuildDate: Date?) = apply { this.lastBuildDate = lastBuildDate }

    /** Set the language value. */
    fun language(language: String?) = apply { this.language = language }

    /** Set the generator value. */
    fun generator(generator: String?) = apply { this.generator = generator }

    /** Set the copyright value. */
    fun copyright(copyright: String?) = apply { this.copyright = copyright }

    /** Set the docs value. */
    fun docs(docs: String?) = apply { this.docs = docs }

    /** Set the managingEditor value. */
    fun managingEditor(managingEditor: String?) = apply { this.managingEditor = managingEditor }

    /** Set the webMaster value. */
    fun webMaster(webMaster: String?) = apply { this.webMaster = webMaster }

    /** Set the Image. */
    fun image(image: Image?) = apply { this.image = image }

    /**
     * Adds an [Episode] to the list of episodes.
     *
     * @param episode The [Episode] to add.
     */
    fun addEpisode(episode: Episode?) = apply {
        episode?.let { this.episodes.add(it) }
    }

    /**
     * Creates an instance of [Podcast] with the properties set in this builder.
     *
     * @return The create instance.
     */
    override fun build() = Podcast(
        title          = title,
        link           = link,
        description    = description,
        pubDate        = pubDate,
        lastBuildDate  = lastBuildDate,
        language       = language,
        generator      = generator,
        copyright      = copyright,
        docs           = docs,
        managingEditor = managingEditor,
        webMaster      = webMaster,
        image          = image,
        episodes       = immutableCopyOf(episodes),
        itunes         = itunes.build(),
        atom           = atom.build(),
        fyyd           = fyyd.build(),
        feedpress      = feedpress.build(),
        googleplay     = googleplay.build()
    )
}
