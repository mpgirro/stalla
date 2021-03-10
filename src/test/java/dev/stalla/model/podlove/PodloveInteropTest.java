package dev.stalla.model.podlove;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static dev.stalla.model.episode.EpisodeFixturesKt.aPodloveSimpleChapter;
import static dev.stalla.model.episode.EpisodeFixturesKt.anEpisodePodlove;
import static org.junit.jupiter.api.Assertions.*;

public class PodloveInteropTest {

    private final EpisodePodlove episodePodlove = anEpisodePodlove();
    private final SimpleChapter simpleChapter = aPodloveSimpleChapter();

    @Test
    @DisplayName("should build an Episode Podlove that exposes an unmodifiable list of the simplechapters")
    public void testEpisodePodloveBuilderUnmodifiableSimpleChapters() {
        assertAll("fail to add to list",
            () -> assertNotNull(episodePodlove.getSimpleChapters()),
            () -> assertThrows(UnsupportedOperationException.class, () -> {
                episodePodlove.getSimpleChapters().add(simpleChapter);
            })
        );
    }

}
