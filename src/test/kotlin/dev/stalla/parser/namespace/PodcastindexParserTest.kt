package dev.stalla.parser.namespace

import assertk.all
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import dev.stalla.builder.fake.FakePodcastindexLocationBuilder
import dev.stalla.builder.fake.FakePodcastindexPersonBuilder
import dev.stalla.builder.fake.episode.FakeEpisodeBuilder
import dev.stalla.builder.fake.episode.FakeEpisodePodcastindexBuilder
import dev.stalla.builder.fake.episode.FakeEpisodePodcastindexChaptersBuilder
import dev.stalla.builder.fake.episode.FakeEpisodePodcastindexEpisodeBuilder
import dev.stalla.builder.fake.episode.FakeEpisodePodcastindexSeasonBuilder
import dev.stalla.builder.fake.episode.FakeEpisodePodcastindexSoundbiteBuilder
import dev.stalla.builder.fake.episode.FakeEpisodePodcastindexTranscriptBuilder
import dev.stalla.builder.fake.podcast.FakePodcastBuilder
import dev.stalla.builder.fake.podcast.FakePodcastPodcastindexBuilder
import dev.stalla.builder.fake.podcast.FakePodcastPodcastindexFundingBuilder
import dev.stalla.builder.fake.podcast.FakePodcastPodcastindexLockedBuilder
import dev.stalla.builder.validating.ValidatingPodcastindexLocationBuilder
import dev.stalla.builder.validating.ValidatingPodcastindexPersonBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodePodcastindexEpisodeBuilder
import dev.stalla.builder.validating.episode.ValidatingEpisodePodcastindexSeasonBuilder
import dev.stalla.dom.XmlRes
import dev.stalla.model.MediaType
import dev.stalla.model.StyledDuration
import dev.stalla.model.podcastindex.GeoLocation
import dev.stalla.model.podcastindex.OpenStreetMapFeature
import dev.stalla.model.podcastindex.TranscriptType
import dev.stalla.parser.NamespaceParserTest
import org.junit.jupiter.api.Test
import org.w3c.dom.Node
import java.util.Locale

internal class PodcastindexParserTest : NamespaceParserTest() {

    override val parser = PodcastindexParser

    private val expectedLockedBuilder = FakePodcastPodcastindexLockedBuilder()
        .locked(true)
        .owner("podcast podcastindex locked owner")

    private val expectedFundingBuilder = FakePodcastPodcastindexFundingBuilder()
        .url("podcast podcastindex funding url")
        .message("podcast podcastindex funding message")

    private val expectedChaptersBuilder = FakeEpisodePodcastindexChaptersBuilder()
        .url("episode podcastindex chapters url")
        .type(MediaType.JSON_CHAPTERS)

    private val expectedSoundbiteBuilder = FakeEpisodePodcastindexSoundbiteBuilder()
        .startTime(StyledDuration.secondsAndFraction(33, 833_000_000))
        .duration(StyledDuration.secondsAndFraction(60))
        .title("episode podcastindex soundbite title")

    private val expectedTranscriptBuilder = FakeEpisodePodcastindexTranscriptBuilder()
        .url("episode podcastindex transcript url")
        .type(TranscriptType.SRT)
        .language(Locale.ITALY)
        .rel("episode podcastindex transcript rel")

    private val expectedPodcastPersonBuilder = FakePodcastindexPersonBuilder()
        .name("podcast podcastindex person name")
        .role("podcast podcastindex person role")
        .group("podcast podcastindex person group")
        .img("podcast podcastindex person img")
        .href("podcast podcastindex person href")

    private val expectedEpisodePersonBuilder = FakePodcastindexPersonBuilder()
        .name("episode podcastindex person name")
        .role("episode podcastindex person role")
        .group("episode podcastindex person group")
        .img("episode podcastindex person img")
        .href("episode podcastindex person href")

    private val expectedPodcastLocationBuilder = FakePodcastindexLocationBuilder()
        .name("podcast podcastindex location name")
        .geo(GeoLocation.of("geo:1,2,3"))
        .osm(OpenStreetMapFeature.of("R123"))

    private val expectedEpisodeLocationBuilder = FakePodcastindexLocationBuilder()
        .name("episode podcastindex location name")
        .geo(GeoLocation.of("geo:4,5,6"))
        .osm(OpenStreetMapFeature.of("W456"))

    private val expectedSeasonBuilder = FakeEpisodePodcastindexSeasonBuilder()
        .number(1)
        .name("episode podcastindex season name")

    private val expectedEpisodeBuilder = FakeEpisodePodcastindexEpisodeBuilder()
        .number(1.0)
        .display("episode podcastindex episode display")

    @Test
    fun `should extract all Podcastindex fields from channel when present`() {
        val channel: Node = XmlRes("/xml/channel.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.podcastPodcastindexBuilder, "channel.podcastindex").isNotNull().all {
            prop(FakePodcastPodcastindexBuilder::lockedBuilderValue).isEqualTo(expectedLockedBuilder)
            prop(FakePodcastPodcastindexBuilder::fundingBuilders).containsExactly(expectedFundingBuilder)
            prop(FakePodcastPodcastindexBuilder::personBuilders).containsExactly(expectedPodcastPersonBuilder)
            prop(FakePodcastPodcastindexBuilder::locationBuilderValue).isEqualTo(expectedPodcastLocationBuilder)
        }
    }

    @Test
    fun `should extract nothing from channel when Podcastindex data is all empty`() {
        val channel: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel")
        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.podcastPodcastindexBuilder, "channel.podcastindex").all {
            prop(FakePodcastPodcastindexBuilder::lockedBuilderValue).isNull()
            prop(FakePodcastPodcastindexBuilder::fundingBuilders).isEmpty()
            prop(FakePodcastPodcastindexBuilder::personBuilders).isEmpty()
            prop(FakePodcastPodcastindexBuilder::locationBuilderValue).isNull()
        }
    }

    @Test
    fun `should extract nothing from channel when no Podcastindex data is present`() {
        val channel: Node = XmlRes("/xml/channel-incomplete.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.podcastPodcastindexBuilder, "channel.podcastindex").all {
            prop(FakePodcastPodcastindexBuilder::lockedBuilderValue).isNull()
            prop(FakePodcastPodcastindexBuilder::fundingBuilders).isEmpty()
            prop(FakePodcastPodcastindexBuilder::personBuilders).isEmpty()
            prop(FakePodcastPodcastindexBuilder::locationBuilderValue).isNull()
        }
    }

    @Test
    fun `should extract all Podcastindex fields from item when present`() {
        val item: Node = XmlRes("/xml/item.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        item.parseItemChildNodes(builder)

        assertThat(builder.podcastindexBuilder, "item.podcastindex").all {
            prop(FakeEpisodePodcastindexBuilder::chaptersBuilderValue).isEqualTo(expectedChaptersBuilder)
            prop(FakeEpisodePodcastindexBuilder::soundbiteBuilders).containsExactly(expectedSoundbiteBuilder)
            prop(FakeEpisodePodcastindexBuilder::transcriptBuilders).containsExactly(expectedTranscriptBuilder)
            prop(FakeEpisodePodcastindexBuilder::personBuilders).containsExactly(expectedEpisodePersonBuilder)
            prop(FakeEpisodePodcastindexBuilder::locationBuilderValue).isEqualTo(expectedEpisodeLocationBuilder)
            prop(FakeEpisodePodcastindexBuilder::seasonBuilder).isEqualTo(expectedSeasonBuilder)
            prop(FakeEpisodePodcastindexBuilder::episodeBuilder).isEqualTo(expectedEpisodeBuilder)
        }
    }

    @Test
    fun `should extract nothing from item when no Podcastindex data is present`() {
        val item: Node = XmlRes("/xml/item-incomplete.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        item.parseItemChildNodes(builder)

        assertThat(builder.podcastindexBuilder, "item.podcastindex").all {
            prop(FakeEpisodePodcastindexBuilder::chaptersBuilderValue).isNull()
            prop(FakeEpisodePodcastindexBuilder::soundbiteBuilders).isEmpty()
            prop(FakeEpisodePodcastindexBuilder::transcriptBuilders).isEmpty()
            prop(FakeEpisodePodcastindexBuilder::personBuilders).isEmpty()
            prop(FakeEpisodePodcastindexBuilder::locationBuilderValue).isNull()
            prop(FakeEpisodePodcastindexBuilder::seasonBuilder).isNull()
            prop(FakeEpisodePodcastindexBuilder::episodeBuilder).isNull()
        }
    }

    @Test
    fun `should extract nothing from item when Podcastindex data is all empty`() {
        val item: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel/item")
        val builder = FakeEpisodeBuilder()
        item.parseItemChildNodes(builder)

        assertThat(builder.podcastindexBuilder, "item.podcastindex").all {
            prop(FakeEpisodePodcastindexBuilder::chaptersBuilderValue).isNull()
            prop(FakeEpisodePodcastindexBuilder::soundbiteBuilders).isEmpty()
            prop(FakeEpisodePodcastindexBuilder::transcriptBuilders).isEmpty()
            prop(FakeEpisodePodcastindexBuilder::personBuilders).isEmpty()
            prop(FakeEpisodePodcastindexBuilder::locationBuilderValue).isNull()
            prop(FakeEpisodePodcastindexBuilder::seasonBuilder).isNull()
            prop(FakeEpisodePodcastindexBuilder::episodeBuilder).isNull()
        }
    }

    @Test
    internal fun `should not extract Podcastindex Locked tags from the channel when the value is invalid`() {
        val channel: Node = XmlRes("/xml/channel-invalid.xml").rootNodeByName("channel")

        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.podcastPodcastindexBuilder, "channel.podcastindex").all {
            prop(FakePodcastPodcastindexBuilder::lockedBuilderValue).isNull()
        }
    }

    @Test
    internal fun `should not extract Podcastindex Soundbite tags from the item when the durations are invalid`() {
        val item: Node = XmlRes("/xml/item-invalid.xml").rootNodeByName("item")

        val builder = FakeEpisodeBuilder()
        item.parseItemChildNodes(builder)

        assertThat(builder.podcastindexBuilder, "item.podcastindex").all {
            prop(FakeEpisodePodcastindexBuilder::soundbiteBuilders).isEmpty()
        }
    }

    @Test
    internal fun `should not extract Podcastindex Transcript tags from the item when the types are invalid`() {
        val item: Node = XmlRes("/xml/item-invalid.xml").rootNodeByName("item")

        val builder = FakeEpisodeBuilder()
        item.parseItemChildNodes(builder)

        assertThat(builder.podcastindexBuilder, "item.podcastindex").all {
            prop(FakeEpisodePodcastindexBuilder::transcriptBuilders).isEmpty()
        }
    }
}
