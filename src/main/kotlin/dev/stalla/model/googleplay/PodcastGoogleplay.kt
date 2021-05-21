package dev.stalla.model.googleplay

import dev.stalla.builder.podcast.PodcastGoogleplayBuilder
import dev.stalla.builder.validating.podcast.ValidatingPodcastGoogleplayBuilder
import dev.stalla.model.BuilderFactory
import dev.stalla.model.HrefOnlyImage

/**
 * Model class for data from the Google Play namespace valid within an RSS `<channel>`.
 *
 * Properties are as defined in the
 * [XML Schema](https://www.google.com/schemas/play-podcasts/1.0/play-podcasts.xsd)
 * for the Google Play Podcasts extension
 *
 * Direct instantiation in Java is discouraged. Use the [builder][PodcastGoogleplay.Factory.builder]
 * method to obtain a [PodcastGoogleplayBuilder] instance for expressive construction instead.
 *
 * @property author The `<googleplay:author>` element text content.
 * @property email The `<googleplay:email>` element text content.
 * @property categories The list of `<googleplay:category>` element text contents as [GoogleplayCategory].
 * @property description The `<googleplay:description>` element text content.
 * @property explicitType The logical value of the `<googleplay:explicit>` element text content. Will be renamed to `explicit` in v2.0.0.
 * @property block The logical value of the `<googleplay:block>` element text content.
 * @property image The data from the `<googleplay:image>` element as an [HrefOnlyImage].
 * @property newFeedUrl The `<googleplay:newFeedUrl>` element text content.
 *
 * @since 1.0.0
 */
public data class PodcastGoogleplay(
    val author: String? = null,
    val email: String? = null,
    val categories: List<GoogleplayCategory>,
    val description: String? = null,
    val explicitType: ExplicitType? = null,
    val block: Boolean,
    val image: HrefOnlyImage? = null,
    val newFeedUrl: String? = null
) {

    @Deprecated(
        message = "Use the new constructor taking explicitType instead. This constructor is scheduled for removal in v2.0.0.",
        replaceWith = ReplaceWith(
            "PodcastGoogleplay(author, email, categories, description, if (explicit) ExplicitType.YES else ExplicitType.NO, block, image, " +
                "newFeedUrl)",
            "dev.stalla.model.googleplay.ExplicitType"
        )
    )
    public constructor(
        author: String? = null,
        email: String? = null,
        categories: List<GoogleplayCategory>,
        description: String? = null,
        explicit: Boolean? = null,
        block: Boolean,
        image: HrefOnlyImage? = null,
        newFeedUrl: String? = null
    ) : this(author, email, categories, description, explicit?.let { if (it) ExplicitType.YES else ExplicitType.NO }, block, image, newFeedUrl)

    /** The logical value of the `<googleplay:explicit>` element text content.  */
    @Deprecated(
        message = "The explicit property is deprecated. Please migrate to the explicitType property, which will be renamed to 'explicit' once " +
            "this property is removed. This property is scheduled for removal in v2.0.0.",
        replaceWith = ReplaceWith("explicitType == ExplicitType.YES", "dev.stalla.model.googleplay.ExplicitType")
    )
    val explicit: Boolean? = explicitType?.let { it == ExplicitType.YES }

    /** Provides a builder for the [PodcastGoogleplay] class. */
    public companion object Factory : BuilderFactory<PodcastGoogleplay, PodcastGoogleplayBuilder> {

        /** Returns a builder implementation for building [PodcastGoogleplay] model instances. */
        @JvmStatic
        override fun builder(): PodcastGoogleplayBuilder = ValidatingPodcastGoogleplayBuilder()
    }
}
