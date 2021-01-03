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
import io.hemin.wien.builder.fake.episode.FakeEpisodeBuilder
import io.hemin.wien.builder.fake.episode.FakeEpisodeITunesBuilder
import io.hemin.wien.builder.fake.podcast.FakePodcastBuilder
import io.hemin.wien.builder.fake.podcast.FakePodcastITunesBuilder
import io.hemin.wien.builder.validating.ValidatingImageBuilder
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Person
import io.hemin.wien.model.Podcast
import io.hemin.wien.nodeFromResource
import io.hemin.wien.parser.namespace.ITunesParser
import org.junit.jupiter.api.Test

/** Provides unit tests for [ITunesParser]. */
internal class ITunesParserTest : NamespaceParserTest() {

    override val parser = ITunesParser()

    private val expectedPodcastImageBuilder = ValidatingImageBuilder().url("http://example.org/podcast-cover.jpg")

    private val expectedEpisodeImageBuilder = ValidatingImageBuilder().url("http://example.org/episode-cover.jpg")

    private val expectedOwner = Person(
        name = "Lorem Ipsum",
        email = "owner@example.org"
    )

    @Test
    fun `should extract all itunes fields from channel when present`() {
        val node = nodeFromResource("channel", "/xml/channel.xml")
        val builder = FakePodcastBuilder()
        parseChannelNode(builder, node)

        assertThat(builder.iTunes, "channel.itunes").all {
            prop(FakePodcastITunesBuilder::author).isEqualTo("Lorem Ipsum")
            prop(FakePodcastITunesBuilder::owner).isEqualTo(expectedOwner)
            prop(FakePodcastITunesBuilder::categories).containsExactly("Technology", "Society & Culture", "Technology")
            prop(FakePodcastITunesBuilder::subtitle).isEqualTo("Lorem Ipsum")
            prop(FakePodcastITunesBuilder::summary).isEqualTo("Lorem Ipsum")
            prop(FakePodcastITunesBuilder::keywords).isEqualTo("Lorem Ipsum")
            prop(FakePodcastITunesBuilder::explicit).isNotNull().isFalse()
            prop(FakePodcastITunesBuilder::block).isNotNull().isFalse()
            prop(FakePodcastITunesBuilder::complete).isNotNull().isFalse()
            prop(FakePodcastITunesBuilder::imageBuilderValue).isEqualTo(expectedPodcastImageBuilder)
            prop(FakePodcastITunesBuilder::type).isEqualTo(Podcast.ITunes.ShowType.EPISODIC)
        }
    }

    @Test
    fun `should not extract itunes fields from channel when absent`() {
        val node = nodeFromResource("channel", "/xml/channel-incomplete.xml")
        val builder = FakePodcastBuilder()
        parseChannelNode(builder, node)

        assertThat(builder.iTunes, "channel.itunes").all {
            prop(FakePodcastITunesBuilder::author).isNull()
            prop(FakePodcastITunesBuilder::owner).isNull()
            prop(FakePodcastITunesBuilder::categories).isEmpty()
            prop(FakePodcastITunesBuilder::subtitle).isNull()
            prop(FakePodcastITunesBuilder::summary).isNull()
            prop(FakePodcastITunesBuilder::keywords).isNull()
            prop(FakePodcastITunesBuilder::explicit).isNull()
            prop(FakePodcastITunesBuilder::block).isNull()
            prop(FakePodcastITunesBuilder::complete).isNull()
            prop(FakePodcastITunesBuilder::imageBuilderValue).isNull()
            prop(FakePodcastITunesBuilder::type).isNull()
        }
    }

    @Test
    fun `should extract all itunes fields from item when present`() {
        val node = nodeFromResource("item", "/xml/item.xml")
        val builder = FakeEpisodeBuilder()
        parseItemNode(builder, node)

        assertThat(builder.iTunes, "item.itunes").all {
            prop(FakeEpisodeITunesBuilder::title).isEqualTo("Lorem Ipsum")
            prop(FakeEpisodeITunesBuilder::duration).isEqualTo("03:24:27")
            prop(FakeEpisodeITunesBuilder::season).isEqualTo(1)
            prop(FakeEpisodeITunesBuilder::episode).isEqualTo(1)
            prop(FakeEpisodeITunesBuilder::explicit).isNotNull().isFalse()
            prop(FakeEpisodeITunesBuilder::block).isNotNull().isFalse()
            prop(FakeEpisodeITunesBuilder::imageBuilder).isEqualTo(expectedEpisodeImageBuilder)
            prop(FakeEpisodeITunesBuilder::episodeType).isEqualTo(Episode.ITunes.EpisodeType.FULL)
        }
    }

    @Test
    fun `should not extract itunes fields from item when absent`() {
        val node = nodeFromResource("item", "/xml/item-incomplete.xml")
        val builder = FakeEpisodeBuilder()
        parseItemNode(builder, node)

        assertThat(builder.iTunes, "item.itunes").all {
            prop(FakeEpisodeITunesBuilder::title).isNull()
            prop(FakeEpisodeITunesBuilder::duration).isNull()
            prop(FakeEpisodeITunesBuilder::season).isNull()
            prop(FakeEpisodeITunesBuilder::episode).isNull()
            prop(FakeEpisodeITunesBuilder::explicit).isNull()
            prop(FakeEpisodeITunesBuilder::block).isNull()
            prop(FakeEpisodeITunesBuilder::imageBuilder).isNull()
            prop(FakeEpisodeITunesBuilder::episodeType).isNull()
        }
    }
}
