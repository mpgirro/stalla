package dev.stalla.model;

import dev.stalla.model.rss.RssCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static dev.stalla.model.FixturesKt.anRssCategory;
import static dev.stalla.model.episode.EpisodeFixturesKt.anEpisode;
import static dev.stalla.model.podcast.PodcastFixturesKt.aPodcast;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PodcastInteropTest {

    private final Podcast podcast = aPodcast();
    private final Episode episode = anEpisode();
    private final RssCategory category = anRssCategory();

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
    @DisplayName("should build a Podcast that exposes an unmodifiable list of the categories")
    public void testPodcastBuilderUnmodifiableCategories() {
        assertAll("fail to add to list",
            () -> assertNotNull(podcast.getCategories()),
            () -> assertThrows(UnsupportedOperationException.class, () -> {
                podcast.getCategories().add(category);
            })
        );
    }

}
