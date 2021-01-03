package io.hemin.wien.builder.validating.podcast

import io.hemin.wien.builder.podcast.PodcastAtomBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.builder.podcast.PodcastFeedpressBuilder
import io.hemin.wien.builder.podcast.PodcastFyydBuilder
import io.hemin.wien.builder.podcast.PodcastGooglePlayBuilder
import io.hemin.wien.builder.podcast.PodcastITunesBuilder
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Image
import io.hemin.wien.model.Podcast
import java.util.Date

/** Builder class for [Podcast] instances. */
internal class ValidatingPodcastBuilder : PodcastBuilder {

    private lateinit var titleValue: String
    private lateinit var linkValue: String
    private lateinit var descriptionValue: String
    private lateinit var languageValue: String

    private var pubDate: Date? = null
    private var lastBuildDate: Date? = null
    private var generator: String? = null
    private var copyright: String? = null
    private var docs: String? = null
    private var managingEditor: String? = null
    private var webMaster: String? = null
    private var image: Image? = null

    private val episodes: MutableList<Episode> = mutableListOf()

    /** The builder for data from the iTunes namespace. */
    override val iTunes: PodcastITunesBuilder = ValidatingPodcastITunesBuilder()

    /** The builder for data from the Atom namespace. */
    override val atom: PodcastAtomBuilder = ValidatingPodcastAtomBuilder()

    /** The builder for data from the Fyyd namespace. */
    override val fyyd: PodcastFyydBuilder = ValidatingPodcastFyydBuilder()

    /** The builder for data from the Feedpress namespace. */
    override val feedpress: PodcastFeedpressBuilder = ValidatingPodcastFeedpressBuilder()

    /** The builder for data from the Google Play namespace. */
    override val googlePlay: PodcastGooglePlayBuilder = ValidatingPodcastGooglePlayBuilder()

    /** Set the title value. */
    override fun title(title: String): PodcastBuilder = apply { this.titleValue = title }

    /** Set the link value. */
    override fun link(link: String): PodcastBuilder = apply { this.linkValue = link }

    /** Set the description value. */
    override fun description(description: String): PodcastBuilder = apply { this.descriptionValue = description }

    /** Set the pubDate value. */
    override fun pubDate(pubDate: Date?): PodcastBuilder = apply { this.pubDate = pubDate }

    /** Set the lastBuildDate value. */
    override fun lastBuildDate(lastBuildDate: Date?): PodcastBuilder = apply { this.lastBuildDate = lastBuildDate }

    /** Set the language value. */
    override fun language(language: String): PodcastBuilder = apply { this.languageValue = language }

    /** Set the generator value. */
    override fun generator(generator: String?): PodcastBuilder = apply { this.generator = generator }

    /** Set the copyright value. */
    override fun copyright(copyright: String?): PodcastBuilder = apply { this.copyright = copyright }

    /** Set the docs value. */
    override fun docs(docs: String?): PodcastBuilder = apply { this.docs = docs }

    /** Set the managingEditor value. */
    override fun managingEditor(managingEditor: String?): PodcastBuilder = apply { this.managingEditor = managingEditor }

    /** Set the webMaster value. */
    override fun webMaster(webMaster: String?): PodcastBuilder = apply { this.webMaster = webMaster }

    /** Set the Image. */
    override fun image(image: Image?): PodcastBuilder = apply { this.image = image }

    /**
     * Adds an [Episode] to the list of episodes.
     *
     * @param episode The [Episode] to add.
     */
    override fun addEpisode(episode: Episode): PodcastBuilder = apply {
        episodes.add(episode)
    }

    /**
     * Creates an instance of [Podcast] with the properties set in this builder.
     *
     * @return The create instance.
     */
    override fun build(): Podcast? {
        if (
            episodes.isEmpty() || !::titleValue.isInitialized || !::descriptionValue.isInitialized ||
            !::linkValue.isInitialized || !::languageValue.isInitialized
        ) {
            return null
        }

        return Podcast(
            title = titleValue,
            link = linkValue,
            description = descriptionValue,
            pubDate = pubDate,
            lastBuildDate = lastBuildDate,
            language = languageValue,
            generator = generator,
            copyright = copyright,
            docs = docs,
            managingEditor = managingEditor,
            webMaster = webMaster,
            image = image,
            episodes = immutableCopyOf(episodes),
            iTunes = iTunes.build(),
            atom = atom.build(),
            fyyd = fyyd.build(),
            feedpress = feedpress.build(),
            googlePlay = googlePlay.build()
        )
    }
}
