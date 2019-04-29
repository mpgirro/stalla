package io.hemin.wien.dom

import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import org.w3c.dom.Node

interface NodeParser {
    fun parse(podcast: Podcast.Builder, node: Node): Unit
    fun parse(episode: Episode.Builder, node: Node): Unit
}
