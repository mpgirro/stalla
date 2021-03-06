package dev.stalla.builder.validating.episode

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import assertk.assertions.prop
import dev.stalla.builder.episode.EpisodeBitloveBuilder
import dev.stalla.model.anEpisodeBitlove
import dev.stalla.model.bitlove.Bitlove
import org.junit.jupiter.api.Test

internal class ValidatingEpisodeBitloveBuilderTest {

    @Test
    internal fun `should not build an Episode Bitlove when the mandatory fields are missing`() {
        val episodeBitloveBuilder = ValidatingEpisodeBitloveBuilder()

        assertAll {
            assertThat(episodeBitloveBuilder).prop(EpisodeBitloveBuilder::hasEnoughDataToBuild).isFalse()

            assertThat(episodeBitloveBuilder.build()).isNull()
        }
    }

    @Test
    internal fun `should build an Episode Bitlove with all the mandatory fields`() {
        val episodeBitloveBuilder = ValidatingEpisodeBitloveBuilder()
            .guid("guid")

        assertAll {
            assertThat(episodeBitloveBuilder).prop(EpisodeBitloveBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeBitloveBuilder.build()).isNotNull().prop(Bitlove::guid).isEqualTo("guid")
        }
    }

    @Test
    internal fun `should populate an Episode Bitlove builder with all properties from an Episode Bitlove model`() {
        val episodeBitlove = anEpisodeBitlove()
        val episodeBitloveBuilder = Bitlove.builder().applyFrom(episodeBitlove)

        assertAll {
            assertThat(episodeBitloveBuilder).prop(EpisodeBitloveBuilder::hasEnoughDataToBuild).isTrue()

            assertThat(episodeBitloveBuilder.build()).isNotNull().isEqualTo(episodeBitlove)
        }
    }
}
