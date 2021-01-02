package io.hemin.wien.parser

import assertk.all
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import io.hemin.wien.builder.fake.FakeImageBuilder
import io.hemin.wien.builder.fake.episode.FakeEpisodeBuilder
import io.hemin.wien.builder.fake.episode.FakeEpisodeGooglePlayBuilder
import io.hemin.wien.builder.fake.podcast.FakePodcastBuilder
import io.hemin.wien.builder.fake.podcast.FakePodcastGooglePlayBuilder
import io.hemin.wien.nodeFromResource
import io.hemin.wien.parser.namespace.GooglePlayParser
import org.junit.jupiter.api.Test

internal class GooglePlayParserTest : NamespaceParserTest() {

    override val parser: NamespaceParser = GooglePlayParser()

    private val expectedPodcastImageBuilder = FakeImageBuilder().url("http://example.org/podcast-cover.jpg")

    private val expectedEpisodeImageBuilder = FakeImageBuilder().url("http://example.org/episode-cover.jpg")

    @Test
    fun `should extract all google play fields from channel when present`() {
        val node = nodeFromResource("channel", "/xml/channel.xml")
        val builder = FakePodcastBuilder()
        node.parseChannelChildNodes(builder)

        assertThat(builder.googlePlay, "channel.googleplay").all {
            prop(FakePodcastGooglePlayBuilder::author).isEqualTo("Lorem Ipsum")
            prop(FakePodcastGooglePlayBuilder::owner).isEqualTo("email@example.org")
            prop(FakePodcastGooglePlayBuilder::categories).containsExactly("Technology")
            prop(FakePodcastGooglePlayBuilder::description).isEqualTo("Lorem Ipsum")
            prop(FakePodcastGooglePlayBuilder::explicit).isNotNull().isFalse()
            prop(FakePodcastGooglePlayBuilder::block).isNotNull().isFalse()
            prop(FakePodcastGooglePlayBuilder::imageBuilder).isEqualTo(expectedPodcastImageBuilder)
        }
    }

    @Test
    fun `should not extract google play fields from channel when absent`() {
        val node = nodeFromResource("channel", "/xml/channel-incomplete.xml")
        val builder = FakePodcastBuilder()
        node.parseChannelChildNodes(builder)

        assertThat(builder.googlePlay, "channel.googleplay").all {
            prop(FakePodcastGooglePlayBuilder::author).isNull()
            prop(FakePodcastGooglePlayBuilder::owner).isNull()
            prop(FakePodcastGooglePlayBuilder::categories).isEmpty()
            prop(FakePodcastGooglePlayBuilder::description).isNull()
            prop(FakePodcastGooglePlayBuilder::explicit).isNull()
            prop(FakePodcastGooglePlayBuilder::block).isNull()
            prop(FakePodcastGooglePlayBuilder::imageBuilder).isNull()
        }
    }

    @Test
    fun `should extract all google play fields from item when present`() {
        val node = nodeFromResource("item", "/xml/item.xml")
        val builder = FakeEpisodeBuilder()
        node.parseItemChildNodes(builder)

        assertThat(builder.googlePlay, "item.googleplay").all {
            prop(FakeEpisodeGooglePlayBuilder::description).isEqualTo("Lorem Ipsum")
            prop(FakeEpisodeGooglePlayBuilder::explicit).isNotNull().isFalse()
            prop(FakeEpisodeGooglePlayBuilder::block).isNotNull().isFalse()
            prop(FakeEpisodeGooglePlayBuilder::imageBuilder).isEqualTo(expectedEpisodeImageBuilder)
        }
    }

    @Test
    fun `should not extract google play fields from item when absent`() {
        val node = nodeFromResource("item", "/xml/item-incomplete.xml")
        val builder = FakeEpisodeBuilder()
        node.parseItemChildNodes(builder)

        assertThat(builder.googlePlay, "item.googleplay").all {
            prop(FakeEpisodeGooglePlayBuilder::description).isNull()
            prop(FakeEpisodeGooglePlayBuilder::explicit).isNull()
            prop(FakeEpisodeGooglePlayBuilder::block).isNull()
            prop(FakeEpisodeGooglePlayBuilder::imageBuilder).isNull()
        }
    }
}
