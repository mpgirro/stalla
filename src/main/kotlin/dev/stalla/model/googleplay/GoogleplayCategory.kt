package dev.stalla.model.googleplay

/**
 * An [Google Play `<category>` tag][https://support.google.com/googleplay/podcasts/answer/6260341#spt].
 *
 * @param type The string representation of the category.
 */
public enum class GoogleplayCategory(public val type: String) {

    /** Category type for _Arts_ */
    Arts("Arts"),

    /** Category type for _Business_ */
    Business("Business"),

    /** Category type for _Comedy_ */
    Comedy("Comedy"),

    /** Category type for _Education_ */
    Education("Education"),

    /** Category type for _Games & Hobbies_ */
    GamesAndHobbies("Games & Hobbies"),

    /** Category type for _Government & Organizations_ */
    GovernmentAndOrganizations("Government & Organizations"),

    /** Category type for _Health_ */
    Health("Health"),

    /** Category type for _Kids & Family_ */
    KidsAndFamily("Kids & Family"),

    /** Category type for _Music_ */
    Music("Music"),

    /** Category type for _News & Politics_ */
    NewsAndPolitics("News & Politics"),

    /** Category type for _Religion & Spirituality_ */
    ReligionAndSpirituality("Religion & Spirituality"),

    /** Category type for _Science & Medicine_ */
    ScienceAndMedicine("Science & Medicine"),

    /** Category type for _Society & Culture_ */
    SocietyAndCulture("Society & Culture"),

    /** Category type for _Sports & Recreation_ */
    SportsAndRecreation("Sports & Recreation"),

    /** Category type for _TV & Film_ */
    TvAndFilm("TV & Film"),

    /** Category type for _Technology_ */
    Technology("Technology");

    public companion object Factory {

        /**
         * Factory method for the instance of the [GoogleplayCategory] matching the [type] parameter.
         *
         * @param type The string representation of the [GoogleplayCategory] instance.
         * @return The [GoogleplayCategory] instance matching [type], or null if no matching instance exists.
         */
        public fun from(type: String?): GoogleplayCategory? = type?.let {
            values().find { t -> t.type.equals(it, ignoreCase = true) }
        }
    }
}
