package io.hemin.wien.builder.podcast

import io.hemin.wien.builder.Builder
import io.hemin.wien.builder.ImageBuilder
import io.hemin.wien.builder.PersonBuilder
import io.hemin.wien.model.Podcast

internal interface PodcastITunesBuilder : Builder<Podcast.ITunes> {

    /** Set the subtitle value. */
    fun subtitle(subtitle: String?): PodcastITunesBuilder

    /** Set the summary value. */
    fun summary(summary: String?): PodcastITunesBuilder

    /** Set the ImageBuilder. */
    fun imageBuilder(imageBuilder: ImageBuilder): PodcastITunesBuilder

    /** Set the keywords value. */
    fun keywords(keywords: String?): PodcastITunesBuilder

    /** Set the author value. */
    fun author(author: String?): PodcastITunesBuilder

    /**
     * Adds a category to the list of categories.
     *
     * @param category The category to add.
     */
    fun addCategory(category: String): PodcastITunesBuilder

    /** Set the explicit flag value. */
    fun explicit(explicit: Boolean): PodcastITunesBuilder

    /** Set the block flag value. */
    fun block(block: Boolean?): PodcastITunesBuilder

    /** Set the complete flag value. */
    fun complete(complete: Boolean?): PodcastITunesBuilder

    /** Set the type value. */
    fun type(type: String?): PodcastITunesBuilder

    /** Set the Person representing the owner. */
    fun ownerBuilder(ownerBuilder: PersonBuilder?): PodcastITunesBuilder

    /** Set the episode title. */
    fun title(title: String?): PodcastITunesBuilder

    /** Set the new URL at which this feed is located. */
    fun newFeedUrl(newFeedUrl: String?): PodcastITunesBuilder
}
