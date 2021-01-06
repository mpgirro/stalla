package io.hemin.wien.parser

import io.hemin.wien.builder.fake.episode.FakeEpisodeBuilder
import io.hemin.wien.builder.fake.podcast.FakePodcastBuilder
import io.hemin.wien.dom.asListOfNodes
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
