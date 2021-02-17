package dev.stalla.model.googleplay

import dev.stalla.model.TypeFactory

/**
 *
 * Enum model for the finite value set encountered within the `<googleplay:category>`
 * element within a `<channel>` element.
 *
 * Available values are defined in [RSS feed guidelines for Google Podcasts](https://support.google.com/podcast-publishers/answer/9889544)
 *
 * @param type The string representation of the category.
 */
public enum class GoogleplayCategory(public val type: String) {

    /** Category type for _Arts_ */
    ARTS("Arts"),

    /** Category type for _Business_ */
    BUSINESS("Business"),

    /** Category type for _Comedy_ */
    COMEDY("Comedy"),

    /** Category type for _Education_ */
    EDUCATION("Education"),

    /** Category type for _Games & Hobbies_ */
    GAMES_AND_HOBBIES("Games & Hobbies"),

    /** Category type for _Government & Organizations_ */
    GOVERNMENT_AND_ORGANIZATIONS("Government & Organizations"),

    /** Category type for _Health_ */
    HEALTH("Health"),

    /** Category type for _Kids & Family_ */
    KIDS_AND_FAMILY("Kids & Family"),

    /** Category type for _Music_ */
    MUSIC("Music"),

    /** Category type for _News & Politics_ */
    NEWS_AND_POLITICS("News & Politics"),

    /** Category type for _Religion & Spirituality_ */
    RELIGION_AND_SPIRITUALITY("Religion & Spirituality"),

    /** Category type for _Science & Medicine_ */
    SCIENCE_AND_MEDICINE("Science & Medicine"),

    /** Category type for _Society & Culture_ */
    SOCIETY_AND_CULTURE("Society & Culture"),

    /** Category type for _Sports & Recreation_ */
    SPORTS_AND_RECREATION("Sports & Recreation"),

    /** Category type for _TV & Film_ */
    TV_AND_FILM("TV & Film"),

    /** Category type for _Technology_ */
    TECHNOLOGY("Technology");

    public companion object Factory : TypeFactory<GoogleplayCategory> {

        @JvmStatic
        override fun of(rawValue: String?): GoogleplayCategory? = rawValue?.let {
            values().find { t -> t.type.equals(it, ignoreCase = true) }
        }
    }
}
