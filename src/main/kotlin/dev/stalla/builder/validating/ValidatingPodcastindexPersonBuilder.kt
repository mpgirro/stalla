package dev.stalla.builder.validating

import dev.stalla.builder.PodcastindexPersonBuilder
import dev.stalla.model.podcastindex.PodcastindexPerson
import dev.stalla.util.InternalAPI

@InternalAPI
internal class ValidatingPodcastindexPersonBuilder : PodcastindexPersonBuilder {

    private lateinit var nameValue: String

    private var role: String? = null
    private var group: String? = null
    private var img: String? = null
    private var href: String? = null

    override fun name(name: String): PodcastindexPersonBuilder = apply { this.nameValue = name }

    override fun role(role: String?): PodcastindexPersonBuilder = apply { this.role = role }

    override fun group(group: String?): PodcastindexPersonBuilder = apply { this.group = group }

    override fun img(img: String?): PodcastindexPersonBuilder = apply { this.img = img }

    override fun href(href: String?): PodcastindexPersonBuilder = apply { this.href = href }

    override val hasEnoughDataToBuild: Boolean
        get() = ::nameValue.isInitialized

    override fun build(): PodcastindexPerson? {
        if (!hasEnoughDataToBuild) return null

        return PodcastindexPerson(
            name = nameValue,
            role = role,
            group = group,
            img = img,
            href = href
        )
    }
}
