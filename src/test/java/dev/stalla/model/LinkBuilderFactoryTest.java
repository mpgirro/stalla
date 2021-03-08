package dev.stalla.model;

import dev.stalla.builder.LinkBuilder;
import dev.stalla.model.atom.Link;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LinkBuilderFactoryTest {

    @Test
    @DisplayName("should build an Link model using builder factory methods only")
    public void testLinkBuilderFactory() {
        LinkBuilder linkBuilder = Link.builder().href("href");

        assertNotNull(linkBuilder.build());
    }

}
