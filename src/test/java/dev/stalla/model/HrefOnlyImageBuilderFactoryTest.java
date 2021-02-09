package dev.stalla.model;

import dev.stalla.builder.HrefOnlyImageBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HrefOnlyImageBuilderFactoryTest {

    @Test
    @DisplayName("should build an HrefOnlyImage model using builder factory methods only")
    public void testEpisodeBuilderFactory() {
        HrefOnlyImageBuilder hrefOnlyImageBuilder = HrefOnlyImage.builder()
            .href("href");

        assertNotNull(hrefOnlyImageBuilder.build());
    }

}
