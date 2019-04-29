package io.hemin.wien.dom

import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import org.w3c.dom.Node

class RssParser : NodeParser {

    companion object {
        val NAMESPACE: String? = null
    }

    val namespace: String? = RssParser.NAMESPACE

    fun parse(podcast: Podcast.Builder, node: Node, episodeFromItem: (Node) -> Episode): Unit {
        when (node.localName) {
            "title" -> podcast.title(asTitle(node))
            "link"  -> podcast.link(asLink(node))
            "item"  -> podcast.addEpisode(episodeFromItem(node))
        }
    }

    override fun parse(podcast: Podcast.Builder, node: Node): Unit {
        // TODO write a logger warning
        parse(podcast, node, fun(n: Node): Episode {
            val e: Episode.Builder = Episode.Builder()
            parse(e, n)
            return e.build()
        })
    }

    override fun parse(episode: Episode.Builder, node: Node): Unit {
        when (node.localName) {
            "title" -> episode.title(asTitle(node))
            "link"  -> episode.link(asLink(node))
        }
    }

    fun asTitle(node: Node): String? = node.textContent

    fun asLink(node: Node): String? = node.textContent


}
