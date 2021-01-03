package io.hemin.wien.builder.fake.podcast

import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Image
import io.hemin.wien.model.Podcast
import java.util.Date

@Suppress("MemberVisibilityCanBePrivate", "Unused")
internal class FakePodcastBuilder : FakeBuilder<Podcast>(), PodcastBuilder {

    var titleValue: String? = null
    var linkValue: String? = null
    var descriptionValue: String? = null
    var languageValue: String? = null

    var pubDate: Date? = null
    var lastBuildDate: Date? = null
    var generator: String? = null
    var copyright: String? = null
    var docs: String? = null
    var managingEditor: String? = null
    var webMaster: String? = null
    var image: Image? = null

    val episodes: MutableList<Episode> = mutableListOf()

    /** The builder for data from the iTunes namespace. */
    override val iTunes: FakePodcastITunesBuilder = FakePodcastITunesBuilder()

    /** The builder for data from the Atom namespace. */
    override val atom: FakePodcastAtomBuilder = FakePodcastAtomBuilder()

    /** The builder for data from the Fyyd namespace. */
    override val fyyd: FakePodcastFyydBuilder = FakePodcastFyydBuilder()

    /** The builder for data from the Feedpress namespace. */
    override val feedpress: FakePodcastFeedpressBuilder = FakePodcastFeedpressBuilder()

    /** The builder for data from the Google Play namespace. */
    override val googlePlay: FakePodcastGooglePlayBuilder = FakePodcastGooglePlayBuilder()

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
}
