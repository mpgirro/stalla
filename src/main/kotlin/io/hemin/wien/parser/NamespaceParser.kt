package io.hemin.wien.parser

import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import org.w3c.dom.Node

interface NamespaceParser {
    val namespace: String?
    fun parse(podcast: Podcast.Builder, node: Node)
    fun parse(episode: Episode.Builder, node: Node)
}
