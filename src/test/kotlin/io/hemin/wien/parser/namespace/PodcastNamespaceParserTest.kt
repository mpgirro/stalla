package io.hemin.wien.parser.namespace

import assertk.all
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import io.hemin.wien.builder.fake.episode.FakeEpisodeBuilder
import io.hemin.wien.builder.fake.episode.FakeEpisodePodcastBuilder
import io.hemin.wien.builder.fake.episode.FakeEpisodePodcastChaptersBuilder
import io.hemin.wien.builder.fake.episode.FakeEpisodePodcastSoundbiteBuilder
import io.hemin.wien.builder.fake.episode.FakeEpisodePodcastTranscriptBuilder
import io.hemin.wien.builder.fake.podcast.FakePodcastBuilder
import io.hemin.wien.builder.fake.podcast.FakePodcastPodcastBuilder
import io.hemin.wien.builder.fake.podcast.FakePodcastPodcastFundingBuilder
import io.hemin.wien.builder.fake.podcast.FakePodcastPodcastLockedBuilder
import io.hemin.wien.dom.XmlRes
import io.hemin.wien.model.Episode
import io.hemin.wien.parser.NamespaceParser
import io.hemin.wien.parser.NamespaceParserTest
import org.junit.jupiter.api.Test
import org.w3c.dom.Node
import java.time.Duration
import java.util.Locale

internal class PodcastNamespaceParserTest : NamespaceParserTest() {

    override val parser: NamespaceParser = PodcastNamespaceParser()

    private val expectedLockedBuilder = FakePodcastPodcastLockedBuilder()
        .locked(true)
        .owner("podcastowner@example.com")

    private val expectedFundingBuilder = FakePodcastPodcastFundingBuilder()
        .url("https://example.com/donate")
        .message("Support the show!")

    private val expectedChaptersBuilder = FakeEpisodePodcastChaptersBuilder()
        .url("https://example.com/ep3_chapters.json")
        .type("application/json")

    private val expectedSoundbiteBuilder = FakeEpisodePodcastSoundbiteBuilder()
        .startTime(Duration.ofMillis(33833))
        .duration(Duration.ofSeconds(60))
        .title("I'm a soundbite")

    private val expectedTranscriptBuilder = FakeEpisodePodcastTranscriptBuilder()
        .url("https://example.com/ep3/transcript.txt")
        .type(Episode.Podcast.Transcript.Type.PLAIN_TEXT)
        .language(Locale.ITALY)
        .rel("captions")

    @Test
    fun `should extract all podcast fields from channel when present`() {
        val channel: Node = XmlRes("/xml/channel.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.podcastBuilder, "podcast: podcast data").isNotNull().all {
            prop(FakePodcastPodcastBuilder::lockedBuilderValue).isEqualTo(expectedLockedBuilder)
            prop(FakePodcastPodcastBuilder::fundingBuilders).containsExactly(expectedFundingBuilder)
        }
    }

    @Test
    fun `should extract nothing from channel when podcast data is all empty`() {
        val channel: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel")
        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.podcastBuilder, "podcast: episode data").all {
            prop(FakePodcastPodcastBuilder::lockedBuilderValue).isNull()
            prop(FakePodcastPodcastBuilder::fundingBuilders).isEmpty()
        }
    }

    @Test
    fun `should extract nothing from channel when no podcast data is present`() {
        val channel: Node = XmlRes("/xml/channel-incomplete.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.podcastBuilder, "podcast: episode data").all {
            prop(FakePodcastPodcastBuilder::lockedBuilderValue).isNull()
            prop(FakePodcastPodcastBuilder::fundingBuilders).isEmpty()
        }
    }

    @Test
    fun `should extract all podcast fields from item when present`() {
        val item: Node = XmlRes("/xml/item.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        item.parseItemChildNodes(builder)

        assertThat(builder.podcastBuilder, "podcast: item data").all {
            prop(FakeEpisodePodcastBuilder::chaptersBuilderValue).isEqualTo(expectedChaptersBuilder)
            prop(FakeEpisodePodcastBuilder::soundbiteBuilders).containsExactly(expectedSoundbiteBuilder)
            prop(FakeEpisodePodcastBuilder::transcriptBuilders).containsExactly(expectedTranscriptBuilder)
        }
    }

    @Test
    fun `should extract nothing from item when no podcast data is present`() {
        val item: Node = XmlRes("/xml/item-incomplete.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        item.parseItemChildNodes(builder)

        assertThat(builder.podcastBuilder, "podcast: item data").all {
            prop(FakeEpisodePodcastBuilder::chaptersBuilderValue).isNull()
            prop(FakeEpisodePodcastBuilder::soundbiteBuilders).isEmpty()
            prop(FakeEpisodePodcastBuilder::transcriptBuilders).isEmpty()
        }
    }

    @Test
    fun `should extract nothing from item when podcast data is all empty`() {
        val item: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel/item")
        val builder = FakeEpisodeBuilder()
        item.parseItemChildNodes(builder)

        assertThat(builder.podcastBuilder, "podcast item data").all {
            prop(FakeEpisodePodcastBuilder::chaptersBuilderValue).isNull()
            prop(FakeEpisodePodcastBuilder::soundbiteBuilders).isEmpty()
            prop(FakeEpisodePodcastBuilder::transcriptBuilders).isEmpty()
        }
    }
}
