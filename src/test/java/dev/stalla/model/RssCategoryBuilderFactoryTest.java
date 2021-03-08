package dev.stalla.model;

import dev.stalla.builder.RssCategoryBuilder;
import dev.stalla.model.rss.RssCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RssCategoryBuilderFactoryTest {

    @Test
    @DisplayName("should build an RssCategory model using builder factory methods only")
    public void testRssCategoryBuilderFactory() {
        RssCategoryBuilder rssCategoryBuilder = RssCategory.builder()
            .category("category");

        assertNotNull(rssCategoryBuilder.build());
    }

}
