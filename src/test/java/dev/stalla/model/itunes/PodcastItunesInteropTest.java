package dev.stalla.model.itunes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static dev.stalla.model.FixturesKt.anItunesCategory;
import static dev.stalla.model.PodcastFixturesKt.aPodcastItunes;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PodcastItunesInteropTest {

    private static PodcastItunes podcastItunes;

    public PodcastItunesInteropTest() {
        // Add extra elements to list properties, because for a single element
        // Kotlin's listOf() method produces an unmodifiable list by default
        podcastItunes = PodcastItunes.builder()
            .applyFrom(aPodcastItunes())
            .addCategory(anItunesCategory())
            .build();
    }

    @Test
    @DisplayName("should build an unmodifiable list of Podcast iTunes categories")
    void shouldBuildUnmodifiablePodcastItunesCategories() {
        final List<ItunesCategory> categories = requireNonNull(podcastItunes.getCategories());
        assertThrows(UnsupportedOperationException.class, () -> {
            categories.add(anItunesCategory());
        });
    }

}
