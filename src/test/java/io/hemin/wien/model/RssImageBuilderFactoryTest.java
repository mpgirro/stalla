package io.hemin.wien.model;

import io.hemin.wien.builder.RssImageBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RssImageBuilderFactoryTest {

    @Test
    @DisplayName("should build an RssImage model using builder factory methods only")
    public void testEpisodeBuilderFactory() {
        RssImageBuilder rssImageBuilder = RssImage.builder()
            .title("title")
            .link("link")
            .url("url");

        assertNotNull(rssImageBuilder.build());
    }

}
