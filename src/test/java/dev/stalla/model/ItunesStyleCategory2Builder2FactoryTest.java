package dev.stalla.model;

import dev.stalla.builder.ItunesStyleCategoryBuilder2;
import dev.stalla.model.itunes.ItunesStyleCategory2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ItunesStyleCategory2Builder2FactoryTest {

    @Test
    @DisplayName("should build an ItunesStyleCategory model using builder factory methods only")
    public void testEpisodeBuilderFactory() {
        ItunesStyleCategoryBuilder2 itunesStyleCategoryBuilder = ItunesStyleCategory2.builder()
            .category("category");

        assertNotNull(itunesStyleCategoryBuilder.build());
    }

}
