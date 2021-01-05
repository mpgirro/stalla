package io.hemin.wien.builder.validating.podcast

import assertk.all
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import io.hemin.wien.builder.validating.ValidatingHrefOnlyImageBuilder
import io.hemin.wien.builder.validating.ValidatingRssImageBuilder
import io.hemin.wien.builder.validating.ValidatingPersonBuilder
import io.hemin.wien.model.Podcast
import org.junit.jupiter.api.Test

internal class ValidatingPodcastITunesBuilderTest {

    private val expectedImageBuilder = ValidatingHrefOnlyImageBuilder().href("image href")

    private val expectedPersonBuilder = ValidatingPersonBuilder().name("name")

    @Test
    internal fun `should not build a Podcast ITunes when all fields are missing`() {
        val podcastBuilder = ValidatingPodcastITunesBuilder()

        assertThat(podcastBuilder.build()).isNull()
    }

    @Test
    internal fun `should build a valid Podcast ITunes when there are all the mandatory fields`() {
        val podcastBuilder = ValidatingPodcastITunesBuilder()
            .imageBuilder(expectedImageBuilder)
            .explicit(false)
            .addCategory("category")

        assertThat(podcastBuilder.build()).isNotNull().all {
            prop(Podcast.ITunes::explicit).isNotNull().isFalse()
            prop(Podcast.ITunes::subtitle).isNull()
            prop(Podcast.ITunes::summary).isNull()
            prop(Podcast.ITunes::keywords).isNull()
            prop(Podcast.ITunes::author).isNull()
            prop(Podcast.ITunes::categories).containsExactly("category")
            prop(Podcast.ITunes::block).isNull()
            prop(Podcast.ITunes::complete).isNull()
            prop(Podcast.ITunes::type).isNull()
            prop(Podcast.ITunes::owner).isNull()
            prop(Podcast.ITunes::title).isNull()
            prop(Podcast.ITunes::newFeedUrl).isNull()
            prop(Podcast.ITunes::image).isEqualTo(expectedImageBuilder.build())
        }
    }

    @Test
    internal fun `should build a valid Podcast ITunes when there are all fields`() {
        val podcastBuilder = ValidatingPodcastITunesBuilder()
            .explicit(true)
            .subtitle("subtitle")
            .summary("summary")
            .keywords("keywords")
            .author("author")
            .addCategory("category 1")
            .addCategory("category 2")
            .block(false)
            .complete(false)
            .type(Podcast.ITunes.ShowType.SERIAL.type)
            .ownerBuilder(expectedPersonBuilder)
            .title("title")
            .newFeedUrl("newFeedUrl")
            .imageBuilder(expectedImageBuilder)

        assertThat(podcastBuilder.build()).isNotNull().all {
            prop(Podcast.ITunes::explicit).isNotNull().isTrue()
            prop(Podcast.ITunes::subtitle).isEqualTo("subtitle")
            prop(Podcast.ITunes::summary).isEqualTo("summary")
            prop(Podcast.ITunes::keywords).isEqualTo("keywords")
            prop(Podcast.ITunes::author).isEqualTo("author")
            prop(Podcast.ITunes::categories).containsExactly("category 1", "category 2")
            prop(Podcast.ITunes::block).isNotNull().isFalse()
            prop(Podcast.ITunes::complete).isNotNull().isFalse()
            prop(Podcast.ITunes::type).isEqualTo(Podcast.ITunes.ShowType.SERIAL)
            prop(Podcast.ITunes::owner).isEqualTo(expectedPersonBuilder.build())
            prop(Podcast.ITunes::title).isEqualTo("title")
            prop(Podcast.ITunes::newFeedUrl).isEqualTo("newFeedUrl")
            prop(Podcast.ITunes::image).isEqualTo(expectedImageBuilder.build())
        }
    }
}
