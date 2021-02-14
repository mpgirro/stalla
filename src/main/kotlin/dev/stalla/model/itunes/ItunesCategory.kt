package dev.stalla.model.itunes

import dev.stalla.util.findItunesCategory

public sealed class ItunesCategory(public open val name: String) {

    /**
     * A simple iTunes-style category, without a nested subcategory:
     *
     * ```
     * <itunes:category text="News" />
     * ```
     *
     * Categories are defined in the [Apple Podcasts Categories](https://help.apple.com/itc/podcasts_connect/#/itc9267a2f12).
     * To define a nested subcategory, use [Simple].
     */
    public abstract class Simple protected constructor(override val name: String) : ItunesCategory(name)

    /**
     * An iTunes-style subcategory that is contained within a parent [Simple]:
     *
     * ```
     * <itunes:category text="News">
     *     <itunes:category text="Tech News" />
     * </itunes:category>
     * ```
     *
     * Categories and their hierarchy are defined in the [Apple Podcasts Categories](https://help.apple.com/itc/podcasts_connect/#/itc9267a2f12).
     *
     * @param parent The parent [Nested].
     */
    public abstract class Nested protected constructor(override val name: String, public val parent: Simple) : ItunesCategory(name)

    public companion object Factory {

        @JvmStatic
        public fun from(category: String?): ItunesCategory? = findItunesCategory(category)

        @JvmField
        public val Arts: Simple = object : Simple("Arts") {}

        @JvmField
        public val Books: Nested = object : Nested("Books", Arts) {}

        @JvmField
        public val Design: Nested = object : Nested("Design", Arts) {}

        @JvmField
        public val FashionAndBeauty: Nested = object : Nested("Fashion & Beauty", Arts) {}

        @JvmField
        public val Food: Nested = object : Nested("Food", Arts) {}

        @JvmField
        public val PerformingArts: Nested = object : Nested("Performing Arts", Arts) {}

        @JvmField
        public val VisualArts: Nested = object : Nested("Visual Arts", Arts) {}

        @JvmField
        public val Business: Simple = object : Simple("Business") {}

        @JvmField
        public val Careers: Nested = object : Nested("Careers", Business) {}

        @JvmField
        public val Entrepreneurship: Nested = object : Nested("Entrepreneurship", Business) {}

        @JvmField
        public val Investing: Nested = object : Nested("Investing", Business) {}

        @JvmField
        public val Management: Nested = object : Nested("Management", Business) {}

        @JvmField
        public val Marketing: Nested = object : Nested("Marketing", Business) {}

        @JvmField
        public val NonProfit: Nested = object : Nested("Non-Profit", Business) {}

        @JvmField
        public val Comedy: Simple = object : Simple("Comedy") {}

        @JvmField
        public val ComedyInterviews: Nested = object : Nested("Comedy Interviews", Comedy) {}

        @JvmField
        public val Improv: Nested = object : Nested("Improv", Comedy) {}

        @JvmField
        public val StandUp: Nested = object : Nested("Stand-Up", Comedy) {}

        @JvmField
        public val Education: Simple = object : Simple("Education") {}

        @JvmField
        public val Courses: Nested = object : Nested("Courses", Education) {}

        @JvmField
        public val HowTo: Nested = object : Nested("How To", Education) {}

        @JvmField
        public val LanguageLearning: Nested = object : Nested("Language Learning", Education) {}

        @JvmField
        public val SelfImprovement: Nested = object : Nested("Self-Improvement", Education) {}

        @JvmField
        public val Fiction: Simple = object : Simple("Fiction") {}

        @JvmField
        public val ComedyFiction: Nested = object : Nested("Comedy Fiction", Fiction) {}

        @JvmField
        public val Drama: Nested = object : Nested("Drama", Fiction) {}

        @JvmField
        public val ScienceFiction: Nested = object : Nested("Science Fiction", Fiction) {}

        @JvmField
        public val Government: Simple = object : Simple("Government") {}

        @JvmField
        public val History: Simple = object : Simple("History") {}

        @JvmField
        public val HealthAndFitness: Simple = object : Simple("Health & Fitness") {}

        @JvmField
        public val AlternativeHealth: Nested = object : Nested("Alternative Health", HealthAndFitness) {}

        @JvmField
        public val Fitness: Nested = object : Nested("Fitness", HealthAndFitness) {}

        @JvmField
        public val Medicine: Nested = object : Nested("Medicine", HealthAndFitness) {}

        @JvmField
        public val MentalHealth: Nested = object : Nested("Mental Health", HealthAndFitness) {}

        @JvmField
        public val Nutrition: Nested = object : Nested("Nutrition", HealthAndFitness) {}

        @JvmField
        public val Sexuality: Nested = object : Nested("Sexuality", HealthAndFitness) {}

        @JvmField
        public val KidsAndFamily: Simple = object : Simple("Kids & Family") {}

        @JvmField
        public val EducationForKids: Nested = object : Nested("Education for Kids", KidsAndFamily) {}

        @JvmField
        public val Parenting: Nested = object : Nested("Parenting", KidsAndFamily) {}

        @JvmField
        public val PetsAndAnimals: Nested = object : Nested("Pets & Animals", KidsAndFamily) {}

        @JvmField
        public val StoriesForKids: Nested = object : Nested("Stories for Kids", KidsAndFamily) {}

        @JvmField
        public val Leisure: Simple = object : Simple("Leisure") {}

        @JvmField
        public val AnimationAndManga: Nested = object : Nested("Animation & Manga", Leisure) {}

        @JvmField
        public val Automotive: Nested = object : Nested("Automotive", Leisure) {}

        @JvmField
        public val Aviation: Nested = object : Nested("Aviation", Leisure) {}

        @JvmField
        public val Crafts: Nested = object : Nested("Crafts", Leisure) {}

        @JvmField
        public val Games: Nested = object : Nested("Games", Leisure) {}

        @JvmField
        public val Hobbies: Nested = object : Nested("Hobbies", Leisure) {}

        @JvmField
        public val HomeAndGarden: Nested = object : Nested("Home & Garden", Leisure) {}

        @JvmField
        public val VideoGames: Nested = object : Nested("Video Games", Leisure) {}

        @JvmField
        public val Music: Simple = object : Simple("Music") {}

        @JvmField
        public val MusicCommentary: Nested = object : Nested("Music Commentary", Music) {}

        @JvmField
        public val MusicHistory: Nested = object : Nested("Music History", Music) {}

        @JvmField
        public val MusicInterviews: Nested = object : Nested("Music Interviews", Music) {}

        @JvmField
        public val News: Simple = object : Simple("News") {}

        @JvmField
        public val BusinessNews: Nested = object : Nested("Business News", News) {}

        @JvmField
        public val DailyNews: Nested = object : Nested("Daily News", News) {}

        @JvmField
        public val EntertainmentNews: Nested = object : Nested("Entertainment News", News) {}

        @JvmField
        public val NewsCommentary: Nested = object : Nested("News Commentary", News) {}

        @JvmField
        public val Politics: Nested = object : Nested("Politics", News) {}

        @JvmField
        public val SportsNews: Nested = object : Nested("Sports News", News) {}

        @JvmField
        public val TechNews: Nested = object : Nested("Tech News", News) {}

        @JvmField
        public val ReligionAndSpirituality: Simple = object : Simple("Religion & Spirituality") {}

        @JvmField
        public val Buddhism: Nested = object : Nested("Buddhism", ReligionAndSpirituality) {}

        @JvmField
        public val Christianity: Nested = object : Nested("Christianity", ReligionAndSpirituality) {}

        @JvmField
        public val Hinduism: Nested = object : Nested("Hinduism", ReligionAndSpirituality) {}

        @JvmField
        public val Islam: Nested = object : Nested("Islam", ReligionAndSpirituality) {}

        @JvmField
        public val Judaism: Nested = object : Nested("Judaism", ReligionAndSpirituality) {}

        @JvmField
        public val Religion: Nested = object : Nested("Religion", ReligionAndSpirituality) {}

        @JvmField
        public val Spirituality: Nested = object : Nested("Spirituality", ReligionAndSpirituality) {}

        @JvmField
        public val Science: Simple = object : Simple("Science") {}

        @JvmField
        public val Astronomy: Nested = object : Nested("Astronomy", Science) {}

        @JvmField
        public val Chemistry: Nested = object : Nested("Chemistry", Science) {}

        @JvmField
        public val EarthSciences: Nested = object : Nested("Earth Sciences", Science) {}

        @JvmField
        public val LifeSciences: Nested = object : Nested("Life Sciences", Science) {}

        @JvmField
        public val Mathematics: Nested = object : Nested("Mathematics", Science) {}

        @JvmField
        public val NaturalSciences: Nested = object : Nested("Natural Sciences", Science) {}

        @JvmField
        public val Nature: Nested = object : Nested("Nature", Science) {}

        @JvmField
        public val Physics: Nested = object : Nested("Physics", Science) {}

        @JvmField
        public val SocialSciences: Nested = object : Nested("Social Sciences", Science) {}

        @JvmField
        public val SocietyAndCulture: Simple = object : Simple("Society & Culture") {}

        @JvmField
        public val Documentary: Nested = object : Nested("Documentary", SocietyAndCulture) {}

        @JvmField
        public val PersonalJournals: Nested = object : Nested("Personal Journals", SocietyAndCulture) {}

        @JvmField
        public val Philosophy: Nested = object : Nested("Philosophy", SocietyAndCulture) {}

        @JvmField
        public val PlacesAndTravel: Nested = object : Nested("Places & Travel", SocietyAndCulture) {}

        @JvmField
        public val Relationships: Nested = object : Nested("Relationships", SocietyAndCulture) {}

        @JvmField
        public val Sports: Simple = object : Simple("Sports") {}

        @JvmField
        public val Baseball: Nested = object : Nested("Baseball", Sports) {}

        @JvmField
        public val Basketball: Nested = object : Nested("Basketball", Sports) {}

        @JvmField
        public val Cricket: Nested = object : Nested("Cricket", Sports) {}

        @JvmField
        public val FantasySports: Nested = object : Nested("Fantasy Sports", Sports) {}

        @JvmField
        public val Football: Nested = object : Nested("Football", Sports) {}

        @JvmField
        public val Golf: Nested = object : Nested("Golf", Sports) {}

        @JvmField
        public val Hockey: Nested = object : Nested("Hockey", Sports) {}

        @JvmField
        public val Rugby: Nested = object : Nested("Rugby", Sports) {}

        @JvmField
        public val Running: Nested = object : Nested("Running", Sports) {}

        @JvmField
        public val Soccer: Nested = object : Nested("Soccer", Sports) {}

        @JvmField
        public val Swimming: Nested = object : Nested("Swimming", Sports) {}

        @JvmField
        public val Tennis: Nested = object : Nested("Tennis", Sports) {}

        @JvmField
        public val Volleyball: Nested = object : Nested("Volleyball", Sports) {}

        @JvmField
        public val Wilderness: Nested = object : Nested("Wilderness", Sports) {}

        @JvmField
        public val Wrestling: Nested = object : Nested("Wrestling", Sports) {}

        @JvmField
        public val Technology: Simple = object : Simple("Technology") {}

        @JvmField
        public val TrueCrime: Simple = object : Simple("True Crime") {}

        @JvmField
        public val TvAndFilm: Simple = object : Simple("TV & Film") {}

        @JvmField
        public val AfterShows: Nested = object : Nested("After Shows", TvAndFilm) {}

        @JvmField
        public val FilmHistory: Nested = object : Nested("Film History", TvAndFilm) {}

        @JvmField
        public val FilmInterviews: Nested = object : Nested("Film Interviews", TvAndFilm) {}

        @JvmField
        public val FilmReviews: Nested = object : Nested("Film Reviews", TvAndFilm) {}

        @JvmField
        public val TvReviews: Nested = object : Nested("TV Reviews", TvAndFilm) {}
    }
}
