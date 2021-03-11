package dev.stalla.model.podlove;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static dev.stalla.model.episode.EpisodeFixturesKt.aPodloveSimpleChapter;
import static dev.stalla.model.episode.EpisodeFixturesKt.anEpisodePodlove;
import static org.junit.jupiter.api.Assertions.*;

public class PodloveInteropTest {

    private static EpisodePodlove episodePodlove;
    private static final SimpleChapter simpleChapter = aPodloveSimpleChapter();

    @BeforeAll
    public static void init(){
        // Add extra elements to list properties, because for a single element
        // Kotlin's listOf() method produces an unmodifiable list by default
        episodePodlove = EpisodePodlove.builder()
            .applyFrom(anEpisodePodlove())
            .addSimpleChapterBuilder(SimpleChapter.builder().applyFrom(simpleChapter))
            .build();
    }

    @Test
    @DisplayName("should build an Episode Podlove that exposes an unmodifiable list of the simplechapters")
    public void testEpisodePodloveBuilderUnmodifiableSimpleChapters() {
        assertAll("fail to add to list",
            () -> assertNotNull(episodePodlove),
            () -> assertNotNull(episodePodlove.getSimpleChapters()),
            () -> assertThrows(UnsupportedOperationException.class, () -> {
                episodePodlove.getSimpleChapters().add(simpleChapter);
            })
        );
    }

}
