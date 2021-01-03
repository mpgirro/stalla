package io.hemin.wien.builder.fake.episode

import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.episode.EpisodeEnclosureBuilder
import io.hemin.wien.builder.episode.EpisodeGuidBuilder
import io.hemin.wien.builder.fake.FakeBuilder
import io.hemin.wien.model.Episode
import java.util.Date

@Suppress("MemberVisibilityCanBePrivate", "Unused")
internal class FakeEpisodeBuilder : FakeBuilder<Episode>(), EpisodeBuilder {

    var titleValue: String? = null
    var enclosureBuilderValue: EpisodeEnclosureBuilder? = null

    var link: String? = null
    var description: String? = null
    var author: String? = null
    val categories: MutableList<String> = mutableListOf()
    var comments: String? = null
    var guidBuilder: EpisodeGuidBuilder? = null
    var pubDate: Date? = null
    var source: String? = null

    override val content: FakeEpisodeContentBuilder = FakeEpisodeContentBuilder()

    override val iTunes: FakeEpisodeITunesBuilder = FakeEpisodeITunesBuilder()

    override val atom: FakeEpisodeAtomBuilder = FakeEpisodeAtomBuilder()

    override val podlove: FakeEpisodePodloveBuilder = FakeEpisodePodloveBuilder()

    override val googlePlay: FakeEpisodeGooglePlayBuilder = FakeEpisodeGooglePlayBuilder()

    override val bitlove: FakeEpisodeBitloveBuilder = FakeEpisodeBitloveBuilder()

    override fun title(title: String): EpisodeBuilder = apply { this.titleValue = title }

    override fun link(link: String?): EpisodeBuilder = apply { this.link = link }

    override fun description(description: String?): EpisodeBuilder = apply { this.description = description }

    override fun author(author: String?): EpisodeBuilder = apply { this.author = author }

    override fun addCategory(category: String): EpisodeBuilder = apply {
        categories.add(category)
    }

    override fun comments(comments: String?): EpisodeBuilder = apply { this.comments = comments }

    override fun enclosureBuilder(enclosureBuilder: EpisodeEnclosureBuilder): EpisodeBuilder = apply {
        this.enclosureBuilderValue = enclosureBuilder
    }

    override fun guidBuilder(guidBuilder: EpisodeGuidBuilder?): EpisodeBuilder = apply { this.guidBuilder = guidBuilder }

    override fun pubDate(pubDate: Date?): EpisodeBuilder = apply { this.pubDate = pubDate }

    override fun source(source: String?): EpisodeBuilder = apply { this.source = source }

    override fun createEnclosureBuilder(): EpisodeEnclosureBuilder = FakeEpisodeEnclosureBuilder()

    override fun createGuidBuilder(): EpisodeGuidBuilder = FakeEpisodeGuidBuilder()
}
