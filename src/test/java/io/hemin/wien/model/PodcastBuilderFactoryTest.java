package io.hemin.wien.model;

import io.hemin.wien.builder.podcast.PodcastAtomBuilder;
import io.hemin.wien.builder.podcast.PodcastBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PodcastBuilderFactoryTest {

    @Test
    public void testPodcastBuilderFactory() {
        // This test demonstrates the usage of the factory function in Java.
        // Technically it is not very useful, but we could have made an error in
        // the type parameters of BuilderFactory<D, B> in the companion object
        assertTrue(Podcast.builder() instanceof PodcastBuilder);
    }

    @Test
    public void testPodcastAtomBuilderFactory() {
        assertTrue(Podcast.Atom.builder() instanceof PodcastAtomBuilder);
    }

}
