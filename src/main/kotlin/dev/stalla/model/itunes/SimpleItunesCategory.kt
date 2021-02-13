package dev.stalla.model.itunes

/**
 * A simple iTunes-style category, without a nested subcategory:
 *
 * ```
 * <itunes:category text="News" />
 * ```
 *
 * Categories are defined in the [Apple Podcasts Categories](https://help.apple.com/itc/podcasts_connect/#/itc9267a2f12).
 * To define a nested subcategory, use [NestedItunesCategory].
 */
public enum class SimpleItunesCategory(public override val categoryName: String) : ItunesCategory {
    ARTS("Arts"),
    BUSINESS("Business"),
    COMEDY("Comedy"),
    EDUCATION("Education"),
    FICTION("Fiction"),
    GOVERNMENT("Government"),
    HISTORY("History"),
    HEALTH_AND_FITNESS("Health & Fitness"),
    KIDS_AND_FAMILY("Kids & Family"),
    LEISURE("Leisure"),
    MUSIC("Music"),
    NEWS("News"),
    RELIGION_AND_SPIRITUALITY("Religion & Spirituality"),
    SCIENCE("Science"),
    SOCIETY_AND_CULTURE("Society & Culture"),
    SPORTS("Sports"),
    TECHNOLOGY("Technology"),
    TRUE_CRIME("True Crime"),
    TV_AND_FILM("TV & Film");

    public companion object Factory : ItunesCategoryFactory {

        private val categoryMap: Map<String, ItunesCategory> = values().map { category ->
            category.categoryName.toLowerCase() to category
        }.toMap()

        @JvmStatic
        public override fun from(category: String?): ItunesCategory? = category?.let {
            return categoryMap[it.toLowerCase()]
        }
    }
}
