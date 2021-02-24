@file:Suppress("TooManyFunctions")

package dev.stalla.builder.episode

import dev.stalla.builder.AtomBuilder
import dev.stalla.builder.Builder
import dev.stalla.builder.RssCategoryBuilder
import dev.stalla.model.Episode
import dev.stalla.model.rss.Enclosure
import dev.stalla.model.rss.Guid
import dev.stalla.util.asBuilders
import dev.stalla.util.whenNotNull
import java.time.temporal.TemporalAccessor

/** Builder for constructing [Episode] instances. */
public interface EpisodeBuilder : Builder<Episode> {

    /** The builder for data from the Content namespace. */
    public val contentBuilder: EpisodeContentBuilder

    /** The builder for data from the iTunes namespace. */
    public val itunesBuilder: EpisodeItunesBuilder

    /** The builder for data from the Atom namespace. */
    public val atomBuilder: AtomBuilder

    /** The builder for data from namespaces of the Podlove standards. */
    public val podloveBuilder: EpisodePodloveBuilder

    /** The builder for data from the Google Play namespace. */
    public val googleplayBuilder: EpisodeGoogleplayBuilder

    /** The builder for data from the Bitlove namespace. */
    public val bitloveBuilder: EpisodeBitloveBuilder

    /** The builder for data from the Podcast namespace. */
    public val podcastindexBuilder: EpisodePodcastindexBuilder

    /** Set the title value. */
    public fun title(title: String): EpisodeBuilder

    /** Set the link value. */
    public fun link(link: String?): EpisodeBuilder

    /** Set the description value. */
    public fun description(description: String?): EpisodeBuilder

    /** Set the author value. */
    public fun author(author: String?): EpisodeBuilder

    /** Adds the [RssCategoryBuilder] to the list of category builders. */
    public fun addCategoryBuilder(categoryBuilder: RssCategoryBuilder): EpisodeBuilder

    /** Adds all of the [RssCategoryBuilder] to the list of category builders. */
    public fun addAllCategoryBuilder(categoryBuilders: List<RssCategoryBuilder>): EpisodeBuilder =
        apply { categoryBuilders.forEach(::addCategoryBuilder) }

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

    override fun applyFrom(prototype: Episode?): EpisodeBuilder = whenNotNull(prototype) { episode ->
        contentBuilder.applyFrom(episode.content)
        itunesBuilder.applyFrom(episode.itunes)
        atomBuilder.applyFrom(episode.atom)
        podloveBuilder.applyFrom(episode.podlove)
        googleplayBuilder.applyFrom(episode.googleplay)
        bitloveBuilder.applyFrom(episode.bitlove)
        podcastindexBuilder.applyFrom(episode.podcastindex)
        title(episode.title)
        link(episode.link)
        description(episode.description)
        author(episode.author)
        addAllCategoryBuilder(episode.categories.asBuilders())
        comments(episode.comments)
        enclosureBuilder(Enclosure.builder().applyFrom(episode.enclosure))
        guidBuilder(Guid.builder().applyFrom(episode.guid))
        pubDate(episode.pubDate)
        source(episode.source)
    }
}
