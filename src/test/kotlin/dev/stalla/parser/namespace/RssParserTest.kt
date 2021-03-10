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
import dev.stalla.model.MediaType
import dev.stalla.parser.NamespaceParserTest
import org.junit.jupiter.api.Test
import org.w3c.dom.Node
import java.time.Month
import java.util.Locale

internal class RssParserTest : NamespaceParserTest() {

    override val parser = RssParser

    // "Fri, 16 Mar 2018 22:49:08 +0000"
    private val expectedDate = dateTime(year = 2018, month = Month.MARCH, day = 16, hour = 22, minute = 49, second = 8)

    private val expectedEnclosureBuilder = FakeEpisodeEnclosureBuilder().apply {
        urlValue = "episode rss enclosure url"
        lengthValue = 78589133
        typeValue = MediaType.MPEG_AUDIO
    }

    private val expectedGuidBuilder = FakeEpisodeGuidBuilder().apply {
        textContent = "episode rss guid"
        isPermalink = true
    }

    private val expectedImageBuilder = FakeRssImageBuilder().apply {
        url = "podcast rss image url"
        title = "podcast rss image title"
        link = "podcast rss image link"
        width = 600
        height = 600
        description = "podcast rss image description"
    }

    @Test
    fun `should extract all RSS fields from channel when present`() {
        val node = XmlRes("/xml/channel.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        node.parseChannelChildNodes(builder)

        assertThat(builder, "channel.rss").all {
            prop(FakePodcastBuilder::titleValue).isEqualTo("podcast rss title")
            prop(FakePodcastBuilder::linkValue).isEqualTo("podcast rss link")
            prop(FakePodcastBuilder::descriptionValue).isEqualTo("podcast rss description")
            prop(FakePodcastBuilder::pubDate).isEqualTo(expectedDate)
            prop(FakePodcastBuilder::lastBuildDate).isEqualTo(expectedDate)
            prop(FakePodcastBuilder::languageValue).isEqualTo(Locale.GERMAN)
            prop(FakePodcastBuilder::generator).isEqualTo("podcast rss generator")
            prop(FakePodcastBuilder::copyright).isEqualTo("podcast rss copyright")
            prop(FakePodcastBuilder::docs).isEqualTo("podcast rss docs")
            prop(FakePodcastBuilder::managingEditor).isEqualTo("podcast rss managingeditor")
            prop(FakePodcastBuilder::webMaster).isEqualTo("podcast rss webmaster")
            prop(FakePodcastBuilder::imageBuilder).isEqualTo(expectedImageBuilder)
            prop(FakePodcastBuilder::categoryBuilders).containsExactly(
                FakeRssCategoryBuilder().category("podcast rss category 1").domain("podcast rss category domain"),
                FakeRssCategoryBuilder().category("podcast rss category 2")
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
            prop(FakeEpisodeBuilder::titleValue).isEqualTo("episode rss title")
            prop(FakeEpisodeBuilder::link).isEqualTo("episode rss link")
            prop(FakeEpisodeBuilder::description).isEqualTo("episode rss description")
            prop(FakeEpisodeBuilder::pubDate).isEqualTo(expectedDate)
            prop(FakeEpisodeBuilder::author).isEqualTo("episode rss author")
            prop(FakeEpisodeBuilder::comments).isEqualTo("episode rss comments")
            prop(FakeEpisodeBuilder::categoryBuilders).containsExactly(
                FakeRssCategoryBuilder().category("episode rss category 1").domain("episode rss category domain"),
                FakeRssCategoryBuilder().category("episode rss category 2")
            )
            prop(FakeEpisodeBuilder::enclosureBuilderValue).isEqualTo(expectedEnclosureBuilder)
            prop(FakeEpisodeBuilder::guidBuilder).isEqualTo(expectedGuidBuilder)
            prop(FakeEpisodeBuilder::source).isEqualTo("episode rss source")
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

    @Test
    fun `should not extract an RSS language tag from the channel when the value is invalid`() {
        val channel: Node = XmlRes("/xml/channel-invalid.xml").rootNodeByName("channel")

        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder, "channel.itunes").all {
            prop(FakePodcastBuilder::languageValue).isNull()
        }
    }
}
