package io.hemin.wien.model;

import io.hemin.wien.builder.RssCategoryBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RssCategoryBuilderFactoryTest {

    @Test
    @DisplayName("should build an RssCategory model using builder factory methods only")
    public void testEpisodeBuilderFactory() {
        RssCategoryBuilder rssCategoryBuilder = RssCategory.builder()
            .category("category");

        assertNotNull(rssCategoryBuilder.build());
    }

}
