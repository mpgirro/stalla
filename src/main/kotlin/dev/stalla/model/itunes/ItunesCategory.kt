package dev.stalla.model.itunes

import dev.stalla.model.itunes.ItunesCategory.Factory.AfterShows
import dev.stalla.model.itunes.ItunesCategory.Factory.AlternativeHealth
import dev.stalla.model.itunes.ItunesCategory.Factory.AnimationAndManga
import dev.stalla.model.itunes.ItunesCategory.Factory.Arts
import dev.stalla.model.itunes.ItunesCategory.Factory.Astronomy
import dev.stalla.model.itunes.ItunesCategory.Factory.Automotive
import dev.stalla.model.itunes.ItunesCategory.Factory.Aviation
import dev.stalla.model.itunes.ItunesCategory.Factory.Baseball
import dev.stalla.model.itunes.ItunesCategory.Factory.Basketball
import dev.stalla.model.itunes.ItunesCategory.Factory.Books
import dev.stalla.model.itunes.ItunesCategory.Factory.Buddhism
import dev.stalla.model.itunes.ItunesCategory.Factory.Business
import dev.stalla.model.itunes.ItunesCategory.Factory.BusinessNews
import dev.stalla.model.itunes.ItunesCategory.Factory.Careers
import dev.stalla.model.itunes.ItunesCategory.Factory.Chemistry
import dev.stalla.model.itunes.ItunesCategory.Factory.Christianity
import dev.stalla.model.itunes.ItunesCategory.Factory.Comedy
import dev.stalla.model.itunes.ItunesCategory.Factory.ComedyFiction
import dev.stalla.model.itunes.ItunesCategory.Factory.ComedyInterviews
import dev.stalla.model.itunes.ItunesCategory.Factory.Courses
import dev.stalla.model.itunes.ItunesCategory.Factory.Crafts
import dev.stalla.model.itunes.ItunesCategory.Factory.Cricket
import dev.stalla.model.itunes.ItunesCategory.Factory.DailyNews
import dev.stalla.model.itunes.ItunesCategory.Factory.Design
import dev.stalla.model.itunes.ItunesCategory.Factory.Documentary
import dev.stalla.model.itunes.ItunesCategory.Factory.Drama
import dev.stalla.model.itunes.ItunesCategory.Factory.EarthSciences
import dev.stalla.model.itunes.ItunesCategory.Factory.Education
import dev.stalla.model.itunes.ItunesCategory.Factory.EducationForKids
import dev.stalla.model.itunes.ItunesCategory.Factory.EntertainmentNews
import dev.stalla.model.itunes.ItunesCategory.Factory.Entrepreneurship
import dev.stalla.model.itunes.ItunesCategory.Factory.FantasySports
import dev.stalla.model.itunes.ItunesCategory.Factory.FashionAndBeauty
import dev.stalla.model.itunes.ItunesCategory.Factory.Fiction
import dev.stalla.model.itunes.ItunesCategory.Factory.FilmHistory
import dev.stalla.model.itunes.ItunesCategory.Factory.FilmInterviews
import dev.stalla.model.itunes.ItunesCategory.Factory.FilmReviews
import dev.stalla.model.itunes.ItunesCategory.Factory.Fitness
import dev.stalla.model.itunes.ItunesCategory.Factory.Food
import dev.stalla.model.itunes.ItunesCategory.Factory.Football
import dev.stalla.model.itunes.ItunesCategory.Factory.Games
import dev.stalla.model.itunes.ItunesCategory.Factory.Golf
import dev.stalla.model.itunes.ItunesCategory.Factory.Government
import dev.stalla.model.itunes.ItunesCategory.Factory.HealthAndFitness
import dev.stalla.model.itunes.ItunesCategory.Factory.Hinduism
import dev.stalla.model.itunes.ItunesCategory.Factory.History
import dev.stalla.model.itunes.ItunesCategory.Factory.Hobbies
import dev.stalla.model.itunes.ItunesCategory.Factory.Hockey
import dev.stalla.model.itunes.ItunesCategory.Factory.HomeAndGarden
import dev.stalla.model.itunes.ItunesCategory.Factory.HowTo
import dev.stalla.model.itunes.ItunesCategory.Factory.Improv
import dev.stalla.model.itunes.ItunesCategory.Factory.Investing
import dev.stalla.model.itunes.ItunesCategory.Factory.Islam
import dev.stalla.model.itunes.ItunesCategory.Factory.Judaism
import dev.stalla.model.itunes.ItunesCategory.Factory.KidsAndFamily
import dev.stalla.model.itunes.ItunesCategory.Factory.LanguageLearning
import dev.stalla.model.itunes.ItunesCategory.Factory.Leisure
import dev.stalla.model.itunes.ItunesCategory.Factory.LifeSciences
import dev.stalla.model.itunes.ItunesCategory.Factory.Management
import dev.stalla.model.itunes.ItunesCategory.Factory.Marketing
import dev.stalla.model.itunes.ItunesCategory.Factory.Mathematics
import dev.stalla.model.itunes.ItunesCategory.Factory.Medicine
import dev.stalla.model.itunes.ItunesCategory.Factory.MentalHealth
import dev.stalla.model.itunes.ItunesCategory.Factory.Music
import dev.stalla.model.itunes.ItunesCategory.Factory.MusicCommentary
import dev.stalla.model.itunes.ItunesCategory.Factory.MusicHistory
import dev.stalla.model.itunes.ItunesCategory.Factory.MusicInterviews
import dev.stalla.model.itunes.ItunesCategory.Factory.NaturalSciences
import dev.stalla.model.itunes.ItunesCategory.Factory.Nature
import dev.stalla.model.itunes.ItunesCategory.Factory.News
import dev.stalla.model.itunes.ItunesCategory.Factory.NewsCommentary
import dev.stalla.model.itunes.ItunesCategory.Factory.NonProfit
import dev.stalla.model.itunes.ItunesCategory.Factory.Nutrition
import dev.stalla.model.itunes.ItunesCategory.Factory.Parenting
import dev.stalla.model.itunes.ItunesCategory.Factory.PerformingArts
import dev.stalla.model.itunes.ItunesCategory.Factory.PersonalJournals
import dev.stalla.model.itunes.ItunesCategory.Factory.PetsAndAnimals
import dev.stalla.model.itunes.ItunesCategory.Factory.Philosophy
import dev.stalla.model.itunes.ItunesCategory.Factory.Physics
import dev.stalla.model.itunes.ItunesCategory.Factory.PlacesAndTravel
import dev.stalla.model.itunes.ItunesCategory.Factory.Politics
import dev.stalla.model.itunes.ItunesCategory.Factory.Relationships
import dev.stalla.model.itunes.ItunesCategory.Factory.Religion
import dev.stalla.model.itunes.ItunesCategory.Factory.ReligionAndSpirituality
import dev.stalla.model.itunes.ItunesCategory.Factory.Rugby
import dev.stalla.model.itunes.ItunesCategory.Factory.Running
import dev.stalla.model.itunes.ItunesCategory.Factory.Science
import dev.stalla.model.itunes.ItunesCategory.Factory.ScienceFiction
import dev.stalla.model.itunes.ItunesCategory.Factory.SelfImprovement
import dev.stalla.model.itunes.ItunesCategory.Factory.Sexuality
import dev.stalla.model.itunes.ItunesCategory.Factory.Soccer
import dev.stalla.model.itunes.ItunesCategory.Factory.SocialSciences
import dev.stalla.model.itunes.ItunesCategory.Factory.SocietyAndCulture
import dev.stalla.model.itunes.ItunesCategory.Factory.Spirituality
import dev.stalla.model.itunes.ItunesCategory.Factory.Sports
import dev.stalla.model.itunes.ItunesCategory.Factory.SportsNews
import dev.stalla.model.itunes.ItunesCategory.Factory.StandUp
import dev.stalla.model.itunes.ItunesCategory.Factory.StoriesForKids
import dev.stalla.model.itunes.ItunesCategory.Factory.Swimming
import dev.stalla.model.itunes.ItunesCategory.Factory.TechNews
import dev.stalla.model.itunes.ItunesCategory.Factory.Technology
import dev.stalla.model.itunes.ItunesCategory.Factory.Tennis
import dev.stalla.model.itunes.ItunesCategory.Factory.TrueCrime
import dev.stalla.model.itunes.ItunesCategory.Factory.TvAndFilm
import dev.stalla.model.itunes.ItunesCategory.Factory.TvReviews
import dev.stalla.model.itunes.ItunesCategory.Factory.VideoGames
import dev.stalla.model.itunes.ItunesCategory.Factory.VisualArts
import dev.stalla.model.itunes.ItunesCategory.Factory.Volleyball
import dev.stalla.model.itunes.ItunesCategory.Factory.Wilderness
import dev.stalla.model.itunes.ItunesCategory.Factory.Wrestling
import kotlin.reflect.KVisibility
import kotlin.reflect.full.declaredMemberProperties

/**
 * Model for the defined values encountered within the `<itunes:category>`
 * element within a `<channel>` element.
 *
 * The following values are available:
 *
 * | Category                | Subcategory        | Property                  |
 * |-------------------------|--------------------|---------------------------|
 * | Arts                    | –                  | [Arts]                    |
 * | Arts                    | Books              | [Books]                   |
 * | Arts                    | Design             | [Design]                  |
 * | Arts                    | Fashion & Beauty   | [FashionAndBeauty]        |
 * | Arts                    | Food               | [Food]                    |
 * | Arts                    | Performing Arts    | [PerformingArts]          |
 * | Arts                    | Visual Arts        | [VisualArts]              |
 * | Business                | –                  | [Business]                |
 * | Business                | Careers            | [Careers]                 |
 * | Business                | Entrepreneurship   | [Entrepreneurship]        |
 * | Business                | Investing          | [Investing]               |
 * | Business                | Management         | [Management]              |
 * | Business                | Marketing          | [Marketing]               |
 * | Business                | Non-Profit         | [NonProfit]               |
 * | Comedy                  | –                  | [Comedy]                  |
 * | Comedy                  | Comedy Interviews  | [ComedyInterviews]        |
 * | Comedy                  | Improv             | [Improv]                  |
 * | Comedy                  | Stand-Up           | [StandUp]                 |
 * | Education               | –                  | [Education]               |
 * | Education               | Courses            | [Courses]                 |
 * | Education               | How To             | [HowTo]                   |
 * | Education               | Language Learning  | [LanguageLearning]        |
 * | Education               | Self-Improvement   | [SelfImprovement]         |
 * | Fiction                 | –                  | [Fiction]                 |
 * | Fiction                 | Comedy Fiction     | [ComedyFiction]           |
 * | Fiction                 | Drama              | [Drama]                   |
 * | Fiction                 | Science Fiction    | [ScienceFiction]          |
 * | Government              | –                  | [Government]              |
 * | History                 | –                  | [History]                 |
 * | Health & Fitness        | –                  | [HealthAndFitness]        |
 * | Health & Fitness        | Alternative Health | [AlternativeHealth]       |
 * | Health & Fitness        | Fitness            | [Fitness]                 |
 * | Health & Fitness        | Medicine           | [Medicine]                |
 * | Health & Fitness        | Mental Health      | [MentalHealth]            |
 * | Health & Fitness        | Nutrition          | [Nutrition]               |
 * | Health & Fitness        | Sexuality          | [Sexuality]               |
 * | Kids & Family           | –                  | [KidsAndFamily]           |
 * | Kids & Family           | Education for Kids | [EducationForKids]        |
 * | Kids & Family           | Parenting          | [Parenting]               |
 * | Kids & Family           | Pets & Animals     | [PetsAndAnimals]          |
 * | Kids & Family           | Stories for Kids   | [StoriesForKids]          |
 * | Leisure                 | –                  | [Leisure]                 |
 * | Leisure                 | Animation & Manga  | [AnimationAndManga]       |
 * | Leisure                 | Automotive         | [Automotive]              |
 * | Leisure                 | Aviation           | [Aviation]                |
 * | Leisure                 | Crafts             | [Crafts]                  |
 * | Leisure                 | Games              | [Games]                   |
 * | Leisure                 | Hobbies            | [Hobbies]                 |
 * | Leisure                 | Home & Garden      | [HomeAndGarden]           |
 * | Leisure                 | Video Games        | [VideoGames]              |
 * | Music                   | –                  | [Music]                   |
 * | Music                   | Music Commentary   | [MusicCommentary]         |
 * | Music                   | Music History      | [MusicHistory]            |
 * | Music                   | Music Interviews   | [MusicInterviews]         |
 * | News                    | –                  | [News]                    |
 * | News                    | Business News      | [BusinessNews]            |
 * | News                    | Daily News         | [DailyNews]               |
 * | News                    | Entertainment News | [EntertainmentNews]       |
 * | News                    | News Commentary    | [NewsCommentary]          |
 * | News                    | Politics           | [Politics]                |
 * | News                    | Sports News        | [SportsNews]              |
 * | News                    | Tech News          | [TechNews]                |
 * | Religion & Spirituality | –                  | [ReligionAndSpirituality] |
 * | Religion & Spirituality | Buddhism           | [Buddhism]                |
 * | Religion & Spirituality | Christianity       | [Christianity]            |
 * | Religion & Spirituality | Hinduism           | [Hinduism]                |
 * | Religion & Spirituality | Islam              | [Islam]                   |
 * | Religion & Spirituality | Judaism            | [Judaism]                 |
 * | Religion & Spirituality | Religion           | [Religion]                |
 * | Religion & Spirituality | Spirituality       | [Spirituality]            |
 * | Science                 | –                  | [Science]                 |
 * | Science                 | Astronomy          | [Astronomy]               |
 * | Science                 | Chemistry          | [Chemistry]               |
 * | Science                 | Earth Sciences     | [EarthSciences]           |
 * | Science                 | Life Sciences      | [LifeSciences]            |
 * | Science                 | Mathematics        | [Mathematics]             |
 * | Science                 | Natural Sciences   | [NaturalSciences]         |
 * | Science                 | Nature             | [Nature]                  |
 * | Science                 | Physics            | [Physics]                 |
 * | Science                 | Social Sciences    | [SocialSciences]          |
 * | Society & Culture       | –                  | [SocietyAndCulture]       |
 * | Society & Culture       | Documentary        | [Documentary]             |
 * | Society & Culture       | Personal Journals  | [PersonalJournals]        |
 * | Society & Culture       | Philosophy         | [Philosophy]              |
 * | Society & Culture       | Places & Travel    | [PlacesAndTravel]         |
 * | Society & Culture       | Relationships      | [Relationships]           |
 * | Sports                  | –                  | [Sports]                  |
 * | Sports                  | Baseball           | [Baseball]                |
 * | Sports                  | Basketball         | [Basketball]              |
 * | Sports                  | Cricket            | [Cricket]                 |
 * | Sports                  | Fantasy Sports     | [FantasySports]           |
 * | Sports                  | Football           | [Football]                |
 * | Sports                  | Golf               | [Golf]                    |
 * | Sports                  | Hockey             | [Hockey]                  |
 * | Sports                  | Rugby              | [Rugby]                   |
 * | Sports                  | Running            | [Running]                 |
 * | Sports                  | Soccer             | [Soccer]                  |
 * | Sports                  | Swimming           | [Swimming]                |
 * | Sports                  | Tennis             | [Tennis]                  |
 * | Sports                  | Volleyball         | [Volleyball]              |
 * | Sports                  | Wilderness         | [Wilderness]              |
 * | Sports                  | Wrestling          | [Wrestling]               |
 * | Technology              | –                  | [Technology]              |
 * | True Crime              | –                  | [TrueCrime]               |
 * | TV & Film               | –                  | [TvAndFilm]               |
 * | TV & Film               | After Shows        | [AfterShows]              |
 * | TV & Film               | Film History       | [FilmHistory]             |
 * | TV & Film               | Film Interviews    | [FilmInterviews]          |
 * | TV & Film               | Film Reviews       | [FilmReviews]             |
 * | TV & Film               | TV Reviews         | [TvReviews]               |
 */
public sealed class ItunesCategory(public open val name: String) {

    /**
     * A simple iTunes-style category, without a nested subcategory:
     *
     * ```
     * <itunes:category text="News" />
     * ```
     *
     * Categories are defined in the [Apple Podcasts Categories](https://help.apple.com/itc/podcasts_connect/#/itc9267a2f12).
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
     * @param parent The parent [Simple].
     */
    public abstract class Nested protected constructor(override val name: String, public val parent: Simple) : ItunesCategory(name)

    public companion object Factory {

        private val categoryMap: Map<String, ItunesCategory> by lazy {
            val categories: List<ItunesCategory> = Factory::class.declaredMemberProperties.mapNotNull { member ->
                var category: ItunesCategory? = null
                if (member.visibility == KVisibility.PUBLIC) {
                    val value = member.getter.call(this)
                    if (value is ItunesCategory) {
                        category = value
                    }
                }
                category
            }
            categories.associateBy({ it.name.toLowerCase() }, { it })
        }

        /**
         * Factory method for the instance of the [ItunesCategory] matching the [name] parameter.
         *
         * @param name The string representation of the [ItunesCategory] instance.
         * @return The [ItunesCategory] instance matching [name], or null if no matching instance exists.
         */
        @JvmStatic
        public fun from(name: String?): ItunesCategory? = name?.let {
            return categoryMap[it.toLowerCase()]
        }

        /** Category type for _Arts_ */
        @JvmField
        public val Arts: Simple = object : Simple("Arts") {}

        /** Category type for _Books_ nested in the [Arts] parent category */
        @JvmField
        public val Books: Nested = object : Nested("Books", Arts) {}

        /** Category type for _Design_ nested in the [Arts] parent category */
        @JvmField
        public val Design: Nested = object : Nested("Design", Arts) {}

        /** Category type for _Fashion & Beauty_ nested in the [Arts] parent category */
        @JvmField
        public val FashionAndBeauty: Nested = object : Nested("Fashion & Beauty", Arts) {}

        /** Category type for _Food_ nested in the [Arts] parent category */
        @JvmField
        public val Food: Nested = object : Nested("Food", Arts) {}

        /** Category type for _Performing Arts_ nested in the [Arts] parent category */
        @JvmField
        public val PerformingArts: Nested = object : Nested("Performing Arts", Arts) {}

        /** Category type for _Visual Arts_ nested in the [Arts] parent category */
        @JvmField
        public val VisualArts: Nested = object : Nested("Visual Arts", Arts) {}

        /** Category type for _Business_ */
        @JvmField
        public val Business: Simple = object : Simple("Business") {}

        /** Category type for _Careers_ nested in the [Business] parent category */
        @JvmField
        public val Careers: Nested = object : Nested("Careers", Business) {}

        /** Category type for _Entrepreneurship_ nested in the [Business] parent category */
        @JvmField
        public val Entrepreneurship: Nested = object : Nested("Entrepreneurship", Business) {}

        /** Category type for _Investing_ nested in the [Business] parent category */
        @JvmField
        public val Investing: Nested = object : Nested("Investing", Business) {}

        /** Category type for _Management_ nested in the [Business] parent category */
        @JvmField
        public val Management: Nested = object : Nested("Management", Business) {}

        /** Category type for _Marketing_ nested in the [Business] parent category */
        @JvmField
        public val Marketing: Nested = object : Nested("Marketing", Business) {}

        /** Category type for _Non-Profit_ nested in the [Business] parent category */
        @JvmField
        public val NonProfit: Nested = object : Nested("Non-Profit", Business) {}

        /** Category type for _Comedy_ */
        @JvmField
        public val Comedy: Simple = object : Simple("Comedy") {}

        /** Category type for _Comedy Interviews_ nested in the [Comedy] parent category */
        @JvmField
        public val ComedyInterviews: Nested = object : Nested("Comedy Interviews", Comedy) {}

        /** Category type for _Improv_ nested in the [Comedy] parent category */
        @JvmField
        public val Improv: Nested = object : Nested("Improv", Comedy) {}

        /** Category type for _Stand-Up_ nested in the [Comedy] parent category */
        @JvmField
        public val StandUp: Nested = object : Nested("Stand-Up", Comedy) {}

        /** Category type for _Education_ */
        @JvmField
        public val Education: Simple = object : Simple("Education") {}

        /** Category type for _Courses_ nested in the [Education] parent category */
        @JvmField
        public val Courses: Nested = object : Nested("Courses", Education) {}

        /** Category type for _HowTo_ nested in the [Education] parent category */
        @JvmField
        public val HowTo: Nested = object : Nested("How To", Education) {}

        /** Category type for _Language Learning_ nested in the [Education] parent category */
        @JvmField
        public val LanguageLearning: Nested = object : Nested("Language Learning", Education) {}

        /** Category type for _Self-Improvement_ nested in the [Education] parent category */
        @JvmField
        public val SelfImprovement: Nested = object : Nested("Self-Improvement", Education) {}

        /** Category type for _Fiction_ */
        @JvmField
        public val Fiction: Simple = object : Simple("Fiction") {}

        /** Category type for _Comedy Fiction_ nested in the [Fiction] parent category */
        @JvmField
        public val ComedyFiction: Nested = object : Nested("Comedy Fiction", Fiction) {}

        /** Category type for _Drama_ nested in the [Fiction] parent category */
        @JvmField
        public val Drama: Nested = object : Nested("Drama", Fiction) {}

        /** Category type for _Science Fiction_ nested in the [Fiction] parent category */
        @JvmField
        public val ScienceFiction: Nested = object : Nested("Science Fiction", Fiction) {}

        /** Category type for _Government_ */
        @JvmField
        public val Government: Simple = object : Simple("Government") {}

        /** Category type for _History_ */
        @JvmField
        public val History: Simple = object : Simple("History") {}

        /** Category type for _Health & Fitness_ */
        @JvmField
        public val HealthAndFitness: Simple = object : Simple("Health & Fitness") {}

        /** Category type for _Alternative Health_ nested in the [HealthAndFitness] parent category */
        @JvmField
        public val AlternativeHealth: Nested = object : Nested("Alternative Health", HealthAndFitness) {}

        /** Category type for _Fitness_ nested in the [HealthAndFitness] parent category */
        @JvmField
        public val Fitness: Nested = object : Nested("Fitness", HealthAndFitness) {}

        /** Category type for _Medicine_ nested in the [HealthAndFitness] parent category */
        @JvmField
        public val Medicine: Nested = object : Nested("Medicine", HealthAndFitness) {}

        /** Category type for _Mental Health_ nested in the [HealthAndFitness] parent category */
        @JvmField
        public val MentalHealth: Nested = object : Nested("Mental Health", HealthAndFitness) {}

        /** Category type for _Nutrition_ nested in the [HealthAndFitness] parent category */
        @JvmField
        public val Nutrition: Nested = object : Nested("Nutrition", HealthAndFitness) {}

        /** Category type for _Sexuality_ nested in the [HealthAndFitness] parent category */
        @JvmField
        public val Sexuality: Nested = object : Nested("Sexuality", HealthAndFitness) {}

        /** Category type for _Kids & Family_ */
        @JvmField
        public val KidsAndFamily: Simple = object : Simple("Kids & Family") {}

        /** Category type for _Education for Kids_ nested in the [KidsAndFamily] parent category */
        @JvmField
        public val EducationForKids: Nested = object : Nested("Education for Kids", KidsAndFamily) {}

        /** Category type for _Parentings_ nested in the [KidsAndFamily] parent category */
        @JvmField
        public val Parenting: Nested = object : Nested("Parenting", KidsAndFamily) {}

        /** Category type for _Pets & Animals_ nested in the [KidsAndFamily] parent category */
        @JvmField
        public val PetsAndAnimals: Nested = object : Nested("Pets & Animals", KidsAndFamily) {}

        /** Category type for _Stories for Kids_ nested in the [KidsAndFamily] parent category */
        @JvmField
        public val StoriesForKids: Nested = object : Nested("Stories for Kids", KidsAndFamily) {}

        /** Category type for _Leisure_ */
        @JvmField
        public val Leisure: Simple = object : Simple("Leisure") {}

        /** Category type for _Animation & Manga_ nested in the [Leisure] parent category */
        @JvmField
        public val AnimationAndManga: Nested = object : Nested("Animation & Manga", Leisure) {}

        /** Category type for _Automotive_ nested in the [Leisure] parent category */
        @JvmField
        public val Automotive: Nested = object : Nested("Automotive", Leisure) {}

        /** Category type for _Aviation_ nested in the [Leisure] parent category */
        @JvmField
        public val Aviation: Nested = object : Nested("Aviation", Leisure) {}

        /** Category type for _Crafts_ nested in the [Leisure] parent category */
        @JvmField
        public val Crafts: Nested = object : Nested("Crafts", Leisure) {}

        /** Category type for _Games_ nested in the [Leisure] parent category */
        @JvmField
        public val Games: Nested = object : Nested("Games", Leisure) {}

        /** Category type for _Hobbies_ nested in the [Leisure] parent category */
        @JvmField
        public val Hobbies: Nested = object : Nested("Hobbies", Leisure) {}

        /** Category type for _Home & Garden_ nested in the [Leisure] parent category */
        @JvmField
        public val HomeAndGarden: Nested = object : Nested("Home & Garden", Leisure) {}

        /** Category type for _Video Games_ nested in the [Leisure] parent category */
        @JvmField
        public val VideoGames: Nested = object : Nested("Video Games", Leisure) {}

        /** Category type for _Music_ */
        @JvmField
        public val Music: Simple = object : Simple("Music") {}

        /** Category type for _Music Commentary_ nested in the [Music] parent category */
        @JvmField
        public val MusicCommentary: Nested = object : Nested("Music Commentary", Music) {}

        /** Category type for _Music History_ nested in the [Music] parent category */
        @JvmField
        public val MusicHistory: Nested = object : Nested("Music History", Music) {}

        /** Category type for _Music Interviews_ nested in the [Music] parent category */
        @JvmField
        public val MusicInterviews: Nested = object : Nested("Music Interviews", Music) {}

        /** Category type for _News_ */
        @JvmField
        public val News: Simple = object : Simple("News") {}

        /** Category type for _Business News_ nested in the [News] parent category */
        @JvmField
        public val BusinessNews: Nested = object : Nested("Business News", News) {}

        /** Category type for _Daily News_ nested in the [News] parent category */
        @JvmField
        public val DailyNews: Nested = object : Nested("Daily News", News) {}

        /** Category type for _Entertainment News_ nested in the [News] parent category */
        @JvmField
        public val EntertainmentNews: Nested = object : Nested("Entertainment News", News) {}

        /** Category type for _News Commentary_ nested in the [News] parent category */
        @JvmField
        public val NewsCommentary: Nested = object : Nested("News Commentary", News) {}

        /** Category type for _Politics_ nested in the [News] parent category */
        @JvmField
        public val Politics: Nested = object : Nested("Politics", News) {}

        /** Category type for _Sports News_ nested in the [News] parent category */
        @JvmField
        public val SportsNews: Nested = object : Nested("Sports News", News) {}

        /** Category type for _Tech News_ nested in the [News] parent category */
        @JvmField
        public val TechNews: Nested = object : Nested("Tech News", News) {}

        /** Category type for _Religion & Spirituality_ */
        @JvmField
        public val ReligionAndSpirituality: Simple = object : Simple("Religion & Spirituality") {}

        /** Category type for _Buddhism_ nested in the [ReligionAndSpirituality] parent category */
        @JvmField
        public val Buddhism: Nested = object : Nested("Buddhism", ReligionAndSpirituality) {}

        /** Category type for _Christianity_ nested in the [ReligionAndSpirituality] parent category */
        @JvmField
        public val Christianity: Nested = object : Nested("Christianity", ReligionAndSpirituality) {}

        /** Category type for _Hinduism_ nested in the [ReligionAndSpirituality] parent category */
        @JvmField
        public val Hinduism: Nested = object : Nested("Hinduism", ReligionAndSpirituality) {}

        /** Category type for _Islam_ nested in the [ReligionAndSpirituality] parent category */
        @JvmField
        public val Islam: Nested = object : Nested("Islam", ReligionAndSpirituality) {}

        /** Category type for _Judaism_ nested in the [ReligionAndSpirituality] parent category */
        @JvmField
        public val Judaism: Nested = object : Nested("Judaism", ReligionAndSpirituality) {}

        /** Category type for _Religion_ nested in the [ReligionAndSpirituality] parent category */
        @JvmField
        public val Religion: Nested = object : Nested("Religion", ReligionAndSpirituality) {}

        /** Category type for _Spirituality_ nested in the [ReligionAndSpirituality] parent category */
        @JvmField
        public val Spirituality: Nested = object : Nested("Spirituality", ReligionAndSpirituality) {}

        /** Category type for _Science_ */
        @JvmField
        public val Science: Simple = object : Simple("Science") {}

        /** Category type for _Astronomy_ nested in the [Science] parent category */
        @JvmField
        public val Astronomy: Nested = object : Nested("Astronomy", Science) {}

        /** Category type for _Chemistry_ nested in the [Science] parent category */
        @JvmField
        public val Chemistry: Nested = object : Nested("Chemistry", Science) {}

        /** Category type for _Earth Sciences_ nested in the [Science] parent category */
        @JvmField
        public val EarthSciences: Nested = object : Nested("Earth Sciences", Science) {}

        /** Category type for _Life Sciences_ nested in the [Science] parent category */
        @JvmField
        public val LifeSciences: Nested = object : Nested("Life Sciences", Science) {}

        /** Category type for _Mathematics_ nested in the [Science] parent category */
        @JvmField
        public val Mathematics: Nested = object : Nested("Mathematics", Science) {}

        /** Category type for _Natural Sciences_ nested in the [Science] parent category */
        @JvmField
        public val NaturalSciences: Nested = object : Nested("Natural Sciences", Science) {}

        /** Category type for _Nature_ nested in the [Science] parent category */
        @JvmField
        public val Nature: Nested = object : Nested("Nature", Science) {}

        /** Category type for _Physics_ nested in the [Science] parent category */
        @JvmField
        public val Physics: Nested = object : Nested("Physics", Science) {}

        /** Category type for _Social Sciences_ nested in the [Science] parent category */
        @JvmField
        public val SocialSciences: Nested = object : Nested("Social Sciences", Science) {}

        /** Category type for _Society & Culture_ */
        @JvmField
        public val SocietyAndCulture: Simple = object : Simple("Society & Culture") {}

        /** Category type for _Documentary_ nested in the [SocietyAndCulture] parent category */
        @JvmField
        public val Documentary: Nested = object : Nested("Documentary", SocietyAndCulture) {}

        /** Category type for _Personal Journals_ nested in the [SocietyAndCulture] parent category */
        @JvmField
        public val PersonalJournals: Nested = object : Nested("Personal Journals", SocietyAndCulture) {}

        /** Category type for _Philosophy_ nested in the [SocietyAndCulture] parent category */
        @JvmField
        public val Philosophy: Nested = object : Nested("Philosophy", SocietyAndCulture) {}

        /** Category type for _Places & Travel_ nested in the [SocietyAndCulture] parent category */
        @JvmField
        public val PlacesAndTravel: Nested = object : Nested("Places & Travel", SocietyAndCulture) {}

        /** Category type for _Relationships_ nested in the [SocietyAndCulture] parent category */
        @JvmField
        public val Relationships: Nested = object : Nested("Relationships", SocietyAndCulture) {}

        /** Category type for _Sports_ */
        @JvmField
        public val Sports: Simple = object : Simple("Sports") {}

        /** Category type for _Baseball_ nested in the [Sports] parent category */
        @JvmField
        public val Baseball: Nested = object : Nested("Baseball", Sports) {}

        /** Category type for _Basketball_ nested in the [Sports] parent category */
        @JvmField
        public val Basketball: Nested = object : Nested("Basketball", Sports) {}

        /** Category type for _Cricket_ nested in the [Sports] parent category */
        @JvmField
        public val Cricket: Nested = object : Nested("Cricket", Sports) {}

        /** Category type for _Fantasy Sports_ nested in the [Sports] parent category */
        @JvmField
        public val FantasySports: Nested = object : Nested("Fantasy Sports", Sports) {}

        /** Category type for _Football_ nested in the [Sports] parent category */
        @JvmField
        public val Football: Nested = object : Nested("Football", Sports) {}

        /** Category type for _Golf_ nested in the [Sports] parent category */
        @JvmField
        public val Golf: Nested = object : Nested("Golf", Sports) {}

        /** Category type for _Hockey_ nested in the [Sports] parent category */
        @JvmField
        public val Hockey: Nested = object : Nested("Hockey", Sports) {}

        /** Category type for _Rugby_ nested in the [Sports] parent category */
        @JvmField
        public val Rugby: Nested = object : Nested("Rugby", Sports) {}

        /** Category type for _Running_ nested in the [Sports] parent category */
        @JvmField
        public val Running: Nested = object : Nested("Running", Sports) {}

        /** Category type for _Soccer_ nested in the [Sports] parent category */
        @JvmField
        public val Soccer: Nested = object : Nested("Soccer", Sports) {}

        /** Category type for _Swimming_ nested in the [Sports] parent category */
        @JvmField
        public val Swimming: Nested = object : Nested("Swimming", Sports) {}

        /** Category type for _Tennis_ nested in the [Sports] parent category */
        @JvmField
        public val Tennis: Nested = object : Nested("Tennis", Sports) {}

        /** Category type for _Volleyball_ nested in the [Sports] parent category */
        @JvmField
        public val Volleyball: Nested = object : Nested("Volleyball", Sports) {}

        /** Category type for _Wilderness_ nested in the [Sports] parent category */
        @JvmField
        public val Wilderness: Nested = object : Nested("Wilderness", Sports) {}

        /** Category type for _Wrestling_ nested in the [Sports] parent category */
        @JvmField
        public val Wrestling: Nested = object : Nested("Wrestling", Sports) {}

        /** Category type for _Technology_ */
        @JvmField
        public val Technology: Simple = object : Simple("Technology") {}

        /** Category type for _True Crime_ */
        @JvmField
        public val TrueCrime: Simple = object : Simple("True Crime") {}

        /** Category type for _TV & Film_ */
        @JvmField
        public val TvAndFilm: Simple = object : Simple("TV & Film") {}

        /** Category type for _After Shows_ nested in the [TvAndFilm] parent category */
        @JvmField
        public val AfterShows: Nested = object : Nested("After Shows", TvAndFilm) {}

        /** Category type for _Film History_ nested in the [TvAndFilm] parent category */
        @JvmField
        public val FilmHistory: Nested = object : Nested("Film History", TvAndFilm) {}

        /** Category type for _Film Interviews_ nested in the [TvAndFilm] parent category */
        @JvmField
        public val FilmInterviews: Nested = object : Nested("Film Interviews", TvAndFilm) {}

        /** Category type for _Film Reviews_ nested in the [TvAndFilm] parent category */
        @JvmField
        public val FilmReviews: Nested = object : Nested("Film Reviews", TvAndFilm) {}

        /** Category type for _TV Reviews_ nested in the [TvAndFilm] parent category */
        @JvmField
        public val TvReviews: Nested = object : Nested("TV Reviews", TvAndFilm) {}
    }
}
