package dev.stalla.builder.validating

import dev.stalla.builder.OpenStreetMapElementBuilder
import dev.stalla.model.podcastindex.OpenStreetMapElement
import dev.stalla.model.podcastindex.OpenStreetMapElementType
import dev.stalla.util.InternalAPI

@InternalAPI
internal class ValidatingOpenStreetMapElementBuilder : OpenStreetMapElementBuilder {

    private lateinit var typeValue: OpenStreetMapElementType
    private var id: Long? = null
    private var revision: Int? = null

    override fun type(type: OpenStreetMapElementType): OpenStreetMapElementBuilder = apply { this.typeValue = type }

    override fun id(id: Long): OpenStreetMapElementBuilder = apply { this.id = id }

    override fun revision(revision: Int?): OpenStreetMapElementBuilder = apply { this.revision = revision }

    override val hasEnoughDataToBuild: Boolean
        get() = ::typeValue.isInitialized && id != null

    override fun build(): OpenStreetMapElement? {
        if (!hasEnoughDataToBuild) return null

        return OpenStreetMapElement(
            type = typeValue,
            id = id ?: return null,
            revision = revision
        )
    }
}
