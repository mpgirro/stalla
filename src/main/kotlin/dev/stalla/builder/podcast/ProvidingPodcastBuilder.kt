package dev.stalla.builder.podcast

import dev.stalla.builder.AtomPersonBuilderProvider
import dev.stalla.builder.GeoLocationBuilder
import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.LinkBuilderProvider
import dev.stalla.builder.PodcastindexLocationBuilder
import dev.stalla.builder.PodcastindexPersonBuilder
import dev.stalla.builder.RssCategoryBuilder
import dev.stalla.builder.RssImageBuilder

internal interface ProvidingPodcastBuilder : PodcastBuilder, AtomPersonBuilderProvider, LinkBuilderProvider {

    /** Creates an instance of [RssImageBuilder] to use with this builder. */
    fun createRssImageBuilder(): RssImageBuilder

    /** Creates an instance of [HrefOnlyImageBuilder] to use with this builder. */
    fun createHrefOnlyImageBuilder(): HrefOnlyImageBuilder

    /** Creates an instance of [RssCategoryBuilder] to use with this builder. */
    fun createRssCategoryBuilder(): RssCategoryBuilder

    /** Creates an instance of [PodcastItunesOwnerBuilder] to use with this builder. */
    fun createItunesOwnerBuilder(): PodcastItunesOwnerBuilder

    /** Creates an instance of [PodcastPodcastindexLockedBuilder] to use with this builder. */
    fun createLockedBuilder(): PodcastPodcastindexLockedBuilder

    /** Creates an instance of [PodcastPodcastindexFundingBuilder] to use with this builder. */
    fun createFundingBuilder(): PodcastPodcastindexFundingBuilder

    /** Creates an instance of [PodcastindexPersonBuilder] to use with this builder. */
    fun createPersonBuilder(): PodcastindexPersonBuilder

    /** Creates an instance of [PodcastindexLocationBuilder] to use with this builder. */
    fun createLocationBuilder(): PodcastindexLocationBuilder
}
