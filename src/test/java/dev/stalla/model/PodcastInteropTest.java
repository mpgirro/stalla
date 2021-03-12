package dev.stalla.model;

import dev.stalla.model.rss.RssCategory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static dev.stalla.model.FixturesKt.anRssCategory;
import static dev.stalla.model.episode.EpisodeFixturesKt.anEpisode;
import static dev.stalla.model.podcast.PodcastFixturesKt.aPodcast;
import static org.junit.jupiter.api.Assertions.*;

public class PodcastInteropTest {

    private static Podcast podcast;
    private static final Episode episode = anEpisode();
    private static final RssCategory category = anRssCategory();

    @BeforeAll
    public static void init() {
        // Add extra elements to list properties, because for a single element
        // Kotlin's listOf() method produces an unmodifiable list by default
        podcast = Podcast.builder()
            .applyFrom(aPodcast())
            .addEpisodeBuilder(Episode.builder().applyFrom(episode))
            .addCategoryBuilder(RssCategory.builder().applyFrom(category))
            .build();
    }

    @Test
    @DisplayName("should build a Podcast that exposes an unmodifiable list of the episodes")
    public void testPodcastBuilderUnmodifiableEpisodes() {
        assertAll("Should fail to add to episode list",
            () -> assertNotNull(podcast),
            () -> assertNotNull(podcast.getEpisodes()),
            () -> assertThrows(UnsupportedOperationException.class, () -> {
                podcast.getEpisodes().add(episode);
            })
        );
    }

    @Test
    @DisplayName("should build a Podcast that exposes an unmodifiable list of the RSS categories")
    public void testPodcastBuilderUnmodifiableRssCategories() {
        assertAll("Should fail to add to category list",
            () -> assertNotNull(podcast),
            () -> assertNotNull(podcast.getCategories()),
            () -> assertThrows(UnsupportedOperationException.class, () -> {
                podcast.getCategories().add(category);
            })
        );
    }

}
