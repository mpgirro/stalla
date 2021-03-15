package dev.stalla.model;

import dev.stalla.model.rss.RssCategory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static dev.stalla.model.FixturesKt.anRssCategory;
import static dev.stalla.model.episode.EpisodeFixturesKt.anEpisode;
import static dev.stalla.model.podcast.PodcastFixturesKt.aPodcast;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;

public class PodcastInteropTest {

    private static Podcast podcast;

    @BeforeAll
    static void init() {
        // Add extra elements to list properties, because for a single element
        // Kotlin's listOf() method produces an unmodifiable list by default
        podcast = Podcast.builder()
            .applyFrom(aPodcast())
            .addEpisodeBuilder(Episode.builder().applyFrom(anEpisode()))
            .addCategoryBuilder(RssCategory.builder().applyFrom(anRssCategory()))
            .build();
    }

    @Test
    @DisplayName("should build an unmodifiable list of Podcast episodes")
    void testPodcastBuilderUnmodifiableEpisodes() {
        final List<Episode> episodes = requireNonNull(podcast.getEpisodes());
        assertThrows(UnsupportedOperationException.class, () -> episodes.add(anEpisode()));
    }

    @Test
    @DisplayName("should build an unmodifiable list of Podcast RSS categories")
    void shouldBuildUnmodifiablePodcastRssCategories() {
        final List<RssCategory> categories = requireNonNull(podcast.getCategories());
        assertThrows(UnsupportedOperationException.class, () -> categories.add(anRssCategory()));
    }

}
