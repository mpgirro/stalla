package dev.stalla.model.podcastindex;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static dev.stalla.model.PodcastFixturesKt.aPodcastPodcastindex;
import static dev.stalla.model.PodcastFixturesKt.aPodcastPodcastindexFunding;
import static dev.stalla.model.PodcastFixturesKt.aPodcastPodcastindexPerson;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PodcastPodcastindexInteropTest {

    private final PodcastPodcastindex podcastPodcastindex;

    public PodcastPodcastindexInteropTest() {
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

    @Test
    @DisplayName("should build an unmodifiable list of Podcast Podcastindex persons")
    void shouldParsePodcastPodcastindexUnmodifiablePerson() {
        final List<PodcastindexPerson> personList = requireNonNull(podcastPodcastindex.getPersons());
        assertThrows(UnsupportedOperationException.class, () -> personList.add(aPodcastPodcastindexPerson()));
    }

}
