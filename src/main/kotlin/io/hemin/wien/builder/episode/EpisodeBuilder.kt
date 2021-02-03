package io.hemin.wien.builder.episode

import io.hemin.wien.builder.AtomBuilder
import io.hemin.wien.builder.Builder
import io.hemin.wien.builder.HrefOnlyImageBuilder
import io.hemin.wien.builder.ITunesStyleCategoryBuilder
import io.hemin.wien.builder.LinkBuilderProvider
import io.hemin.wien.builder.PersonBuilderProvider
import io.hemin.wien.builder.RssCategoryBuilder
import io.hemin.wien.model.Episode
import io.hemin.wien.model.RssCategory
import io.hemin.wien.util.whenNotNull
import java.time.temporal.TemporalAccessor

/** Builder for constructing [Episode] instances. */
interface EpisodeBuilder : Builder<Episode>, PersonBuilderProvider, LinkBuilderProvider {

    /** The builder for data from the Content namespace. */
    val contentBuilder: EpisodeContentBuilder

    /** The builder for data from the iTunes namespace. */
    val iTunesBuilder: EpisodeITunesBuilder

    /** The builder for data from the Atom namespace. */
    val atomBuilder: AtomBuilder

    /** The builder for data from namespaces of the Podlove standards. */
    val podloveBuilder: EpisodePodloveBuilder

    /** The builder for data from the Google Play namespace. */
    val googlePlayBuilder: EpisodeGooglePlayBuilder

    /** The builder for data from the Bitlove namespace. */
    val bitloveBuilder: EpisodeBitloveBuilder

    /** The builder for data from the Podcast namespace. */
    val podcastBuilder: EpisodePodcastBuilder

    /** Set the title value. */
    fun title(title: String): EpisodeBuilder

    /** Set the link value. */
    fun link(link: String?): EpisodeBuilder

    /** Set the description value. */
    fun description(description: String?): EpisodeBuilder

    /** Set the author value. */
    fun author(author: String?): EpisodeBuilder

    /** Adds an [RssCategoryBuilder] to the list of category builders. */
    fun addCategoryBuilder(categoryBuilder: RssCategoryBuilder): EpisodeBuilder

    /** Adds multiple [RssCategoryBuilder] to the list of category builders. */
    fun addCategoryBuilderys(categoryBuilders: List<RssCategoryBuilder>): EpisodeBuilder = apply {
        categoryBuilders.forEach(::addCategoryBuilder)
    }

    /** Set the comments value. */
    fun comments(comments: String?): EpisodeBuilder

    /** Set the [EpisodeEnclosureBuilder]. */
    fun enclosureBuilder(enclosureBuilder: EpisodeEnclosureBuilder): EpisodeBuilder

    /** Set the [EpisodeGuidBuilder]. */
    fun guidBuilder(guidBuilder: EpisodeGuidBuilder?): EpisodeBuilder

    /** Set the pubDate value. */
    fun pubDate(pubDate: TemporalAccessor?): EpisodeBuilder

    /** Set the source value. */
    fun source(source: String?): EpisodeBuilder

    /** Creates an instance of [EpisodeEnclosureBuilder] to use with this builder. */
    fun createEnclosureBuilder(): EpisodeEnclosureBuilder

    /** Creates an instance of [EpisodeGuidBuilder] to use with this builder. */
    fun createGuidBuilder(): EpisodeGuidBuilder

    /** Creates an instance of [HrefOnlyImageBuilder] to use with this builder. */
    fun createHrefOnlyImageBuilder(): HrefOnlyImageBuilder

    /** Creates an instance of [EpisodePodloveSimpleChapterBuilder] to use with this builder. */
    fun createPodloveSimpleChapterBuilder(): EpisodePodloveSimpleChapterBuilder

    /** Creates an instance of [RssCategoryBuilder] to use with this builder. */
    fun createRssCategoryBuilder(): RssCategoryBuilder

    /** Creates an instance of [ITunesStyleCategoryBuilder] to use with this builder. */
    fun createITunesStyleCategoryBuilder(): ITunesStyleCategoryBuilder

    /** Creates an instance of [EpisodePodcastTranscriptBuilder] to use with this builder. */
    fun createEpisodePodcastTranscriptBuilder(): EpisodePodcastTranscriptBuilder

    /** Creates an instance of [EpisodePodcastChaptersBuilder] to use with this builder. */
    fun createEpisodePodcastChaptersBuilder(): EpisodePodcastChaptersBuilder

    /** Creates an instance of [EpisodePodcastSoundbiteBuilder] to use with this builder. */
    fun createEpisodePodcastSoundbiteBuilder(): EpisodePodcastSoundbiteBuilder

    override fun from(model: Episode?): EpisodeBuilder = whenNotNull(model) { episode ->
        contentBuilder.from(episode.content)
        iTunesBuilder.from(episode.iTunes)
        atomBuilder.from(episode.atom)
        podloveBuilder.from(episode.podlove)
        googlePlayBuilder.from(episode.googlePlay)
        bitloveBuilder.from(episode.bitlove)
        podcastBuilder.from(episode.podcast)
        title(episode.title)
        link(episode.link)
        description(episode.description)
        author(episode.author)
        addCategoryBuilderys(episode.categories.map(RssCategory.builder()::from))
        comments(episode.comments)
        enclosureBuilder(Episode.Enclosure.builder().from(episode.enclosure))
        guidBuilder(Episode.Guid.builder().from(episode.guid))
        pubDate(episode.pubDate)
        source(episode.source)
    }
}
