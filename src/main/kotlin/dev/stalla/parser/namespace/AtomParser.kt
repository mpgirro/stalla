package dev.stalla.parser.namespace

import dev.stalla.builder.AtomBuilder
import dev.stalla.builder.AtomPersonBuilderProvider
import dev.stalla.builder.LinkBuilder
import dev.stalla.builder.LinkBuilderProvider
import dev.stalla.builder.episode.ProvidingEpisodeBuilder
import dev.stalla.builder.podcast.ProvidingPodcastBuilder
import dev.stalla.dom.getAttributeValueByName
import dev.stalla.dom.toAtomPersonBuilder
import dev.stalla.parser.NamespaceParser
import dev.stalla.util.FeedNamespace
import dev.stalla.util.InternalApi
import org.w3c.dom.Node

/**
 * Parser implementation for the Atom namespace.
 *
 * The namespace URI is: `http://www.w3.org/2005/Atom`
 */
@InternalApi
internal object AtomParser : NamespaceParser() {

    override val namespace = FeedNamespace.ATOM

    override fun Node.parseChannelData(builder: ProvidingPodcastBuilder) {
        val atomBuilder = builder.atomBuilder
        parseCommonAtomData(
            atomPersonBuilderProvider = builder,
            linkBuilderProvider = builder,
            atomBuilder = atomBuilder
        )
    }

    override fun Node.parseItemData(builder: ProvidingEpisodeBuilder) {
        val atomBuilder = builder.atomBuilder
        parseCommonAtomData(
            atomPersonBuilderProvider = builder,
            linkBuilderProvider = builder,
            atomBuilder = atomBuilder
        )
    }

    private fun Node.parseCommonAtomData(
        atomPersonBuilderProvider: AtomPersonBuilderProvider,
        linkBuilderProvider: LinkBuilderProvider,
        atomBuilder: AtomBuilder
    ) {
        when (localName) {
            "contributor" -> {
                val personBuilder = ifCanBeParsed {
                    toAtomPersonBuilder(atomPersonBuilderProvider.createAtomPersonBuilder(), namespace)
                } ?: return
                atomBuilder.addContributorBuilder(personBuilder)
            }
            "author" -> {
                val personBuilder = ifCanBeParsed {
                    toAtomPersonBuilder(atomPersonBuilderProvider.createAtomPersonBuilder(), namespace)
                } ?: return
                atomBuilder.addAuthorBuilder(personBuilder)
            }
            "link" -> {
                val linkBuilder = ifCanBeParsed {
                    toLinkBuilder(linkBuilderProvider.createLinkBuilder())
                } ?: return
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
