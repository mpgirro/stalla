package dev.stalla.model.podlove;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static dev.stalla.model.episode.EpisodeFixturesKt.aPodloveSimpleChapter;
import static dev.stalla.model.episode.EpisodeFixturesKt.anEpisodePodlove;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;

public class EpisodePodloveInteropTest {

    private static EpisodePodlove episodePodlove;

    @BeforeAll
    public static void init() {
        // Add extra elements to list properties, because for a single element
        // Kotlin's listOf() method produces an unmodifiable list by default
        episodePodlove = EpisodePodlove.builder()
            .applyFrom(anEpisodePodlove())
            .addSimpleChapterBuilder(SimpleChapter.builder().applyFrom(aPodloveSimpleChapter()))
            .build();
    }

    @Test
    @DisplayName("should build an unmodifiable list of Episode Podlove simplechapters")
    public void testEpisodePodloveBuilderUnmodifiableSimpleChapters() {
        final List<SimpleChapter> simpleChaptes = requireNonNull(episodePodlove.getSimpleChapters());
        assertThrows(UnsupportedOperationException.class, () -> simpleChaptes.add(aPodloveSimpleChapter()));
    }

}
