package dev.stalla.builder

import dev.stalla.model.podcastindex.PodcastindexPerson
import dev.stalla.util.whenNotNull

public interface PodcastindexPersonBuilder : Builder<PodcastindexPerson> {

    public fun name(name: String): PodcastindexPersonBuilder

    public fun role(role: String?): PodcastindexPersonBuilder

    public fun group(group: String?): PodcastindexPersonBuilder

    public fun img(img: String?): PodcastindexPersonBuilder

    public fun href(href: String?): PodcastindexPersonBuilder

    override fun applyFrom(prototype: PodcastindexPerson?): PodcastindexPersonBuilder =
        whenNotNull(prototype) { person ->
            name(person.name)
            role(person.role)
            group(person.group)
            img(person.img)
            href(person.href)
        }
}
