package dev.stalla.model;

import dev.stalla.model.rss.RssCategory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static dev.stalla.model.FixturesKt.anRssCategory;
import static dev.stalla.model.episode.EpisodeFixturesKt.anEpisode;
import static org.junit.jupiter.api.Assertions.*;

public class EpisodeInteropTest {

    private static Episode episode;
    private static final RssCategory category = anRssCategory();

    @BeforeAll
    public static void init() {
        // Add extra elements to list properties, because for a single element
        // Kotlin's listOf() method produces an unmodifiable list by default
        episode = Episode.builder()
            .applyFrom(anEpisode())
            .addCategoryBuilder(RssCategory.builder().applyFrom(category))
            .build();
    }

    @Test
    @DisplayName("should build an Episode that exposes an unmodifiable list of the categories")
    public void testEpisodeBuilderUnmodifiableCategories() {
        assertAll("Should fail to add to category list",
            () -> assertNotNull(episode),
            () -> assertNotNull(episode.getCategories()),
            () -> assertThrows(UnsupportedOperationException.class, () -> {
                episode.getCategories().add(category);
            })
        );
    }

}
