package dev.stalla.model;

import dev.stalla.model.rss.RssCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static dev.stalla.model.FixturesKt.anRssCategory;
import static dev.stalla.model.episode.EpisodeFixturesKt.anEpisode;
import static org.junit.jupiter.api.Assertions.*;

public class EpisodeInteropTest {

    private final Episode episode = anEpisode();
    private final RssCategory category = anRssCategory();

    @Test
    @DisplayName("should build an Episode that exposes an unmodifiable list of the categories")
    public void testEpisodeBuilderUnmodifiableCategories() {
        assertAll("fail to add to list",
            () -> assertNotNull(episode.getCategories()),
            () -> assertThrows(UnsupportedOperationException.class, () -> {
                episode.getCategories().add(category);
            })
        );
    }

}
