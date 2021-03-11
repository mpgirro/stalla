package dev.stalla.model;

import dev.stalla.model.atom.Atom;
import dev.stalla.model.atom.AtomPerson;
import dev.stalla.model.atom.Link;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static dev.stalla.model.FixturesKt.aLink;
import static dev.stalla.model.FixturesKt.anAtomPerson;
import static dev.stalla.model.podcast.PodcastFixturesKt.aPodcastAtom;
import static org.junit.jupiter.api.Assertions.*;

public class AtomInteropTest {

    private static Atom atom;
    private static final AtomPerson person = anAtomPerson();
    private static final Link link = aLink();

    @BeforeAll
    public static void init(){
        // Add extra elements to list properties, because for a single element
        // Kotlin's listOf() method produces an unmodifiable list by default
        atom = Atom.builder().applyFrom(aPodcastAtom())
            .addAuthorBuilder(AtomPerson.builder().applyFrom(person))
            .addContributorBuilder(AtomPerson.builder().applyFrom(person))
            .addLinkBuilder(Link.builder().applyFrom(link))
            .build();
    }

    @Test
    @DisplayName("should build an Atom model that exposes an unmodifiable list of the authors")
    public void testAtomBuilderUnmodifiableAuthorsFactory() {
        assertAll("Should fail to add to author list",
            () -> assertNotNull(atom),
            () -> assertNotNull(atom.getAuthors()),
            () -> assertThrows(UnsupportedOperationException.class, () -> {
                atom.getAuthors().add(person);
            })
        );
    }

    @Test
    @DisplayName("should build an Atom model that exposes an unmodifiable list of the contributors")
    public void testAtomBuilderUnmodifiableContributorsFactory() {
        assertAll("Should fail to add to contributor list",
            () -> assertNotNull(atom),
            () -> assertNotNull(atom.getContributors()),
            () -> assertThrows(UnsupportedOperationException.class, () -> {
                atom.getContributors().add(person);
            })
        );
    }

    @Test
    @DisplayName("should build an Atom model that exposes an unmodifiable list of the links")
    public void testAtomBuilderUnmodifiableLinksFactory() {
        assertAll("Should fail to add to link list",
            () -> assertNotNull(atom),
            () -> assertNotNull(atom.getLinks()),
            () -> assertThrows(UnsupportedOperationException.class, () -> {
                atom.getLinks().add(link);
            })
        );
    }

}
