package dev.stalla.model;

import dev.stalla.builder.episode.*;
import dev.stalla.model.googleplay.EpisodeGoogleplay;
import dev.stalla.model.itunes.EpisodeItunes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EpisodeBuilderFactoryTest {

    @Test
    @DisplayName("should build an Episode model using builder factory methods only")
    public void testEpisodeBuilderFactory() {
        EpisodeEnclosureBuilder episodeEnclosureBuilder = Episode.Enclosure.builder()
            .url("url")
            .length(123)
            .type("type");

        EpisodeBuilder episodeBuilder = Episode.builder()
            .title("title")
            .enclosureBuilder(episodeEnclosureBuilder);

        assertNotNull(episodeBuilder.build());
    }

    @Test
    @DisplayName("should build an Episode.Enclosure model using builder factory methods only")
    public void testEpisodeEnclosureBuilderFactory() {
        EpisodeEnclosureBuilder episodeEnclosureBuilder = Episode.Enclosure.builder()
            .url("url")
            .length(123)
            .type("type");

        assertNotNull(episodeEnclosureBuilder.build());
    }

    @Test
    @DisplayName("should build an Episode.Guid model using builder factory methods only")
    public void testEpisodeGuidBuilderFactory() {
        EpisodeGuidBuilder episodeGuidBuilder = Episode.Guid.builder()
            .textContent("content");

        assertNotNull(episodeGuidBuilder.build());
    }

    @Test
    @DisplayName("should build an Episode.Content model using builder factory methods only")
    public void testEpisodeContentBuilderFactory() {
        EpisodeContentBuilder episodeContentBuilder = Episode.Content.builder()
            .encoded("encoded");

        assertNotNull(episodeContentBuilder.build());
    }

    @Test
    @DisplayName("should build an EpisodeItunes model using builder factory methods only")
    public void testEpisodeItunesBuilderFactory() {
        EpisodeITunesBuilder episodeITunesBuilder = EpisodeItunes.builder()
            .title("title");

        assertNotNull(episodeITunesBuilder.build());
    }

    @Test
    @DisplayName("should build an EpisodeGoogleplay model model using builder factory methods only")
    public void testEpisodeGooglePlayBuilderFactory() {
        EpisodeGooglePlayBuilder episodeGooglePlayBuilder = EpisodeGoogleplay.builder()
            .explicit(false);

        assertNotNull(episodeGooglePlayBuilder.build());
    }

    @Test
    @DisplayName("should build an Episode.Podlove model using builder factory methods only")
    public void testEpisodePodloveBuilderFactory() {
        EpisodePodloveSimpleChapterBuilder episodePodloveSimpleChapterBuilder = Episode.Podlove.SimpleChapter.builder()
            .title("title")
            .start("start");

        EpisodePodloveBuilder episodePodloveBuilder = Episode.Podlove.builder()
            .addSimpleChapterBuilder(episodePodloveSimpleChapterBuilder);

        assertNotNull(episodePodloveBuilder.build());
    }

    @Test
    @DisplayName("should build an Episode.Podlove.SimpleChapter model using builder factory methods only")
    public void testEpisodePodloveSimpleChapterBuilderFactory() {
        EpisodePodloveSimpleChapterBuilder episodePodloveSimpleChapterBuilder = Episode.Podlove.SimpleChapter.builder()
            .title("title")
            .start("start");

        assertNotNull(episodePodloveSimpleChapterBuilder.build());
    }

    @Test
    @DisplayName("should build an Episode.Bitlove model using builder factory methods only")
    public void testEpisodeBitloveBuilderFactory() {
        EpisodeBitloveBuilder episodeBitloveBuilder = Episode.Bitlove.builder()
            .guid("guid");

        assertNotNull(episodeBitloveBuilder.build());
    }

    @Test
    @DisplayName("should build an Episode.Podcast model using builder factory methods only")
    public void testEpisodePodcastBuilderFactory() {
        EpisodePodcastSoundbiteBuilder episodePodcastSoundbiteBuilder = Episode.Podcast.Soundbite.builder()
            .startTime(Duration.ZERO)
            .duration(Duration.ZERO);

        EpisodePodcastBuilder episodePodcastBuilder = Episode.Podcast.builder()
            .addSoundbiteBuilder(episodePodcastSoundbiteBuilder);

        assertNotNull(episodePodcastBuilder.build());
    }

    @Test
    @DisplayName("should build an Episode.Podcast.Transcript model using builder factory methods only")
    public void testEpisodePodcastTranscriptBuilderFactory() {
        EpisodePodcastTranscriptBuilder episodePodcastTranscriptBuilder = Episode.Podcast.Transcript.builder()
            .url("url")
            .type(Episode.Podcast.Transcript.Type.PLAIN_TEXT);

        assertNotNull(episodePodcastTranscriptBuilder.build());
    }

    @Test
    @DisplayName("should build an Episode.Podcast.Chapter model using builder factory methods only")
    public void testEpisodePodcastChapterBuilderFactory() {
        EpisodePodcastChaptersBuilder episodePodcastChaptersBuilder = Episode.Podcast.Chapters.builder()
            .url("url")
            .type("type");

        assertNotNull(episodePodcastChaptersBuilder.build());
    }

    @Test
    @DisplayName("should build an Episode.Podcast.Soundbite model using builder factory methods only")
    public void testEpisodePodcastSoundbiteBuilderFactory() {
        EpisodePodcastSoundbiteBuilder episodePodcastSoundbiteBuilder = Episode.Podcast.Soundbite.builder()
            .startTime(Duration.ZERO)
            .duration(Duration.ZERO.plusMinutes(1));

        assertNotNull(episodePodcastSoundbiteBuilder.build());
    }

}
