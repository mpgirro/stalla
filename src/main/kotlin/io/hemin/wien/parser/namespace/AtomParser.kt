package io.hemin.wien.parser.namespace

import io.hemin.wien.builder.AtomBuilder
import io.hemin.wien.builder.LinkBuilder
import io.hemin.wien.builder.LinkBuilderProvider
import io.hemin.wien.builder.PersonBuilderProvider
import io.hemin.wien.builder.episode.EpisodeBuilder
import io.hemin.wien.builder.podcast.PodcastBuilder
import io.hemin.wien.dom.getAttributeValueByName
import io.hemin.wien.dom.toPersonBuilder
import io.hemin.wien.parser.NamespaceParser
import io.hemin.wien.util.FeedNamespace
import io.hemin.wien.util.InternalApi
import org.w3c.dom.Node

/**
 * Parser implementation for the Atom namespace.
 *
 * The namespace URI is: `http://www.w3.org/2005/Atom`
 */
@InternalApi
internal object AtomParser : NamespaceParser() {

    override val namespace = FeedNamespace.ATOM

    override fun Node.parseChannelData(builder: PodcastBuilder) {
        val atomBuilder = builder.atomBuilder
        parseCommonAtomData(personBuilderProvider = builder, linkBuilderProvider = builder, atomBuilder = atomBuilder)
    }

    override fun Node.parseItemData(builder: EpisodeBuilder) {
        val atomBuilder = builder.atomBuilder
        parseCommonAtomData(personBuilderProvider = builder, linkBuilderProvider = builder, atomBuilder = atomBuilder)
    }

    private fun Node.parseCommonAtomData(
        personBuilderProvider: PersonBuilderProvider,
        linkBuilderProvider: LinkBuilderProvider,
        atomBuilder: AtomBuilder
    ) {
        when (localName) {
            "contributor" -> {
                val personBuilder = ifCanBeParsed { toPersonBuilder(personBuilderProvider.createPersonBuilder(), namespace) } ?: return
                atomBuilder.addContributorBuilder(personBuilder)
            }
            "author" -> {
                val personBuilder = ifCanBeParsed { toPersonBuilder(personBuilderProvider.createPersonBuilder(), namespace) } ?: return
                atomBuilder.addAuthorBuilder(personBuilder)
            }
            "link" -> {
                val linkBuilder = ifCanBeParsed { toLinkBuilder(linkBuilderProvider.createLinkBuilder()) } ?: return
                atomBuilder.addLinkBuilder(linkBuilder)
            }
            else -> pass
        }
    }

    private fun Node.toLinkBuilder(linkBuilder: LinkBuilder): LinkBuilder? = ifCanBeParsed {
        val href = getAttributeValueByName("href") ?: return@ifCanBeParsed null

        linkBuilder.href(href)
            .hrefLang(getAttributeValueByName("hrefLang"))
            .hrefResolved(getAttributeValueByName("hrefResolved"))
            .length(getAttributeValueByName("length"))
            .rel(getAttributeValueByName("rel"))
            .title(getAttributeValueByName("title"))
            .type(getAttributeValueByName("type"))
    }
}
