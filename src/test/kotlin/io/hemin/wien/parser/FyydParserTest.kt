package io.hemin.wien.parser

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import assertk.assertions.prop
import io.hemin.wien.builder.fake.podcast.FakePodcastBuilder
import io.hemin.wien.builder.fake.podcast.FakePodcastFyydBuilder
import io.hemin.wien.nodeFromResource
import io.hemin.wien.parser.namespace.FyydParser
import org.junit.jupiter.api.Test

internal class FyydParserTest : NamespaceParserTest() {

    override val parser: NamespaceParser = FyydParser()

    @Test
    fun `should extract fyyd data from channel when present`() {
        val node = nodeFromResource("channel", "/xml/channel.xml")
        val builder = FakePodcastBuilder()
        parseChannelChildNodes(builder, node)

        assertThat(builder.fyyd, "channel.fyyd")
            .prop(FakePodcastFyydBuilder::verifyValue).isEqualTo("abcdefg")
    }

    @Test
    fun `should not extract fyyd data from channel when absent`() {
        val node = nodeFromResource("channel", "/xml/channel-incomplete.xml")
        val builder = FakePodcastBuilder()
        parseChannelChildNodes(builder, node)

        assertThat(builder.fyyd, "channel.fyyd")
            .prop(FakePodcastFyydBuilder::verifyValue).isNull()
    }
}
