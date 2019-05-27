package io.hemin.wien.builder

import io.hemin.wien.model.Episode

/** Builder class for [Episode.Googleplay] instances. */
class EpisodeGoogleplayBuilder : Builder<Episode.Googleplay> {

    private var description: String? = null
    private var duration: String?    = null
    private var explicit: Boolean?   = null

    /** Set the description value. */
    fun description(description: String?) = apply { this.description = description }

    /** Set the duration value. */
    fun duration(duration: String?) = apply { this.duration = duration }

    /** Set the explicit value. */
    fun explicit(explicit: Boolean?) = apply { this.explicit = explicit }

    override fun build(): Episode.Googleplay? {
        return if (anyNotNull(description, duration, explicit)) {
            return Episode.Googleplay(
                description = description,
                duration    = duration,
                explicit    = explicit
            )
        } else {
            null
        }
    }

}
