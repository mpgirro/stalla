package dev.stalla.model

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.prop
import dev.stalla.model.podcastindex.TranscriptType
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import kotlin.reflect.KVisibility
import kotlin.reflect.full.declaredMemberProperties

class TranscriptTypeTest {

    internal class TranscriptTypeNameProvider : ArgumentsProvider by arguments(*allTranscriptTypeNames.toTypedArray())

    internal class TranscriptTypeFactoryPropertyProvider : ArgumentsProvider by arguments(
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
        assertThat(TranscriptType.of(typeName)).isNotNull()
            .prop("toString") { TranscriptType::toString.call(it) }.isEqualTo(typeName)
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

    @Test
    fun `should not retrieve an undefined Transcript Type from the interface factory method`() {
        assertThat(TranscriptType.of("undefined transcript type")).isNull()
    }

    companion object {

        @JvmStatic
        val allTranscriptTypeNames = listOf(
            "text/plain",
            "text/html",
            "application/json",
            "application/srt"
        )

    }

}
