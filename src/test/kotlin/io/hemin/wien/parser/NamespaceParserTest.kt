package io.hemin.wien.parser

import io.hemin.wien.builder.fake.episode.FakeEpisodeBuilder
import io.hemin.wien.builder.fake.podcast.FakePodcastBuilder
import io.hemin.wien.util.NodeListWrapper.Companion.asListOfNodes
import org.w3c.dom.Node

internal abstract class NamespaceParserTest {

    abstract val parser: NamespaceParser

    protected fun parseChannelNode(builder: FakePodcastBuilder, channel: Node) {
        for (element in channel.childNodes.asListOfNodes()) {
            parser.parse(builder, element)
        }
    }

    protected fun parseItemNode(builder: FakeEpisodeBuilder, item: Node) {
        for (element in item.childNodes.asListOfNodes()) {
            parser.parse(builder, element)
        }
    }
}
