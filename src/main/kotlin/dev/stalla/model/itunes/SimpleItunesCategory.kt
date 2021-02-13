package dev.stalla.model.itunes

/**
 * A simple iTunes-style category, without a nested subcategory:
 *
 * ```
 * <itunes:category text="News" />
 * ```
 */
public enum class SimpleItunesCategory(public override val value: String) : ItunesCategory {
    ARTS("Arts"),
    BUSINESS("Business"),
    COMEDY("Comedy"),
    EDUCATION("Education"),
    FICTION("Fiction"),
    GOVERNMENT("Government"),
    HISTORY("History"),
    HEALTH_AND_FITNESS("Health &amp; Fitness"),
    KIDS_AND_FAMILY("Kids &amp; Family"),
    LEISURE("Leisure"),
    MUSIC("Music"),
    NEWS("News"),
    RELIGION_AND_SPIRITUALITY("Religion &amp; Spirituality"),
    SCIENCE("Science"),
    SOCIETY_AND_CULTURE("Society &amp; Culture"),
    SPORTS("Sports"),
    TECHNOLOGY("Technology"),
    TRUE_CRIME("True Crime"),
    TV_AND_FILM("TV &amp; Film")
}
