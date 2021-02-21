package dev.stalla.builder.episode

import dev.stalla.builder.Builder
import dev.stalla.model.podlove.SimpleChapter
import dev.stalla.util.whenNotNull

/** Builder for constructing [SimpleChapter] instances. */
public interface EpisodePodloveSimpleChapterBuilder : Builder<SimpleChapter> {

    /** Set the start value. */
    public fun start(start: String): EpisodePodloveSimpleChapterBuilder

    /** Set the title value. */
    public fun title(title: String): EpisodePodloveSimpleChapterBuilder

    /** Set the href value. */
    public fun href(href: String?): EpisodePodloveSimpleChapterBuilder

    /** Set the image value. */
    public fun image(image: String?): EpisodePodloveSimpleChapterBuilder

    override fun applyFrom(prototype: SimpleChapter?): EpisodePodloveSimpleChapterBuilder {
        return whenNotNull(prototype) { simpleChapter ->
            start(simpleChapter.start)
            title(simpleChapter.title)
            href(simpleChapter.href)
            image(simpleChapter.image)
        }
    }
}
