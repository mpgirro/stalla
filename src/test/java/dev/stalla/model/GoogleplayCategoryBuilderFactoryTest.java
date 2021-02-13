package dev.stalla.model;

import dev.stalla.builder.GoogleplayCategoryBuilder;
import dev.stalla.builder.ItunesCategoryBuilder;
import dev.stalla.model.googleplay.GoogleplayCategory;
import dev.stalla.model.itunes.ItunesCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GoogleplayCategoryBuilderFactoryTest {

    @Test
    @DisplayName("should build an GoogleplayCategory model using builder factory methods only")
    public void testEpisodeBuilderFactory() {
        GoogleplayCategoryBuilder googleplayCategoryBuilder = GoogleplayCategory.builder()
            .category("category");

        assertNotNull(googleplayCategoryBuilder.build());
    }

}
