package io.hemin.wien.builder.validating.episode

import io.hemin.wien.builder.HrefOnlyImageBuilder
import io.hemin.wien.builder.RssImageBuilder
import io.hemin.wien.builder.LinkBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.builder.episode.EpisodeAtomBuilder
import io.hemin.wien.builder.episode.EpisodeBitloveBuilder
import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.episode.EpisodeContentBuilder
import io.hemin.wien.builder.episode.EpisodeEnclosureBuilder
import io.hemin.wien.builder.episode.EpisodeGooglePlayBuilder
import io.hemin.wien.builder.episode.EpisodeGuidBuilder
import io.hemin.wien.builder.episode.EpisodeITunesBuilder
import io.hemin.wien.builder.episode.EpisodePodloveBuilder
import io.hemin.wien.builder.episode.EpisodePodloveSimpleChapterBuilder
import io.hemin.wien.builder.validating.ValidatingHrefOnlyImageBuilder
import io.hemin.wien.builder.validating.ValidatingRssImageBuilder
import io.hemin.wien.builder.validating.ValidatingLinkBuilder
import io.hemin.wien.builder.validating.ValidatingPersonBuilder
import io.hemin.wien.model.Episode
import java.time.temporal.TemporalAccessor

internal class ValidatingEpisodeBuilder : EpisodeBuilder {

    private lateinit var titleValue: String
    private lateinit var enclosureBuilderValue: EpisodeEnclosureBuilder

    private var link: String? = null
    private var description: String? = null
    private var author: String? = null
    private val categories: MutableList<String> = mutableListOf()
    private var comments: String? = null
    private var guidBuilder: EpisodeGuidBuilder? = null
    private var pubDate: TemporalAccessor? = null
    private var source: String? = null

    override val content: EpisodeContentBuilder = ValidatingEpisodeContentBuilder()

    override val iTunes: EpisodeITunesBuilder = ValidatingEpisodeITunesBuilder()

    override val atom: EpisodeAtomBuilder = ValidatingEpisodeAtomBuilder()

    override val podlove: EpisodePodloveBuilder = ValidatingEpisodePodloveBuilder()

    override val googlePlay: EpisodeGooglePlayBuilder = ValidatingEpisodeGooglePlayBuilder()

    override val bitlove: EpisodeBitloveBuilder = ValidatingEpisodeBitloveBuilder()

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

    override fun pubDate(pubDate: TemporalAccessor?): EpisodeBuilder = apply { this.pubDate = pubDate }

    override fun source(source: String?): EpisodeBuilder = apply { this.source = source }

    override fun createEnclosureBuilder(): EpisodeEnclosureBuilder = ValidatingEpisodeEnclosureBuilder()

    override fun createGuidBuilder(): EpisodeGuidBuilder = ValidatingEpisodeGuidBuilder()

    override fun createLinkBuilder(): LinkBuilder = ValidatingLinkBuilder()

    override fun createPersonBuilder(): PersonBuilder = ValidatingPersonBuilder()

    override fun createHrefOnlyImageBuilder(): HrefOnlyImageBuilder = ValidatingHrefOnlyImageBuilder()

    override fun createPodloveSimpleChapterBuilder(): EpisodePodloveSimpleChapterBuilder = ValidatingEpisodePodloveSimpleChapterBuilder()

    override fun build(): Episode? {
        if (!::titleValue.isInitialized || !::enclosureBuilderValue.isInitialized) {
            return null
        }
        val enclosure = enclosureBuilderValue.build() ?: return null

        return Episode(
            title = titleValue,
            link = link,
            description = description,
            author = author,
            categories = immutableCopyOf(categories),
            comments = comments,
            enclosure = enclosure,
            guid = guidBuilder?.build(),
            pubDate = pubDate,
            source = source,
            content = content.build(),
            iTunes = iTunes.build(),
            atom = atom.build(),
            podlove = podlove.build(),
            googlePlay = googlePlay.build(),
            bitlove = bitlove.build()
        )
    }
}
