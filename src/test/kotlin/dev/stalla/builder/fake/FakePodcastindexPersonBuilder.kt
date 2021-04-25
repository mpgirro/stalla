package dev.stalla.builder.fake

import dev.stalla.builder.PodcastindexPersonBuilder
import dev.stalla.model.podcastindex.PodcastindexPerson

internal class FakePodcastindexPersonBuilder : FakeBuilder<PodcastindexPerson>(), PodcastindexPersonBuilder {

    var name: String? = null
    var role: String? = null
    var group: String? = null
    var img: String? = null
    var href: String? = null

    override fun name(name: String): PodcastindexPersonBuilder = apply { this.name = name }

    override fun role(role: String?): PodcastindexPersonBuilder = apply { this.role = role }

    override fun group(group: String?): PodcastindexPersonBuilder = apply { this.group = group }

    override fun img(img: String?): PodcastindexPersonBuilder = apply { this.img = img }

    override fun href(href: String?): PodcastindexPersonBuilder = apply { this.href = href }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FakePodcastindexPersonBuilder

        if (name != other.name) return false
        if (role != other.role) return false
        if (group != other.group) return false
        if (img != other.img) return false
        if (href != other.href) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name?.hashCode() ?: 0
        result = 31 * result + (role?.hashCode() ?: 0)
        result = 31 * result + (group?.hashCode() ?: 0)
        result = 31 * result + (img?.hashCode() ?: 0)
        result = 31 * result + (href?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "FakePodcastindexPersonBuilder(name=$name, role=$role, group=$group, img=$img, href=$href)"
    }
}
