package dev.stalla.parser.namespace

import assertk.all
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import dev.stalla.builder.fake.episode.FakeEpisodeBuilder
import dev.stalla.builder.fake.episode.FakeEpisodePodcastindexBuilder
import dev.stalla.builder.fake.episode.FakeEpisodePodcastindexChaptersBuilder
import dev.stalla.builder.fake.episode.FakeEpisodePodcastindexSoundbiteBuilder
import dev.stalla.builder.fake.episode.FakeEpisodePodcastindexTranscriptBuilder
import dev.stalla.builder.fake.podcast.FakePodcastBuilder
import dev.stalla.builder.fake.podcast.FakePodcastPodcastindexBuilder
import dev.stalla.builder.fake.podcast.FakePodcastPodcastindexFundingBuilder
import dev.stalla.builder.fake.podcast.FakePodcastPodcastindexLockedBuilder
import dev.stalla.dom.XmlRes
import dev.stalla.model.StyledDuration
import dev.stalla.model.podcastindex.TranscriptType
import dev.stalla.parser.NamespaceParserTest
import org.junit.jupiter.api.Test
import org.w3c.dom.Node
import java.util.Locale

internal class PodcastindexParserTest : NamespaceParserTest() {

    override val parser = PodcastindexParser

    private val expectedLockedBuilder = FakePodcastPodcastindexLockedBuilder()
        .locked(true)
        .owner("podcastowner@example.com")

    private val expectedFundingBuilder = FakePodcastPodcastindexFundingBuilder()
        .url("https://example.com/donate")
        .message("Support the show!")

    private val expectedChaptersBuilder = FakeEpisodePodcastindexChaptersBuilder()
        .url("https://example.com/ep3_chapters.json")
        .type("application/json")

    private val expectedSoundbiteBuilder = FakeEpisodePodcastindexSoundbiteBuilder()
        .startTime(StyledDuration.secondsAndFraction(0, 33833))
        .duration(StyledDuration.secondsAndFraction(60))
        .title("I'm a soundbite")

    private val expectedTranscriptBuilder = FakeEpisodePodcastindexTranscriptBuilder()
        .url("https://example.com/ep3/transcript.txt")
        .type(TranscriptType.PLAIN_TEXT)
        .language(Locale.ITALY)
        .rel("captions")

    @Test
    fun `should extract all podcast fields from channel when present`() {
        val channel: Node = XmlRes("/xml/channel.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.podcastPodcastindexBuilder, "podcast: podcast data").isNotNull().all {
            prop(FakePodcastPodcastindexBuilder::lockedBuilderValue).isEqualTo(expectedLockedBuilder)
            prop(FakePodcastPodcastindexBuilder::fundingBuilders).containsExactly(expectedFundingBuilder)
        }
    }

    @Test
    fun `should extract nothing from channel when podcast data is all empty`() {
        val channel: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel")
        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.podcastPodcastindexBuilder, "podcast: episode data").all {
            prop(FakePodcastPodcastindexBuilder::lockedBuilderValue).isNull()
            prop(FakePodcastPodcastindexBuilder::fundingBuilders).isEmpty()
        }
    }

    @Test
    fun `should extract nothing from channel when no podcast data is present`() {
        val channel: Node = XmlRes("/xml/channel-incomplete.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.podcastPodcastindexBuilder, "podcast: episode data").all {
            prop(FakePodcastPodcastindexBuilder::lockedBuilderValue).isNull()
            prop(FakePodcastPodcastindexBuilder::fundingBuilders).isEmpty()
        }
    }

    @Test
    fun `should extract all podcast fields from item when present`() {
        val item: Node = XmlRes("/xml/item.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        item.parseItemChildNodes(builder)

        assertThat(builder.podcastindexBuilder, "podcast: item data").all {
            prop(FakeEpisodePodcastindexBuilder::chaptersBuilderValue).isEqualTo(expectedChaptersBuilder)
            prop(FakeEpisodePodcastindexBuilder::soundbiteBuilders).containsExactly(expectedSoundbiteBuilder)
            prop(FakeEpisodePodcastindexBuilder::transcriptBuilders).containsExactly(expectedTranscriptBuilder)
        }
    }

    @Test
    fun `should extract nothing from item when no podcast data is present`() {
        val item: Node = XmlRes("/xml/item-incomplete.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        item.parseItemChildNodes(builder)

        assertThat(builder.podcastindexBuilder, "podcast: item data").all {
            prop(FakeEpisodePodcastindexBuilder::chaptersBuilderValue).isNull()
            prop(FakeEpisodePodcastindexBuilder::soundbiteBuilders).isEmpty()
            prop(FakeEpisodePodcastindexBuilder::transcriptBuilders).isEmpty()
        }
    }

    @Test
    fun `should extract nothing from item when podcast data is all empty`() {
        val item: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel/item")
        val builder = FakeEpisodeBuilder()
        item.parseItemChildNodes(builder)

        assertThat(builder.podcastindexBuilder, "podcast: item data").all {
            prop(FakeEpisodePodcastindexBuilder::chaptersBuilderValue).isNull()
            prop(FakeEpisodePodcastindexBuilder::soundbiteBuilders).isEmpty()
            prop(FakeEpisodePodcastindexBuilder::transcriptBuilders).isEmpty()
        }
    }

    @Test
    internal fun `should not extract podcast soundbite tags from the item when the durations are invalid`() {
        val item: Node = XmlRes("/xml/item-invalid.xml").rootNodeByName("item")

        val builder = FakeEpisodeBuilder()
        item.parseItemChildNodes(builder)

        assertThat(builder.podcastindexBuilder, "podcast: item data").all {
            prop(FakeEpisodePodcastindexBuilder::soundbiteBuilders).isEmpty()
        }
    }
}
