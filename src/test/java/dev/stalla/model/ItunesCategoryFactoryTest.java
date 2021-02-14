package dev.stalla.model;

import dev.stalla.model.itunes.ItunesCategory;
import dev.stalla.model.itunes.NestedItunesCategory;
import dev.stalla.model.itunes.SimpleItunesCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItunesCategoryFactoryTest {

    @Test
    @DisplayName("should get the correct iTunes category instances from the interface factory method")
    public void testItunesCategoryFactory() {
        assertEquals(SimpleItunesCategory.ARTS, ItunesCategory.ARTS);
        assertEquals(NestedItunesCategory.BOOKS, ItunesCategory.BOOKS);
        assertEquals(NestedItunesCategory.DESIGN, ItunesCategory.DESIGN);
        assertEquals(NestedItunesCategory.FASHION_AND_BEAUTY, ItunesCategory.FASHION_AND_BEAUTY);
        assertEquals(NestedItunesCategory.FOOD, ItunesCategory.FOOD);
        assertEquals(NestedItunesCategory.PERFORMING_ARTS, ItunesCategory.PERFORMING_ARTS);
        assertEquals(NestedItunesCategory.VISUAL_ARTS, ItunesCategory.VISUAL_ARTS);
        assertEquals(SimpleItunesCategory.BUSINESS, ItunesCategory.BUSINESS);
        assertEquals(NestedItunesCategory.CAREERS, ItunesCategory.CAREERS);
        assertEquals(NestedItunesCategory.ENTREPRENEURSHIP, ItunesCategory.ENTREPRENEURSHIP);
        assertEquals(NestedItunesCategory.INVESTING, ItunesCategory.INVESTING);
        assertEquals(NestedItunesCategory.MANAGEMENT, ItunesCategory.MANAGEMENT);
        assertEquals(NestedItunesCategory.MARKETING, ItunesCategory.MARKETING);
        assertEquals(NestedItunesCategory.NON_PROFIT, ItunesCategory.NON_PROFIT);
        assertEquals(SimpleItunesCategory.COMEDY, ItunesCategory.COMEDY);
        assertEquals(NestedItunesCategory.COMEDY_INTERVIEWS, ItunesCategory.COMEDY_INTERVIEWS);
        assertEquals(NestedItunesCategory.IMPROV, ItunesCategory.IMPROV);
        assertEquals(NestedItunesCategory.STAND_UP, ItunesCategory.STAND_UP);
        assertEquals(SimpleItunesCategory.EDUCATION, ItunesCategory.EDUCATION);
        assertEquals(NestedItunesCategory.COURSES, ItunesCategory.COURSES);
        assertEquals(NestedItunesCategory.HOW_TO, ItunesCategory.HOW_TO);
        assertEquals(NestedItunesCategory.LANGUAGE_LEARNING, ItunesCategory.LANGUAGE_LEARNING);
        assertEquals(NestedItunesCategory.SELF_IMPROVEMENT, ItunesCategory.SELF_IMPROVEMENT);
        assertEquals(SimpleItunesCategory.FICTION, ItunesCategory.FICTION);
        assertEquals(NestedItunesCategory.COMEDY_FICTION, ItunesCategory.COMEDY_FICTION);
        assertEquals(NestedItunesCategory.DRAMA, ItunesCategory.DRAMA);
        assertEquals(NestedItunesCategory.SCIENCE_FICTION, ItunesCategory.SCIENCE_FICTION);
        assertEquals(SimpleItunesCategory.GOVERNMENT, ItunesCategory.GOVERNMENT);
        assertEquals(SimpleItunesCategory.HISTORY, ItunesCategory.HISTORY);
        assertEquals(SimpleItunesCategory.HEALTH_AND_FITNESS, ItunesCategory.HEALTH_AND_FITNESS);
        assertEquals(NestedItunesCategory.ALTERNATIVE_HEALTH, ItunesCategory.ALTERNATIVE_HEALTH);
        assertEquals(NestedItunesCategory.FITNESS, ItunesCategory.FITNESS);
        assertEquals(NestedItunesCategory.MEDICINE, ItunesCategory.MEDICINE);
        assertEquals(NestedItunesCategory.MENTAL_HEALTH, ItunesCategory.MENTAL_HEALTH);
        assertEquals(NestedItunesCategory.NUTRITION, ItunesCategory.NUTRITION);
        assertEquals(NestedItunesCategory.SEXUALITY, ItunesCategory.SEXUALITY);
        assertEquals(SimpleItunesCategory.KIDS_AND_FAMILY, ItunesCategory.KIDS_AND_FAMILY);
        assertEquals(NestedItunesCategory.EDUCATION_FOR_KIDS, ItunesCategory.EDUCATION_FOR_KIDS);
        assertEquals(NestedItunesCategory.PARENTING, ItunesCategory.PARENTING);
        assertEquals(NestedItunesCategory.PETS_AND_ANIMALS, ItunesCategory.PETS_AND_ANIMALS);
        assertEquals(NestedItunesCategory.STORIES_FOR_KIDS, ItunesCategory.STORIES_FOR_KIDS);
        assertEquals(SimpleItunesCategory.LEISURE, ItunesCategory.LEISURE);
        assertEquals(NestedItunesCategory.ANIMATION_AND_MANGA, ItunesCategory.ANIMATION_AND_MANGA);
        assertEquals(NestedItunesCategory.AUTOMOTIVE, ItunesCategory.AUTOMOTIVE);
        assertEquals(NestedItunesCategory.AVIATION, ItunesCategory.AVIATION);
        assertEquals(NestedItunesCategory.CRAFTS, ItunesCategory.CRAFTS);
        assertEquals(NestedItunesCategory.GAMES, ItunesCategory.GAMES);
        assertEquals(NestedItunesCategory.HOBBIES, ItunesCategory.HOBBIES);
        assertEquals(NestedItunesCategory.HOME_AND_GARDEN, ItunesCategory.HOME_AND_GARDEN);
        assertEquals(NestedItunesCategory.VIDEO_GAMES, ItunesCategory.VIDEO_GAMES);
        assertEquals(SimpleItunesCategory.MUSIC, ItunesCategory.MUSIC);
        assertEquals(NestedItunesCategory.MUSIC_COMMENTARY, ItunesCategory.MUSIC_COMMENTARY);
        assertEquals(NestedItunesCategory.MUSIC_HISTORY, ItunesCategory.MUSIC_HISTORY);
        assertEquals(NestedItunesCategory.MUSIC_INTERVIEWS, ItunesCategory.MUSIC_INTERVIEWS);
        assertEquals(SimpleItunesCategory.NEWS, ItunesCategory.NEWS);
        assertEquals(NestedItunesCategory.BUSINESS_NEWS, ItunesCategory.BUSINESS_NEWS);
        assertEquals(NestedItunesCategory.DAILY_NEWS, ItunesCategory.DAILY_NEWS);
        assertEquals(NestedItunesCategory.ENTERTAINMENT_NEWS, ItunesCategory.ENTERTAINMENT_NEWS);
        assertEquals(NestedItunesCategory.NEWS_COMMENTARY, ItunesCategory.NEWS_COMMENTARY);
        assertEquals(NestedItunesCategory.POLITICS, ItunesCategory.POLITICS);
        assertEquals(NestedItunesCategory.SPORTS_NEWS, ItunesCategory.SPORTS_NEWS);
        assertEquals(NestedItunesCategory.TECH_NEWS, ItunesCategory.TECH_NEWS);
        assertEquals(SimpleItunesCategory.RELIGION_AND_SPIRITUALITY, ItunesCategory.RELIGION_AND_SPIRITUALITY);
        assertEquals(NestedItunesCategory.BUDDHISM, ItunesCategory.BUDDHISM);
        assertEquals(NestedItunesCategory.CHRISTIANITY, ItunesCategory.CHRISTIANITY);
        assertEquals(NestedItunesCategory.HINDUISM, ItunesCategory.HINDUISM);
        assertEquals(NestedItunesCategory.ISLAM, ItunesCategory.ISLAM);
        assertEquals(NestedItunesCategory.JUDAISM, ItunesCategory.JUDAISM);
        assertEquals(NestedItunesCategory.RELIGION, ItunesCategory.RELIGION);
        assertEquals(NestedItunesCategory.SPIRITUALITY, ItunesCategory.SPIRITUALITY);
        assertEquals(SimpleItunesCategory.SCIENCE, ItunesCategory.SCIENCE);
        assertEquals(NestedItunesCategory.ASTRONOMY, ItunesCategory.ASTRONOMY);
        assertEquals(NestedItunesCategory.CHEMISTRY, ItunesCategory.CHEMISTRY);
        assertEquals(NestedItunesCategory.EARTH_SCIENCES, ItunesCategory.EARTH_SCIENCES);
        assertEquals(NestedItunesCategory.LIFE_SCIENCES, ItunesCategory.LIFE_SCIENCES);
        assertEquals(NestedItunesCategory.MATHEMATICS, ItunesCategory.MATHEMATICS);
        assertEquals(NestedItunesCategory.NATURAL_SCIENCES, ItunesCategory.NATURAL_SCIENCES);
        assertEquals(NestedItunesCategory.NATURE, ItunesCategory.NATURE);
        assertEquals(NestedItunesCategory.PHYSICS, ItunesCategory.PHYSICS);
        assertEquals(NestedItunesCategory.SOCIAL_SCIENCES, ItunesCategory.SOCIAL_SCIENCES);
        assertEquals(SimpleItunesCategory.SOCIETY_AND_CULTURE, ItunesCategory.SOCIETY_AND_CULTURE);
        assertEquals(NestedItunesCategory.DOCUMENTARY, ItunesCategory.DOCUMENTARY);
        assertEquals(NestedItunesCategory.PERSONAL_JOURNALS, ItunesCategory.PERSONAL_JOURNALS);
        assertEquals(NestedItunesCategory.PHILOSOPHY, ItunesCategory.PHILOSOPHY);
        assertEquals(NestedItunesCategory.PLACES_AND_TRAVEL, ItunesCategory.PLACES_AND_TRAVEL);
        assertEquals(NestedItunesCategory.RELATIONSHIPS, ItunesCategory.RELATIONSHIPS);
        assertEquals(SimpleItunesCategory.SPORTS, ItunesCategory.SPORTS);
        assertEquals(NestedItunesCategory.BASEBALL, ItunesCategory.BASEBALL);
        assertEquals(NestedItunesCategory.BASKETBALL, ItunesCategory.BASKETBALL);
        assertEquals(NestedItunesCategory.CRICKET, ItunesCategory.CRICKET);
        assertEquals(NestedItunesCategory.FANTASY_SPORTS, ItunesCategory.FANTASY_SPORTS);
        assertEquals(NestedItunesCategory.FOOTBALL, ItunesCategory.FOOTBALL);
        assertEquals(NestedItunesCategory.GOLF, ItunesCategory.GOLF);
        assertEquals(NestedItunesCategory.HOCKEY, ItunesCategory.HOCKEY);
        assertEquals(NestedItunesCategory.RUGBY, ItunesCategory.RUGBY);
        assertEquals(NestedItunesCategory.RUNNING, ItunesCategory.RUNNING);
        assertEquals(NestedItunesCategory.SOCCER, ItunesCategory.SOCCER);
        assertEquals(NestedItunesCategory.SWIMMING, ItunesCategory.SWIMMING);
        assertEquals(NestedItunesCategory.TENNIS, ItunesCategory.TENNIS);
        assertEquals(NestedItunesCategory.VOLLEYBALL, ItunesCategory.VOLLEYBALL);
        assertEquals(NestedItunesCategory.WILDERNESS, ItunesCategory.WILDERNESS);
        assertEquals(NestedItunesCategory.WRESTLING, ItunesCategory.WRESTLING);
        assertEquals(SimpleItunesCategory.TECHNOLOGY, ItunesCategory.TECHNOLOGY);
        assertEquals(SimpleItunesCategory.TRUE_CRIME, ItunesCategory.TRUE_CRIME);
        assertEquals(SimpleItunesCategory.TV_AND_FILM, ItunesCategory.TV_AND_FILM);
        assertEquals(NestedItunesCategory.AFTER_SHOWS, ItunesCategory.AFTER_SHOWS);
        assertEquals(NestedItunesCategory.FILM_HISTORY, ItunesCategory.FILM_HISTORY);
        assertEquals(NestedItunesCategory.FILM_INTERVIEWS, ItunesCategory.FILM_INTERVIEWS);
        assertEquals(NestedItunesCategory.FILM_REVIEWS, ItunesCategory.FILM_REVIEWS);
        assertEquals(NestedItunesCategory.TV_REVIEWS, ItunesCategory.TV_REVIEWS);
    }

}