package dev.stalla.model.podcastindex;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static dev.stalla.model.podcast.PodcastFixturesKt.aPodcastPodcastindex;
import static dev.stalla.model.podcast.PodcastFixturesKt.aPodcastPodcastindexFunding;
import static org.junit.jupiter.api.Assertions.*;

public class PodcastPodcastindexInteropTest {

    private static PodcastPodcastindex podcastPodcastindex;
    private static final Funding funding = aPodcastPodcastindexFunding();

    @BeforeAll
    public static void init() {
        // Add extra elements to list properties, because for a single element
        // Kotlin's listOf() method produces an unmodifiable list by default
        podcastPodcastindex = PodcastPodcastindex.builder()
            .applyFrom(aPodcastPodcastindex())
            .addFundingBuilder(Funding.builder().applyFrom(funding))
            .build();
    }

    @Test
    @DisplayName("should build a Podcast Podcastindex that exposes an unmodifiable list of the funding")
    public void testPodcastPodcastindexBuilderUnmodifiableFunding() {
        assertAll("Should fail to add to funding list",
            () -> assertNotNull(podcastPodcastindex),
            () -> assertNotNull(podcastPodcastindex.getFunding()),
            () -> assertThrows(UnsupportedOperationException.class, () -> {
                podcastPodcastindex.getFunding().add(funding);
            })
        );
    }

}
