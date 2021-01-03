package io.hemin.wien.builder.validating.episode

import assertk.all
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import io.hemin.wien.model.Episode
import org.junit.jupiter.api.Test

internal class ValidatingEpisodeBitloveBuilderTest {

    @Test
    internal fun `should not build an Episode Bitlove when the mandatory fields are missing`() {
        val episodeBitloveBuilder = ValidatingEpisodeBitloveBuilder()

        assertThat(episodeBitloveBuilder.build()).isNull()
    }

    @Test
    internal fun `should build an Episode Bitlove with all the mandatory fields`() {
        val episodeBitloveBuilder = ValidatingEpisodeBitloveBuilder()
            .guid("guid")

        assertThat(episodeBitloveBuilder.build()).isNotNull().all {
            prop(Episode.Bitlove::guid).isEqualTo("guid")
        }
    }
}
