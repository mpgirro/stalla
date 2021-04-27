package dev.stalla.builder.episode

import dev.stalla.builder.AtomPersonBuilderProvider
import dev.stalla.builder.HrefOnlyImageBuilder
import dev.stalla.builder.LinkBuilderProvider
import dev.stalla.builder.PodcastindexLocationBuilder
import dev.stalla.builder.PodcastindexPersonBuilder
import dev.stalla.builder.RssCategoryBuilder
import dev.stalla.util.InternalAPI

@InternalAPI
internal interface ProvidingEpisodeBuilder : EpisodeBuilder, AtomPersonBuilderProvider, LinkBuilderProvider {

    /** Creates an instance of [EpisodeEnclosureBuilder] to use with this builder. */
    fun createEnclosureBuilder(): EpisodeEnclosureBuilder

    /** Creates an instance of [EpisodeGuidBuilder] to use with this builder. */
    fun createGuidBuilder(): EpisodeGuidBuilder

    /** Creates an instance of [HrefOnlyImageBuilder] to use with this builder. */
    fun createHrefOnlyImageBuilder(): HrefOnlyImageBuilder

    /** Creates an instance of [EpisodePodloveSimpleChapterBuilder] to use with this builder. */
    fun createSimpleChapterBuilder(): EpisodePodloveSimpleChapterBuilder

    /** Creates an instance of [RssCategoryBuilder] to use with this builder. */
    fun createRssCategoryBuilder(): RssCategoryBuilder

    /** Creates an instance of [EpisodePodcastindexTranscriptBuilder] to use with this builder. */
    fun createTranscriptBuilder(): EpisodePodcastindexTranscriptBuilder

    /** Creates an instance of [EpisodePodcastindexChaptersBuilder] to use with this builder. */
    fun createChaptersBuilder(): EpisodePodcastindexChaptersBuilder

    /** Creates an instance of [EpisodePodcastindexSoundbiteBuilder] to use with this builder. */
    fun createSoundbiteBuilder(): EpisodePodcastindexSoundbiteBuilder

    /** Creates an instance of [PodcastindexPersonBuilder] to use with this builder. */
    fun createPersonBuilder(): PodcastindexPersonBuilder

    /** Creates an instance of [PodcastindexLocationBuilder] to use with this builder. */
    fun createLocationBuilder(): PodcastindexLocationBuilder

    /** Creates an instance of [EpisodePodcastindexSeasonBuilder] to use with this builder. */
    fun createSeasonBuilder(): EpisodePodcastindexSeasonBuilder

    /** Creates an instance of [EpisodePodcastindexEpisodeBuilder] to use with this builder. */
    fun createEpisodeBuilder(): EpisodePodcastindexEpisodeBuilder
}
