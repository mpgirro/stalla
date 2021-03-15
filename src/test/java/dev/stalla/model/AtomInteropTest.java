package dev.stalla.model;

import dev.stalla.model.atom.Atom;
import dev.stalla.model.atom.AtomPerson;
import dev.stalla.model.atom.Link;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static dev.stalla.model.FixturesKt.aLink;
import static dev.stalla.model.FixturesKt.anAtomPerson;
import static dev.stalla.model.podcast.PodcastFixturesKt.aPodcastAtom;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;

public class AtomInteropTest {

    private static Atom atom;

    @BeforeAll
    static void init() {
        // Add extra elements to list properties, because for a single element
        // Kotlin's listOf() method produces an unmodifiable list by default
        atom = Atom.builder().applyFrom(aPodcastAtom())
            .addAuthorBuilder(AtomPerson.builder().applyFrom(anAtomPerson()))
            .addContributorBuilder(AtomPerson.builder().applyFrom(anAtomPerson()))
            .addLinkBuilder(Link.builder().applyFrom(aLink()))
            .build();
    }

    @Test
    @DisplayName("should build an unmodifiable list of Atom authors")
    void shouldBuildUnmodifiableAtomAuthors() {
        final List<AtomPerson> authors = requireNonNull(atom.getAuthors());
        assertThrows(UnsupportedOperationException.class, () -> authors.add(anAtomPerson()));
    }

    @Test
    @DisplayName("should build an unmodifiable list of Atom contributors")
    void shouldBuildUnmodifiableAtomContributors() {
        final List<AtomPerson> contributors = requireNonNull(atom.getContributors());
        assertThrows(UnsupportedOperationException.class, () -> contributors.add(anAtomPerson()));
    }

    @Test
    @DisplayName("should build an unmodifiable list of Atom links")
    void shouldBuildUnmodifiableAtomLinks() {
        final List<Link> links = requireNonNull(atom.getLinks());
        assertThrows(UnsupportedOperationException.class, () -> links.add(aLink()));
    }

}
