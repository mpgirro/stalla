package dev.stalla.parser

import dev.stalla.builder.fake.episode.FakeEpisodeBuilder
import dev.stalla.builder.fake.podcast.FakePodcastBuilder
import dev.stalla.dom.asListOfNodes
import org.w3c.dom.Node

internal abstract class NamespaceParserTest {

    abstract val parser: NamespaceParser

    protected fun Node.parseChannelChildNodes(builder: FakePodcastBuilder) {
        for (element in childNodes.asListOfNodes()) {
            parser.tryParsingChannelChildNode(element, builder)
        }
    }

    protected fun Node.parseItemChildNodes(builder: FakeEpisodeBuilder) {
        for (element in childNodes.asListOfNodes()) {
            parser.tryParsingItemChildNode(element, builder)
        }
    }
}
