package dev.stalla.builder.fake

import assertk.fail
import dev.stalla.builder.Builder

/**
 * A test-only variant of a [Builder] that cannot be built. Subclasses are expected
 * to expose all their status for tests to inspect.
 */
internal abstract class FakeBuilder<T> : Builder<T> {

    final override val hasEnoughDataToBuild = false

    /**
     * Calling this method will always make the test fail. Inspect the
     * property values directly, instead
     */
    final override fun build(): T? {
        fail("You're not supposed to call this method")
    }
}
