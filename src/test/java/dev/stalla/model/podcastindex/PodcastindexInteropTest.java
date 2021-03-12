package dev.stalla.model.podcastindex;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static dev.stalla.model.episode.EpisodeFixturesKt.*;
import static dev.stalla.model.podcast.PodcastFixturesKt.aPodcastPodcastindex;
import static dev.stalla.model.podcast.PodcastFixturesKt.aPodcastPodcastindexFunding;
import static org.junit.jupiter.api.Assertions.*;

public class PodcastindexInteropTest {

    private static EpisodePodcastindex episodePodcastindex;
    private static PodcastPodcastindex podcastPodcastindex;
    private static final Funding funding = aPodcastPodcastindexFunding();
    private static final Soundbite soundbite = anEpisodePodcastindexSoundbite();
    private static final Transcript transcript = anEpisodePodcastindexTranscript();

    @BeforeAll
    public static void init() {
        // Add extra elements to list properties, because for a single element
        // Kotlin's listOf() method produces an unmodifiable list by default
        podcastPodcastindex = PodcastPodcastindex.builder()
            .applyFrom(aPodcastPodcastindex())
            .addFundingBuilder(Funding.builder().applyFrom(funding))
            .build();
        episodePodcastindex = EpisodePodcastindex.builder()
            .applyFrom(anEpisodePodcastindex())
            .addSoundbiteBuilder(Soundbite.builder().applyFrom(soundbite))
            .addTranscriptBuilder(Transcript.builder().applyFrom(transcript))
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

    @Test
    @DisplayName("should build an Episode Podcastindex that exposes an unmodifiable list of the soundbites")
    public void testEpisodePodcastindexBuilderUnmodifiableSoundbites() {
        assertAll("Should fail to add to soundbite list",
            () -> assertNotNull(episodePodcastindex),
            () -> assertNotNull(episodePodcastindex.getSoundbites()),
            () -> assertThrows(UnsupportedOperationException.class, () -> {
                episodePodcastindex.getSoundbites().add(soundbite);
            })
        );
    }

    @Test
    @DisplayName("should build an Episode Podcastindex that exposes an unmodifiable list of the transcripts")
    public void testEpisodePodcastindexBuilderUnmodifiableTranscripts() {
        assertAll("Should fail to add to transcript list",
            () -> assertNotNull(episodePodcastindex),
            () -> assertNotNull(episodePodcastindex.getTranscripts()),
            () -> assertThrows(UnsupportedOperationException.class, () -> {
                episodePodcastindex.getTranscripts().add(transcript);
            })
        );
    }

}
