package dev.stalla.model.goggleplay;

import dev.stalla.model.googleplay.GoogleplayCategory;
import dev.stalla.model.googleplay.PodcastGoogleplay;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static dev.stalla.model.FixturesKt.aGoogleplayCategory;
import static dev.stalla.model.PodcastFixturesKt.aPodcastGoogleplay;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PodcastGoogleplayInteropTest {

    private final PodcastGoogleplay podcastGoogleplay;

    public PodcastGoogleplayInteropTest() {
        // Add extra elements to list properties, because for a single element
        // Kotlin's listOf() method produces an unmodifiable list by default
        podcastGoogleplay = PodcastGoogleplay.builder()
            .applyFrom(aPodcastGoogleplay())
            .addCategory(aGoogleplayCategory())
            .build();
    }

    @Test
    @DisplayName("should build an unmodifiable list of Podcast Google Play categories")
    void shouldBuildUnmodifiablePodcastGoogleplayCategories() {
        final List<GoogleplayCategory> categories = requireNonNull(podcastGoogleplay.getCategories());
        assertThrows(UnsupportedOperationException.class, () -> categories.add(aGoogleplayCategory()));
    }

}
