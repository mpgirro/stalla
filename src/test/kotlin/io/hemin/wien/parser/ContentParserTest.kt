package io.hemin.wien.parser

import io.hemin.wien.builder.EpisodeBuilder
import io.hemin.wien.model.Episode
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.w3c.dom.Node

class ContentParserTest : NamespaceParserTest() {

    override val parser: NamespaceParser = ContentParser()

    val item: Node? = nodeFromResource("item", "/xml/item.xml")

    @Test
    fun testParseItemContent() {
        item?.let {
            val builder = EpisodeBuilder()
            parse(builder, it)

            builder.build().content?.let {
                assertEquals("Lorem Ipsum", it.encoded)
            } ?: run {
                fail("Episode Content data not extracted")
            }
        } ?: run {
            fail("item not found")
        }
    }

}
