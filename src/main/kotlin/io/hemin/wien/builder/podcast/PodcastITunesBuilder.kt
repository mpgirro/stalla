package io.hemin.wien.builder.podcast

import io.hemin.wien.builder.Builder
import io.hemin.wien.builder.HrefOnlyImageBuilder
import io.hemin.wien.builder.ITunesStyleCategoryBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.model.Podcast

/** Builder for constructing [Podcast.ITunes] instances. */
interface PodcastITunesBuilder : Builder<Podcast.ITunes> {

    /** Set the subtitle value. */
    fun subtitle(subtitle: String?): PodcastITunesBuilder

    /** Set the summary value. */
    fun summary(summary: String?): PodcastITunesBuilder

    /** Set the ImageBuilder. */
    fun imageBuilder(imageBuilder: HrefOnlyImageBuilder): PodcastITunesBuilder

    /** Set the keywords value. */
    fun keywords(keywords: String?): PodcastITunesBuilder

    /** Set the author value. */
    fun author(author: String?): PodcastITunesBuilder

    /**
     * Adds a category builder to the list of category builders.
     *
     * @param categoryBuilder The [ITunesStyleCategoryBuilder] used to initialize the
     * [Podcast.ITunes.categories] list when [build] is called.
     */
    fun addCategoryBuilder(categoryBuilder: ITunesStyleCategoryBuilder): PodcastITunesBuilder

    /** Set the explicit flag value. */
    fun explicit(explicit: Boolean): PodcastITunesBuilder

    /** Set the block flag value. */
    fun block(block: Boolean): PodcastITunesBuilder

    /** Set the complete flag value. */
    fun complete(complete: Boolean): PodcastITunesBuilder

    /** Set the type value. */
    fun type(type: String?): PodcastITunesBuilder

    /** Set the Person representing the owner. */
    fun ownerBuilder(ownerBuilder: PersonBuilder?): PodcastITunesBuilder

    /** Set the episode title. */
    fun title(title: String?): PodcastITunesBuilder

    /** Set the new URL at which this feed is located. */
    fun newFeedUrl(newFeedUrl: String?): PodcastITunesBuilder
}
