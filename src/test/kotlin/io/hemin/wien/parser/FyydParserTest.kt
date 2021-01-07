package io.hemin.wien.parser

import assertk.all
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import assertk.assertions.prop
import io.hemin.wien.builder.fake.podcast.FakePodcastAtomBuilder
import io.hemin.wien.builder.fake.podcast.FakePodcastBuilder
import io.hemin.wien.builder.fake.podcast.FakePodcastFyydBuilder
import io.hemin.wien.dom.XmlRes
import io.hemin.wien.noneHasEnoughDataToBuild
import io.hemin.wien.parser.namespace.FyydParser
import org.junit.jupiter.api.Test
import org.w3c.dom.Node

internal class FyydParserTest : NamespaceParserTest() {

    override val parser: NamespaceParser = FyydParser()

    @Test
    fun `should extract fyyd data from channel when present`() {
        val node = XmlRes("/xml/channel.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        node.parseChannelChildNodes(builder)

        assertThat(builder.fyyd, "channel.fyyd")
            .prop(FakePodcastFyydBuilder::verifyValue).isEqualTo("abcdefg")
    }

    @Test
    fun `should not extract fyyd data from channel when absent`() {
        val node = XmlRes("/xml/channel-incomplete.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        node.parseChannelChildNodes(builder)

        assertThat(builder.fyyd, "channel.fyyd")
            .prop(FakePodcastFyydBuilder::verifyValue).isNull()
    }

    @Test
    fun `should extract nothing from channel when fyyd data is all empty`() {
        val channel: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel")
        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.fyyd, "channel.fyyd")
            .prop(FakePodcastFyydBuilder::verifyValue).isNull()
    }
}
