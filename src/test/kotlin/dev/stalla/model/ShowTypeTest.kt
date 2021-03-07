package dev.stalla.model

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import dev.stalla.arguments
import dev.stalla.model.itunes.ShowType
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

internal class ShowTypeTest {

    private class ShowTypeNameProvider : ArgumentsProvider by arguments(*allShowTypeNames.toTypedArray())

    private class ShowTypeEnumPropertyProvider : ArgumentsProvider by arguments(*ShowType.values())

    @ParameterizedTest
    @ArgumentsSource(ShowTypeNameProvider::class)
    fun `should retrieve all iTunes show types from the factory method`(typeName: String) {
        assertThat(ShowType.of(typeName)).isNotNull()
            .prop(ShowType::type).isEqualTo(typeName)
    }

    @ParameterizedTest
    @ArgumentsSource(ShowTypeEnumPropertyProvider::class)
    fun `should expose only iTunes show type properties that are defined`(showType: ShowType) {
        assertThat(allShowTypeNames).contains(showType.type)
    }

    @Test
    fun `should not retrieve an undefined iTunes show type from the factory method`() {
        assertThat(ShowType.of("itunes show type")).isNull()
    }

    @Test
    fun `should be case insensitive in the iTunes show type factory method`() {
        assertThat(ShowType.of("EPISODIC")).isNotNull()
            .prop(ShowType::type).isEqualTo("episodic")
    }

    companion object {

        @JvmStatic
        private val allShowTypeNames = listOf(
            "episodic",
            "serial"
        )
    }
}
