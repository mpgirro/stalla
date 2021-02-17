package dev.stalla.model.itunes

import dev.stalla.model.TypeFactory
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
import kotlin.reflect.KVisibility
import kotlin.reflect.full.declaredMemberProperties

/**
 * Model for the finite value set encountered within the `<itunes:category>`
 * element within a `<channel>` element.
 *
 * The [Apple Podcasts Categories](https://help.apple.com/itc/podcasts_connect/#/itc9267a2f12)
 * hierarchy is modeled in accordance with the following table:
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
 * @see Simple
 * @see Nested
 */
public sealed class ItunesCategory(public open val type: String) {

    /**
     * A simple iTunes-style category, without a nested subcategory:
     *
     * ```
     * <itunes:category text="News" />
     * ```
     *
     * Categories are defined in the [Apple Podcasts Categories](https://help.apple.com/itc/podcasts_connect/#/itc9267a2f12).
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
     * Categories and their hierarchy are defined in the [Apple Podcasts Categories](https://help.apple.com/itc/podcasts_connect/#/itc9267a2f12).
     *
     * @param parent The parent [Simple].
     */
    public abstract class Nested protected constructor(override val type: String, public val parent: Simple) : ItunesCategory(type)

    public companion object Factory : TypeFactory<ItunesCategory> {

        private val valueMap: Map<String, ItunesCategory> by lazy {
            val values: List<ItunesCategory> = Factory::class.declaredMemberProperties
                .filter { member -> member.visibility == KVisibility.PUBLIC }
                .mapNotNull { member ->
                    val value = member.getter.call(this)
                    if (value is ItunesCategory) value else null
                }
            values.associateBy({ it.type.toLowerCase() }, { it })
        }

        @JvmStatic
        override fun of(rawValue: String?): ItunesCategory? = rawValue?.let {
            return valueMap[it.toLowerCase()]
        }

        /** Category type for _Arts_ */
        @JvmField
        public val ARTS: Simple = object : Simple("Arts") {}

        /** Category type for _Books_ nested in the [ARTS] parent category */
        @JvmField
        public val BOOKS: Nested = object : Nested("Books", ARTS) {}

        /** Category type for _Design_ nested in the [ARTS] parent category */
        @JvmField
        public val DESIGN: Nested = object : Nested("Design", ARTS) {}

        /** Category type for _Fashion & Beauty_ nested in the [ARTS] parent category */
        @JvmField
        public val FASHION_AND_BEAUTY: Nested = object : Nested("Fashion & Beauty", ARTS) {}

        /** Category type for _Food_ nested in the [ARTS] parent category */
        @JvmField
        public val FOOD: Nested = object : Nested("Food", ARTS) {}

        /** Category type for _Performing Arts_ nested in the [ARTS] parent category */
        @JvmField
        public val PERFORMING_ARTS: Nested = object : Nested("Performing Arts", ARTS) {}

        /** Category type for _Visual Arts_ nested in the [ARTS] parent category */
        @JvmField
        public val VISUAL_ARTS: Nested = object : Nested("Visual Arts", ARTS) {}

        /** Category type for _Business_ */
        @JvmField
        public val BUSINESS: Simple = object : Simple("Business") {}

        /** Category type for _Careers_ nested in the [BUSINESS] parent category */
        @JvmField
        public val CAREERS: Nested = object : Nested("Careers", BUSINESS) {}

        /** Category type for _Entrepreneurship_ nested in the [BUSINESS] parent category */
        @JvmField
        public val ENTREPRENEURSHIP: Nested = object : Nested("Entrepreneurship", BUSINESS) {}

        /** Category type for _Investing_ nested in the [BUSINESS] parent category */
        @JvmField
        public val INVESTING: Nested = object : Nested("Investing", BUSINESS) {}

        /** Category type for _Management_ nested in the [BUSINESS] parent category */
        @JvmField
        public val MANAGEMENT: Nested = object : Nested("Management", BUSINESS) {}

        /** Category type for _Marketing_ nested in the [BUSINESS] parent category */
        @JvmField
        public val MARKETING: Nested = object : Nested("Marketing", BUSINESS) {}

        /** Category type for _Non-Profit_ nested in the [BUSINESS] parent category */
        @JvmField
        public val NON_PROFIT: Nested = object : Nested("Non-Profit", BUSINESS) {}

        /** Category type for _Comedy_ */
        @JvmField
        public val COMEDY: Simple = object : Simple("Comedy") {}

        /** Category type for _Comedy Interviews_ nested in the [COMEDY] parent category */
        @JvmField
        public val COMEDY_INTERVIEWS: Nested = object : Nested("Comedy Interviews", COMEDY) {}

        /** Category type for _Improv_ nested in the [COMEDY] parent category */
        @JvmField
        public val IMPROV: Nested = object : Nested("Improv", COMEDY) {}

        /** Category type for _Stand-Up_ nested in the [COMEDY] parent category */
        @JvmField
        public val STAND_UP: Nested = object : Nested("Stand-Up", COMEDY) {}

        /** Category type for _Education_ */
        @JvmField
        public val EDUCATION: Simple = object : Simple("Education") {}

        /** Category type for _Courses_ nested in the [EDUCATION] parent category */
        @JvmField
        public val COURSES: Nested = object : Nested("Courses", EDUCATION) {}

        /** Category type for _HowTo_ nested in the [EDUCATION] parent category */
        @JvmField
        public val HOW_TO: Nested = object : Nested("How To", EDUCATION) {}

        /** Category type for _Language Learning_ nested in the [EDUCATION] parent category */
        @JvmField
        public val LANGUAGE_LEARNING: Nested = object : Nested("Language Learning", EDUCATION) {}

        /** Category type for _Self-Improvement_ nested in the [EDUCATION] parent category */
        @JvmField
        public val SELF_IMPROVEMENT: Nested = object : Nested("Self-Improvement", EDUCATION) {}

        /** Category type for _Fiction_ */
        @JvmField
        public val FICTION: Simple = object : Simple("Fiction") {}

        /** Category type for _Comedy Fiction_ nested in the [FICTION] parent category */
        @JvmField
        public val COMEDY_FICTION: Nested = object : Nested("Comedy Fiction", FICTION) {}

        /** Category type for _Drama_ nested in the [FICTION] parent category */
        @JvmField
        public val DRAMA: Nested = object : Nested("Drama", FICTION) {}

        /** Category type for _Science Fiction_ nested in the [FICTION] parent category */
        @JvmField
        public val SCIENCE_FICTION: Nested = object : Nested("Science Fiction", FICTION) {}

        /** Category type for _Government_ */
        @JvmField
        public val GOVERNMENT: Simple = object : Simple("Government") {}

        /** Category type for _History_ */
        @JvmField
        public val HISTORY: Simple = object : Simple("History") {}

        /** Category type for _Health & Fitness_ */
        @JvmField
        public val HEALTH_AND_FITNESS: Simple = object : Simple("Health & Fitness") {}

        /** Category type for _Alternative Health_ nested in the [HEALTH_AND_FITNESS] parent category */
        @JvmField
        public val ALTERNATIVE_HEALTH: Nested = object : Nested("Alternative Health", HEALTH_AND_FITNESS) {}

        /** Category type for _Fitness_ nested in the [HEALTH_AND_FITNESS] parent category */
        @JvmField
        public val FITNESS: Nested = object : Nested("Fitness", HEALTH_AND_FITNESS) {}

        /** Category type for _Medicine_ nested in the [HEALTH_AND_FITNESS] parent category */
        @JvmField
        public val MEDICINE: Nested = object : Nested("Medicine", HEALTH_AND_FITNESS) {}

        /** Category type for _Mental Health_ nested in the [HEALTH_AND_FITNESS] parent category */
        @JvmField
        public val MENTAL_HEALTH: Nested = object : Nested("Mental Health", HEALTH_AND_FITNESS) {}

        /** Category type for _Nutrition_ nested in the [HEALTH_AND_FITNESS] parent category */
        @JvmField
        public val NUTRITION: Nested = object : Nested("Nutrition", HEALTH_AND_FITNESS) {}

        /** Category type for _Sexuality_ nested in the [HEALTH_AND_FITNESS] parent category */
        @JvmField
        public val SEXUALITY: Nested = object : Nested("Sexuality", HEALTH_AND_FITNESS) {}

        /** Category type for _Kids & Family_ */
        @JvmField
        public val KIDS_AND_FAMILY: Simple = object : Simple("Kids & Family") {}

        /** Category type for _Education for Kids_ nested in the [KIDS_AND_FAMILY] parent category */
        @JvmField
        public val EDUCATION_FOR_KIDS: Nested = object : Nested("Education for Kids", KIDS_AND_FAMILY) {}

        /** Category type for _Parentings_ nested in the [KIDS_AND_FAMILY] parent category */
        @JvmField
        public val PARENTING: Nested = object : Nested("Parenting", KIDS_AND_FAMILY) {}

        /** Category type for _Pets & Animals_ nested in the [KIDS_AND_FAMILY] parent category */
        @JvmField
        public val PETS_AND_ANIMALS: Nested = object : Nested("Pets & Animals", KIDS_AND_FAMILY) {}

        /** Category type for _Stories for Kids_ nested in the [KIDS_AND_FAMILY] parent category */
        @JvmField
        public val STORIES_FOR_KIDS: Nested = object : Nested("Stories for Kids", KIDS_AND_FAMILY) {}

        /** Category type for _Leisure_ */
        @JvmField
        public val LEISURE: Simple = object : Simple("Leisure") {}

        /** Category type for _Animation & Manga_ nested in the [LEISURE] parent category */
        @JvmField
        public val ANIMATION_AND_MANGA: Nested = object : Nested("Animation & Manga", LEISURE) {}

        /** Category type for _Automotive_ nested in the [LEISURE] parent category */
        @JvmField
        public val AUTOMOTIVE: Nested = object : Nested("Automotive", LEISURE) {}

        /** Category type for _Aviation_ nested in the [LEISURE] parent category */
        @JvmField
        public val AVIATION: Nested = object : Nested("Aviation", LEISURE) {}

        /** Category type for _Crafts_ nested in the [LEISURE] parent category */
        @JvmField
        public val CRAFTS: Nested = object : Nested("Crafts", LEISURE) {}

        /** Category type for _Games_ nested in the [LEISURE] parent category */
        @JvmField
        public val GAMES: Nested = object : Nested("Games", LEISURE) {}

        /** Category type for _Hobbies_ nested in the [LEISURE] parent category */
        @JvmField
        public val HOBBIES: Nested = object : Nested("Hobbies", LEISURE) {}

        /** Category type for _Home & Garden_ nested in the [LEISURE] parent category */
        @JvmField
        public val HOME_AND_GARDEN: Nested = object : Nested("Home & Garden", LEISURE) {}

        /** Category type for _Video Games_ nested in the [LEISURE] parent category */
        @JvmField
        public val VIDEO_GAMES: Nested = object : Nested("Video Games", LEISURE) {}

        /** Category type for _Music_ */
        @JvmField
        public val MUSIC: Simple = object : Simple("Music") {}

        /** Category type for _Music Commentary_ nested in the [MUSIC] parent category */
        @JvmField
        public val MUSIC_COMMENTARY: Nested = object : Nested("Music Commentary", MUSIC) {}

        /** Category type for _Music History_ nested in the [MUSIC] parent category */
        @JvmField
        public val MUSIC_HISTORY: Nested = object : Nested("Music History", MUSIC) {}

        /** Category type for _Music Interviews_ nested in the [MUSIC] parent category */
        @JvmField
        public val MUSIC_INTERVIEWS: Nested = object : Nested("Music Interviews", MUSIC) {}

        /** Category type for _News_ */
        @JvmField
        public val NEWS: Simple = object : Simple("News") {}

        /** Category type for _Business News_ nested in the [NEWS] parent category */
        @JvmField
        public val BUSINESS_NEWS: Nested = object : Nested("Business News", NEWS) {}

        /** Category type for _Daily News_ nested in the [NEWS] parent category */
        @JvmField
        public val DAILY_NEWS: Nested = object : Nested("Daily News", NEWS) {}

        /** Category type for _Entertainment News_ nested in the [NEWS] parent category */
        @JvmField
        public val ENTERTAINMENT_NEWS: Nested = object : Nested("Entertainment News", NEWS) {}

        /** Category type for _News Commentary_ nested in the [NEWS] parent category */
        @JvmField
        public val NEWS_COMMENTARY: Nested = object : Nested("News Commentary", NEWS) {}

        /** Category type for _Politics_ nested in the [NEWS] parent category */
        @JvmField
        public val POLITICS: Nested = object : Nested("Politics", NEWS) {}

        /** Category type for _Sports News_ nested in the [NEWS] parent category */
        @JvmField
        public val SPORTS_NEWS: Nested = object : Nested("Sports News", NEWS) {}

        /** Category type for _Tech News_ nested in the [NEWS] parent category */
        @JvmField
        public val TECH_NEWS: Nested = object : Nested("Tech News", NEWS) {}

        /** Category type for _Religion & Spirituality_ */
        @JvmField
        public val RELIGION_AND_SPIRITUALITY: Simple = object : Simple("Religion & Spirituality") {}

        /** Category type for _Buddhism_ nested in the [RELIGION_AND_SPIRITUALITY] parent category */
        @JvmField
        public val BUDDHISM: Nested = object : Nested("Buddhism", RELIGION_AND_SPIRITUALITY) {}

        /** Category type for _Christianity_ nested in the [RELIGION_AND_SPIRITUALITY] parent category */
        @JvmField
        public val CHRISTIANITY: Nested = object : Nested("Christianity", RELIGION_AND_SPIRITUALITY) {}

        /** Category type for _Hinduism_ nested in the [RELIGION_AND_SPIRITUALITY] parent category */
        @JvmField
        public val HINDUISM: Nested = object : Nested("Hinduism", RELIGION_AND_SPIRITUALITY) {}

        /** Category type for _Islam_ nested in the [RELIGION_AND_SPIRITUALITY] parent category */
        @JvmField
        public val ISLAM: Nested = object : Nested("Islam", RELIGION_AND_SPIRITUALITY) {}

        /** Category type for _Judaism_ nested in the [RELIGION_AND_SPIRITUALITY] parent category */
        @JvmField
        public val JUDAISM: Nested = object : Nested("Judaism", RELIGION_AND_SPIRITUALITY) {}

        /** Category type for _Religion_ nested in the [RELIGION_AND_SPIRITUALITY] parent category */
        @JvmField
        public val RELIGION: Nested = object : Nested("Religion", RELIGION_AND_SPIRITUALITY) {}

        /** Category type for _Spirituality_ nested in the [RELIGION_AND_SPIRITUALITY] parent category */
        @JvmField
        public val SPIRITUALITY: Nested = object : Nested("Spirituality", RELIGION_AND_SPIRITUALITY) {}

        /** Category type for _Science_ */
        @JvmField
        public val SCIENCE: Simple = object : Simple("Science") {}

        /** Category type for _Astronomy_ nested in the [SCIENCE] parent category */
        @JvmField
        public val ASTRONOMY: Nested = object : Nested("Astronomy", SCIENCE) {}

        /** Category type for _Chemistry_ nested in the [SCIENCE] parent category */
        @JvmField
        public val CHEMISTRY: Nested = object : Nested("Chemistry", SCIENCE) {}

        /** Category type for _Earth Sciences_ nested in the [SCIENCE] parent category */
        @JvmField
        public val EARTH_SCIENCES: Nested = object : Nested("Earth Sciences", SCIENCE) {}

        /** Category type for _Life Sciences_ nested in the [SCIENCE] parent category */
        @JvmField
        public val LIFE_SCIENCES: Nested = object : Nested("Life Sciences", SCIENCE) {}

        /** Category type for _Mathematics_ nested in the [SCIENCE] parent category */
        @JvmField
        public val MATHEMATICS: Nested = object : Nested("Mathematics", SCIENCE) {}

        /** Category type for _Natural Sciences_ nested in the [SCIENCE] parent category */
        @JvmField
        public val NATURAL_SCIENCES: Nested = object : Nested("Natural Sciences", SCIENCE) {}

        /** Category type for _Nature_ nested in the [SCIENCE] parent category */
        @JvmField
        public val NATURE: Nested = object : Nested("Nature", SCIENCE) {}

        /** Category type for _Physics_ nested in the [SCIENCE] parent category */
        @JvmField
        public val PHYSICS: Nested = object : Nested("Physics", SCIENCE) {}

        /** Category type for _Social Sciences_ nested in the [SCIENCE] parent category */
        @JvmField
        public val SOCIAL_SCIENCES: Nested = object : Nested("Social Sciences", SCIENCE) {}

        /** Category type for _Society & Culture_ */
        @JvmField
        public val SOCIETY_AND_CULTURE: Simple = object : Simple("Society & Culture") {}

        /** Category type for _Documentary_ nested in the [SOCIETY_AND_CULTURE] parent category */
        @JvmField
        public val DOCUMENTARY: Nested = object : Nested("Documentary", SOCIETY_AND_CULTURE) {}

        /** Category type for _Personal Journals_ nested in the [SOCIETY_AND_CULTURE] parent category */
        @JvmField
        public val PERSONAL_JOURNALS: Nested = object : Nested("Personal Journals", SOCIETY_AND_CULTURE) {}

        /** Category type for _Philosophy_ nested in the [SOCIETY_AND_CULTURE] parent category */
        @JvmField
        public val PHILOSOPHY: Nested = object : Nested("Philosophy", SOCIETY_AND_CULTURE) {}

        /** Category type for _Places & Travel_ nested in the [SOCIETY_AND_CULTURE] parent category */
        @JvmField
        public val PLACES_AND_TRAVEL: Nested = object : Nested("Places & Travel", SOCIETY_AND_CULTURE) {}

        /** Category type for _Relationships_ nested in the [SOCIETY_AND_CULTURE] parent category */
        @JvmField
        public val RELATIONSHIPS: Nested = object : Nested("Relationships", SOCIETY_AND_CULTURE) {}

        /** Category type for _Sports_ */
        @JvmField
        public val SPORTS: Simple = object : Simple("Sports") {}

        /** Category type for _Baseball_ nested in the [SPORTS] parent category */
        @JvmField
        public val BASEBALL: Nested = object : Nested("Baseball", SPORTS) {}

        /** Category type for _Basketball_ nested in the [SPORTS] parent category */
        @JvmField
        public val BASKETBALL: Nested = object : Nested("Basketball", SPORTS) {}

        /** Category type for _Cricket_ nested in the [SPORTS] parent category */
        @JvmField
        public val CRICKET: Nested = object : Nested("Cricket", SPORTS) {}

        /** Category type for _Fantasy Sports_ nested in the [SPORTS] parent category */
        @JvmField
        public val FANTASY_SPORTS: Nested = object : Nested("Fantasy Sports", SPORTS) {}

        /** Category type for _Football_ nested in the [SPORTS] parent category */
        @JvmField
        public val FOOTBALL: Nested = object : Nested("Football", SPORTS) {}

        /** Category type for _Golf_ nested in the [SPORTS] parent category */
        @JvmField
        public val GOLF: Nested = object : Nested("Golf", SPORTS) {}

        /** Category type for _Hockey_ nested in the [SPORTS] parent category */
        @JvmField
        public val HOCKEY: Nested = object : Nested("Hockey", SPORTS) {}

        /** Category type for _Rugby_ nested in the [SPORTS] parent category */
        @JvmField
        public val RUGBY: Nested = object : Nested("Rugby", SPORTS) {}

        /** Category type for _Running_ nested in the [SPORTS] parent category */
        @JvmField
        public val RUNNING: Nested = object : Nested("Running", SPORTS) {}

        /** Category type for _Soccer_ nested in the [SPORTS] parent category */
        @JvmField
        public val SOCCER: Nested = object : Nested("Soccer", SPORTS) {}

        /** Category type for _Swimming_ nested in the [SPORTS] parent category */
        @JvmField
        public val SWIMMING: Nested = object : Nested("Swimming", SPORTS) {}

        /** Category type for _Tennis_ nested in the [SPORTS] parent category */
        @JvmField
        public val TENNIS: Nested = object : Nested("Tennis", SPORTS) {}

        /** Category type for _Volleyball_ nested in the [SPORTS] parent category */
        @JvmField
        public val VOLLEYBALL: Nested = object : Nested("Volleyball", SPORTS) {}

        /** Category type for _Wilderness_ nested in the [SPORTS] parent category */
        @JvmField
        public val WILDERNESS: Nested = object : Nested("Wilderness", SPORTS) {}

        /** Category type for _Wrestling_ nested in the [SPORTS] parent category */
        @JvmField
        public val WRESTLING: Nested = object : Nested("Wrestling", SPORTS) {}

        /** Category type for _Technology_ */
        @JvmField
        public val TECHNOLOGY: Simple = object : Simple("Technology") {}

        /** Category type for _True Crime_ */
        @JvmField
        public val TRUE_CRIME: Simple = object : Simple("True Crime") {}

        /** Category type for _TV & Film_ */
        @JvmField
        public val TV_AND_FILM: Simple = object : Simple("TV & Film") {}

        /** Category type for _After Shows_ nested in the [TV_AND_FILM] parent category */
        @JvmField
        public val AFTER_SHOWS: Nested = object : Nested("After Shows", TV_AND_FILM) {}

        /** Category type for _Film History_ nested in the [TV_AND_FILM] parent category */
        @JvmField
        public val FILM_HISTORY: Nested = object : Nested("Film History", TV_AND_FILM) {}

        /** Category type for _Film Interviews_ nested in the [TV_AND_FILM] parent category */
        @JvmField
        public val FILM_INTERVIEWS: Nested = object : Nested("Film Interviews", TV_AND_FILM) {}

        /** Category type for _Film Reviews_ nested in the [TV_AND_FILM] parent category */
        @JvmField
        public val FILM_REVIEWS: Nested = object : Nested("Film Reviews", TV_AND_FILM) {}

        /** Category type for _TV Reviews_ nested in the [TV_AND_FILM] parent category */
        @JvmField
        public val TV_REVIEWS: Nested = object : Nested("TV Reviews", TV_AND_FILM) {}
    }
}
