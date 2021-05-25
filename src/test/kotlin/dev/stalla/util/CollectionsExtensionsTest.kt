package dev.stalla.util

import assertk.assertThat
import assertk.assertions.containsExactly
import dev.stalla.model.Episode
import dev.stalla.model.MediaType
import dev.stalla.model.StyledDuration
import dev.stalla.model.bitlove.Bitlove
import dev.stalla.model.content.Content
import dev.stalla.model.podcastindex.Funding
import dev.stalla.model.podcastindex.Soundbite
import dev.stalla.model.podcastindex.Transcript
import dev.stalla.model.podcastindex.TranscriptType
import dev.stalla.model.podlove.SimpleChapter
import dev.stalla.model.rss.Enclosure
import dev.stalla.model.rss.Guid
import dev.stalla.model.rss.RssCategory
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.Locale

internal class CollectionsExtensionsTest {

    @Nested
    inner class RssCategories {

        @Test
        internal fun `should transform all RssCategories to builders`() {
            val rssCategories = listOf(
                RssCategory("category 1", domain = null),
                RssCategory("category 2", domain = "banana")
            )

            assertThat(rssCategories.asBuilders()).containsExactly(
                RssCategory.builder().category("category 1"),
                RssCategory.builder().category("category 2").domain("banana")
            )
        }
    }

    @Nested
    inner class Enclosures {

        @Test
        internal fun `should transform all Enclosure to builders`() {
            val enclosures = listOf(
                Enclosure("url 1", 123L, MediaType.ANY_AUDIO),
                Enclosure("url 2", 456L, MediaType.MPEG_AUDIO)
            )

            assertThat(enclosures.asBuilders()).containsExactly(
                Enclosure.builder().url("url 1").length(123L).type(MediaType.ANY_AUDIO),
                Enclosure.builder().url("url 2").length(456L).type(MediaType.MPEG_AUDIO)
            )
        }
    }

    @Nested
    inner class SoundBites {

        @Test
        internal fun `should transform all SoundBite to builders`() {
            val twoSeconds = StyledDuration.Factory.secondsAndFraction(seconds = 2)
            val threeSeconds = StyledDuration.Factory.secondsAndFraction(seconds = 3)

            val soundBites = listOf(
                Soundbite(
                    startTime = twoSeconds,
                    duration = threeSeconds,
                    title = "SoundBite 1"
                ),
                Soundbite(
                    startTime = threeSeconds,
                    duration = twoSeconds,
                    title = "SoundBite 2"
                ),
            )

            assertThat(soundBites.asBuilders()).containsExactly(
                Soundbite.builder()
                    .startTime(twoSeconds)
                    .duration(threeSeconds)
                    .title("SoundBite 1"),
                Soundbite.builder()
                    .startTime(threeSeconds)
                    .duration(twoSeconds)
                    .title("SoundBite 2"),
            )
        }
    }

    @Nested
    inner class Transcripts {

        @Test
        internal fun `should transform all Transcript to builders`() {
            val transcripts = listOf(
                Transcript("url 1", TranscriptType.PLAIN_TEXT),
                Transcript("url 2", TranscriptType.JSON, Locale.ITALIAN, "banana")
            )

            assertThat(transcripts.asBuilders()).containsExactly(
                Transcript.builder().url("url 1").type(TranscriptType.PLAIN_TEXT),
                Transcript.builder().url("url 2").type(TranscriptType.JSON).language(Locale.ITALIAN).rel("banana")
            )
        }
    }

    @Nested
    inner class Fundings {

        @Test
        internal fun `should transform all Funding to builders`() {
            val transcripts = listOf(
                Funding("url 1", "message 1"),
                Funding("url 2", "banana")
            )

            assertThat(transcripts.asBuilders()).containsExactly(
                Funding.builder().url("url 1").message("message 1"),
                Funding.builder().url("url 2").message("banana")
            )
        }
    }

    @Nested
    inner class SimpleChapters {

        @Test
        internal fun `should transform all SimpleChapter to builders`() {
            val simpleChapters = listOf(
                SimpleChapter("start", "simple chapter 1"),
                SimpleChapter("start", "simple chapter 2"),
            )

            assertThat(simpleChapters.asBuilders()).containsExactly(
                SimpleChapter.builder().start("start").title("simple chapter 1"),
                SimpleChapter.builder().start("start").title("simple chapter 2"),
            )
        }
    }

    @Nested
    inner class Episodes {

        @Test
        internal fun `should transform all Episode to builders`() {
            val episodes = listOf(
                Episode(
                    title = "episode 1",
                    guid = Guid("1234"),
                    enclosure = Enclosure("url 1", 123L, MediaType.ANY_AUDIO),
                    content = Content("encoded 1"),
                    bitlove = Bitlove("123")
                ),
                Episode(
                    title = "episode 2",
                    guid = Guid("5678"),
                    enclosure = Enclosure("url 2", 123L, MediaType.ANY_AUDIO),
                    content = Content("encoded 2"),
                    bitlove = Bitlove("456")
                ),
            )

            assertThat(episodes.asBuilders()).containsExactly(
                Episode.builder()
                    .title("episode 1")
                    .guidBuilder(Guid.builder().textContent("1234"))
                    .enclosureBuilder(Enclosure.builder().url("url 1").length(123L).type(MediaType.ANY_AUDIO)).apply {
                        contentBuilder.encoded("encoded 1")
                        bitloveBuilder.guid("123")
                    },
                Episode.builder().title("episode 2")
                    .guidBuilder(Guid.builder().textContent("5678"))
                    .enclosureBuilder(Enclosure.builder().url("url 2").length(123L).type(MediaType.ANY_AUDIO)).apply {
                        contentBuilder.encoded("encoded 2")
                        bitloveBuilder.guid("456")
                    }
            )
        }
    }
}
