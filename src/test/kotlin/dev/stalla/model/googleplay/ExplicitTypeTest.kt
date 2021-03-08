package dev.stalla.model.googleplay

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import dev.stalla.arguments
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

internal class ExplicitTypeTest {

    private class ExplicitTypeNameProvider : ArgumentsProvider by arguments(*allExplicitTypeNames.toTypedArray())

    private class ExplicitTypeEnumPropertyProvider : ArgumentsProvider by arguments(*ExplicitType.values())

    @ParameterizedTest
    @ArgumentsSource(ExplicitTypeNameProvider::class)
    fun `should retrieve all Google Play explicit types from the factory method`(typeName: String) {
        assertThat(ExplicitType.of(typeName)).isNotNull()
            .prop(ExplicitType::type).isEqualTo(typeName)
    }

    @ParameterizedTest
    @ArgumentsSource(ExplicitTypeEnumPropertyProvider::class)
    fun `should expose only Google Play explicit type properties that are defined`(explicitType: ExplicitType) {
        assertThat(allExplicitTypeNames).contains(explicitType.type)
    }

    @Test
    fun `should not retrieve an undefined Google Play explicit type from the factory method`() {
        assertThat(ExplicitType.of("googleplay explicit type")).isNull()
    }

    @Test
    fun `should be case insensitive in the Google Play explicit type factory method`() {
        assertThat(ExplicitType.of("CLEAN")).isNotNull()
            .prop(ExplicitType::type).isEqualTo("clean")
    }

    @Test
    fun `should not parse null to an instance in the factory nethod`() {
        assertThat(ExplicitType.of(null)).isNull()
    }

    companion object {

        @JvmStatic
        private val allExplicitTypeNames = listOf(
            "yes",
            "no",
            "clean"
        )
    }
}
