package io.hemin.wien.builder.validating.podcast

import io.hemin.wien.builder.ImageBuilder
import io.hemin.wien.builder.LinkBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.podcast.PodcastAtomBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.builder.podcast.PodcastFeedpressBuilder
import io.hemin.wien.builder.podcast.PodcastFyydBuilder
import io.hemin.wien.builder.podcast.PodcastGooglePlayBuilder
import io.hemin.wien.builder.podcast.PodcastITunesBuilder
import io.hemin.wien.builder.validating.ValidatingImageBuilder
import io.hemin.wien.builder.validating.ValidatingLinkBuilder
import io.hemin.wien.builder.validating.ValidatingPersonBuilder
import io.hemin.wien.model.Podcast
import java.util.Date

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
    private var imageBuilder: ImageBuilder? = null

    private val episodeBuilders: MutableList<EpisodeBuilder> = mutableListOf()

    override val iTunes: PodcastITunesBuilder = ValidatingPodcastITunesBuilder()

    override val atom: PodcastAtomBuilder = ValidatingPodcastAtomBuilder()

    override val fyyd: PodcastFyydBuilder = ValidatingPodcastFyydBuilder()

    override val feedpress: PodcastFeedpressBuilder = ValidatingPodcastFeedpressBuilder()

    override val googlePlay: PodcastGooglePlayBuilder = ValidatingPodcastGooglePlayBuilder()

    override fun title(title: String): PodcastBuilder = apply { this.titleValue = title }

    override fun link(link: String): PodcastBuilder = apply { this.linkValue = link }

    override fun description(description: String): PodcastBuilder = apply { this.descriptionValue = description }

    override fun pubDate(pubDate: Date?): PodcastBuilder = apply { this.pubDate = pubDate }

    override fun lastBuildDate(lastBuildDate: Date?): PodcastBuilder = apply { this.lastBuildDate = lastBuildDate }

    override fun language(language: String): PodcastBuilder = apply { this.languageValue = language }

    override fun generator(generator: String?): PodcastBuilder = apply { this.generator = generator }

    override fun copyright(copyright: String?): PodcastBuilder = apply { this.copyright = copyright }

    override fun docs(docs: String?): PodcastBuilder = apply { this.docs = docs }

    override fun managingEditor(managingEditor: String?): PodcastBuilder = apply { this.managingEditor = managingEditor }

    override fun webMaster(webMaster: String?): PodcastBuilder = apply { this.webMaster = webMaster }

    override fun imageBuilder(imageBuilder: ImageBuilder?): PodcastBuilder = apply { this.imageBuilder = imageBuilder }

    override fun addEpisodeBuilder(episodeBuilder: EpisodeBuilder): PodcastBuilder = apply {
        episodeBuilders.add(episodeBuilder)
    }

    override fun createImageBuilder(): ImageBuilder = ValidatingImageBuilder()

    override fun createLinkBuilder(): LinkBuilder = ValidatingLinkBuilder()

    override fun createPersonBuilder(): PersonBuilder = ValidatingPersonBuilder()

    override fun build(): Podcast? {
        val builtEpisodes = episodeBuilders.mapNotNull { it.build() }
        if (
            builtEpisodes.isEmpty() ||
            !::titleValue.isInitialized || !::descriptionValue.isInitialized ||
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
            image = imageBuilder?.build(),
            episodes = builtEpisodes,
            iTunes = iTunes.build(),
            atom = atom.build(),
            fyyd = fyyd.build(),
            feedpress = feedpress.build(),
            googlePlay = googlePlay.build()
        )
    }
}
