package dev.stalla.builder.podcast

import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.LinkBuilderProvider
import dev.stalla.builder.PersonBuilderProvider
import dev.stalla.builder.RssCategoryBuilder
import dev.stalla.builder.RssImageBuilder

internal interface ProvidingPodcastBuilder : PodcastBuilder, PersonBuilderProvider, LinkBuilderProvider {

    /** Creates an instance of [RssImageBuilder] to use with this builder. */
    fun createRssImageBuilder(): RssImageBuilder

    /** Creates an instance of [HrefOnlyImageBuilder] to use with this builder. */
    fun createHrefOnlyImageBuilder(): HrefOnlyImageBuilder

    /** Creates an instance of [RssCategoryBuilder] to use with this builder. */
    fun createRssCategoryBuilder(): RssCategoryBuilder

    /** Creates an instance of [PodcastPodcastindexLockedBuilder] to use with this builder. */
    fun createPodcastPodcastLockedBuilder(): PodcastPodcastindexLockedBuilder

    /** Creates an instance of [PodcastPodcastindexFundingBuilder] to use with this builder. */
    fun createPodcastPodcastFundingBuilder(): PodcastPodcastindexFundingBuilder
}
