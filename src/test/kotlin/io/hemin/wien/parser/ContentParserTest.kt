package io.hemin.wien.parser

import io.hemin.wien.model.Episode
import io.hemin.wien.builder.EpisodeBuilder
import io.hemin.wien.util.DomBuilderFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.w3c.dom.Node
import javax.xml.parsers.DocumentBuilder

class ContentParserTest : NamespaceParserTest {

    override val parser: NamespaceParser = ContentParser()
    override val domBuilder: DocumentBuilder = DomBuilderFactory.newBuilder()

    val item: Node? = nodeFromResource("item", "/xml/item.xml")

    @Test
    fun testParseContent() {
        item?.let {
            val builder = EpisodeBuilder()
            parse(builder, it)
            val e: Episode = builder.build()

            Assertions.assertEquals("Lorem Ipsum", e.contentEncoded)
        } ?: run {
            fail("item not found")
        }
    }

}
