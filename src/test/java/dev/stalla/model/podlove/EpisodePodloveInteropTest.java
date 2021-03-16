package dev.stalla.model.podlove;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static dev.stalla.model.EpisodeFixturesKt.aPodloveSimpleChapter;
import static dev.stalla.model.EpisodeFixturesKt.anEpisodePodlove;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EpisodePodloveInteropTest {

    private final EpisodePodlove episodePodlove;

    public EpisodePodloveInteropTest() {
        // Add extra elements to list properties, because for a single element
        // Kotlin's listOf() method produces an unmodifiable list by default
        episodePodlove = EpisodePodlove.builder()
            .applyFrom(anEpisodePodlove())
            .addSimpleChapterBuilder(SimpleChapter.builder().applyFrom(aPodloveSimpleChapter()))
            .build();
    }

    @Test
    @DisplayName("should build an unmodifiable list of Episode Podlove simplechapters")
    void shouldBuildUnmodifiableEpisodePodloveSimpleChapters() {
        final List<SimpleChapter> simpleChaptes = requireNonNull(episodePodlove.getSimpleChapters());
        assertThrows(UnsupportedOperationException.class, () -> simpleChaptes.add(aPodloveSimpleChapter()));
    }

}
