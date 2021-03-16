package dev.stalla.util

import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.Test

class AnyExtensionsTest {

    @Test
    internal fun `should treat optional parameters as null in allNull(null)`() {
        assertThat(allNull(null)).isTrue()
    }

    @Test
    internal fun `should treat optional parameters as null in allNull(null, null)`() {
        assertThat(allNull(null, null)).isTrue()
    }

    @Test
    internal fun `should treat optional parameters as null in allNull(a)`() {
        assertThat(allNull("a")).isFalse()
    }

    @Test
    internal fun `should treat optional parameters as null in allNull(a,b)`() {
        assertThat(allNull("a", "b")).isFalse()
    }

    @Test
    internal fun `should treat optional parameters as null in allNotNull(null)`() {
        assertThat(allNotNull(null)).isFalse()
    }

    @Test
    internal fun `should treat optional parameters as null in allNotNull(null, null)`() {
        assertThat(allNotNull(null, null)).isFalse()
    }

    @Test
    internal fun `should treat optional parameters as null in allNotNull(a)`() {
        assertThat(allNotNull("a")).isTrue()
    }

    @Test
    internal fun `should treat optional parameters as null in allNotNull(a,b)`() {
        assertThat(allNotNull("a", "b")).isTrue()
    }

}
