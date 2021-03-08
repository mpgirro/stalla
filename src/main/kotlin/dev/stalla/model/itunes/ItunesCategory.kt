package dev.stalla.model.itunes

import dev.stalla.model.TypeFactory
import dev.stalla.model.itunes.ItunesCategory.Factory
import dev.stalla.model.itunes.ItunesCategory.Factory.AFTER_SHOWS
import dev.stalla.model.itunes.ItunesCategory.Factory.ALTERNATIVE_HEALTH
import dev.stalla.model.itunes.ItunesCategory.Factory.ANIMATION_AND_MANGA
import dev.stalla.model.itunes.ItunesCategory.Factory.ARTS
import dev.stalla.model.itunes.ItunesCategory.Factory.ASTRONOMY
import dev.stalla.model.itunes.ItunesCategory.Factory.AUTOMOTIVE
import dev.stalla.model.itunes.ItunesCategory.Factory.AVIATION
import dev.stalla.model.itunes.ItunesCategory.Factory.BASEBALL
import dev.stalla.model.itunes.ItunesCategory.Factory.BASKETBALL
import dev.stalla.model.itunes.ItunesCategory.Factory.BOOKS
import dev.stalla.model.itunes.ItunesCategory.Factory.BUDDHISM
import dev.stalla.model.itunes.ItunesCategory.Factory.BUSINESS
import dev.stalla.model.itunes.ItunesCategory.Factory.BUSINESS_NEWS
import dev.stalla.model.itunes.ItunesCategory.Factory.CAREERS
import dev.stalla.model.itunes.ItunesCategory.Factory.CHEMISTRY
import dev.stalla.model.itunes.ItunesCategory.Factory.CHRISTIANITY
import dev.stalla.model.itunes.ItunesCategory.Factory.COMEDY
import dev.stalla.model.itunes.ItunesCategory.Factory.COMEDY_FICTION
import dev.stalla.model.itunes.ItunesCategory.Factory.COMEDY_INTERVIEWS
import dev.stalla.model.itunes.ItunesCategory.Factory.COURSES
import dev.stalla.model.itunes.ItunesCategory.Factory.CRAFTS
import dev.stalla.model.itunes.ItunesCategory.Factory.CRICKET
import dev.stalla.model.itunes.ItunesCategory.Factory.DAILY_NEWS
import dev.stalla.model.itunes.ItunesCategory.Factory.DESIGN
import dev.stalla.model.itunes.ItunesCategory.Factory.DOCUMENTARY
import dev.stalla.model.itunes.ItunesCategory.Factory.DRAMA
import dev.stalla.model.itunes.ItunesCategory.Factory.EARTH_SCIENCES
import dev.stalla.model.itunes.ItunesCategory.Factory.EDUCATION
import dev.stalla.model.itunes.ItunesCategory.Factory.EDUCATION_FOR_KIDS
import dev.stalla.model.itunes.ItunesCategory.Factory.ENTERTAINMENT_NEWS
import dev.stalla.model.itunes.ItunesCategory.Factory.ENTREPRENEURSHIP
import dev.stalla.model.itunes.ItunesCategory.Factory.FANTASY_SPORTS
import dev.stalla.model.itunes.ItunesCategory.Factory.FASHION_AND_BEAUTY
import dev.stalla.model.itunes.ItunesCategory.Factory.FICTION
import dev.stalla.model.itunes.ItunesCategory.Factory.FILM_HISTORY
import dev.stalla.model.itunes.ItunesCategory.Factory.FILM_INTERVIEWS
import dev.stalla.model.itunes.ItunesCategory.Factory.FILM_REVIEWS
import dev.stalla.model.itunes.ItunesCategory.Factory.FITNESS
import dev.stalla.model.itunes.ItunesCategory.Factory.FOOD
import dev.stalla.model.itunes.ItunesCategory.Factory.FOOTBALL
import dev.stalla.model.itunes.ItunesCategory.Factory.GAMES
import dev.stalla.model.itunes.ItunesCategory.Factory.GOLF
import dev.stalla.model.itunes.ItunesCategory.Factory.GOVERNMENT
import dev.stalla.model.itunes.ItunesCategory.Factory.HEALTH_AND_FITNESS
import dev.stalla.model.itunes.ItunesCategory.Factory.HINDUISM
import dev.stalla.model.itunes.ItunesCategory.Factory.HISTORY
import dev.stalla.model.itunes.ItunesCategory.Factory.HOBBIES
import dev.stalla.model.itunes.ItunesCategory.Factory.HOCKEY
import dev.stalla.model.itunes.ItunesCategory.Factory.HOME_AND_GARDEN
import dev.stalla.model.itunes.ItunesCategory.Factory.HOW_TO
import dev.stalla.model.itunes.ItunesCategory.Factory.IMPROV
import dev.stalla.model.itunes.ItunesCategory.Factory.INVESTING
import dev.stalla.model.itunes.ItunesCategory.Factory.ISLAM
import dev.stalla.model.itunes.ItunesCategory.Factory.JUDAISM
import dev.stalla.model.itunes.ItunesCategory.Factory.KIDS_AND_FAMILY
import dev.stalla.model.itunes.ItunesCategory.Factory.LANGUAGE_LEARNING
import dev.stalla.model.itunes.ItunesCategory.Factory.LEISURE
import dev.stalla.model.itunes.ItunesCategory.Factory.LIFE_SCIENCES
import dev.stalla.model.itunes.ItunesCategory.Factory.MANAGEMENT
import dev.stalla.model.itunes.ItunesCategory.Factory.MARKETING
import dev.stalla.model.itunes.ItunesCategory.Factory.MATHEMATICS
import dev.stalla.model.itunes.ItunesCategory.Factory.MEDICINE
import dev.stalla.model.itunes.ItunesCategory.Factory.MENTAL_HEALTH
import dev.stalla.model.itunes.ItunesCategory.Factory.MUSIC
import dev.stalla.model.itunes.ItunesCategory.Factory.MUSIC_COMMENTARY
import dev.stalla.model.itunes.ItunesCategory.Factory.MUSIC_HISTORY
import dev.stalla.model.itunes.ItunesCategory.Factory.MUSIC_INTERVIEWS
import dev.stalla.model.itunes.ItunesCategory.Factory.NATURAL_SCIENCES
import dev.stalla.model.itunes.ItunesCategory.Factory.NATURE
import dev.stalla.model.itunes.ItunesCategory.Factory.NEWS
import dev.stalla.model.itunes.ItunesCategory.Factory.NEWS_COMMENTARY
import dev.stalla.model.itunes.ItunesCategory.Factory.NON_PROFIT
import dev.stalla.model.itunes.ItunesCategory.Factory.NUTRITION
import dev.stalla.model.itunes.ItunesCategory.Factory.PARENTING
import dev.stalla.model.itunes.ItunesCategory.Factory.PERFORMING_ARTS
import dev.stalla.model.itunes.ItunesCategory.Factory.PERSONAL_JOURNALS
import dev.stalla.model.itunes.ItunesCategory.Factory.PETS_AND_ANIMALS
import dev.stalla.model.itunes.ItunesCategory.Factory.PHILOSOPHY
import dev.stalla.model.itunes.ItunesCategory.Factory.PHYSICS
import dev.stalla.model.itunes.ItunesCategory.Factory.PLACES_AND_TRAVEL
import dev.stalla.model.itunes.ItunesCategory.Factory.POLITICS
import dev.stalla.model.itunes.ItunesCategory.Factory.RELATIONSHIPS
import dev.stalla.model.itunes.ItunesCategory.Factory.RELIGION
import dev.stalla.model.itunes.ItunesCategory.Factory.RELIGION_AND_SPIRITUALITY
import dev.stalla.model.itunes.ItunesCategory.Factory.RUGBY
import dev.stalla.model.itunes.ItunesCategory.Factory.RUNNING
import dev.stalla.model.itunes.ItunesCategory.Factory.SCIENCE
import dev.stalla.model.itunes.ItunesCategory.Factory.SCIENCE_FICTION
import dev.stalla.model.itunes.ItunesCategory.Factory.SELF_IMPROVEMENT
import dev.stalla.model.itunes.ItunesCategory.Factory.SEXUALITY
import dev.stalla.model.itunes.ItunesCategory.Factory.SOCCER
import dev.stalla.model.itunes.ItunesCategory.Factory.SOCIAL_SCIENCES
import dev.stalla.model.itunes.ItunesCategory.Factory.SOCIETY_AND_CULTURE
import dev.stalla.model.itunes.ItunesCategory.Factory.SPIRITUALITY
import dev.stalla.model.itunes.ItunesCategory.Factory.SPORTS
import dev.stalla.model.itunes.ItunesCategory.Factory.SPORTS_NEWS
import dev.stalla.model.itunes.ItunesCategory.Factory.STAND_UP
import dev.stalla.model.itunes.ItunesCategory.Factory.STORIES_FOR_KIDS
import dev.stalla.model.itunes.ItunesCategory.Factory.SWIMMING
import dev.stalla.model.itunes.ItunesCategory.Factory.TECHNOLOGY
import dev.stalla.model.itunes.ItunesCategory.Factory.TECH_NEWS
import dev.stalla.model.itunes.ItunesCategory.Factory.TENNIS
import dev.stalla.model.itunes.ItunesCategory.Factory.TRUE_CRIME
import dev.stalla.model.itunes.ItunesCategory.Factory.TV_AND_FILM
import dev.stalla.model.itunes.ItunesCategory.Factory.TV_REVIEWS
import dev.stalla.model.itunes.ItunesCategory.Factory.VIDEO_GAMES
import dev.stalla.model.itunes.ItunesCategory.Factory.VISUAL_ARTS
import dev.stalla.model.itunes.ItunesCategory.Factory.VOLLEYBALL
import dev.stalla.model.itunes.ItunesCategory.Factory.WILDERNESS
import dev.stalla.model.itunes.ItunesCategory.Factory.WRESTLING
import dev.stalla.model.itunes.ItunesCategory.Nested
import dev.stalla.model.itunes.ItunesCategory.Simple
import java.util.Locale
import kotlin.reflect.KVisibility
import kotlin.reflect.full.declaredMemberProperties

/**
 * Supported category types encountered within the `<itunes:category>` element
 * within a `<channel>` element, modeled as a finite set sealed class.
 *
 * Defined category values are listed in
 * [Apple Podcasts Categories](https://help.apple.com/itc/podcasts_connect/#/itc9267a2f12).
 * The categories and their nested hierarchies are modeled according to the table below.
 * This classes [companion object][Factory] exposed a reference for each instance.
 *
 * | Category                | Subcategory        | Property                    |
 * |-------------------------|--------------------|-----------------------------|
 * | Arts                    | –                  | [ARTS]                      |
 * | Arts                    | Books              | [BOOKS]                     |
 * | Arts                    | Design             | [DESIGN]                    |
 * | Arts                    | Fashion & Beauty   | [FASHION_AND_BEAUTY]        |
 * | Arts                    | Food               | [FOOD]                      |
 * | Arts                    | Performing Arts    | [PERFORMING_ARTS]           |
 * | Arts                    | Visual Arts        | [VISUAL_ARTS]               |
 * | Business                | –                  | [BUSINESS]                  |
 * | Business                | Careers            | [CAREERS]                   |
 * | Business                | Entrepreneurship   | [ENTREPRENEURSHIP]          |
 * | Business                | Investing          | [INVESTING]                 |
 * | Business                | Management         | [MANAGEMENT]                |
 * | Business                | Marketing          | [MARKETING]                 |
 * | Business                | Non-Profit         | [NON_PROFIT]                |
 * | Comedy                  | –                  | [COMEDY]                    |
 * | Comedy                  | Comedy Interviews  | [COMEDY_INTERVIEWS]         |
 * | Comedy                  | Improv             | [IMPROV]                    |
 * | Comedy                  | Stand-Up           | [STAND_UP]                  |
 * | Education               | –                  | [EDUCATION]                 |
 * | Education               | Courses            | [COURSES]                   |
 * | Education               | How To             | [HOW_TO]                    |
 * | Education               | Language Learning  | [LANGUAGE_LEARNING]         |
 * | Education               | Self-Improvement   | [SELF_IMPROVEMENT]          |
 * | Fiction                 | –                  | [FICTION]                   |
 * | Fiction                 | Comedy Fiction     | [COMEDY_FICTION]            |
 * | Fiction                 | Drama              | [DRAMA]                     |
 * | Fiction                 | Science Fiction    | [SCIENCE_FICTION]           |
 * | Government              | –                  | [GOVERNMENT]                |
 * | History                 | –                  | [HISTORY]                   |
 * | Health & Fitness        | –                  | [HEALTH_AND_FITNESS]        |
 * | Health & Fitness        | Alternative Health | [ALTERNATIVE_HEALTH]        |
 * | Health & Fitness        | Fitness            | [FITNESS]                   |
 * | Health & Fitness        | Medicine           | [MEDICINE]                  |
 * | Health & Fitness        | Mental Health      | [MENTAL_HEALTH]             |
 * | Health & Fitness        | Nutrition          | [NUTRITION]                 |
 * | Health & Fitness        | Sexuality          | [SEXUALITY]                 |
 * | Kids & Family           | –                  | [KIDS_AND_FAMILY]           |
 * | Kids & Family           | Education for Kids | [EDUCATION_FOR_KIDS]        |
 * | Kids & Family           | Parenting          | [PARENTING]                 |
 * | Kids & Family           | Pets & Animals     | [PETS_AND_ANIMALS]          |
 * | Kids & Family           | Stories for Kids   | [STORIES_FOR_KIDS]          |
 * | Leisure                 | –                  | [LEISURE]                   |
 * | Leisure                 | Animation & Manga  | [ANIMATION_AND_MANGA]       |
 * | Leisure                 | Automotive         | [AUTOMOTIVE]                |
 * | Leisure                 | Aviation           | [AVIATION]                  |
 * | Leisure                 | Crafts             | [CRAFTS]                    |
 * | Leisure                 | Games              | [GAMES]                     |
 * | Leisure                 | Hobbies            | [HOBBIES]                   |
 * | Leisure                 | Home & Garden      | [HOME_AND_GARDEN]           |
 * | Leisure                 | Video Games        | [VIDEO_GAMES]               |
 * | Music                   | –                  | [MUSIC]                     |
 * | Music                   | Music Commentary   | [MUSIC_COMMENTARY]          |
 * | Music                   | Music History      | [MUSIC_HISTORY]             |
 * | Music                   | Music Interviews   | [MUSIC_INTERVIEWS]          |
 * | News                    | –                  | [NEWS]                      |
 * | News                    | Business News      | [BUSINESS_NEWS]             |
 * | News                    | Daily News         | [DAILY_NEWS]                |
 * | News                    | Entertainment News | [ENTERTAINMENT_NEWS]        |
 * | News                    | News Commentary    | [NEWS_COMMENTARY]           |
 * | News                    | Politics           | [POLITICS]                  |
 * | News                    | Sports News        | [SPORTS_NEWS]               |
 * | News                    | Tech News          | [TECH_NEWS]                 |
 * | Religion & Spirituality | –                  | [RELIGION_AND_SPIRITUALITY] |
 * | Religion & Spirituality | Buddhism           | [BUDDHISM]                  |
 * | Religion & Spirituality | Christianity       | [CHRISTIANITY]              |
 * | Religion & Spirituality | Hinduism           | [HINDUISM]                  |
 * | Religion & Spirituality | Islam              | [ISLAM]                     |
 * | Religion & Spirituality | Judaism            | [JUDAISM]                   |
 * | Religion & Spirituality | Religion           | [RELIGION]                  |
 * | Religion & Spirituality | Spirituality       | [SPIRITUALITY]              |
 * | Science                 | –                  | [SCIENCE]                   |
 * | Science                 | Astronomy          | [ASTRONOMY]                 |
 * | Science                 | Chemistry          | [CHEMISTRY]                 |
 * | Science                 | Earth Sciences     | [EARTH_SCIENCES]            |
 * | Science                 | Life Sciences      | [LIFE_SCIENCES]             |
 * | Science                 | Mathematics        | [MATHEMATICS]               |
 * | Science                 | Natural Sciences   | [NATURAL_SCIENCES]          |
 * | Science                 | Nature             | [NATURE]                    |
 * | Science                 | Physics            | [PHYSICS]                   |
 * | Science                 | Social Sciences    | [SOCIAL_SCIENCES]           |
 * | Society & Culture       | –                  | [SOCIETY_AND_CULTURE]       |
 * | Society & Culture       | Documentary        | [DOCUMENTARY]               |
 * | Society & Culture       | Personal Journals  | [PERSONAL_JOURNALS]         |
 * | Society & Culture       | Philosophy         | [PHILOSOPHY]                |
 * | Society & Culture       | Places & Travel    | [PLACES_AND_TRAVEL]         |
 * | Society & Culture       | Relationships      | [RELATIONSHIPS]             |
 * | Sports                  | –                  | [SPORTS]                    |
 * | Sports                  | Baseball           | [BASEBALL]                  |
 * | Sports                  | Basketball         | [BASKETBALL]                |
 * | Sports                  | Cricket            | [CRICKET]                   |
 * | Sports                  | Fantasy Sports     | [FANTASY_SPORTS]            |
 * | Sports                  | Football           | [FOOTBALL]                  |
 * | Sports                  | Golf               | [GOLF]                      |
 * | Sports                  | Hockey             | [HOCKEY]                    |
 * | Sports                  | Rugby              | [RUGBY]                     |
 * | Sports                  | Running            | [RUNNING]                   |
 * | Sports                  | Soccer             | [SOCCER]                    |
 * | Sports                  | Swimming           | [SWIMMING]                  |
 * | Sports                  | Tennis             | [TENNIS]                    |
 * | Sports                  | Volleyball         | [VOLLEYBALL]                |
 * | Sports                  | Wilderness         | [WILDERNESS]                |
 * | Sports                  | Wrestling          | [WRESTLING]                 |
 * | Technology              | –                  | [TECHNOLOGY]                |
 * | True Crime              | –                  | [TRUE_CRIME]                |
 * | TV & Film               | –                  | [TV_AND_FILM]               |
 * | TV & Film               | After Shows        | [AFTER_SHOWS]               |
 * | TV & Film               | Film History       | [FILM_HISTORY]              |
 * | TV & Film               | Film Interviews    | [FILM_INTERVIEWS]           |
 * | TV & Film               | Film Reviews       | [FILM_REVIEWS]              |
 * | TV & Film               | TV Reviews         | [TV_REVIEWS]                |
 *
 * @property type The raw category `type` value.
 *
 * @see Simple Subtype for simple categories.
 * @see Nested Subtype for categories that are nested in a [Simple] category.
 * @see Factory Companion object exposing references to all valid instances and a factory method.
 */
public sealed class ItunesCategory(public open val type: String) {

    /**
     * A simple iTunes-style category, without a nested subcategory:
     *
     * ```
     * <itunes:category text="News" />
     * ```
     *
     * Categories are defined in the
     * [Apple Podcasts Categories](https://help.apple.com/itc/podcasts_connect/#/itc9267a2f12).
     */
    public abstract class Simple protected constructor(override val type: String) : ItunesCategory(type)

    /**
     * An iTunes-style subcategory that is contained within a parent [Simple]:
     *
     * ```
     * <itunes:category text="News">
     *     <itunes:category text="Tech News" />
     * </itunes:category>
     * ```
     *
     * Categories and their hierarchy are defined in the
     * [Apple Podcasts Categories](https://help.apple.com/itc/podcasts_connect/#/itc9267a2f12).
     *
     * @param parent The parent [Simple].
     */
    public abstract class Nested protected constructor(
        override val type: String,
        public val parent: Simple
    ) : ItunesCategory(type)

    /** Gets an instance of [ItunesCategory] from a raw value. */
    public companion object Factory : TypeFactory<ItunesCategory> {

        private val valueMap: Map<String, ItunesCategory> by lazy {
            val values: List<ItunesCategory> = Factory::class.declaredMemberProperties
                .filter { member -> member.visibility == KVisibility.PUBLIC }
                .mapNotNull { member -> member.getter.call(this) }
                .filterIsInstance<ItunesCategory>()

            values.associateBy({ it.type.toLowerCase(Locale.ROOT) }, { it })
        }

        @JvmStatic
        override fun of(rawValue: String?): ItunesCategory? = rawValue?.let {
            return valueMap[it.toLowerCase(Locale.ROOT)]
        }

        /** Category type for `Arts`. */
        @JvmField
        public val ARTS: Simple = object : Simple("Arts") {}

        /** Category type for the value `Books` nested in the [ARTS] parent category. */
        @JvmField
        public val BOOKS: Nested = object : Nested("Books", ARTS) {}

        /** Category type for value `Design` nested in the [ARTS] parent category. */
        @JvmField
        public val DESIGN: Nested = object : Nested("Design", ARTS) {}

        /** Category type for the value `Fashion & Beauty` nested in the [ARTS] parent category. */
        @JvmField
        public val FASHION_AND_BEAUTY: Nested = object : Nested("Fashion & Beauty", ARTS) {}

        /** Category type for the value `Food` nested in the [ARTS] parent category. */
        @JvmField
        public val FOOD: Nested = object : Nested("Food", ARTS) {}

        /** Category type for the value `Performing Arts` nested in the [ARTS] parent category. */
        @JvmField
        public val PERFORMING_ARTS: Nested = object : Nested("Performing Arts", ARTS) {}

        /** Category type for the value `Visual Arts` nested in the [ARTS] parent category. */
        @JvmField
        public val VISUAL_ARTS: Nested = object : Nested("Visual Arts", ARTS) {}

        /** Category type for the value `Business`. */
        @JvmField
        public val BUSINESS: Simple = object : Simple("Business") {}

        /** Category type for the value `Careers` nested in the [BUSINESS] parent category. */
        @JvmField
        public val CAREERS: Nested = object : Nested("Careers", BUSINESS) {}

        /** Category type for the value `Entrepreneurship` nested in the [BUSINESS] parent category. */
        @JvmField
        public val ENTREPRENEURSHIP: Nested = object : Nested("Entrepreneurship", BUSINESS) {}

        /** Category type for the value `Investing` nested in the [BUSINESS] parent category. */
        @JvmField
        public val INVESTING: Nested = object : Nested("Investing", BUSINESS) {}

        /** Category type for the value `Management` nested in the [BUSINESS] parent category. */
        @JvmField
        public val MANAGEMENT: Nested = object : Nested("Management", BUSINESS) {}

        /** Category type for the value `Marketing` nested in the [BUSINESS] parent category. */
        @JvmField
        public val MARKETING: Nested = object : Nested("Marketing", BUSINESS) {}

        /** Category type for the value `Non-Profit` nested in the [BUSINESS] parent category. */
        @JvmField
        public val NON_PROFIT: Nested = object : Nested("Non-Profit", BUSINESS) {}

        /** Category type for the value `Comedy`. */
        @JvmField
        public val COMEDY: Simple = object : Simple("Comedy") {}

        /** Category type for the value `Comedy Interviews` nested in the [COMEDY] parent category. */
        @JvmField
        public val COMEDY_INTERVIEWS: Nested = object : Nested("Comedy Interviews", COMEDY) {}

        /** Category type for the value `Improv` nested in the [COMEDY] parent category. */
        @JvmField
        public val IMPROV: Nested = object : Nested("Improv", COMEDY) {}

        /** Category type for the value `Stand-Up` nested in the [COMEDY] parent category. */
        @JvmField
        public val STAND_UP: Nested = object : Nested("Stand-Up", COMEDY) {}

        /** Category type for the value `Education`. */
        @JvmField
        public val EDUCATION: Simple = object : Simple("Education") {}

        /** Category type for the value `Courses` nested in the [EDUCATION] parent category. */
        @JvmField
        public val COURSES: Nested = object : Nested("Courses", EDUCATION) {}

        /** Category type for the value `HowTo` nested in the [EDUCATION] parent category. */
        @JvmField
        public val HOW_TO: Nested = object : Nested("How To", EDUCATION) {}

        /** Category type for the value `Language Learning` nested in the [EDUCATION] parent category. */
        @JvmField
        public val LANGUAGE_LEARNING: Nested = object : Nested("Language Learning", EDUCATION) {}

        /** Category type for the value `Self-Improvement` nested in the [EDUCATION] parent category. */
        @JvmField
        public val SELF_IMPROVEMENT: Nested = object : Nested("Self-Improvement", EDUCATION) {}

        /** Category type for the value `Fiction`. */
        @JvmField
        public val FICTION: Simple = object : Simple("Fiction") {}

        /** Category type for the value `Comedy Fiction` nested in the [FICTION] parent category. */
        @JvmField
        public val COMEDY_FICTION: Nested = object : Nested("Comedy Fiction", FICTION) {}

        /** Category type for the value `Drama` nested in the [FICTION] parent category. */
        @JvmField
        public val DRAMA: Nested = object : Nested("Drama", FICTION) {}

        /** Category type for the value `Science Fiction` nested in the [FICTION] parent category. */
        @JvmField
        public val SCIENCE_FICTION: Nested = object : Nested("Science Fiction", FICTION) {}

        /** Category type for the value `Government`. */
        @JvmField
        public val GOVERNMENT: Simple = object : Simple("Government") {}

        /** Category type for the value `History`. */
        @JvmField
        public val HISTORY: Simple = object : Simple("History") {}

        /** Category type for the value `Health & Fitness`. */
        @JvmField
        public val HEALTH_AND_FITNESS: Simple = object : Simple("Health & Fitness") {}

        /** Category type for the value `Alternative Health` nested in the [HEALTH_AND_FITNESS] parent category. */
        @JvmField
        public val ALTERNATIVE_HEALTH: Nested = object : Nested("Alternative Health", HEALTH_AND_FITNESS) {}

        /** Category type for the value `Fitness` nested in the [HEALTH_AND_FITNESS] parent category. */
        @JvmField
        public val FITNESS: Nested = object : Nested("Fitness", HEALTH_AND_FITNESS) {}

        /** Category type for the value `Medicine` nested in the [HEALTH_AND_FITNESS] parent category. */
        @JvmField
        public val MEDICINE: Nested = object : Nested("Medicine", HEALTH_AND_FITNESS) {}

        /** Category type for the value `Mental Health` nested in the [HEALTH_AND_FITNESS] parent category. */
        @JvmField
        public val MENTAL_HEALTH: Nested = object : Nested("Mental Health", HEALTH_AND_FITNESS) {}

        /** Category type for the value `Nutrition` nested in the [HEALTH_AND_FITNESS] parent category. */
        @JvmField
        public val NUTRITION: Nested = object : Nested("Nutrition", HEALTH_AND_FITNESS) {}

        /** Category type for the value `Sexuality` nested in the [HEALTH_AND_FITNESS] parent category. */
        @JvmField
        public val SEXUALITY: Nested = object : Nested("Sexuality", HEALTH_AND_FITNESS) {}

        /** Category type for the value `Kids & Family`. */
        @JvmField
        public val KIDS_AND_FAMILY: Simple = object : Simple("Kids & Family") {}

        /** Category type for the value `Education for Kids` nested in the [KIDS_AND_FAMILY] parent category. */
        @JvmField
        public val EDUCATION_FOR_KIDS: Nested = object : Nested("Education for Kids", KIDS_AND_FAMILY) {}

        /** Category type for the value `Parentings` nested in the [KIDS_AND_FAMILY] parent category. */
        @JvmField
        public val PARENTING: Nested = object : Nested("Parenting", KIDS_AND_FAMILY) {}

        /** Category type for the value `Pets & Animals` nested in the [KIDS_AND_FAMILY] parent category. */
        @JvmField
        public val PETS_AND_ANIMALS: Nested = object : Nested("Pets & Animals", KIDS_AND_FAMILY) {}

        /** Category type for the value `Stories for Kids` nested in the [KIDS_AND_FAMILY] parent category. */
        @JvmField
        public val STORIES_FOR_KIDS: Nested = object : Nested("Stories for Kids", KIDS_AND_FAMILY) {}

        /** Category type for the value `Leisure`. */
        @JvmField
        public val LEISURE: Simple = object : Simple("Leisure") {}

        /** Category type for the value `Animation & Manga` nested in the [LEISURE] parent category. */
        @JvmField
        public val ANIMATION_AND_MANGA: Nested = object : Nested("Animation & Manga", LEISURE) {}

        /** Category type for the value `Automotive` nested in the [LEISURE] parent category. */
        @JvmField
        public val AUTOMOTIVE: Nested = object : Nested("Automotive", LEISURE) {}

        /** Category type for the value `Aviation` nested in the [LEISURE] parent category. */
        @JvmField
        public val AVIATION: Nested = object : Nested("Aviation", LEISURE) {}

        /** Category type for the value `Crafts` nested in the [LEISURE] parent category. */
        @JvmField
        public val CRAFTS: Nested = object : Nested("Crafts", LEISURE) {}

        /** Category type for the value `Games` nested in the [LEISURE] parent category. */
        @JvmField
        public val GAMES: Nested = object : Nested("Games", LEISURE) {}

        /** Category type for the value `Hobbies` nested in the [LEISURE] parent category. */
        @JvmField
        public val HOBBIES: Nested = object : Nested("Hobbies", LEISURE) {}

        /** Category type for the value `Home & Garden` nested in the [LEISURE] parent category. */
        @JvmField
        public val HOME_AND_GARDEN: Nested = object : Nested("Home & Garden", LEISURE) {}

        /** Category type for the value `Video Games` nested in the [LEISURE] parent category. */
        @JvmField
        public val VIDEO_GAMES: Nested = object : Nested("Video Games", LEISURE) {}

        /** Category type for the value `Music`. */
        @JvmField
        public val MUSIC: Simple = object : Simple("Music") {}

        /** Category type for the value `Music Commentary` nested in the [MUSIC] parent category. */
        @JvmField
        public val MUSIC_COMMENTARY: Nested = object : Nested("Music Commentary", MUSIC) {}

        /** Category type for the value `Music History` nested in the [MUSIC] parent category. */
        @JvmField
        public val MUSIC_HISTORY: Nested = object : Nested("Music History", MUSIC) {}

        /** Category type for the value `Music Interviews` nested in the [MUSIC] parent category. */
        @JvmField
        public val MUSIC_INTERVIEWS: Nested = object : Nested("Music Interviews", MUSIC) {}

        /** Category type for the value `News`. */
        @JvmField
        public val NEWS: Simple = object : Simple("News") {}

        /** Category type for the value `Business News` nested in the [NEWS] parent category. */
        @JvmField
        public val BUSINESS_NEWS: Nested = object : Nested("Business News", NEWS) {}

        /** Category type for the value `Daily News` nested in the [NEWS] parent category. */
        @JvmField
        public val DAILY_NEWS: Nested = object : Nested("Daily News", NEWS) {}

        /** Category type for the value `Entertainment News` nested in the [NEWS] parent category. */
        @JvmField
        public val ENTERTAINMENT_NEWS: Nested = object : Nested("Entertainment News", NEWS) {}

        /** Category type for the value `News Commentary` nested in the [NEWS] parent category. */
        @JvmField
        public val NEWS_COMMENTARY: Nested = object : Nested("News Commentary", NEWS) {}

        /** Category type for the value `Politics` nested in the [NEWS] parent category. */
        @JvmField
        public val POLITICS: Nested = object : Nested("Politics", NEWS) {}

        /** Category type for the value `Sports News` nested in the [NEWS] parent category. */
        @JvmField
        public val SPORTS_NEWS: Nested = object : Nested("Sports News", NEWS) {}

        /** Category type for the value `Tech News` nested in the [NEWS] parent category. */
        @JvmField
        public val TECH_NEWS: Nested = object : Nested("Tech News", NEWS) {}

        /** Category type for the value `Religion & Spirituality`. */
        @JvmField
        public val RELIGION_AND_SPIRITUALITY: Simple = object : Simple("Religion & Spirituality") {}

        /** Category type for the value `Buddhism` nested in the [RELIGION_AND_SPIRITUALITY] parent category. */
        @JvmField
        public val BUDDHISM: Nested = object : Nested("Buddhism", RELIGION_AND_SPIRITUALITY) {}

        /** Category type for the value `Christianity` nested in the [RELIGION_AND_SPIRITUALITY] parent category. */
        @JvmField
        public val CHRISTIANITY: Nested = object : Nested("Christianity", RELIGION_AND_SPIRITUALITY) {}

        /** Category type for the value `Hinduism` nested in the [RELIGION_AND_SPIRITUALITY] parent category. */
        @JvmField
        public val HINDUISM: Nested = object : Nested("Hinduism", RELIGION_AND_SPIRITUALITY) {}

        /** Category type for the value `Islam` nested in the [RELIGION_AND_SPIRITUALITY] parent category. */
        @JvmField
        public val ISLAM: Nested = object : Nested("Islam", RELIGION_AND_SPIRITUALITY) {}

        /** Category type for the value `Judaism` nested in the [RELIGION_AND_SPIRITUALITY] parent category. */
        @JvmField
        public val JUDAISM: Nested = object : Nested("Judaism", RELIGION_AND_SPIRITUALITY) {}

        /** Category type for the value `Religion` nested in the [RELIGION_AND_SPIRITUALITY] parent category. */
        @JvmField
        public val RELIGION: Nested = object : Nested("Religion", RELIGION_AND_SPIRITUALITY) {}

        /** Category type for the value `Spirituality` nested in the [RELIGION_AND_SPIRITUALITY] parent category. */
        @JvmField
        public val SPIRITUALITY: Nested = object : Nested("Spirituality", RELIGION_AND_SPIRITUALITY) {}

        /** Category type for the value `Science`. */
        @JvmField
        public val SCIENCE: Simple = object : Simple("Science") {}

        /** Category type for the value `Astronomy` nested in the [SCIENCE] parent category. */
        @JvmField
        public val ASTRONOMY: Nested = object : Nested("Astronomy", SCIENCE) {}

        /** Category type for the value `Chemistry` nested in the [SCIENCE] parent category. */
        @JvmField
        public val CHEMISTRY: Nested = object : Nested("Chemistry", SCIENCE) {}

        /** Category type for the value `Earth Sciences` nested in the [SCIENCE] parent category. */
        @JvmField
        public val EARTH_SCIENCES: Nested = object : Nested("Earth Sciences", SCIENCE) {}

        /** Category type for the value `Life Sciences` nested in the [SCIENCE] parent category. */
        @JvmField
        public val LIFE_SCIENCES: Nested = object : Nested("Life Sciences", SCIENCE) {}

        /** Category type for the value `Mathematics` nested in the [SCIENCE] parent category. */
        @JvmField
        public val MATHEMATICS: Nested = object : Nested("Mathematics", SCIENCE) {}

        /** Category type for the value `Natural Sciences` nested in the [SCIENCE] parent category. */
        @JvmField
        public val NATURAL_SCIENCES: Nested = object : Nested("Natural Sciences", SCIENCE) {}

        /** Category type for the value `Nature` nested in the [SCIENCE] parent category. */
        @JvmField
        public val NATURE: Nested = object : Nested("Nature", SCIENCE) {}

        /** Category type for the value `Physics` nested in the [SCIENCE] parent category. */
        @JvmField
        public val PHYSICS: Nested = object : Nested("Physics", SCIENCE) {}

        /** Category type for the value `Social Sciences` nested in the [SCIENCE] parent category. */
        @JvmField
        public val SOCIAL_SCIENCES: Nested = object : Nested("Social Sciences", SCIENCE) {}

        /** Category type for the value `Society & Culture`. */
        @JvmField
        public val SOCIETY_AND_CULTURE: Simple = object : Simple("Society & Culture") {}

        /** Category type for the value `Documentary` nested in the [SOCIETY_AND_CULTURE] parent category. */
        @JvmField
        public val DOCUMENTARY: Nested = object : Nested("Documentary", SOCIETY_AND_CULTURE) {}

        /** Category type for the value `Personal Journals` nested in the [SOCIETY_AND_CULTURE] parent category. */
        @JvmField
        public val PERSONAL_JOURNALS: Nested = object : Nested("Personal Journals", SOCIETY_AND_CULTURE) {}

        /** Category type for the value `Philosophy` nested in the [SOCIETY_AND_CULTURE] parent category. */
        @JvmField
        public val PHILOSOPHY: Nested = object : Nested("Philosophy", SOCIETY_AND_CULTURE) {}

        /** Category type for the value `Places & Travel` nested in the [SOCIETY_AND_CULTURE] parent category. */
        @JvmField
        public val PLACES_AND_TRAVEL: Nested = object : Nested("Places & Travel", SOCIETY_AND_CULTURE) {}

        /** Category type for the value `Relationships` nested in the [SOCIETY_AND_CULTURE] parent category. */
        @JvmField
        public val RELATIONSHIPS: Nested = object : Nested("Relationships", SOCIETY_AND_CULTURE) {}

        /** Category type for the value `Sports`. */
        @JvmField
        public val SPORTS: Simple = object : Simple("Sports") {}

        /** Category type for the value `Baseball` nested in the [SPORTS] parent category. */
        @JvmField
        public val BASEBALL: Nested = object : Nested("Baseball", SPORTS) {}

        /** Category type for the value `Basketball` nested in the [SPORTS] parent category. */
        @JvmField
        public val BASKETBALL: Nested = object : Nested("Basketball", SPORTS) {}

        /** Category type for the value `Cricket` nested in the [SPORTS] parent category. */
        @JvmField
        public val CRICKET: Nested = object : Nested("Cricket", SPORTS) {}

        /** Category type for the value `Fantasy Sports` nested in the [SPORTS] parent category. */
        @JvmField
        public val FANTASY_SPORTS: Nested = object : Nested("Fantasy Sports", SPORTS) {}

        /** Category type for the value `Football` nested in the [SPORTS] parent category. */
        @JvmField
        public val FOOTBALL: Nested = object : Nested("Football", SPORTS) {}

        /** Category type for the value `Golf` nested in the [SPORTS] parent category. */
        @JvmField
        public val GOLF: Nested = object : Nested("Golf", SPORTS) {}

        /** Category type for the value `Hockey` nested in the [SPORTS] parent category. */
        @JvmField
        public val HOCKEY: Nested = object : Nested("Hockey", SPORTS) {}

        /** Category type for the value `Rugby` nested in the [SPORTS] parent category. */
        @JvmField
        public val RUGBY: Nested = object : Nested("Rugby", SPORTS) {}

        /** Category type for the value `Running` nested in the [SPORTS] parent category. */
        @JvmField
        public val RUNNING: Nested = object : Nested("Running", SPORTS) {}

        /** Category type for the value `Soccer` nested in the [SPORTS] parent category. */
        @JvmField
        public val SOCCER: Nested = object : Nested("Soccer", SPORTS) {}

        /** Category type for the value `Swimming` nested in the [SPORTS] parent category. */
        @JvmField
        public val SWIMMING: Nested = object : Nested("Swimming", SPORTS) {}

        /** Category type for the value `Tennis` nested in the [SPORTS] parent category. */
        @JvmField
        public val TENNIS: Nested = object : Nested("Tennis", SPORTS) {}

        /** Category type for the value `Volleyball` nested in the [SPORTS] parent category. */
        @JvmField
        public val VOLLEYBALL: Nested = object : Nested("Volleyball", SPORTS) {}

        /** Category type for the value `Wilderness` nested in the [SPORTS] parent category. */
        @JvmField
        public val WILDERNESS: Nested = object : Nested("Wilderness", SPORTS) {}

        /** Category type for the value `Wrestling` nested in the [SPORTS] parent category. */
        @JvmField
        public val WRESTLING: Nested = object : Nested("Wrestling", SPORTS) {}

        /** Category type for the value `Technology`. */
        @JvmField
        public val TECHNOLOGY: Simple = object : Simple("Technology") {}

        /** Category type for the value `True Crime`. */
        @JvmField
        public val TRUE_CRIME: Simple = object : Simple("True Crime") {}

        /** Category type for the value `TV & Film`. */
        @JvmField
        public val TV_AND_FILM: Simple = object : Simple("TV & Film") {}

        /** Category type for the value `After Shows` nested in the [TV_AND_FILM] parent category. */
        @JvmField
        public val AFTER_SHOWS: Nested = object : Nested("After Shows", TV_AND_FILM) {}

        /** Category type for the value `Film History` nested in the [TV_AND_FILM] parent category. */
        @JvmField
        public val FILM_HISTORY: Nested = object : Nested("Film History", TV_AND_FILM) {}

        /** Category type for the value `Film Interviews` nested in the [TV_AND_FILM] parent category. */
        @JvmField
        public val FILM_INTERVIEWS: Nested = object : Nested("Film Interviews", TV_AND_FILM) {}

        /** Category type for the value `Film Reviews` nested in the [TV_AND_FILM] parent category. */
        @JvmField
        public val FILM_REVIEWS: Nested = object : Nested("Film Reviews", TV_AND_FILM) {}

        /** Category type for the value `TV Reviews` nested in the [TV_AND_FILM] parent category. */
        @JvmField
        public val TV_REVIEWS: Nested = object : Nested("TV Reviews", TV_AND_FILM) {}
    }
}
