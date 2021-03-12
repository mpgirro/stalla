package dev.stalla.model.itunes;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static dev.stalla.model.FixturesKt.anItunesCategory;
import static dev.stalla.model.podcast.PodcastFixturesKt.aPodcastItunes;
import static org.junit.jupiter.api.Assertions.*;

public class PodcastItunesInteropTest {

    private static PodcastItunes podcastItunes;
    private static final ItunesCategory category = anItunesCategory();

    @BeforeAll
    public static void init() {
        // Add extra elements to list properties, because for a single element
        // Kotlin's listOf() method produces an unmodifiable list by default
        podcastItunes = PodcastItunes.builder()
            .applyFrom(aPodcastItunes())
            .addCategory(category)
            .build();
    }

    @Test
    @DisplayName("should build a Podcast that exposes an unmodifiable list of the iTunes categories")
    public void testPodcastBuilderUnmodifiableItunesCategories() {
        assertAll("Should fail to add to category list",
            () -> assertNotNull(podcastItunes),
            () -> assertNotNull(podcastItunes.getCategories()),
            () -> assertThrows(UnsupportedOperationException.class, () -> {
                podcastItunes.getCategories().add(category);
            })
        );
    }

}
