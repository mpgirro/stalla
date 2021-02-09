package dev.stalla.model;

import dev.stalla.builder.HrefOnlyImageBuilder;
import dev.stalla.builder.ITunesStyleCategoryBuilder;
import dev.stalla.builder.episode.EpisodeBuilder;
import dev.stalla.builder.episode.EpisodeEnclosureBuilder;
import dev.stalla.builder.podcast.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PodcastBuilderFactoryTest {

    @Test
    @DisplayName("should build a Podcast model using builder factory methods only")
    public void testPodcastBuilderFactory() {
        EpisodeEnclosureBuilder episodeEnclosureBuilder = Episode.Enclosure.builder()
            .url("url")
            .length(123)
            .type("type");

        EpisodeBuilder episodeBuilder = Episode.builder()
            .title("title")
            .enclosureBuilder(episodeEnclosureBuilder);

        PodcastBuilder podcastBuilder = Podcast.builder()
            .title("title")
            .description("description")
            .link("link")
            .language("language")
            .addEpisodeBuilder(episodeBuilder);

        assertNotNull(podcastBuilder.build());
    }

    @Test
    @DisplayName("should build a Podcast.Itunes model using builder factory methods only")
    public void testPodcastItunesBuilderFactory() {
        ITunesStyleCategoryBuilder iTunesStyleCategoryBuilder = ITunesStyleCategory.builder()
            .category("category");

        HrefOnlyImageBuilder hrefOnlyImageBuilder = HrefOnlyImage.builder()
            .href("href");

        PodcastITunesBuilder podcastITunesBuilder = Podcast.ITunes.builder()
            .explicit(false)
            .addCategoryBuilder(iTunesStyleCategoryBuilder)
            .imageBuilder(hrefOnlyImageBuilder);

        assertNotNull(podcastITunesBuilder.build());
    }

    @Test
    @DisplayName("should build a Podcast.GooglePlay model using builder factory methods only")
    public void testPodcastGooglePlayBuilderFactory() {
        PodcastGooglePlayBuilder podcastGooglePlayBuilder = Podcast.GooglePlay.builder()
            .author("author");

        assertNotNull(podcastGooglePlayBuilder.build());
    }

    @Test
    @DisplayName("should build a Podcast.Fyyd model using builder factory methods only")
    public void testPodcastFyydBuilderFactory() {
        PodcastFyydBuilder podcastFyydBuilder = Podcast.Fyyd.builder()
            .verify("verify");

        assertNotNull(podcastFyydBuilder.build());
    }

    @Test
    @DisplayName("should build a Podcast.Feedpress model using builder factory methods only")
    public void testPodcastFeedpressBuilderFactory() {
        PodcastFeedpressBuilder podcastFeedpressBuilder = Podcast.Feedpress.builder()
            .locale("locale");

        assertNotNull(podcastFeedpressBuilder.build());
    }

    @Test
    @DisplayName("should build a Podcast.Podcast model using builder factory methods only")
    public void testPodcastPodcastBuilderFactory() {
        PodcastPodcastFundingBuilder podcastFundingBuilder = Podcast.Podcast.Funding.builder()
            .url("url")
            .message("message");

        PodcastPodcastBuilder podcastPodcastBuilder = Podcast.Podcast.builder()
            .addFundingBuilder(podcastFundingBuilder);

        assertNotNull(podcastPodcastBuilder.build());
    }

    @Test
    @DisplayName("should build a Podcast.Podcast.Locked model using builder factory methods only")
    public void testPodcastPodcastLockedBuilderFactory() {
        PodcastPodcastLockedBuilder podcastLockedBuilder = Podcast.Podcast.Locked.builder()
            .locked(false)
            .owner("owner");

        assertNotNull(podcastLockedBuilder.build());
    }

    @Test
    @DisplayName("should build a Podcast.Podcast.Funding model using builder factory methods only")
    public void testPodcastPodcastFundingBuilderFactory() {
        PodcastPodcastFundingBuilder podcastFundingBuilder = Podcast.Podcast.Funding.builder()
            .url("url")
            .message("message");

        assertNotNull(podcastFundingBuilder.build());
    }

}
