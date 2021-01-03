package io.hemin.wien.builder.fake.podcast

import io.hemin.wien.builder.ImageBuilder
import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.builder.fake.FakeImageBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.model.Episode
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
    var imageBuilder: ImageBuilder? = null

    val episodes: MutableList<Episode> = mutableListOf()

    override val iTunes: FakePodcastITunesBuilder = FakePodcastITunesBuilder()

    override val atom: FakePodcastAtomBuilder = FakePodcastAtomBuilder()

    override val fyyd: FakePodcastFyydBuilder = FakePodcastFyydBuilder()

    override val feedpress: FakePodcastFeedpressBuilder = FakePodcastFeedpressBuilder()

    override val googlePlay: FakePodcastGooglePlayBuilder = FakePodcastGooglePlayBuilder()

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

    override fun addEpisode(episode: Episode): PodcastBuilder = apply {
        episodes.add(episode)
    }

    override fun createImageBuilder(): ImageBuilder = FakeImageBuilder()
}
