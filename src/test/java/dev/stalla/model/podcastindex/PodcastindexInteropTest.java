package dev.stalla.model.podcastindex;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static dev.stalla.model.episode.EpisodeFixturesKt.*;
import static dev.stalla.model.podcast.PodcastFixturesKt.aPodcastPodcastindex;
import static dev.stalla.model.podcast.PodcastFixturesKt.aPodcastPodcastindexFunding;
import static org.junit.jupiter.api.Assertions.*;

public class PodcastindexInteropTest {

    private final EpisodePodcastindex episodePodcastindex = anEpisodePodcastindex();
    private final PodcastPodcastindex podcastPodcastindex = aPodcastPodcastindex();
    private final Funding funding = aPodcastPodcastindexFunding();
    private final Soundbite soundbite = anEpisodePodcastindexSoundbite();
    private final Transcript transcript = anEpisodePodcastindexTranscript();

    @Test
    @DisplayName("should build a Podcast Podcastindex that exposes an unmodifiable list of the funding")
    public void testPodcastPodcastindexBuilderUnmodifiableFunding() {
        assertAll("fail to add to list",
            () -> assertNotNull(podcastPodcastindex.getFunding()),
            () -> assertThrows(UnsupportedOperationException.class, () -> {
                podcastPodcastindex.getFunding().add(funding);
            })
        );
    }

    @Test
    @DisplayName("should build an Episode Podcastindex that exposes an unmodifiable list of the soundbites")
    public void testEpisodePodcastindexBuilderUnmodifiableSoundbites() {
        assertAll("fail to add to list",
            () -> assertNotNull(episodePodcastindex.getSoundbites()),
            () -> assertThrows(UnsupportedOperationException.class, () -> {
                episodePodcastindex.getSoundbites().add(soundbite);
            })
        );
    }

    @Test
    @DisplayName("should build an Episode Podcastindex that exposes an unmodifiable list of the transcripts")
    public void testEpisodePodcastindexBuilderUnmodifiableTranscripts() {
        assertAll("fail to add to list",
            () -> assertNotNull(episodePodcastindex.getTranscripts()),
            () -> assertThrows(UnsupportedOperationException.class, () -> {
                episodePodcastindex.getTranscripts().add(transcript);
            })
        );
    }

}
