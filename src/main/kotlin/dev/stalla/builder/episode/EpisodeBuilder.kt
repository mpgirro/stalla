package dev.stalla.builder.episode

import dev.stalla.builder.AtomBuilder
import dev.stalla.builder.Builder
import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.ItunesStyleCategoryBuilder2
import dev.stalla.builder.LinkBuilderProvider
import dev.stalla.builder.PersonBuilderProvider
import dev.stalla.builder.RssCategoryBuilder
import dev.stalla.model.Episode
import dev.stalla.model.rss.Enclosure
import dev.stalla.model.rss.Guid
import dev.stalla.util.asBuilders
import dev.stalla.util.whenNotNull
import java.time.temporal.TemporalAccessor

/** Builder for constructing [Episode] instances. */
public interface EpisodeBuilder : Builder<Episode>, PersonBuilderProvider, LinkBuilderProvider {

    /** The builder for data from the Content namespace. */
    public val contentBuilder: EpisodeContentBuilder

    /** The builder for data from the iTunes namespace. */
    public val itunesBuilder: EpisodeItunesBuilder2

    /** The builder for data from the Atom namespace. */
    public val atomBuilder: AtomBuilder

    /** The builder for data from namespaces of the Podlove standards. */
    public val podloveBuilder: EpisodePodloveBuilder

    /** The builder for data from the Google Play namespace. */
    public val googleplayBuilder: EpisodeGoogleplayBuilder2

    /** The builder for data from the Bitlove namespace. */
    public val bitloveBuilder: EpisodeBitloveBuilder

    /** The builder for data from the Podcast namespace. */
    public val podcastBuilder: EpisodePodcastBuilder

    /** Set the title value. */
    public fun title(title: String): EpisodeBuilder

    /** Set the link value. */
    public fun link(link: String?): EpisodeBuilder

    /** Set the description value. */
    public fun description(description: String?): EpisodeBuilder

    /** Set the author value. */
    public fun author(author: String?): EpisodeBuilder

    /** Adds an [RssCategoryBuilder] to the list of category builders. */
    public fun addCategoryBuilder(categoryBuilder: RssCategoryBuilder): EpisodeBuilder

    /** Adds multiple [RssCategoryBuilder] to the list of category builders. */
    public fun addCategoryBuilderys(categoryBuilders: List<RssCategoryBuilder>): EpisodeBuilder = apply {
        categoryBuilders.forEach(::addCategoryBuilder)
    }

    /** Set the comments value. */
    public fun comments(comments: String?): EpisodeBuilder

    /** Set the [EpisodeEnclosureBuilder]. */
    public fun enclosureBuilder(enclosureBuilder: EpisodeEnclosureBuilder): EpisodeBuilder

    /** Set the [EpisodeGuidBuilder]. */
    public fun guidBuilder(guidBuilder: EpisodeGuidBuilder?): EpisodeBuilder

    /** Set the pubDate value. */
    public fun pubDate(pubDate: TemporalAccessor?): EpisodeBuilder

    /** Set the source value. */
    public fun source(source: String?): EpisodeBuilder

    /** Creates an instance of [EpisodeEnclosureBuilder] to use with this builder. */
    public fun createEnclosureBuilder(): EpisodeEnclosureBuilder

    /** Creates an instance of [EpisodeGuidBuilder] to use with this builder. */
    public fun createGuidBuilder(): EpisodeGuidBuilder

    /** Creates an instance of [HrefOnlyImageBuilder] to use with this builder. */
    public fun createHrefOnlyImageBuilder(): HrefOnlyImageBuilder

    /** Creates an instance of [EpisodePodloveSimpleChapterBuilder] to use with this builder. */
    public fun createPodloveSimpleChapterBuilder(): EpisodePodloveSimpleChapterBuilder

    /** Creates an instance of [RssCategoryBuilder] to use with this builder. */
    public fun createRssCategoryBuilder(): RssCategoryBuilder

    /** Creates an instance of [ItunesStyleCategoryBuilder2] to use with this builder. */
    public fun createITunesStyleCategoryBuilder(): ItunesStyleCategoryBuilder2

    /** Creates an instance of [EpisodePodcastTranscriptBuilder] to use with this builder. */
    public fun createEpisodePodcastTranscriptBuilder(): EpisodePodcastTranscriptBuilder

    /** Creates an instance of [EpisodePodcastChaptersBuilder] to use with this builder. */
    public fun createEpisodePodcastChaptersBuilder(): EpisodePodcastChaptersBuilder

    /** Creates an instance of [EpisodePodcastSoundbiteBuilder] to use with this builder. */
    public fun createEpisodePodcastSoundbiteBuilder(): EpisodePodcastSoundbiteBuilder

    override fun from(model: Episode?): EpisodeBuilder = whenNotNull(model) { episode ->
        contentBuilder.from(episode.content)
        itunesBuilder.from(episode.itunes)
        atomBuilder.from(episode.atom)
        podloveBuilder.from(episode.podlove)
        googleplayBuilder.from(episode.googleplay)
        bitloveBuilder.from(episode.bitlove)
        podcastBuilder.from(episode.podcast)
        title(episode.title)
        link(episode.link)
        description(episode.description)
        author(episode.author)
        addCategoryBuilderys(episode.categories.asBuilders())
        comments(episode.comments)
        enclosureBuilder(Enclosure.builder().from(episode.enclosure))
        guidBuilder(Guid.builder().from(episode.guid))
        pubDate(episode.pubDate)
        source(episode.source)
    }
}
