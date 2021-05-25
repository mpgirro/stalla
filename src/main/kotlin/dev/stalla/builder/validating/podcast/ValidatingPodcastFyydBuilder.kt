package dev.stalla.builder.validating.podcast

import dev.stalla.builder.podcast.PodcastFyydBuilder
import dev.stalla.builder.validating.checkRequiredProperty
import dev.stalla.model.fyyd.Fyyd
import dev.stalla.util.InternalAPI

@InternalAPI
internal class ValidatingPodcastFyydBuilder : PodcastFyydBuilder {

    private var verify: String? = null

    override fun verify(verify: String): PodcastFyydBuilder = apply { this.verify = verify }

    override val hasEnoughDataToBuild: Boolean
        get() = verify != null

    override fun build(): Fyyd? {
        if (!hasEnoughDataToBuild) {
            return null
        }

        return Fyyd(checkRequiredProperty(::verify))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ValidatingPodcastFyydBuilder) return false

        if (verify != other.verify) return false

        return true
    }

    override fun hashCode(): Int = verify.hashCode()

    override fun toString(): String = "ValidatingPodcastFyydBuilder(verify='$verify')"
}
