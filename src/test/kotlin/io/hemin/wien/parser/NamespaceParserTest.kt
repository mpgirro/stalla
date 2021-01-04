package io.hemin.wien.parser

import io.hemin.wien.builder.fake.episode.FakeEpisodeBuilder
import io.hemin.wien.builder.fake.podcast.FakePodcastBuilder
import io.hemin.wien.util.NodeListWrapper.Companion.asListOfNodes
import org.w3c.dom.Node

/** Base class for unit test classes checking implementations of [NamespaceParser]. */
internal abstract class NamespaceParserTest {

    /** The [NamespaceParser] implementation that the test class tests. */
    abstract val parser: NamespaceParser

    /** Parse [channel] and add result to [builder]. */
    protected fun parseChannelNode(builder: FakePodcastBuilder, channel: Node) {
        for (element in channel.childNodes.asListOfNodes()) {
            parser.parse(builder, element)
        }
    }

    /** Parse [item] and add result to [builder]. */
    protected fun parseItemNode(builder: FakeEpisodeBuilder, item: Node) {
        for (element in item.childNodes.asListOfNodes()) {
            parser.parse(builder, element)
        }
    }
}
