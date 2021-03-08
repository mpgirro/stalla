package dev.stalla.model;

import dev.stalla.model.atom.Atom;
import dev.stalla.model.atom.AtomPerson;
import dev.stalla.model.atom.Link;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static dev.stalla.model.FixturesKt.aLink;
import static dev.stalla.model.FixturesKt.anAtomPerson;
import static dev.stalla.model.podcast.PodcastFixturesKt.aPodcastAtom;
import static org.junit.jupiter.api.Assertions.*;

public class AtomInteropTest {

    private final AtomPerson person = anAtomPerson();
    private final Link link = aLink();
    private final Atom atom = aPodcastAtom();

    @Test
    @DisplayName("should build an Atom model that exposes an unmodifiable list of the authors")
    public void testAtomBuilderUnmodifiableAuthorsFactory() {
        assertAll("fail to add to list",
            () -> assertNotNull(atom.getAuthors()),
            () -> assertThrows(UnsupportedOperationException.class, () -> {
                atom.getAuthors().add(person);
            })
        );
    }

    @Test
    @DisplayName("should build an Atom model that exposes an unmodifiable list of the contributors")
    public void testAtomBuilderUnmodifiableContributorsFactory() {
        assertAll("fail to add to list",
            () -> assertNotNull(atom.getContributors()),
            () -> assertThrows(UnsupportedOperationException.class, () -> {
                atom.getContributors().add(person);
            })
        );
    }

    @Test
    @DisplayName("should build an Atom model that exposes an unmodifiable list of the links")
    public void testAtomBuilderUnmodifiableLinksFactory() {
        assertAll("fail to add to list",
            () -> assertNotNull(atom.getLinks()),
            () -> assertThrows(UnsupportedOperationException.class, () -> {
                atom.getLinks().add(link);
            })
        );
    }

}
