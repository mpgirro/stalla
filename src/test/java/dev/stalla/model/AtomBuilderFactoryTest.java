package dev.stalla.model;

import dev.stalla.builder.AtomBuilder;
import dev.stalla.builder.AtomPersonBuilder;
import dev.stalla.model.atom.Atom;
import dev.stalla.model.atom.AtomPerson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AtomBuilderFactoryTest {

    @Test
    @DisplayName("should build an Atom model using builder factory methods only")
    public void testEpisodeBuilderFactory() {
        AtomPersonBuilder personBuilder = AtomPerson.builder()
            .name("name");

        AtomBuilder atomBuilder = Atom.builder()
            .addAuthorBuilder(personBuilder);

        assertNotNull(atomBuilder.build());
    }

}
