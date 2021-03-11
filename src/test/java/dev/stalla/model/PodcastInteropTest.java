package dev.stalla.model;

import dev.stalla.model.googleplay.GoogleplayCategory;
import dev.stalla.model.itunes.ItunesCategory;
import dev.stalla.model.rss.RssCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static dev.stalla.model.FixturesKt.*;
import static dev.stalla.model.episode.EpisodeFixturesKt.anEpisode;
import static dev.stalla.model.podcast.PodcastFixturesKt.aPodcast;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PodcastInteropTest {

    private final Podcast podcast = aPodcast();
    private final Episode episode = anEpisode();
    private final RssCategory rssCategory = anRssCategory();
    private final ItunesCategory itunesCategory = anItunesCategory();
    private final GoogleplayCategory googleplayCategory = aGoogleplayCategory();

    @Test
    @DisplayName("should build a Podcast that exposes an unmodifiable list of the episodes")
    public void testPodcastBuilderUnmodifiableEpisodes() {
        assertAll("fail to add to list",
            () -> assertNotNull(podcast.getEpisodes()),
            () -> assertThrows(UnsupportedOperationException.class, () -> {
                podcast.getEpisodes().add(episode);
            })
        );
    }

    @Test
    @DisplayName("should build a Podcast that exposes an unmodifiable list of the RSS categories")
    public void testPodcastBuilderUnmodifiableRssCategories() {
        assertAll("fail to add to list",
            () -> assertNotNull(podcast.getCategories()),
            () -> assertThrows(UnsupportedOperationException.class, () -> {
                podcast.getCategories().add(rssCategory);
            })
        );
    }

    @Test
    @DisplayName("should build a Podcast that exposes an unmodifiable list of the iTunes categories")
    public void testPodcastBuilderUnmodifiableItunesCategories() {
        assertAll("fail to add to list",
            () -> assertNotNull(podcast.getItunes()),
            () -> assertNotNull(Objects.requireNonNull(podcast.getItunes()).getCategories()),
            () -> assertThrows(UnsupportedOperationException.class, () -> {
                podcast.getItunes().getCategories().add(itunesCategory);
            })
        );
    }

    @Test
    @DisplayName("should build a Podcast that exposes an unmodifiable list of the Google Play categories")
    public void testPodcastBuilderUnmodifiableGoogleplayCategories() {
        assertAll("fail to add to list",
            () -> assertNotNull(podcast.getGoogleplay()),
            () -> assertNotNull(Objects.requireNonNull(podcast.getGoogleplay()).getCategories()),
            () -> assertThrows(UnsupportedOperationException.class, () -> {
                podcast.getGoogleplay().getCategories().add(googleplayCategory);
            })
        );
    }

}
