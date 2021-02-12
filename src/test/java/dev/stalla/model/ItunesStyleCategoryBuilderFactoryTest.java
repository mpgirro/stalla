package dev.stalla.model;

import dev.stalla.builder.ItunesStyleCategoryBuilder;
import dev.stalla.model.itunes.ItunesStyleCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ItunesStyleCategoryBuilderFactoryTest {

    @Test
    @DisplayName("should build an ItunesStyleCategory model using builder factory methods only")
    public void testEpisodeBuilderFactory() {
        ItunesStyleCategoryBuilder itunesStyleCategoryBuilder = ItunesStyleCategory.builder()
            .category("category");

        assertNotNull(itunesStyleCategoryBuilder.build());
    }

}
