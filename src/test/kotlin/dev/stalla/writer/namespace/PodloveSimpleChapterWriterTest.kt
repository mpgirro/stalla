package dev.stalla.writer.namespace

import assertk.all
import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isInstanceOf
import dev.stalla.hasNoAttribute
import dev.stalla.hasNoDifferences
import dev.stalla.hasOneChild
import dev.stalla.hasValue
import dev.stalla.model.episode.aPodloveSimpleChapter
import dev.stalla.model.episode.anEpisode
import dev.stalla.model.episode.anEpisodePodlove
import org.junit.jupiter.api.Test
import org.w3c.dom.Element

internal class PodloveSimpleChapterWriterTest : NamespaceWriterTest() {

    override val writer = PodloveSimpleChapterWriter

    @Test
    internal fun `should write the correct Podlove SimpleChapter tags to the item when there is data to write`() {
        writeEpisodeData("chapters") { element ->
            val diff = element.diffFromExpected("/rss/channel/item[1]/psc:chapters[1]")
            assertThat(diff).hasNoDifferences()
        }
    }

    @Test
    internal fun `should not write Podlove SimpleChapter tags to the item when there is no data to write`() {
        assertTagIsNotWrittenToEpisode(anEpisode(podlove = null), "chapters")
    }

    @Test
    internal fun `should not write Podlove SimpleChapter tags to the item when data is blank`() {
        val podlove = anEpisodePodlove(
            listOf(
                aPodloveSimpleChapter(start = "start 1", title = "title 1", href = " ", image = " "),
                aPodloveSimpleChapter(start = " ", title = " ", href = " ", image = " ")
            )
        )
        assertAll {
            writeEpisodeData("chapters", episode = anEpisode(podlove = podlove)) { element ->
                assertThat(element).hasOneChild()
                assertThat(element.firstChild, "chapter").isInstanceOf(Element::class).all {
                    hasAttribute("start", namespace = null).hasValue("start 1")
                    hasAttribute("title", namespace = null).hasValue("title 1")
                    hasNoAttribute("href", namespace = null)
                    hasNoAttribute("image", namespace = null)
                }
            }
        }
    }

    @Test
    internal fun `should not write Podlove SimpleChapter tags to the item when data is empty`() {
        val podlove = anEpisodePodlove(
            listOf(
                aPodloveSimpleChapter(start = "start 1", title = "title 1", href = "", image = ""),
                aPodloveSimpleChapter(start = "", title = "", href = "", image = "")
            )
        )
        assertAll {
            writeEpisodeData("chapters", episode = anEpisode(podlove = podlove)) { element ->
                assertThat(element).hasOneChild()
                assertThat(element.firstChild, "chapter").isInstanceOf(Element::class).all {
                    hasAttribute("start", namespace = null).hasValue("start 1")
                    hasAttribute("title", namespace = null).hasValue("title 1")
                    hasNoAttribute("href", namespace = null)
                    hasNoAttribute("image", namespace = null)
                }
            }
        }
    }
}
