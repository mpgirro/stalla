package io.hemin.wien.parser

import io.hemin.wien.model.Episode
import io.hemin.wien.model.builder.EpisodeBuilder
import org.junit.Test
import org.w3c.dom.Node
import kotlin.test.assertEquals
import kotlin.test.fail

class ContentParserTest : NamespaceParserTest() {

    override val parser: NamespaceParser = ContentParser()

    val item: Node? = nodeFromResource("item", "/xml/item.xml")

    @Test
    fun testParseContent() {
        item?.let {
            val builder = EpisodeBuilder()
            parse(builder, it)
            val e: Episode = builder.build()

            assertEquals("Lorem Ipsum", e.contentEncoded, "content:encoded was not as expected")
        } ?: run {
            fail("item not found")
        }
    }

}
