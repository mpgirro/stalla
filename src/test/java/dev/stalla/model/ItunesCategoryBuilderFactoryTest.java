package dev.stalla.model;

import dev.stalla.builder.ItunesCategoryBuilder;
import dev.stalla.model.itunes.ItunesCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ItunesCategoryBuilderFactoryTest {

    @Test
    @DisplayName("should build an ItunesStyleCategory model using builder factory methods only")
    public void testEpisodeBuilderFactory() {
        ItunesCategoryBuilder itunesCategoryBuilder = ItunesCategory.builder()
            .category("category");

        assertNotNull(itunesCategoryBuilder.build());
    }

}
