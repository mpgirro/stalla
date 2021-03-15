package dev.stalla.model.podcastindex;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static dev.stalla.model.podcast.PodcastFixturesKt.aPodcastPodcastindex;
import static dev.stalla.model.podcast.PodcastFixturesKt.aPodcastPodcastindexFunding;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;

public class PodcastPodcastindexInteropTest {

    private static PodcastPodcastindex podcastPodcastindex;

    @BeforeAll
    static void init() {
        // Add extra elements to list properties, because for a single element
        // Kotlin's listOf() method produces an unmodifiable list by default
        podcastPodcastindex = PodcastPodcastindex.builder()
            .applyFrom(aPodcastPodcastindex())
            .addFundingBuilder(Funding.builder().applyFrom(aPodcastPodcastindexFunding()))
            .build();
    }

    @Test
    @DisplayName("should build an unmodifiable list of Podcast Podcastindex funding")
    void shouldBuildUnmodifiablePodcastPodcastindexFunding() {
        final List<Funding> fundingList = requireNonNull(podcastPodcastindex.getFunding());
        assertThrows(UnsupportedOperationException.class, () -> fundingList.add(aPodcastPodcastindexFunding()));
    }

}
