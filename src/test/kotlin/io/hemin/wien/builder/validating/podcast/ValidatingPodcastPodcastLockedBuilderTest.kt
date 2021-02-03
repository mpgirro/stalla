package io.hemin.wien.builder.validating.podcast

import assertk.all
import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import io.hemin.wien.builder.podcast.PodcastPodcastLockedBuilder
import io.hemin.wien.model.Podcast
import io.hemin.wien.model.podcast.aPodcastPodcastLocked
import org.junit.jupiter.api.Test

internal class ValidatingPodcastPodcastLockedBuilderTest {

    @Test
    internal fun `should not build a Podcast Podcast Locked with when all the fields are missing`() {
        val lockedBuilder = ValidatingPodcastPodcastLockedBuilder()

        assertAll {
            assertThat(lockedBuilder).prop(PodcastPodcastLockedBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(lockedBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build a Podcast Podcast Locked with when the locked field is missing`() {
        val lockedBuilder = ValidatingPodcastPodcastLockedBuilder()
            .owner("Send me money please, kthxbye")

        assertAll {
            assertThat(lockedBuilder).prop(PodcastPodcastLockedBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(lockedBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build a Podcast Podcast Locked with when the owner field is missing`() {
        val lockedBuilder = ValidatingPodcastPodcastLockedBuilder()
            .locked(true)

        assertAll {
            assertThat(lockedBuilder).prop(PodcastPodcastLockedBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(lockedBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build a Podcast Podcast Locked with with all the added entries to its fields`() {
        val lockedBuilder = ValidatingPodcastPodcastLockedBuilder()
            .locked(true)
            .owner("Send me money please, kthxbye")

        assertAll {
            assertThat(lockedBuilder).prop(PodcastPodcastLockedBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(lockedBuilder.build()).isNotNull().all {
                prop(Podcast.Podcast.Locked::locked).isTrue()
                prop(Podcast.Podcast.Locked::owner).isEqualTo("Send me money please, kthxbye")
            }
        }
    }

    @Test
    internal fun `should populate a Podcastindex Locked builder with all properties from an Podcastindex Locked model`() {
        val locked = aPodcastPodcastLocked()
        val lockedBuilder = Podcast.Podcast.Locked.builder().from(locked)

        assertAll {
            assertThat(lockedBuilder).prop(PodcastPodcastLockedBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(lockedBuilder.build()).isNotNull().isEqualTo(locked)
        }
    }
}
