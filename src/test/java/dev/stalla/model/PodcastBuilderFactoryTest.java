package dev.stalla.model;

import dev.stalla.builder.HrefOnlyImageBuilder;
import dev.stalla.builder.ItunesStyleCategoryBuilder;
import dev.stalla.builder.episode.EpisodeBuilder;
import dev.stalla.builder.episode.EpisodeEnclosureBuilder;
import dev.stalla.builder.podcast.*;
import dev.stalla.model.feedpress.Feedpress;
import dev.stalla.model.fyyd.Fyyd;
import dev.stalla.model.googleplay.PodcastGoogleplay;
import dev.stalla.model.itunes.ItunesStyleCategory;
import dev.stalla.model.itunes.PodcastItunes;
import dev.stalla.model.podcastns.Funding;
import dev.stalla.model.podcastns.Locked;
import dev.stalla.model.podcastns.PodcastPodcast;
import dev.stalla.model.rss.Enclosure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PodcastBuilderFactoryTest {

    @Test
    @DisplayName("should build a Podcast model using builder factory methods only")
    public void testPodcastBuilderFactory() {
        EpisodeEnclosureBuilder episodeEnclosureBuilder = Enclosure.builder()
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
    @DisplayName("should build a PodcastItunes model using builder factory methods only")
    public void testPodcastItunesBuilderFactory() {
        ItunesStyleCategoryBuilder itunesStyleCategoryBuilder = ItunesStyleCategory.builder()
            .category("category");

        HrefOnlyImageBuilder hrefOnlyImageBuilder = HrefOnlyImage.builder()
            .href("href");

        PodcastItunesBuilder podcastItunesBuilder = PodcastItunes.builder()
            .explicit(false)
            .addCategoryBuilder(itunesStyleCategoryBuilder)
            .imageBuilder(hrefOnlyImageBuilder);

        assertNotNull(podcastItunesBuilder.build());
    }

    @Test
    @DisplayName("should build a PodcastGoogleplay model using builder factory methods only")
    public void testPodcastGooglePlayBuilderFactory() {
        PodcastGoogleplayBuilder podcastGoogleplayBuilder = PodcastGoogleplay.builder()
            .author("author");

        assertNotNull(podcastGoogleplayBuilder.build());
    }

    @Test
    @DisplayName("should build a Podcast.Fyyd model using builder factory methods only")
    public void testPodcastFyydBuilderFactory() {
        PodcastFyydBuilder podcastFyydBuilder = Fyyd.builder()
            .verify("verify");

        assertNotNull(podcastFyydBuilder.build());
    }

    @Test
    @DisplayName("should build a Podcast.Feedpress model using builder factory methods only")
    public void testPodcastFeedpressBuilderFactory() {
        PodcastFeedpressBuilder podcastFeedpressBuilder = Feedpress.builder()
            .locale("locale");

        assertNotNull(podcastFeedpressBuilder.build());
    }

    @Test
    @DisplayName("should build a Podcast.Podcast model using builder factory methods only")
    public void testPodcastPodcastBuilderFactory() {
        PodcastPodcastFundingBuilder podcastFundingBuilder = Funding.builder()
            .url("url")
            .message("message");

        PodcastPodcastBuilder podcastPodcastBuilder = PodcastPodcast.builder()
            .addFundingBuilder(podcastFundingBuilder);

        assertNotNull(podcastPodcastBuilder.build());
    }

    @Test
    @DisplayName("should build a Podcast.Podcast.Locked model using builder factory methods only")
    public void testPodcastPodcastLockedBuilderFactory() {
        PodcastPodcastLockedBuilder podcastLockedBuilder = Locked.builder()
            .locked(false)
            .owner("owner");

        assertNotNull(podcastLockedBuilder.build());
    }

    @Test
    @DisplayName("should build a Podcast.Podcast.Funding model using builder factory methods only")
    public void testPodcastPodcastFundingBuilderFactory() {
        PodcastPodcastFundingBuilder podcastFundingBuilder = Funding.builder()
            .url("url")
            .message("message");

        assertNotNull(podcastFundingBuilder.build());
    }

}
