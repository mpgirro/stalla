package io.hemin.wien.writer.namespace

import assertk.all
import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isInstanceOf
import io.hemin.wien.hasNoAttribute
import io.hemin.wien.hasNoDifferences
import io.hemin.wien.hasOneChild
import io.hemin.wien.hasValue
import io.hemin.wien.model.episode.aPodloveSimpleChapter
import io.hemin.wien.model.episode.anEpisode
import io.hemin.wien.model.episode.anEpisodePodlove
import org.junit.jupiter.api.Test
import org.w3c.dom.Element

internal class PodloveSimpleChapterWriterTest : NamespaceWriterTest() {

    override val writer = PodloveSimpleChapterWriter

    @Test
    internal fun `should write the correct psc tags to the item when there is data to write`() {
        writeEpisodeData("chapters") { element ->
            val diff = element.diffFromExpected("/rss/channel/item[1]/psc:chapters[1]")
            assertThat(diff).hasNoDifferences()
        }
    }

    @Test
    internal fun `should not write psc tags to the item when there is no data to write`() {
        assertTagIsNotWrittenToEpisode(anEpisode(podlove = null), "chapters")
    }

    @Test
    internal fun `should not write psc tags to the item when data is blank`() {
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
    internal fun `should not write psc tags to the item when data is empty`() {
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
