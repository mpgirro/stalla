package dev.stalla.parser.namespace

import assertk.all
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import dev.stalla.builder.fake.FakeRssCategoryBuilder
import dev.stalla.builder.fake.FakeRssImageBuilder
import dev.stalla.builder.fake.episode.FakeEpisodeBuilder
import dev.stalla.builder.fake.episode.FakeEpisodeEnclosureBuilder
import dev.stalla.builder.fake.episode.FakeEpisodeGuidBuilder
import dev.stalla.builder.fake.podcast.FakePodcastBuilder
import dev.stalla.dateTime
import dev.stalla.dom.XmlRes
import dev.stalla.hasNotEnoughDataToBuild
import dev.stalla.parser.NamespaceParserTest
import org.junit.jupiter.api.Test
import org.w3c.dom.Node
import java.time.Month

internal class RssParserTest : NamespaceParserTest() {

    override val parser = RssParser

    // "Fri, 16 Mar 2018 22:49:08 +0000"
    private val expectedDate = dateTime(year = 2018, month = Month.MARCH, day = 16, hour = 22, minute = 49, second = 8)

    private val expectedEnclosureBuilder = FakeEpisodeEnclosureBuilder().apply {
        urlValue = "http://example.org/episode1.m4a"
        lengthValue = 78589133
        typeValue = "audio/mp4"
    }

    private val expectedGuidBuilder = FakeEpisodeGuidBuilder().apply {
        textContent = "1fa609024fdf097"
        isPermalink = true
    }

    private val expectedImageBuilder = FakeRssImageBuilder().apply {
        url = "http://example.org/podcast-cover.jpg"
        title = "Lorem Ipsum"
        link = "http://example.org"
        width = 600
        height = 600
        description = "Lorem Ipsum"
    }

    @Test
    fun `should extract all RSS fields from channel when present`() {
        val node = XmlRes("/xml/channel.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        node.parseChannelChildNodes(builder)

        assertThat(builder, "channel.rss").all {
            prop(FakePodcastBuilder::titleValue).isEqualTo("Lorem Ipsum")
            prop(FakePodcastBuilder::linkValue).isEqualTo("http://example.org")
            prop(FakePodcastBuilder::descriptionValue).isEqualTo("Lorem Ipsum")
            prop(FakePodcastBuilder::pubDate).isEqualTo(expectedDate)
            prop(FakePodcastBuilder::lastBuildDate).isEqualTo(expectedDate)
            prop(FakePodcastBuilder::languageValue).isEqualTo("de-DE")
            prop(FakePodcastBuilder::generator).isEqualTo("Lorem Ipsum")
            prop(FakePodcastBuilder::copyright).isEqualTo("Lorem Ipsum")
            prop(FakePodcastBuilder::docs).isEqualTo("Lorem Ipsum")
            prop(FakePodcastBuilder::managingEditor).isEqualTo("editor@example.org")
            prop(FakePodcastBuilder::webMaster).isEqualTo("webmaster@example.org")
            prop(FakePodcastBuilder::imageBuilder).isEqualTo(expectedImageBuilder)
            prop(FakePodcastBuilder::categoryBuilders).containsExactly(
                FakeRssCategoryBuilder().category("category 1").domain("banana"),
                FakeRssCategoryBuilder().category("category 2")
            )
        }
    }

    @Test
    fun `should not extract RSS fields from channel when absent`() {
        val node = XmlRes("/xml/channel-incomplete.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        node.parseChannelChildNodes(builder)

        assertThat(builder, "channel.rss").all {
            prop(FakePodcastBuilder::titleValue).isEqualTo("Lorem Ipsum")
            prop(FakePodcastBuilder::linkValue).isEqualTo("http://example.org")
            prop(FakePodcastBuilder::descriptionValue).isEqualTo("Lorem Ipsum")
            prop(FakePodcastBuilder::pubDate).isNull()
            prop(FakePodcastBuilder::lastBuildDate).isNull()
            prop(FakePodcastBuilder::languageValue).isNull()
            prop(FakePodcastBuilder::generator).isNull()
            prop(FakePodcastBuilder::copyright).isNull()
            prop(FakePodcastBuilder::docs).isNull()
            prop(FakePodcastBuilder::managingEditor).isNull()
            prop(FakePodcastBuilder::webMaster).isNull()
            prop(FakePodcastBuilder::imageBuilder).isNull()
            prop(FakePodcastBuilder::categoryBuilders).isEmpty()
        }
    }

    @Test
    fun `should extract nothing from channel when RSS data is all empty`() {
        val channel: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel")
        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder, "channel.rss").all {
            prop(FakePodcastBuilder::titleValue).isNull()
            prop(FakePodcastBuilder::linkValue).isNull()
            prop(FakePodcastBuilder::descriptionValue).isNull()
            prop(FakePodcastBuilder::pubDate).isNull()
            prop(FakePodcastBuilder::lastBuildDate).isNull()
            prop(FakePodcastBuilder::languageValue).isNull()
            prop(FakePodcastBuilder::generator).isNull()
            prop(FakePodcastBuilder::copyright).isNull()
            prop(FakePodcastBuilder::docs).isNull()
            prop(FakePodcastBuilder::managingEditor).isNull()
            prop(FakePodcastBuilder::webMaster).isNull()
            prop(FakePodcastBuilder::imageBuilder).isNotNull().hasNotEnoughDataToBuild()
            prop(FakePodcastBuilder::categoryBuilders).isEmpty()
        }
    }

    @Test
    fun `should extract all RSS fields from item when present`() {
        val node = XmlRes("/xml/item.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        node.parseItemChildNodes(builder)

        assertThat(builder, "item.rss").all {
            prop(FakeEpisodeBuilder::titleValue).isEqualTo("Lorem Ipsum")
            prop(FakeEpisodeBuilder::link).isEqualTo("http://example.org/episode1")
            prop(FakeEpisodeBuilder::description).isEqualTo("Lorem Ipsum")
            prop(FakeEpisodeBuilder::pubDate).isEqualTo(expectedDate)
            prop(FakeEpisodeBuilder::author).isEqualTo("author@example.org")
            prop(FakeEpisodeBuilder::comments).isEqualTo("http://example.org/episode1/comments")
            prop(FakeEpisodeBuilder::categoryBuilders).containsExactly(
                FakeRssCategoryBuilder().category("category 1").domain("banana"),
                FakeRssCategoryBuilder().category("category 2")
            )
            prop(FakeEpisodeBuilder::enclosureBuilderValue).isEqualTo(expectedEnclosureBuilder)
            prop(FakeEpisodeBuilder::guidBuilder).isEqualTo(expectedGuidBuilder)
            prop(FakeEpisodeBuilder::source).isEqualTo("http://example.org/rss")
        }
    }

    @Test
    fun `should not extract RSS fields from item when absent`() {
        val node = XmlRes("/xml/item-incomplete.xml").rootNodeByName("item")
        val builder = FakeEpisodeBuilder()
        node.parseItemChildNodes(builder)

        assertThat(builder, "item.rss").all {
            prop(FakeEpisodeBuilder::titleValue).isEqualTo("Lorem Ipsum")
            prop(FakeEpisodeBuilder::link).isEqualTo("http://example.org/episode1")
            prop(FakeEpisodeBuilder::description).isEqualTo("Lorem Ipsum")
            prop(FakeEpisodeBuilder::pubDate).isNull()
            prop(FakeEpisodeBuilder::author).isNull()
            prop(FakeEpisodeBuilder::comments).isNull()
            prop(FakeEpisodeBuilder::categoryBuilders).isEmpty()
            prop(FakeEpisodeBuilder::enclosureBuilderValue).isNull()
            prop(FakeEpisodeBuilder::guidBuilder).isNull()
            prop(FakeEpisodeBuilder::source).isNull()
        }
    }

    @Test
    fun `should extract nothing from item when RSS data is all empty`() {
        val node: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel/item")
        val builder = FakeEpisodeBuilder()
        node.parseItemChildNodes(builder)

        assertThat(builder, "item.rss").all {
            prop(FakeEpisodeBuilder::titleValue).isNull()
            prop(FakeEpisodeBuilder::link).isNull()
            prop(FakeEpisodeBuilder::description).isNull()
            prop(FakeEpisodeBuilder::pubDate).isNull()
            prop(FakeEpisodeBuilder::author).isNull()
            prop(FakeEpisodeBuilder::comments).isNull()
            prop(FakeEpisodeBuilder::categoryBuilders).isEmpty()
            prop(FakeEpisodeBuilder::enclosureBuilderValue).isNotNull().hasNotEnoughDataToBuild()
            prop(FakeEpisodeBuilder::guidBuilder).isNotNull().hasNotEnoughDataToBuild()
            prop(FakeEpisodeBuilder::source).isNull()
        }
    }
}
