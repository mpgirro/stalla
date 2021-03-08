package dev.stalla.model;

import dev.stalla.builder.RssImageBuilder;
import dev.stalla.model.rss.RssImage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RssImageBuilderFactoryTest {

    @Test
    @DisplayName("should build an RssImage model using builder factory methods only")
    public void testRssImageBuilderFactory() {
        RssImageBuilder rssImageBuilder = RssImage.builder()
            .title("title")
            .link("link")
            .url("url");

        assertNotNull(rssImageBuilder.build());
    }

}
