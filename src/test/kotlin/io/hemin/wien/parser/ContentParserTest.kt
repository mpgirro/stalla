package io.hemin.wien.parser

import io.hemin.wien.model.Episode
import io.hemin.wien.model.builder.EpisodeBuilder
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test
import org.w3c.dom.Node

class ContentParserTest : NamespaceParserTest() {

    override val parser: NamespaceParser = ContentParser()

    val item: Node? = nodeFromResource("item", "/xml/item.xml")

    @Test
    fun testParseContent() {
        item?.let {
            val builder = EpisodeBuilder()
            parse(builder, it)
            val e: Episode = builder.build()

            assertEquals("Lorem Ipsum", e.contentEncoded)
        } ?: run {
            fail("item not found")
        }
    }

}
