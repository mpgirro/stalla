package dev.stalla.model;

import dev.stalla.builder.ITunesStyleCategoryBuilder;
import dev.stalla.model.itunes.ITunesStyleCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ItunesStyleCategoryBuilderFactoryTest {

    @Test
    @DisplayName("should build an ItunesStyleCategory model using builder factory methods only")
    public void testEpisodeBuilderFactory() {
        ITunesStyleCategoryBuilder iTunesStyleCategoryBuilder = ITunesStyleCategory.builder()
            .category("category");

        assertNotNull(iTunesStyleCategoryBuilder.build());
    }

}
