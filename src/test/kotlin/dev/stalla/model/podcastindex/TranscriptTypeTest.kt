package dev.stalla.model.podcastindex

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import dev.stalla.arguments
import dev.stalla.equalToString
import dev.stalla.model.MediaType
import dev.stalla.model.TranscriptTypeNameProvider
import dev.stalla.model.allTranscriptTypeNames
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import kotlin.reflect.KVisibility
import kotlin.reflect.full.declaredMemberProperties

internal class TranscriptTypeTest {

    private class TranscriptTypeFactoryPropertyProvider : ArgumentsProvider by arguments(
        *TranscriptType.Factory::class.declaredMemberProperties
            .filter { member -> member.visibility == KVisibility.PUBLIC }
            .mapNotNull { member -> member.getter.call(this) }
            .filterIsInstance<TranscriptType>()
            .toTypedArray()
    )

    private val factoryPropertyMap: Map<String, TranscriptType> by lazy {
        val values: List<TranscriptType> = TranscriptType.Factory::class.declaredMemberProperties
            .filter { member -> member.visibility == KVisibility.PUBLIC }
            .mapNotNull { member -> member.getter.call(this) }
            .filterIsInstance<TranscriptType>()

        values.associateBy({ it.toString() }, { it })
    }

    @ParameterizedTest
    @ArgumentsSource(TranscriptTypeNameProvider::class)
    fun `should retrieve all Transcript Types from the factory method`(typeName: String) {
        assertThat(TranscriptType.of(typeName)).isNotNull().equalToString(typeName)
    }

    @ParameterizedTest
    @ArgumentsSource(TranscriptTypeNameProvider::class)
    fun `should have a companion object property for every Transcript Type`(typeName: String) {
        assertThat(factoryPropertyMap[typeName]).isNotNull()
    }

    @ParameterizedTest
    @ArgumentsSource(TranscriptTypeFactoryPropertyProvider::class)
    fun `should expose only Transcript Type properties that are defined`(type: TranscriptType) {
        assertThat(allTranscriptTypeNames).contains(type.toString())
    }

    @ParameterizedTest
    @ArgumentsSource(TranscriptTypeFactoryPropertyProvider::class)
    fun `should retrieve the correct Transcript Type instances from the companion object factory method`(
        type: TranscriptType
    ) {
        assertThat(TranscriptType.of(type.toString())).isEqualTo(type)
    }

    @ParameterizedTest
    @ArgumentsSource(TranscriptTypeFactoryPropertyProvider::class)
    fun `should provide Transcript Type instances that are also valid Media Types`(type: TranscriptType) {
        assertThat(type).isInstanceOf(MediaType::class)
    }

    @Test
    fun `should not retrieve an undefined Transcript Type from the interface factory method`() {
        assertThat(TranscriptType.of("undefined transcript type")).isNull()
    }

    @Test
    fun `should be case insensitive in the Podcastindex transcript type factory method`() {
        assertThat(TranscriptType.of("TEXT/PLAIN")).isNotNull().equalToString("text/plain")
    }

    @Test
    fun `should not parse null to an instance in the factory nethod`() {
        assertThat(TranscriptType.of(null)).isNull()
    }

    companion object // Ensure there is a `this` instance to pass to kotlin.reflect.KCallable.call
}
