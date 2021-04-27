package dev.stalla.builder

import dev.stalla.model.podcastindex.PodcastindexPerson
import dev.stalla.util.whenNotNull

/**
 * Builder for constructing [PodcastindexPerson] instances.
 *
 * @since 1.1.0
 */
public interface PodcastindexPersonBuilder : Builder<PodcastindexPerson> {

    /** Set the name value. */
    public fun name(name: String): PodcastindexPersonBuilder

    /** Set the role value. */
    public fun role(role: String?): PodcastindexPersonBuilder

    /** Set the group value. */
    public fun group(group: String?): PodcastindexPersonBuilder

    /** Set the img value. */
    public fun img(img: String?): PodcastindexPersonBuilder

    /** Set the href value. */
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
