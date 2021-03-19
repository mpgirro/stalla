package dev.stalla.model;

import dev.stalla.model.rss.RssCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static dev.stalla.model.EpisodeFixturesKt.anEpisode;
import static dev.stalla.model.FixturesKt.anRssCategory;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EpisodeInteropTest {

    private static Episode episode;

    public EpisodeInteropTest() {
        // Add extra elements to list properties, because for a single element
        // Kotlin's listOf() method produces an unmodifiable list by default
        episode = Episode.builder()
            .applyFrom(anEpisode())
            .addCategoryBuilder(RssCategory.builder().applyFrom(anRssCategory()))
            .build();
    }

    @Test
    @DisplayName("should build an unmodifiable list of Episode RSS categories")
    void shouldBuildUnmodifiableEpisodeRssCategories() {
        final List<RssCategory> categories = requireNonNull(episode.getCategories());
        assertThrows(UnsupportedOperationException.class, () -> categories.add(anRssCategory()));
    }

}
