package dev.stalla.model.itunes

import dev.stalla.util.findItunesCategory

/**
 * An [iTunes-style `<category>` tag][https://help.apple.com/itc/podcasts_connect/#/itc9267a2f12].
 */
public sealed interface ItunesCategory {

    /** The name of the category */
    public val categoryName: String

    public companion object Factory {

        @JvmStatic
        public fun from(category: String?): ItunesCategory? = findItunesCategory(category)

        @JvmField
        public val ARTS: ItunesCategory = SimpleItunesCategory.ARTS

        @JvmField
        public val BOOKS: ItunesCategory = NestedItunesCategory.BOOKS

        @JvmField
        public val DESIGN: ItunesCategory = NestedItunesCategory.DESIGN

        @JvmField
        public val FASHION_AND_BEAUTY: ItunesCategory = NestedItunesCategory.FASHION_AND_BEAUTY

        @JvmField
        public val FOOD: ItunesCategory = NestedItunesCategory.FOOD

        @JvmField
        public val PERFORMING_ARTS: ItunesCategory = NestedItunesCategory.PERFORMING_ARTS

        @JvmField
        public val VISUAL_ARTS: ItunesCategory = NestedItunesCategory.VISUAL_ARTS

        @JvmField
        public val BUSINESS: ItunesCategory = SimpleItunesCategory.BUSINESS

        @JvmField
        public val CAREERS: ItunesCategory = NestedItunesCategory.CAREERS

        @JvmField
        public val ENTREPRENEURSHIP: ItunesCategory = NestedItunesCategory.ENTREPRENEURSHIP

        @JvmField
        public val INVESTING: ItunesCategory = NestedItunesCategory.INVESTING

        @JvmField
        public val MANAGEMENT: ItunesCategory = NestedItunesCategory.MANAGEMENT

        @JvmField
        public val MARKETING: ItunesCategory = NestedItunesCategory.MARKETING

        @JvmField
        public val NON_PROFIT: ItunesCategory = NestedItunesCategory.NON_PROFIT

        @JvmField
        public val COMEDY: ItunesCategory = SimpleItunesCategory.COMEDY

        @JvmField
        public val COMEDY_INTERVIEWS: ItunesCategory = NestedItunesCategory.COMEDY_INTERVIEWS

        @JvmField
        public val IMPROV: ItunesCategory = NestedItunesCategory.IMPROV

        @JvmField
        public val STAND_UP: ItunesCategory = NestedItunesCategory.STAND_UP

        @JvmField
        public val EDUCATION: ItunesCategory = SimpleItunesCategory.EDUCATION

        @JvmField
        public val COURSES: ItunesCategory = NestedItunesCategory.COURSES

        @JvmField
        public val HOW_TO: ItunesCategory = NestedItunesCategory.HOW_TO

        @JvmField
        public val LANGUAGE_LEARNING: ItunesCategory = NestedItunesCategory.LANGUAGE_LEARNING

        @JvmField
        public val SELF_IMPROVEMENT: ItunesCategory = NestedItunesCategory.SELF_IMPROVEMENT

        @JvmField
        public val FICTION: ItunesCategory = SimpleItunesCategory.FICTION

        @JvmField
        public val COMEDY_FICTION: ItunesCategory = NestedItunesCategory.COMEDY_FICTION

        @JvmField
        public val DRAMA: ItunesCategory = NestedItunesCategory.DRAMA

        @JvmField
        public val SCIENCE_FICTION: ItunesCategory = NestedItunesCategory.SCIENCE_FICTION

        @JvmField
        public val GOVERNMENT: ItunesCategory = SimpleItunesCategory.GOVERNMENT

        @JvmField
        public val HISTORY: ItunesCategory = SimpleItunesCategory.HISTORY

        @JvmField
        public val HEALTH_AND_FITNESS: ItunesCategory = SimpleItunesCategory.HEALTH_AND_FITNESS

        @JvmField
        public val ALTERNATIVE_HEALTH: ItunesCategory = NestedItunesCategory.ALTERNATIVE_HEALTH

        @JvmField
        public val FITNESS: ItunesCategory = NestedItunesCategory.FITNESS

        @JvmField
        public val MEDICINE: ItunesCategory = NestedItunesCategory.MEDICINE

        @JvmField
        public val MENTAL_HEALTH: ItunesCategory = NestedItunesCategory.MENTAL_HEALTH

        @JvmField
        public val NUTRITION: ItunesCategory = NestedItunesCategory.NUTRITION

        @JvmField
        public val SEXUALITY: ItunesCategory = NestedItunesCategory.SEXUALITY

        @JvmField
        public val KIDS_AND_FAMILY: ItunesCategory = SimpleItunesCategory.KIDS_AND_FAMILY

        @JvmField
        public val EDUCATION_FOR_KIDS: ItunesCategory = NestedItunesCategory.EDUCATION_FOR_KIDS

        @JvmField
        public val PARENTING: ItunesCategory = NestedItunesCategory.PARENTING

        @JvmField
        public val PETS_AND_ANIMALS: ItunesCategory = NestedItunesCategory.PETS_AND_ANIMALS

        @JvmField
        public val STORIES_FOR_KIDS: ItunesCategory = NestedItunesCategory.STORIES_FOR_KIDS

        @JvmField
        public val LEISURE: ItunesCategory = SimpleItunesCategory.LEISURE

        @JvmField
        public val ANIMATION_AND_MANGA: ItunesCategory = NestedItunesCategory.ANIMATION_AND_MANGA

        @JvmField
        public val AUTOMOTIVE: ItunesCategory = NestedItunesCategory.AUTOMOTIVE

        @JvmField
        public val AVIATION: ItunesCategory = NestedItunesCategory.AVIATION

        @JvmField
        public val CRAFTS: ItunesCategory = NestedItunesCategory.CRAFTS

        @JvmField
        public val GAMES: ItunesCategory = NestedItunesCategory.GAMES

        @JvmField
        public val HOBBIES: ItunesCategory = NestedItunesCategory.HOBBIES

        @JvmField
        public val HOME_AND_GARDEN: ItunesCategory = NestedItunesCategory.HOME_AND_GARDEN

        @JvmField
        public val VIDEO_GAMES: ItunesCategory = NestedItunesCategory.VIDEO_GAMES

        @JvmField
        public val MUSIC: ItunesCategory = SimpleItunesCategory.MUSIC

        @JvmField
        public val MUSIC_COMMENTARY: ItunesCategory = NestedItunesCategory.MUSIC_COMMENTARY

        @JvmField
        public val MUSIC_HISTORY: ItunesCategory = NestedItunesCategory.MUSIC_HISTORY

        @JvmField
        public val MUSIC_INTERVIEWS: ItunesCategory = NestedItunesCategory.MUSIC_INTERVIEWS

        @JvmField
        public val NEWS: ItunesCategory = SimpleItunesCategory.NEWS

        @JvmField
        public val BUSINESS_NEWS: ItunesCategory = NestedItunesCategory.BUSINESS_NEWS

        @JvmField
        public val DAILY_NEWS: ItunesCategory = NestedItunesCategory.DAILY_NEWS

        @JvmField
        public val ENTERTAINMENT_NEWS: ItunesCategory = NestedItunesCategory.ENTERTAINMENT_NEWS

        @JvmField
        public val NEWS_COMMENTARY: ItunesCategory = NestedItunesCategory.NEWS_COMMENTARY

        @JvmField
        public val POLITICS: ItunesCategory = NestedItunesCategory.POLITICS

        @JvmField
        public val SPORTS_NEWS: ItunesCategory = NestedItunesCategory.SPORTS_NEWS

        @JvmField
        public val TECH_NEWS: ItunesCategory = NestedItunesCategory.TECH_NEWS

        @JvmField
        public val RELIGION_AND_SPIRITUALITY: ItunesCategory = SimpleItunesCategory.RELIGION_AND_SPIRITUALITY

        @JvmField
        public val BUDDHISM: ItunesCategory = NestedItunesCategory.BUDDHISM

        @JvmField
        public val CHRISTIANITY: ItunesCategory = NestedItunesCategory.CHRISTIANITY

        @JvmField
        public val HINDUISM: ItunesCategory = NestedItunesCategory.HINDUISM

        @JvmField
        public val ISLAM: ItunesCategory = NestedItunesCategory.ISLAM

        @JvmField
        public val JUDAISM: ItunesCategory = NestedItunesCategory.JUDAISM

        @JvmField
        public val RELIGION: ItunesCategory = NestedItunesCategory.RELIGION

        @JvmField
        public val SPIRITUALITY: ItunesCategory = NestedItunesCategory.SPIRITUALITY

        @JvmField
        public val SCIENCE: ItunesCategory = SimpleItunesCategory.SCIENCE

        @JvmField
        public val ASTRONOMY: ItunesCategory = NestedItunesCategory.ASTRONOMY

        @JvmField
        public val CHEMISTRY: ItunesCategory = NestedItunesCategory.CHEMISTRY

        @JvmField
        public val EARTH_SCIENCES: ItunesCategory = NestedItunesCategory.EARTH_SCIENCES

        @JvmField
        public val LIFE_SCIENCES: ItunesCategory = NestedItunesCategory.LIFE_SCIENCES

        @JvmField
        public val MATHEMATICS: ItunesCategory = NestedItunesCategory.MATHEMATICS

        @JvmField
        public val NATURAL_SCIENCES: ItunesCategory = NestedItunesCategory.NATURAL_SCIENCES

        @JvmField
        public val NATURE: ItunesCategory = NestedItunesCategory.NATURE

        @JvmField
        public val PHYSICS: ItunesCategory = NestedItunesCategory.PHYSICS

        @JvmField
        public val SOCIAL_SCIENCES: ItunesCategory = NestedItunesCategory.SOCIAL_SCIENCES

        @JvmField
        public val SOCIETY_AND_CULTURE: ItunesCategory = SimpleItunesCategory.SOCIETY_AND_CULTURE

        @JvmField
        public val DOCUMENTARY: ItunesCategory = NestedItunesCategory.DOCUMENTARY

        @JvmField
        public val PERSONAL_JOURNALS: ItunesCategory = NestedItunesCategory.PERSONAL_JOURNALS

        @JvmField
        public val PHILOSOPHY: ItunesCategory = NestedItunesCategory.PHILOSOPHY

        @JvmField
        public val PLACES_AND_TRAVEL: ItunesCategory = NestedItunesCategory.PLACES_AND_TRAVEL

        @JvmField
        public val RELATIONSHIPS: ItunesCategory = NestedItunesCategory.RELATIONSHIPS

        @JvmField
        public val SPORTS: ItunesCategory = SimpleItunesCategory.SPORTS

        @JvmField
        public val BASEBALL: ItunesCategory = NestedItunesCategory.BASEBALL

        @JvmField
        public val BASKETBALL: ItunesCategory = NestedItunesCategory.BASKETBALL

        @JvmField
        public val CRICKET: ItunesCategory = NestedItunesCategory.CRICKET

        @JvmField
        public val FANTASY_SPORTS: ItunesCategory = NestedItunesCategory.FANTASY_SPORTS

        @JvmField
        public val FOOTBALL: ItunesCategory = NestedItunesCategory.FOOTBALL

        @JvmField
        public val GOLF: ItunesCategory = NestedItunesCategory.GOLF

        @JvmField
        public val HOCKEY: ItunesCategory = NestedItunesCategory.HOCKEY

        @JvmField
        public val RUGBY: ItunesCategory = NestedItunesCategory.RUGBY

        @JvmField
        public val RUNNING: ItunesCategory = NestedItunesCategory.RUNNING

        @JvmField
        public val SOCCER: ItunesCategory = NestedItunesCategory.SOCCER

        @JvmField
        public val SWIMMING: ItunesCategory = NestedItunesCategory.SWIMMING

        @JvmField
        public val TENNIS: ItunesCategory = NestedItunesCategory.TENNIS

        @JvmField
        public val VOLLEYBALL: ItunesCategory = NestedItunesCategory.VOLLEYBALL

        @JvmField
        public val WILDERNESS: ItunesCategory = NestedItunesCategory.WILDERNESS

        @JvmField
        public val WRESTLING: ItunesCategory = NestedItunesCategory.WRESTLING

        @JvmField
        public val TECHNOLOGY: ItunesCategory = SimpleItunesCategory.TECHNOLOGY

        @JvmField
        public val TRUE_CRIME: ItunesCategory = SimpleItunesCategory.TRUE_CRIME

        @JvmField
        public val TV_AND_FILM: ItunesCategory = SimpleItunesCategory.TV_AND_FILM

        @JvmField
        public val AFTER_SHOWS: ItunesCategory = NestedItunesCategory.AFTER_SHOWS

        @JvmField
        public val FILM_HISTORY: ItunesCategory = NestedItunesCategory.FILM_HISTORY

        @JvmField
        public val FILM_INTERVIEWS: ItunesCategory = NestedItunesCategory.FILM_INTERVIEWS

        @JvmField
        public val FILM_REVIEWS: ItunesCategory = NestedItunesCategory.FILM_REVIEWS

        @JvmField
        public val TV_REVIEWS: ItunesCategory = NestedItunesCategory.TV_REVIEWS
    }
}
