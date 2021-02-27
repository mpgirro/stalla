package dev.stalla.model;

import dev.stalla.builder.HrefOnlyImageBuilder;
import dev.stalla.builder.episode.EpisodeBuilder;
import dev.stalla.builder.episode.EpisodeEnclosureBuilder;
import dev.stalla.builder.podcast.*;
import dev.stalla.model.feedpress.Feedpress;
import dev.stalla.model.fyyd.Fyyd;
import dev.stalla.model.googleplay.PodcastGoogleplay;
import dev.stalla.model.itunes.ItunesCategory;
import dev.stalla.model.itunes.PodcastItunes;
import dev.stalla.model.podcastindex.Funding;
import dev.stalla.model.podcastindex.Locked;
import dev.stalla.model.podcastindex.PodcastPodcastindex;
import dev.stalla.model.rss.Enclosure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Locale;

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
            .language(Locale.GERMAN)
            .addEpisodeBuilder(episodeBuilder);

        assertNotNull(podcastBuilder.build());
    }

    @Test
    @DisplayName("should build a Podcast Itunes model using builder factory methods only")
    public void testPodcastItunesBuilderFactory() {
        HrefOnlyImageBuilder hrefOnlyImageBuilder = HrefOnlyImage.builder()
            .href("href");

        PodcastItunesBuilder podcastItunesBuilder = PodcastItunes.builder()
            .explicit(false)
            .addCategory(ItunesCategory.NEWS)
            .imageBuilder(hrefOnlyImageBuilder);

        assertNotNull(podcastItunesBuilder.build());
    }

    @Test
    @DisplayName("should build a Podcast Googleplay model using builder factory methods only")
    public void testPodcastGooglePlayBuilderFactory() {
        PodcastGoogleplayBuilder podcastGoogleplayBuilder = PodcastGoogleplay.builder()
            .author("author");

        assertNotNull(podcastGoogleplayBuilder.build());
    }

    @Test
    @DisplayName("should build a Podcast Fyyd model using builder factory methods only")
    public void testPodcastFyydBuilderFactory() {
        PodcastFyydBuilder podcastFyydBuilder = Fyyd.builder()
            .verify("verify");

        assertNotNull(podcastFyydBuilder.build());
    }

    @Test
    @DisplayName("should build a Podcast Feedpress model using builder factory methods only")
    public void testPodcastFeedpressBuilderFactory() {
        PodcastFeedpressBuilder podcastFeedpressBuilder = Feedpress.builder()
            .locale("locale");

        assertNotNull(podcastFeedpressBuilder.build());
    }

    @Test
    @DisplayName("should build a Podcast Podcastindex model using builder factory methods only")
    public void testPodcastPodcastBuilderFactory() {
        PodcastPodcastindexFundingBuilder podcastFundingBuilder = Funding.builder()
            .url("url")
            .message("message");

        PodcastPodcastindexBuilder podcastPodcastindexBuilder = PodcastPodcastindex.builder()
            .addFundingBuilder(podcastFundingBuilder);

        assertNotNull(podcastPodcastindexBuilder.build());
    }

    @Test
    @DisplayName("should build a Podcast Podcastindex Locked model using builder factory methods only")
    public void testPodcastPodcastLockedBuilderFactory() {
        PodcastPodcastindexLockedBuilder podcastLockedBuilder = Locked.builder()
            .locked(false)
            .owner("owner");

        assertNotNull(podcastLockedBuilder.build());
    }

    @Test
    @DisplayName("should build a Podcast Podcastindex Funding model using builder factory methods only")
    public void testPodcastPodcastFundingBuilderFactory() {
        PodcastPodcastindexFundingBuilder podcastFundingBuilder = Funding.builder()
            .url("url")
            .message("message");

        assertNotNull(podcastFundingBuilder.build());
    }

}
