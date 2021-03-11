package dev.stalla.model.goggleplay;

import dev.stalla.model.googleplay.GoogleplayCategory;
import dev.stalla.model.googleplay.PodcastGoogleplay;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static dev.stalla.model.FixturesKt.aGoogleplayCategory;
import static dev.stalla.model.podcast.PodcastFixturesKt.aPodcastGoogleplay;
import static org.junit.jupiter.api.Assertions.*;

public class PodcastGoogleplayInteropTest {

    private static PodcastGoogleplay podcastGoogleplay;
    private static final GoogleplayCategory category = aGoogleplayCategory();

    @BeforeAll
    public static void init(){
        // Add extra elements to list properties, because for a single element
        // Kotlin's listOf() method produces an unmodifiable list by default
        podcastGoogleplay = PodcastGoogleplay.builder()
            .applyFrom(aPodcastGoogleplay())
            .addCategory(category)
            .build();
    }

    @Test
    @DisplayName("should build a Podcast that exposes an unmodifiable list of the Google Play categories")
    public void testPodcastBuilderUnmodifiableGoogleplayCategories() {
        assertAll("Should fail to add to category list",
            () -> assertNotNull(podcastGoogleplay),
            () -> assertNotNull(podcastGoogleplay.getCategories()),
            () -> assertThrows(UnsupportedOperationException.class, () -> {
                podcastGoogleplay.getCategories().add(category);
            })
        );
    }

}
