package io.hemin.wien.parser

import io.hemin.wien.builder.EpisodeBuilder
import io.hemin.wien.builder.PodcastBuilder
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.DomBuilderFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.w3c.dom.Node
import javax.xml.parsers.DocumentBuilder

class ItunesParserTest : NamespaceParserTest {

    override val parser = ItunesParser()
    override val domBuilder: DocumentBuilder = DomBuilderFactory.newBuilder()

    val channel: Node? = nodeFromResource("channel", "/xml/channel.xml")
    val item: Node? = nodeFromResource("item", "/xml/item.xml")

    @Test
    fun testParseChannelItunes() {
        channel?.let {
            val builder = PodcastBuilder()
            parse(builder, it)

            builder.build().itunes?.let {
                assertEquals("Lorem Ipsum", it.subtitle)
            }
        } ?: run {
            fail("channel not found")
        }
    }

    @Test
    fun testParseItemItunes() {
        item?.let {
            val builder = EpisodeBuilder()
            parse(builder, it)

            builder.build().itunes?.let {
                assertEquals("Lorem Ipsum", it.title)
            }
        } ?: run {
            fail("item not found")
        }
    }

}
