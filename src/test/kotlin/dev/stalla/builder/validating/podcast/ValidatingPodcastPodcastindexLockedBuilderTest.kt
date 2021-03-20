package dev.stalla.builder.validating.podcast

import assertk.all
import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import dev.stalla.builder.podcast.PodcastPodcastindexLockedBuilder
import dev.stalla.model.aPodcastPodcastindexLocked
import dev.stalla.model.podcastindex.Locked
import org.junit.jupiter.api.Test

internal class ValidatingPodcastPodcastindexLockedBuilderTest {

    @Test
    internal fun `should not build a Podcast Podcastindex Locked with when all the fields are missing`() {
        val lockedBuilder = ValidatingPodcastPodcastindexLockedBuilder()

        assertAll {
            assertThat(lockedBuilder).prop(PodcastPodcastindexLockedBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(lockedBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build a Podcast Podcastindex Locked with when the locked field is missing`() {
        val lockedBuilder = ValidatingPodcastPodcastindexLockedBuilder()
            .owner("Send me money please, kthxbye")

        assertAll {
            assertThat(lockedBuilder).prop(PodcastPodcastindexLockedBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(lockedBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should not build a Podcast Podcastindex Locked with when the owner field is missing`() {
        val lockedBuilder = ValidatingPodcastPodcastindexLockedBuilder()
            .locked(true)

        assertAll {
            assertThat(lockedBuilder).prop(PodcastPodcastindexLockedBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(lockedBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build a Podcast Podcastindex Locked with with all the added entries to its fields`() {
        val lockedBuilder = ValidatingPodcastPodcastindexLockedBuilder()
            .locked(true)
            .owner("Send me money please, kthxbye")

        assertAll {
            assertThat(lockedBuilder).prop(PodcastPodcastindexLockedBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(lockedBuilder.build()).isNotNull().all {
                prop(Locked::locked).isTrue()
                prop(Locked::owner).isEqualTo("Send me money please, kthxbye")
            }
        }
    }

    @Test
    internal fun `should populate a Podcast Podcastindex Locked builder with all properties from an Podcast Podcastindex Locked model`() {
        val locked = aPodcastPodcastindexLocked()
        val lockedBuilder = Locked.builder().applyFrom(locked)

        assertAll {
            assertThat(lockedBuilder).prop(PodcastPodcastindexLockedBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(lockedBuilder.build()).isNotNull().isEqualTo(locked)
        }
    }
}
