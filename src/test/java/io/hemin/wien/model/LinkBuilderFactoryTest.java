package io.hemin.wien.model;

import io.hemin.wien.builder.LinkBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LinkBuilderFactoryTest {

    @Test
    @DisplayName("should build an Link model using builder factory methods only")
    public void testEpisodeBuilderFactory() {
        LinkBuilder linkBuilder = Link.builder().href("href");

        assertNotNull(linkBuilder.build());
    }

}
