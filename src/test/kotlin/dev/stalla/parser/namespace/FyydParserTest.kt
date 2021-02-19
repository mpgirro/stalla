package dev.stalla.parser.namespace

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import assertk.assertions.prop
import dev.stalla.builder.fake.podcast.FakePodcastBuilder
import dev.stalla.builder.fake.podcast.FakePodcastFyydBuilder
import dev.stalla.dom.XmlRes
import dev.stalla.parser.NamespaceParserTest
import org.junit.jupiter.api.Test
import org.w3c.dom.Node

internal class FyydParserTest : NamespaceParserTest() {

    override val parser = FyydParser

    @Test
    fun `should extract Fyyd data from channel when present`() {
        val node = XmlRes("/xml/channel.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        node.parseChannelChildNodes(builder)

        assertThat(builder.fyydBuilder, "channel.fyyd")
            .prop(FakePodcastFyydBuilder::verifyValue).isEqualTo("abcdefg")
    }

    @Test
    fun `should not extract Fyyd data from channel when absent`() {
        val node = XmlRes("/xml/channel-incomplete.xml").rootNodeByName("channel")
        val builder = FakePodcastBuilder()
        node.parseChannelChildNodes(builder)

        assertThat(builder.fyydBuilder, "channel.fyyd")
            .prop(FakePodcastFyydBuilder::verifyValue).isNull()
    }

    @Test
    fun `should extract nothing from channel when Fyyd data is all empty`() {
        val channel: Node = XmlRes("/xml/rss-all-empty.xml").nodeByXPath("/rss/channel")
        val builder = FakePodcastBuilder()
        channel.parseChannelChildNodes(builder)

        assertThat(builder.fyydBuilder, "channel.fyyd")
            .prop(FakePodcastFyydBuilder::verifyValue).isNull()
    }
}
