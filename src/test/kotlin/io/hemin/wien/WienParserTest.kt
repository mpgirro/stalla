package io.hemin.wien

import assertk.all
import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import io.hemin.wien.model.Episode
import io.hemin.wien.model.Podcast
import io.hemin.wien.util.findElementByTagName
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test
import javax.xml.parsers.DocumentBuilderFactory

/** Provides unit tests for [WienParser]. */
internal class WienParserTest {

    private val documentBuilder = DocumentBuilderFactory.newInstance()
        .newDocumentBuilder()

    @Test
    internal fun `should return null when parsing an empty document`() {
        assertThat(WienParser.parse(createDocument())).isNull()
    }

    @Test
    internal fun `should return null when parsing a document with no 'rss' tag at top level`() {
        assertThat(WienParser.parse(createDocumentWithNode("banana"))).isNull()
    }

    @Test
    internal fun `should return null when parsing a document with an empty 'rss' tag`() {
        assertThat(WienParser.parse(createDocumentWithNode("rss"))).isNull()
    }

    @Test
    internal fun `should return null when parsing a document with no 'channel' tag in a non-empty 'rss'`() {
        val document = createDocumentWithNode("rss").apply {
            val rssElement = findElementByTagName("rss") ?: fail("No rss element found")
            rssElement.appendChild(createElement("banana"))
        }
        assertThat(WienParser.parse(document)).isNull()
    }

    @Test
    internal fun `should return null when parsing a document with an empty 'channel' tag in 'rss'`() {
        val document = createDocumentWithNode("rss").apply {
            val rssElement = findElementByTagName("rss") ?: fail("No rss element found")
            rssElement.appendChild(createElement("channel"))
        }
        assertThat(WienParser.parse(document)).isNull()
    }

    @Test
    internal fun `should pick up the first non empty 'channel' tag in 'rss'`() {
        val document = validRssDocument().apply {
            val rssElement = findElementByTagName("rss") ?: fail("No rss element found")
            val channelElement = rssElement.findElementByTagName("channel") ?: fail("No channel element found")
            rssElement.insertBefore(createElement("channel"), channelElement)
        }
        assertThat(WienParser.parse(document)).isNotNull()
    }

    @Test
    internal fun `should return null when parsing a document with no 'item' tag in a non-empty 'channel'`() {
        val document = createDocumentWithNode("rss").apply {
            val rssElement = findElementByTagName("rss") ?: fail("No rss element found")
            val channelElement = rssElement.appendChild(createElement("channel"))
            channelElement.appendChild(createElement("banana"))
        }
        assertThat(WienParser.parse(document)).isNull()
    }

    @Test
    internal fun `should skip empty 'item' tags in 'channel' when parsing a valid document`() {
        val document = validRssDocument().apply {
            val rssElement = findElementByTagName("rss") ?: fail("No rss element found")
            val channelElement = rssElement.findElementByTagName("channel") ?: fail("No channel element found")
            val firstItemElement = channelElement.findElementByTagName("item") ?: fail("No item element found")
            channelElement.insertBefore(createElement("item"), firstItemElement)
        }
        assertThat(WienParser.parse(document)).isNotNull()
            .prop(Podcast::episodes).hasSize(2) // Two in the xml — the empty one we inserted is skipped
    }

    @Test
    internal fun `should parse a basic feed correctly`() {
        val document = validRssDocument()
        assertThat(WienParser.parse(document)).isNotNull().all {
            prop(Podcast::title).isEqualTo("Lorem Ipsum title")
            prop(Podcast::description).isEqualTo("Lorem Ipsum description")
            prop(Podcast::link).isEqualTo("http://example.org")
            prop(Podcast::episodes).given { episodes ->
                assertThat(episodes).hasSize(2)

                assertThat(episodes[0]).all {
                    prop(Episode::title).isEqualTo("Lorem Ipsum")
                    prop(Episode::link).isEqualTo("http://example.org/episode1")
                    prop(Episode::description).isEqualTo("Lorem Ipsum episode 1 description")
                    prop(Episode::enclosure).all {
                        prop(Episode.Enclosure::url).isEqualTo("http://example.org/episode1.m4a")
                        prop(Episode.Enclosure::type).isEqualTo("audio/mp4")
                        prop(Episode.Enclosure::length).isEqualTo(78589133)
                    }
                }

                assertThat(episodes[1]).all {
                    prop(Episode::title).isEqualTo("Lorem Ipsum 2")
                    prop(Episode::link).isEqualTo("http://example.org/episode2")
                    prop(Episode::description).isEqualTo("Lorem Ipsum episode 2 description")
                    prop(Episode::enclosure).all {
                        prop(Episode.Enclosure::url).isEqualTo("http://example.org/episode2.mp3")
                        prop(Episode.Enclosure::type).isEqualTo("audio/mp3")
                        prop(Episode.Enclosure::length).isEqualTo(78133)
                    }
                }
            }
        }
    }

    private fun createDocumentWithNode(localName: String) = createDocument().apply {
        val element = createElement(localName)
        appendChild(element)
    }

    private fun createDocument() = documentBuilder.newDocument()

    private fun validRssDocument() = documentFromResource("/xml/rss.xml")
}
